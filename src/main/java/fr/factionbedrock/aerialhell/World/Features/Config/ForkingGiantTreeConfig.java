package fr.factionbedrock.aerialhell.World.Features.Config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

public record ForkingGiantTreeConfig(int trunkMaxHorizontalOffset, int trunkMinVerticalOffset, int trunkMaxVerticalOffset, BlockStateProvider trunkProvider, BlockStateProvider foliageProvider) implements FeatureConfig
{
    public static final Codec<ForkingGiantTreeConfig> CODEC = RecordCodecBuilder.create((builder) -> {
        return builder.group(Codecs.POSITIVE_INT.fieldOf("trunk_max_horizontal_offset").forGetter(ForkingGiantTreeConfig::trunkMaxHorizontalOffset), Codecs.POSITIVE_INT.fieldOf("trunk_min_vertical_offset").forGetter(ForkingGiantTreeConfig::trunkMinVerticalOffset), Codecs.POSITIVE_INT.fieldOf("trunk_max_vertical_offset").forGetter(ForkingGiantTreeConfig::trunkMaxVerticalOffset), BlockStateProvider.TYPE_CODEC.fieldOf("trunk_provider").forGetter(ForkingGiantTreeConfig::trunkProvider), BlockStateProvider.TYPE_CODEC.fieldOf("foliage_provider").forGetter(ForkingGiantTreeConfig::foliageProvider)).apply(builder, ForkingGiantTreeConfig::new);
    });
}