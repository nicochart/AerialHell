package fr.factionbedrock.aerialhell.Client.Packet;

import fr.factionbedrock.aerialhell.Config.LoadedConfigParams;
import net.minecraft.client.Minecraft;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class ClientPayloadHandler
{
    public static void handleDataOnMain(final AerialHellData data, final IPayloadContext context)
    {
        // Do something with the data, on the main thread
        if (data.name().equals("reloadTextures"))
        {
            if (LoadedConfigParams.ENABLE_SHADOW_BIND_RELOAD_TEXTURE && LoadedConfigParams.ENABLE_SHADOW_BIND_TEXTURE_SHIFT) //not necessary to reload textures if shifting is disabled
            {
                Minecraft.getInstance().reloadResourcePacks();
            }
        }
    }
}