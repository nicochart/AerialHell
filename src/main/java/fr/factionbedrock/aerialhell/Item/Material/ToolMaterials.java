package fr.factionbedrock.aerialhell.Item.Material;


import java.util.function.Supplier;

import com.google.common.base.Suppliers;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import net.minecraft.block.Block;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;

public class ToolMaterials
{
	public static final ToolMaterial SKY_WOOD = new ToolMaterial(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 59, 2.0F, 0.0F, 15, () -> Ingredient.fromTag(AerialHellTags.Items.AERIALHELL_PLANKS));
	public static final ToolMaterial STELLAR_STONE = new ToolMaterial(BlockTags.INCORRECT_FOR_STONE_TOOL, 131, 4.0F, 1.0F, 5, () -> Ingredient.ofItems(AerialHellItems.STELLAR_COBBLESTONE));
	public static final ToolMaterial RUBY = new ToolMaterial(BlockTags.INCORRECT_FOR_IRON_TOOL, 250, 6.0F, 2.0F, 14, () -> Ingredient.ofItems(AerialHellItems.RUBY));
	public static final ToolMaterial AZURITE = new ToolMaterial(BlockTags.INCORRECT_FOR_STONE_TOOL, 110, 12.0F, 0.0F, 22, () -> Ingredient.ofItems(AerialHellItems.AZURITE_CRYSTAL));
	public static final ToolMaterial MAGMATIC_GEL = new ToolMaterial(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 32, 12.0F, 0.0F, 22, () -> Ingredient.ofItems(AerialHellItems.MAGMATIC_GEL));
	public static final ToolMaterial OBSIDIAN = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 2031, 8.0F, 3.0F, 10, () -> Ingredient.ofItems(AerialHellItems.OBSIDIAN_SHARD));
	public static final ToolMaterial SHADOW = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 112, 8.0F, 3.0F, 15, () -> Ingredient.ofItems(AerialHellItems.CURSED_CRYSAL));
	public static final ToolMaterial VOLUCITE = new ToolMaterial(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 1620, 8.5F, 4.5F, 10, () -> Ingredient.ofItems(AerialHellItems.VOLUCITE_VIBRANT));
	public static final ToolMaterial HEAVY = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1561, 8.0F, 6.0F, 10, () -> Ingredient.ofItems(AerialHellItems.OBSIDIAN_SHARD));
	public static final ToolMaterial LUNATIC = new ToolMaterial(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 1712, 8.0F, 4.0F, 15, () -> Ingredient.ofItems(AerialHellItems.LUNATIC_CRYSTAL));
	public static final ToolMaterial BREAKER = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 742, 7.5F, 5.0F, 10, () -> Ingredient.ofItems(AerialHellItems.LUNATIC_CRYSTAL));
	public static final ToolMaterial ARSONIST = new ToolMaterial(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 2031, 9.0F, 5.5F, 15, () -> Ingredient.ofItems(AerialHellItems.ARSONIST_INGOT));

	public static class ToolMaterial implements net.minecraft.item.ToolMaterial
	{
		private final TagKey<Block> incorrectBlocksForDrops;
		private final int itemDurability;
		private final float attackSpeed;
		private final float attackDamage;
		private final int enchantability;
		private final Supplier<Ingredient> repairIngredient;

		public ToolMaterial(final TagKey<Block> incorrectBlocksForDrops, final int uses, final float speed, final float damage, final int enchantmentValue, final Supplier<Ingredient> repairIngredients)
		{
			this.incorrectBlocksForDrops = incorrectBlocksForDrops;
			this.itemDurability = uses;
			this.attackSpeed = speed;
			this.attackDamage = damage;
			this.enchantability = enchantmentValue;
			this.repairIngredient = Suppliers.memoize(repairIngredients::get);
		}

		@Override public int getDurability() {return this.itemDurability;}
		@Override public float getMiningSpeedMultiplier() {return this.attackSpeed;}
		@Override public float getAttackDamage() {return this.attackDamage;}
		@Override public TagKey<Block> getInverseTag() {return this.incorrectBlocksForDrops;}
		@Override public int getEnchantability() {return this.enchantability;}
		@Override public Ingredient getRepairIngredient() {return this.repairIngredient.get();}
	}
}
