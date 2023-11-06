package fr.factionbedrock.aerialhell.Integration.JEI;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Recipe.FreezingRecipe;
import fr.factionbedrock.aerialhell.Recipe.OscillatingRecipe;
import fr.factionbedrock.aerialhell.Registry.AerialHellRecipes;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.List;
import java.util.Objects;

//Thanks to Kaupenjoe for his JEI integration tutorial : https://www.youtube.com/watch?v=yBfIt-joUxQ

@JeiPlugin
public class AerialHellJei implements IModPlugin
{
    public RecipeType<OscillatingRecipe> OSCILLATING = RecipeType.create(AerialHell.MODID, "oscillating", OscillatingRecipe.class);
    public RecipeType<FreezingRecipe> FREEZING = RecipeType.create(AerialHell.MODID, "freezing", FreezingRecipe.class);

    @Override
	public ResourceLocation getPluginUid() {return new ResourceLocation(AerialHell.MODID, "jei_plugin");}

	@Override
    public void registerCategories(IRecipeCategoryRegistration registration)
	{
		registration.addRecipeCategories(new OscillatingRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
		registration.addRecipeCategories(new FreezingRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration)
    {
        RecipeManager rm = Objects.requireNonNull(Minecraft.getInstance().level).getRecipeManager();
        List<OscillatingRecipe> oscillating_recipes = rm.getAllRecipesFor(AerialHellRecipes.RecipeTypes.OSCILLATING.get()); //TODO
        List<FreezingRecipe> freezing_recipes = rm.getAllRecipesFor(AerialHellRecipes.RecipeTypes.FREEZING.get()); //TODO
        registration.addRecipes(OSCILLATING, oscillating_recipes);
        registration.addRecipes(FREEZING, freezing_recipes);
    }
}
