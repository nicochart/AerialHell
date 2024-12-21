package fr.factionbedrock.aerialhell.World.Features.Config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

public record GiantPineTreeConfig(int trunkMaxHorizontalOffset, int trunkMinVerticalOffset, int trunkMaxVerticalOffset, int branchQuantity, int verticalBranchSeparation, BlockStateProvider trunkProvider, BlockStateProvider foliageProvider) implements FeatureConfig
{
    public static final Codec<GiantPineTreeConfig> CODEC = RecordCodecBuilder.create((builder) -> {
        return builder.group(Codecs.POSITIVE_INT.fieldOf("trunk_max_horizontal_offset").forGetter(GiantPineTreeConfig::trunkMaxHorizontalOffset), Codecs.POSITIVE_INT.fieldOf("trunk_min_vertical_offset").forGetter(GiantPineTreeConfig::trunkMinVerticalOffset), Codecs.POSITIVE_INT.fieldOf("trunk_max_vertical_offset").forGetter(GiantPineTreeConfig::trunkMaxVerticalOffset), Codecs.POSITIVE_INT.fieldOf("branch_quantity").forGetter(GiantPineTreeConfig::branchQuantity), Codecs.POSITIVE_INT.fieldOf("vertical_branch_separation").forGetter(GiantPineTreeConfig::verticalBranchSeparation), BlockStateProvider.TYPE_CODEC.fieldOf("trunk_provider").forGetter(GiantPineTreeConfig::trunkProvider), BlockStateProvider.TYPE_CODEC.fieldOf("foliage_provider").forGetter(GiantPineTreeConfig::foliageProvider)).apply(builder, GiantPineTreeConfig::new);
    });
}