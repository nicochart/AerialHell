package fr.factionbedrock.aerialhell.Effect;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectCategory;

public class GodEffect extends MobEffect
{
	private int duration;
    public GodEffect(MobEffectCategory typeIn, int liquidColorIn)
    {
        super(typeIn, liquidColorIn);
    }

    @Override
    public boolean applyEffectTick(ServerLevel level, LivingEntity entityLivingBaseIn, int amplifier)
    {    	
		if (entityLivingBaseIn.getHealth() < entityLivingBaseIn.getMaxHealth() && duration  % 20 == 0)
        {
            entityLivingBaseIn.heal(1.0F);
        }
        return true;
    }
    
    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier)
    {
    	this.duration = duration;
    	return true;
    }

    @Override
    public boolean isInstantenous()
    {
        return false;
    }
}