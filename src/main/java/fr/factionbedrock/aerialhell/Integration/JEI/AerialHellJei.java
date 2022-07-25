package fr.factionbedrock.aerialhell.Integration.JEI;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Recipe.FreezingRecipe;
import fr.factionbedrock.aerialhell.Recipe.VibrationRecipe;
import fr.factionbedrock.aerialhell.Registry.AerialHellRecipes;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.util.ResourceLocation;

import java.util.Objects;
import java.util.stream.Collectors;

//Thanks to Kaupenjoe for his JEI integration tutorial : https://www.youtube.com/watch?v=L7srFZgHuy8

@JeiPlugin
public class AerialHellJei implements IModPlugin
{
	@Override
	public ResourceLocation getPluginUid() {return new ResourceLocation(AerialHell.MODID, "jei_plugin");}

	@Override
    public void registerCategories(IRecipeCategoryRegistration registration)
	{
		registration.addRecipeCategories(new VibrationRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
		registration.addRecipeCategories(new FreezingRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration)
    {
        RecipeManager rm = Objects.requireNonNull(Minecraft.getInstance().world).getRecipeManager();
        registration.addRecipes(rm.getRecipesForType(AerialHellRecipes.RecipeTypes.VIBRATION).stream().filter(r -> r instanceof VibrationRecipe).collect(Collectors.toList()), VibrationRecipeCategory.UID);
        registration.addRecipes(rm.getRecipesForType(AerialHellRecipes.RecipeTypes.FREEZING).stream().filter(r -> r instanceof FreezingRecipe).collect(Collectors.toList()), FreezingRecipeCategory.UID);
    }
}
