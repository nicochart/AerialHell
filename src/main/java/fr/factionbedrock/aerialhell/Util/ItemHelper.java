package fr.factionbedrock.aerialhell.Util;

import fr.factionbedrock.aerialhell.AerialHell;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemAttributeModifiers;
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
            else if (incorrectTag == BlockTags.INCORRECT_FOR_NETHERITE_TOOL) {return 4;}
        }
        return 0;
    }

    public static final ResourceLocation BASE_ATTACK_DAMAGE_ID = ResourceLocation.withDefaultNamespace("base_attack_damage");
    public static final ResourceLocation BASE_ATTACK_SPEED_ID = ResourceLocation.withDefaultNamespace("base_attack_speed");
    public static final ResourceLocation BASE_MOVEMENT_SPEED_ID = ResourceLocation.withDefaultNamespace("base_movement_speed");
    public static final ResourceLocation BASE_MAX_HEALTH_ID = ResourceLocation.withDefaultNamespace("base_movement_speed");

    public static ItemAttributeModifiers createAerialHellToolOrWeaponAttributes(Tier tier, float attackDamage, float attackSpeed, float movementSpeedIn, float maxHealthIn)
    {
        ItemAttributeModifiers.Builder builder = ItemAttributeModifiers.builder();
        builder.add(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_ID, attackDamage + tier.getAttackDamageBonus(), AttributeModifier.Operation.ADD_VALUE),EquipmentSlotGroup.MAINHAND);
        builder.add(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_ID, attackSpeed, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND);
        if (movementSpeedIn != 0) {builder.add(Attributes.MOVEMENT_SPEED, new AttributeModifier(BASE_MOVEMENT_SPEED_ID, movementSpeedIn, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), EquipmentSlotGroup.MAINHAND);}
        if (maxHealthIn != 0) {builder.add(Attributes.MAX_HEALTH, new AttributeModifier(BASE_MAX_HEALTH_ID, maxHealthIn, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND);}
        return builder.build();
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
            return Component.translatable(Util.makeDescriptionId("upgrade", ResourceLocation.fromNamespaceAndPath(AerialHell.MODID,materialName + "_upgrade"))).withStyle(TITLE_FORMAT);
        }

        private static Component makeSmithingTemplateItemDescComponent(String materialName, String info, @Nullable ChatFormatting format)
        {
            MutableComponent returnComponent = Component.translatable(Util.makeDescriptionId("item", ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "smithing_template." + materialName + "_upgrade." + info)));
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
