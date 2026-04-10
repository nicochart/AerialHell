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

public class FreezingRecipe extends AbstractCookingRecipe
{
	public FreezingRecipe(String group, CookingBookCategory category, Ingredient ingredient, ItemStack result, float experience, int cookingTime)
	{
		super(group, category, ingredient, result, experience, cookingTime);
	}

	@Override public Item furnaceIcon() {return AerialHellBlocks.FREEZER.asItem();}

	@Override public RecipeType<FreezingRecipe> getType() {return RecipeTypes.FREEZING;}

	@Override public RecipeSerializer<FreezingRecipe> getSerializer() {return AerialHellRecipes.FREEZING;}

	@Override public RecipeBookCategory recipeBookCategory() {return RecipeBookCategories.FURNACE_BLOCKS;}

	public static class Serializer extends AbstractCookingRecipe.Serializer<FreezingRecipe>
	{
		public Serializer() {super(FreezingRecipe::new, 200);}
	}
}
