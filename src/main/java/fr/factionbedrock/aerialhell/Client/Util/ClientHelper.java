package fr.factionbedrock.aerialhell.Client.Util;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;

public class ClientHelper
{
    public static Player getLocalPlayer() {return Minecraft.getInstance().player;}
}
