package fr.factionbedrock.aerialhell.Client.Packet;

import fr.factionbedrock.aerialhell.Client.Event.Listeners.RenderRegistrationListener;
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
                RenderRegistrationListener.initialiseToBakeList(); //reinit ToBake BlockState list (shifting models), because models are reloaded with textures
                MinecraftClient.getInstance().reloadResources();
            }
        });
    }
}