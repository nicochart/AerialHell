package fr.factionbedrock.aerialhell.Mixin;

import fr.factionbedrock.aerialhell.Registry.AerialHellMobEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityGravityMixin
{
    @Inject(method = "getEffectiveGravity", at = @At("RETURN"), cancellable = true)
    private void onGetEffectiveGravity(CallbackInfoReturnable<Double> callbackInfo)
    {
        double baseGravity = callbackInfo.getReturnValue();
        LivingEntity entity = (LivingEntity) (Object) this;
        if (entity.hasStatusEffect(AerialHellMobEffects.HEAD_IN_THE_CLOUDS) && !entity.isInSneakingPose())
        {
            Vec3d deltaMovement = entity.getVelocity();
            if (deltaMovement.y < -0.2D)
            {
                double yDeficit = 1.0D / 4.0D * (deltaMovement.y + 0.2D);
                callbackInfo.setReturnValue(yDeficit);
                return;
            }
            entity.setVelocity(deltaMovement.x * 1.5D, deltaMovement.y, deltaMovement.z * 1.5D);
            callbackInfo.setReturnValue(baseGravity - 0.05D);
        }
    }
}
