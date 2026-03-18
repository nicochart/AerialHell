package fr.factionbedrock.aerialhell.Util;

import net.minecraft.text.Text;
import net.minecraft.world.World;

public class DebugHelper
{
    public static void sendDebugMessage(World world, String message)
    {
        if (!world.isClient() && world.getServer() != null)
        {
            world.getServer().getPlayerManager().broadcast(Text.literal("[DEBUG] " + message), false);
        }
    }
}
