package fr.factionbedrock.aerialhell.World.Features.Config;

import com.mojang.serialization.MapCodec;

//replacement of vanilla NoneFeatureConfiguration, removed in 26.3
public record NoneFeatureConfiguration()
{
    public static final NoneFeatureConfiguration INSTANCE = new NoneFeatureConfiguration();
    public static final MapCodec<NoneFeatureConfiguration> CODEC = MapCodec.unit(INSTANCE);
}
