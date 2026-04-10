package fr.factionbedrock.aerialhell.Effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class AerialHellEffect extends MobEffect
{
    public AerialHellEffect(MobEffectCategory category, int liquidColor) {super(category, liquidColor);}
    
    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {return true;}

    @Override
    public boolean isInstantenous() {return false;}
}