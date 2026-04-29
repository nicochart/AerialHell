package fr.factionbedrock.aerialhell.Item.Armor;

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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AerialHellArmorMaterial
{
    public final ArmorMaterial vanillaMaterial;
    private final List<AttributeEntry> extraAttributes = new ArrayList<>();

    public AerialHellArmorMaterial(int durability, Map<ArmorType, Integer> defense, int enchantmentValue, Holder<SoundEvent> equipSound, float toughness, float knockbackResistance, TagKey<Item> repairIngredient, ResourceKey<EquipmentAsset> assetId)
    {
        this.vanillaMaterial = new ArmorMaterial(durability, defense, enchantmentValue, equipSound, toughness, knockbackResistance, repairIngredient, assetId);
    }

    public int durability() {return vanillaMaterial.durability();}
    public int enchantmentValue() {return vanillaMaterial.enchantmentValue();}
    public Holder<SoundEvent> equipSound() {return vanillaMaterial.equipSound();}
    public ResourceKey<EquipmentAsset> assetId() {return vanillaMaterial.assetId();}
    public TagKey<Item> repairIngredient() {return vanillaMaterial.repairIngredient();}

    public AerialHellArmorMaterial addAttributeModifier(Holder<Attribute> attribute, float value, AttributeModifier.Operation operation)
    {
        this.extraAttributes.add(new AttributeEntry(attribute, value, operation));
        return this;
    }

    public ItemAttributeModifiers createAttributes(ArmorType type)
    {
        //vanilla copy
        int defense = this.vanillaMaterial.defense().getOrDefault(type, 0);
        ItemAttributeModifiers.Builder modifiers = ItemAttributeModifiers.builder();
        EquipmentSlotGroup slotGroup = EquipmentSlotGroup.bySlot(type.getSlot());
        Identifier modifierId = Identifier.fromNamespaceAndPath(AerialHell.MODID, "armor." + type.getName());
        modifiers.add(Attributes.ARMOR, new AttributeModifier(modifierId, defense, AttributeModifier.Operation.ADD_VALUE), slotGroup);
        modifiers.add(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(modifierId, this.vanillaMaterial.toughness(), AttributeModifier.Operation.ADD_VALUE), slotGroup);
        if (this.vanillaMaterial.knockbackResistance() > 0.0F) {modifiers.add(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(modifierId, this.vanillaMaterial.knockbackResistance(), AttributeModifier.Operation.ADD_VALUE), slotGroup);}

        //adding custom attributes
        for (AttributeEntry entry : this.extraAttributes) {modifiers.add(entry.attribute(), new AttributeModifier(modifierId, entry.value, entry.operation), slotGroup);}

        return modifiers.build();
    }

    private record AttributeEntry(Holder<Attribute> attribute, float value, AttributeModifier.Operation operation) {}
}
