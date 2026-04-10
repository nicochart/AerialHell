package fr.factionbedrock.aerialhell.Registry;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Recipe.OscillatingRecipe;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import fr.factionbedrock.aerialhell.Recipe.FreezingRecipe;

public class AerialHellRecipes
{
	public static final RecipeSerializer<OscillatingRecipe> OSCILLATING = registerSerializer("oscillating", new OscillatingRecipe.Serializer());
	public static final RecipeSerializer<FreezingRecipe> FREEZING = registerSerializer("freezing", new FreezingRecipe.Serializer());

	public static <S extends RecipeSerializer<T>, T extends Recipe<?>> S registerSerializer(String name, S serializer) {return Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, AerialHell.id(name), serializer);}

	public static void load() {}

	public static class RecipeTypes
	{
		public static final RecipeType<OscillatingRecipe> OSCILLATING = registerType("oscillating");
		public static final RecipeType<FreezingRecipe> FREEZING = registerType("freezing");

		public static <T extends Recipe<?>> RecipeType<T> registerType(String name) {return Registry.register(BuiltInRegistries.RECIPE_TYPE, AerialHell.id(name), new RecipeType<T>() {public String toString() {return name;}});}

		public static void load() {}
	}
}
