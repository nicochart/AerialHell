package fr.factionbedrock.aerialhell.World.Features;

import com.mojang.serialization.Codec;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Util.FeatureHelper;
import fr.factionbedrock.aerialhell.World.Features.Config.ForkingGiantTreeConfig;
import fr.factionbedrock.aerialhell.World.Features.Util.*;
import fr.factionbedrock.aerialhell.World.Features.Util.GiantTree.ClassicGiantBranch;
import fr.factionbedrock.aerialhell.World.Features.Util.GiantTree.ClassicGiantFoliage;
import fr.factionbedrock.aerialhell.World.Features.Util.GiantTree.ClassicGiantTrunk;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

public class ForkingGiantTreeFeature extends Feature<ForkingGiantTreeConfig>
{
    private static final SplineKnotsDeformedStraightLine.KnotsParameters TRUNK_KNOTS_PARAMETERS = new SplineKnots.KnotsParameters(8, 16, 0.3F, 5, 20);
    private static final SplineKnotsDeformedStraightLine.KnotsParameters FOLIAGE_KNOTS_PARAMETERS = new SplineKnots.KnotsParameters(8, 18, 0.4F, 6, 19);

    public ForkingGiantTreeFeature(Codec<ForkingGiantTreeConfig> codec) {super(codec);}

    @Override public boolean place(FeaturePlaceContext<ForkingGiantTreeConfig> context)
    {
        WorldGenLevel level = context.level(); RandomSource rand = context.random(); ForkingGiantTreeConfig config = context.config();
        BlockPos origin = context.origin();

        boolean generatesInDungeon = FeatureHelper.isFeatureGeneratingNextToDungeon(context);

        if (!isValidTreePos(level,origin) || generatesInDungeon) {return false;}
        else
        {
            int maxXZdistance=config.trunkMaxHorizontalOffset(), minYdistance=config.trunkMinVerticalOffset(), maxYdistance=config.trunkMaxVerticalOffset();
            BlockPos trunkStart = origin.below(2);
            int xOffset = rand.nextInt(-maxXZdistance, maxXZdistance), yOffset = rand.nextInt(minYdistance, maxYdistance), zOffset = rand.nextInt(-maxXZdistance, maxXZdistance);
            BlockPos trunkEnd = origin.offset(xOffset, yOffset, zOffset);
            int yFoliageSize = getYFoliageSize(yOffset, minYdistance, maxYdistance);
            int xzFoliageSize = (int) (yFoliageSize * 3.2F);
            ForkingTrunkBlockPosList trunkPosList = generateTrunk(context, trunkStart, trunkEnd, false, false);
            BlockPos mainFoliageCenter = trunkPosList.getEndPos();
            BlockPos forkStart = trunkPosList.getForkPos();
            BlockPos forkEnd = forkStart.offset(-2*xOffset, yOffset/2, -2*zOffset);
            ForkingTrunkBlockPosList trunkPosList2 = generateTrunk(context, forkStart, forkEnd, true, false);
            int yForkFoliageSize = getYFoliageSize(yOffset * 3 / 4, minYdistance * 3 / 4, maxYdistance * 3 / 4);
            int xzForkFoliageSize = (int) (yForkFoliageSize * 3.2F);
            BlockPos forkFoliageCenter = trunkPosList2.getEndPos();
            if (trunkPosList.get2ndForkPos() != null)
            {
                BlockPos fork2Start = trunkPosList.get2ndForkPos();
                BlockPos fork2End = fork2Start.offset(-2*zOffset, yOffset/2, -2*xOffset);
                ForkingTrunkBlockPosList trunkPosList3 = generateTrunk(context, fork2Start, fork2End, true, false);
                BlockPos fork2FoliageCenter = trunkPosList3.getEndPos();
                generateFoliage(context, fork2FoliageCenter, xzForkFoliageSize, yForkFoliageSize);
            }
            generateFoliage(context, mainFoliageCenter, xzFoliageSize, yFoliageSize);
            generateFoliage(context, forkFoliageCenter, xzForkFoliageSize, yForkFoliageSize);
            generateBranches(context, mainFoliageCenter, xzFoliageSize, yFoliageSize);
            generateBranches(context, forkFoliageCenter, xzForkFoliageSize, yForkFoliageSize);
            return true;
        }
    }

    protected ForkingTrunkBlockPosList generateTrunk(FeaturePlaceContext<ForkingGiantTreeConfig> context, BlockPos trunkStart, BlockPos trunkEnd, boolean isFork, boolean generateDebug)
    {
        ForkingGiantTrunk trunkSpline = new ForkingGiantTrunk(context, new StraightLine.StraightLineParameters(trunkStart, trunkEnd), 2 + context.random().nextInt(2), isFork);
        ForkingTrunkBlockPosList trunkPosList = trunkSpline.generateForkingTrunk(generateDebug);
        trunkSpline = null;
        return trunkPosList;
    }

