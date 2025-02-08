package fr.factionbedrock.aerialhell.Event.Listeners;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.LevelAccessor;
import net.neoforged.neoforge.event.level.SleepFinishedTimeEvent;

public class LivingEntityEventListener
{
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