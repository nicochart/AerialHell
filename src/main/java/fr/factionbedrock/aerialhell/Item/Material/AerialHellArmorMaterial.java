package fr.factionbedrock.aerialhell.Item.Material;

import fr.factionbedrock.aerialhell.AerialHell;
import net.minecraft.core.Holder;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAsset;

import java.util.List;
import java.util.Map;

public class AerialHellArmorMaterial extends ExtraAttributeModifiersMaterial
{
    public final ArmorMaterial vanillaMaterial;

    public AerialHellArmorMaterial(int durability, Map<ArmorType, Integer> defense, int enchantmentValue, Holder<SoundEvent> equipSound, float toughness, float knockbackResistance, TagKey<Item> repairIngredient, ResourceKey<EquipmentAsset> assetId)
    {
        super();
        this.vanillaMaterial = new ArmorMaterial(durability, defense, enchantmentValue, equipSound, toughness, knockbackResistance, repairIngredient, assetId);
    }

    public int durability() {return vanillaMaterial.durability();}
    public int enchantmentValue() {return vanillaMaterial.enchantmentValue();}
    public Holder<SoundEvent> equipSound() {return vanillaMaterial.equipSound();}
    public ResourceKey<EquipmentAsset> assetId() {return vanillaMaterial.assetId();}
    public TagKey<Item> repairIngredient() {return vanillaMaterial.repairIngredient();}

    @Override public AerialHellArmorMaterial addAttributeModifier(Holder<Attribute> attribute, float value, AttributeModifier.Operation operation) {return (AerialHellArmorMaterial) super.addAttributeModifier(attribute, value, operation);}

    public ItemAttributeModifiers createAttributes(ArmorType type, List<AttributeEntry> additionalAttributes)
    {
        //vanilla copy
        int defense = this.vanillaMaterial.defense().getOrDefault(type, 0);
        ItemAttributeModifiers.Builder modifiers = ItemAttributeModifiers.builder();
        EquipmentSlotGroup slotGroup = EquipmentSlotGroup.bySlot(type.getSlot());
        Identifier modifierId = this.getModifierId(type.getName());
        modifiers.add(Attributes.ARMOR, new AttributeModifier(modifierId, defense, AttributeModifier.Operation.ADD_VALUE), slotGroup);
        modifiers.add(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(modifierId, this.vanillaMaterial.toughness(), AttributeModifier.Operation.ADD_VALUE), slotGroup);
        if (this.vanillaMaterial.knockbackResistance() > 0.0F) {modifiers.add(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(modifierId, this.vanillaMaterial.knockbackResistance(), AttributeModifier.Operation.ADD_VALUE), slotGroup);}

        //adding extra attributes
        this.applyExtraAttributes(modifiers, additionalAttributes, slotGroup, type.getName()+"_extra");

        return modifiers.build();
    }
}