    protected void generateFoliage(FeaturePlaceContext<ForkingGiantTreeConfig> context, BlockPos centerPos, int xzSize, int ySize)
    {
        GiantFoliage foliage = new GiantFoliage(context, ClassicGiantFoliage.createClassicGiantFoliageEllipsoidParameters(xzSize, ySize), centerPos, 8);
        foliage.generateFoliage();
        foliage = null;
    }

    protected void generateBranches(FeaturePlaceContext<ForkingGiantTreeConfig> context, BlockPos foliageCenterPos, int xzFoliageSize, int yFoliageSize)
    {
        int yMaxDistance = yFoliageSize - 1; int yMinDistance = yMaxDistance >= 3 ? 2 : yMaxDistance - 1;
        int xzMaxDistance = xzFoliageSize; int xzMinDistance = xzMaxDistance * 2 / 3;

        generateRandomBranch(context, foliageCenterPos, 1, 4, xzMinDistance, xzMaxDistance, yMinDistance, yMaxDistance, xzMinDistance, xzMaxDistance);
        generateRandomBranch(context, foliageCenterPos, 1, 4, xzMinDistance, xzMaxDistance, yMinDistance, yMaxDistance, - xzMaxDistance, - xzMinDistance);
        generateRandomBranch(context, foliageCenterPos, 1, 4, - xzMaxDistance, - xzMinDistance, yMinDistance, yMaxDistance, xzMinDistance, xzMaxDistance);
        generateRandomBranch(context, foliageCenterPos, 1, 4, - xzMaxDistance, - xzMinDistance, yMinDistance, yMaxDistance, - xzMaxDistance, - xzMinDistance);

        generateRandomBranch(context, foliageCenterPos, 1, 4, - xzMaxDistance, xzMaxDistance, yMinDistance, yMaxDistance, - xzMaxDistance, xzMaxDistance);
    }

    protected void generateRandomBranch(FeaturePlaceContext<ForkingGiantTreeConfig> context, BlockPos foliageCenterPos, int startMinYoffset, int startMaxYoffset, int minXoffset, int maxXoffset, int minYoffset, int maxYoffset, int minZoffset, int maxZoffset)
    {
        RandomSource rand = context.random();
        BlockPos branchStart = foliageCenterPos.below(rand.nextInt(startMinYoffset, startMaxYoffset));
        BlockPos branchEnd = foliageCenterPos.offset(rand.nextInt(minXoffset, maxXoffset), rand.nextInt(minYoffset, maxYoffset), rand.nextInt(minZoffset, maxZoffset));
        generateBranch(context, branchStart, branchEnd);
    }

    protected void generateBranch(FeaturePlaceContext<ForkingGiantTreeConfig> context, BlockPos branchStart, BlockPos branchEnd)
    {
        GiantBranch branch = new GiantBranch(context, new StraightLine.StraightLineParameters(branchStart, branchEnd), 1);
        branch.generate(false);
        branch = null;
    }

    protected int getYFoliageSize(BlockPos trunkStart, BlockPos trunkEnd, int minTrunkHeight, int maxTrunkHeight) {return getYFoliageSize(trunkEnd.getY() - trunkStart.getY(), minTrunkHeight, maxTrunkHeight);}
    protected int getYFoliageSize(int trunkHeight, int minTrunkHeight, int maxTrunkHeight) {return Math.max((minTrunkHeight + maxTrunkHeight) / 16 /*average divided by 8*/, trunkHeight / 8);}

    private boolean isValidTreePos(WorldGenLevel level, BlockPos pos) {return isValidTreeSupport(level.getBlockState(pos.below())) && level.isEmptyBlock(pos) && thereIsAirAbovePosition(level, pos);}
    private boolean isValidTreeSupport(BlockState state) {return state.is(AerialHellTags.Blocks.STELLAR_DIRT);}
    private boolean thereIsAirAbovePosition(WorldGenLevel level, BlockPos pos) {return thereIsAirColumnAbovePos(level, pos);}

    private boolean thereIsAirColumnAbovePos(WorldGenLevel reader, BlockPos pos)
    {
        for (int y=1; y<=8; y++) {if (!reader.getBlockState(pos.above(y)).isAir()) {return false;}} return true;
    }

    private static class ForkingGiantTrunk extends ClassicGiantTrunk
    {
        private final boolean largeTrunk, isFork;
        public ForkingGiantTrunk(FeaturePlaceContext<ForkingGiantTreeConfig> context, StraightLineParameters straightLineParams, int knotsNumber, boolean isFork)
        {
            super(context, straightLineParams, knotsNumber, TRUNK_KNOTS_PARAMETERS, () -> context.config().trunkProvider().getState(context.random(), context.origin()).getBlock());
            this.largeTrunk = (context.config().trunkMaxVerticalOffset() + context.config().trunkMinVerticalOffset()) / 2 > 16;
            this.isFork = isFork;
        }

        @Override protected boolean isLarge() {return this.largeTrunk;}

