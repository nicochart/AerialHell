package fr.factionbedrock.aerialhell.Registry;

import com.google.common.collect.ImmutableMap;
import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Recipe.OscillatingRecipe;
import fr.factionbedrock.aerialhell.Recipe.FreezingRecipe;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class AerialHellRecipes
{
	public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(BuiltInRegistries.RECIPE_SERIALIZER, AerialHell.MODID);

	public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<OscillatingRecipe>> OSCILLATING_SERIALIZER = RECIPE_SERIALIZERS.register("oscillating", () -> OscillatingRecipe.SERIALIZER);
	public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<FreezingRecipe>> FREEZING_SERIALIZER = RECIPE_SERIALIZERS.register("freezing", () -> FreezingRecipe.SERIALIZER);

	public static class RecipeTypes
	{
		public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(BuiltInRegistries.RECIPE_TYPE, AerialHell.MODID);

		public static final DeferredHolder<RecipeType<?>, RecipeType<OscillatingRecipe>> OSCILLATING = RECIPE_TYPES.register("oscillating", () -> RecipeType.simple(Identifier.fromNamespaceAndPath(AerialHell.MODID, "oscillating")));
		public static final DeferredHolder<RecipeType<?>, RecipeType<FreezingRecipe>> FREEZING = RECIPE_TYPES.register("freezing", () -> RecipeType.simple(Identifier.fromNamespaceAndPath(AerialHell.MODID, "freezing")));
	}

	public static class PropertySet
	{
		public static final ResourceKey<RecipePropertySet> OSCILLATOR_INPUT = register("oscillating_input");
		public static final ResourceKey<RecipePropertySet> FREEZER_INPUT = register("freezing_input");

		private static ResourceKey<RecipePropertySet> register(String id) {return ResourceKey.create(RecipePropertySet.TYPE_KEY, Identifier.fromNamespaceAndPath(AerialHell.MODID, id));}

		public static void registerRecipeProperty()
		{
			Map<ResourceKey<RecipePropertySet>, RecipeManager.IngredientExtractor> recipePropertyMap = new HashMap<>(RecipeManager.RECIPE_PROPERTY_SETS);
			recipePropertyMap.put(OSCILLATOR_INPUT, forSingleInput(AerialHellRecipes.RecipeTypes.OSCILLATING.get()));
			recipePropertyMap.put(FREEZER_INPUT, forSingleInput(AerialHellRecipes.RecipeTypes.FREEZING.get()));
			RecipeManager.RECIPE_PROPERTY_SETS = recipePropertyMap;
		}

		//copy of net.minecraft.world.item.crafting.RecipeManager method of same name
		private static RecipeManager.IngredientExtractor forSingleInput(RecipeType<? extends SingleItemRecipe> type)
		{
			return recipe -> recipe.getType() == type && recipe instanceof SingleItemRecipe singleItemRecipe ? Optional.of(singleItemRecipe.input()) : Optional.empty();
		}
	}
}
