package fr.factionbedrock.aerialhell.Effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

public class HeadInTheCloudsEffect extends Effect
{
    public HeadInTheCloudsEffect(EffectType typeIn, int liquidColorIn)
    {
        super(typeIn, liquidColorIn);
    }

    @Override
    public void performEffect(LivingEntity entityLivingBaseIn, int amplifier)
    {
    	entityLivingBaseIn.jumpMovementFactor = 0.03f * (1 + amplifier);
        entityLivingBaseIn.fallDistance = 0.0f;
		
		if (entityLivingBaseIn.getMotion().y < -0.2 && !entityLivingBaseIn.isSneaking())
		{
			entityLivingBaseIn.setMotion(entityLivingBaseIn.getMotion().x, -0.2, entityLivingBaseIn.getMotion().z);
		}
    }

    @Override
    public boolean isReady(int duration, int amplifier)
    {
    	return true;
    }

    @Override
    public boolean isInstant()
    {
        return false;
    }
}