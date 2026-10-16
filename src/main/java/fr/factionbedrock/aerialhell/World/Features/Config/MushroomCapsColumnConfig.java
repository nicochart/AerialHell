package fr.factionbedrock.aerialhell.World.Features.Config;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public record MushroomCapsColumnConfig(int stemMaxHorizontalOffset, int stemMinVerticalOffset, int stemMaxVerticalOffset, int capMeanSize, int verticalCapSeparation, BlockStateProvider stemProvider, BlockStateProvider capProvider, BlockStateProvider lightProvider)
{
    public static final MapCodec<MushroomCapsColumnConfig> CODEC = RecordCodecBuilder.mapCodec((builder) -> {
        return builder.group(ExtraCodecs.POSITIVE_INT.fieldOf("stem_max_horizontal_offset").forGetter(MushroomCapsColumnConfig::stemMaxHorizontalOffset), ExtraCodecs.POSITIVE_INT.fieldOf("stem_min_vertical_offset").forGetter(MushroomCapsColumnConfig::stemMinVerticalOffset), ExtraCodecs.POSITIVE_INT.fieldOf("stem_max_vertical_offset").forGetter(MushroomCapsColumnConfig::stemMaxVerticalOffset), ExtraCodecs.POSITIVE_INT.fieldOf("cap_mean_size").forGetter(MushroomCapsColumnConfig::capMeanSize), ExtraCodecs.POSITIVE_INT.fieldOf("vertical_cap_separation").forGetter(MushroomCapsColumnConfig::verticalCapSeparation), BlockStateProvider.DIRECT_CODEC.fieldOf("stem_provider").forGetter(MushroomCapsColumnConfig::stemProvider), BlockStateProvider.DIRECT_CODEC.fieldOf("cap_provider").forGetter(MushroomCapsColumnConfig::capProvider), BlockStateProvider.DIRECT_CODEC.fieldOf("light_provider").forGetter(MushroomCapsColumnConfig::lightProvider)).apply(builder, MushroomCapsColumnConfig::new);
    });
}