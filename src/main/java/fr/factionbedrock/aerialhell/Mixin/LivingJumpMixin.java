package fr.factionbedrock.aerialhell.Mixin;

import fr.factionbedrock.aerialhell.Registry.AerialHellMobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class LivingJumpMixin
{
    @Inject(method = "jumpFromGround", at = @At("RETURN"), cancellable = true)
    private void onLivingJump(CallbackInfo callbackInfo)
    {
        LivingEntity livingEntity = (LivingEntity) (Object) this;

        if (livingEntity.hasEffect(AerialHellMobEffects.HEAD_IN_THE_CLOUDS))
        {
            float multiplier = 1.0F + 0.25F * (livingEntity.getEffect(AerialHellMobEffects.HEAD_IN_THE_CLOUDS).getAmplifier() + 1);
            Vec3 baseMotion = livingEntity.getDeltaMovement();
            livingEntity.setDeltaMovement(baseMotion.x, baseMotion.y + 0.2F * multiplier, baseMotion.z);
        }
    }
}
