package fr.factionbedrock.aerialhell.Mixin;

import fr.factionbedrock.aerialhell.Registry.AerialHellMobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class LivingEntityFallFlyingMixin
{
    @Inject(method = "travelInAir", at = @At("RETURN"), cancellable = true)
    private void onTravelInAir(Vec3 travelVector, CallbackInfo callbackInfo)
    {
        LivingEntity entity = (LivingEntity) (Object) this;
        if (entity.hasEffect(AerialHellMobEffects.HEAD_IN_THE_CLOUDS))
        {
            double x = entity.getDeltaMovement().x, y = entity.getDeltaMovement().y, z = entity.getDeltaMovement().z;
            int amplifier = entity.getEffect(AerialHellMobEffects.HEAD_IN_THE_CLOUDS).getAmplifier();
            double maxHorizontalSpeed = 0.17f * (1 + amplifier);
            double horizontalSpeed = Math.sqrt(x * x + z * z);
            if (horizontalSpeed < maxHorizontalSpeed) {x*=1.1; z*=1.1;} //maximize horizontal speed if speed is not already too high
            if (entity.isCrouching()) {x /= 1.2; z /= 1.2;} //slow down horizontal speed if crouching

            entity.setDeltaMovement(x, y, z);
        }
    }
}
