package fr.factionbedrock.aerialhell.Mixin;

import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellDimensions;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerLevel.class)
public class SleepFinishMixin
{
    @Inject(method = "setDayTime", at = @At("RETURN"), cancellable = true)
    private void onSetTimeOfDay(long timeOfDay, CallbackInfo callbackInfo)
    {
        ServerLevel world = (ServerLevel) (Object) this;
        if (!world.dimension().equals(AerialHellDimensions.AERIAL_HELL_DIMENSION)) {return;}

        MinecraftServer server = world.getServer();
        for (ServerLevel serverWorld : server.getAllLevels())
        {
            serverWorld.serverLevelData.setDayTime(timeOfDay);
        }
    }
}
