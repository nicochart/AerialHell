package fr.factionbedrock.aerialhell.Recipe;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.AerialHellRecipes;
import fr.factionbedrock.aerialhell.Registry.AerialHellRecipes.RecipeTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.AbstractCookingRecipe;
import net.minecraft.recipe.CookingRecipeSerializer;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.book.CookingRecipeCategory;

public class FreezingRecipe extends AbstractCookingRecipe
{
	public FreezingRecipe(String group, CookingRecipeCategory category, Ingredient ingredient, ItemStack result, float experience, int cookingTime)
	{
		super(RecipeTypes.FREEZING, group, category, ingredient, result, experience, cookingTime);
	}

	@Override public ItemStack createIcon() {return new ItemStack(AerialHellBlocks.FREEZER);}

	@Override public RecipeSerializer<?> getSerializer() {return AerialHellRecipes.FREEZING;}

	public static class Serializer extends CookingRecipeSerializer<FreezingRecipe>
	{
		public Serializer() {super(FreezingRecipe::new, 200);}
	}
}
