package fr.factionbedrock.aerialhell.Util;

import fr.factionbedrock.aerialhell.AerialHell;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;

import javax.annotation.Nullable;

public class ItemHelper
{
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
        if (item instanceof DiggerItem diggerItem)
        {
            Tier tier = diggerItem.getTier();
            TagKey<Block> incorrectTag = tier.getIncorrectBlocksForDrops();
            if (incorrectTag == BlockTags.INCORRECT_FOR_WOODEN_TOOL) {return 0;}
            else if (incorrectTag == BlockTags.INCORRECT_FOR_STONE_TOOL) {return 1;}
            else if (incorrectTag == BlockTags.INCORRECT_FOR_IRON_TOOL) {return 2;}
            else if (incorrectTag == BlockTags.INCORRECT_FOR_DIAMOND_TOOL) {return 3;}
            else /*if (incorrectTag == BlockTags.INCORRECT_FOR_NETHERITE_TOOL)*/ {return 4;}
        }
        return 0;
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
            return Component.translatable(Util.makeDescriptionId("upgrade", new ResourceLocation(AerialHell.MODID,materialName + "_upgrade"))).withStyle(TITLE_FORMAT);
        }

        private static Component makeSmithingTemplateItemDescComponent(String materialName, String info, @Nullable ChatFormatting format)
        {
            MutableComponent returnComponent = Component.translatable(Util.makeDescriptionId("item", new ResourceLocation(AerialHell.MODID, "smithing_template." + materialName + "_upgrade." + info)));
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
}
