package fr.factionbedrock.aerialhell.Client.Packet;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.MinecraftClient;

//https://wiki.fabricmc.net/tutorial:networking
public class ClientPayloadHandler
{
    public static void handleDataOnMain()
    {
        ClientPlayNetworking.registerGlobalReceiver(AerialHellData.ID, (payload, context) ->
        {
            if (payload.name().equals("reloadTextures"))
            {
                MinecraftClient.getInstance().reloadResources();
            }
        });
    }
}