package fr.factionbedrock.aerialhell.Client.Packet;

import net.minecraft.network.FriendlyByteBuf;

public record AerialHellData(String name, int age)
{
    public void encode(FriendlyByteBuf buffer)
    {
        buffer.writeUtf(this.name);
        buffer.writeVarInt(this.age);
    }

    public static AerialHellData decode(FriendlyByteBuf buffer)
    {
        return new AerialHellData(buffer.readUtf(), buffer.readVarInt());
    }
}