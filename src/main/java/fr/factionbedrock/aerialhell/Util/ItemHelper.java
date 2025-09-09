package fr.factionbedrock.aerialhell.Util;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellMobEffects;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import javax.annotation.Nullable;
import java.util.List;

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

    public static final ResourceLocation BASE_ATTACK_DAMAGE_ID = ResourceLocation.withDefaultNamespace("base_attack_damage");
    public static final ResourceLocation BASE_ATTACK_SPEED_ID = ResourceLocation.withDefaultNamespace("base_attack_speed");
    public static final ResourceLocation BASE_MOVEMENT_SPEED_ID = ResourceLocation.withDefaultNamespace("base_movement_speed");
    public static final ResourceLocation BASE_MAX_HEALTH_ID = ResourceLocation.withDefaultNamespace("base_movement_speed");


    public static Item.Properties applySwordProperties(Item.Properties properties, ToolMaterial toolMaterial, float attackDamage, float attackSpeed, float movementSpeed, float maxHealth)
    {
        return applyToolProperties(properties, toolMaterial, null, attackDamage, attackSpeed, movementSpeed, maxHealth);
    }

    //copy of ToolMaterial applyToolProperties / applySwordProperties method, with the ToolMaterial as additional parameter
    //if mineableBlocks == null, the tool is a sword
    public static Item.Properties applyToolProperties(Item.Properties properties, ToolMaterial toolMaterial, @Nullable TagKey<Block> mineableBlocks, float attackDamage, float attackSpeed, float movementSpeed, float maxHealth)
    {
        boolean canDestroyBlocksInCreative = mineableBlocks != null;
        HolderGetter<Block> holdergetter = BuiltInRegistries.acquireBootstrapRegistrationLookup(BuiltInRegistries.BLOCK);
        return applyCommonProperties(properties, toolMaterial)
                .component(
                        DataComponents.TOOL,
                        new Tool(getRules(holdergetter, toolMaterial, mineableBlocks), 1.0F, 1, canDestroyBlocksInCreative)
                )
                .attributes(createAerialHellToolOrWeaponAttributes(toolMaterial, attackDamage, attackSpeed, movementSpeed, maxHealth));
    }

    private static List<Tool.Rule> getRules(HolderGetter<Block> bootstrapRegistrationLookup, ToolMaterial toolMaterial, @Nullable TagKey<Block> mineableBlocks)
    {
        if (mineableBlocks == null) {return getSwordRules(bootstrapRegistrationLookup);}
        else {return getToolRules(bootstrapRegistrationLookup, toolMaterial, mineableBlocks);}
    }

    //copy of applyToolProperties(..) rules list
    private static List<Tool.Rule> getToolRules(HolderGetter<Block> bootstrapRegistrationLookup, ToolMaterial toolMaterial, TagKey<Block> mineableBlocks)
    {
        return List.of(
            Tool.Rule.deniesDrops(bootstrapRegistrationLookup.getOrThrow(toolMaterial.incorrectBlocksForDrops())),
            Tool.Rule.minesAndDrops(bootstrapRegistrationLookup.getOrThrow(mineableBlocks), toolMaterial.speed())
        );
    }

    //copy of applySwordProperties(..) rules list
    private static List<Tool.Rule> getSwordRules(HolderGetter<Block> bootstrapRegistrationLookup)
    {
        return List.of(
            Tool.Rule.minesAndDrops(HolderSet.direct(Blocks.COBWEB.builtInRegistryHolder()), 15.0F),
            Tool.Rule.overrideSpeed(bootstrapRegistrationLookup.getOrThrow(BlockTags.SWORD_EFFICIENT), 1.5F)
        );
    }

    //copy of ToolMaterial method
    private static Item.Properties applyCommonProperties(Item.Properties properties, ToolMaterial material)
    {
        return properties.durability(material.durability()).repairable(material.repairItems()).enchantable(material.enchantmentValue());
    }

    public static ItemAttributeModifiers createAerialHellToolOrWeaponAttributes(ToolMaterial toolMaterial, float attackDamage, float attackSpeed, float movementSpeed, float maxHealth)
    {
        ItemAttributeModifiers.Builder builder = ItemAttributeModifiers.builder();
        builder.add(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_ID, attackDamage + toolMaterial.attackDamageBonus(), AttributeModifier.Operation.ADD_VALUE),EquipmentSlotGroup.MAINHAND);
        builder.add(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_ID, attackSpeed, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND);
        if (movementSpeed != 0) {builder.add(Attributes.MOVEMENT_SPEED, new AttributeModifier(BASE_MOVEMENT_SPEED_ID, movementSpeed, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), EquipmentSlotGroup.MAINHAND);}
        if (maxHealth != 0) {builder.add(Attributes.MAX_HEALTH, new AttributeModifier(BASE_MAX_HEALTH_ID, maxHealth, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND);}
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
