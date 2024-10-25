package fr.factionbedrock.aerialhell.Client.Packet;

import net.minecraft.client.Minecraft;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class ClientPayloadHandler
{
    public static void handleDataOnMain(final AerialHellData data, final IPayloadContext context)
    {
        // Do something with the data, on the main thread
        if (data.name().equals("reloadTextures"))
        {
            Minecraft.getInstance().reloadResourcePacks();
        }
    }
}