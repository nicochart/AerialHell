package fr.factionbedrock.aerialhell.Recipe;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellRecipes;
import fr.factionbedrock.aerialhell.Registry.AerialHellRecipes.RecipeTypes;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.AbstractCookingRecipe;
import net.minecraft.item.crafting.CookingRecipeSerializer;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;

public class OscillatingRecipe extends AbstractCookingRecipe
{
	public OscillatingRecipe(ResourceLocation idIn, String groupIn, Ingredient ingredientIn, ItemStack resultIn, float experienceIn, int cookTimeIn)
	{
		super(RecipeTypes.OSCILLATING, idIn, groupIn, ingredientIn, resultIn, experienceIn, cookTimeIn);
	}

	@Override
	public ItemStack getIcon()
	{
		return new ItemStack(AerialHellBlocksAndItems.OSCILLATOR.get());
	}

	@Override
	public IRecipeSerializer<?> getSerializer()
	{
		return AerialHellRecipes.OSCILLATING.get();
	}

	public static class Serializer extends CookingRecipeSerializer<OscillatingRecipe>
	{
		public Serializer()
		{
			super(OscillatingRecipe::new, 200); //AccessTransformer to access the CookingRecipeSerializer IFactory
		}
	}
}
