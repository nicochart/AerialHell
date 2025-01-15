package fr.factionbedrock.aerialhell.Effect;

import fr.factionbedrock.aerialhell.Client.Packet.AerialHellData;
import fr.factionbedrock.aerialhell.Registry.AerialHellMobEffects;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.Set;

public class ShadowBind extends MobEffect
{
    public ShadowBind(MobEffectCategory typeIn, int liquidColorIn) {super(typeIn, liquidColorIn);}

    @Override public boolean applyEffectTick(ServerLevel level, LivingEntity livingEntity, int amplifier)
    {
        MobEffectInstance instance = livingEntity.getEffect(AerialHellMobEffects.SHADOW_BIND.getDelegate());
        if (instance != null)
        {
            if (amplifier > 0)
            {
                livingEntity.removeEffect(AerialHellMobEffects.SHADOW_BIND.getDelegate());
                livingEntity.addEffect(new MobEffectInstance(AerialHellMobEffects.SHADOW_BIND.getDelegate(), instance.getDuration(), 0));
                if (livingEntity instanceof ServerPlayer serverPlayer) {PacketDistributor.sendToPlayer(serverPlayer, new AerialHellData("reloadTextures", 0));}
            }
            if (instance.getDuration() < 2)
            {
                livingEntity.removeEffect(AerialHellMobEffects.SHADOW_BIND.getDelegate());
                if (livingEntity instanceof ServerPlayer serverPlayer) {PacketDistributor.sendToPlayer(serverPlayer, new AerialHellData("reloadTextures", 0));}
            }
        }

        // Old dynamic chunk update around the player. Caused too much client lag (?).
    	//if (livingEntity instanceof ServerPlayer serverPlayer && livingEntity.level() instanceof ServerLevel serverLevel)
        //{
        //    EntityHelper.refreshChunkColors(serverPlayer, serverLevel, 100);
        //}
        return true;
    }

    @Override public boolean shouldApplyEffectTickThisTick(int duration, int amplifier)
    {
    	return true;
    }

    @Override public boolean isInstantenous() {return false;}

    //@Override public void fillEffectCures(Set<EffectCure> cures, MobEffectInstance effectInstance) {} TODO
}