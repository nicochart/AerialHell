package fr.factionbedrock.aerialhell.World.Features;

import com.mojang.serialization.Codec;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Util.FeatureHelper;
import fr.factionbedrock.aerialhell.World.Features.Config.GiantPineTreeConfig;
import fr.factionbedrock.aerialhell.World.Features.Util.GiantTree.ClassicGiantTrunk;
import fr.factionbedrock.aerialhell.World.Features.Util.SplineKnots;
import fr.factionbedrock.aerialhell.World.Features.Util.SplineKnotsDeformedStraightLine;
import fr.factionbedrock.aerialhell.World.Features.Util.StraightLine;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

public class GiantPineTreeFeature extends Feature<GiantPineTreeConfig>
{
    private static final SplineKnotsDeformedStraightLine.KnotsParameters TRUNK_KNOTS_PARAMETERS = new SplineKnots.KnotsParameters(8, 16, 0.3F, 5, 20);

    public GiantPineTreeFeature(Codec<GiantPineTreeConfig> codec) {super(codec);}

    @Override public boolean place(FeaturePlaceContext<GiantPineTreeConfig> context)
    {
        WorldGenLevel level = context.level(); RandomSource rand = context.random(); GiantPineTreeConfig config = context.config();
        BlockPos origin = context.origin();

        boolean generatesInDungeon = FeatureHelper.isFeatureGeneratingNextToDungeon(context);

        if (!isValidTreePos(level,origin) || context.origin().above(config.trunkMaxVerticalOffset()).getY() > context.level().getMaxBuildHeight() || generatesInDungeon) {return false;}
        else
        {
            int maxXZdistance=config.trunkMaxHorizontalOffset(), minYdistance=config.trunkMinVerticalOffset(), maxYdistance=config.trunkMaxVerticalOffset();
            BlockPos trunkStart = origin.below(2);
            int xOffset = rand.nextInt(-maxXZdistance, maxXZdistance), yOffset = rand.nextInt(minYdistance, maxYdistance), zOffset = rand.nextInt(-maxXZdistance, maxXZdistance);
            BlockPos trunkEnd = origin.offset(xOffset, yOffset, zOffset);
            generate(context, trunkStart, trunkEnd, false);
            return true;
        }
    }

    protected void generate(FeaturePlaceContext<GiantPineTreeConfig> context, BlockPos startPos, BlockPos endPos, boolean generateDebug)
    {
        GiantPineTree pineTree = new GiantPineTree(context, new StraightLine.StraightLineParameters(startPos, endPos), 2 + context.random().nextInt(2));
        pineTree.generate(false, generateDebug);
        pineTree = null;
    }

    private boolean isValidTreePos(WorldGenLevel level, BlockPos pos) {return isValidTreeSupport(level.getBlockState(pos.below())) && (level.isEmptyBlock(pos) || level.getBlockState(pos).is(AerialHellTags.Blocks.AERIALHELL_SAPLINGS)) && thereIsAirAbovePosition(level, pos);}
    private boolean isValidTreeSupport(BlockState state) {return state.is(AerialHellTags.Blocks.STELLAR_DIRT);}
    private boolean thereIsAirAbovePosition(WorldGenLevel level, BlockPos pos) {return thereIsAirColumnAbovePos(level, pos);}

    private boolean thereIsAirColumnAbovePos(WorldGenLevel reader, BlockPos pos)
    {
        for (int y=1; y<=8; y++) {if (!reader.getBlockState(pos.above(y)).isAir()) {return false;}} return true;
    }

    private static class GiantPineTree extends ClassicGiantTrunk
    {
        private final boolean largeTrunk;
        public GiantPineTree(FeaturePlaceContext<GiantPineTreeConfig> context, StraightLineParameters straightLineParams, int knotsNumber)
        {
            super(context, straightLineParams, knotsNumber, TRUNK_KNOTS_PARAMETERS, () -> context.config().trunkProvider().getState(context.random(), context.origin()).getBlock());
            this.largeTrunk = (context.config().trunkMaxVerticalOffset() + context.config().trunkMinVerticalOffset()) / 2 > 30;
        }

