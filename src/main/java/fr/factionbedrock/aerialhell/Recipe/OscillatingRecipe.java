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

//vanilla copy : net.minecraft.world.item.crafting.SmeltingRecipe
public class OscillatingRecipe extends AbstractCookingRecipe
{
	public OscillatingRecipe(String group, CookingBookCategory category, Ingredient ingredient, ItemStack result, float experience, int cookingTime)
	{
		super(RecipeTypes.OSCILLATING, group, category, ingredient, result, experience, cookingTime);
	}

	@Override public ItemStack getToastSymbol() {return new ItemStack(AerialHellBlocks.OSCILLATOR);}

	@Override public RecipeSerializer<?> getSerializer() {return AerialHellRecipes.OSCILLATING;}

	public static class Serializer extends SimpleCookingSerializer<OscillatingRecipe>
	{
		public Serializer() {super(OscillatingRecipe::new, 200);}
	}
}
