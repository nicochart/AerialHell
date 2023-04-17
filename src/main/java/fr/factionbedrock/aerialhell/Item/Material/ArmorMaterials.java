package fr.factionbedrock.aerialhell.Item.Material;

import java.util.function.Supplier;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ArmorMaterials
{
	public final static IArmorMaterial ruby = new ArmorMaterial
	(
		AerialHell.MODID + ":ruby", //Nom du matériau 
		15, //Facteur de dégats, permet de calculer la durabilité avec le Max_Damage_Array 
		new int[] {2, 5, 6, 2}, //Protection des Bottes, du Pantalon, du Plastron, et du Casque
		9, //Enchantabilité
		SoundEvents.ITEM_ARMOR_EQUIP_IRON, //Son lorsqu'on équipe
		0.0F, //Robustesse
		0.0F, //Resistance au recul
		() -> Ingredient.fromItems(AerialHellBlocksAndItems.RUBY.get()) //Matériaux de réparation
	);
	
	public final static IArmorMaterial azurite = new ArmorMaterial
	(
		AerialHell.MODID + ":azurite", //Nom du matériau 
		10, //Facteur de dégats, permet de calculer la durabilité avec le Max_Damage_Array 
		new int[] {1, 3, 5, 2}, //Protection des Bottes, du Pantalon, du Plastron, et du Casque
		25, //Enchantabilité
		SoundEvents.ITEM_ARMOR_EQUIP_GOLD, //Son lorsqu'on équipe
		0.0F, //Robustesse
		0.0F, //Resistance au recul
		() -> Ingredient.fromItems(AerialHellBlocksAndItems.AZURITE_CRYSTAL.get()) //Matériaux de réparation
	);
	
	public final static IArmorMaterial magmatic_gel = new ArmorMaterial
	(
		AerialHell.MODID + ":magmatic_gel", //Nom du matériau 
		7, //Facteur de dégats, permet de calculer la durabilité avec le Max_Damage_Array 
		new int[] {1, 3, 5, 2}, //Protection des Bottes, du Pantalon, du Plastron, et du Casque
		25, //Enchantabilité
		SoundEvents.ITEM_ARMOR_EQUIP_GOLD, //Son lorsqu'on équipe
		0.0F, //Robustesse
		0.0F, //Resistance au recul
		() -> Ingredient.fromItems(AerialHellBlocksAndItems.MAGMATIC_GEL.get()) //Matériaux de réparation
	);
	
	public final static IArmorMaterial obsidian = new ArmorMaterial
	(
		AerialHell.MODID + ":obsidian", //Nom du matériau 
		37, //Facteur de dégats, permet de calculer la durabilité avec le Max_Damage_Array 
		new int[] {3, 6, 8, 3}, //Protection des Bottes, du Pantalon, du Plastron, et du Casque
		10, //Enchantabilité
		SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, //Son lorsqu'on équipe
		2.0F, //Robustesse
		0.1F, //Resistance au recul
		() -> Ingredient.fromItems(AerialHellBlocksAndItems.OBSIDIAN_SHARD.get()) //Matériaux de réparation
	);
	
	public final static IArmorMaterial volucite = new ArmorMaterial
	(
		AerialHell.MODID + ":volucite", //Nom du matériau 
		35, //Facteur de dégats, permet de calculer la durabilité avec le Max_Damage_Array 
		new int[] {4, 7, 9, 4}, //Protection des Bottes, du Pantalon, du Plastron, et du Casque
		10, //Enchantabilité
		SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, //Son lorsqu'on équipe
		2.5F, //Robustesse
		0.05F, //Resistance au recul
		() -> Ingredient.fromItems(AerialHellBlocksAndItems.AZURITE_CRYSTAL.get()) //Matériaux de réparation
	);
	
	public final static IArmorMaterial lunatic = new ArmorMaterial
	(
		AerialHell.MODID + ":lunatic", //Nom du matériau
		33, //Facteur de dégats, permet de calculer la durabilité avec le Max_Damage_Array
		new int[] {4, 7, 8, 3}, //Protection des Bottes, du Pantalon, du Plastron, et du Casque
		15, //Enchantabilité
		SoundEvents.ITEM_ARMOR_EQUIP_GOLD, //Son lorsqu'on équipe
		1.5F, //Robustesse
		0.0F, //Resistance au recul
		() -> Ingredient.fromItems(AerialHellBlocksAndItems.LUNATIC_CRYSTAL.get()) //Matériaux de réparation
	);

	public final static IArmorMaterial shadow = new ArmorMaterial
	(
		AerialHell.MODID + ":shadow", //Nom du matériau
		25, //Facteur de dégats, permet de calculer la durabilité avec le Max_Damage_Array
		new int[] {4, 7, 7, 3}, //Protection des Bottes, du Pantalon, du Plastron, et du Casque
		15, //Enchantabilité
		SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, //Son lorsqu'on équipe
		1.0F, //Robustesse
		0.0F, //Resistance au recul
		() -> Ingredient.fromItems(AerialHellBlocksAndItems.CURSED_CRYSAL.get()) //Matériaux de réparation
	);
	
	public final static IArmorMaterial arsonist = new ArmorMaterial
	(
		AerialHell.MODID + ":arsonist", //Nom du matériau 
		37, //Facteur de dégats, permet de calculer la durabilité avec le Max_Damage_Array 
		new int[] {5, 8, 9, 4}, //Protection des Bottes, du Pantalon, du Plastron, et du Casque
		15, //Enchantabilité
		SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, //Son lorsqu'on équipe
		3.0F, //Robustesse
		0.1F, //Resistance au recul
		() -> Ingredient.fromItems(AerialHellBlocksAndItems.ARSONIST_INGOT.get()) //Matériaux de réparation
	);
	
	private static class ArmorMaterial implements IArmorMaterial
	{
        private static final int[] Max_Damage_Array = new int[] {13,15,16,11};
        private final String name;
        private final int maxDamageFactor;
        private final int[] damageReductionAmountArray;
        private final int enchantability;
        private final SoundEvent soundEvent;
        private final float toughness;
        private final float knockbackResistance;
        private final LazyValue<Ingredient> repairMaterial;

        public ArmorMaterial(String name, int maxDamageFactor, int[] damageReductionAmountArray, int enchantability, SoundEvent soundEvent, double toughness, float knockbackResistance, Supplier<Ingredient> supplier)
        {
            this.name = name;
            this.maxDamageFactor = maxDamageFactor;
            this.damageReductionAmountArray = damageReductionAmountArray;
            this.enchantability = enchantability;
            this.soundEvent = soundEvent;
            this.toughness = (float)toughness;
            this.knockbackResistance = knockbackResistance;
            this.repairMaterial = new LazyValue<Ingredient>(supplier);
        }

        //getters
        @Override
        public int getDurability(EquipmentSlotType slotIn) {return Max_Damage_Array[slotIn.getIndex()] * maxDamageFactor;}

        @Override
        public int getDamageReductionAmount(EquipmentSlotType slotIn) {return damageReductionAmountArray[slotIn.getIndex()];}

        @Override
        public int getEnchantability() {return enchantability;}

        @Override
        public SoundEvent getSoundEvent() {return soundEvent;}

        @Override
        public Ingredient getRepairMaterial() {return repairMaterial.getValue();}

        @OnlyIn(Dist.CLIENT)
        @Override
        public String getName() {return name;}

        @Override
        public float getToughness() {return toughness;}

        @Override
        public float getKnockbackResistance() {return this.knockbackResistance;}
    }
}
