package fr.factionbedrock.aerialhell.Item.Ability.Module;

import fr.factionbedrock.aerialhell.Item.Ability.ModuleAction;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import org.jetbrains.annotations.Nullable;

import java.util.function.ToIntFunction;

public class SideEffectModule extends AbilityModule
{
    private final ModuleAction sideEffect;

    private SideEffectModule(ModuleAction sideEffect) {super();this.sideEffect = sideEffect;}

    public void apply(LivingEntity entity, ItemStack itemStack, @Nullable EquipmentSlot equipmentSlot)
    {
        this.sideEffect.apply(entity, itemStack, equipmentSlot);
    }

    //this module can be applied in on use modules on an ability. But it can also be put in :
    //passive :
    //- this means that it will passively damage item over time. every tick if there is no cooldown in passive modules.
    //on hurt enemy :
    //- hurting an enemy already damages the item. Use it on hurt enemy only if you want to add additional damage on ability use.
    public static class DamageItem extends SideEffectModule
    {
        private DamageItem(int amount)
        {
            super((entity, stack, equipmentSlot) ->
            {
                if (equipmentSlot != null) {stack.hurtAndBreak(amount, entity, equipmentSlot);}
            });
        }

        public static DamageItem simple() {return new DamageItem(1);}
        public static DamageItem withAmount(int amount) {return new DamageItem(amount);}
    }

    public static class Shrink extends SideEffectModule
    {
        private Shrink(int amount)
        {
            super((entity, stack, equipmentSlot) ->
            {
                if (equipmentSlot != null)
                {
                    if (!EntityHelper.isCreativePlayer(entity) && !EntityHelper.hasEnchantment(entity, Enchantments.INFINITY)) {stack.shrink(amount);}
                }
            });
        }

        public static Shrink.Builder builder() {return new Shrink.Builder();}

        public static class Builder
        {
            public Builder() {}

            public Shrink simple() {return new Shrink(1);}
            public Shrink withAmount(int amount) {return new Shrink(amount);}
        }
    }

    public static class Cooldown extends SideEffectModule
    {
        private final ToIntFunction<LivingEntity> cooldownDuration;
        private Cooldown(ToIntFunction<LivingEntity> cooldownDuration)
        {
            super((entity, stack, equipmentSlot) ->
            {
                int cooldown = cooldownDuration.applyAsInt(entity);
                if (entity instanceof Player player && cooldown != 0) {player.getCooldowns().addCooldown(stack, cooldown);}
            });

            this.cooldownDuration = cooldownDuration;
        }

        public int getCooldownDuration(LivingEntity entity) {return this.cooldownDuration.applyAsInt(entity);}

        public static Cooldown.Builder builder() {return new Builder();}

        public static class Builder
        {
            private Builder() {}

            public SideEffectModule.Cooldown of(int cooldownDuration) {return new SideEffectModule.Cooldown((entity) -> cooldownDuration);}
            public SideEffectModule.Cooldown of(ToIntFunction<LivingEntity> cooldownDuration) {return new SideEffectModule.Cooldown(cooldownDuration);}
        }
    }
}