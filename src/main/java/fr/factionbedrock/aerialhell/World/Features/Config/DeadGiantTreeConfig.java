package fr.factionbedrock.aerialhell.World.Features.Config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

public record DeadGiantTreeConfig(int trunkMaxHorizontalOffset, int trunkMinVerticalOffset, int trunkMaxVerticalOffset, float randomChanceToNotPlaceBlock, BlockStateProvider trunkProvider) implements FeatureConfig
{
    public static final Codec<DeadGiantTreeConfig> CODEC = RecordCodecBuilder.create((builder) -> {
        return builder.group(Codecs.POSITIVE_INT.fieldOf("trunk_max_horizontal_offset").forGetter(DeadGiantTreeConfig::trunkMaxHorizontalOffset), Codecs.POSITIVE_INT.fieldOf("trunk_min_vertical_offset").forGetter(DeadGiantTreeConfig::trunkMinVerticalOffset), Codecs.POSITIVE_INT.fieldOf("trunk_max_vertical_offset").forGetter(DeadGiantTreeConfig::trunkMaxVerticalOffset), Codecs.POSITIVE_FLOAT.fieldOf("chance_to_not_place_block").forGetter(DeadGiantTreeConfig::randomChanceToNotPlaceBlock), BlockStateProvider.TYPE_CODEC.fieldOf("trunk_provider").forGetter(DeadGiantTreeConfig::trunkProvider)).apply(builder, DeadGiantTreeConfig::new);
    });
}