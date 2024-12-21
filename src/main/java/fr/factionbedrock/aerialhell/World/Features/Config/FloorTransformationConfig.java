package fr.factionbedrock.aerialhell.World.Features.Config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

public record FloorTransformationConfig(int ellipsisBaseSize, int ellipsisRandomSpreading, float innerChance, float outerChance, BlockStateProvider innerStateProvider, BlockStateProvider outerStateProvider, String needsRoof) implements FeatureConfig
{
    public static final Codec<FloorTransformationConfig> CODEC = RecordCodecBuilder.create((builder) -> {
        return builder.group(Codecs.POSITIVE_INT.fieldOf("size").forGetter(FloorTransformationConfig::ellipsisBaseSize), Codecs.POSITIVE_INT.fieldOf("random_spread").forGetter(FloorTransformationConfig::ellipsisRandomSpreading), Codecs.POSITIVE_FLOAT.fieldOf("inner_chance").forGetter(FloorTransformationConfig::innerChance), Codecs.POSITIVE_FLOAT.fieldOf("outer_chance").forGetter(FloorTransformationConfig::outerChance), BlockStateProvider.TYPE_CODEC.fieldOf("inner_state_provider").forGetter(FloorTransformationConfig::innerStateProvider), BlockStateProvider.TYPE_CODEC.fieldOf("outer_state_provider").forGetter(FloorTransformationConfig::outerStateProvider), Codecs.NON_EMPTY_STRING.fieldOf("needs_roof").forGetter(FloorTransformationConfig::needsRoof)).apply(builder, FloorTransformationConfig::new);
    });
}