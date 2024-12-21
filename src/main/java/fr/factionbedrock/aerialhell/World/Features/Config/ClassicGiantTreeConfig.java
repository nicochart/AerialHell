package fr.factionbedrock.aerialhell.World.Features.Config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

public record ClassicGiantTreeConfig(int trunkMaxHorizontalOffset, int trunkMinVerticalOffset, int trunkMaxVerticalOffset, float xzFoliageSizeFactor, float yFoliageSizeFactor, BlockStateProvider trunkProvider, BlockStateProvider foliageProvider) implements FeatureConfig
{
    public static final Codec<ClassicGiantTreeConfig> CODEC = RecordCodecBuilder.create((builder) -> {
        return builder.group(Codecs.POSITIVE_INT.fieldOf("trunk_max_horizontal_offset").forGetter(ClassicGiantTreeConfig::trunkMaxHorizontalOffset), Codecs.POSITIVE_INT.fieldOf("trunk_min_vertical_offset").forGetter(ClassicGiantTreeConfig::trunkMinVerticalOffset), Codecs.POSITIVE_INT.fieldOf("trunk_max_vertical_offset").forGetter(ClassicGiantTreeConfig::trunkMaxVerticalOffset), Codecs.POSITIVE_FLOAT.fieldOf("xz_foliage_size_factor").forGetter(ClassicGiantTreeConfig::xzFoliageSizeFactor), Codecs.POSITIVE_FLOAT.fieldOf("y_foliage_size_factor").forGetter(ClassicGiantTreeConfig::yFoliageSizeFactor), BlockStateProvider.TYPE_CODEC.fieldOf("trunk_provider").forGetter(ClassicGiantTreeConfig::trunkProvider), BlockStateProvider.TYPE_CODEC.fieldOf("foliage_provider").forGetter(ClassicGiantTreeConfig::foliageProvider)).apply(builder, ClassicGiantTreeConfig::new);
    });
}