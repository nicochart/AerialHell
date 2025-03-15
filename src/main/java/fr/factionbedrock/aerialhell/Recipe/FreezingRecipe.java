package fr.factionbedrock.aerialhell.Recipe;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.AerialHellRecipes;
import fr.factionbedrock.aerialhell.Registry.AerialHellRecipes.RecipeTypes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.AbstractCookingRecipe;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.book.CookingRecipeCategory;
import net.minecraft.recipe.book.RecipeBookCategories;
import net.minecraft.recipe.book.RecipeBookCategory;

public class FreezingRecipe extends AbstractCookingRecipe
{
	public FreezingRecipe(String group, CookingRecipeCategory category, Ingredient ingredient, ItemStack result, float experience, int cookingTime)
	{
		super(group, category, ingredient, result, experience, cookingTime);
	}

	@Override public Item getCookerItem() {return AerialHellBlocks.FREEZER.asItem();}

	@Override public RecipeType<FreezingRecipe> getType() {return RecipeTypes.FREEZING;}

	@Override public RecipeSerializer<FreezingRecipe> getSerializer() {return AerialHellRecipes.FREEZING;}

	@Override public RecipeBookCategory getRecipeBookCategory() {return RecipeBookCategories.FURNACE_BLOCKS;}

	public static class Serializer extends AbstractCookingRecipe.Serializer<FreezingRecipe>
	{
		public Serializer() {super(FreezingRecipe::new, 200);}
	}
}
