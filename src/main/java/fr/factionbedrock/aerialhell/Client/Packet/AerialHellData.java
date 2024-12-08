package fr.factionbedrock.aerialhell.Client.Packet;

import fr.factionbedrock.aerialhell.AerialHell;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;

public record AerialHellData(String name, int age) implements CustomPayload
{
    public static final CustomPayload.Id<AerialHellData> ID = new CustomPayload.Id<>(AerialHell.id("data"));

    public static final PacketCodec<RegistryByteBuf, AerialHellData> CODEC = PacketCodec.tuple(
            PacketCodecs.STRING, AerialHellData::name,
            PacketCodecs.VAR_INT, AerialHellData::age,
            AerialHellData::new);

    @Override public Id<? extends CustomPayload> getId() {return ID;}
}