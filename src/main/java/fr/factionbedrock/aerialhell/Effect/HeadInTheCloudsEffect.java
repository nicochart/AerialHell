package fr.factionbedrock.aerialhell.Effect;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class HeadInTheCloudsEffect extends AerialHellEffect
{
    public HeadInTheCloudsEffect(MobEffectCategory category, int liquidColor) {super(category, liquidColor);}

    @Override
    public boolean applyEffectTick(ServerLevel serverWorld, LivingEntity entityLivingBaseIn, int amplifier)
    {
        // 1.21.4 - Movement effects are now applied in LivingEntityFallFlyingMixin and LivingEntityGravityMixin
        //          because applying player delta movement modifications here doesn't work : delta movement is edited somewhere else just after mob effect tick and before any movement.
        //          so any movement modification here is ignored for players
        entityLivingBaseIn.resetFallDistance(); //reset fall distance
        return true;
    }
}