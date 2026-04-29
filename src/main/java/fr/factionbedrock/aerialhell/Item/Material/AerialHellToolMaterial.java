package fr.factionbedrock.aerialhell.Item.Material;

import fr.factionbedrock.aerialhell.Item.AerialHellItem;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.item.component.Weapon;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.List;

public class AerialHellToolMaterial extends ExtraAttributeModifiersMaterial
{
    private final ToolMaterial vanillaMaterial;

    public AerialHellToolMaterial(TagKey<Block> incorrectBlocksForDrops, int durability, float efficientMiningSpeed, float attackDamage, int enchantmentValue, TagKey<Item> repairItems)
    {
        super();
        this.vanillaMaterial = new ToolMaterial(incorrectBlocksForDrops, durability, efficientMiningSpeed, attackDamage, enchantmentValue, repairItems);
    }

    @Override public AerialHellToolMaterial addAttributeModifier(Holder<Attribute> attribute, float value, AttributeModifier.Operation operation) {return (AerialHellToolMaterial) super.addAttributeModifier(attribute, value, operation);}

    private AerialHellItem.Properties applyCommonProperties(AerialHellItem.Properties properties)
    {
        return (AerialHellItem.Properties) properties.durability(this.vanillaMaterial.durability()).repairable(this.vanillaMaterial.repairItems()).enchantable(this.vanillaMaterial.enchantmentValue());
    }

    public AerialHellItem.Properties applyToolProperties(AerialHellItem.Properties properties, TagKey<Block> minesEfficiently, float attackDamage, float attackSpeed, List<AttributeEntry> additionalAttributes, float disableBlockingSeconds)
    {
        HolderGetter<Block> registrationLookup = BuiltInRegistries.acquireBootstrapRegistrationLookup(BuiltInRegistries.BLOCK);
        return (AerialHellItem.Properties) this.applyCommonProperties(properties)
                .component(DataComponents.TOOL, new Tool(this.getToolRules(registrationLookup, minesEfficiently), 1.0F, 1, true))
                .attributes(this.createAttributes(attackDamage, attackSpeed, additionalAttributes))
                .component(DataComponents.WEAPON, new Weapon(2, disableBlockingSeconds));
    }

    private List<Tool.Rule> getToolRules(HolderGetter<Block> registrationLookup, TagKey<Block> minesEfficiently)
    {
        return List.of(
                Tool.Rule.deniesDrops(registrationLookup.getOrThrow(this.vanillaMaterial.incorrectBlocksForDrops())),
                Tool.Rule.minesAndDrops(registrationLookup.getOrThrow(minesEfficiently), this.vanillaMaterial.speed())
        );
    }

    public AerialHellItem.Properties applySwordProperties(AerialHellItem.Properties properties, float attackDamage, float attackSpeed, List<AttributeEntry> additionalAttributes)
    {
        HolderGetter<Block> registrationLookup = BuiltInRegistries.acquireBootstrapRegistrationLookup(BuiltInRegistries.BLOCK);
        return (AerialHellItem.Properties) this.applyCommonProperties(properties)
                .component(DataComponents.TOOL, new Tool(getSwordRules(registrationLookup),1.0F, 2, false))
                .attributes(this.createAttributes(attackDamage, attackSpeed, additionalAttributes))
                .component(DataComponents.WEAPON, new Weapon(1));
    }

    private List<Tool.Rule> getSwordRules(HolderGetter<Block> registrationLookup)
    {
        return List.of(
                Tool.Rule.minesAndDrops(HolderSet.direct(new Holder[]{Blocks.COBWEB.builtInRegistryHolder()}), 15.0F),
                Tool.Rule.overrideSpeed(registrationLookup.getOrThrow(BlockTags.SWORD_INSTANTLY_MINES), Float.MAX_VALUE),
                Tool.Rule.overrideSpeed(registrationLookup.getOrThrow(BlockTags.SWORD_EFFICIENT), 1.5F)
        );
    }

    private ItemAttributeModifiers createAttributes(float attackDamage, float attackSpeed, List<AttributeEntry> additionalAttributes)
    {
        float effectiveAttackDamage = attackDamage + this.vanillaMaterial.attackDamageBonus();
        ItemAttributeModifiers.Builder modifiers = ItemAttributeModifiers.builder();
        Identifier modifierId = this.getModifierId("tool");
        if (effectiveAttackDamage != 0.0F) {modifiers.add(Attributes.ATTACK_DAMAGE, new AttributeModifier(modifierId, effectiveAttackDamage, AttributeModifier.Operation.ADD_VALUE),EquipmentSlotGroup.MAINHAND);}
        if (attackSpeed != 0.0F) {modifiers.add(Attributes.ATTACK_SPEED, new AttributeModifier(modifierId, attackSpeed, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND);}

        //applying custom attributes
        this.applyExtraAttributes(modifiers, additionalAttributes, EquipmentSlotGroup.MAINHAND, "tool_extra");

        return modifiers.build();
    }
}
