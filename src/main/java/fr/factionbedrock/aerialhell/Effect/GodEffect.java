package fr.factionbedrock.aerialhell.Effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectCategory;

public class GodEffect extends AerialHellEffect
{
	private int duration;
    public GodEffect(StatusEffectCategory category, int liquidColor) {super(category, liquidColor);}

    @Override
    public boolean applyUpdateEffect(LivingEntity entityLivingBaseIn, int amplifier)
    {    	
		if (entityLivingBaseIn.getHealth() < entityLivingBaseIn.getMaxHealth() && duration  % 20 == 0)
        {
            entityLivingBaseIn.heal(1.0F);
        }
        return true;
    }
    
    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier)
    {
    	this.duration = duration;
    	return true;
    }
}