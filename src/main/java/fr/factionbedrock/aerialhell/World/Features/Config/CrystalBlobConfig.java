package fr.factionbedrock.aerialhell.World.Features.Config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public record CrystalBlobConfig(BlockStateProvider crystalStateProvider) implements FeatureConfiguration
{
    public static final Codec<CrystalBlobConfig> CODEC = RecordCodecBuilder.create((builder) -> {
        return builder.group(BlockStateProvider.CODEC.fieldOf("crystal_state_provider").forGetter(CrystalBlobConfig::crystalStateProvider)).apply(builder, CrystalBlobConfig::new);
    });
}