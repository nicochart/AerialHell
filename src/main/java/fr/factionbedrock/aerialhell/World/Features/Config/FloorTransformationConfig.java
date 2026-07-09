package fr.factionbedrock.aerialhell.World.Features.Config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public record FloorTransformationConfig(int ellipsisBaseSize, int ellipsisRandomSpreading, float innerChance, float outerChance, BlockStateProvider innerStateProvider, BlockStateProvider outerStateProvider, String needsRoof) implements FeatureConfiguration
{
    public static final Codec<FloorTransformationConfig> CODEC = RecordCodecBuilder.create((builder) -> {
        return builder.group(ExtraCodecs.POSITIVE_INT.fieldOf("size").forGetter(FloorTransformationConfig::ellipsisBaseSize), ExtraCodecs.POSITIVE_INT.fieldOf("random_spread").forGetter(FloorTransformationConfig::ellipsisRandomSpreading), ExtraCodecs.POSITIVE_FLOAT.fieldOf("inner_chance").forGetter(FloorTransformationConfig::innerChance), ExtraCodecs.POSITIVE_FLOAT.fieldOf("outer_chance").forGetter(FloorTransformationConfig::outerChance), BlockStateProvider.CODEC.fieldOf("inner_state_provider").forGetter(FloorTransformationConfig::innerStateProvider), BlockStateProvider.CODEC.fieldOf("outer_state_provider").forGetter(FloorTransformationConfig::outerStateProvider), ExtraCodecs.NON_EMPTY_STRING.fieldOf("needs_roof").forGetter(FloorTransformationConfig::needsRoof)).apply(builder, FloorTransformationConfig::new);
    });
}