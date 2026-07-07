package fr.factionbedrock.aerialhell.Item.Armor;

import fr.factionbedrock.aerialhell.Item.Material.AerialHellArmorMaterial;
import fr.factionbedrock.aerialhell.Item.Material.AttributeEntryList;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.component.ItemAttributeModifiers;

import java.util.function.Supplier;

public class AerialHellArmorItem extends ArmorItem
{
    private final Supplier<ItemAttributeModifiers> aerialHellDefaultModifiers;
    public AerialHellArmorItem(AerialHellArmorMaterial armorMaterial, Type type, Properties properties) {this(armorMaterial, type, new AttributeEntryList(), properties);}
    public AerialHellArmorItem(AerialHellArmorMaterial armorMaterial, Type type, AttributeEntryList additionalAttributes, Properties properties)
    {
        super(armorMaterial.vanillaMaterial, type, properties);
        aerialHellDefaultModifiers = armorMaterial.createAttributes(type, additionalAttributes);
    }

    @Override public ItemAttributeModifiers getDefaultAttributeModifiers()
    {
        return this.aerialHellDefaultModifiers.get();
    }
}
