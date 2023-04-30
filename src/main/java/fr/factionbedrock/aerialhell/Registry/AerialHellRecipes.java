package fr.factionbedrock.aerialhell.Registry;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Recipe.OscillatingRecipe;
import fr.factionbedrock.aerialhell.Recipe.FreezingRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class AerialHellRecipes
{
	public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, AerialHell.MODID);

	public static final RegistryObject<RecipeSerializer<OscillatingRecipe>> OSCILLATING = RECIPE_SERIALIZERS.register("oscillating", OscillatingRecipe.Serializer::new);
	public static final RegistryObject<RecipeSerializer<FreezingRecipe>> FREEZING = RECIPE_SERIALIZERS.register("freezing", FreezingRecipe.Serializer::new);

	public static class RecipeTypes
	{
		public static final RecipeType<OscillatingRecipe> OSCILLATING = RecipeType.register(AerialHell.MODID + ":oscillating"); //name used in oscillating recipes json files ("type" : "aerialhell:oscillating")
		public static final RecipeType<FreezingRecipe> FREEZING = RecipeType.register(AerialHell.MODID + ":freezing");
	}
}
