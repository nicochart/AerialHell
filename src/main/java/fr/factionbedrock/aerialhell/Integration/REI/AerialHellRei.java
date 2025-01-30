package fr.factionbedrock.aerialhell.Integration.REI;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.Gui.Screen.Inventory.FreezerScreen;
import fr.factionbedrock.aerialhell.Client.Gui.Screen.Inventory.OscillatorScreen;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.client.registry.screen.ScreenRegistry;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.api.common.util.EntryStacks;
import me.shedaniel.rei.forge.REIPluginClient;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@REIPluginClient
public class AerialHellRei implements REIClientPlugin
{
    public static final CategoryIdentifier<AerialHellRecipeDisplay> OSCILLATING = CategoryIdentifier.of(AerialHell.MODID, "plugins/oscillating");
    public static final CategoryIdentifier<AerialHellRecipeDisplay> FREEZING = CategoryIdentifier.of(AerialHell.MODID, "plugins/freezing");

    @Override public void registerCategories(CategoryRegistry registry)
    {
        registry.add(new OscillatingRecipeCategory());
        registry.add(new FreezingRecipeCategory());

        registry.addWorkstations(OSCILLATING, EntryStacks.of(AerialHellBlocks.OSCILLATOR));
        registry.addWorkstations(FREEZING, EntryStacks.of(AerialHellBlocks.FREEZER));
    }

    @Override public void registerDisplays(DisplayRegistry registry)
    {
        REIHelper.OSCILLATING_MAP.forEach((input, output) -> registry.add(new OscillatingRecipeDisplay(EntryStacks.of(input), EntryStacks.of(output))));
        REIHelper.FREEZING_MAP.forEach((input, output) -> registry.add(new FreezingRecipeDisplay(EntryStacks.of(input), EntryStacks.of(output))));
    }

    @Override public void registerScreens(ScreenRegistry registry)
    {
        registry.registerClickArea(screen -> new Rectangle(75, 30, 20, 30), OscillatorScreen.class, OSCILLATING);
        registry.registerClickArea(screen -> new Rectangle(75, 30, 20, 30), FreezerScreen.class, FREEZING);
    }

    private static <R extends AbstractCookingRecipe> List<EntryIngredient> getInputList(R recipe)
    {
        if(recipe == null) return Collections.emptyList();
        List<EntryIngredient> list = new ArrayList<>();
        list.add(EntryIngredients.ofIngredient(recipe.input()));
        return list;
    }
}