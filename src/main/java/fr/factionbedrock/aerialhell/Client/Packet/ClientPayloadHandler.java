package fr.factionbedrock.aerialhell.Client.Packet;

import fr.factionbedrock.aerialhell.Config.LoadedConfigParams;
import net.minecraft.client.Minecraft;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ClientPayloadHandler
{
    public static void handleDataOnMain(AerialHellData msg, Supplier<NetworkEvent.Context> contextSupplier)
    {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() ->
        {
            if (LoadedConfigParams.ENABLE_SHADOW_BIND_RELOAD_TEXTURE && LoadedConfigParams.ENABLE_SHADOW_BIND_TEXTURE_SHIFT) //not necessary to reload textures if shifting is disabled
            {
                Minecraft.getInstance().reloadResourcePacks();
            }
        });
        context.setPacketHandled(true);
    }
}