package fr.factionbedrock.aerialhell.Effect;

import fr.factionbedrock.aerialhell.Registry.AerialHellMobEffects;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class AerialHellPortalEffect extends MobEffect
{
    public AerialHellPortalEffect(MobEffectCategory typeIn, int liquidColorIn)
    {
        super(typeIn, liquidColorIn);
    }

    @Override
    public boolean applyEffectTick(LivingEntity livingIn, int amplifier)
    {
    	if (!EntityHelper.isLivingEntityInAerialHellPortal(livingIn) && !EntityHelper.isLivingEntityReadyToTeleport(livingIn))
        {
            livingIn.removeEffect(AerialHellMobEffects.AERIAL_HELL_PORTAL.getHolder().get());
        }
        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier)
    {
    	return true;
    }

    @Override
    public boolean isInstantenous()
    {
        return false;
    }
}