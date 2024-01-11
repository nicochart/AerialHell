package fr.factionbedrock.aerialhell.World.Features.GiantTree;

import com.mojang.serialization.Codec;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Util.FeatureHelper;
import fr.factionbedrock.aerialhell.World.Features.Config.DeadGiantTreeConfig;
import fr.factionbedrock.aerialhell.World.Features.Util.GiantTree.ClassicGiantTrunk;
import fr.factionbedrock.aerialhell.World.Features.Util.SplineKnots;
import fr.factionbedrock.aerialhell.World.Features.Util.SplineKnotsDeformedStraightLine;
import fr.factionbedrock.aerialhell.World.Features.Util.StraightLine;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

import java.util.ArrayList;
import java.util.List;

public class DeadGiantTreeFeature extends AbstractGiantTreeFeature<DeadGiantTreeConfig>
{
    private static final SplineKnotsDeformedStraightLine.KnotsParameters TRUNK_KNOTS_PARAMETERS = new SplineKnots.KnotsParameters(8, 16, 0.3F, 5, 20);

    public DeadGiantTreeFeature(Codec<DeadGiantTreeConfig> codec) {super(codec);}

    @Override public boolean place(FeaturePlaceContext<DeadGiantTreeConfig> context)
    {
        RandomSource rand = context.random(); DeadGiantTreeConfig config = context.config();
        BlockPos origin = context.origin();

        if (!canPlace(context)) {return false;}
        else
        {
            int maxXZdistance=config.trunkMaxHorizontalOffset(), minYdistance=config.trunkMinVerticalOffset(), maxYdistance=config.trunkMaxVerticalOffset();
            BlockPos trunkStart = origin.below(2);
            int xOffset = rand.nextInt(-maxXZdistance, maxXZdistance), yOffset = rand.nextInt(minYdistance, maxYdistance), zOffset = rand.nextInt(-maxXZdistance, maxXZdistance);
            BlockPos trunkEnd = origin.offset(xOffset, yOffset, zOffset);
            if (!FeatureHelper.isBelowMaxBuildHeight(context, context.origin().above(yOffset / 2))) {return false;}
            generateTrunk(context, trunkStart, trunkEnd, false);
            return true;
        }
    }

    protected void generateTrunk(FeaturePlaceContext<DeadGiantTreeConfig> context, BlockPos trunkStart, BlockPos trunkEnd, boolean generateDebug)
    {
        DeadGiantTrunk trunkSpline = new DeadGiantTrunk(context, new StraightLine.StraightLineParameters(trunkStart, trunkEnd), 2 + context.random().nextInt(2));
        trunkSpline.generate(false, generateDebug);
        trunkSpline = null;
    }

    @Override protected boolean isValidTreeSupport(BlockState state) {return super.isValidTreeSupport(state) || state.is(AerialHellBlocksAndItems.SLIPPERY_SAND.get());}

    private static class DeadGiantTrunk extends ClassicGiantTrunk
    {
        private final boolean largeTrunk;
        public DeadGiantTrunk(FeaturePlaceContext<DeadGiantTreeConfig> context, StraightLineParameters straightLineParams, int knotsNumber)
        {
            super(context, straightLineParams, knotsNumber, TRUNK_KNOTS_PARAMETERS, () -> context.config().trunkProvider().getState(context.random(), context.origin()).getBlock());
            this.largeTrunk = (context.config().trunkMaxVerticalOffset() + context.config().trunkMinVerticalOffset()) / 2 > 12;
        }

        private DeadGiantTreeConfig getContextConfig() {return (DeadGiantTreeConfig) context.config();}

        @Override protected boolean isLarge() {return this.largeTrunk;}

        @Override protected boolean tryPlacingBlock(BlockPos.MutableBlockPos pos)
        {
            if (context.random().nextFloat() < getContextConfig().randomChanceToNotPlaceBlock()) {return false;}
            else {return super.tryPlacingBlock(pos);}
        }

        @Override public BlockState getStateForPlacement(BlockPos pos)
        {
            return ((DeadGiantTreeConfig)context.config()).trunkProvider().getState(context.random(), pos);
        }

