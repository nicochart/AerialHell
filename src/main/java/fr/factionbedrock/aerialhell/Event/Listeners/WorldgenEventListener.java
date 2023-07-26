package fr.factionbedrock.aerialhell.Event.Listeners;

import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellPlacedFeatures;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class WorldgenEventListener
{
    // TODO : use forge biome modifiers - https://forge.gemwire.uk/wiki/Biome_Modifiers#Builtin_Biome_Modifier_Types
    /*@SubscribeEvent(priority = EventPriority.HIGH)
    public static void BiomeLoadingEvent(final BiomeLoadingEvent event)
    {
        if (event.getCategory() != Biome.BiomeCategory.THEEND && event.getCategory() != Biome.BiomeCategory.NETHER)
        {
            Object RegisterPlacedFeatures;
            event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES).add(AerialHellPlacedFeatures.STELLAR_PORTAL_FRAME_ORE.getHolder().get());
        }
    }*/
}