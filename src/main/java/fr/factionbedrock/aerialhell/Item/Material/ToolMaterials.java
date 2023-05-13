package fr.factionbedrock.aerialhell.Item.Material;


import java.util.function.Supplier;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.util.LazyLoadedValue;

public class ToolMaterials
{
	public static final Tier sky_wood = new ToolMaterial
	(
		0, //Niveau de minage
		59, //durabilité
		2.0F, //efficacité
		0.0F, //Dégats d'attaque
		15, //Enchantabilité
		() -> Ingredient.of(AerialHellBlocksAndItems.STELLAR_COBBLESTONE_ITEM.get()) //Ingrédient de réparation (Enclume)
	);
	
	public static final Tier stellar_stone = new ToolMaterial
	(
		1, //Niveau de minage
		131, //durabilité
		4.0F, //efficacité
		1.0F, //Dégats d'attaque
		5, //Enchantabilité
		() -> {return Ingredient.of(AerialHellTags.Items.AERIALHELL_PLANKS);} //Ingrédient de réparation (Enclume)
	);
	
	public static final Tier ruby = new ToolMaterial
	(
		2, //Niveau de minage
		250, //durabilité
		6.0F, //efficacité
		2.0F, //Dégats d'attaque
		14, //Enchantabilité
		() -> Ingredient.of(AerialHellBlocksAndItems.RUBY.get()) //Ingrédient de réparation (Enclume)
	);
	
	public static final Tier azurite = new ToolMaterial
	(
		0, //Niveau de minage
		110, //durabilité
		12.0F, //efficacité
		0.0F, //Dégats d'attaque
		22, //Enchantabilité
		() -> Ingredient.of(AerialHellBlocksAndItems.AZURITE_CRYSTAL.get()) //Ingrédient de réparation (Enclume)
	);
	
	public static final Tier magmatic_gel = new ToolMaterial
	(
		0, //Niveau de minage
		32, //durabilité
		12.0F, //efficacité
		0.0F, //Dégats d'attaque
		22, //Enchantabilité
		() -> Ingredient.of(AerialHellBlocksAndItems.MAGMATIC_GEL.get()) //Ingrédient de réparation (Enclume)
	);
	
	public static final Tier obsidian = new ToolMaterial
	(
		3, //Niveau de minage
		2031, //durabilité
		8.0F, //efficacité
		3.0F, //Dégats d'attaque
		10, //Enchantabilité
		() -> Ingredient.of(AerialHellBlocksAndItems.OBSIDIAN_SHARD.get()) //Ingrédient de réparation (Enclume)
	);
	
	public static final Tier shadow = new ToolMaterial
	(
		3, //Niveau de minage
		112, //durabilité
		8.0F, //efficacité
		3.0F, //Dégats d'attaque
		15, //Enchantabilité
		() -> Ingredient.of(AerialHellBlocksAndItems.CURSED_CRYSAL.get()) //Ingrédient de réparation (Enclume)
	);
	
	public static final Tier volucite = new ToolMaterial
	(
		4, //Niveau de minage
		1620, //durabilité
		8.5F, //efficacité
		4.5F, //Dégats d'attaque
		10, //Enchantabilité
		() -> Ingredient.of(AerialHellBlocksAndItems.VOLUCITE_VIBRANT.get()) //Ingrédient de réparation (Enclume)
	);
	
	public static final Tier heavy = new ToolMaterial
	(
		3, //Niveau de minage
		1561, //durabilité
		8.0F, //efficacité
		6.0F, //Dégats d'attaque
		10, //Enchantabilité
		() -> Ingredient.of(AerialHellBlocksAndItems.OBSIDIAN_SHARD.get()) //Ingrédient de réparation (Enclume)
	);
	
	public static final Tier lunatic = new ToolMaterial
	(
		4, //Niveau de minage
		1712, //durabilité
		8.0F, //efficacité
		4.0F, //Dégats d'attaque
		15, //Enchantabilité
		() -> Ingredient.of(AerialHellBlocksAndItems.LUNATIC_CRYSTAL.get()) //Ingrédient de réparation (Enclume)
	);
	
	public static final Tier breaker = new ToolMaterial
	(
		3, //Niveau de minage
		742, //durabilité
		7.5F, //efficacité
		5.0F, //Dégats d'attaque
		10, //Enchantabilité
		() -> Ingredient.of(AerialHellBlocksAndItems.LUNATIC_CRYSTAL.get()) //Ingrédient de réparation (Enclume)
	);

	public static final Tier arsonist = new ToolMaterial
	(
		4, //Niveau de minage
		2031, //durabilité
		9.0F, //efficacité
		5.5F, //Dégats d'attaque
		15, //Enchantabilité
		() -> Ingredient.of(AerialHellBlocksAndItems.ARSONIST_INGOT.get()) //Ingrédient de réparation (Enclume)
	);
	
	private static class ToolMaterial implements Tier
	{

        private final int harvestLevel;
        private final int maxUses;
        private final float efficiency;
        private final float attackDamage;
        private final int enchantability;
        private final LazyLoadedValue<Ingredient> repair;

        public ToolMaterial(int harvestLevel, int maxUses, float efficiency, double attackDamage, int enchantability, Supplier<Ingredient> supplier)
        {
            this.harvestLevel = harvestLevel;
            this.maxUses = maxUses;
            this.efficiency = efficiency;
            this.attackDamage = (float)attackDamage;
            this.enchantability = enchantability;
            this.repair = new LazyLoadedValue<Ingredient>(supplier);
        }

        @Override
        public int getUses() {return maxUses;}

        @Override
        public float getSpeed() {return efficiency;}

        @Override
        public float getAttackDamageBonus() {return attackDamage;}

        @Override
        public int getLevel() {return harvestLevel;}

        @Override
        public int getEnchantmentValue() {return enchantability;}

        @Override
        public Ingredient getRepairIngredient() {return repair.get();}
	}
}
