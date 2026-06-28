package fr.factionbedrock.aerialhell.Item.Ability.Module;

import fr.factionbedrock.aerialhell.Item.Ability.AbilityUseSituation;
import fr.factionbedrock.aerialhell.Item.Ability.ModuleAction;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;

import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;

public class SideEffectModule extends AbilityModule
{
    private final ModuleAction sideEffect;

    private SideEffectModule(ModuleAction sideEffect) {super();this.sideEffect = sideEffect;}

    public void apply(AbilityUseSituation useSituation)
    {
        this.sideEffect.apply(useSituation.itemStack, useSituation.itemOwner, useSituation.equipmentSlot, useSituation.usingItemUseSituationInfo, useSituation.damageUseSituationInfo, useSituation.miningUseSituationInfo);
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
            super((stack, itemOwner, equipmentSlot, usingItemInfo, damageInfo, miningInfo) ->
            {
                if (equipmentSlot != null) {stack.hurtAndBreak(amount, itemOwner, equipmentSlot);}
            });
        }

        public static DamageItem simple() {return new DamageItem(1);}
        public static DamageItem withAmount(int amount) {return new DamageItem(amount);}
    }

    public static class ShrinkUsedItem extends SideEffectModule
    {
        private ShrinkUsedItem(int amount, boolean unlessCreative)
        {
            super((stack, itemOwner, equipmentSlot, usingItemInfo, damageInfo, miningInfo) ->
            {
                if (equipmentSlot != null)
                {
                    if (!(unlessCreative && EntityHelper.isCreativePlayer(itemOwner)) && !EntityHelper.hasEnchantment(itemOwner, Enchantments.INFINITY)) {stack.shrink(amount);}
                }
            });
        }

        public static ShrinkUsedItem.Builder builder() {return new ShrinkUsedItem.Builder();}

        public static class Builder
        {
            public Builder() {}

            public ShrinkUsedItem simple() {return new ShrinkUsedItem(1, true);}
            public ShrinkUsedItem withAmount(int amount) {return this.withAmount(amount, true);}
            public ShrinkUsedItem withAmount(int amount, boolean unlessCreative) {return new ShrinkUsedItem(amount, unlessCreative);}
        }
    }

    public static class ShrinkItem extends SideEffectModule
    {
        private ShrinkItem(Predicate<Item> itemPredicate, int amount, boolean unlessCreative)
        {
            super((usedStack, itemOwner, equipmentSlot, usingItemInfo, damageInfo, miningInfo) ->
            {
                shrinkItem(usedStack, itemOwner, itemPredicate, amount, unlessCreative);
            });
        }

        //the usedItem is the item (with the ability) that triggers the shrink
        private static void shrinkItem(ItemStack usedStack, LivingEntity itemOwner, Predicate<Item> itemPredicate, int amountToShrink, boolean unlessCreative)
        {
            if ((unlessCreative && EntityHelper.isCreativePlayer(itemOwner)) || EntityHelper.hasEnchantment(itemOwner, Enchantments.INFINITY)) {return;}
            int shrinkRemaining = amountToShrink;
            if (itemOwner instanceof Player player)
            {
                Inventory inventory = player.getInventory();
                for (int i = 0; i < inventory.getContainerSize(); i++)
                {
                    ItemStack stack = inventory.getItem(i);
                    if (stack.isEmpty()) {continue;}
                    else if (itemPredicate.test(stack.getItem()))
                    {
                        shrinkRemaining-= tryShrink(stack, amountToShrink);
                        if (shrinkRemaining == 0) {return;}
                    }
                }
            }
            else
            {
                ItemStack mainHandStack = itemOwner.getMainHandItem();
                ItemStack offHandStack = itemOwner.getOffhandItem();
                if (!mainHandStack.isEmpty() && itemPredicate.test(mainHandStack.getItem()))
                {
                    shrinkRemaining-= tryShrink(mainHandStack, amountToShrink);
                    if (shrinkRemaining == 0) {return;}
                }
                if (!offHandStack.isEmpty() && itemPredicate.test(offHandStack.getItem()))
                {
                    shrinkRemaining-= tryShrink(offHandStack, amountToShrink);
                    if (shrinkRemaining == 0) {return;}
                }
            }
        }

        //returns the shrank number
        private static int tryShrink(ItemStack stack, int amountToShrink)
        {
            if (stack.isEmpty()) {return 0;}

            int willShrinkCount = Math.min(stack.count(), amountToShrink);
            stack.shrink(willShrinkCount);
            return willShrinkCount;
        }

        public static ShrinkItem.Builder builder() {return new ShrinkItem.Builder();}

        public static class Builder
        {
            public Builder() {}

            public ShrinkItem item(Supplier<Item> item) {return new ShrinkItem((testedItem) -> testedItem == item.get(), 1, true);}
            public ShrinkItem item(Supplier<Item> item, int amount) {return this.of((testedItem) -> testedItem == item.get(), amount, true);}
            public ShrinkItem of(Predicate<Item> itemPredicate, int amount, boolean unlessCreative) {return new ShrinkItem(itemPredicate, amount, unlessCreative);}
        }
    }

    public static class Cooldown extends SideEffectModule
    {
        private final ToIntFunction<LivingEntity> cooldownDuration;
        private Cooldown(ToIntFunction<LivingEntity> cooldownDuration)
        {
            super((stack, itemOwner, equipmentSlot, usingItemInfo, damageInfo, miningInfo) ->
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