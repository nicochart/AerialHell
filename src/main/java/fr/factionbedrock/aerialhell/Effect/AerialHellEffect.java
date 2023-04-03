package fr.factionbedrock.aerialhell.Effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

public class AerialHellEffect extends Effect
{
    public AerialHellEffect(EffectType typeIn, int liquidColorIn)
    {
        super(typeIn, liquidColorIn);
    }

    @Override
    public void performEffect(LivingEntity entityLivingBaseIn, int amplifier) {}
    
    @Override
    public boolean isReady(int duration, int amplifier) {return true;}

    @Override
    public boolean isInstant() {return false;}
}