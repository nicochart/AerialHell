package fr.factionbedrock.aerialhell.Util;

import com.google.common.collect.Maps;
import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Item.AerialHellItem;
import fr.factionbedrock.aerialhell.Item.Armor.AerialHellArmorItem;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellMobEffects;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.locale.Language;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

public class ItemHelper
{
    public static Map<Item, Integer> burnTimeMap = Maps.<Item, Integer>newLinkedHashMap();

    public static void forEachAerialHellItem(List<EquippedItemStack> items, BiConsumer<AerialHellItem, EquippedItemStack> action)
    {
        for (EquippedItemStack e : items)
        {
            ItemStack stack = e.stack();
            if (stack.getItem() instanceof AerialHellItem item) {action.accept(item, e);}
        }
    }

    public static void forEachAerialHellArmorItem(List<EquippedItemStack> items, BiConsumer<AerialHellArmorItem, EquippedItemStack> action)
    {
        for (EquippedItemStack e : items)
        {
            ItemStack stack = e.stack();
            if (stack.getItem() instanceof AerialHellArmorItem item) {action.accept(item, e);}
        }
    }

    public static int getItemInTagCount(Iterable<ItemStack> stuff, TagKey<Item> tag)
    {
        int count = 0;
        for (ItemStack item : stuff)
        {
            if (item.is(tag)) {count++;}
        }
        return count;
    }

    public static int getItemMiningLevel(Item item)
    {
        if (item instanceof DiggerItem toolItem)
        {
            Tier toolMaterial = toolItem.getTier();
            TagKey<Block> incorrectTag = toolMaterial.getIncorrectBlocksForDrops();
            if (incorrectTag == BlockTags.INCORRECT_FOR_WOODEN_TOOL) {return 0;}
            else if (incorrectTag == BlockTags.INCORRECT_FOR_STONE_TOOL) {return 1;}
            else if (incorrectTag == BlockTags.INCORRECT_FOR_IRON_TOOL) {return 2;}
            else if (incorrectTag == BlockTags.INCORRECT_FOR_DIAMOND_TOOL) {return 3;}
            else if (incorrectTag == BlockTags.INCORRECT_FOR_NETHERITE_TOOL) {return 4;}
        }
        return 0;
    }

    public static int countItemStacksMatching(Iterable<ItemStack> itemStackList, Predicate<ItemStack> condition)
    {
        int count = 0;
        for (ItemStack itemStack : itemStackList)
        {
            if (condition.test(itemStack)) {count++;}
        }
        return count;
    }

    public static int countItemStacksInTag(Iterable<ItemStack> itemStackList, TagKey<Item> tag)
    {
        return countItemStacksMatching(itemStackList, (itemStack) -> itemStack.is(tag));
    }

    public static int countMagmaticGelStuff(Iterable<ItemStack> itemStackList) {return countItemStacksInTag(itemStackList, AerialHellTags.Items.MAGMATIC_GEL);}
    public static int countLunaticStuff(Iterable<ItemStack> itemStackList) {return countItemStacksInTag(itemStackList, AerialHellTags.Items.LUNATIC_STUFF);}
    public static int countShadowStuff(Iterable<ItemStack> itemStackList) {return countItemStacksInTag(itemStackList, AerialHellTags.Items.SHADOW_STUFF);}
    public static int countArsonistStuff(Iterable<ItemStack> itemStackList) {return countItemStacksInTag(itemStackList, AerialHellTags.Items.ARSONIST_STUFF);}
    public static int countVoluciteStuff(Iterable<ItemStack> itemStackList) {return countItemStacksInTag(itemStackList, AerialHellTags.Items.VOLUCITE_STUFF);}

    public static int countHeavyStuff(Iterable<ItemStack> itemStackList)
    {
        return countItemStacksMatching(itemStackList, (itemStack) -> itemStack.is(AerialHellTags.Items.OBSIDIAN_STUFF) || itemStack.is(AerialHellTags.Items.ARSONIST_STUFF));
    }

    public static class SmithingTemplate
    {
        private static final ChatFormatting DESCRIPTION_FORMAT = ChatFormatting.BLUE; private static final ChatFormatting TITLE_FORMAT = ChatFormatting.GRAY;
        private static final String APPLIES_TO = "applies_to";
        private static final String INGREDIENTS = "ingredients";
        private static final String BASE_SLOT_DESCRIPTION = "base_slot_description";
        private static final String ADDITIONS_SLOT_DESCRIPTION = "additions_slot_description";

        private static Component makeUpgradeTitleComponent(String materialName)
        {
            return Component.translatable(Util.makeDescriptionId("upgrade", AerialHell.id(materialName + "_upgrade"))).withStyle(TITLE_FORMAT);
        }

        private static Component makeSmithingTemplateItemDescComponent(String materialName, String info, @Nullable ChatFormatting format)
        {
            MutableComponent returnComponent = Component.translatable(Util.makeDescriptionId("item", AerialHell.id("smithing_template." + materialName + "_upgrade." + info)));
            return format == null ? returnComponent : returnComponent.withStyle(format);
        }

