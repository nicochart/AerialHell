package fr.factionbedrock.aerialhell.Effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

public class GodEffect extends Effect
{
	private int duration;
    public GodEffect(EffectType typeIn, int liquidColorIn)
    {
        super(typeIn, liquidColorIn);
    }

    @Override
    public void performEffect(LivingEntity entityLivingBaseIn, int amplifier)
    {    	
		if (entityLivingBaseIn.getHealth() < entityLivingBaseIn.getMaxHealth() && duration  % 20 == 0)
        {
            entityLivingBaseIn.heal(1.0F);
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