        @Override public BlockPos generate(boolean stopAtAnyObstacle, boolean generateDebug)
        {
            int i = 0, maxAbsOffset = FeatureHelper.getMaxAbsoluteXYZOffset(this.straightLineParams.getStart(), this.straightLineParams.getEnd());
            boolean shouldStop = false;
            List<BlockPos> insideTrunkPosList = new ArrayList<>();

            BlockPos.MutableBlockPos placementPos = this.straightLineParams.getStart().mutable();
            while(!placementPos.equals(this.straightLineParams.getEnd()) && i <= maxAbsOffset * straightLineParams.getPrecisionMultiplicator() && !shouldStop)
            {
                placementPos.set(getKnotsDeformedPos(getOffsetPosFromStart(i), this.getKnots(), this.getKnotsNumber(), this.getKnotsParameters()));
                boolean onePlaced = simulateTryPlacingBlock(placementPos, i, maxAbsOffset * straightLineParams.getPrecisionMultiplicator(), insideTrunkPosList);
                if (stopAtAnyObstacle && !onePlaced) {shouldStop = true;}
                i++;
            }

            if (insideTrunkPosList.isEmpty()) {return this.straightLineParams.getStart();}
            else
            {
                int yMeanOffset = (getContextConfig().trunkMinVerticalOffset() + getContextConfig().trunkMaxVerticalOffset()) / 2;
                int yCut = this.straightLineParams.getStart().getY() + yMeanOffset/2 + context.random().nextInt(-yMeanOffset/4, +yMeanOffset/4);
                for (BlockPos pos : insideTrunkPosList)
                {
                    if (isInsideBorder(pos, insideTrunkPosList) && pos.getY() <= yCut + context.random().nextInt(2))
                    {
                        tryPlacingBlock(pos.mutable());
                    }
                }
            }

            if (generateDebug) {this.generateDebug();}
            return new BlockPos(placementPos);
        }

        private boolean isInsideBorder(BlockPos pos, List<BlockPos> insidePosList)
        {
            return !insidePosList.contains(pos.north()) || !insidePosList.contains(pos.south()) || !insidePosList.contains(pos.west()) || !insidePosList.contains(pos.east()) || !insidePosList.contains(pos.above()) || !insidePosList.contains(pos.below());
        }

        private boolean simulateTryPlacingBlock(BlockPos.MutableBlockPos pos, int step, int maxStep, List<BlockPos> posListToFill)
        {
            if (this.isLarge())
            {
                if (step < maxStep / 8) {return simulateTryPlacingBlocksSphere(pos, 3, posListToFill);}
                else {return simulateTryPlacingBlocksSphere(pos, 2, posListToFill);}
            }
            else
            {
                if (step < maxStep / 8) {return simulateTryPlacingBlocksSphere(pos, 2, posListToFill);}
                else {return simulateTryPlacingBlocksCross(pos, posListToFill);}
            }
        }

        protected boolean simulateTryPlacingBlocksSphere(BlockPos.MutableBlockPos pos, int radius, List<BlockPos> posListToFill)
        {
            boolean onePlaced = false;
            BlockPos.MutableBlockPos placementPos = pos.mutable();
            for (int x=-radius; x<=radius; x++)
            {
                for (int y=-radius; y<=radius; y++)
                {
                    for (int z=-radius; z<=radius; z++)
                    {
                        if (x*x + y*y + z*z <= radius*radius)
                        {
                            placementPos.set(pos.offset(x,y,z));
                            onePlaced = simulateTryPlacingBlock(placementPos, posListToFill) || onePlaced;
                        }
                    }
                }
            }
            return onePlaced;
        }

        protected boolean simulateTryPlacingBlocksCross(BlockPos.MutableBlockPos pos, List<BlockPos> posListToFill)
        {
            boolean onePlaced = simulateTryPlacingBlock(pos, posListToFill);
            pos.move(1, 0, 0);
            onePlaced = simulateTryPlacingBlock(pos, posListToFill) || onePlaced;
            pos.move(-2, 0, 0);
            onePlaced = simulateTryPlacingBlock(pos, posListToFill) || onePlaced;
            pos.move(1, 1, 0);
            onePlaced = simulateTryPlacingBlock(pos, posListToFill) || onePlaced;
            pos.move(0, -2, 0);
            onePlaced = simulateTryPlacingBlock(pos, posListToFill) || onePlaced;
            pos.move(0, 1, 1);
            onePlaced = simulateTryPlacingBlock(pos, posListToFill) || onePlaced;
            pos.move(0, 0, -2);
            onePlaced = simulateTryPlacingBlock(pos, posListToFill) || onePlaced;
            pos.move(0, 0, 1);
            return onePlaced;
        }

        protected boolean simulateTryPlacingBlock(BlockPos.MutableBlockPos pos, List<BlockPos> posListToFill) //simulate the block placing, but instead of placing, puts the position in the list
        {
            WorldGenLevel level = context.level();
            BlockPos blockpos = new BlockPos(pos);
            if (isReplaceable(level, pos)) {if (!posListToFill.contains(blockpos)) {posListToFill.add(blockpos);} return true;}
            else {return false;}
        }
    }
}