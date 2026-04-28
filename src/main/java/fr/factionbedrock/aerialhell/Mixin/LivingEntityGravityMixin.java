package fr.factionbedrock.aerialhell.Mixin;

import fr.factionbedrock.aerialhell.Registry.AerialHellMobEffects;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
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
        if (entity.isInLiquid() || EntityHelper.isSpectatorPlayer(entity) || EntityHelper.isFlyingCreativePlayer(entity)) {return;}
        if (entity.hasEffect(AerialHellMobEffects.HEAD_IN_THE_CLOUDS))
        {
            int verticalSlowdownAmplification = entity.getEffect(AerialHellMobEffects.HEAD_IN_THE_CLOUDS).getAmplifier() + 1;
            if (entity.hasEffect(MobEffects.SLOW_FALLING)) {verticalSlowdownAmplification++;}

            //overriding vanilla getEffectiveGravity to ignore slowFalling in baseGravity value.
            baseGravity = entity.getGravity();
            if (entity.isCrouching()) {callbackInfo.setReturnValue(baseGravity); return;}

            Vec3 deltaMovement = entity.getDeltaMovement();

            double yMovementMin = -0.2D / verticalSlowdownAmplification;
            if (deltaMovement.y < yMovementMin)
            {
                double yDeficit = 0.25D * (deltaMovement.y - yMovementMin);
                callbackInfo.setReturnValue(yDeficit);
                return;
            }
            entity.setDeltaMovement(deltaMovement.x * 1.5D, deltaMovement.y, deltaMovement.z * 1.5D);
            callbackInfo.setReturnValue(baseGravity - 0.05D);
        }
    }
}
