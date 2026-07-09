package fr.factionbedrock.aerialhell.Event.Listeners;

import fr.factionbedrock.aerialhell.Registry.AerialHellMobEffects;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.entity.living.LivingEvent;
import net.neoforged.neoforge.event.level.SleepFinishedTimeEvent;

public class LivingEntityEventListener
{
    public static void onLivingJumpEvent(LivingEvent.LivingJumpEvent event)
    {
    	LivingEntity livingEntity = event.getEntity();
    	if (livingEntity.hasEffect(AerialHellMobEffects.HEAD_IN_THE_CLOUDS.getDelegate()))
    	{
    		float multiplier = 1.0F + 0.25F * (livingEntity.getEffect(AerialHellMobEffects.HEAD_IN_THE_CLOUDS.getDelegate()).getAmplifier() + 1);
    		Vec3 baseMotion = livingEntity.getDeltaMovement();
    		livingEntity.setDeltaMovement(baseMotion.x, baseMotion.y + 0.2F * multiplier, baseMotion.z);
    	}
    }

	public static void onSleepFinishEvent(SleepFinishedTimeEvent event)
	{
		LevelAccessor level = event.getLevel();
		if (level instanceof ServerLevel)
		{
			MinecraftServer server = ((ServerLevel) level).getServer();
			for (ServerLevel serverLevel : server.getAllLevels())
			{
				long TICK_PER_DAY = 24000L;
				long time = event.getNewTime();
				long timePlus1Day = time + TICK_PER_DAY;
				long timeAddition = timePlus1Day - timePlus1Day % TICK_PER_DAY;
				long newTime = time + timeAddition;
				serverLevel.setDayTime(newTime);
			}
		}
	}
}