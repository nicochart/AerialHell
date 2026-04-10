package fr.factionbedrock.aerialhell.Util;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;

public class DebugHelper
{
    public static void sendDebugMessage(Level world, String message)
    {
        long time = world.getGameTime();
        String chatMessage = "[DEBUG - "+(world.isClientSide() ? "Client" : "Server")+" side - "+time+"] " + message;

        if (world.isClientSide())
        {
            Minecraft.getInstance().player.displayClientMessage(Component.literal(chatMessage), false);
        }
        else if (world.getServer() != null)
        {
            world.getServer().getPlayerList().broadcastSystemMessage(Component.literal(chatMessage), false);
        }
    }
}
