package fr.factionbedrock.aerialhell.Item.Ability;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nullable;

public class AbilityUseSituation
{
    public final ItemStack itemStack;
    public final LivingEntity itemOwner;
    public final @Nullable EquipmentSlot equipmentSlot;
    public final @Nullable DamageUseSituationInfo damageUseSituationInfo;
    public final AbilityUseSituation.Category category;

    private AbilityUseSituation(ItemStack itemStack, LivingEntity itemOwner, @Nullable EquipmentSlot equipmentSlot, @Nullable DamageUseSituationInfo damageUseSituationInfo, AbilityUseSituation.Category useSituation)
    {
        this.itemStack = itemStack;
        this.itemOwner = itemOwner;
        this.equipmentSlot = equipmentSlot;
        this.damageUseSituationInfo = damageUseSituationInfo;
        this.category = useSituation;
    }

    public static class Tick extends AbilityUseSituation
    {
        public Tick(ItemStack itemStack, LivingEntity itemOwner, @Nullable EquipmentSlot equipmentSlot)
        {
            super(itemStack, itemOwner, equipmentSlot, null, Category.TICK);
        }
    }

    public static class OnUse extends AbilityUseSituation
    {
        public OnUse(ItemStack itemStack, LivingEntity itemOwner, @Nullable EquipmentSlot equipmentSlot)
        {
            super(itemStack, itemOwner, equipmentSlot, null, Category.ON_USE);
        }
    }

    public static class OnDealDamage extends AbilityUseSituation
    {
        public OnDealDamage(ItemStack itemStack, LivingEntity itemOwner, @Nullable EquipmentSlot equipmentSlot, DamageUseSituationInfo damageInfo)
        {
            super(itemStack, itemOwner, equipmentSlot, damageInfo, Category.ON_DEAL_DAMAGE);
        }
    }

    public static class OnTakeDamage extends AbilityUseSituation
    {
        public OnTakeDamage(ItemStack itemStack, LivingEntity itemOwner, @Nullable EquipmentSlot equipmentSlot, DamageUseSituationInfo damageInfo)
        {
            super(itemStack, itemOwner, equipmentSlot, damageInfo, Category.ON_TAKE_DAMAGE);
        }
    }

    public enum Category {TICK, ON_USE, ON_DEAL_DAMAGE, ON_TAKE_DAMAGE}
}
