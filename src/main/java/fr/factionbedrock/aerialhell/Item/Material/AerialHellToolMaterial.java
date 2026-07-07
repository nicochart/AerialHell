package fr.factionbedrock.aerialhell.Item.Material;

import fr.factionbedrock.aerialhell.Item.AerialHellItem;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.List;
import java.util.function.Supplier;

public class AerialHellToolMaterial extends ExtraAttributeModifiersMaterial
{
    //need to use these modifier ids so that the extra speed & damage modifier values stack with base vanilla ones
    public static final ResourceLocation BASE_ATTACK_SPEED_ATTRIBUTE_MODIFIER_ID = ResourceLocation.withDefaultNamespace("base_attack_speed");
    public static final ResourceLocation BASE_ATTACK_DAMAGE_ATTRIBUTE_MODIFIER_ID = ResourceLocation.withDefaultNamespace("base_attack_damage");

    private final VanillaToolMaterial vanillaMaterial;

    public AerialHellToolMaterial(TagKey<Block> incorrectBlocksForDrops, int durability, float efficientMiningSpeed, float attackDamage, int enchantmentValue, Supplier<Ingredient> repairItems)
    {
        super();
        this.vanillaMaterial = new VanillaToolMaterial(incorrectBlocksForDrops, durability, efficientMiningSpeed, attackDamage, enchantmentValue, repairItems);
    }

    public VanillaToolMaterial vanillaMaterial() {return vanillaMaterial;}

    @Override public AerialHellToolMaterial addAttributeModifier(Holder<Attribute> attribute, float value, AttributeModifier.Operation operation) {return (AerialHellToolMaterial) super.addAttributeModifier(attribute, value, operation);}

    private AerialHellItem.Properties applyCommonProperties(AerialHellItem.Properties properties)
    {
        return properties.durability(this.vanillaMaterial.getUses()).repairable(this.vanillaMaterial.getRepairIngredient()).enchantable(this.vanillaMaterial.getEnchantmentValue());
    }

    public AerialHellItem.Properties applyToolProperties(AerialHellItem.Properties properties, TagKey<Block> minesEfficiently, float attackDamage, float attackSpeed, AttributeEntryList additionalAttributes)
    {
        return (AerialHellItem.Properties) this.applyCommonProperties(properties)
                .component(DataComponents.TOOL, new Tool(this.getToolRules(minesEfficiently), 1.0F, 1))
                .attributes(this.createAttributes(attackDamage, attackSpeed, additionalAttributes));
    }

    private List<Tool.Rule> getToolRules(TagKey<Block> minesEfficiently)
    {
        return List.of(
                Tool.Rule.deniesDrops(this.vanillaMaterial.getIncorrectBlocksForDrops()),
                Tool.Rule.minesAndDrops(minesEfficiently, this.vanillaMaterial.getSpeed())
        );
    }

    public AerialHellItem.Properties applySwordProperties(AerialHellItem.Properties properties, float attackDamage, float attackSpeed, AttributeEntryList additionalAttributes)
    {
        return (AerialHellItem.Properties) this.applyCommonProperties(properties)
                .component(DataComponents.TOOL, new Tool(getSwordRules(),1.0F, 2))
                .attributes(this.createAttributes(attackDamage, attackSpeed, additionalAttributes));
    }

    private List<Tool.Rule> getSwordRules()
    {
        return List.of(
                Tool.Rule.minesAndDrops(List.of(Blocks.COBWEB), 15.0F),
                Tool.Rule.overrideSpeed(BlockTags.SWORD_EFFICIENT, 1.5F)
        );
    }

    private ItemAttributeModifiers createAttributes(float attackDamage, float attackSpeed, AttributeEntryList additionalAttributes)
    {
        float effectiveAttackDamage = attackDamage + this.vanillaMaterial.getAttackDamageBonus();
        ItemAttributeModifiers.Builder modifiers = ItemAttributeModifiers.builder();
        if (effectiveAttackDamage != 0.0F) {modifiers.add(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_ATTRIBUTE_MODIFIER_ID, effectiveAttackDamage, AttributeModifier.Operation.ADD_VALUE),EquipmentSlotGroup.MAINHAND);}
        if (attackSpeed != 0.0F) {modifiers.add(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_ATTRIBUTE_MODIFIER_ID, attackSpeed, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND);}

        //applying custom attributes
        this.applyExtraAttributes(modifiers, additionalAttributes, EquipmentSlotGroup.MAINHAND, "tool");

        return modifiers.build();
    }
}
