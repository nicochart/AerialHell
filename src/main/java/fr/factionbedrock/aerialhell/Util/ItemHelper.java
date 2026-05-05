package fr.factionbedrock.aerialhell.Util;

import com.google.common.collect.Maps;
import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Item.AerialHellItem;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellMobEffects;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Util;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

public class ItemHelper
{
    public static void forEachAerialHellItem(List<EquippedItemStack> items, BiConsumer<AerialHellItem, EquippedItemStack> action)
    {
        for (EquippedItemStack e : items)
        {
            ItemStack stack = e.stack();
            if (stack.getItem() instanceof AerialHellItem item) {action.accept(item, e);}
        }
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
            return Component.translatable(Util.makeDescriptionId("upgrade", Identifier.fromNamespaceAndPath(AerialHell.MODID,materialName + "_upgrade"))).withStyle(TITLE_FORMAT);
        }

        private static Component makeSmithingTemplateItemDescComponent(String materialName, String info, @Nullable ChatFormatting format)
        {
            MutableComponent returnComponent = Component.translatable(Util.makeDescriptionId("item", Identifier.fromNamespaceAndPath(AerialHell.MODID, "smithing_template." + materialName + "_upgrade." + info)));
            return format == null ? returnComponent : returnComponent.withStyle(format);
        }

        public static SmithingTemplateItem createUpgradeTemplate(String materialName, Item.Properties properties)
        {
            return new SmithingTemplateItem(
                    makeSmithingTemplateItemDescComponent(materialName, APPLIES_TO, DESCRIPTION_FORMAT),
                    makeSmithingTemplateItemDescComponent(materialName, INGREDIENTS, DESCRIPTION_FORMAT),
                    //makeUpgradeTitleComponent(materialName), TODO work as expected ?
                    makeSmithingTemplateItemDescComponent(materialName, BASE_SLOT_DESCRIPTION, null),
                    makeSmithingTemplateItemDescComponent(materialName, ADDITIONS_SLOT_DESCRIPTION, null),
                    SmithingTemplateItem.createNetheriteUpgradeIconList(),
                    SmithingTemplateItem.createNetheriteUpgradeMaterialList(),
                    properties);
        }
    }

    public static void removeEffectCuredBy(LivingEntity livingEntity, ItemStack stack)
    {
        if (livingEntity.level().isClientSide()) {return;}

        if (stack.is(AerialHellItems.SHADOW_FRUIT_STEW))
        {
            livingEntity.removeEffect(AerialHellMobEffects.VULNERABILITY);
        }
    }

    public static Map<Item, Integer> getOscillatingMap()
    {
        Map<Item, Integer> map = Maps.newLinkedHashMap();
        map.put(AerialHellItems.FLUORITE.get(), 1200);
        map.put(AerialHellItems.FLUORITE_BLOCK.get(), 10800);
        map.put(AerialHellItems.CRYSTAL.get(), 300);
        map.put(AerialHellItems.CRYSTAL_BLOCK.get(), 1200);
        return map;
    }

    public static Map<Item, Integer> getCorruptingMap()
    {
        Map<Item, Integer> map = Maps.newLinkedHashMap();
        map.put(AerialHellItems.SHADOW_CRYSTAL.get(), 400);
        map.put(AerialHellItems.SHADOW_CRYSTAL_BLOCK.get(), 3600);
        map.put(AerialHellItems.SHADOW_SHARD.get(), 1000);
        map.put(AerialHellItems.CURSED_CRYSTAL.get(), 2000);
        map.put(AerialHellItems.CURSED_CRYSAL_BLOCK.get(), 18000);
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
}
