package fr.factionbedrock.aerialhell.Effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class AerialHellEffect extends StatusEffect
{
    public AerialHellEffect(StatusEffectCategory category, int liquidColor) {super(category, liquidColor);}
    
    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {return true;}

    @Override
    public boolean isInstant() {return false;}
}