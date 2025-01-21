package fr.factionbedrock.aerialhell.Recipe;

import fr.factionbedrock.aerialhell.Registry.AerialHellRecipes;
import fr.factionbedrock.aerialhell.Registry.AerialHellRecipes.RecipeTypes;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;

public class FreezingRecipe extends AbstractCookingRecipe
{
	public FreezingRecipe(String groupIn, CookingBookCategory category, Ingredient ingredientIn, ItemStack resultIn, float experienceIn, int cookTimeIn)
	{
		super(groupIn, category, ingredientIn, resultIn, experienceIn, cookTimeIn);
	}

	@Override protected Item furnaceIcon() {return AerialHellBlocks.FREEZER.asItem();}

	@Override public RecipeSerializer<FreezingRecipe> getSerializer() {return AerialHellRecipes.FREEZING.get();}

	@Override public RecipeType<FreezingRecipe> getType() {return RecipeTypes.FREEZING.get();}

	@Override public RecipeBookCategory recipeBookCategory() {return RecipeBookCategories.FURNACE_BLOCKS;}

	public static class Serializer extends AbstractCookingRecipe.Serializer<FreezingRecipe>
	{
		public Serializer() {super(FreezingRecipe::new, 200);}
	}
}
