package fr.factionbedrock.aerialhell.Item.Material;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public class AerialHellToolMaterial extends ExtraAttributeModifiersMaterial
{
    //need to use these modifier ids so that the extra speed & damage modifier values stack with base vanilla ones
    public static final ResourceLocation BASE_ATTACK_SPEED_ATTRIBUTE_MODIFIER_ID = ResourceLocation.withDefaultNamespace("base_attack_speed");
    public static final ResourceLocation BASE_ATTACK_DAMAGE_ATTRIBUTE_MODIFIER_ID = ResourceLocation.withDefaultNamespace("base_attack_damage");

    private final ToolMaterials.ToolMaterial vanillaMaterial;

    public AerialHellToolMaterial(TagKey<Block> incorrectBlocksForDrops, int durability, float efficientMiningSpeed, float attackDamage, int enchantmentValue, Supplier<Ingredient> repairItems)
    {
        super();
        this.vanillaMaterial = new ToolMaterials.ToolMaterial(incorrectBlocksForDrops, durability, efficientMiningSpeed, attackDamage, enchantmentValue, repairItems);
    }

    @Override public AerialHellToolMaterial addAttributeModifier(Holder<Attribute> attribute, float value, AttributeModifier.Operation operation) {return (AerialHellToolMaterial) super.addAttributeModifier(attribute, value, operation);}

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
