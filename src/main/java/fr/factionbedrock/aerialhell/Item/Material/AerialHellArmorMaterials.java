package fr.factionbedrock.aerialhell.Item.Material;

import java.util.EnumMap;
import java.util.function.Supplier;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import net.minecraft.Util;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class AerialHellArmorMaterials
{
	public final static ArmorMaterial ruby = new AerialHellArmorMaterial
	(
		AerialHell.MODID + ":ruby", //Nom du matériau 
		15, //Facteur de dégats, permet de calculer la durabilité avec le Max_Damage_Array 
		new int[] {2, 5, 6, 2}, //Protection des Bottes, du Pantalon, du Plastron, et du Casque
		9, //Enchantabilité
		SoundEvents.ARMOR_EQUIP_IRON, //Son lorsqu'on équipe
		0.0F, //Robustesse
		0.0F, //Resistance au recul
		() -> Ingredient.of(AerialHellBlocksAndItems.RUBY.get()) //Matériaux de réparation
	);
	
	public final static ArmorMaterial azurite = new AerialHellArmorMaterial
	(
		AerialHell.MODID + ":azurite", //Nom du matériau 
		10, //Facteur de dégats, permet de calculer la durabilité avec le Max_Damage_Array 
		new int[] {1, 3, 5, 2}, //Protection des Bottes, du Pantalon, du Plastron, et du Casque
		25, //Enchantabilité
		SoundEvents.ARMOR_EQUIP_GOLD, //Son lorsqu'on équipe
		0.0F, //Robustesse
		0.0F, //Resistance au recul
		() -> Ingredient.of(AerialHellBlocksAndItems.AZURITE_CRYSTAL.get()) //Matériaux de réparation
	);
	
	public final static ArmorMaterial magmatic_gel = new AerialHellArmorMaterial
	(
		AerialHell.MODID + ":magmatic_gel", //Nom du matériau 
		7, //Facteur de dégats, permet de calculer la durabilité avec le Max_Damage_Array 
		new int[] {1, 3, 5, 2}, //Protection des Bottes, du Pantalon, du Plastron, et du Casque
		25, //Enchantabilité
		SoundEvents.ARMOR_EQUIP_GOLD, //Son lorsqu'on équipe
		0.0F, //Robustesse
		0.0F, //Resistance au recul
		() -> Ingredient.of(AerialHellBlocksAndItems.MAGMATIC_GEL.get()) //Matériaux de réparation
	);
	
	public final static ArmorMaterial obsidian = new AerialHellArmorMaterial
	(
		AerialHell.MODID + ":obsidian", //Nom du matériau 
		37, //Facteur de dégats, permet de calculer la durabilité avec le Max_Damage_Array 
		new int[] {3, 6, 8, 3}, //Protection des Bottes, du Pantalon, du Plastron, et du Casque
		10, //Enchantabilité
		SoundEvents.ARMOR_EQUIP_DIAMOND, //Son lorsqu'on équipe
		2.0F, //Robustesse
		0.1F, //Resistance au recul
		() -> Ingredient.of(AerialHellBlocksAndItems.OBSIDIAN_SHARD.get()) //Matériaux de réparation
	);
	
	public final static ArmorMaterial volucite = new AerialHellArmorMaterial
	(
		AerialHell.MODID + ":volucite", //Nom du matériau 
		35, //Facteur de dégats, permet de calculer la durabilité avec le Max_Damage_Array 
		new int[] {4, 7, 9, 4}, //Protection des Bottes, du Pantalon, du Plastron, et du Casque
		10, //Enchantabilité
		SoundEvents.ARMOR_EQUIP_DIAMOND, //Son lorsqu'on équipe
		2.5F, //Robustesse
		0.05F, //Resistance au recul
		() -> Ingredient.of(AerialHellBlocksAndItems.AZURITE_CRYSTAL.get()) //Matériaux de réparation
	);
	
	public final static ArmorMaterial lunatic = new AerialHellArmorMaterial
	(
		AerialHell.MODID + ":lunatic", //Nom du matériau
		33, //Facteur de dégats, permet de calculer la durabilité avec le Max_Damage_Array
		new int[] {4, 7, 8, 3}, //Protection des Bottes, du Pantalon, du Plastron, et du Casque
		15, //Enchantabilité
		SoundEvents.ARMOR_EQUIP_GOLD, //Son lorsqu'on équipe
		1.5F, //Robustesse
		0.0F, //Resistance au recul
		() -> Ingredient.of(AerialHellBlocksAndItems.LUNATIC_CRYSTAL.get()) //Matériaux de réparation
	);

	public final static ArmorMaterial shadow = new AerialHellArmorMaterial
	(
		AerialHell.MODID + ":shadow", //Nom du matériau
		25, //Facteur de dégats, permet de calculer la durabilité avec le Max_Damage_Array
		new int[] {4, 7, 7, 3}, //Protection des Bottes, du Pantalon, du Plastron, et du Casque
		15, //Enchantabilité
		SoundEvents.ARMOR_EQUIP_LEATHER, //Son lorsqu'on équipe
		1.0F, //Robustesse
		0.0F, //Resistance au recul
		() -> Ingredient.of(AerialHellBlocksAndItems.CURSED_CRYSAL.get()) //Matériaux de réparation
	);
	
	public final static ArmorMaterial arsonist = new AerialHellArmorMaterial
	(
		AerialHell.MODID + ":arsonist", //Nom du matériau 
		37, //Facteur de dégats, permet de calculer la durabilité avec le Max_Damage_Array 
		new int[] {5, 8, 9, 4}, //Protection des Bottes, du Pantalon, du Plastron, et du Casque
		15, //Enchantabilité
		SoundEvents.ARMOR_EQUIP_NETHERITE, //Son lorsqu'on équipe
		3.0F, //Robustesse
		0.1F, //Resistance au recul
		() -> Ingredient.of(AerialHellBlocksAndItems.ARSONIST_INGOT.get()) //Matériaux de réparation
	);
	
	private static class AerialHellArmorMaterial implements ArmorMaterial
	{
		private static final EnumMap<ArmorItem.Type, Integer> HEALTH_FUNCTION_FOR_TYPE = makeDamageReductionForArmorTypeEnumMap(new int[] {13,15,16,11});
        private final String name;
        private final int maxDamageFactor;
		private final EnumMap<ArmorItem.Type, Integer> protectionFunctionForType;
        private final int enchantability;
        private final SoundEvent soundEvent;
        private final float toughness;
        private final float knockbackResistance;
        private final LazyLoadedValue<Ingredient> repairMaterial;

        public AerialHellArmorMaterial(String name, int maxDamageFactor, int[] damageReductionAmountArray, int enchantability, SoundEvent soundEvent, double toughness, float knockbackResistance, Supplier<Ingredient> supplier)
        {
            this.name = name;
            this.maxDamageFactor = maxDamageFactor;
            this.protectionFunctionForType = makeDamageReductionForArmorTypeEnumMap(damageReductionAmountArray);
            this.enchantability = enchantability;
            this.soundEvent = soundEvent;
            this.toughness = (float)toughness;
            this.knockbackResistance = knockbackResistance;
            this.repairMaterial = new LazyLoadedValue<Ingredient>(supplier);
        }

        //getters
		@Override
		public int getDurabilityForType(ArmorItem.Type type) {return HEALTH_FUNCTION_FOR_TYPE.get(type) * this.maxDamageFactor;}

		@Override
		public int getDefenseForType(ArmorItem.Type type) {return this.protectionFunctionForType.get(type);}

        @Override
        public int getEnchantmentValue() {return enchantability;}

        @Override
        public SoundEvent getEquipSound() {return soundEvent;}

        @Override
        public Ingredient getRepairIngredient() {return repairMaterial.get();}

        @OnlyIn(Dist.CLIENT)
        @Override
        public String getName() {return name;}

        @Override
        public float getToughness() {return toughness;}

        @Override
        public float getKnockbackResistance() {return this.knockbackResistance;}

		private static EnumMap<ArmorItem.Type, Integer> makeDamageReductionForArmorTypeEnumMap(int[] damageReductionArray)
		{
			return Util.make(new EnumMap<>(ArmorItem.Type.class), (map) -> {
				map.put(ArmorItem.Type.BOOTS, damageReductionArray[0]);
				map.put(ArmorItem.Type.LEGGINGS, damageReductionArray[1]);
				map.put(ArmorItem.Type.CHESTPLATE, damageReductionArray[2]);
				map.put(ArmorItem.Type.HELMET, damageReductionArray[3]);
			});
		}
    }
}
