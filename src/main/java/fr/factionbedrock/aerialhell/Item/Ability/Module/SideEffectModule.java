package fr.factionbedrock.aerialhell.Item.Ability.Module;

import fr.factionbedrock.aerialhell.Item.Ability.AbilityUseSituation;
import fr.factionbedrock.aerialhell.Item.Ability.ModuleAction;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.Enchantments;

import java.util.function.ToIntFunction;

public class SideEffectModule extends AbilityModule
{
    private final ModuleAction sideEffect;

    private SideEffectModule(ModuleAction sideEffect) {super();this.sideEffect = sideEffect;}

    public void apply(AbilityUseSituation useSituation)
    {
        this.sideEffect.apply(useSituation.itemStack, useSituation.itemOwner, useSituation.equipmentSlot, useSituation.damageUseSituationInfo);
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
            super((stack, itemOwner, equipmentSlot, damageInfo) ->
            {
                if (equipmentSlot != null) {stack.hurtAndBreak(amount, itemOwner, equipmentSlot);}
            });
        }

        public static DamageItem simple() {return new DamageItem(1);}
        public static DamageItem withAmount(int amount) {return new DamageItem(amount);}
    }

    public static class Shrink extends SideEffectModule
    {
        private Shrink(int amount)
        {
            super((stack, itemOwner, equipmentSlot, damageInfo) ->
            {
                if (equipmentSlot != null)
                {
                    if (!EntityHelper.isCreativePlayer(itemOwner) && !EntityHelper.hasEnchantment(itemOwner, Enchantments.INFINITY)) {stack.shrink(amount);}
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
            super((stack, itemOwner, equipmentSlot, damageInfo) ->
            {
                int cooldown = cooldownDuration.applyAsInt(itemOwner);
                if (itemOwner instanceof Player player && cooldown != 0) {player.getCooldowns().addCooldown(stack, cooldown);}
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