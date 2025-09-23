package fr.factionbedrock.aerialhell.World.Features.Config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

public record CrystalBlobConfig(BlockStateProvider crystalStateProvider) implements FeatureConfig
{
    public static final Codec<CrystalBlobConfig> CODEC = RecordCodecBuilder.create((builder) -> {
        return builder.group(BlockStateProvider.TYPE_CODEC.fieldOf("crystal_state_provider").forGetter(CrystalBlobConfig::crystalStateProvider)).apply(builder, CrystalBlobConfig::new);
    });
}
