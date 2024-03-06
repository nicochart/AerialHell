package fr.factionbedrock.aerialhell.Recipe;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellRecipes;
import fr.factionbedrock.aerialhell.Registry.AerialHellRecipes.RecipeTypes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.resources.ResourceLocation;

//vanilla copy : net.minecraft.world.item.crafting.SmeltingRecipe
public class OscillatingRecipe extends AbstractCookingRecipe
{
	public OscillatingRecipe(ResourceLocation idIn, String groupIn, CookingBookCategory category, Ingredient ingredientIn, ItemStack resultIn, float experienceIn, int cookTimeIn)
	{
		super(RecipeTypes.OSCILLATING.get(), idIn, groupIn, category, ingredientIn, resultIn, experienceIn, cookTimeIn);
	}

	@Override public ItemStack getToastSymbol()
	{
		return new ItemStack(AerialHellBlocksAndItems.OSCILLATOR.get());
	}

	@Override public RecipeSerializer<?> getSerializer() {return AerialHellRecipes.OSCILLATING.get();}

	public static class Serializer extends SimpleCookingSerializer<OscillatingRecipe>
	{
		public Serializer() {super(OscillatingRecipe::new, 200);}
	}
}
