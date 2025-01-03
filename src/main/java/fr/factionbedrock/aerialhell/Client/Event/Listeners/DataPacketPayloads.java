package fr.factionbedrock.aerialhell.Client.Event.Listeners;

import fr.factionbedrock.aerialhell.Client.Packet.AerialHellData;
import fr.factionbedrock.aerialhell.Client.Packet.ClientPayloadHandler;
import fr.factionbedrock.aerialhell.Client.Packet.ServerPayloadHandler;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.DirectionalPayloadHandler;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

//https://docs.neoforged.net/docs/networking/payload/
public class DataPacketPayloads
{
    public static void register(final RegisterPayloadHandlersEvent event)
    {
        final PayloadRegistrar registrar = event.registrar("1");
        registrar.playBidirectional(AerialHellData.TYPE, AerialHellData.STREAM_CODEC, new DirectionalPayloadHandler<>(ClientPayloadHandler::handleDataOnMain, ServerPayloadHandler::handleDataOnMain));
    }
}
