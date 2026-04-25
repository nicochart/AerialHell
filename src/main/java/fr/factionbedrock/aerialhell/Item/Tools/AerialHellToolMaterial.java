package fr.factionbedrock.aerialhell.Item.Tools;

import fr.factionbedrock.aerialhell.Item.AerialHellItemProperties;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EquipmentSlotGroup;
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

public class AerialHellToolMaterial
{
    public static final Identifier BASE_ATTACK_DAMAGE_ATTRIBUTE_MODIFIER_ID = Identifier.withDefaultNamespace("base_attack_damage");
    public static final Identifier BASE_ATTACK_SPEED_ATTRIBUTE_MODIFIER_ID = Identifier.withDefaultNamespace("base_attack_speed");
    public static final Identifier BASE_MOVEMENT_SPEED_ATTRIBUTE_MODIFIER_ID = Identifier.withDefaultNamespace("base_movement_speed");
    public static final Identifier BASE_MAX_HEALTH_ATTRIBUTE_MODIFIER_ID = Identifier.withDefaultNamespace("base_movement_speed");

    private final ToolMaterial vanillaMaterial;
    public final float movementSpeed;
    public final float health;

    public AerialHellToolMaterial(TagKey<Block> incorrectBlocksForDrops, int durability, float efficientMiningSpeed, float attackDamage, float movementSpeed, float health, int enchantmentValue, TagKey<Item> repairItems)
    {
        this.vanillaMaterial = new ToolMaterial(incorrectBlocksForDrops, durability, efficientMiningSpeed, attackDamage, enchantmentValue, repairItems);
        this.movementSpeed = movementSpeed;
        this.health = health;
    }

    public ToolMaterial vanillaMaterial() {return this.vanillaMaterial;}

    private AerialHellItemProperties applyCommonProperties(AerialHellItemProperties properties)
    {
        return (AerialHellItemProperties) properties.durability(this.vanillaMaterial.durability()).repairable(this.vanillaMaterial.repairItems()).enchantable(this.vanillaMaterial.enchantmentValue());
    }

    public AerialHellItemProperties applyToolProperties(AerialHellItemProperties properties, TagKey<Block> minesEfficiently, float attackDamage, float attackSpeed, float movementSpeed, float maxHealth, float disableBlockingSeconds)
    {
        HolderGetter<Block> registrationLookup = BuiltInRegistries.acquireBootstrapRegistrationLookup(BuiltInRegistries.BLOCK);
        return (AerialHellItemProperties) this.applyCommonProperties(properties)
                .component(DataComponents.TOOL, new Tool(this.getToolRules(registrationLookup, minesEfficiently), 1.0F, 1, true))
                .attributes(this.createAttributes(attackDamage, attackSpeed, movementSpeed, maxHealth))
                .component(DataComponents.WEAPON, new Weapon(2, disableBlockingSeconds));
    }

    private List<Tool.Rule> getToolRules(HolderGetter<Block> registrationLookup, TagKey<Block> minesEfficiently)
    {
        return List.of(
                Tool.Rule.deniesDrops(registrationLookup.getOrThrow(this.vanillaMaterial.incorrectBlocksForDrops())),
                Tool.Rule.minesAndDrops(registrationLookup.getOrThrow(minesEfficiently), this.vanillaMaterial.speed())
        );
    }

    public AerialHellItemProperties applySwordProperties(AerialHellItemProperties properties, float attackDamage, float attackSpeed, float movementSpeed, float maxHealth)
    {
        HolderGetter<Block> registrationLookup = BuiltInRegistries.acquireBootstrapRegistrationLookup(BuiltInRegistries.BLOCK);
        return (AerialHellItemProperties) this.applyCommonProperties(properties)
                .component(DataComponents.TOOL, new Tool(getSwordRules(registrationLookup),1.0F, 2, false))
                .attributes(this.createAttributes(attackDamage, attackSpeed, movementSpeed, maxHealth))
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

    private ItemAttributeModifiers createAttributes(float attackDamage, float attackSpeed, float movementSpeed, float health)
    {
        float effectiveAttackDamage = attackDamage + this.vanillaMaterial.attackDamageBonus();
        float effectiveMovementSpeed = movementSpeed + this.movementSpeed;
        float effectiveHealth = health + this.health;
        ItemAttributeModifiers.Builder attributeBuilder = ItemAttributeModifiers.builder();
        if (effectiveAttackDamage != 0.0F) {attributeBuilder.add(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_ATTRIBUTE_MODIFIER_ID, effectiveAttackDamage, AttributeModifier.Operation.ADD_VALUE),EquipmentSlotGroup.MAINHAND);}
        if (attackSpeed != 0.0F) {attributeBuilder.add(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_ATTRIBUTE_MODIFIER_ID, attackSpeed, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND);}
        if (effectiveMovementSpeed != 0.0F) {attributeBuilder.add(Attributes.MOVEMENT_SPEED, new AttributeModifier(BASE_MOVEMENT_SPEED_ATTRIBUTE_MODIFIER_ID, effectiveMovementSpeed, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), EquipmentSlotGroup.MAINHAND);}
        if (effectiveHealth != 0.0F) {attributeBuilder.add(Attributes.MAX_HEALTH, new AttributeModifier(BASE_MAX_HEALTH_ATTRIBUTE_MODIFIER_ID, effectiveHealth, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND);}
        return attributeBuilder.build();
    }
}
