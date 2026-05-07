package fr.factionbedrock.aerialhell.Util;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;

public class DebugHelper
{
    public static void sendDebugMessage(Level level, String message, boolean includeDefaultInfo)
    {
        long time = level.getGameTime();
        String chatMessage = includeDefaultInfo ? "[DEBUG - "+(level.isClientSide() ? "Client" : "Server")+" side - "+time+"] " + message : "[DEBUG] " + message;

        if (level.isClientSide())
        {
            Minecraft.getInstance().player.sendSystemMessage(Component.literal(chatMessage));
        }
        else if (level.getServer() != null)
        {
            level.getServer().getPlayerList().broadcastSystemMessage(Component.literal(chatMessage), false);
        }
    }
}
