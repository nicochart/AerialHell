package fr.factionbedrock.aerialhell.Item.Material;

import com.google.common.collect.ImmutableMultimap;
import fr.factionbedrock.aerialhell.Item.AerialHellItem;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;

import java.util.UUID;
import java.util.function.Supplier;

public class AerialHellToolMaterial extends ExtraAttributeModifiersMaterial
{
    //need to use these modifier ids so that the extra speed & damage modifier values stack with base vanilla ones
    protected static final UUID BASE_ATTACK_DAMAGE_UUID = UUID.fromString("CB3F55D3-645C-4F38-A497-9C13A33DB5CF");
    protected static final UUID BASE_ATTACK_SPEED_UUID = UUID.fromString("FA233E1C-4180-4865-B01B-BCCE9785ACA3");

    private final VanillaToolMaterial vanillaMaterial;

    public AerialHellToolMaterial(int level, int durability, float efficientMiningSpeed, float attackDamage, int enchantmentValue, Supplier<Ingredient> repairItems)
    {
        super();
        this.vanillaMaterial = new VanillaToolMaterial(level, durability, efficientMiningSpeed, attackDamage, enchantmentValue, repairItems);
    }

    public VanillaToolMaterial vanillaMaterial() {return vanillaMaterial;}

    @Override public AerialHellToolMaterial addAttributeModifier(Attribute attribute, float value, AttributeModifier.Operation operation) {return (AerialHellToolMaterial) super.addAttributeModifier(attribute, value, operation);}

    private AerialHellItem.Properties applyCommonProperties(AerialHellItem.Properties properties)
    {
        return properties.durability(this.vanillaMaterial.getUses()).repairable(this.vanillaMaterial.getRepairIngredient()).enchantable(this.vanillaMaterial.getEnchantmentValue());
    }

    public AerialHellItem.Properties applyToolProperties(AerialHellItem.Properties properties, TagKey<Block> minesEfficiently, float attackDamage, float attackSpeed, AttributeEntryList additionalAttributes)
    {
        return this.applyCommonProperties(properties)
                .attributes(this.createAttributes(attackDamage, attackSpeed, additionalAttributes));
    }

    public AerialHellItem.Properties applySwordProperties(AerialHellItem.Properties properties, float attackDamage, float attackSpeed, AttributeEntryList additionalAttributes)
    {
        return this.applyCommonProperties(properties)
                .attributes(this.createAttributes(attackDamage, attackSpeed, additionalAttributes));
    }

    private ImmutableMultimap<Attribute, AttributeModifier> createAttributes(float attackDamage, float attackSpeed, AttributeEntryList additionalAttributes)
    {
        float effectiveAttackDamage = attackDamage + this.vanillaMaterial.getAttackDamageBonus();
        ImmutableMultimap.Builder<Attribute, AttributeModifier> modifiers = ImmutableMultimap.builder();
        if (effectiveAttackDamage != 0.0F) {modifiers.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Tool modifier", effectiveAttackDamage, AttributeModifier.Operation.ADDITION));}
        if (attackSpeed != 0.0F) {modifiers.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Tool modifier", attackSpeed, AttributeModifier.Operation.ADDITION));}

        //applying custom attributes
        this.applyExtraAttributes(modifiers, additionalAttributes, "tool");

        return modifiers.build();
    }
}
