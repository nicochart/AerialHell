package fr.factionbedrock.aerialhell.Item.Ability.Module;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.function.ToIntFunction;

public class SideEffectModule extends AbilityModule
{
    private final ModuleAction sideEffect;

    private SideEffectModule(ModuleAction sideEffect, UseSituation useSituation)
    {
        super(useSituation);
        this.sideEffect = sideEffect;
    }

    public void apply(LivingEntity entity, ItemStack itemStack, @Nullable EquipmentSlot equipmentSlot)
    {
        this.sideEffect.apply(entity, itemStack, equipmentSlot);
    }

    public static class DamageItem extends SideEffectModule
    {
        private DamageItem(int amount, UseSituation useSituation)
        {
            super((entity, stack, equipmentSlot) ->
            {
                if (equipmentSlot != null) {stack.hurtAndBreak(amount, entity, equipmentSlot);}
            }, useSituation);
        }

        //note that this will passively damage item over time. every tick if there is no cooldown in passive ability.
        public static DamageItem passive() {return passive(1);}
        public static DamageItem passive(int damageAmount) {return new DamageItem(damageAmount, UseSituation.PASSIVE);}
        //commonly used for ability use damage
        public static DamageItem onUse() {return onUse(1);}
        public static DamageItem onUse(int damageAmount) {return new DamageItem(damageAmount, UseSituation.ON_USE);}
        //note that hurting an enemy already damages the item. Use onHurtEnemy only if you want to add additional damage on ability use.
        public static DamageItem onHurtEnemy() {return onHurtEnemy(1);}
        public static DamageItem onHurtEnemy(int damageAmount) {return new DamageItem(damageAmount, UseSituation.ON_HURT_ENEMY);}
    }

    public static class Cooldown extends SideEffectModule
    {
        private Cooldown(ToIntFunction<LivingEntity> cooldownDuration, UseSituation useSituation)
        {
            super((entity, stack, equipmentSlot) ->
            {
                int cooldown = cooldownDuration.applyAsInt(entity);
                if (entity instanceof Player player && cooldown != 0) {player.getCooldowns().addCooldown(stack, cooldown);}
            }, useSituation);
        }

        public static Cooldown passive(ToIntFunction<LivingEntity> cooldownDuration) {return new Cooldown(cooldownDuration, UseSituation.PASSIVE);}
        public static Cooldown onUse(ToIntFunction<LivingEntity> cooldownDuration) {return new Cooldown(cooldownDuration, UseSituation.ON_USE);}
        public static Cooldown onHurtEnemy(ToIntFunction<LivingEntity> cooldownDuration) {return new Cooldown(cooldownDuration, UseSituation.ON_HURT_ENEMY);}
    }
}
