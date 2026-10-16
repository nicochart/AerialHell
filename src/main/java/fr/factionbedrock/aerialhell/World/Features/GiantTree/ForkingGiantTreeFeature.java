package fr.factionbedrock.aerialhell.World.Features.GiantTree;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellConfiguredFeatures;
import fr.factionbedrock.aerialhell.Util.FeatureHelper;
import fr.factionbedrock.aerialhell.World.Features.Util.*;
import fr.factionbedrock.aerialhell.World.Features.Util.GiantTree.ClassicGiantBranch;
import fr.factionbedrock.aerialhell.World.Features.Util.GiantTree.ClassicGiantFoliage;
import fr.factionbedrock.aerialhell.World.Features.Util.GiantTree.ClassicGiantTrunk;
import fr.factionbedrock.aerialhell.World.Features.Util.GiantTree.PosLists.FoliagePosList;
import fr.factionbedrock.aerialhell.World.Features.Util.GiantTree.PosLists.ForkingTrunkBlockPosList;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

import org.jetbrains.annotations.Nullable;
import java.util.List;

public record ForkingGiantTreeFeature(int trunkMaxHorizontalOffset, int trunkMinVerticalOffset, int trunkMaxVerticalOffset, Holder<BlockStateProvider> trunkProvider, Holder<BlockStateProvider> foliageProvider) implements AbstractGiantTreeFeature
{
    private static final SplineKnotsDeformedStraightLine.KnotsParameters TRUNK_KNOTS_PARAMETERS = new SplineKnots.KnotsParameters(8, 16, 0.3F, 5, 20);
    private static final SplineKnotsDeformedStraightLine.KnotsParameters FOLIAGE_KNOTS_PARAMETERS = new SplineKnots.KnotsParameters(8, 18, 0.4F, 6, 19);

    public static final MapCodec<ForkingGiantTreeFeature> CODEC = RecordCodecBuilder.mapCodec(
        i -> i.group(
                ExtraCodecs.POSITIVE_INT.fieldOf("trunk_max_horizontal_offset").forGetter(ForkingGiantTreeFeature::trunkMaxHorizontalOffset),
                ExtraCodecs.POSITIVE_INT.fieldOf("trunk_min_vertical_offset").forGetter(ForkingGiantTreeFeature::trunkMinVerticalOffset),
                ExtraCodecs.POSITIVE_INT.fieldOf("trunk_max_vertical_offset").forGetter(ForkingGiantTreeFeature::trunkMaxVerticalOffset),
                BlockStateProvider.CODEC.fieldOf("trunk_provider").forGetter(ForkingGiantTreeFeature::trunkProvider),
                BlockStateProvider.CODEC.fieldOf("foliage_provider").forGetter(ForkingGiantTreeFeature::foliageProvider)
            )
            .apply(i, ForkingGiantTreeFeature::new)
    );

    @Override public MapCodec<ForkingGiantTreeFeature> codec() {return CODEC;}

    @Override public List<ResourceKey<Feature>> getAssociatedConfiguredFeatures() {return AerialHellConfiguredFeatures.Lists.FORKING_GIANT_TREE_TREE;}

    @Override public boolean place(WorldGenLevel level, ChunkGenerator chunkGenerator, RandomSource random, BlockPos origin)
    {
        if (!canPlace(level, origin)) {return false;}
        else
        {
            int maxXZdistance=this.trunkMaxHorizontalOffset, minYdistance=this.trunkMinVerticalOffset, maxYdistance=this.trunkMaxVerticalOffset;
            BlockPos trunkStart = origin.below(2);
            int xOffset = random.nextInt(-maxXZdistance, maxXZdistance), yOffset = random.nextInt(minYdistance, maxYdistance), zOffset = random.nextInt(-maxXZdistance, maxXZdistance);
            BlockPos trunkEnd = origin.offset(xOffset, yOffset, zOffset);
            if (!FeatureHelper.isBelowMaxBuildHeight(level, origin.above(yOffset + getYFoliageSize(trunkStart, trunkEnd)/2))) {return false;}
            FoliagePosList foliagePosList = generateTrunk(level, random, origin, trunkStart, trunkEnd, false);
            generateFoliagesAndBranches(level, random, origin, foliagePosList);
            return true;
        }
    }

