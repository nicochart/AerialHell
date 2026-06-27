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
    public final @Nullable ReleaseUsingUseSituationInfo releaseUsingUseSituationInfo;
    public final @Nullable DamageUseSituationInfo damageUseSituationInfo;
    public final @Nullable MiningUseSituationInfo miningUseSituationInfo;
    public final AbilityUseSituation.Category category;

    private AbilityUseSituation(ItemStack itemStack, LivingEntity itemOwner, @Nullable EquipmentSlot equipmentSlot, @Nullable ReleaseUsingUseSituationInfo releaseUsingUseSituationInfo, @Nullable DamageUseSituationInfo damageUseSituationInfo, @Nullable MiningUseSituationInfo miningUseSituationInfo, AbilityUseSituation.Category useSituation)
    {
        this.itemStack = itemStack;
        this.itemOwner = itemOwner;
        this.equipmentSlot = equipmentSlot;
        this.releaseUsingUseSituationInfo = releaseUsingUseSituationInfo;
        this.damageUseSituationInfo = damageUseSituationInfo;
        this.miningUseSituationInfo = miningUseSituationInfo;
        this.category = useSituation;
    }

    public static class Tick extends AbilityUseSituation
    {
        public Tick(ItemStack itemStack, LivingEntity itemOwner, @Nullable EquipmentSlot equipmentSlot)
        {
            super(itemStack, itemOwner, equipmentSlot, null, null, null, Category.TICK);
        }
    }

    public static class OnUse extends AbilityUseSituation
    {
        public OnUse(ItemStack itemStack, LivingEntity itemOwner, @Nullable EquipmentSlot equipmentSlot)
        {
            super(itemStack, itemOwner, equipmentSlot, null, null, null, Category.ON_USE);
        }
    }

    public static class OnReleaseUsing extends AbilityUseSituation
    {
        public OnReleaseUsing(ItemStack itemStack, LivingEntity itemOwner, ReleaseUsingUseSituationInfo releaseUsingUseSituationInfo)
        {
            super(itemStack, itemOwner, EquipmentSlot.MAINHAND, releaseUsingUseSituationInfo, null, null, Category.ON_FINISH_USING);
        }
    }

    public static class OnDealDamage extends AbilityUseSituation
    {
        public OnDealDamage(ItemStack itemStack, LivingEntity itemOwner, @Nullable EquipmentSlot equipmentSlot, DamageUseSituationInfo damageInfo)
        {
            super(itemStack, itemOwner, equipmentSlot, null, damageInfo, null, Category.ON_DEAL_DAMAGE);
        }
    }

    public static class OnTakeDamage extends AbilityUseSituation
    {
        public OnTakeDamage(ItemStack itemStack, LivingEntity itemOwner, @Nullable EquipmentSlot equipmentSlot, DamageUseSituationInfo damageInfo)
        {
            super(itemStack, itemOwner, equipmentSlot, null, damageInfo, null, Category.ON_TAKE_DAMAGE);
        }
    }

    public static class OnMining extends AbilityUseSituation
    {
        public OnMining(ItemStack itemStack, LivingEntity itemOwner, MiningUseSituationInfo miningUseSituationInfo)
        {
            super(itemStack, itemOwner, EquipmentSlot.MAINHAND, null, null, miningUseSituationInfo, Category.ON_MINING);
        }
    }

    public enum Category {TICK, ON_USE, ON_FINISH_USING, ON_DEAL_DAMAGE, ON_TAKE_DAMAGE, ON_MINING}
}
