package fr.factionbedrock.aerialhell.Registry;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Recipe.OscillatingRecipe;
import fr.factionbedrock.aerialhell.Recipe.FreezingRecipe;
import net.minecraft.item.crafting.CookingRecipeSerializer;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class AerialHellRecipes
{
	public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, AerialHell.MODID);

	public static final RegistryObject<CookingRecipeSerializer<OscillatingRecipe>> OSCILLATING = RECIPE_SERIALIZERS.register("oscillating", OscillatingRecipe.Serializer::new);
	public static final RegistryObject<CookingRecipeSerializer<FreezingRecipe>> FREEZING = RECIPE_SERIALIZERS.register("freezing", FreezingRecipe.Serializer::new);

	public static class RecipeTypes
	{
		public static final IRecipeType<OscillatingRecipe> OSCILLATING = IRecipeType.register(AerialHell.MODID + ":oscillating"); //name used in oscillating recipes json files ("type" : "aerialhell:oscillating")
		public static final IRecipeType<FreezingRecipe> FREEZING = IRecipeType.register(AerialHell.MODID + ":freezing");
	}
}
