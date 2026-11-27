package fr.factionbedrock.aerialhell.Event.Listeners;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Config.LoadedConfigParams;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellDimensions;
import fr.factionbedrock.aerialhell.World.AerialHellWorldSpawnFinder;
import net.minecraft.core.BlockPos;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.storage.LevelData;
import net.neoforged.neoforge.event.level.LevelEvent;
import net.neoforged.neoforge.event.server.ServerStartedEvent;

import javax.annotation.Nullable;

public class WorldSpawnEventListener
{
    @Nullable private static MinecraftServer serverOfNewWorld = null;

    //only fired on world creation (not on next world loads)
    //setting the server to be able to move world spawn on server start
    //fabric : called by CreateWorldSpawnMixin
    public static void onCreateWorldSpawn(LevelEvent.CreateSpawnPosition event)
    {
        if (LoadedConfigParams.SET_WORLD_SPAWN_IN_AERIAL_HELL && event.getLevel() instanceof ServerLevel level) {serverOfNewWorld = level.getServer();}
    }

    //setting the world spawn
    //vanilla spawn point is still created and used as fallback
    //fabric : registered with ServerLifecycleEvents.SERVER_STARTED
    public static void onServerStarted(ServerStartedEvent event)
    {
        if (event.getServer() == serverOfNewWorld) {moveWorldSpawnToAerialHell(event.getServer());}
        serverOfNewWorld = null;
    }

    private static void moveWorldSpawnToAerialHell(MinecraftServer server)
    {
        ServerLevel aerialHell = server.getLevel(AerialHellDimensions.AERIAL_HELL_DIMENSION);
        BlockPos spawnPos = aerialHell == null ? null : AerialHellWorldSpawnFinder.findWorldSpawn(aerialHell);
        if (spawnPos == null) {AerialHell.LOGGER.warn("Aerial Hell : no spawn position found in Aerial Hell, world spawn stays in the overworld."); return;}

        server.setRespawnData(LevelData.RespawnData.of(AerialHellDimensions.AERIAL_HELL_DIMENSION, spawnPos, 0.0F, 0.0F));
        AerialHell.LOGGER.info("Aerial Hell : world spawn set in Aerial Hell at {}.", spawnPos.toShortString());
    }
}
