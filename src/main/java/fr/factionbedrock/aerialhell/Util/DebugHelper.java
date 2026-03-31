package fr.factionbedrock.aerialhell.Util;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;

public class DebugHelper
{
    public static void sendDebugMessage(Level level, String message)
    {
        long time = level.getGameTime();
        String chatMessage = "[DEBUG - "+(level.isClientSide() ? "Client" : "Server")+" side - "+time+"] " + message;

        if (level.isClientSide())
        {
            Minecraft.getInstance().player.displayClientMessage(Component.literal(chatMessage), false);
        }
        else if (level.getServer() != null)
        {
            level.getServer().getPlayerList().broadcastSystemMessage(Component.literal(chatMessage), false);
        }
    }
}
