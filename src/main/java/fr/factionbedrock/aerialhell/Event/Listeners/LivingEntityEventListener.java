package fr.factionbedrock.aerialhell.Event.Listeners;

import fr.factionbedrock.aerialhell.Registry.AerialHellMobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class LivingEntityEventListener
{
    @SubscribeEvent
    public static void onLivingJumpEvent(LivingEvent.LivingJumpEvent event)
    {
    	LivingEntity livingEntity = event.getEntityLiving();
    	if (livingEntity.hasEffect(AerialHellMobEffects.HEAD_IN_THE_CLOUDS.get()))
    	{
    		int bonus = livingEntity.getEffect(AerialHellMobEffects.HEAD_IN_THE_CLOUDS.get()).getAmplifier() + 1;
    		livingEntity.heal(0.5F * bonus);
    		Vec3 baseMotion = livingEntity.getDeltaMovement();
    		livingEntity.setDeltaMovement(baseMotion.x, baseMotion.y + (0.4 * bonus), baseMotion.z);
    	}
    }
}