package fr.factionbedrock.aerialhell.Registry;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Recipe.OscillatingRecipe;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.crafting.*;
import fr.factionbedrock.aerialhell.Recipe.FreezingRecipe;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class AerialHellRecipes
{
	public static final RecipeSerializer<OscillatingRecipe> OSCILLATING_SERIALIZER = registerSerializer("oscillating", OscillatingRecipe.SERIALIZER);
	public static final RecipeSerializer<FreezingRecipe> FREEZING_SERIALIZER = registerSerializer("freezing", FreezingRecipe.SERIALIZER);

	public static <S extends RecipeSerializer<T>, T extends Recipe<?>> S registerSerializer(String name, S serializer) {return Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, AerialHell.id(name), serializer);}

	public static void load() {}

	public static class RecipeTypes
	{
		public static final RecipeType<OscillatingRecipe> OSCILLATING = registerType("oscillating");
		public static final RecipeType<FreezingRecipe> FREEZING = registerType("freezing");

		public static <T extends Recipe<?>> RecipeType<T> registerType(String name) {return Registry.register(BuiltInRegistries.RECIPE_TYPE, AerialHell.id(name), new RecipeType<T>() {public String toString() {return name;}});}

		public static void load() {}
	}

	public static class PropertySet
	{
		public static final ResourceKey<RecipePropertySet> OSCILLATOR_INPUT = register("oscillating_input");
		public static final ResourceKey<RecipePropertySet> FREEZER_INPUT = register("freezing_input");

		private static ResourceKey<RecipePropertySet> register(String id) {return ResourceKey.create(RecipePropertySet.TYPE_KEY, Identifier.fromNamespaceAndPath(AerialHell.MODID, id));}

		public static void registerRecipeProperty()
		{
			Map<ResourceKey<RecipePropertySet>, RecipeManager.IngredientExtractor> recipePropertyMap = new HashMap<>(RecipeManager.RECIPE_PROPERTY_SETS);
			recipePropertyMap.put(OSCILLATOR_INPUT, forSingleInput(AerialHellRecipes.RecipeTypes.OSCILLATING));
			recipePropertyMap.put(FREEZER_INPUT, forSingleInput(AerialHellRecipes.RecipeTypes.FREEZING));
			RecipeManager.RECIPE_PROPERTY_SETS = recipePropertyMap;
		}

		//copy of net.minecraft.world.item.crafting.RecipeManager method of same name
		private static RecipeManager.IngredientExtractor forSingleInput(RecipeType<? extends SingleItemRecipe> type)
		{
			return recipe -> recipe.getType() == type && recipe instanceof SingleItemRecipe singleItemRecipe ? Optional.of(singleItemRecipe.input()) : Optional.empty();
		}
	}
}
