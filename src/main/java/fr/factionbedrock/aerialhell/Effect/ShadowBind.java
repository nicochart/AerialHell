package fr.factionbedrock.aerialhell.Effect;

import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class ShadowBind extends MobEffect
{
    public ShadowBind(MobEffectCategory typeIn, int liquidColorIn) {super(typeIn, liquidColorIn);}

    @Override
    public boolean applyEffectTick(LivingEntity livingEntity, int amplifier)
    {
        BlockPos pos = livingEntity.getOnPos().above();
        System.out.println("APPLYING EFFECT TICK TO LIVING ENTITY X="+pos.getX()+" Y="+pos.getY()+" Z="+pos.getZ());
    	if (livingEntity instanceof ServerPlayer serverPlayer && livingEntity.level() instanceof ServerLevel serverLevel)
        {
            EntityHelper.refreshChunkColors(serverPlayer, serverLevel, 10);
        }
        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier)
    {
    	return duration == 1 || duration % 20 == 0;
    }

    @Override
    public boolean isInstantenous()
    {
        return false;
    }
}