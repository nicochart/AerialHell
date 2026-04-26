package fr.factionbedrock.aerialhell.Util;

import fr.factionbedrock.aerialhell.AerialHell;
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
import java.util.function.Predicate;

public class ItemHelper
{
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
}
