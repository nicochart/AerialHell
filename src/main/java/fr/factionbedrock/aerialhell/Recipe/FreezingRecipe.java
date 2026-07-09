package fr.factionbedrock.aerialhell.Recipe;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.AerialHellRecipes;
import fr.factionbedrock.aerialhell.Registry.AerialHellRecipes.RecipeTypes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCookingSerializer;

public class FreezingRecipe extends AbstractCookingRecipe
{
	public FreezingRecipe(String group, CookingBookCategory category, Ingredient ingredient, ItemStack result, float experience, int cookingTime)
	{
		super(RecipeTypes.FREEZING, group, category, ingredient, result, experience, cookingTime);
	}

	@Override public ItemStack getToastSymbol() {return new ItemStack(AerialHellBlocks.FREEZER);}

	@Override public RecipeSerializer<?> getSerializer() {return AerialHellRecipes.FREEZING;}

	public static class Serializer extends SimpleCookingSerializer<FreezingRecipe>
	{
		public Serializer() {super(FreezingRecipe::new, 200);}
	}
}
