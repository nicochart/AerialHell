package fr.factionbedrock.aerialhell.World.Features.Config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public record ForkingGiantTreeConfig(int trunkMaxHorizontalOffset, int trunkMinVerticalOffset, int trunkMaxVerticalOffset, BlockStateProvider trunkProvider, BlockStateProvider foliageProvider) implements FeatureConfiguration
{
    public static final Codec<ForkingGiantTreeConfig> CODEC = RecordCodecBuilder.create((builder) -> {
        return builder.group(ExtraCodecs.POSITIVE_INT.fieldOf("trunk_max_horizontal_offset").forGetter(ForkingGiantTreeConfig::trunkMaxHorizontalOffset), ExtraCodecs.POSITIVE_INT.fieldOf("trunk_min_vertical_offset").forGetter(ForkingGiantTreeConfig::trunkMinVerticalOffset), ExtraCodecs.POSITIVE_INT.fieldOf("trunk_max_vertical_offset").forGetter(ForkingGiantTreeConfig::trunkMaxVerticalOffset), BlockStateProvider.CODEC.fieldOf("trunk_provider").forGetter(ForkingGiantTreeConfig::trunkProvider), BlockStateProvider.CODEC.fieldOf("foliage_provider").forGetter(ForkingGiantTreeConfig::foliageProvider)).apply(builder, ForkingGiantTreeConfig::new);
    });
}