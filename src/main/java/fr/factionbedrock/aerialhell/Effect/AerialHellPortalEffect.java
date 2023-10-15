package fr.factionbedrock.aerialhell.Effect;

import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

public class AerialHellPortalEffect extends Effect
{
    public AerialHellPortalEffect(EffectType typeIn, int liquidColorIn)
    {
        super(typeIn, liquidColorIn);
    }

    @Override
    public void performEffect(LivingEntity livingIn, int amplifier)
    {
        if (!EntityHelper.isLivingEntityInAerialHellPortal(livingIn) && !EntityHelper.isLivingEntityReadyToTeleport(livingIn))
        {
            livingIn.removePotionEffect(this);
        }
    }

    @Override public boolean isReady(int duration, int amplifier) {return true;}
    @Override public boolean isInstant() {return false;}
}