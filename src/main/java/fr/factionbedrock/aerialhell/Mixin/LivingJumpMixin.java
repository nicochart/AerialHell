package fr.factionbedrock.aerialhell.Mixin;

import fr.factionbedrock.aerialhell.Registry.AerialHellMobEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class LivingJumpMixin
{
    @Inject(method = "jump", at = @At("RETURN"), cancellable = true)
    private void onLivingJump(CallbackInfo callbackInfo)
    {
        LivingEntity livingEntity = (LivingEntity) (Object) this;

        if (livingEntity.hasStatusEffect(AerialHellMobEffects.HEAD_IN_THE_CLOUDS))
        {
            System.out.println("CALLED");
            int bonus = livingEntity.getStatusEffect(AerialHellMobEffects.HEAD_IN_THE_CLOUDS).getAmplifier() + 1;
            livingEntity.heal(0.5F * bonus);
            Vec3d baseMotion = livingEntity.getVelocity();
            livingEntity.setVelocity(baseMotion.x, baseMotion.y + (0.4 * bonus), baseMotion.z);
        }
    }
}
