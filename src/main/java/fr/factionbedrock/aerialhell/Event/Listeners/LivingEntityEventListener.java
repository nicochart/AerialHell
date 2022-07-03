package fr.factionbedrock.aerialhell.Event.Listeners;

import fr.factionbedrock.aerialhell.Registry.AerialHellPotionEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.vector.Vector3d;
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
    	if (livingEntity.isPotionActive(AerialHellPotionEffects.HEAD_IN_THE_CLOUDS.get()))
    	{
    		int bonus = livingEntity.getActivePotionEffect(AerialHellPotionEffects.HEAD_IN_THE_CLOUDS.get()).getAmplifier() + 1;
    		livingEntity.heal(0.5F * bonus);
    		Vector3d baseMotion = livingEntity.getMotion();
    		livingEntity.setMotion(baseMotion.getX(), baseMotion.getY() + (0.4 * bonus), baseMotion.getZ());
    	}
    }
}