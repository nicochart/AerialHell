package fr.factionbedrock.aerialhell.Util;

import com.google.common.collect.Maps;
import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellMobEffects;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.locale.Language;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SmithingTemplateItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;

public class ItemHelper
{
    public static Map<Item, Integer> burnTimeMap = Maps.<Item, Integer>newLinkedHashMap();

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

    public static final ResourceLocation BASE_ATTACK_DAMAGE_ID = ResourceLocation.withDefaultNamespace("base_attack_damage");
    public static final ResourceLocation BASE_ATTACK_SPEED_ID = ResourceLocation.withDefaultNamespace("base_attack_speed");
    public static final ResourceLocation BASE_MOVEMENT_SPEED_ID = ResourceLocation.withDefaultNamespace("base_movement_speed");
    public static final ResourceLocation BASE_MAX_HEALTH_ID = ResourceLocation.withDefaultNamespace("base_movement_speed");

    public static ItemAttributeModifiers createAerialHellToolOrWeaponAttributes(Tier toolMaterial, float attackDamage, float attackSpeed, float movementSpeedIn, float maxHealthIn)
    {
        ItemAttributeModifiers.Builder builder = ItemAttributeModifiers.builder();
        builder.add(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_ID, attackDamage + toolMaterial.getAttackDamageBonus(), AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND);
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
}