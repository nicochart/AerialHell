package fr.factionbedrock.aerialhell.Registry;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Recipe.OscillatingRecipe;
import fr.factionbedrock.aerialhell.Recipe.FreezingRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class AerialHellRecipes
{
	public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, AerialHell.MODID);

	public static final RegistryObject<RecipeSerializer<OscillatingRecipe>> OSCILLATING = RECIPE_SERIALIZERS.register("oscillating", OscillatingRecipe.Serializer::new);
	public static final RegistryObject<RecipeSerializer<FreezingRecipe>> FREEZING = RECIPE_SERIALIZERS.register("freezing", FreezingRecipe.Serializer::new);

	@Mod.EventBusSubscriber(modid = AerialHell.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class RecipeTypes
	{
		public static RecipeType<OscillatingRecipe> OSCILLATING;
		public static RecipeType<FreezingRecipe> FREEZING;

		@SubscribeEvent
		public static void onRegisterRecipeSerializers(RegistryEvent.Register<RecipeSerializer<?>> event)
		{
			OSCILLATING = RecipeType.register(AerialHell.MODID + ":oscillating"); //name used in oscillating recipes json files ("type" : "aerialhell:oscillating")
			FREEZING = RecipeType.register(AerialHell.MODID + ":freezing");
		}
	}
}
