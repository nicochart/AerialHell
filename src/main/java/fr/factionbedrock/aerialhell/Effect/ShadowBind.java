package fr.factionbedrock.aerialhell.Effect;

import fr.factionbedrock.aerialhell.Client.Packet.AerialHellData;
import fr.factionbedrock.aerialhell.Registry.AerialHellMobEffects;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.Set;

public class ShadowBind extends AerialHellEffect
{
    public ShadowBind(StatusEffectCategory category, int liquidColor) {super(category, liquidColor);}

    @Override public boolean applyUpdateEffect(LivingEntity livingEntity, int amplifier)
    {
        StatusEffectInstance instance = livingEntity.getStatusEffect(AerialHellMobEffects.SHADOW_BIND);
        if (instance != null)
        {
            if (amplifier > 0)
            {
                livingEntity.removeStatusEffect(AerialHellMobEffects.SHADOW_BIND);
                livingEntity.addStatusEffect(new StatusEffectInstance(AerialHellMobEffects.SHADOW_BIND, instance.getDuration(), 0));
                if (livingEntity instanceof ServerPlayerEntity serverPlayer) {ServerPlayNetworking.send(serverPlayer, new AerialHellData("reloadTextures", 0));}
            }
            if (instance.getDuration() < 2)
            {
                livingEntity.removeStatusEffect(AerialHellMobEffects.SHADOW_BIND);
                if (livingEntity instanceof ServerPlayerEntity serverPlayer) {ServerPlayNetworking.send(serverPlayer, new AerialHellData("reloadTextures", 0));}
            }
        }

        // Old dynamic chunk update around the player. Caused too much client lag (?).
    	//if (livingEntity instanceof ServerPlayer serverPlayer && livingEntity.level() instanceof ServerLevel serverLevel)
        //{
        //    EntityHelper.refreshChunkColors(serverPlayer, serverLevel, 100);
        //}
        return true;
    }

    /*@Override public void fillEffectCures(Set<EffectCure> cures, MobEffectInstance effectInstance) {}TODO*/
}