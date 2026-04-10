package fr.factionbedrock.aerialhell.Util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import net.minecraft.world.World;

public class DebugHelper
{
    public static void sendDebugMessage(World world, String message)
    {
        long time = world.getTime();
        String chatMessage = "[DEBUG - "+(world.isClient() ? "Client" : "Server")+" side - "+time+"] " + message;

        if (world.isClient())
        {
            MinecraftClient.getInstance().player.sendMessage(Text.literal(chatMessage), false);
        }
        else if (world.getServer() != null)
        {
            world.getServer().getPlayerManager().broadcast(Text.literal(chatMessage), false);
        }
    }
}