    private FoliagePosList generateTrunk(WorldGenLevel level, RandomSource random, BlockPos origin, BlockPos trunkStart, BlockPos trunkEnd, boolean generateDebug)
    {
        ForkingGiantTrunk trunkSpline = new ForkingGiantTrunk(this, level, random, origin, new StraightLine.StraightLineParameters(trunkStart, trunkEnd), 2 + random.nextInt(2), false);
        ForkingTrunkBlockPosList trunkPosList = trunkSpline.generateForkingTrunk(generateDebug);
        trunkSpline = null;

        int yFoliageSize = getYFoliageSize(trunkStart, trunkEnd); int xzFoliageSize = getXZFoliageSize(yFoliageSize);
        FoliagePosList foliagePosList = new FoliagePosList(trunkPosList.getEndPos(), xzFoliageSize, yFoliageSize);

        @Nullable ForkingTrunkBlockPosList trunkPosList1 = generateTrunkFork(level, random, origin, trunkStart, trunkEnd, trunkPosList, 1);
        if (trunkPosList1 != null) {foliagePosList.addFoliageInfo(createFoliageInfo(trunkStart, trunkEnd, trunkPosList1, 3.0F / 4.0F));} //should use forkStart and forkEnd, but instead we use trunkStart and trunkEnd + a factor

        @Nullable ForkingTrunkBlockPosList trunkPosList2 = generateTrunkFork(level, random, origin, trunkStart, trunkEnd, trunkPosList, 2);
        if (trunkPosList2 != null) {foliagePosList.addFoliageInfo(createFoliageInfo(trunkStart, trunkEnd, trunkPosList2, 5.0F / 8.0F));} //should use forkStart and forkEnd, but instead we use trunkStart and trunkEnd + a factor

        return foliagePosList;
    }

    @Nullable private ForkingTrunkBlockPosList generateTrunkFork(WorldGenLevel level, RandomSource random, BlockPos origin, BlockPos supportTrunkStart, BlockPos supportTrunkEnd, ForkingTrunkBlockPosList supportTrunkPosList, int forkIndex)
    {
        BlockPos forkStart = supportTrunkPosList.getForkPos(forkIndex);
        if (forkStart == null) {return null;}
        BlockPos forkEnd = getForkEnd(forkStart, supportTrunkStart, supportTrunkEnd, -2, 0.5F);

        ForkingGiantTrunk trunkSpline = new ForkingGiantTrunk(this, level, random, origin, new StraightLine.StraightLineParameters(forkStart, forkEnd), 1 + random.nextInt(2), true);
        ForkingTrunkBlockPosList trunkPosList = trunkSpline.generateForkingTrunk(false);
        trunkSpline = null;
        return trunkPosList;
    }

    private void generateFoliagesAndBranches(WorldGenLevel level, RandomSource random, BlockPos origin, FoliagePosList posList)
    {
        FoliagePosList.FoliageInfo mainFoliageInfo = posList.getFoliage1();
        FoliagePosList.FoliageInfo fork1FoliageInfo = posList.getFoliage2();
        FoliagePosList.FoliageInfo fork2FoliageInfo = posList.getFoliage3();

        generateFoliage(level, random, origin, mainFoliageInfo.getFoliagePos(), mainFoliageInfo.getXzSize(), mainFoliageInfo.getySize());
        if (fork1FoliageInfo != null) {generateFoliage(level, random, origin, fork1FoliageInfo.getFoliagePos(), fork1FoliageInfo.getXzSize(), fork1FoliageInfo.getySize());}
        if (fork2FoliageInfo != null) {generateFoliage(level, random, origin, fork2FoliageInfo.getFoliagePos(), fork2FoliageInfo.getXzSize(), fork2FoliageInfo.getySize());}

        generateBranches(level, random, origin, mainFoliageInfo.getFoliagePos(), mainFoliageInfo.getXzSize(), mainFoliageInfo.getySize());
        if (fork1FoliageInfo != null) {generateBranches(level, random, origin, fork1FoliageInfo.getFoliagePos(), fork1FoliageInfo.getXzSize(), fork1FoliageInfo.getySize());}
        if (fork2FoliageInfo != null) {generateBranches(level, random, origin, fork2FoliageInfo.getFoliagePos(), fork2FoliageInfo.getXzSize(), fork2FoliageInfo.getySize());}
    }

