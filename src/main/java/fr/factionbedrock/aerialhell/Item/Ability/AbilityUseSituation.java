package fr.factionbedrock.aerialhell.Item.Ability;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nullable;

public class AbilityUseSituation
{
    public final ItemStack itemStack;
    public final LivingEntity itemOwner;
    public final @Nullable EquipmentSlot equipmentSlot;
    public final @Nullable LivingEntity enemyEntity;
    public final @Nullable DamageSource damageSource;
    public final AbilityUseSituation.Category category;

    private AbilityUseSituation(ItemStack itemStack, LivingEntity itemOwner, @Nullable EquipmentSlot equipmentSlot, @Nullable LivingEntity enemyEntity, @Nullable DamageSource damageSource, AbilityUseSituation.Category useSituation)
    {
        this.itemStack = itemStack;
        this.itemOwner = itemOwner;
        this.equipmentSlot = equipmentSlot;
        this.enemyEntity = enemyEntity;
        this.damageSource = damageSource;
        this.category = useSituation;
    }

    public static class Tick extends AbilityUseSituation
    {
        public Tick(ItemStack itemStack, LivingEntity itemOwner, @Nullable EquipmentSlot equipmentSlot)
        {
            super(itemStack, itemOwner, equipmentSlot, null, null, Category.TICK);
        }
    }

    public static class OnUse extends AbilityUseSituation
    {
        public OnUse(ItemStack itemStack, LivingEntity itemOwner, @Nullable EquipmentSlot equipmentSlot)
        {
            super(itemStack, itemOwner, equipmentSlot, null, null, Category.ON_USE);
        }
    }

    public static class OnHurtEnemy extends AbilityUseSituation
    {
        public OnHurtEnemy(ItemStack itemStack, LivingEntity itemOwner, @Nullable EquipmentSlot equipmentSlot, @Nullable LivingEntity enemyEntity)
        {
            super(itemStack, itemOwner, equipmentSlot, enemyEntity, null, Category.ON_HURT_ENEMY);
        }
    }

    public static class OnTakeDamage extends AbilityUseSituation
    {
        public OnTakeDamage(ItemStack itemStack, LivingEntity itemOwner, @Nullable EquipmentSlot equipmentSlot, @Nullable LivingEntity enemyEntity, @Nullable DamageSource damageSource)
        {
            super(itemStack, itemOwner, equipmentSlot, enemyEntity, damageSource, Category.ON_TAKE_DAMAGE);
        }
    }

    public enum Category {TICK, ON_USE, ON_HURT_ENEMY, ON_TAKE_DAMAGE}
}
