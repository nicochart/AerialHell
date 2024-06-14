package fr.factionbedrock.aerialhell.Item.Material;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.crafting.Ingredient;

public class AerialHellArmorMaterials
{
	public final static Holder<ArmorMaterial> RUBY = register(":ruby", createProtectionMap(2, 5, 6, 2, 5), 15, SoundEvents.ARMOR_EQUIP_IRON,0.0F,0.0F, () -> Ingredient.of(AerialHellBlocksAndItems.RUBY.get()));
	public final static Holder<ArmorMaterial> AZURITE = register(":azurite", createProtectionMap(1, 3, 5, 2, 4), 10, SoundEvents.ARMOR_EQUIP_GOLD,0.0F,0.0F, () -> Ingredient.of(AerialHellBlocksAndItems.AZURITE_CRYSTAL.get()));
	public final static Holder<ArmorMaterial> MAGMATIC_GEL = register(":magmatic_gel", createProtectionMap(1, 3, 5, 2, 4), 7, SoundEvents.ARMOR_EQUIP_GOLD,0.0F,0.0F, () -> Ingredient.of(AerialHellBlocksAndItems.MAGMATIC_GEL.get()));
	public final static Holder<ArmorMaterial> OBSIDIAN = register(":obsidian", createProtectionMap(3, 6, 8, 3, 11), 37, SoundEvents.ARMOR_EQUIP_DIAMOND,2.0F,0.1F, () -> Ingredient.of(AerialHellBlocksAndItems.OBSIDIAN_SHARD.get()));
	public final static Holder<ArmorMaterial> VOLUCITE = register(":volucite", createProtectionMap(4, 7, 9, 4, 12), 35, SoundEvents.ARMOR_EQUIP_DIAMOND,2.5F,0.05F, () -> Ingredient.of(AerialHellBlocksAndItems.VOLUCITE_VIBRANT.get()));
	public final static Holder<ArmorMaterial> LUNATIC = register(":lunatic", createProtectionMap(4, 7, 8, 3, 11), 33, SoundEvents.ARMOR_EQUIP_GOLD,1.5F,0.0F, () -> Ingredient.of(AerialHellBlocksAndItems.LUNATIC_CRYSTAL.get()));
	public final static Holder<ArmorMaterial> SHADOW = register(":shadow", createProtectionMap(4, 7, 7, 3, 11), 25, SoundEvents.ARMOR_EQUIP_LEATHER,1.0F,0.0F, () -> Ingredient.of(AerialHellBlocksAndItems.CURSED_CRYSAL.get()));
	public final static Holder<ArmorMaterial> ARSONIST = register(":arsonist", createProtectionMap(5, 8, 9, 4, 12), 37, SoundEvents.ARMOR_EQUIP_LEATHER,3.0F,0.1F, () -> Ingredient.of(AerialHellBlocksAndItems.ARSONIST_INGOT.get()));

	//copy of ArmorMaterials "register" method
	private static Holder<ArmorMaterial> register(String id, EnumMap<ArmorItem.Type, Integer> protectionMap, int damageFactor, Holder<SoundEvent> equipSound, float toughness, float knockbackReduction, Supplier<Ingredient> repairIngredient)
	{
		List<ArmorMaterial.Layer> layerList = List.of(new ArmorMaterial.Layer(new ResourceLocation(AerialHell.MODID, id)));
		EnumMap<ArmorItem.Type, Integer> enummap = new EnumMap<>(ArmorItem.Type.class);

		for (ArmorItem.Type armoritem$type : ArmorItem.Type.values()) {enummap.put(armoritem$type, protectionMap.get(armoritem$type));}
		return Registry.registerForHolder(BuiltInRegistries.ARMOR_MATERIAL, new ResourceLocation(AerialHell.MODID, id), new ArmorMaterial(enummap, damageFactor, equipSound, repairIngredient, layerList, toughness, knockbackReduction));
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
