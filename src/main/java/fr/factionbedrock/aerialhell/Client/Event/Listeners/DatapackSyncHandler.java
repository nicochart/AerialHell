package fr.factionbedrock.aerialhell.Client.Event.Listeners;

import fr.factionbedrock.aerialhell.Registry.AerialHellRecipes;
import net.neoforged.neoforge.event.OnDatapackSyncEvent;

public class DatapackSyncHandler
{
    //NeoForge : Recipe sync done here
    //Fabric : recipe (serializers) sync done in AerialHellSetup
    public static void onDatapackSync(OnDatapackSyncEvent event)
    {
        event.sendRecipes(AerialHellRecipes.RecipeTypes.OSCILLATING.get(), AerialHellRecipes.RecipeTypes.FREEZING.get());
    }
}
