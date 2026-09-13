package fr.factionbedrock.aerialhell.Item.Material;

import com.google.common.collect.ImmutableMultimap;
import fr.factionbedrock.aerialhell.AerialHell;
import net.minecraft.Util;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.EnumMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Supplier;

public class AerialHellArmorMaterial extends ExtraAttributeModifiersMaterial implements ArmorMaterial
{
    //vanilla copy from ArmorItem
    private static final EnumMap<ArmorItem.Type, UUID> ARMOR_MODIFIER_UUID_PER_TYPE = Util.make(new EnumMap<>(ArmorItem.Type.class), (map) -> {
        map.put(ArmorItem.Type.BOOTS, UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6B"));
        map.put(ArmorItem.Type.LEGGINGS, UUID.fromString("D8499B04-0E66-4726-AB29-64469D734E0D"));
        map.put(ArmorItem.Type.CHESTPLATE, UUID.fromString("9F3D476D-C118-4544-8365-64846904B48E"));
        map.put(ArmorItem.Type.HELMET, UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150"));
    });

    public final String name;
    public final int durabilityMultiplier;
    public final Map<ArmorItem.Type, Integer> defense;
    public final int enchantmentValue;
    public final SoundEvent equipSound;
    public final float toughness;
    public final float knockbackResistance;
    public final Supplier<Ingredient> repairIngredient;

    public AerialHellArmorMaterial(String name, int durabilityMultiplier, Map<ArmorItem.Type, Integer> defense, int enchantmentValue, SoundEvent equipSound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient)
    {
        super();
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.defense = defense;
        this.enchantmentValue = enchantmentValue;
        this.equipSound = equipSound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = repairIngredient;
    }

    @Override public AerialHellArmorMaterial addAttributeModifier(Attribute attribute, float value, AttributeModifier.Operation operation) {return (AerialHellArmorMaterial) super.addAttributeModifier(attribute, value, operation);}

    public ImmutableMultimap<Attribute, AttributeModifier> createAttributes(ArmorItem.Type type, AttributeEntryList additionalAttributes)
    {
        //vanilla copy of ArmorItem default attributes creation

        ImmutableMultimap.Builder<Attribute, AttributeModifier> modifiers = ImmutableMultimap.builder();
        UUID uuid = ARMOR_MODIFIER_UUID_PER_TYPE.get(type);
        int defense = this.getDefenseForType(type);
        float toughness = this.getToughness();
        modifiers.put(Attributes.ARMOR, new AttributeModifier(uuid, "Armor modifier", defense, AttributeModifier.Operation.ADDITION));
        modifiers.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(uuid, "Armor toughness", toughness, AttributeModifier.Operation.ADDITION));
        float knockbackResistance = this.getKnockbackResistance();
        if (knockbackResistance > 0) {modifiers.put(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(uuid, "Armor knockback resistance", knockbackResistance, AttributeModifier.Operation.ADDITION));}

        this.applyExtraAttributes(modifiers, additionalAttributes, type.getName()+"_extra");

        return modifiers.build();
    }

    //copy of HEALTH_FUNCTION_FOR_TYPE from ArmorMaterials
    private static final EnumMap<ArmorItem.Type, Integer> HEALTH_FUNCTION_FOR_TYPE = Util.make(new EnumMap<>(ArmorItem.Type.class), (map) -> {
        map.put(ArmorItem.Type.BOOTS, 13);
        map.put(ArmorItem.Type.LEGGINGS, 15);
        map.put(ArmorItem.Type.CHESTPLATE, 16);
        map.put(ArmorItem.Type.HELMET, 11);
    });

    @Override public int getDurabilityForType(ArmorItem.Type type) {return HEALTH_FUNCTION_FOR_TYPE.get(type) * this.durabilityMultiplier;}
    @Override public int getDefenseForType(ArmorItem.Type type) {return this.defense.get(type);}
    @Override public int getEnchantmentValue() {return this.enchantmentValue;}
    @Override public SoundEvent getEquipSound() {return this.equipSound;}
    @Override public Ingredient getRepairIngredient() {return this.repairIngredient.get();}
    @Override public String getName() {return AerialHell.MODID + ":" + this.name;}
    @Override public float getToughness() {return this.toughness;}
    @Override public float getKnockbackResistance() {return this.knockbackResistance;}
}
