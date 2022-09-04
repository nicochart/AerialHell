package fr.factionbedrock.aerialhell.Item.Material;


import java.util.function.Supplier;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellTags;
import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;

public class ToolMaterials
{
	public static final IItemTier sky_wood = new ToolMaterial
	(
		0, //Niveau de minage
		59, //durabilit�
		2.0F, //efficacit�
		0.0F, //D�gats d'attaque
		15, //Enchantabilit�
		() -> Ingredient.fromItems(AerialHellBlocksAndItems.STELLAR_COBBLESTONE_ITEM.get()) //Ingr�dient de r�paration (Enclume)
	);
	
	public static final IItemTier stellar_stone = new ToolMaterial
	(
		1, //Niveau de minage
		131, //durabilit�
		4.0F, //efficacit�
		1.0F, //D�gats d'attaque
		5, //Enchantabilit�
		() -> {return Ingredient.fromTag(AerialHellTags.Items.AERIALHELL_PLANKS);} //Ingr�dient de r�paration (Enclume)
	);
	
	public static final IItemTier ruby = new ToolMaterial
	(
		2, //Niveau de minage
		250, //durabilit�
		6.0F, //efficacit�
		2.0F, //D�gats d'attaque
		14, //Enchantabilit�
		() -> Ingredient.fromItems(AerialHellBlocksAndItems.RUBY.get()) //Ingr�dient de r�paration (Enclume)
	);
	
	public static final IItemTier azurite = new ToolMaterial
	(
		0, //Niveau de minage
		32, //durabilit�
		12.0F, //efficacit�
		0.0F, //D�gats d'attaque
		22, //Enchantabilit�
		() -> Ingredient.fromItems(AerialHellBlocksAndItems.AZURITE_CRYSTAL.get()) //Ingr�dient de r�paration (Enclume)
	);
	
	public static final IItemTier magmatic_gel = new ToolMaterial
	(
		0, //Niveau de minage
		32, //durabilit�
		12.0F, //efficacit�
		0.0F, //D�gats d'attaque
		22, //Enchantabilit�
		() -> Ingredient.fromItems(AerialHellBlocksAndItems.MAGMATIC_GEL.get()) //Ingr�dient de r�paration (Enclume)
	);
	
	public static final IItemTier obsidian = new ToolMaterial
	(
		3, //Niveau de minage
		2031, //durabilit�
		8.0F, //efficacit�
		3.0F, //D�gats d'attaque
		10, //Enchantabilit�
		() -> Ingredient.fromItems(AerialHellBlocksAndItems.OBSIDIAN_SHARD.get()) //Ingr�dient de r�paration (Enclume)
	);
	
	public static final IItemTier volucite = new ToolMaterial
	(
		4, //Niveau de minage
		1620, //durabilit�
		8.5F, //efficacit�
		4.5F, //D�gats d'attaque
		10, //Enchantabilit�
		() -> Ingredient.fromItems(AerialHellBlocksAndItems.VOLUCITE_VIBRANT.get()) //Ingr�dient de r�paration (Enclume)
	);
	
	public static final IItemTier heavy = new ToolMaterial
	(
		3, //Niveau de minage
		1561, //durabilit�
		8.0F, //efficacit�
		6.0F, //D�gats d'attaque
		10, //Enchantabilit�
		() -> Ingredient.fromItems(AerialHellBlocksAndItems.OBSIDIAN_SHARD.get()) //Ingr�dient de r�paration (Enclume)
	);
	
	public static final IItemTier lunatic = new ToolMaterial
	(
		4, //Niveau de minage
		1712, //durabilit�
		8.0F, //efficacit�
		4.0F, //D�gats d'attaque
		15, //Enchantabilit�
		() -> Ingredient.fromItems(AerialHellBlocksAndItems.LUNATIC_CRYSTAL.get()) //Ingr�dient de r�paration (Enclume)
	);

	public static final IItemTier arsonist = new ToolMaterial
	(
		4, //Niveau de minage
		2031, //durabilit�
		9.0F, //efficacit�
		5.5F, //D�gats d'attaque
		15, //Enchantabilit�
		() -> Ingredient.fromItems(AerialHellBlocksAndItems.ARSONIST_INGOT.get()) //Ingr�dient de r�paration (Enclume)
	);
	
	private static class ToolMaterial implements IItemTier
	{

        private final int harvestLevel;
        private final int maxUses;
        private final float efficiency;
        private final float attackDamage;
        private final int enchantability;
        private final LazyValue<Ingredient> repair;

        public ToolMaterial(int harvestLevel, int maxUses, float efficiency, double attackDamage, int enchantability, Supplier<Ingredient> supplier)
        {
            this.harvestLevel = harvestLevel;
            this.maxUses = maxUses;
            this.efficiency = efficiency;
            this.attackDamage = (float)attackDamage;
            this.enchantability = enchantability;
            this.repair = new LazyValue<Ingredient>(supplier);
        }

        @Override
        public int getMaxUses() {return maxUses;}

        @Override
        public float getEfficiency() {return efficiency;}

        @Override
        public float getAttackDamage() {return attackDamage;}

        @Override
        public int getHarvestLevel() {return harvestLevel;}

        @Override
        public int getEnchantability() {return enchantability;}

        @Override
        public Ingredient getRepairMaterial() {return repair.getValue();}
	}
}
