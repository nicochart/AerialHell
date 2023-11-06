package fr.factionbedrock.aerialhell.Recipe;

import fr.factionbedrock.aerialhell.Registry.AerialHellRecipes;
import fr.factionbedrock.aerialhell.Registry.AerialHellRecipes.RecipeTypes;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.resources.ResourceLocation;

public class FreezingRecipe extends AbstractCookingRecipe
{
	public FreezingRecipe(/*ResourceLocation idIn,*/ String groupIn, CookingBookCategory category, Ingredient ingredientIn, ItemStack resultIn, float experienceIn, int cookTimeIn)
	{
		super(RecipeTypes.FREEZING.get(), /*idIn,*/ groupIn, category, ingredientIn, resultIn, experienceIn, cookTimeIn);
	}

	@Override public ItemStack getToastSymbol() {return new ItemStack(AerialHellBlocksAndItems.FREEZER.get());}

	@Override public RecipeSerializer<?> getSerializer() {return AerialHellRecipes.FREEZING.get();}

	public static class Serializer extends SimpleCookingSerializer<FreezingRecipe>
	{
		public Serializer() {super(FreezingRecipe::new, 200);}
	}
}
