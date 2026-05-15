package fr.factionbedrock.aerialhell.Client.Event.Listeners;

import fr.factionbedrock.aerialhell.Registry.AerialHellRecipes;
import net.neoforged.neoforge.event.OnDatapackSyncEvent;

public class DatapackSyncHandler
{
    public static void onDatapackSync(OnDatapackSyncEvent event)
    {
        event.sendRecipes(AerialHellRecipes.RecipeTypes.OSCILLATING.get(), AerialHellRecipes.RecipeTypes.FREEZING.get());
    }
}
