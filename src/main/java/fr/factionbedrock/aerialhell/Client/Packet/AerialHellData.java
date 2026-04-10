package fr.factionbedrock.aerialhell.Client.Packet;

import fr.factionbedrock.aerialhell.AerialHell;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

public record AerialHellData(String name, int age) implements CustomPacketPayload
{
    public static final CustomPacketPayload.Type<AerialHellData> ID = new CustomPacketPayload.Type<>(AerialHell.id("data"));

    public static final StreamCodec<RegistryFriendlyByteBuf, AerialHellData> CODEC = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8, AerialHellData::name,
            ByteBufCodecs.VAR_INT, AerialHellData::age,
            AerialHellData::new);

    @Override public Type<? extends CustomPacketPayload> type() {return ID;}
}