    private void generateFoliage(WorldGenLevel level, RandomSource random, BlockPos origin, BlockPos centerPos, int xzSize, int ySize)
    {
        GiantFoliage foliage = new GiantFoliage(this, level, random, origin, ClassicGiantFoliage.createClassicGiantFoliageEllipsoidParameters(xzSize, ySize), centerPos, 8);
        foliage.generateFoliage();
        foliage = null;
    }

    private void generateBranches(WorldGenLevel level, RandomSource random, BlockPos origin, BlockPos foliageCenterPos, int xzFoliageSize, int yFoliageSize)
    {
        int yMaxDistance = yFoliageSize - 1; int yMinDistance = yMaxDistance >= 3 ? 2 : yMaxDistance - 1;
        int xzMaxDistance = xzFoliageSize; int xzMinDistance = xzMaxDistance * 2 / 3;

        generateRandomBranch(level, random, origin, foliageCenterPos, 1, 4, xzMinDistance, xzMaxDistance, yMinDistance, yMaxDistance, xzMinDistance, xzMaxDistance);
        generateRandomBranch(level, random, origin, foliageCenterPos, 1, 4, xzMinDistance, xzMaxDistance, yMinDistance, yMaxDistance, - xzMaxDistance, - xzMinDistance);
        generateRandomBranch(level, random, origin, foliageCenterPos, 1, 4, - xzMaxDistance, - xzMinDistance, yMinDistance, yMaxDistance, xzMinDistance, xzMaxDistance);
        generateRandomBranch(level, random, origin, foliageCenterPos, 1, 4, - xzMaxDistance, - xzMinDistance, yMinDistance, yMaxDistance, - xzMaxDistance, - xzMinDistance);

        generateRandomBranch(level, random, origin, foliageCenterPos, 1, 4, - xzMaxDistance, xzMaxDistance, yMinDistance, yMaxDistance, - xzMaxDistance, xzMaxDistance);
    }

    private void generateRandomBranch(WorldGenLevel level, RandomSource random, BlockPos origin, BlockPos foliageCenterPos, int startMinYoffset, int startMaxYoffset, int minXoffset, int maxXoffset, int minYoffset, int maxYoffset, int minZoffset, int maxZoffset)
    {
        BlockPos branchStart = foliageCenterPos.below(random.nextInt(startMinYoffset, startMaxYoffset));
        BlockPos branchEnd = foliageCenterPos.offset(random.nextInt(minXoffset, maxXoffset), random.nextInt(minYoffset, maxYoffset), random.nextInt(minZoffset, maxZoffset));
        generateBranch(level, random, origin, branchStart, branchEnd);
    }

    private void generateBranch(WorldGenLevel level, RandomSource random, BlockPos origin, BlockPos branchStart, BlockPos branchEnd)
    {
        GiantBranch branch = new GiantBranch(this, level, random, origin, new StraightLine.StraightLineParameters(branchStart, branchEnd), 1);
        branch.generate(false, false);
        branch = null;
    }

    private BlockPos getForkEnd(BlockPos forkStart, BlockPos supportTrunkStart, BlockPos supportTrunkEnd, float xzfactor, float yfactor)
    {
        int xOffset = supportTrunkEnd.getX() - supportTrunkStart.getX(), yOffset = supportTrunkEnd.getY() - supportTrunkStart.getY(), zOffset = supportTrunkEnd.getZ() - supportTrunkStart.getZ();
        return forkStart.offset((int) (xzfactor*xOffset), (int) (yOffset*yfactor), (int) (xzfactor*zOffset));
    }

