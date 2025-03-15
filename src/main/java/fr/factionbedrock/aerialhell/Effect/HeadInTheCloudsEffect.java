package fr.factionbedrock.aerialhell.Effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.server.world.ServerWorld;

public class HeadInTheCloudsEffect extends AerialHellEffect
{
    public HeadInTheCloudsEffect(StatusEffectCategory category, int liquidColor) {super(category, liquidColor);}

    @Override
    public boolean applyUpdateEffect(ServerWorld serverWorld, LivingEntity entityLivingBaseIn, int amplifier)
    {
        // 1.21.4 - Movement effects are now applied in LivingEntityFallFlyingMixin and LivingEntityGravityMixin
        //          because applying player delta movement modifications here doesn't work : delta movement is edited somewhere else just after mob effect tick and before any movement.
        //          so any movement modification here is ignored for players
        entityLivingBaseIn.onLanding(); //reset fall distance
        return true;
    }
}