package fr.factionbedrock.aerialhell.Mixin;

import fr.factionbedrock.aerialhell.Registry.AerialHellMobEffects;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class LivingEntityGravityMixin
{
    @Inject(method = "getGravity", at = @At("RETURN"), cancellable = true)
    private void onGetEffectiveGravity(CallbackInfoReturnable<Double> callbackInfo)
    {
        double baseGravity = callbackInfo.getReturnValue();
        Entity entity = (Entity) (Object) this;
        if (!(entity instanceof LivingEntity livingEntity) || livingEntity.isCrouching()) {return;}
        if (livingEntity.isInLiquid() || EntityHelper.isSpectatorPlayer(livingEntity) || EntityHelper.isFlyingCreativePlayer(livingEntity)) {return;}
        if (livingEntity.hasEffect(AerialHellMobEffects.HEAD_IN_THE_CLOUDS))
        {
            int verticalSlowdownAmplification = livingEntity.getEffect(AerialHellMobEffects.HEAD_IN_THE_CLOUDS).getAmplifier() + 1;
            if (livingEntity.hasEffect(MobEffects.SLOW_FALLING)) {verticalSlowdownAmplification++;}

            Vec3 deltaMovement = livingEntity.getDeltaMovement();

            double yMovementMin = -0.2D / verticalSlowdownAmplification;
            if (deltaMovement.y < yMovementMin)
            {
                double yDeficit = 0.25D * (deltaMovement.y - yMovementMin);
                callbackInfo.setReturnValue(yDeficit);
                return;
            }
            callbackInfo.setReturnValue(baseGravity - 0.05D);
        }
    }
}