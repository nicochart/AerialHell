package fr.factionbedrock.aerialhell.World.Features.GiantTree;

import com.mojang.serialization.Codec;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Util.FeatureHelper;
import fr.factionbedrock.aerialhell.World.Features.Config.ForkingGiantTreeConfig;
import fr.factionbedrock.aerialhell.World.Features.Util.*;
import fr.factionbedrock.aerialhell.World.Features.Util.GiantTree.ClassicGiantBranch;
import fr.factionbedrock.aerialhell.World.Features.Util.GiantTree.ClassicGiantFoliage;
import fr.factionbedrock.aerialhell.World.Features.Util.GiantTree.ClassicGiantTrunk;
import fr.factionbedrock.aerialhell.World.Features.Util.GiantTree.PosLists.FoliagePosList;
import fr.factionbedrock.aerialhell.World.Features.Util.GiantTree.PosLists.ForkingTrunkBlockPosList;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

import javax.annotation.Nullable;

public class ForkingGiantTreeFeature extends AbstractGiantTreeFeature<ForkingGiantTreeConfig>
{
    private static final SplineKnotsDeformedStraightLine.KnotsParameters TRUNK_KNOTS_PARAMETERS = new SplineKnots.KnotsParameters(8, 16, 0.3F, 5, 20);
    private static final SplineKnotsDeformedStraightLine.KnotsParameters FOLIAGE_KNOTS_PARAMETERS = new SplineKnots.KnotsParameters(8, 18, 0.4F, 6, 19);

    public ForkingGiantTreeFeature(Codec<ForkingGiantTreeConfig> codec) {super(codec);}

    @Override public boolean place(FeaturePlaceContext<ForkingGiantTreeConfig> context)
    {
        RandomSource rand = context.random(); ForkingGiantTreeConfig config = context.config();
        BlockPos origin = context.origin();

        if (!canPlace(context)) {return false;}
        else
        {
            int maxXZdistance=config.trunkMaxHorizontalOffset(), minYdistance=config.trunkMinVerticalOffset(), maxYdistance=config.trunkMaxVerticalOffset();
            BlockPos trunkStart = origin.below(2);
            int xOffset = rand.nextInt(-maxXZdistance, maxXZdistance), yOffset = rand.nextInt(minYdistance, maxYdistance), zOffset = rand.nextInt(-maxXZdistance, maxXZdistance);
            BlockPos trunkEnd = origin.offset(xOffset, yOffset, zOffset);
            if (!FeatureHelper.isBelowMaxBuildHeight(context, context.origin().above(yOffset + getYFoliageSize(trunkStart, trunkEnd, context)/2))) {return false;}
            FoliagePosList foliagePosList = generateTrunk(context, trunkStart, trunkEnd, false);
            generateFoliagesAndBranches(context, foliagePosList);
            return true;
        }
    }

    protected FoliagePosList generateTrunk(FeaturePlaceContext<ForkingGiantTreeConfig> context, BlockPos trunkStart, BlockPos trunkEnd, boolean generateDebug)
    {
        ForkingGiantTrunk trunkSpline = new ForkingGiantTrunk(context, new StraightLine.StraightLineParameters(trunkStart, trunkEnd), 2 + context.random().nextInt(2), false);
        ForkingTrunkBlockPosList trunkPosList = trunkSpline.generateForkingTrunk(generateDebug);
        trunkSpline = null;

        int yFoliageSize = getYFoliageSize(trunkStart, trunkEnd, context); int xzFoliageSize = getXZFoliageSize(yFoliageSize);
        FoliagePosList foliagePosList = new FoliagePosList(trunkPosList.getEndPos(), xzFoliageSize, yFoliageSize);

        @Nullable ForkingTrunkBlockPosList trunkPosList1 = generateTrunkFork(context, trunkStart, trunkEnd, trunkPosList, 1);
        if (trunkPosList1 != null) {foliagePosList.addFoliageInfo(createFoliageInfo(context, trunkStart, trunkEnd, trunkPosList1, 3.0F / 4.0F));} //should use forkStart and forkEnd, but instead we use trunkStart and trunkEnd + a factor

        @Nullable ForkingTrunkBlockPosList trunkPosList2 = generateTrunkFork(context, trunkStart, trunkEnd, trunkPosList, 2);
        if (trunkPosList2 != null) {foliagePosList.addFoliageInfo(createFoliageInfo(context, trunkStart, trunkEnd, trunkPosList2, 5.0F / 8.0F));} //should use forkStart and forkEnd, but instead we use trunkStart and trunkEnd + a factor

        return foliagePosList;
    }

    @Nullable protected ForkingTrunkBlockPosList generateTrunkFork(FeaturePlaceContext<ForkingGiantTreeConfig> context, BlockPos supportTrunkStart, BlockPos supportTrunkEnd, ForkingTrunkBlockPosList supportTrunkPosList, int forkIndex)
    {
        BlockPos forkStart = supportTrunkPosList.getForkPos(forkIndex);
        if (forkStart == null) {return null;}
        BlockPos forkEnd = getForkEnd(forkStart, supportTrunkStart, supportTrunkEnd, -2, 0.5F);

        ForkingGiantTrunk trunkSpline = new ForkingGiantTrunk(context, new StraightLine.StraightLineParameters(forkStart, forkEnd), 1 + context.random().nextInt(2), true);
        ForkingTrunkBlockPosList trunkPosList = trunkSpline.generateForkingTrunk(false);
        trunkSpline = null;
        return trunkPosList;
    }

