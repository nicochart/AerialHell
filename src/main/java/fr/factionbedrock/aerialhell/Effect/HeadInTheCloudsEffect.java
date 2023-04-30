package fr.factionbedrock.aerialhell.Effect;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class HeadInTheCloudsEffect extends MobEffect
{
    public HeadInTheCloudsEffect(MobEffectCategory typeIn, int liquidColorIn)
    {
        super(typeIn, liquidColorIn);
    }

    @Override
    public void applyEffectTick(LivingEntity entityLivingBaseIn, int amplifier)
    {
    	entityLivingBaseIn.flyingSpeed = 0.03f * (1 + amplifier);
		
		if (entityLivingBaseIn.getDeltaMovement().y < -0.2 && !entityLivingBaseIn.isShiftKeyDown())
		{
			entityLivingBaseIn.setDeltaMovement(entityLivingBaseIn.getDeltaMovement().x, -0.2, entityLivingBaseIn.getDeltaMovement().z);
		}
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