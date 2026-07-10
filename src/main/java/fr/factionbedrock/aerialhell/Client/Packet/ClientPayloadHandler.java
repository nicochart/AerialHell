package fr.factionbedrock.aerialhell.Client.Packet;

import fr.factionbedrock.aerialhell.Client.Event.Listeners.RenderRegistrationListener;
import fr.factionbedrock.aerialhell.Config.LoadedConfigParams;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.Minecraft;

//https://wiki.fabricmc.net/tutorial:networking
public class ClientPayloadHandler
{
    public static void handleDataOnMain()
    {
        ClientPlayNetworking.registerGlobalReceiver(AerialHellData.ID, (payload, context) ->
        {
            if (payload.name().equals("reloadTextures"))
            {
                if (LoadedConfigParams.ENABLE_SHADOW_BIND_RELOAD_TEXTURE && LoadedConfigParams.ENABLE_SHADOW_BIND_TEXTURE_SHIFT) //not necessary to reload textures if shifting is disabled
                {
                    RenderRegistrationListener.initialiseToBakeList(); //reinit ToBake BlockState list (shifting models), because models are reloaded with textures
                    Minecraft.getInstance().reloadResourcePacks();
                }
            }
        });
    }
}