package fr.factionbedrock.aerialhell.Effect;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class GodEffect extends AerialHellEffect
{
	private int duration;
    public GodEffect(MobEffectCategory category, int liquidColor) {super(category, liquidColor);}

    @Override
    public boolean applyEffectTick(ServerLevel serverWorld, LivingEntity entityLivingBaseIn, int amplifier)
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
}