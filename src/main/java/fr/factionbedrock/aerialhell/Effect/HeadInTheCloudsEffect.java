package fr.factionbedrock.aerialhell.Effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

public class HeadInTheCloudsEffect extends Effect
{
	private int duration;
	private boolean jumping = false;
    public HeadInTheCloudsEffect(EffectType typeIn, int liquidColorIn)
    {
        super(typeIn, liquidColorIn);
    }

    @Override
    public void performEffect(LivingEntity entityLivingBaseIn, int amplifier)
    {
    	entityLivingBaseIn.jumpMovementFactor = 0.06f;
    	
		if (entityLivingBaseIn.getHealth() < entityLivingBaseIn.getMaxHealth() && duration  % 40 == 0)
        {
            entityLivingBaseIn.heal(1.0F);
        }
		if (entityLivingBaseIn.isOnGround())
		{
			jumping = false;
		}
		else if (entityLivingBaseIn.getMotion().y > 0 && !jumping)
		{
			entityLivingBaseIn.setMotion(entityLivingBaseIn.getMotion().x, entityLivingBaseIn.getMotion().y + 0.6, entityLivingBaseIn.getMotion().z);
			if (entityLivingBaseIn.getMotion().y > 1.5)
			{
				jumping=true;
			}
		}
		
		if (entityLivingBaseIn.getMotion().y < -0.2 && !entityLivingBaseIn.isSneaking())
		{
			entityLivingBaseIn.setMotion(entityLivingBaseIn.getMotion().x, -0.2, entityLivingBaseIn.getMotion().z);
		}
    }

    @Override
    public boolean isReady(int duration, int amplifier)
    {
    	this.duration = duration;
    	return true;
    }

    @Override
    public boolean isInstant()
    {
        return false;
    }
}