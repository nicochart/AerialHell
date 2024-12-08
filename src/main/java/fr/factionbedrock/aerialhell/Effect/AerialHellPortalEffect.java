package fr.factionbedrock.aerialhell.Effect;

import fr.factionbedrock.aerialhell.Registry.AerialHellMobEffects;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class AerialHellPortalEffect extends StatusEffect
{
    public AerialHellPortalEffect(StatusEffectCategory category, int liquidColor) {super(category, liquidColor);}

    @Override
    public boolean applyUpdateEffect(LivingEntity livingIn, int amplifier)
    {
    	if (!EntityHelper.isLivingEntityInAerialHellPortal(livingIn) && !EntityHelper.isLivingEntityReadyToTeleport(livingIn))
        {
            livingIn.removeStatusEffect(AerialHellMobEffects.AERIAL_HELL_PORTAL);
        }
        return true;
    }

    @Override public boolean canApplyUpdateEffect(int duration, int amplifier) {return true;}

    @Override public boolean isInstant() {return false;}
}