        private FeaturePlaceContext<GiantPineTreeConfig> getContext() {return (FeaturePlaceContext<GiantPineTreeConfig>)context;}

        @Override protected boolean isLarge() {return this.largeTrunk;}

        @Override public BlockPos generate(boolean stopAtAnyObstacle, boolean generateDebug)
        {
            int i = 0, maxAbsOffset = FeatureHelper.getMaxAbsoluteXYZOffset(this.straightLineParams.getStart(), this.straightLineParams.getEnd());
            int branchCooldown = 5, branchDistance = getContext().config().verticalBranchSeparation();

            BlockPos.MutableBlockPos placementPos = this.straightLineParams.getStart().mutable();
            while(!placementPos.equals(this.straightLineParams.getEnd()) && i <= maxAbsOffset * straightLineParams.getPrecisionMultiplicator())
            {
                placementPos.set(getKnotsDeformedPos(getOffsetPosFromStart(i), this.getKnots(), this.getKnotsNumber(), this.getKnotsParameters()));
                boolean onePlaced = tryPlacingBlocks(placementPos, i, maxAbsOffset * straightLineParams.getPrecisionMultiplicator());

                if (branchCooldown-- <= 0 && i <= maxAbsOffset * straightLineParams.getPrecisionMultiplicator() * 7 / 8)
                {
                    generateBranches(new BlockPos(placementPos), i, maxAbsOffset * straightLineParams.getPrecisionMultiplicator());
                    branchCooldown = branchDistance;
                }

                if (stopAtAnyObstacle && !onePlaced) {return placementPos;}
                i++;
            }
            if (generateDebug) {this.generateDebug();}
            return new BlockPos(placementPos);
        }

        private void generateBranches(BlockPos startPos, int step, int maxStep)
        {
            int maxXZoffset = 15, meanYoffset = -10;
            //float factor = 0.3F + 3 * (step > maxStep / 8 ? (float)(maxStep - step)/maxStep : (float)step/maxStep) / 4;
            float xzLargeFactor = this.isLarge() ? 1.2F : 1; float yLargeFactor = this.isLarge() ? 1.5F : 1;
            float factor = 0.25F + (step > maxStep / 8 ? (float)(maxStep - step)/maxStep : (float)step/maxStep) / 2;
            int randomBranchCount = getRandomBranchCount();

            int xOffset = (int) (xzLargeFactor * factor * context.random().nextInt(-maxXZoffset, maxXZoffset));
            int yOffset = (int) (yLargeFactor * factor * context.random().nextInt(meanYoffset-1, meanYoffset+2));
            int zOffset = (int) (xzLargeFactor * factor * context.random().nextInt(-maxXZoffset, maxXZoffset));
            StraightPineBranch branch = new StraightPineBranch(getContext(), new StraightLineParameters(startPos, startPos.offset(xOffset, yOffset, zOffset)));
            branch.generate(true, false);
            branch = new StraightPineBranch(getContext(), new StraightLineParameters(startPos, startPos.offset(-xOffset, yOffset, -zOffset)));
            branch.generate(true, false);

            for (int i=2; i<randomBranchCount; i++)
            {
                xOffset = (int) (xzLargeFactor * factor * context.random().nextInt(-maxXZoffset, maxXZoffset));
                yOffset = (int) (yLargeFactor * factor * context.random().nextInt(meanYoffset-1, meanYoffset+2));
                zOffset = (int) (xzLargeFactor * factor * context.random().nextInt(-maxXZoffset, maxXZoffset));
                BlockPos endPos = startPos.offset(xOffset, yOffset, zOffset);
                branch = new StraightPineBranch(getContext(), new StraightLineParameters(startPos, endPos));
                branch.generate(true, false);
            }
            branch = null;
        }

        private int getRandomBranchCount()
        {
            return (this.isLarge() ? getContext().config().branchQuantity() / 2 : 0) + this.context.random().nextInt(getContext().config().branchQuantity() / 2, getContext().config().branchQuantity());
        }

