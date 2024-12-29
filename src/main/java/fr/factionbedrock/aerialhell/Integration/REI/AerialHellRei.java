package fr.factionbedrock.aerialhell.Integration.REI;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.Gui.Screen.Inventory.*;
import fr.factionbedrock.aerialhell.Recipe.FreezingRecipe;
import fr.factionbedrock.aerialhell.Recipe.OscillatingRecipe;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.AerialHellRecipes;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.client.registry.screen.ScreenRegistry;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.util.EntryStacks;

//Thanks to Kaupenjoe for his JEI integration tutorial : https://www.youtube.com/watch?v=HbZ6ocABo-M

public class AerialHellRei implements REIClientPlugin
{
    public static final CategoryIdentifier<AerialHellRecipeDisplay> OSCILLATING = CategoryIdentifier.of(AerialHell.MODID, "oscillating");
    public static final CategoryIdentifier<AerialHellRecipeDisplay> FREEZING = CategoryIdentifier.of(AerialHell.MODID, "freezing");

    @Override public void registerCategories(CategoryRegistry registry)
    {
        registry.add(new OscillatingRecipeCategory());
        registry.add(new FreezingRecipeCategory());

        registry.addWorkstations(OSCILLATING, EntryStacks.of(AerialHellBlocks.OSCILLATOR));
        registry.addWorkstations(FREEZING, EntryStacks.of(AerialHellBlocks.FREEZER));
    }

    @Override public void registerDisplays(DisplayRegistry registry)
    {
        registry.registerRecipeFiller(OscillatingRecipe.class, AerialHellRecipes.RecipeTypes.OSCILLATING, (recipe) -> new AerialHellRecipeDisplay(recipe, OSCILLATING));
        registry.registerRecipeFiller(FreezingRecipe.class, AerialHellRecipes.RecipeTypes.FREEZING, (recipe) -> new AerialHellRecipeDisplay(recipe, FREEZING));
    }

    @Override public void registerScreens(ScreenRegistry registry)
    {
        registry.registerClickArea(screen -> new Rectangle(75, 30, 20, 30), OscillatorScreen.class, OSCILLATING);
        registry.registerClickArea(screen -> new Rectangle(75, 30, 20, 30), FreezerScreen.class, FREEZING);
    }
}