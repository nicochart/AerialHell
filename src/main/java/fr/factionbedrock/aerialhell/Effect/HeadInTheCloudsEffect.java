package fr.factionbedrock.aerialhell.Effect;

import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.phys.Vec3;

public class HeadInTheCloudsEffect extends MobEffect
{
    public HeadInTheCloudsEffect(MobEffectCategory typeIn, int liquidColorIn) {super(typeIn, liquidColorIn);}

    @Override
    public void applyEffectTick(LivingEntity entityLivingBaseIn, int amplifier)
    {
        entityLivingBaseIn.resetFallDistance();
        if (entityLivingBaseIn.isInWater() || entityLivingBaseIn.isInLava() || EntityHelper.isSpectatorPlayer(entityLivingBaseIn) || EntityHelper.isFlyingCreativePlayer(entityLivingBaseIn)) {return;}

        double x=entityLivingBaseIn.getDeltaMovement().x, y=entityLivingBaseIn.getDeltaMovement().y, z=entityLivingBaseIn.getDeltaMovement().z;
        double xNew = x, yNew = y, zNew = z;
        if (!entityLivingBaseIn.onGround())
        {
            double maxHorizontalSpeed = 0.17f * (1 + amplifier);
            double horizontalSpeed = Math.sqrt(x * x + z * z);
            if (horizontalSpeed < maxHorizontalSpeed) {xNew = x*1.1; zNew = z*1.1;} //maximize horizontal speed if speed is not already too high
        }

        int verticalAmplifier = entityLivingBaseIn.hasEffect(MobEffects.SLOW_FALLING) ? amplifier + 1 : amplifier; //slow falling counts as one more level
        double yMin = Math.min(-0.2 + 0.05 * verticalAmplifier, 0.0); //verticalAmplifier=0 : -0.2  ;  1 : -0.15  ; 2 : -0.1  ;  3 : -0.05  ;  4+ : 0.0
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