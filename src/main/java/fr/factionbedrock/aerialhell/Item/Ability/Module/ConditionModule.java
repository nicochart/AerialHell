package fr.factionbedrock.aerialhell.Item.Ability.Module;

import fr.factionbedrock.aerialhell.Item.Ability.*;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.function.Predicate;
import java.util.function.Supplier;

public class ConditionModule extends AbilityModule
{
    private final ModuleCondition condition;

    public ConditionModule(ModuleCondition condition) {super(); this.condition = condition;}

    public static ConditionModule itemOwnerCondition(Predicate<LivingEntity> entityPredicate) {return new ConditionModule((itemStack, itemOwner, equipmentSlot, usingItemInfo, damageInfo, miningInfo) -> entityPredicate.test(itemOwner));}
    public static ConditionModule otherEntityCondition(Predicate<LivingEntity> entityPredicate) {return new ConditionModule((itemStack, itemOwner, equipmentSlot, usingItemInfo, damageInfo, miningInfo) ->
    {
        if (damageInfo == null || !(damageInfo.otherEntity() instanceof LivingEntity livingOther)) {return true;}
        else {return entityPredicate.test(livingOther);}
    });}

    public ConditionModule opposite() {return new ConditionModule((itemStack, itemOwner, equipmentSlot, usingItemInfo, damageInfo, miningInfo) -> !this.conditionMet(itemStack, itemOwner, equipmentSlot, usingItemInfo, damageInfo, miningInfo));}

    public boolean conditionMet(AbilityUseSituation useSituation) {return this.conditionMet(useSituation.itemStack, useSituation.itemOwner, useSituation.equipmentSlot, useSituation.usingItemUseSituationInfo, useSituation.damageUseSituationInfo, useSituation.miningUseSituationInfo);}

    private boolean conditionMet(ItemStack itemStack, LivingEntity itemOwner, @Nullable EquipmentSlot equipmentSlot, @Nullable UsingItemUseSituationInfo usingItemInfo, @Nullable DamageUseSituationInfo damageInfo, @Nullable MiningUseSituationInfo miningInfo) {return condition.conditionMet(itemStack, itemOwner, equipmentSlot, usingItemInfo, damageInfo, miningInfo);}

    public static class OwnerHasItemInInventory extends ConditionModule
    {
        public OwnerHasItemInInventory(Predicate<Item> itemPredicate, int minCount, boolean ignoreCreative) {super((stack, itemOwner, equipmentSlot, usingItemInfo, damageInfo, miningInfo) ->
        {
            return (ignoreCreative && EntityHelper.isCreativePlayer(itemOwner)) || countItemInInventory(itemOwner, itemPredicate) >= minCount;
        });}

        private static int countItemInInventory(LivingEntity itemOwner, Predicate<Item> itemPredicate)
        {
            int totalCount = 0;
            if (itemOwner instanceof Player player)
            {
                Inventory inventory = player.getInventory();
                for (int i = 0; i < inventory.getContainerSize(); i++)
                {
                    ItemStack stack = inventory.getItem(i);
                    if (stack.isEmpty()) {continue;}
                    else if(itemPredicate.test(stack.getItem()))
                    {
                        totalCount+= stack.count();
                    }
                }
            }
            else
            {
                ItemStack mainHandStack = itemOwner.getMainHandItem();
                ItemStack offHandStack = itemOwner.getOffhandItem();
                if (!mainHandStack.isEmpty() && itemPredicate.test(mainHandStack.getItem())) {totalCount += mainHandStack.count();}
                if (!offHandStack.isEmpty() && itemPredicate.test(offHandStack.getItem())) {totalCount += offHandStack.count();}
            }
            return totalCount;
        }

        public static ConditionModule.OwnerHasItemInInventory.Builder builder() {return new ConditionModule.OwnerHasItemInInventory.Builder();}

        public static class Builder
        {
            private Builder() {}

            public ConditionModule.OwnerHasItemInInventory unlessCreative(Supplier<Item> item, int minCount) {return new OwnerHasItemInInventory((testedItem) -> testedItem == item.get(), minCount, true);}
            public ConditionModule.OwnerHasItemInInventory unlessCreative(TagKey<Item> itemTag, int minCount) {return new OwnerHasItemInInventory((testedItem) -> testedItem.getDefaultInstance().is(itemTag), minCount, true);}
            public ConditionModule.OwnerHasItemInInventory of(Supplier<Item> item, int minCount, boolean ignoreCreative) {return new OwnerHasItemInInventory((testedItem) -> testedItem == item.get(), minCount, ignoreCreative);}
            public ConditionModule.OwnerHasItemInInventory of(TagKey<Item> itemTag, int minCount, boolean ignoreCreative) {return new OwnerHasItemInInventory((testedItem) -> testedItem.getDefaultInstance().is(itemTag), minCount, ignoreCreative);}
        }
    }

    public static class TicksUsed extends ConditionModule
    {
        public TicksUsed(int minTicksUsed) {super((stack, itemOwner, equipmentSlot, usingItemInfo, damageInfo, miningInfo) ->
        {
            return usingItemInfo != null && usingItemInfo.ticksUsed() >= minTicksUsed;
        });}

        public static ConditionModule.TicksUsed.Builder builder() {return new ConditionModule.TicksUsed.Builder();}

        public static class Builder
        {
            private Builder() {}

            public ConditionModule.TicksUsed min(int minTicksUsed) {return new TicksUsed(minTicksUsed);}
        }
    }
}
