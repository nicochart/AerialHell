package fr.factionbedrock.aerialhell.World.Features.Config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public record GiantPineTreeConfig(int trunkMaxHorizontalOffset, int trunkMinVerticalOffset, int trunkMaxVerticalOffset, int branchQuantity, int verticalBranchSeparation, BlockStateProvider trunkProvider, BlockStateProvider foliageProvider) implements FeatureConfiguration
{
    public static final Codec<GiantPineTreeConfig> CODEC = RecordCodecBuilder.create((builder) -> {
        return builder.group(ExtraCodecs.POSITIVE_INT.fieldOf("trunk_max_horizontal_offset").forGetter(GiantPineTreeConfig::trunkMaxHorizontalOffset), ExtraCodecs.POSITIVE_INT.fieldOf("trunk_min_vertical_offset").forGetter(GiantPineTreeConfig::trunkMinVerticalOffset), ExtraCodecs.POSITIVE_INT.fieldOf("trunk_max_vertical_offset").forGetter(GiantPineTreeConfig::trunkMaxVerticalOffset), ExtraCodecs.POSITIVE_INT.fieldOf("branch_quantity").forGetter(GiantPineTreeConfig::branchQuantity), ExtraCodecs.POSITIVE_INT.fieldOf("vertical_branch_separation").forGetter(GiantPineTreeConfig::verticalBranchSeparation), BlockStateProvider.CODEC.fieldOf("trunk_provider").forGetter(GiantPineTreeConfig::trunkProvider), BlockStateProvider.CODEC.fieldOf("foliage_provider").forGetter(GiantPineTreeConfig::foliageProvider)).apply(builder, GiantPineTreeConfig::new);
    });
}