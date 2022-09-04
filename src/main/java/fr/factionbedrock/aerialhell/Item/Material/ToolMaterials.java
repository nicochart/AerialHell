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
		59, //durabilité
		2.0F, //efficacité
		0.0F, //Dégats d'attaque
		15, //Enchantabilité
		() -> Ingredient.fromItems(AerialHellBlocksAndItems.STELLAR_COBBLESTONE_ITEM.get()) //Ingrédient de réparation (Enclume)
	);
	
	public static final IItemTier stellar_stone = new ToolMaterial
	(
		1, //Niveau de minage
		131, //durabilité
		4.0F, //efficacité
		1.0F, //Dégats d'attaque
		5, //Enchantabilité
		() -> {return Ingredient.fromTag(AerialHellTags.Items.AERIALHELL_PLANKS);} //Ingrédient de réparation (Enclume)
	);
	
	public static final IItemTier ruby = new ToolMaterial
	(
		2, //Niveau de minage
		250, //durabilité
		6.0F, //efficacité
		2.0F, //Dégats d'attaque
		14, //Enchantabilité
		() -> Ingredient.fromItems(AerialHellBlocksAndItems.RUBY.get()) //Ingrédient de réparation (Enclume)
	);
	
	public static final IItemTier azurite = new ToolMaterial
	(
		0, //Niveau de minage
		32, //durabilité
		12.0F, //efficacité
		0.0F, //Dégats d'attaque
		22, //Enchantabilité
		() -> Ingredient.fromItems(AerialHellBlocksAndItems.AZURITE_CRYSTAL.get()) //Ingrédient de réparation (Enclume)
	);
	
	public static final IItemTier magmatic_gel = new ToolMaterial
	(
		0, //Niveau de minage
		32, //durabilité
		12.0F, //efficacité
		0.0F, //Dégats d'attaque
		22, //Enchantabilité
		() -> Ingredient.fromItems(AerialHellBlocksAndItems.MAGMATIC_GEL.get()) //Ingrédient de réparation (Enclume)
	);
	
	public static final IItemTier obsidian = new ToolMaterial
	(
		3, //Niveau de minage
		2031, //durabilité
		8.0F, //efficacité
		3.0F, //Dégats d'attaque
		10, //Enchantabilité
		() -> Ingredient.fromItems(AerialHellBlocksAndItems.OBSIDIAN_SHARD.get()) //Ingrédient de réparation (Enclume)
	);
	
	public static final IItemTier volucite = new ToolMaterial
	(
		4, //Niveau de minage
		1620, //durabilité
		8.5F, //efficacité
		4.5F, //Dégats d'attaque
		10, //Enchantabilité
		() -> Ingredient.fromItems(AerialHellBlocksAndItems.VOLUCITE_VIBRANT.get()) //Ingrédient de réparation (Enclume)
	);
	
	public static final IItemTier heavy = new ToolMaterial
	(
		3, //Niveau de minage
		1561, //durabilité
		8.0F, //efficacité
		6.0F, //Dégats d'attaque
		10, //Enchantabilité
		() -> Ingredient.fromItems(AerialHellBlocksAndItems.OBSIDIAN_SHARD.get()) //Ingrédient de réparation (Enclume)
	);
	
	public static final IItemTier lunatic = new ToolMaterial
	(
		4, //Niveau de minage
		1712, //durabilité
		8.0F, //efficacité
		4.0F, //Dégats d'attaque
		15, //Enchantabilité
		() -> Ingredient.fromItems(AerialHellBlocksAndItems.LUNATIC_CRYSTAL.get()) //Ingrédient de réparation (Enclume)
	);

	public static final IItemTier arsonist = new ToolMaterial
	(
		4, //Niveau de minage
		2031, //durabilité
		9.0F, //efficacité
		5.5F, //Dégats d'attaque
		15, //Enchantabilité
		() -> Ingredient.fromItems(AerialHellBlocksAndItems.ARSONIST_INGOT.get()) //Ingrédient de réparation (Enclume)
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
