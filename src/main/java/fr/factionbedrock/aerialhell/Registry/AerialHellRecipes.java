package fr.factionbedrock.aerialhell.Registry;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Recipe.VibrationRecipe;
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

	public static final RegistryObject<CookingRecipeSerializer<VibrationRecipe>> VIBRATION = RECIPE_SERIALIZERS.register("vibration", VibrationRecipe.Serializer::new);
	public static final RegistryObject<CookingRecipeSerializer<FreezingRecipe>> FREEZING = RECIPE_SERIALIZERS.register("freezing", FreezingRecipe.Serializer::new);

	public static class RecipeTypes
	{
		public static final IRecipeType<VibrationRecipe> VIBRATION = IRecipeType.register(AerialHell.MODID + ":vibration"); //name used in vibration recipes json files ("type" : "aerialhell:vibration")
		public static final IRecipeType<FreezingRecipe> FREEZING = IRecipeType.register(AerialHell.MODID + ":freezing");
	}
}
