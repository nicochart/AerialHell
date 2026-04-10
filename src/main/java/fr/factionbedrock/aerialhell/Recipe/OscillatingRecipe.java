package fr.factionbedrock.aerialhell.Recipe;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.AerialHellRecipes;
import fr.factionbedrock.aerialhell.Registry.AerialHellRecipes.RecipeTypes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeBookCategories;
import net.minecraft.world.item.crafting.RecipeBookCategory;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

//vanilla copy : net.minecraft.world.item.crafting.SmeltingRecipe
public class OscillatingRecipe extends AbstractCookingRecipe
{
	public OscillatingRecipe(String group, CookingBookCategory category, Ingredient ingredient, ItemStack result, float experience, int cookingTime)
	{
		super(group, category, ingredient, result, experience, cookingTime);
	}

	@Override public Item furnaceIcon() {return AerialHellBlocks.OSCILLATOR.asItem();}

	@Override public RecipeType<OscillatingRecipe> getType() {return RecipeTypes.OSCILLATING;}

	@Override public RecipeSerializer<OscillatingRecipe> getSerializer() {return AerialHellRecipes.OSCILLATING;}

	@Override public RecipeBookCategory recipeBookCategory() {return RecipeBookCategories.FURNACE_BLOCKS;}

	public static class Serializer extends AbstractCookingRecipe.Serializer<OscillatingRecipe>
	{
		public Serializer() {super(OscillatingRecipe::new, 200);}
	}
}