        public static SmithingTemplateItem createUpgradeTemplate(String materialName)
        {
            return new SmithingTemplateItem(
                    makeSmithingTemplateItemDescComponent(materialName, APPLIES_TO, DESCRIPTION_FORMAT),
                    makeSmithingTemplateItemDescComponent(materialName, INGREDIENTS, DESCRIPTION_FORMAT),
                    makeUpgradeTitleComponent(materialName),
                    makeSmithingTemplateItemDescComponent(materialName, BASE_SLOT_DESCRIPTION, null),
                    makeSmithingTemplateItemDescComponent(materialName, ADDITIONS_SLOT_DESCRIPTION, null),
                    SmithingTemplateItem.createNetheriteUpgradeIconList(),
                    SmithingTemplateItem.createNetheriteUpgradeMaterialList());
        }
    }

    public static void removeEffectCuredBy(LivingEntity livingEntity, ItemStack stack)
    {
        if (livingEntity.level().isClientSide) {return;}

        if (stack.is(AerialHellItems.SHADOW_FRUIT_STEW))
        {
            livingEntity.removeEffect(AerialHellMobEffects.VULNERABILITY);
        }
    }

    public static void appendItemTooltip(String translationKey, List<Component> tooltip)
    {
        String desc = ".desc", desc_2 = ".desc_2";
        tooltip.add(getFormatedDescFrom(translationKey+desc));
        if (Language.getInstance().has(translationKey+desc_2))
        {
            tooltip.add(getFormatedDescFrom(translationKey+desc_2));
        }
    }

    public static void appendBerserkAxeItemTooltip(String translationKey, List<Component> tooltip, String status)
    {
        String desc = ".desc", desc_2 = ".desc_2";
        tooltip.add(getFormatedDescFrom(translationKey+desc));
        if (Language.getInstance().has(translationKey+desc_2))
        {
            tooltip.add(getFormatedDescWithAppendedText(translationKey+desc_2, status));
        }
    }

    public static MutableComponent getFormatedDescWithAppendedText(String translationKey, String textToAppend) {return getTranslatableFrom(translationKey).append(textToAppend).withStyle(ChatFormatting.GRAY);}

    public static MutableComponent getFormatedDescFrom(String translationKey) {return getTranslatableFrom(translationKey).withStyle(ChatFormatting.GRAY);}

    public static MutableComponent getTranslatableFrom(String translationKey) {return Component.translatable(translationKey);}

    public static Map<Item, Integer> getOscillatingMap()
    {
        Map<Item, Integer> map = Maps.newLinkedHashMap();
        map.put(AerialHellItems.FLUORITE, 1200);
        map.put(AerialHellItems.FLUORITE_BLOCK, 10800);
        map.put(AerialHellItems.CRYSTAL, 300);
        map.put(AerialHellItems.CRYSTAL_BLOCK, 1200);
        return map;
    }

    public static Map<Item, Integer> getCorruptingMap()
    {
        Map<Item, Integer> map = Maps.newLinkedHashMap();
        map.put(AerialHellItems.SHADOW_CRYSTAL, 400);
        map.put(AerialHellItems.SHADOW_CRYSTAL_BLOCK, 3600);
        map.put(AerialHellItems.SHADOW_SHARD, 1000);
        map.put(AerialHellItems.CURSED_CRYSTAL, 2000);
        map.put(AerialHellItems.CURSED_CRYSTAL_BLOCK, 18000);
        return map;
    }

    public static String getTimeStringFromTicks(int ticks)
    {
        int secondsToRegain = ticks / 20;
        return getTimeStringFromSeconds(secondsToRegain);
    }

    public static String getTimeStringFromSeconds(int secs)
    {
        int days = secs / 86400;
        int hours = (secs % 86400) / 3600;
        int minutes = (secs % 3600) / 60;
        int seconds = secs % 60;

        StringBuilder sb = new StringBuilder();
        if (days > 0) sb.append(days).append("d ");
        if (hours > 0 || days > 0) sb.append(hours).append("h ");
        if (minutes > 0 || hours > 0 || days > 0) sb.append(minutes).append("m ");
        sb.append(seconds).append("s");

        return sb.toString().trim();
    }

    public static ItemStack createPotionItemStack(Holder<Potion> potion)
    {
        ItemStack stack = new ItemStack(Items.POTION);
        stack.set(DataComponents.POTION_CONTENTS, new PotionContents(potion));
        return stack;
    }

    public static ItemStack createEnchantedBookItemStack(ResourceKey<Enchantment> enchantment, int amplifier, RegistryAccess registryAccess)
    {
        ItemStack stack = new ItemStack(Items.ENCHANTED_BOOK);
        ItemEnchantments.Mutable enchantments = new ItemEnchantments.Mutable(ItemEnchantments.EMPTY);
        enchantments.set(registryAccess.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(enchantment), amplifier);
        stack.set(DataComponents.STORED_ENCHANTMENTS, enchantments.toImmutable());
        return stack;
    }
}