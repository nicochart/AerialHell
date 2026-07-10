package fr.factionbedrock.aerialhell.Item.Material;

import com.google.common.base.Suppliers;
import fr.factionbedrock.aerialhell.AerialHell;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class AerialHellArmorMaterial extends ExtraAttributeModifiersMaterial
{
    public final int durability;

    public final Holder<ArmorMaterial> vanillaMaterial;

    public AerialHellArmorMaterial(String name, int durability, Map<ArmorItem.Type, Integer> defense, int enchantmentValue, Holder<SoundEvent> equipSound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient)
    {
        super();
        this.durability = durability;
        this.vanillaMaterial = register(name, defense, enchantmentValue, equipSound, toughness, knockbackResistance, repairIngredient);
    }

    @Override public AerialHellArmorMaterial addAttributeModifier(Holder<Attribute> attribute, float value, AttributeModifier.Operation operation) {return (AerialHellArmorMaterial) super.addAttributeModifier(attribute, value, operation);}

    public Supplier<ItemAttributeModifiers> createAttributes(ArmorItem.Type type, AttributeEntryList additionalAttributes)
    {
        //vanilla copy of ArmorItem default attributes creation
        return Suppliers.memoize(
            () -> {
                int defense = this.vanillaMaterial.value().getDefense(type);
                float toughness = this.vanillaMaterial.value().toughness();
                ItemAttributeModifiers.Builder modifiers = ItemAttributeModifiers.builder();
                EquipmentSlotGroup equipmentslotgroup = EquipmentSlotGroup.bySlot(type.getSlot());
                ResourceLocation resourcelocation = ResourceLocation.withDefaultNamespace("armor." + type.getName());
                modifiers.add(Attributes.ARMOR, new AttributeModifier(resourcelocation, (double)defense, AttributeModifier.Operation.ADD_VALUE), equipmentslotgroup);
                modifiers.add(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(resourcelocation, (double)toughness, AttributeModifier.Operation.ADD_VALUE), equipmentslotgroup);
                float knockbackResistance = this.vanillaMaterial.value().knockbackResistance();
                if (knockbackResistance > 0.0F) {modifiers.add(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(resourcelocation, (double)knockbackResistance, AttributeModifier.Operation.ADD_VALUE), equipmentslotgroup);}

                this.applyExtraAttributes(modifiers, additionalAttributes, EquipmentSlotGroup.bySlot(type.getSlot()), type.getName()+"_extra");

                return modifiers.build();
            }
        );
    }

    //copy of ArmorMaterials "register" method
    private static Holder<ArmorMaterial> register(String id, Map<ArmorItem.Type, Integer> protectionMap, int enchantmentValue, Holder<SoundEvent> equipSound, float toughness, float knockbackReduction, Supplier<Ingredient> repairIngredient)
    {
        List<ArmorMaterial.Layer> layerList = List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, id)));
        EnumMap<ArmorItem.Type, Integer> enummap = new EnumMap<>(ArmorItem.Type.class);

        for (ArmorItem.Type armoritem$type : ArmorItem.Type.values()) {enummap.put(armoritem$type, protectionMap.get(armoritem$type));}
        return Registry.registerForHolder(BuiltInRegistries.ARMOR_MATERIAL, ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, id), new ArmorMaterial(enummap, enchantmentValue, equipSound, repairIngredient, layerList, toughness, knockbackReduction));
    }
}
