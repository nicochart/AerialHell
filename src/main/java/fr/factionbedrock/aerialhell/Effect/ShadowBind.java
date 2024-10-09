package fr.factionbedrock.aerialhell.Effect;

import fr.factionbedrock.aerialhell.Registry.AerialHellMobEffects;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.common.EffectCure;

import java.util.Set;

public class ShadowBind extends MobEffect
{
    public ShadowBind(MobEffectCategory typeIn, int liquidColorIn) {super(typeIn, liquidColorIn);}

    @Override public boolean applyEffectTick(LivingEntity livingEntity, int amplifier)
    {
    	if (livingEntity instanceof ServerPlayer serverPlayer && livingEntity.level() instanceof ServerLevel serverLevel)
        {
            EntityHelper.refreshChunkColors(serverPlayer, serverLevel, 100);
        }
        return true;
    }

    @Override public boolean shouldApplyEffectTickThisTick(int duration, int amplifier)
    {
    	return duration == 1 || duration % 20 == 0;
    }

    @Override public boolean isInstantenous() {return false;}

    @Override public void fillEffectCures(Set<EffectCure> cures, MobEffectInstance effectInstance) {}
}