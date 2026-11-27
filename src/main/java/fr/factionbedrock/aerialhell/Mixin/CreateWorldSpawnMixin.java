package fr.factionbedrock.aerialhell.Mixin;

import fr.factionbedrock.aerialhell.Event.Listeners.WorldSpawnEventListener;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.progress.LevelLoadListener;
import net.minecraft.world.level.storage.ServerLevelData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftServer.class)
public class CreateWorldSpawnMixin
{
    //MinecraftServer.setInitialSpawn is only called on world creation (not on next world loads)
    //neoforge : done with LevelEvent.CreateSpawnPosition, fired in setInitialSpawn (not for debug worlds)
    @Inject(method = "setInitialSpawn", at = @At("HEAD"))
    private static void onCreateWorldSpawn(ServerLevel level, ServerLevelData levelData, boolean spawnBonusChest, boolean isDebug, LevelLoadListener levelLoadListener, CallbackInfo callbackInfo)
    {
        if (!isDebug) {WorldSpawnEventListener.onCreateWorldSpawn(level);}
    }
}
