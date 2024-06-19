package fr.factionbedrock.aerialhell.Item.Material;


import java.util.function.Supplier;

import com.google.common.base.Suppliers;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.level.block.Block;

public class ToolMaterials
{
	public static final Tier SKY_WOOD = new ToolMaterial(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 59, 2.0F, 0.0F, 15, () -> Ingredient.of(AerialHellTags.Items.AERIALHELL_PLANKS));
	public static final Tier STELLAR_STONE = new ToolMaterial(BlockTags.INCORRECT_FOR_STONE_TOOL, 131, 4.0F, 1.0F, 5, () -> Ingredient.of(AerialHellBlocksAndItems.STELLAR_COBBLESTONE_ITEM.get()));
	public static final Tier RUBY = new ToolMaterial(BlockTags.INCORRECT_FOR_IRON_TOOL, 250, 6.0F, 2.0F, 14, () -> Ingredient.of(AerialHellBlocksAndItems.RUBY.get()));
	public static final Tier AZURITE = new ToolMaterial(BlockTags.INCORRECT_FOR_STONE_TOOL, 110, 12.0F, 0.0F, 22, () -> Ingredient.of(AerialHellBlocksAndItems.AZURITE_CRYSTAL.get()));
	public static final Tier MAGMATIC_GEL = new ToolMaterial(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 32, 12.0F, 0.0F, 22, () -> Ingredient.of(AerialHellBlocksAndItems.MAGMATIC_GEL.get()));
	public static final Tier OBSIDIAN = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 2031, 8.0F, 3.0F, 10, () -> Ingredient.of(AerialHellBlocksAndItems.OBSIDIAN_SHARD.get()));
	public static final Tier SHADOW = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 112, 8.0F, 3.0F, 15, () -> Ingredient.of(AerialHellBlocksAndItems.CURSED_CRYSAL.get()));
	public static final Tier VOLUCITE = new ToolMaterial(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 1620, 8.5F, 4.5F, 10, () -> Ingredient.of(AerialHellBlocksAndItems.VOLUCITE_VIBRANT.get()));
	public static final Tier HEAVY = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1561, 8.0F, 6.0F, 10, () -> Ingredient.of(AerialHellBlocksAndItems.OBSIDIAN_SHARD.get()));
	public static final Tier LUNATIC = new ToolMaterial(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 1712, 8.0F, 4.0F, 15, () -> Ingredient.of(AerialHellBlocksAndItems.LUNATIC_CRYSTAL.get()));
	public static final Tier BREAKER = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 742, 7.5F, 5.0F, 10, () -> Ingredient.of(AerialHellBlocksAndItems.LUNATIC_CRYSTAL.get()));
	public static final Tier ARSONIST = new ToolMaterial(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 2031, 9.0F, 5.5F, 15, () -> Ingredient.of(AerialHellBlocksAndItems.ARSONIST_INGOT.get()));

	public static class ToolMaterial implements Tier
	{
		private final TagKey<Block> incorrectBlocksForDrops;
		private final int uses;
		private final float speed;
		private final float damage;
		private final int enchantmentValue;
		private final Supplier<Ingredient> repairIngredient;

		public ToolMaterial(final TagKey<Block> incorrectBlocksForDrops, final int uses, final float speed, final float damage, final int enchantmentValue, final Supplier<Ingredient> repairIngredients)
		{
			this.incorrectBlocksForDrops = incorrectBlocksForDrops;
			this.uses = uses;
			this.speed = speed;
			this.damage = damage;
			this.enchantmentValue = enchantmentValue;
			this.repairIngredient = Suppliers.memoize(repairIngredients::get);
		}

		@Override public int getUses() {return this.uses;}
		@Override public float getSpeed() {return this.speed;}
		@Override public float getAttackDamageBonus() {return this.damage;}
		@Override public TagKey<Block> getIncorrectBlocksForDrops() {return this.incorrectBlocksForDrops;}
		@Override public int getEnchantmentValue() {return this.enchantmentValue;}
		@Override public Ingredient getRepairIngredient() {return this.repairIngredient.get();}
	}
}
