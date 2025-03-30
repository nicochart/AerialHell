package fr.factionbedrock.aerialhell.World.Features.Config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

public record NaturalFieldConfig(int maxVerticalOffset, float baseChance, float acceptOffsetChance, BlockStateProvider cropStateProvider) implements FeatureConfig
{
    public static final Codec<NaturalFieldConfig> CODEC = RecordCodecBuilder.create((builder) -> {
        return builder.group(Codecs.POSITIVE_INT.fieldOf("max_vertical_offset").forGetter(NaturalFieldConfig::maxVerticalOffset), Codecs.POSITIVE_FLOAT.fieldOf("base_chance").forGetter(NaturalFieldConfig::baseChance), Codecs.POSITIVE_FLOAT.fieldOf("accept_offset_chance").forGetter(NaturalFieldConfig::acceptOffsetChance), BlockStateProvider.TYPE_CODEC.fieldOf("crop_state_provider").forGetter(NaturalFieldConfig::cropStateProvider)).apply(builder, NaturalFieldConfig::new);
    });
}