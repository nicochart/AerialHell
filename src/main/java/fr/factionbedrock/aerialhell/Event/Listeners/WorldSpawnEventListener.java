package fr.factionbedrock.aerialhell.Event.Listeners;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Config.LoadedConfigParams;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellDimensions;
import fr.factionbedrock.aerialhell.World.AerialHellWorldSpawnFinder;
import net.minecraft.core.BlockPos;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.storage.LevelData;
import org.jetbrains.annotations.Nullable;

public class WorldSpawnEventListener
{
    @Nullable private static MinecraftServer serverOfNewWorld = null;

    //only fired on world creation (not on next world loads)
    //setting the server to be able to move world spawn on server start
    //called by CreateWorldSpawnMixin
    //neoforge : listener of LevelEvent.CreateSpawnPosition
    public static void onCreateWorldSpawn(ServerLevel level)
    {
        if (LoadedConfigParams.SET_WORLD_SPAWN_IN_AERIAL_HELL) {serverOfNewWorld = level.getServer();}
    }

    //setting the world spawn
    //vanilla spawn point is still created and used as fallback
    //registered with ServerLifecycleEvents.SERVER_STARTED in AerialHellSetup
    //neoforge : listener of ServerStartedEvent
    public static void onServerStarted(MinecraftServer server)
    {
        if (server == serverOfNewWorld) {moveWorldSpawnToAerialHell(server);}
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
