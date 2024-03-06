package fr.factionbedrock.aerialhell.Effect;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class AerialHellEffect extends MobEffect
{
    public AerialHellEffect(MobEffectCategory typeIn, int liquidColorIn)
    {
        super(typeIn, liquidColorIn);
    }

    @Override
    public void applyEffectTick(LivingEntity entityLivingBaseIn, int amplifier) {}
    
    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {return true;}

    @Override
    public boolean isInstantenous() {return false;}
}