package fr.factionbedrock.aerialhell.World.Features;

import com.mojang.serialization.Codec;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Util.FeatureHelper;
import fr.factionbedrock.aerialhell.World.Features.Config.ClassicGiantTreeConfig;
import fr.factionbedrock.aerialhell.World.Features.Util.*;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

public class GiantTreeFeature extends Feature<ClassicGiantTreeConfig>
{
    private static final SplineKnotsDeformedStraightLine.KnotsParameters TRUNK_KNOTS_PARAMETERS = new SplineKnots.KnotsParameters(8, 16, 0.3F, 5, 20);
    private static final SplineKnotsDeformedStraightLine.KnotsParameters FOLIAGE_KNOTS_PARAMETERS = new SplineKnots.KnotsParameters(8, 18, 0.4F, 6, 19);

    public GiantTreeFeature(Codec<ClassicGiantTreeConfig> codec) {super(codec);}

    @Override public boolean place(FeaturePlaceContext<ClassicGiantTreeConfig> context)
    {
        WorldGenLevel level = context.level(); RandomSource rand = context.random(); ClassicGiantTreeConfig config = context.config();
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
            int xzFoliageSize = (int) (yFoliageSize * 1.6F);
            BlockPos foliageCenter = generateTrunk(context, trunkStart, trunkEnd, false);
            generateFoliage(context, foliageCenter, xzFoliageSize, yFoliageSize);
            generateBranches(context, foliageCenter, xzFoliageSize, yFoliageSize);
            return true;
        }
    }

    protected BlockPos generateTrunk(FeaturePlaceContext<ClassicGiantTreeConfig> context, BlockPos trunkStart, BlockPos trunkEnd, boolean generateDebug)
    {
        GiantTrunk trunkSpline = new GiantTrunk(context, new StraightLine.StraightLineParameters(trunkStart, trunkEnd), 2 + context.random().nextInt(2));
        BlockPos lastPos = trunkSpline.generate(generateDebug);
        trunkSpline = null;
        return lastPos;
    }

    protected void generateFoliage(FeaturePlaceContext<ClassicGiantTreeConfig> context, BlockPos centerPos, int xzSize, int ySize)
    {
        GiantFoliage foliage = new GiantFoliage(context, createEllipsoidParameters(xzSize, ySize), centerPos, 8);
        foliage.generate();
        foliage.generateOutsideBorder();
        foliage = null;
    }

    protected void generateBranches(FeaturePlaceContext<ClassicGiantTreeConfig> context, BlockPos foliageCenterPos, int xzFoliageSize, int yFoliageSize)
    {
        int yMaxDistance = yFoliageSize - 1; int yMinDistance = yMaxDistance >= 3 ? 2 : yMaxDistance - 1;
        int xzMaxDistance = xzFoliageSize; int xzMinDistance = xzMaxDistance * 2 / 3;

        generateRandomBranch(context, foliageCenterPos, 1, 4, xzMinDistance, xzMaxDistance, yMinDistance, yMaxDistance, xzMinDistance, xzMaxDistance);
        generateRandomBranch(context, foliageCenterPos, 1, 4, xzMinDistance, xzMaxDistance, yMinDistance, yMaxDistance, - xzMaxDistance, - xzMinDistance);
        generateRandomBranch(context, foliageCenterPos, 1, 4, - xzMaxDistance, - xzMinDistance, yMinDistance, yMaxDistance, xzMinDistance, xzMaxDistance);
        generateRandomBranch(context, foliageCenterPos, 1, 4, - xzMaxDistance, - xzMinDistance, yMinDistance, yMaxDistance, - xzMaxDistance, - xzMinDistance);

        generateRandomBranch(context, foliageCenterPos, 1, 4, - xzMaxDistance, xzMaxDistance, yMinDistance, yMaxDistance, - xzMaxDistance, xzMaxDistance);
    }

    protected void generateRandomBranch(FeaturePlaceContext<ClassicGiantTreeConfig> context, BlockPos foliageCenterPos, int startMinYoffset, int startMaxYoffset, int minXoffset, int maxXoffset, int minYoffset, int maxYoffset, int minZoffset, int maxZoffset)
    {
        RandomSource rand = context.random();
        BlockPos branchStart = foliageCenterPos.below(rand.nextInt(startMinYoffset, startMaxYoffset));
        BlockPos branchEnd = foliageCenterPos.offset(rand.nextInt(minXoffset, maxXoffset), rand.nextInt(minYoffset, maxYoffset), rand.nextInt(minZoffset, maxZoffset));
        generateBranch(context, branchStart, branchEnd);
    }

    protected void generateBranch(FeaturePlaceContext<ClassicGiantTreeConfig> context, BlockPos branchStart, BlockPos branchEnd)
    {
        GiantBranch branch = new GiantBranch(context, new StraightLine.StraightLineParameters(branchStart, branchEnd), 1);
        branch.generate(false);
        branch = null;
    }

    protected int getYFoliageSize(BlockPos trunkStart, BlockPos trunkEnd, int minTrunkHeight, int maxTrunkHeight) {return getYFoliageSize(trunkEnd.getY() - trunkStart.getY(), minTrunkHeight, maxTrunkHeight);}
    protected int getYFoliageSize(int trunkHeight, int minTrunkHeight, int maxTrunkHeight) {return Math.max((minTrunkHeight + maxTrunkHeight) / 8 /*average divided by 4*/, trunkHeight / 4);}

    private boolean isValidTreePos(WorldGenLevel level, BlockPos pos) {return isValidTreeSupport(level.getBlockState(pos.below())) && level.isEmptyBlock(pos) && thereIsAirAbovePosition(level, pos);}
    private boolean isValidTreeSupport(BlockState state) {return state.is(AerialHellTags.Blocks.STELLAR_DIRT);}
    private boolean thereIsAirAbovePosition(WorldGenLevel level, BlockPos pos) {return thereIsAirColumnAbovePos(level, pos);}

    private boolean thereIsAirColumnAbovePos(WorldGenLevel reader, BlockPos pos)
    {
        for (int y=1; y<=8; y++) {if (!reader.getBlockState(pos.above(y)).isAir()) {return false;}} return true;
    }

    private static class GiantTrunk extends SplineKnotsDeformedStraightLine
    {
        public GiantTrunk(FeaturePlaceContext<ClassicGiantTreeConfig> context, StraightLineParameters straightLineParams, int knotsNumber) {super(context, straightLineParams, knotsNumber, TRUNK_KNOTS_PARAMETERS, () -> context.config().trunkProvider().getState(context.random(), context.origin()).getBlock());}

        @Override protected boolean isReplaceable(WorldGenLevel level, BlockPos blockPos)
        {
            BlockState previousBlock = level.getBlockState(blockPos);
            return super.isReplaceable(level, blockPos) || previousBlock.is(AerialHellTags.Blocks.STELLAR_DIRT);
        }

        @Override protected void tryPlacingBlocks(BlockPos.MutableBlockPos pos, int step, int maxStep)
        {
            if (step < maxStep / 8) {tryPlacingBlocksSphere(pos, 3);}
            else {tryPlacingBlocksSphere(pos, 2);}
        }
    }

    private Ellipsoid.EllipsoidParameters createEllipsoidParameters(int xzSize, int ySize)
    {
        return new Ellipsoid.EllipsoidParameters(xzSize, ySize, xzSize, - xzSize, xzSize, -1, ySize, - xzSize, xzSize, 1);
    }

    private static class GiantFoliage extends SplineKnotsDeformedEllipsoid
    {
        public GiantFoliage(FeaturePlaceContext<ClassicGiantTreeConfig> context, Ellipsoid.EllipsoidParameters parameters, BlockPos centerPos, int knotsNumber)
        {
            super(context, () -> context.config().foliageProvider().getState(context.random(), context.origin()).getBlock(), parameters, centerPos, Ellipsoid.Types.CENTER_1x1, knotsNumber, FOLIAGE_KNOTS_PARAMETERS, true);
        }

        @Override public BlockState getStateForPlacement(BlockPos ellipsoidPos)
        {
            return ((ClassicGiantTreeConfig)context.config()).foliageProvider().getState(context.random(), centerPos.offset(ellipsoidPos)).setValue(LeavesBlock.DISTANCE, getLeavesDistance(ellipsoidPos));
        }

        protected int getLeavesDistance(BlockPos ellipsoidPos)
        {
            int x = Math.abs(ellipsoidPos.getX()), y = Math.abs(ellipsoidPos.getY()), z = Math.abs(ellipsoidPos.getZ());
            float r = (float) x*x/(ellipsoidParams.xSize()*ellipsoidParams.xSize()) + (float) y*y/(ellipsoidParams.ySize()*ellipsoidParams.ySize()) + (float) z*z/(ellipsoidParams.zSize()*ellipsoidParams.zSize());

            if (r > 0.87) {return 7;}
            else if (r > 0.75) {return 6;}
            else if (r > 0.6) {return 5;}
            else if (r > 0.4) {return 4;}
            else if (r > 0.3) {return 3;}
            else if (r > 0.2) {return 2;}
            else {return 1;}
        }

        @Override public float randomChanceToGenerateBlock(boolean generatingBorder) {return generatingBorder ? 0.5F : 1.0F;}

        @Override protected void generateInnerLoop(BlockPos.MutableBlockPos placementPos, int x, int y, int z, boolean generateBorder)
        {
            if (y == this.ellipsoidParams.yForMin() && randomlyChooseToNotPlaceBlock(true)) {return;}
            else {super.generateInnerLoop(placementPos, x, y, z, generateBorder);}
        }
    }

    private static class GiantBranch extends SplineKnotsDeformedStraightLine
    {
        public GiantBranch(FeaturePlaceContext<ClassicGiantTreeConfig> context, StraightLineParameters straightLineParams, int knotsNumber) {super(context, straightLineParams, knotsNumber, TRUNK_KNOTS_PARAMETERS, () -> context.config().trunkProvider().getState(context.random(), context.origin()).getBlock());}

        @Override protected boolean isReplaceable(WorldGenLevel level, BlockPos blockPos)
        {
            BlockState previousBlock = level.getBlockState(blockPos);
            return previousBlock.is(AerialHellTags.Blocks.LEAVES) || super.isReplaceable(level, blockPos) || previousBlock.is(AerialHellTags.Blocks.STELLAR_DIRT);
        }

        @Override protected void tryPlacingBlock(FeaturePlaceContext<?> context, BlockPos.MutableBlockPos pos)
        {
            if (!context.level().isEmptyBlock(pos.above())) {super.tryPlacingBlock(context, pos);}
        }

        @Override public BlockState getStateForPlacement(BlockPos pos)
        {
            return ((ClassicGiantTreeConfig)context.config()).trunkProvider().getState(context.random(), pos);
        }
    }
}