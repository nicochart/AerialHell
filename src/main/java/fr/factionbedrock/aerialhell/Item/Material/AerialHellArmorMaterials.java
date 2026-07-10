package fr.factionbedrock.aerialhell.Item.Material;

import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import net.minecraft.Util;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.EnumMap;

public class AerialHellArmorMaterials
{
	public final static AerialHellArmorMaterial RUBY = new AerialHellArmorMaterial("ruby", 15, createProtectionMap(2, 5, 6, 2, 5), 9, SoundEvents.ARMOR_EQUIP_IRON,0.0F,0.0F, () -> Ingredient.of(AerialHellTags.Items.REPAIRS_RUBY_MATERIAL));
	public final static AerialHellArmorMaterial AZURITE = new AerialHellArmorMaterial("azurite", 10, createProtectionMap(1, 3, 5, 2, 4), 25, SoundEvents.ARMOR_EQUIP_GOLD,0.0F,0.0F, () -> Ingredient.of(AerialHellTags.Items.REPAIRS_AZURITE_MATERIAL));
	public final static AerialHellArmorMaterial MAGMATIC_GEL = new AerialHellArmorMaterial("magmatic_gel", 7, createProtectionMap(1, 3, 5, 2, 4), 25, SoundEvents.ARMOR_EQUIP_GOLD,0.0F,0.0F, () -> Ingredient.of(AerialHellTags.Items.REPAIRS_MAGMATIC_GEL_MATERIAL));
	public final static AerialHellArmorMaterial OBSIDIAN = new AerialHellArmorMaterial("obsidian", 37, createProtectionMap(3, 6, 8, 3, 11), 10, SoundEvents.ARMOR_EQUIP_DIAMOND,2.0F,0.1F, () -> Ingredient.of(AerialHellTags.Items.REPAIRS_OBSIDIAN_MATERIAL))
			.addAttributeModifier(Attributes.EXPLOSION_KNOCKBACK_RESISTANCE, 0.1F, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
			.addAttributeModifier(Attributes.MOVEMENT_SPEED, -0.002F, AttributeModifier.Operation.ADD_VALUE)
			.addAttributeModifier(Attributes.ATTACK_DAMAGE, 0.5F, AttributeModifier.Operation.ADD_VALUE);
	public final static AerialHellArmorMaterial VOLUCITE = new AerialHellArmorMaterial("volucite", 35, createProtectionMap(4, 7, 9, 4, 12), 10, SoundEvents.ARMOR_EQUIP_DIAMOND,2.5F,0.05F, () -> Ingredient.of(AerialHellTags.Items.REPAIRS_VOLUCITE_MATERIAL))
			.addAttributeModifier(Attributes.GRAVITY, -0.05F, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
			.addAttributeModifier(Attributes.JUMP_STRENGTH, 0.05F, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
	public final static AerialHellArmorMaterial LUNATIC = new AerialHellArmorMaterial("lunatic", 33, createProtectionMap(4, 7, 8, 3, 11), 15, SoundEvents.ARMOR_EQUIP_GOLD,1.5F,0.0F, () -> Ingredient.of(AerialHellTags.Items.REPAIRS_LUNATIC_MATERIAL))
			.addAttributeModifier(Attributes.ENTITY_INTERACTION_RANGE, 0.05F, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
			.addAttributeModifier(Attributes.BLOCK_INTERACTION_RANGE, 0.05F, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
	public final static AerialHellArmorMaterial SHADOW = new AerialHellArmorMaterial("shadow", 25, createProtectionMap(4, 7, 7, 3, 11), 15, SoundEvents.ARMOR_EQUIP_LEATHER,1.0F,0.0F, () -> Ingredient.of(AerialHellTags.Items.REPAIRS_SHADOW_MATERIAL))
			.addAttributeModifier(Attributes.MOVEMENT_SPEED, 0.002F, AttributeModifier.Operation.ADD_VALUE);
	public final static AerialHellArmorMaterial ARSONIST = new AerialHellArmorMaterial("arsonist", 37, createProtectionMap(5, 8, 9, 4, 12), 15, SoundEvents.ARMOR_EQUIP_LEATHER,3.0F,0.1F, () -> Ingredient.of(AerialHellTags.Items.REPAIRS_ARSONIST_MATERIAL))
			.addAttributeModifier(Attributes.BURNING_TIME, 0.5F, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
			.addAttributeModifier(Attributes.ATTACK_DAMAGE, 0.5F, AttributeModifier.Operation.ADD_VALUE);

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
