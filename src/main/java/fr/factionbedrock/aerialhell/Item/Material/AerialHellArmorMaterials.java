package fr.factionbedrock.aerialhell.Item.Material;

import java.util.EnumMap;

import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Util;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;

public class AerialHellArmorMaterials
{
	public final static ArmorMaterial RUBY = new ArmorMaterial(15, createProtectionMap(2, 5, 6, 2, 5), 9, SoundEvents.ARMOR_EQUIP_IRON,0.0F,0.0F, AerialHellTags.Items.REPAIRS_RUBY_MATERIAL, AerialHellEquipmentAssets.RUBY);
	public final static ArmorMaterial AZURITE = new ArmorMaterial(10, createProtectionMap(1, 3, 5, 2, 4), 25, SoundEvents.ARMOR_EQUIP_GOLD,0.0F,0.0F, AerialHellTags.Items.REPAIRS_AZURITE_MATERIAL, AerialHellEquipmentAssets.AZURITE);
	public final static ArmorMaterial MAGMATIC_GEL = new ArmorMaterial(7, createProtectionMap(1, 3, 5, 2, 4), 25, SoundEvents.ARMOR_EQUIP_GOLD,0.0F,0.0F, AerialHellTags.Items.REPAIRS_MAGMATIC_GEL_MATERIAL, AerialHellEquipmentAssets.MAGMATIC_GEL);
	public final static ArmorMaterial OBSIDIAN = new ArmorMaterial(37, createProtectionMap(3, 6, 8, 3, 11), 10, SoundEvents.ARMOR_EQUIP_DIAMOND,2.0F,0.1F, AerialHellTags.Items.REPAIRS_OBSIDIAN_MATERIAL, AerialHellEquipmentAssets.OBSIDIAN);
	public final static ArmorMaterial VOLUCITE = new ArmorMaterial(35, createProtectionMap(4, 7, 9, 4, 12), 10, SoundEvents.ARMOR_EQUIP_DIAMOND,2.5F,0.05F, AerialHellTags.Items.REPAIRS_VOLUCITE_MATERIAL, AerialHellEquipmentAssets.VOLUCITE);
	public final static ArmorMaterial LUNATIC = new ArmorMaterial(33, createProtectionMap(4, 7, 8, 3, 11), 15, SoundEvents.ARMOR_EQUIP_GOLD,1.5F,0.0F, AerialHellTags.Items.REPAIRS_LUNATIC_MATERIAL, AerialHellEquipmentAssets.LUNATIC);
	public final static ArmorMaterial SHADOW = new ArmorMaterial(25, createProtectionMap(4, 7, 7, 3, 11), 15, SoundEvents.ARMOR_EQUIP_LEATHER,1.0F,0.0F, AerialHellTags.Items.REPAIRS_SHADOW_MATERIAL, AerialHellEquipmentAssets.SHADOW);
	public final static ArmorMaterial ARSONIST = new ArmorMaterial(37, createProtectionMap(5, 8, 9, 4, 12), 15, SoundEvents.ARMOR_EQUIP_LEATHER,3.0F,0.1F, AerialHellTags.Items.REPAIRS_ARSONIST_MATERIAL, AerialHellEquipmentAssets.ARSONIST);

	private static EnumMap<ArmorType, Integer> createProtectionMap(int boots, int leggings, int chestplate, int helmet, int body)
	{
		return Util.make(new EnumMap<>(ArmorType.class), map -> {
			map.put(ArmorType.BOOTS, boots);
			map.put(ArmorType.LEGGINGS, leggings);
			map.put(ArmorType.CHESTPLATE, chestplate);
			map.put(ArmorType.HELMET, helmet);
			map.put(ArmorType.BODY, body);
		});
	}
}
