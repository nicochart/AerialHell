package fr.factionbedrock.aerialhell.Util;

import com.google.common.collect.Maps;
import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellMobEffects;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.component.type.ToolComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.Language;
import net.minecraft.util.Util;
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
            if (item.isIn(tag)) {count++;}
        }
        return count;
    }

    public static final Identifier BASE_ATTACK_DAMAGE_ID = Identifier.ofVanilla("base_attack_damage");
    public static final Identifier BASE_ATTACK_SPEED_ID = Identifier.ofVanilla("base_attack_speed");
    public static final Identifier BASE_MOVEMENT_SPEED_ID = Identifier.ofVanilla("base_movement_speed");
    public static final Identifier BASE_MAX_HEALTH_ID = Identifier.ofVanilla("base_movement_speed");

    public static Item.Settings applySwordProperties(Item.Settings properties, ToolMaterial toolMaterial, float attackDamage, float attackSpeed, float movementSpeed, float maxHealth)
    {
        return applyToolProperties(properties, toolMaterial, null, attackDamage, attackSpeed, movementSpeed, maxHealth);
    }

    //copy of ToolMaterial applyToolSettings / applySwordSettings method, with the ToolMaterial as additional parameter
    //if mineableBlocks == null, the tool is a sword
    public static Item.Settings applyToolProperties(Item.Settings properties, ToolMaterial toolMaterial, @Nullable TagKey<Block> mineableBlocks, float attackDamage, float attackSpeed, float movementSpeed, float maxHealth)
    {
        RegistryEntryLookup<Block> registryEntryLookup = Registries.createEntryLookup(Registries.BLOCK);
        return applyCommonProperties(properties, toolMaterial)
                .component(
                        DataComponentTypes.TOOL,
                        new ToolComponent(getRules(registryEntryLookup, toolMaterial, mineableBlocks), 1.0F, 1)
                )
                .attributeModifiers(createAerialHellToolOrWeaponAttributes(toolMaterial, attackDamage, attackSpeed, movementSpeed, maxHealth));
    }

    private static List<ToolComponent.Rule> getRules(RegistryEntryLookup<Block> bootstrapRegistrationLookup, ToolMaterial toolMaterial, @Nullable TagKey<Block> mineableBlocks)
    {
        if (mineableBlocks == null) {return getSwordRules(bootstrapRegistrationLookup);}
        else {return getToolRules(bootstrapRegistrationLookup, toolMaterial, mineableBlocks);}
    }

    //copy of applyToolSettings(..) rules list
    private static List<ToolComponent.Rule> getToolRules(RegistryEntryLookup<Block> bootstrapRegistrationLookup, ToolMaterial toolMaterial, TagKey<Block> mineableBlocks)
    {
        return List.of(
                ToolComponent.Rule.ofNeverDropping(bootstrapRegistrationLookup.getOrThrow(toolMaterial.incorrectBlocksForDrops())),
                ToolComponent.Rule.ofAlwaysDropping(bootstrapRegistrationLookup.getOrThrow(mineableBlocks), toolMaterial.speed())
        );
    }

    //copy of applySwordSettings(..) rules list
    private static List<ToolComponent.Rule> getSwordRules(RegistryEntryLookup<Block> bootstrapRegistrationLookup)
    {
        return List.of(
                ToolComponent.Rule.ofAlwaysDropping(RegistryEntryList.of(Blocks.COBWEB.getRegistryEntry()), 15.0F),
                ToolComponent.Rule.of(bootstrapRegistrationLookup.getOrThrow(BlockTags.SWORD_EFFICIENT), 1.5F)
        );
    }

    //copy of ToolMaterial applyBaseSettings method
    private static Item.Settings applyCommonProperties(Item.Settings properties, ToolMaterial material)
    {
        return properties.maxDamage(material.durability()).repairable(material.repairItems()).enchantable(material.enchantmentValue());
    }

    public static AttributeModifiersComponent createAerialHellToolOrWeaponAttributes(ToolMaterial toolMaterial, float attackDamage, float attackSpeed, float movementSpeedIn, float maxHealthIn)
    {
        AttributeModifiersComponent.Builder builder = AttributeModifiersComponent.builder();
        builder.add(EntityAttributes.ATTACK_DAMAGE, new EntityAttributeModifier(BASE_ATTACK_DAMAGE_ID, attackDamage + toolMaterial.attackDamageBonus(), EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND);
        builder.add(EntityAttributes.ATTACK_SPEED, new EntityAttributeModifier(BASE_ATTACK_SPEED_ID, attackSpeed, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND);
        if (movementSpeedIn != 0) {builder.add(EntityAttributes.MOVEMENT_SPEED, new EntityAttributeModifier(BASE_MOVEMENT_SPEED_ID, movementSpeedIn, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), AttributeModifierSlot.MAINHAND);}
        if (maxHealthIn != 0) {builder.add(EntityAttributes.MAX_HEALTH, new EntityAttributeModifier(BASE_MAX_HEALTH_ID, maxHealthIn, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND);}
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

        public static SmithingTemplateItem createUpgradeTemplate(String materialName, Item.Settings settings)
        {
            return new SmithingTemplateItem(
                    makeSmithingTemplateItemDescComponent(materialName, APPLIES_TO, DESCRIPTION_FORMAT),
                    makeSmithingTemplateItemDescComponent(materialName, INGREDIENTS, DESCRIPTION_FORMAT),
                    //makeUpgradeTitleComponent(materialName), TODO work as expected ?
                    makeSmithingTemplateItemDescComponent(materialName, BASE_SLOT_DESCRIPTION, null),
                    makeSmithingTemplateItemDescComponent(materialName, ADDITIONS_SLOT_DESCRIPTION, null),
                    SmithingTemplateItem.getNetheriteUpgradeEmptyBaseSlotTextures(),
                    SmithingTemplateItem.getNetheriteUpgradeEmptyAdditionsSlotTextures(),
                    settings);
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

    public static void appendItemTooltip(String translationKey, List<Text> tooltip)
    {
        String desc = ".desc", desc_2 = ".desc_2";
        tooltip.add(getFormatedDescFrom(translationKey+desc));
        if (Language.getInstance().hasTranslation(translationKey+desc_2))
        {
            tooltip.add(getFormatedDescFrom(translationKey+desc_2));
        }
    }

    public static void appendBerserkAxeItemTooltip(String translationKey, List<Text> tooltip, String status)
    {
        String desc = ".desc", desc_2 = ".desc_2";
        tooltip.add(getFormatedDescFrom(translationKey+desc));
        if (Language.getInstance().hasTranslation(translationKey+desc_2))
        {
            tooltip.add(getFormatedDescWithAppendedText(translationKey+desc_2, status));
        }
    }

    public static MutableText getFormatedDescWithAppendedText(String translationKey, String textToAppend) {return getTranslatableFrom(translationKey).append(textToAppend).formatted(Formatting.GRAY);}

    public static MutableText getFormatedDescFrom(String translationKey) {return getTranslatableFrom(translationKey).formatted(Formatting.GRAY);}

    public static MutableText getTranslatableFrom(String translationKey) {return Text.translatable(translationKey);}
}