package fr.factionbedrock.aerialhell.World.Features.Config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

public record MushroomCapsColumnConfig(int stemMaxHorizontalOffset, int stemMinVerticalOffset, int stemMaxVerticalOffset, int capMeanSize, int verticalCapSeparation, BlockStateProvider stemProvider, BlockStateProvider capProvider, BlockStateProvider lightProvider) implements FeatureConfig
{
    public static final Codec<MushroomCapsColumnConfig> CODEC = RecordCodecBuilder.create((builder) -> {
        return builder.group(Codecs.POSITIVE_INT.fieldOf("stem_max_horizontal_offset").forGetter(MushroomCapsColumnConfig::stemMaxHorizontalOffset), Codecs.POSITIVE_INT.fieldOf("stem_min_vertical_offset").forGetter(MushroomCapsColumnConfig::stemMinVerticalOffset), Codecs.POSITIVE_INT.fieldOf("stem_max_vertical_offset").forGetter(MushroomCapsColumnConfig::stemMaxVerticalOffset), Codecs.POSITIVE_INT.fieldOf("cap_mean_size").forGetter(MushroomCapsColumnConfig::capMeanSize), Codecs.POSITIVE_INT.fieldOf("vertical_cap_separation").forGetter(MushroomCapsColumnConfig::verticalCapSeparation), BlockStateProvider.TYPE_CODEC.fieldOf("stem_provider").forGetter(MushroomCapsColumnConfig::stemProvider), BlockStateProvider.TYPE_CODEC.fieldOf("cap_provider").forGetter(MushroomCapsColumnConfig::capProvider), BlockStateProvider.TYPE_CODEC.fieldOf("light_provider").forGetter(MushroomCapsColumnConfig::lightProvider)).apply(builder, MushroomCapsColumnConfig::new);
    });
}