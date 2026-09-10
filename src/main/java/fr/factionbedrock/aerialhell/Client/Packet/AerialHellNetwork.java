package fr.factionbedrock.aerialhell.Client.Packet;

import fr.factionbedrock.aerialhell.AerialHell;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class AerialHellNetwork
{
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(new ResourceLocation(AerialHell.MODID, "main"), () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);

    private static int packetId = 0;

    public static void register()
    {
        CHANNEL.messageBuilder(AerialHellData.class, packetId++, NetworkDirection.PLAY_TO_CLIENT)
                .encoder(AerialHellData::encode)
                .decoder(AerialHellData::decode)
                .consumerNetworkThread(ClientPayloadHandler::handleDataOnMain)
                .add();
    }
}