    protected void generateFoliagesAndBranches(FeaturePlaceContext<ForkingGiantTreeConfig> context, FoliagePosList posList)
    {
        FoliagePosList.FoliageInfo mainFoliageInfo = posList.getFoliage1();
        FoliagePosList.FoliageInfo fork1FoliageInfo = posList.getFoliage2();
        FoliagePosList.FoliageInfo fork2FoliageInfo = posList.getFoliage3();

        generateFoliage(context, mainFoliageInfo.getFoliagePos(), mainFoliageInfo.getXzSize(), mainFoliageInfo.getySize());
        if (fork1FoliageInfo != null) {generateFoliage(context, fork1FoliageInfo.getFoliagePos(), fork1FoliageInfo.getXzSize(), fork1FoliageInfo.getySize());}
        if (fork2FoliageInfo != null) {generateFoliage(context, fork2FoliageInfo.getFoliagePos(), fork2FoliageInfo.getXzSize(), fork2FoliageInfo.getySize());}

        generateBranches(context, mainFoliageInfo.getFoliagePos(), mainFoliageInfo.getXzSize(), mainFoliageInfo.getySize());
        if (fork1FoliageInfo != null) {generateBranches(context, fork1FoliageInfo.getFoliagePos(), fork1FoliageInfo.getXzSize(), fork1FoliageInfo.getySize());}
        if (fork2FoliageInfo != null) {generateBranches(context, fork2FoliageInfo.getFoliagePos(), fork2FoliageInfo.getXzSize(), fork2FoliageInfo.getySize());}
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
        branch.generate(false, false);
        branch = null;
    }

    protected BlockPos getForkEnd(BlockPos forkStart, BlockPos supportTrunkStart, BlockPos supportTrunkEnd, float xzfactor, float yfactor)
    {
        int xOffset = supportTrunkEnd.getX() - supportTrunkStart.getX(), yOffset = supportTrunkEnd.getY() - supportTrunkStart.getY(), zOffset = supportTrunkEnd.getZ() - supportTrunkStart.getZ();
        return forkStart.offset((int) (xzfactor*xOffset), (int) (yOffset*yfactor), (int) (xzfactor*zOffset));
    }

    protected int getYFoliageSize(BlockPos trunkStart, BlockPos trunkEnd, FeaturePlaceContext<ForkingGiantTreeConfig> context) {return getYFoliageSize(trunkStart, trunkEnd, context, 1);}
    protected int getYFoliageSize(BlockPos trunkStart, BlockPos trunkEnd, FeaturePlaceContext<ForkingGiantTreeConfig> context, float sizeFactor) {return getYFoliageSize(trunkStart, trunkEnd, context.config().trunkMinVerticalOffset(), context.config().trunkMaxVerticalOffset(), sizeFactor);}
    protected int getYFoliageSize(BlockPos trunkStart, BlockPos trunkEnd, int minTrunkHeight, int maxTrunkHeight, float sizeFactor) {return getYFoliageSize(trunkEnd.getY() - trunkStart.getY(), minTrunkHeight, maxTrunkHeight, sizeFactor);}
    protected int getYFoliageSize(int trunkHeight, int minTrunkHeight, int maxTrunkHeight, float sizeFactor) {return getYFoliageSize((int) (trunkHeight * sizeFactor), (int) (minTrunkHeight * sizeFactor), (int) (maxTrunkHeight * sizeFactor));}
    protected int getYFoliageSize(int trunkHeight, int minTrunkHeight, int maxTrunkHeight) {return Math.max((minTrunkHeight + maxTrunkHeight) / 16 /*average divided by 8*/, trunkHeight / 8);}
    protected int getXZFoliageSize(int yFoliageSize) {return (int) (yFoliageSize * 3.2F);}

    protected FoliagePosList.FoliageInfo createFoliageInfo(FeaturePlaceContext<ForkingGiantTreeConfig> context, BlockPos trunkStart, BlockPos trunkEnd, ForkingTrunkBlockPosList trunkPosList, float sizeFactor)
    {
        int yFoliageSize = getYFoliageSize(trunkStart, trunkEnd, context, sizeFactor); int xzFoliageSize = getXZFoliageSize(yFoliageSize);
        return new FoliagePosList.FoliageInfo(trunkPosList.getEndPos(), xzFoliageSize, yFoliageSize);
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

                if (this.isFork && i==0) {generateForkToSupportTrunkJonction(this.straightLineParams.getStart(), new BlockPos(placementPos));}

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

        private void generateForkToSupportTrunkJonction(BlockPos supportStart, BlockPos supportEnd)
        {
            StraightLineForkJonction jonction = new StraightLineForkJonction(((FeaturePlaceContext<ForkingGiantTreeConfig>) this.context), new StraightLine.StraightLineParameters(supportStart, supportEnd));
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
            if (forkNumber == 1) {return context.random().nextFloat() < 0.2F;}
            else /*if (forkNumber == 2)*/ {return context.random().nextFloat() < 0.05F;}
        }

        @Override protected boolean tryPlacingBlocks(BlockPos.MutableBlockPos pos, int step, int maxStep)
        {
            if (this.isFork) {return this.tryPlacingBlocksCross(pos);}
            else {return super.tryPlacingBlocks(pos, step, maxStep);}
        }

        @Override public BlockState getStateForPlacement(BlockPos pos) {return ((ForkingGiantTreeConfig)context.config()).trunkProvider().getState(context.random(), pos);}
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
            return super.isReplaceable(reader, blockPos) || previousBlock.is(AerialHellTags.Blocks.STELLAR_DIRT) || previousBlock.isOf(AerialHellBlocks.STELLAR_STONE.get());
        }

        @Override public BlockState getStateForPlacement(BlockPos pos) {return ((ForkingGiantTreeConfig)context.config()).trunkProvider().getState(context.random(), pos);}
    }
}