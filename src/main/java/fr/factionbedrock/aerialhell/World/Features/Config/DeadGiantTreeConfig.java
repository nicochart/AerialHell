package fr.factionbedrock.aerialhell.World.Features.Config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public record DeadGiantTreeConfig(int trunkMaxHorizontalOffset, int trunkMinVerticalOffset, int trunkMaxVerticalOffset, float randomChanceToNotPlaceBlock, BlockStateProvider trunkProvider) implements FeatureConfiguration
{
    public static final Codec<DeadGiantTreeConfig> CODEC = RecordCodecBuilder.create((builder) -> {
        return builder.group(ExtraCodecs.POSITIVE_INT.fieldOf("trunk_max_horizontal_offset").forGetter(DeadGiantTreeConfig::trunkMaxHorizontalOffset), ExtraCodecs.POSITIVE_INT.fieldOf("trunk_min_vertical_offset").forGetter(DeadGiantTreeConfig::trunkMinVerticalOffset), ExtraCodecs.POSITIVE_INT.fieldOf("trunk_max_vertical_offset").forGetter(DeadGiantTreeConfig::trunkMaxVerticalOffset), ExtraCodecs.POSITIVE_FLOAT.fieldOf("chance_to_not_place_block").forGetter(DeadGiantTreeConfig::randomChanceToNotPlaceBlock), BlockStateProvider.CODEC.fieldOf("trunk_provider").forGetter(DeadGiantTreeConfig::trunkProvider)).apply(builder, DeadGiantTreeConfig::new);
    });
}