        @Override protected boolean tryPlacingBlocks(BlockPos.MutableBlockPos pos, int step, int maxStep)
        {
            if (step >= maxStep * 7 / 8) {return tryPlacingPineTopBlocks(pos, step >= maxStep * 15 / 16);}
            else {return super.tryPlacingBlocks(pos, step, maxStep);}
        }

        protected boolean tryPlacingPineTopBlocks(BlockPos.MutableBlockPos pos, boolean isVeryTop)
        {
            if (this.isLarge() && !isVeryTop) {return tryPlacingTopBlocksSphere(pos, 2);}
            else {return tryPlacingPineTopBlocksCross(pos);}
        }

        protected boolean tryPlacingPineTopBlocksCross(BlockPos.MutableBlockPos pos)
        {
            boolean onePlaced = tryPlacingBlock(pos);
            pos.move(1, 0, 0);
            onePlaced = tryPlacingLeavesBlock(pos) || onePlaced;
            pos.move(-2, 0, 0);
            onePlaced = tryPlacingLeavesBlock(pos) || onePlaced;
            pos.move(1, 1, 0);
            onePlaced = tryPlacingLeavesBlock(pos) || onePlaced;
            pos.move(0, -2, 0);
            onePlaced = tryPlacingLeavesBlock(pos) || onePlaced;
            pos.move(0, 1, 1);
            onePlaced = tryPlacingLeavesBlock(pos) || onePlaced;
            pos.move(0, 0, -2);
            onePlaced = tryPlacingLeavesBlock(pos) || onePlaced;
            pos.move(0, 0, 1);
            return onePlaced;
        }

        protected boolean tryPlacingTopBlocksSphere(BlockPos.MutableBlockPos pos, int radius)
        {
            boolean onePlaced = false;
            BlockPos.MutableBlockPos placementPos = pos.mutable();
            for (int x=-radius; x<=radius; x++)
            {
                for (int y=-radius; y<=radius; y++)
                {
                    for (int z=-radius; z<=radius; z++)
                    {
                        int p = x*x + y*y + z*z;
                        if (p <= radius*radius / 4)
                        {
                            placementPos.set(pos.offset(x,y,z));
                            onePlaced = tryPlacingBlock(placementPos) || onePlaced;
                        }
                        else if (p <= radius*radius)
                        {
                            placementPos.set(pos.offset(x,y,z));
                            onePlaced = tryPlacingLeavesBlock(placementPos) || onePlaced;
                        }
                    }
                }
            }
            return onePlaced;
        }

        protected boolean tryPlacingLeavesBlock(BlockPos.MutableBlockPos pos)
        {
            WorldGenLevel level = context.level();
            if (isReplaceableByLeaves(level, pos)) {level.setBlock(pos, getLeavesStateForPlacement(pos), 2); return true;}
            else {return false;}
        }

        @Override public BlockState getStateForPlacement(BlockPos pos) {return ((GiantPineTreeConfig)context.config()).trunkProvider().getState(context.random(), pos);}
        public BlockState getLeavesStateForPlacement(BlockPos pos) {return ((GiantPineTreeConfig)context.config()).foliageProvider().getState(context.random(), pos).setValue(LeavesBlock.DISTANCE, 1);}

        protected boolean isReplaceableByLeaves(WorldGenLevel reader, BlockPos blockPos)
        {
            BlockState previousBlock = reader.getBlockState(blockPos);
            return super.isReplaceable(reader, blockPos) || previousBlock.is(AerialHellTags.Blocks.STELLAR_DIRT);
        }
    }

    private static class StraightPineBranch extends StraightLine
    {
        private final boolean isLarge;
        public StraightPineBranch(FeaturePlaceContext<GiantPineTreeConfig> context, StraightLineParameters straightLineParams)
        {
            super(context, straightLineParams, () -> context.config().trunkProvider().getState(context.random(), context.origin()).getBlock());
            this.isLarge = (context.config().trunkMaxVerticalOffset() + context.config().trunkMinVerticalOffset()) / 2 > 22 && straightLineParams.getStart().distSqr(straightLineParams.getEnd()) > 64;
        }

