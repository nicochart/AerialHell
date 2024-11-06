package fr.factionbedrock.aerialhell.Client.Packet;

import net.neoforged.neoforge.network.handling.IPayloadContext;

public class ServerPayloadHandler
{
    public static void handleDataOnMain(final AerialHellData data, final IPayloadContext context)
    {
        // Do something with the data, on the main thread
    }
}