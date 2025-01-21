package fr.factionbedrock.aerialhell.Recipe;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.AerialHellRecipes;
import fr.factionbedrock.aerialhell.Registry.AerialHellRecipes.RecipeTypes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;

//vanilla copy : net.minecraft.world.item.crafting.SmeltingRecipe
public class OscillatingRecipe extends AbstractCookingRecipe
{
	public OscillatingRecipe(String groupIn, CookingBookCategory category, Ingredient ingredientIn, ItemStack resultIn, float experienceIn, int cookTimeIn)
	{
		super(groupIn, category, ingredientIn, resultIn, experienceIn, cookTimeIn);
	}

	@Override protected Item furnaceIcon() {return AerialHellBlocks.OSCILLATOR.asItem();}

	@Override public RecipeSerializer<OscillatingRecipe> getSerializer() {return AerialHellRecipes.OSCILLATING.get();}

	@Override public RecipeType<OscillatingRecipe> getType() {return RecipeTypes.OSCILLATING.get();}

	@Override public RecipeBookCategory recipeBookCategory() {return RecipeBookCategories.FURNACE_BLOCKS;}

	public static class Serializer extends AbstractCookingRecipe.Serializer<OscillatingRecipe>
	{
		public Serializer() {super(OscillatingRecipe::new, 200);}
	}
}
