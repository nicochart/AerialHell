package fr.factionbedrock.aerialhell.Client.Caches;

import net.minecraft.world.item.crafting.RecipeMap;
import net.neoforged.neoforge.client.event.RecipesReceivedEvent;

public class RecipesCache
{
    public static RecipeMap RECIPES_CACHE = RecipeMap.EMPTY;

    public static void onRecipesReceived(RecipesReceivedEvent event) {RECIPES_CACHE = event.getRecipeMap();}
}
