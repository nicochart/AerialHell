package fr.factionbedrock.aerialhell.World.Features.GiantTree;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellConfiguredFeatures;
import fr.factionbedrock.aerialhell.Util.FeatureHelper;
import fr.factionbedrock.aerialhell.World.Features.Util.GiantTree.ClassicGiantTrunk;
import fr.factionbedrock.aerialhell.World.Features.Util.SplineKnots;
import fr.factionbedrock.aerialhell.World.Features.Util.SplineKnotsDeformedStraightLine;
import fr.factionbedrock.aerialhell.World.Features.Util.StraightLine;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

import java.util.List;

public record DeadGiantTreeFeature(int trunkMaxHorizontalOffset, int trunkMinVerticalOffset, int trunkMaxVerticalOffset, float randomChanceToNotPlaceBlock, Holder<BlockStateProvider> trunkProvider) implements AbstractGiantTreeFeature
{
    private static final SplineKnotsDeformedStraightLine.KnotsParameters TRUNK_KNOTS_PARAMETERS = new SplineKnots.KnotsParameters(8, 16, 0.3F, 5, 20);

    public static final MapCodec<DeadGiantTreeFeature> CODEC = RecordCodecBuilder.mapCodec(
        i -> i.group(
                ExtraCodecs.POSITIVE_INT.fieldOf("trunk_max_horizontal_offset").forGetter(DeadGiantTreeFeature::trunkMaxHorizontalOffset),
                ExtraCodecs.POSITIVE_INT.fieldOf("trunk_min_vertical_offset").forGetter(DeadGiantTreeFeature::trunkMinVerticalOffset),
                ExtraCodecs.POSITIVE_INT.fieldOf("trunk_max_vertical_offset").forGetter(DeadGiantTreeFeature::trunkMaxVerticalOffset),
                ExtraCodecs.POSITIVE_FLOAT.fieldOf("chance_to_not_place_block").forGetter(DeadGiantTreeFeature::randomChanceToNotPlaceBlock),
                BlockStateProvider.CODEC.fieldOf("trunk_provider").forGetter(DeadGiantTreeFeature::trunkProvider)
            )
            .apply(i, DeadGiantTreeFeature::new)
    );

    @Override public MapCodec<DeadGiantTreeFeature> codec() {return CODEC;}

    @Override public List<ResourceKey<Feature>> getAssociatedConfiguredFeatures() {return AerialHellConfiguredFeatures.Lists.DEAD_GIANT_TREE_TREE_LIST;}

    @Override public boolean place(WorldGenLevel level, ChunkGenerator chunkGenerator, RandomSource random, BlockPos origin)
    {
        if (!canPlace(level, origin)) {return false;}
        else
        {
            int maxXZdistance=this.trunkMaxHorizontalOffset, minYdistance=this.trunkMinVerticalOffset, maxYdistance=this.trunkMaxVerticalOffset;
            BlockPos trunkStart = origin.below(2);
            int xOffset = random.nextInt(-maxXZdistance, maxXZdistance), yOffset = random.nextInt(minYdistance, maxYdistance), zOffset = random.nextInt(-maxXZdistance, maxXZdistance);
            BlockPos trunkEnd = origin.offset(xOffset, yOffset, zOffset);
            if (!FeatureHelper.isBelowMaxBuildHeight(level, origin.above(yOffset / 2))) {return false;}
            generateTrunk(level, random, origin, trunkStart, trunkEnd, false);
            return true;
        }
    }

    private void generateTrunk(WorldGenLevel level, RandomSource random, BlockPos origin, BlockPos trunkStart, BlockPos trunkEnd, boolean generateDebug)
    {
        DeadGiantTrunk trunkSpline = new DeadGiantTrunk(this, level, random, origin, new StraightLine.StraightLineParameters(trunkStart, trunkEnd), 2 + random.nextInt(2));
        trunkSpline.generateInsideBorder(false, generateDebug);
        trunkSpline = null;
    }

    @Override public boolean isValidTreeSupport(BlockState state) {return AbstractGiantTreeFeature.super.isValidTreeSupport(state) || state.is(AerialHellBlocks.SLIPPERY_SAND);}

    private static class DeadGiantTrunk extends ClassicGiantTrunk
    {
        private final DeadGiantTreeFeature feature;
        public DeadGiantTrunk(DeadGiantTreeFeature feature, WorldGenLevel level, RandomSource random, BlockPos origin, StraightLineParameters straightLineParams, int knotsNumber)
        {
            super(level, random, origin, straightLineParams, knotsNumber, TRUNK_KNOTS_PARAMETERS, () -> feature.trunkProvider().value().getState(level, random, origin).getBlock());
            this.feature = feature;
        }

        @Override protected boolean isLarge() {return (this.feature.trunkMaxVerticalOffset() + this.feature.trunkMinVerticalOffset()) / 2 > 12;}

        @Override protected boolean tryPlacingBlock(BlockPos.MutableBlockPos pos)
        {
            if (this.random.nextFloat() < this.feature.randomChanceToNotPlaceBlock()) {return false;}
            else {return super.tryPlacingBlock(pos);}
        }

        @Override public BlockState getStateForPlacement(BlockPos pos)
        {
            return this.feature.trunkProvider().value().getState(this.level, this.random, pos);
        }

        @Override public BlockPos generateInsideBorder(boolean stopAtAnyObstacle, boolean generateDebug)
        {
            if (generatePosList == null) {simulateGenerate(stopAtAnyObstacle);}

            int yMeanOffset = (this.feature.trunkMinVerticalOffset() + this.feature.trunkMaxVerticalOffset()) / 2;
            int yCut = this.straightLineParams.getStart().getY() + yMeanOffset/2 + this.random.nextInt(-yMeanOffset/4, +yMeanOffset/4);
            BlockPos lastPos = this.straightLineParams.getStart();
            if (generatePosList.isEmpty()) {return this.straightLineParams.getStart();}
            else
            {
                for (BlockPos pos : generatePosList)
                {
                    if (isInsideBorder(pos) && pos.getY() <= yCut + this.random.nextInt(2))
                    {
                        tryPlacingBlock(pos.mutable());
                        lastPos = pos;
                    }
                }
            }
            if (generateDebug) {this.generateDebug();}
            return lastPos;
        }
    }
}
