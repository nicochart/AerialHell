package fr.factionbedrock.aerialhell.Recipe;

import fr.factionbedrock.aerialhell.Registry.AerialHellRecipes;
import fr.factionbedrock.aerialhell.Registry.AerialHellRecipes.RecipeTypes;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.item.crafting.CookingRecipeSerializer;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCookingSerializer;

public class FreezingRecipe extends AbstractCookingRecipe
{
	public FreezingRecipe(ResourceLocation idIn, String groupIn, Ingredient ingredientIn, ItemStack resultIn, float experienceIn, int cookTimeIn)
	{
		super(RecipeTypes.FREEZING, idIn, groupIn, ingredientIn, resultIn, experienceIn, cookTimeIn);
	}

	@Override public ItemStack getToastSymbol() {return new ItemStack(AerialHellBlocksAndItems.FREEZER.get());}

	@Override public RecipeSerializer<?> getSerializer() {return AerialHellRecipes.FREEZING.get();}

	public static class Serializer extends SimpleCookingSerializer<FreezingRecipe>
	{
		public Serializer() {super(FreezingRecipe::new, 200);}
	}
}
