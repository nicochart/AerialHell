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

//vanilla copy : net.minecraft.world.item.crafting.SmeltingRecipe
public class OscillatingRecipe extends AbstractCookingRecipe
{
	public OscillatingRecipe(String group, CookingRecipeCategory category, Ingredient ingredient, ItemStack result, float experience, int cookingTime)
	{
		super(RecipeTypes.OSCILLATING, group, category, ingredient, result, experience, cookingTime);
	}

	@Override public ItemStack createIcon() {return new ItemStack(AerialHellBlocks.OSCILLATOR);}

	@Override public RecipeSerializer<?> getSerializer() {return AerialHellRecipes.OSCILLATING;}

	public static class Serializer extends CookingRecipeSerializer<OscillatingRecipe>
	{
		public Serializer() {super(OscillatingRecipe::new, 200);}
	}
}
