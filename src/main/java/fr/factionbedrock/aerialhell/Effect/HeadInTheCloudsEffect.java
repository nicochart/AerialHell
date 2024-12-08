package fr.factionbedrock.aerialhell.Effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectCategory;

public class HeadInTheCloudsEffect extends AerialHellEffect
{
    public HeadInTheCloudsEffect(StatusEffectCategory category, int liquidColor) {super(category, liquidColor);}

    @Override
    public boolean applyUpdateEffect(LivingEntity entityLivingBaseIn, int amplifier)
    {
        double x=entityLivingBaseIn.getVelocity().x, y=entityLivingBaseIn.getVelocity().y, z=entityLivingBaseIn.getVelocity().z;
        double xNew = x, yNew = y, zNew = z;
        if (!entityLivingBaseIn.isOnGround())
        {
            double maxHorizontalSpeed = 0.17f * (1 + amplifier);
            double horizontalSpeed = Math.sqrt(x * x + z * z);
            if (horizontalSpeed < maxHorizontalSpeed) {xNew = x*1.1; zNew = z*1.1;} //maximize horizontal speed if speed is not already too high
        }

        double yMin = Math.min(-0.2 + 0.05 * amplifier, 0.0); //amplifier=0 : -0.2  ;  amplifier=1 : -0.15  ; amplifier=2 : -0.1  ;  amplifier=3 : -0.05  ;  amplifier=4+ : 0.0
		if (entityLivingBaseIn.isSneaking()) {xNew /= 1.2; zNew /= 1.2; if (yNew > -2) {yNew -= 0.02;}} //slow down horizontal speed, faster vertical speed if player is crouching
        else if (y < yMin) {yNew = yMin;} //minimize vertical speed if player is not crouching

        entityLivingBaseIn.setVelocity(xNew, yNew, zNew);
        entityLivingBaseIn.onLanding(); //reset fall distance
        return true;
    }
}