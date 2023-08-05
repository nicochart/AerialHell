package fr.factionbedrock.aerialhell.Effect;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.phys.Vec3;

public class HeadInTheCloudsEffect extends MobEffect
{
    public HeadInTheCloudsEffect(MobEffectCategory typeIn, int liquidColorIn) {super(typeIn, liquidColorIn);}

    @Override
    public void applyEffectTick(LivingEntity entityLivingBaseIn, int amplifier)
    {
        double x=entityLivingBaseIn.getDeltaMovement().x, y=entityLivingBaseIn.getDeltaMovement().y, z=entityLivingBaseIn.getDeltaMovement().z;
        double xNew = x, yNew = y, zNew = z;
        if (!entityLivingBaseIn.onGround())
        {
            double maxHorizontalSpeed = 0.17f * (1 + amplifier);
            double horizontalSpeed = Math.sqrt(x * x + z * z);
            if (horizontalSpeed < maxHorizontalSpeed) {xNew = x*1.1; zNew = z*1.1;} //maximize horizontal speed if speed is not already too high
        }

        double yMin = Math.min(-0.2 + 0.05 * amplifier, 0.0); //amplifier=0 : -0.2  ;  amplifier=1 : -0.15  ; amplifier=2 : -0.1  ;  amplifier=3 : -0.05  ;  amplifier=4+ : 0.0
		if (entityLivingBaseIn.isCrouching()) {xNew /= 1.2; zNew /= 1.2; if (yNew > -2) {yNew -= 0.02;}} //slow down horizontal speed, faster vertical speed if player is crouching
        else if (y < yMin) {yNew = yMin;} //minimize vertical speed if player is not crouching

        entityLivingBaseIn.setDeltaMovement(xNew, yNew, zNew);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier)
    {
    	return true;
    }

    @Override
    public boolean isInstantenous()
    {
        return false;
    }
}