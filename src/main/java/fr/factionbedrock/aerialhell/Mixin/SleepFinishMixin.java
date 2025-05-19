package fr.factionbedrock.aerialhell.Mixin;

import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellDimensions;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerWorld.class)
public class SleepFinishMixin
{
    @Inject(method = "setTimeOfDay", at = @At("RETURN"), cancellable = true)
    private void onSetTimeOfDay(long timeOfDay, CallbackInfo callbackInfo)
    {
        ServerWorld world = (ServerWorld) (Object) this;
        if (!world.getRegistryKey().equals(AerialHellDimensions.AERIAL_HELL_DIMENSION)) {return;}

        MinecraftServer server = world.getServer();
        for (ServerWorld serverWorld : server.getWorlds())
        {
            serverWorld.worldProperties.setTimeOfDay(timeOfDay);
        }
    }
}
