package fr.factionbedrock.aerialhell.Util;

import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;

public class DebugHelper
{
    public static void sendDebugMessage(Level level, String message)
    {
        if (!level.isClientSide() && level.getServer() != null)
        {
            level.getServer().getPlayerList().broadcastSystemMessage(Component.literal("[DEBUG] " + message), false);
        }
    }
}
