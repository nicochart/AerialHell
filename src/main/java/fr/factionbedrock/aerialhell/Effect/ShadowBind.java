package fr.factionbedrock.aerialhell.Effect;

import fr.factionbedrock.aerialhell.Client.Packet.AerialHellData;
import fr.factionbedrock.aerialhell.Config.LoadedConfigParams;
import fr.factionbedrock.aerialhell.Registry.AerialHellMobEffects;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import java.util.Set;

public class ShadowBind extends AerialHellEffect
{
    public ShadowBind(MobEffectCategory category, int liquidColor) {super(category, liquidColor);}

    @Override public boolean applyEffectTick(ServerLevel serverWorld, LivingEntity livingEntity, int amplifier)
    {
        MobEffectInstance instance = livingEntity.getEffect(AerialHellMobEffects.SHADOW_BIND);
        if (instance != null)
        {
            if (amplifier > 0)
            {
                livingEntity.removeEffect(AerialHellMobEffects.SHADOW_BIND);
                livingEntity.addEffect(new MobEffectInstance(AerialHellMobEffects.SHADOW_BIND, instance.getDuration(), 0));
                if (livingEntity instanceof ServerPlayer serverPlayer && LoadedConfigParams.ENABLE_SHADOW_BIND_RELOAD_TEXTURE) {ServerPlayNetworking.send(serverPlayer, new AerialHellData("reloadTextures", 0));}
            }
            if (instance.getDuration() < 2)
            {
                livingEntity.removeEffect(AerialHellMobEffects.SHADOW_BIND);
                if (livingEntity instanceof ServerPlayer serverPlayer && LoadedConfigParams.ENABLE_SHADOW_BIND_RELOAD_TEXTURE) {ServerPlayNetworking.send(serverPlayer, new AerialHellData("reloadTextures", 0));}
            }
        }

        // Old dynamic chunk update around the player. Caused too much client lag (?).
    	//if (livingEntity instanceof ServerPlayer serverPlayer && livingEntity.level() instanceof ServerLevel serverLevel)
        //{
        //    EntityHelper.refreshChunkColors(serverPlayer, serverLevel, 100);
        //}
        return true;
    }

    /*@Override public void fillEffectCures(Set<EffectCure> cures, MobEffectInstance effectInstance) {}TODO milk bukket now removes effect. Mixin finishUsing ?*/
}