        public ForkingTrunkBlockPosList generateForkingTrunk(boolean generateDebug)
        {
            int i = 0, maxAbsOffset = FeatureHelper.getMaxAbsoluteXYZOffset(this.straightLineParams.getStart(), this.straightLineParams.getEnd());

            BlockPos.MutableBlockPos placementPos = this.straightLineParams.getStart().mutable();
            BlockPos forkPos1 = null, forkPos2 = null;
            while(!placementPos.equals(this.straightLineParams.getEnd()) && i <= maxAbsOffset * straightLineParams.getPrecisionMultiplicator())
            {
                placementPos.set(getKnotsDeformedPos(getOffsetPosFromStart(i), this.getKnots(), this.getKnotsNumber(), this.getKnotsParameters()));

                if (this.isFork && i==0)
                {
                    StraightLineForkJonction jonction = new StraightLineForkJonction(((FeaturePlaceContext<ForkingGiantTreeConfig>) this.context), new StraightLine.StraightLineParameters(this.straightLineParams.getStart(), new BlockPos(placementPos)));
                    jonction.generate(generateDebug);
                    jonction = null;
                }

                if (isStepFor1stFork(i, maxAbsOffset * straightLineParams.getPrecisionMultiplicator()))
                {
                    if (forkPos1 == null) {forkPos1 = new BlockPos(placementPos);}
                    else if (randomPlaceForkPos(1)) {forkPos1 = new BlockPos(placementPos);}
                }
                else if (isStepFor2ndFork(i, maxAbsOffset * straightLineParams.getPrecisionMultiplicator()) && randomPlaceForkPos(2)) {forkPos2 = new BlockPos(placementPos);}

                tryPlacingBlocks(placementPos, i, maxAbsOffset * straightLineParams.getPrecisionMultiplicator());
                i++;
            }
            if (generateDebug) {this.generateDebug();}
            return new ForkingTrunkBlockPosList(forkPos1, forkPos2, new BlockPos(placementPos));
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
            if (forkNumber == 1) {return context.random().nextFloat() < 0.2F;}
            else /*if (forkNumber == 2)*/ {return context.random().nextFloat() < 0.05F;}
        }

        @Override protected void tryPlacingBlocks(BlockPos.MutableBlockPos pos, int step, int maxStep)
        {
            if (this.isFork) {this.tryPlacingBlocksCross(pos);}
            else {super.tryPlacingBlocks(pos, step, maxStep);}
        }
    }

    private static class GiantFoliage extends ClassicGiantFoliage
    {
        public GiantFoliage(FeaturePlaceContext<ForkingGiantTreeConfig> context, EllipsoidParameters parameters, BlockPos centerPos, int knotsNumber)
        {
            super(context, () -> context.config().foliageProvider().getState(context.random(), context.origin()).getBlock(), parameters, centerPos, Types.CENTER_1x1, knotsNumber, FOLIAGE_KNOTS_PARAMETERS, true);
        }

        @Override public BlockState getStateForPlacement(BlockPos ellipsoidPos)
        {
            return ((ForkingGiantTreeConfig)context.config()).foliageProvider().getState(context.random(), centerPos.offset(ellipsoidPos)).setValue(LeavesBlock.DISTANCE, getLeavesDistance(ellipsoidPos));
        }
    }

    private static class GiantBranch extends ClassicGiantBranch
    {
        private final boolean largeTrunk;

        public GiantBranch(FeaturePlaceContext<ForkingGiantTreeConfig> context, StraightLineParameters straightLineParams, int knotsNumber)
        {
            super(context, straightLineParams, knotsNumber, TRUNK_KNOTS_PARAMETERS, () -> context.config().trunkProvider().getState(context.random(), context.origin()).getBlock());
            this.largeTrunk = (context.config().trunkMaxVerticalOffset() + context.config().trunkMinVerticalOffset()) / 2 > 16;
        }

        @Override protected boolean isLarge() {return this.largeTrunk;}

        @Override public BlockState getStateForPlacement(BlockPos pos) {return ((ForkingGiantTreeConfig)context.config()).trunkProvider().getState(context.random(), pos);}
    }

    private static class StraightLineForkJonction extends StraightLine
    {
        public StraightLineForkJonction(FeaturePlaceContext<ForkingGiantTreeConfig> context, StraightLineParameters straightLineParams) {super(context, straightLineParams, () -> context.config().trunkProvider().getState(context.random(), context.origin()).getBlock());}

        @Override protected boolean isReplaceable(WorldGenLevel reader, BlockPos blockPos)
        {
            BlockState previousBlock = reader.getBlockState(blockPos);
            return super.isReplaceable(reader, blockPos) || previousBlock.is(AerialHellTags.Blocks.STELLAR_DIRT) || previousBlock.is(AerialHellBlocksAndItems.STELLAR_STONE.get());
        }

        @Override public BlockState getStateForPlacement(BlockPos pos) {return ((ForkingGiantTreeConfig)context.config()).trunkProvider().getState(context.random(), pos);}
    }
}