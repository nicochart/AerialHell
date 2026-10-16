package fr.factionbedrock.aerialhell.World.Features.Config;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public record NaturalFieldConfig(int maxVerticalOffset, float baseChance, float acceptOffsetChance, BlockStateProvider cropStateProvider)
{
    public static final MapCodec<NaturalFieldConfig> CODEC = RecordCodecBuilder.mapCodec((builder) -> {
        return builder.group(ExtraCodecs.POSITIVE_INT.fieldOf("max_vertical_offset").forGetter(NaturalFieldConfig::maxVerticalOffset), ExtraCodecs.POSITIVE_FLOAT.fieldOf("base_chance").forGetter(NaturalFieldConfig::baseChance), ExtraCodecs.POSITIVE_FLOAT.fieldOf("accept_offset_chance").forGetter(NaturalFieldConfig::acceptOffsetChance), BlockStateProvider.DIRECT_CODEC.fieldOf("crop_state_provider").forGetter(NaturalFieldConfig::cropStateProvider)).apply(builder, NaturalFieldConfig::new);
    });
}