        @Override protected boolean tryPlacingBlocks(BlockPos.MutableBlockPos pos, int step, int maxStep)
        {
            if (pos.getY() < context.origin().getY()) {return false;}
            if (this.isLarge)
            {
                if (step < maxStep / 6) {return tryPlacingBlocksSphere(pos, 2);}
                else {return tryPlacingBlocksCross(pos);}
            }
            else {return tryPlacingBlocksCross(pos);}
        }

        @Override protected boolean tryPlacingBlocksCross(BlockPos.MutableBlockPos pos)
        {
            boolean onePlaced = tryPlacingBlock(pos);
            pos.move(1, 0, 0);
            onePlaced = tryPlacingLeavesBlock(pos) || onePlaced;
            pos.move(-2, 0, 0);
            onePlaced = tryPlacingLeavesBlock(pos) || onePlaced;
            pos.move(1, 1, 0);
            onePlaced = tryPlacingLeavesBlock(pos) || onePlaced;
            pos.move(0, -2, 0);
            onePlaced = tryPlacingLeavesBlock(pos) || onePlaced;
            pos.move(0, 1, 1);
            onePlaced = tryPlacingLeavesBlock(pos) || onePlaced;
            pos.move(0, 0, -2);
            onePlaced = tryPlacingLeavesBlock(pos) || onePlaced;
            pos.move(0, 0, 1);
            return onePlaced;
        }

        @Override protected boolean tryPlacingBlocksSphere(BlockPos.MutableBlockPos pos, int radius)
        {
            boolean onePlaced = false;
            BlockPos.MutableBlockPos placementPos = pos.mutable();
            for (int x=-radius; x<=radius; x++)
            {
                for (int y=-radius; y<=radius; y++)
                {
                    for (int z=-radius; z<=radius; z++)
                    {
                        int p = x*x + y*y + z*z;
                        if (p <= radius*radius / 4)
                        {
                            placementPos.set(pos.offset(x,y,z));
                            onePlaced = tryPlacingBlock(placementPos) || onePlaced;
                        }
                        else if (p <= radius*radius)
                        {
                            placementPos.set(pos.offset(x,y,z));
                            onePlaced = tryPlacingLeavesBlock(placementPos) || onePlaced;
                        }
                    }
                }
            }
            return onePlaced;
        }

        @Override protected boolean tryPlacingBlock(BlockPos.MutableBlockPos pos)
        {
            boolean isThisBlockAlready = context.level().getBlockState(pos).is(getStateForPlacement(pos).getBlock());
            boolean placed = super.tryPlacingBlock(pos);
            return placed || isThisBlockAlready;
        }

        protected boolean tryPlacingLeavesBlock(BlockPos.MutableBlockPos pos)
        {
            boolean isTreeBlockAlready = context.level().getBlockState(pos).is(getStateForPlacement(pos).getBlock()) || context.level().getBlockState(pos).is(getLeavesStateForPlacement(pos).getBlock());
            WorldGenLevel level = context.level(); boolean isPlaceable = isReplaceableByLeaves(level, pos);
            if (isPlaceable) level.setBlock(pos, getLeavesStateForPlacement(pos), 2);
            return isPlaceable || isTreeBlockAlready;
        }

        @Override public BlockState getStateForPlacement(BlockPos pos) {return ((GiantPineTreeConfig)context.config()).trunkProvider().getState(context.random(), pos);}
        public BlockState getLeavesStateForPlacement(BlockPos pos) {return ((GiantPineTreeConfig)context.config()).foliageProvider().getState(context.random(), pos).setValue(LeavesBlock.DISTANCE, 1);}

        @Override protected boolean isReplaceable(WorldGenLevel level, BlockPos blockPos)
        {
            BlockState previousBlock = level.getBlockState(blockPos);
            return super.isReplaceable(level, blockPos) || previousBlock.is(AerialHellTags.Blocks.LEAVES);
        }

        protected boolean isReplaceableByLeaves(WorldGenLevel reader, BlockPos blockPos) {return super.isReplaceable(reader, blockPos);}
    }
}