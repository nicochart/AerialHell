package fr.factionbedrock.aerialhell.World.Features.GiantTree;

import com.mojang.serialization.Codec;
import fr.factionbedrock.aerialhell.Util.FeatureHelper;
import fr.factionbedrock.aerialhell.World.Features.Config.ClassicGiantTreeConfig;
import fr.factionbedrock.aerialhell.World.Features.Util.*;
import fr.factionbedrock.aerialhell.World.Features.Util.GiantTree.ClassicGiantBranch;
import fr.factionbedrock.aerialhell.World.Features.Util.GiantTree.ClassicGiantFoliage;
import fr.factionbedrock.aerialhell.World.Features.Util.GiantTree.ClassicGiantTrunk;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class ClassicGiantTreeFeature extends AbstractGiantTreeFeature<ClassicGiantTreeConfig>
{
    private static final SplineKnotsDeformedStraightLine.KnotsParameters TRUNK_KNOTS_PARAMETERS = new SplineKnots.KnotsParameters(8, 16, 0.3F, 5, 20);
    private static final SplineKnotsDeformedStraightLine.KnotsParameters FOLIAGE_KNOTS_PARAMETERS = new SplineKnots.KnotsParameters(8, 18, 0.4F, 6, 19);

    public ClassicGiantTreeFeature(Codec<ClassicGiantTreeConfig> codec) {super(codec);}

    @Override public boolean generate(FeatureContext<ClassicGiantTreeConfig> context)
    {
        Random rand = context.getRandom(); ClassicGiantTreeConfig config = context.getConfig();
        BlockPos origin = context.getOrigin();

        if (!canPlace(context)) {return false;}
        else
        {
            int maxXZdistance=config.trunkMaxHorizontalOffset(), minYdistance=config.trunkMinVerticalOffset(), maxYdistance=config.trunkMaxVerticalOffset();
            BlockPos trunkStart = origin.down(2);
            int xOffset = rand.nextBetweenExclusive(-maxXZdistance, maxXZdistance), yOffset = rand.nextBetweenExclusive(minYdistance, maxYdistance), zOffset = rand.nextBetweenExclusive(-maxXZdistance, maxXZdistance);
            BlockPos trunkEnd = origin.add(xOffset, yOffset, zOffset);
            int yFoliageSize = getYFoliageSize(yOffset, minYdistance, maxYdistance);
            int xzFoliageSize = (int) (yFoliageSize * 1.6F);
            yFoliageSize = (int) (config.yFoliageSizeFactor() * (float) yFoliageSize);
            xzFoliageSize = (int) (config.xzFoliageSizeFactor() * (float) xzFoliageSize);
            if (!FeatureHelper.isBelowMaxBuildHeight(context, context.getOrigin().up(yOffset + yFoliageSize/2))) {return false;}
            BlockPos foliageCenter = generateTrunk(context, trunkStart, trunkEnd, false);
            if (config.yFoliageSizeFactor() >= 1.3F) {foliageCenter = foliageCenter.down(config.yFoliageSizeFactor() >= 1.5F ? 2 : 1);}
            generateFoliage(context, foliageCenter, xzFoliageSize, yFoliageSize);
            generateBranches(context, foliageCenter, xzFoliageSize, yFoliageSize);
            return true;
        }
    }

    protected BlockPos generateTrunk(FeatureContext<ClassicGiantTreeConfig> context, BlockPos trunkStart, BlockPos trunkEnd, boolean generateDebug)
    {
        GiantTrunk trunkSpline = new GiantTrunk(context, new StraightLine.StraightLineParameters(trunkStart, trunkEnd), 2 + context.getRandom().nextInt(2));
        BlockPos lastPos = trunkSpline.generate(false, generateDebug);
        trunkSpline = null;
        return lastPos;
    }

    protected void generateFoliage(FeatureContext<ClassicGiantTreeConfig> context, BlockPos centerPos, int xzSize, int ySize)
    {
        GiantFoliage foliage = new GiantFoliage(context, ClassicGiantFoliage.createClassicGiantFoliageEllipsoidParameters(xzSize, ySize), centerPos, 8);
        foliage.generateFoliage();
        foliage = null;
    }

    protected void generateBranches(FeatureContext<ClassicGiantTreeConfig> context, BlockPos foliageCenterPos, int xzFoliageSize, int yFoliageSize)
    {
        int yMaxDistance = yFoliageSize - 1; int yMinDistance = yMaxDistance >= 3 ? 2 : yMaxDistance - 1;
        int xzMaxDistance = xzFoliageSize; int xzMinDistance = xzMaxDistance * 2 / 3;

        generateRandomBranch(context, foliageCenterPos, 1, 4, xzMinDistance, xzMaxDistance, yMinDistance, yMaxDistance, xzMinDistance, xzMaxDistance);
        generateRandomBranch(context, foliageCenterPos, 1, 4, xzMinDistance, xzMaxDistance, yMinDistance, yMaxDistance, - xzMaxDistance, - xzMinDistance);
        generateRandomBranch(context, foliageCenterPos, 1, 4, - xzMaxDistance, - xzMinDistance, yMinDistance, yMaxDistance, xzMinDistance, xzMaxDistance);
        generateRandomBranch(context, foliageCenterPos, 1, 4, - xzMaxDistance, - xzMinDistance, yMinDistance, yMaxDistance, - xzMaxDistance, - xzMinDistance);

        generateRandomBranch(context, foliageCenterPos, 1, 4, - xzMaxDistance, xzMaxDistance, yMinDistance, yMaxDistance, - xzMaxDistance, xzMaxDistance);
    }

    protected void generateRandomBranch(FeatureContext<ClassicGiantTreeConfig> context, BlockPos foliageCenterPos, int startMinYoffset, int startMaxYoffset, int minXoffset, int maxXoffset, int minYoffset, int maxYoffset, int minZoffset, int maxZoffset)
    {
        Random rand = context.getRandom();
        BlockPos branchStart = foliageCenterPos.down(rand.nextBetweenExclusive(startMinYoffset, startMaxYoffset));
        BlockPos branchEnd = foliageCenterPos.add(rand.nextBetweenExclusive(minXoffset, maxXoffset), rand.nextBetweenExclusive(minYoffset, maxYoffset), rand.nextBetweenExclusive(minZoffset, maxZoffset));
        generateBranch(context, branchStart, branchEnd);
    }

    protected void generateBranch(FeatureContext<ClassicGiantTreeConfig> context, BlockPos branchStart, BlockPos branchEnd)
    {
        GiantBranch branch = new GiantBranch(context, new StraightLine.StraightLineParameters(branchStart, branchEnd), 1);
        branch.generate(false, false);
        branch = null;
    }

    protected int getYFoliageSize(BlockPos trunkStart, BlockPos trunkEnd, int minTrunkHeight, int maxTrunkHeight) {return getYFoliageSize(trunkEnd.getY() - trunkStart.getY(), minTrunkHeight, maxTrunkHeight);}
    protected int getYFoliageSize(int trunkHeight, int minTrunkHeight, int maxTrunkHeight) {return Math.max((minTrunkHeight + maxTrunkHeight) / 8 /*average divided by 4*/, trunkHeight / 4);}

    private static class GiantTrunk extends ClassicGiantTrunk
    {
        private final boolean largeTrunk;
        public GiantTrunk(FeatureContext<ClassicGiantTreeConfig> context, StraightLineParameters straightLineParams, int knotsNumber)
        {
            super(context, straightLineParams, knotsNumber, TRUNK_KNOTS_PARAMETERS, () -> context.getConfig().trunkProvider().get(context.getRandom(), context.getOrigin()).getBlock());
            this.largeTrunk = (context.getConfig().trunkMaxVerticalOffset() + context.getConfig().trunkMinVerticalOffset()) / 2 > 16;
        }

        @Override public BlockState getStateForPlacement(BlockPos pos)
        {
            return ((ClassicGiantTreeConfig)context.getConfig()).trunkProvider().get(context.getRandom(), pos);
        }

        @Override protected boolean isLarge() {return this.largeTrunk;}
    }

    private static class GiantFoliage extends ClassicGiantFoliage
    {
        public GiantFoliage(FeatureContext<ClassicGiantTreeConfig> context, Ellipsoid.EllipsoidParameters parameters, BlockPos centerPos, int knotsNumber)
        {
            super(context, () -> context.getConfig().foliageProvider().get(context.getRandom(), context.getOrigin()).getBlock(), parameters, centerPos, Ellipsoid.Types.CENTER_1x1, knotsNumber, FOLIAGE_KNOTS_PARAMETERS, true);
        }

        @Override public BlockState getStateForPlacement(BlockPos ellipsoidPos)
        {
            return ((ClassicGiantTreeConfig)context.getConfig()).foliageProvider().get(context.getRandom(), centerPos.add(ellipsoidPos)).with(LeavesBlock.DISTANCE, getLeavesDistance(ellipsoidPos));
        }
    }

    private static class GiantBranch extends ClassicGiantBranch
    {
        private final boolean largeTrunk;

        public GiantBranch(FeatureContext<ClassicGiantTreeConfig> context, StraightLineParameters straightLineParams, int knotsNumber)
        {
            super(context, straightLineParams, knotsNumber, TRUNK_KNOTS_PARAMETERS, () -> context.getConfig().trunkProvider().get(context.getRandom(), context.getOrigin()).getBlock());
            this.largeTrunk = (context.getConfig().trunkMaxVerticalOffset() + context.getConfig().trunkMinVerticalOffset()) / 2 > 16;
        }

        @Override protected boolean isLarge() {return this.largeTrunk;}

        @Override public BlockState getStateForPlacement(BlockPos pos)
        {
            return ((ClassicGiantTreeConfig)context.getConfig()).trunkProvider().get(context.getRandom(), pos);
        }
    }
}