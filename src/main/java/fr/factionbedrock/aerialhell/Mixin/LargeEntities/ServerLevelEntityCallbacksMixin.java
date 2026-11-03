package fr.factionbedrock.aerialhell.Mixin.LargeEntities;

import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.VoluciteWardenPartEntity;
import fr.factionbedrock.aerialhell.Entity.Util.LargeEntityLevel;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//large volucite warden parts are registered while tracked by the server level, like vanilla dragon parts (see LargeEntityLevel)
@Mixin(targets = "net.minecraft.server.level.ServerLevel$EntityCallbacks")
public class ServerLevelEntityCallbacksMixin
{
    @Inject(method = "onTrackingStart(Lnet/minecraft/world/entity/Entity;)V", at = @At("HEAD"))
    private void registerLargeEntity(Entity entity, CallbackInfo callbackInfo)
    {
        if (entity instanceof VoluciteWardenPartEntity) {LargeEntityLevel.register(entity);}
    }

    @Inject(method = "onTrackingEnd(Lnet/minecraft/world/entity/Entity;)V", at = @At("HEAD"))
    private void unregisterLargeEntity(Entity entity, CallbackInfo callbackInfo)
    {
        if (entity instanceof VoluciteWardenPartEntity) {LargeEntityLevel.unregister(entity);}
    }
}
