package fr.factionbedrock.aerialhell.World.Features.Config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public record NaturalFieldConfig(int maxVerticalOffset, float baseChance, float acceptOffsetChance, BlockStateProvider cropStateProvider) implements FeatureConfiguration
{
    public static final Codec<NaturalFieldConfig> CODEC = RecordCodecBuilder.create((builder) -> {
        return builder.group(ExtraCodecs.POSITIVE_INT.fieldOf("max_vertical_offset").forGetter(NaturalFieldConfig::maxVerticalOffset), ExtraCodecs.POSITIVE_FLOAT.fieldOf("base_chance").forGetter(NaturalFieldConfig::baseChance), ExtraCodecs.POSITIVE_FLOAT.fieldOf("accept_offset_chance").forGetter(NaturalFieldConfig::acceptOffsetChance), BlockStateProvider.CODEC.fieldOf("crop_state_provider").forGetter(NaturalFieldConfig::cropStateProvider)).apply(builder, NaturalFieldConfig::new);
    });
}