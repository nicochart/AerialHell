package fr.factionbedrock.aerialhell.Util;

import com.google.common.collect.Maps;
import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellMobEffects;
import net.minecraft.block.Block;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.*;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class ItemHelper
{
    public static Map<Item, Integer> burnTimeMap = Maps.<Item, Integer>newLinkedHashMap();

    public static int getItemInTagCount(Iterable<ItemStack> stuff, TagKey<Item> tag)
    {
        int count = 0;
        for (ItemStack item : stuff)
        {
            if (item.isIn(tag)) {count++;}
        }
        return count;
    }

    public static int getItemMiningLevel(Item item)
    {
        if (item instanceof MiningToolItem toolItem)
        {
            ToolMaterial toolMaterial = toolItem.getMaterial();
            TagKey<Block> incorrectTag = toolMaterial.getInverseTag();
            if (incorrectTag == BlockTags.INCORRECT_FOR_WOODEN_TOOL) {return 0;}
            else if (incorrectTag == BlockTags.INCORRECT_FOR_STONE_TOOL) {return 1;}
            else if (incorrectTag == BlockTags.INCORRECT_FOR_IRON_TOOL) {return 2;}
            else if (incorrectTag == BlockTags.INCORRECT_FOR_DIAMOND_TOOL) {return 3;}
            else /*if (incorrectTag == BlockTags.INCORRECT_FOR_NETHERITE_TOOL)*/ {return 4;}
        }
        return 0;
    }

    public static final Identifier BASE_ATTACK_DAMAGE_ID = Identifier.ofVanilla("base_attack_damage");
    public static final Identifier BASE_ATTACK_SPEED_ID = Identifier.ofVanilla("base_attack_speed");
    public static final Identifier BASE_MOVEMENT_SPEED_ID = Identifier.ofVanilla("base_movement_speed");
    public static final Identifier BASE_MAX_HEALTH_ID = Identifier.ofVanilla("base_movement_speed");

    public static AttributeModifiersComponent createAerialHellToolOrWeaponAttributes(ToolMaterial toolMaterial, float attackDamage, float attackSpeed, float movementSpeedIn, float maxHealthIn)
    {
        AttributeModifiersComponent.Builder builder = AttributeModifiersComponent.builder();
        builder.add(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(BASE_ATTACK_DAMAGE_ID, attackDamage + toolMaterial.getAttackDamage(), EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND);
        builder.add(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier(BASE_ATTACK_SPEED_ID, attackSpeed, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND);
        if (movementSpeedIn != 0) {builder.add(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier(BASE_MOVEMENT_SPEED_ID, movementSpeedIn, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), AttributeModifierSlot.MAINHAND);}
        if (maxHealthIn != 0) {builder.add(EntityAttributes.GENERIC_MAX_HEALTH, new EntityAttributeModifier(BASE_MAX_HEALTH_ID, maxHealthIn, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND);}
        return builder.build();
    }

    public static class SmithingTemplate
    {
        private static final Formatting DESCRIPTION_FORMAT = Formatting.BLUE; private static final Formatting TITLE_FORMAT = Formatting.GRAY;
        private static final String APPLIES_TO = "applies_to";
        private static final String INGREDIENTS = "ingredients";
        private static final String BASE_SLOT_DESCRIPTION = "base_slot_description";
        private static final String ADDITIONS_SLOT_DESCRIPTION = "additions_slot_description";

        private static Text makeUpgradeTitleComponent(String materialName)
        {
            return Text.translatable(Util.createTranslationKey("upgrade", AerialHell.id(materialName + "_upgrade"))).formatted(TITLE_FORMAT);
        }

        private static Text makeSmithingTemplateItemDescComponent(String materialName, String info, @Nullable Formatting format)
        {
            MutableText returnComponent = Text.translatable(Util.createTranslationKey("item", AerialHell.id("smithing_template." + materialName + "_upgrade." + info)));
            return format == null ? returnComponent : returnComponent.formatted(format);
        }

        public static SmithingTemplateItem createUpgradeTemplate(String materialName)
        {
            return new SmithingTemplateItem(
                    makeSmithingTemplateItemDescComponent(materialName, APPLIES_TO, DESCRIPTION_FORMAT),
                    makeSmithingTemplateItemDescComponent(materialName, INGREDIENTS, DESCRIPTION_FORMAT),
                    makeUpgradeTitleComponent(materialName),
                    makeSmithingTemplateItemDescComponent(materialName, BASE_SLOT_DESCRIPTION, null),
                    makeSmithingTemplateItemDescComponent(materialName, ADDITIONS_SLOT_DESCRIPTION, null),
                    SmithingTemplateItem.getNetheriteUpgradeEmptyBaseSlotTextures(),
                    SmithingTemplateItem.getNetheriteUpgradeEmptyAdditionsSlotTextures());
        }
    }

    public static void removeEffectCuredBy(LivingEntity livingEntity, ItemStack stack)
    {
        if (livingEntity.getWorld().isClient) {return;}

        if (stack.isOf(AerialHellItems.SHADOW_FRUIT_STEW))
        {
            livingEntity.removeStatusEffect(AerialHellMobEffects.VULNERABILITY);
        }
    }
}
