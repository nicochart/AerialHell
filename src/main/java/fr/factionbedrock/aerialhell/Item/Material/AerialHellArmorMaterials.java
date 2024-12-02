package fr.factionbedrock.aerialhell.Item.Material;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

public class AerialHellArmorMaterials
{
	public final static RegistryEntry<ArmorMaterial> RUBY = register("ruby", createProtectionMap(2, 5, 6, 2, 5), 9, SoundEvents.ITEM_ARMOR_EQUIP_IRON,0.0F,0.0F, () -> Ingredient.ofItems(AerialHellItems.RUBY));
	public final static RegistryEntry<ArmorMaterial> AZURITE = register("azurite", createProtectionMap(1, 3, 5, 2, 4), 25, SoundEvents.ITEM_ARMOR_EQUIP_GOLD,0.0F,0.0F, () -> Ingredient.ofItems(AerialHellItems.AZURITE_CRYSTAL));
	public final static RegistryEntry<ArmorMaterial> MAGMATIC_GEL = register("magmatic_gel", createProtectionMap(1, 3, 5, 2, 4), 25, SoundEvents.ITEM_ARMOR_EQUIP_GOLD,0.0F,0.0F, () -> Ingredient.ofItems(AerialHellItems.MAGMATIC_GEL));
	public final static RegistryEntry<ArmorMaterial> OBSIDIAN = register("obsidian", createProtectionMap(3, 6, 8, 3, 11), 10, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND,2.0F,0.1F, () -> Ingredient.ofItems(AerialHellItems.OBSIDIAN_SHARD));
	public final static RegistryEntry<ArmorMaterial> VOLUCITE = register("volucite", createProtectionMap(4, 7, 9, 4, 12), 10, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND,2.5F,0.05F, () -> Ingredient.ofItems(AerialHellItems.VOLUCITE_VIBRANT));
	public final static RegistryEntry<ArmorMaterial> LUNATIC = register("lunatic", createProtectionMap(4, 7, 8, 3, 11), 15, SoundEvents.ITEM_ARMOR_EQUIP_GOLD,1.5F,0.0F, () -> Ingredient.ofItems(AerialHellItems.LUNATIC_CRYSTAL));
	public final static RegistryEntry<ArmorMaterial> SHADOW = register("shadow", createProtectionMap(4, 7, 7, 3, 11), 15, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER,1.0F,0.0F, () -> Ingredient.ofItems(AerialHellItems.CURSED_CRYSAL));
	public final static RegistryEntry<ArmorMaterial> ARSONIST = register("arsonist", createProtectionMap(5, 8, 9, 4, 12), 15, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER,3.0F,0.1F, () -> Ingredient.ofItems(AerialHellItems.ARSONIST_INGOT));

	//copy of ArmorMaterials "register" method
	private static RegistryEntry<ArmorMaterial> register(String id, EnumMap<ArmorItem.Type, Integer> protection, int enchantability, RegistryEntry<SoundEvent> equipSound, float toughness, float knockbackReduction, Supplier<Ingredient> repairIngredient)
	{
		List<ArmorMaterial.Layer> layerList = List.of(new ArmorMaterial.Layer(Identifier.of(id)));
		EnumMap<ArmorItem.Type, Integer> enumMap = new EnumMap<>(ArmorItem.Type.class);

		for (ArmorItem.Type armoritem$type : ArmorItem.Type.values()) {enumMap.put(armoritem$type, protection.get(armoritem$type));}
		return Registry.registerReference(Registries.ARMOR_MATERIAL, Identifier.of(id), new ArmorMaterial(enumMap, enchantability, equipSound, repairIngredient, layerList, toughness, knockbackReduction));
	}

	private static EnumMap<ArmorItem.Type, Integer> createProtectionMap(int boots, int leggings, int chestplate, int helmet, int body)
	{
		return Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
			map.put(ArmorItem.Type.BOOTS, boots);
			map.put(ArmorItem.Type.LEGGINGS, leggings);
			map.put(ArmorItem.Type.CHESTPLATE, chestplate);
			map.put(ArmorItem.Type.HELMET, helmet);
			map.put(ArmorItem.Type.BODY, body);
		});
	}
}