    private int getYFoliageSize(BlockPos trunkStart, BlockPos trunkEnd) {return getYFoliageSize(trunkStart, trunkEnd, 1);}
    private int getYFoliageSize(BlockPos trunkStart, BlockPos trunkEnd, float sizeFactor) {return getYFoliageSize(trunkStart, trunkEnd, this.trunkMinVerticalOffset, this.trunkMaxVerticalOffset, sizeFactor);}
    private int getYFoliageSize(BlockPos trunkStart, BlockPos trunkEnd, int minTrunkHeight, int maxTrunkHeight, float sizeFactor) {return getYFoliageSize(trunkEnd.getY() - trunkStart.getY(), minTrunkHeight, maxTrunkHeight, sizeFactor);}
    private int getYFoliageSize(int trunkHeight, int minTrunkHeight, int maxTrunkHeight, float sizeFactor) {return getYFoliageSize((int) (trunkHeight * sizeFactor), (int) (minTrunkHeight * sizeFactor), (int) (maxTrunkHeight * sizeFactor));}
    private int getYFoliageSize(int trunkHeight, int minTrunkHeight, int maxTrunkHeight) {return Math.max((minTrunkHeight + maxTrunkHeight) / 16 /*average divided by 8*/, trunkHeight / 8);}
    private int getXZFoliageSize(int yFoliageSize) {return (int) (yFoliageSize * 3.2F);}

    private FoliagePosList.FoliageInfo createFoliageInfo(BlockPos trunkStart, BlockPos trunkEnd, ForkingTrunkBlockPosList trunkPosList, float sizeFactor)
    {
        int yFoliageSize = getYFoliageSize(trunkStart, trunkEnd, sizeFactor); int xzFoliageSize = getXZFoliageSize(yFoliageSize);
        return new FoliagePosList.FoliageInfo(trunkPosList.getEndPos(), xzFoliageSize, yFoliageSize);
    }

    private boolean hasLargeTrunk() {return (this.trunkMaxVerticalOffset + this.trunkMinVerticalOffset) / 2 > 16;}

    private static class ForkingGiantTrunk extends ClassicGiantTrunk
    {
        private final ForkingGiantTreeFeature feature;
        private final boolean isFork;
        public ForkingGiantTrunk(ForkingGiantTreeFeature feature, WorldGenLevel level, RandomSource random, BlockPos origin, StraightLineParameters straightLineParams, int knotsNumber, boolean isFork)
        {
            super(level, random, origin, straightLineParams, knotsNumber, TRUNK_KNOTS_PARAMETERS, () -> feature.trunkProvider().value().getState(level, random, origin).getBlock());
            this.feature = feature;
            this.isFork = isFork;
        }

        @Override protected boolean isLarge() {return this.feature.hasLargeTrunk();}

        public ForkingTrunkBlockPosList generateForkingTrunk(boolean generateDebug)
        {
            int i = 0, maxAbsOffset = FeatureHelper.getMaxAbsoluteXYZOffset(this.straightLineParams.getStart(), this.straightLineParams.getEnd());

            BlockPos.MutableBlockPos placementPos = this.straightLineParams.getStart().mutable();
            BlockPos forkPos1 = null, forkPos2 = null;
            while(!placementPos.equals(this.straightLineParams.getEnd()) && i <= maxAbsOffset * straightLineParams.getPrecisionMultiplicator())
            {
                placementPos.set(getKnotsDeformedPos(getOffsetPosFromStart(i), this.getKnots(), this.getKnotsNumber(), this.getKnotsParameters()));

                if (this.isFork && i==0) {generateForkToSupportTrunkJonction(this.straightLineParams.getStart(), placementPos.immutable());}

                if (isStepFor1stFork(i, maxAbsOffset * straightLineParams.getPrecisionMultiplicator()))
                {
                    if (forkPos1 == null) {forkPos1 = placementPos.immutable();}
                    else if (randomPlaceForkPos(1)) {forkPos1 = placementPos.immutable();}
                }
                else if (isStepFor2ndFork(i, maxAbsOffset * straightLineParams.getPrecisionMultiplicator()) && randomPlaceForkPos(2)) {forkPos2 = placementPos.immutable();}

                tryPlacingBlocks(placementPos, i, maxAbsOffset * straightLineParams.getPrecisionMultiplicator());
                i++;
            }
            if (generateDebug) {this.generateDebug();}
            return new ForkingTrunkBlockPosList(forkPos1, forkPos2, placementPos.immutable());
        }

