package fr.factionbedrock.aerialhell.Item.Material;

import fr.factionbedrock.aerialhell.Item.AerialHellItem;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwingAnimationType;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.component.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.List;
import java.util.Optional;

public class AerialHellToolMaterial extends ExtraAttributeModifiersMaterial
{
    //need to use these modifier ids so that the extra speed & damage modifier values stack with base vanilla ones
    public static final Identifier BASE_ATTACK_SPEED_ATTRIBUTE_MODIFIER_ID = Identifier.withDefaultNamespace("base_attack_speed");
    public static final Identifier BASE_ATTACK_DAMAGE_ATTRIBUTE_MODIFIER_ID = Identifier.withDefaultNamespace("base_attack_damage");

    private final ToolMaterial vanillaMaterial;
    public final SpearStats spearStats;

    public AerialHellToolMaterial(TagKey<Block> incorrectBlocksForDrops, int durability, float efficientMiningSpeed, float attackDamage, int enchantmentValue, SpearStats spearStats, TagKey<Item> repairItems)
    {
        super();
        this.vanillaMaterial = new ToolMaterial(incorrectBlocksForDrops, durability, efficientMiningSpeed, attackDamage, enchantmentValue, repairItems);
        this.spearStats = spearStats;
    }

    @Override public AerialHellToolMaterial addAttributeModifier(Holder<Attribute> attribute, float value, AttributeModifier.Operation operation) {return (AerialHellToolMaterial) super.addAttributeModifier(attribute, value, operation);}

    private AerialHellItem.Properties applyCommonProperties(AerialHellItem.Properties properties)
    {
        return (AerialHellItem.Properties) properties.durability(this.vanillaMaterial.durability()).repairable(this.vanillaMaterial.repairItems()).enchantable(this.vanillaMaterial.enchantmentValue());
    }

    public AerialHellItem.Properties applyToolProperties(AerialHellItem.Properties properties, TagKey<Block> minesEfficiently, float attackDamage, float attackSpeed, AttributeEntryList additionalAttributes, float disableBlockingSeconds)
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

    public AerialHellItem.Properties applySwordProperties(AerialHellItem.Properties properties, float attackDamage, float attackSpeed, AttributeEntryList additionalAttributes)
    {
        HolderGetter<Block> registrationLookup = BuiltInRegistries.acquireBootstrapRegistrationLookup(BuiltInRegistries.BLOCK);
        return (AerialHellItem.Properties) this.applyCommonProperties(properties)
                .component(DataComponents.TOOL, new Tool(getSwordRules(registrationLookup),1.0F, 2, false))
                .attributes(this.createAttributes(attackDamage, attackSpeed, additionalAttributes))
                .component(DataComponents.WEAPON, new Weapon(1));
    }

    public AerialHellItem.Properties applySpearProperties(AerialHellItem.Properties properties, AttributeEntryList additionalAttributes)
    {
        float attackDuration = this.spearStats.attackDuration();float damageMultiplier = this.spearStats.damageMultiplier();float delay = this.spearStats.delay();float dismountTime = this.spearStats.dismountTime();float dismountThreshold = this.spearStats.dismountThreshold();float knockbackTime = this.spearStats.knockbackTime();float knockbackThreshold = this.spearStats.knockbackThreshold();float damageTime = this.spearStats.damageTime();float damageThreshold = this.spearStats.damageThreshold();

        Optional<Holder<SoundEvent>> sound = Optional.of(this == AerialHellToolMaterials.SKY_WOOD ? SoundEvents.SPEAR_WOOD_ATTACK : SoundEvents.SPEAR_ATTACK);
        Optional<Holder<SoundEvent>> useSound = Optional.of(this == AerialHellToolMaterials.SKY_WOOD ? SoundEvents.SPEAR_WOOD_USE : SoundEvents.SPEAR_USE);
        Optional<Holder<SoundEvent>> hitSound = Optional.of(this == AerialHellToolMaterials.SKY_WOOD ? SoundEvents.SPEAR_WOOD_HIT : SoundEvents.SPEAR_HIT);
        return (AerialHellItem.Properties) this.applyCommonProperties(properties)
                .delayedHolderComponent(DataComponents.DAMAGE_TYPE, DamageTypes.SPEAR)
                .component(DataComponents.KINETIC_WEAPON, new KineticWeapon(10, (int)(delay * 20.0F), KineticWeapon.Condition.ofAttackerSpeed((int)(dismountTime * 20.0F), dismountThreshold), KineticWeapon.Condition.ofAttackerSpeed((int)(knockbackTime * 20.0F), knockbackThreshold), KineticWeapon.Condition.ofRelativeSpeed((int)(damageTime * 20.0F), damageThreshold), 0.38F, damageMultiplier, useSound, hitSound))
                .component(DataComponents.PIERCING_WEAPON, new PiercingWeapon(true, false, sound, hitSound))
                .component(DataComponents.ATTACK_RANGE, new AttackRange(2.0F, 4.5F, 2.0F, 6.5F, 0.125F, 0.5F))
                .component(DataComponents.MINIMUM_ATTACK_CHARGE, 1.0F)
                .component(DataComponents.SWING_ANIMATION, new SwingAnimation(SwingAnimationType.STAB, (int)(attackDuration * 20.0F)))
                .attributes(this.createAttributes(0.0F, (1.0F / attackDuration) - 4.0F, additionalAttributes))
                .component(DataComponents.USE_EFFECTS, new UseEffects(true, false, 1.0F))
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

    private ItemAttributeModifiers createAttributes(float attackDamage, float attackSpeed, AttributeEntryList additionalAttributes)
    {
        float effectiveAttackDamage = attackDamage + this.vanillaMaterial.attackDamageBonus();
        ItemAttributeModifiers.Builder modifiers = ItemAttributeModifiers.builder();
        if (effectiveAttackDamage != 0.0F) {modifiers.add(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_ATTRIBUTE_MODIFIER_ID, effectiveAttackDamage, AttributeModifier.Operation.ADD_VALUE),EquipmentSlotGroup.MAINHAND);}
        if (attackSpeed != 0.0F) {modifiers.add(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_ATTRIBUTE_MODIFIER_ID, attackSpeed, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND);}

        //applying custom attributes
        this.applyExtraAttributes(modifiers, additionalAttributes, EquipmentSlotGroup.MAINHAND, "tool");

        return modifiers.build();
    }
}