        private void generateForkToSupportTrunkJonction(BlockPos supportStart, BlockPos supportEnd)
        {
            StraightLineForkJonction jonction = new StraightLineForkJonction(this.feature, this.level, this.random, this.origin, new StraightLine.StraightLineParameters(supportStart, supportEnd));
            jonction.generate(false, false);
            jonction = null;
        }

        public boolean isStepFor1stFork(int step, int maxStep)
        {
            int quarter = maxStep / 4;
            return step >= quarter && step <= quarter * 2;
        }

        public boolean isStepFor2ndFork(int step, int maxStep)
        {
            int quarter = maxStep / 4;
            return step >= quarter * 2 && step <= quarter * 3;
        }

        public boolean randomPlaceForkPos(int forkNumber)
        {
            if (forkNumber == 1) {return this.random.nextFloat() < 0.2F;}
            else /*if (forkNumber == 2)*/ {return this.random.nextFloat() < 0.05F;}
        }

        @Override protected boolean tryPlacingBlocks(BlockPos.MutableBlockPos pos, int step, int maxStep)
        {
            if (this.isFork) {return this.tryPlacingBlocksCross(pos);}
            else {return super.tryPlacingBlocks(pos, step, maxStep);}
        }

        @Override public BlockState getStateForPlacement(BlockPos pos) {return this.feature.trunkProvider().value().getState(this.level, this.random, pos);}
    }

    private static class GiantFoliage extends ClassicGiantFoliage
    {
        private final ForkingGiantTreeFeature feature;
        public GiantFoliage(ForkingGiantTreeFeature feature, WorldGenLevel level, RandomSource random, BlockPos origin, EllipsoidParameters parameters, BlockPos centerPos, int knotsNumber)
        {
            super(level, random, origin, () -> feature.foliageProvider().value().getState(level, random, origin).getBlock(), parameters, centerPos, Types.CENTER_1x1, knotsNumber, FOLIAGE_KNOTS_PARAMETERS, true);
            this.feature = feature;
        }

        @Override public BlockState getStateForPlacement(BlockPos ellipsoidPos)
        {
            return this.feature.foliageProvider().value().getState(this.level, this.random, centerPos.offset(ellipsoidPos)).setValue(LeavesBlock.DISTANCE, getLeavesDistance(ellipsoidPos));
        }
    }

    private static class GiantBranch extends ClassicGiantBranch
    {
        private final ForkingGiantTreeFeature feature;

        public GiantBranch(ForkingGiantTreeFeature feature, WorldGenLevel level, RandomSource random, BlockPos origin, StraightLineParameters straightLineParams, int knotsNumber)
        {
            super(level, random, origin, straightLineParams, knotsNumber, TRUNK_KNOTS_PARAMETERS, () -> feature.trunkProvider().value().getState(level, random, origin).getBlock());
            this.feature = feature;
        }

        @Override protected boolean isLarge() {return this.feature.hasLargeTrunk();}

        @Override public BlockState getStateForPlacement(BlockPos pos) {return this.feature.trunkProvider().value().getState(this.level, this.random, pos);}
    }

    private static class StraightLineForkJonction extends StraightLine
    {
        private final ForkingGiantTreeFeature feature;

        public StraightLineForkJonction(ForkingGiantTreeFeature feature, WorldGenLevel level, RandomSource random, BlockPos origin, StraightLineParameters straightLineParams)
        {
            super(level, random, origin, straightLineParams, () -> feature.trunkProvider().value().getState(level, random, origin).getBlock());
            this.feature = feature;
        }

        @Override protected boolean isReplaceable(WorldGenLevel reader, BlockPos blockPos)
        {
            BlockState previousBlock = reader.getBlockState(blockPos);
            return super.isReplaceable(reader, blockPos) || previousBlock.is(AerialHellTags.Blocks.STELLAR_DIRT) || previousBlock.is(AerialHellBlocks.STELLAR_STONE);
        }

        @Override public BlockState getStateForPlacement(BlockPos pos) {return this.feature.trunkProvider().value().getState(this.level, this.random, pos);}
    }
}
