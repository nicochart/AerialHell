package fr.factionbedrock.aerialhell.World.Features.GiantTree;

import com.mojang.serialization.Codec;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellConfiguredFeatures;
import fr.factionbedrock.aerialhell.Util.FeatureHelper;
import fr.factionbedrock.aerialhell.World.Features.Config.GiantPineTreeConfig;
import fr.factionbedrock.aerialhell.World.Features.Util.GiantTree.ClassicGiantTrunk;
import fr.factionbedrock.aerialhell.World.Features.Util.SplineKnots;
import fr.factionbedrock.aerialhell.World.Features.Util.SplineKnotsDeformedStraightLine;
import fr.factionbedrock.aerialhell.World.Features.Util.StraightLine;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.List;

public class GiantPineTreeFeature extends AbstractGiantTreeFeature<GiantPineTreeConfig>
{
    private static final SplineKnotsDeformedStraightLine.KnotsParameters TRUNK_KNOTS_PARAMETERS = new SplineKnots.KnotsParameters(8, 16, 0.3F, 5, 20);

    public GiantPineTreeFeature(Codec<GiantPineTreeConfig> codec) {super(codec);}

    @Override public List<RegistryKey<ConfiguredFeature<?, ?>>> getAssociatedConfiguredFeatures() {return AerialHellConfiguredFeatures.Lists.GIANT_PINE_TREE_LIST;}

    @Override public boolean generate(FeatureContext<GiantPineTreeConfig> context)
    {
        Random rand = context.getRandom(); GiantPineTreeConfig config = context.getConfig();
        BlockPos origin = context.getOrigin();

        if (!canPlace(context)) {return false;}
        else
        {
            int maxXZdistance=config.trunkMaxHorizontalOffset(), minYdistance=config.trunkMinVerticalOffset(), maxYdistance=config.trunkMaxVerticalOffset();
            BlockPos trunkStart = origin.down(2);
            int xOffset = rand.nextBetweenExclusive(-maxXZdistance, maxXZdistance), yOffset = rand.nextBetweenExclusive(minYdistance, maxYdistance), zOffset = rand.nextBetweenExclusive(-maxXZdistance, maxXZdistance);
            if (!FeatureHelper.isBelowMaxBuildHeight(context, context.getOrigin().up(yOffset))) {return false;}
            BlockPos trunkEnd = origin.add(xOffset, yOffset, zOffset);
            generate(context, trunkStart, trunkEnd, false);
            return true;
        }
    }

    protected void generate(FeatureContext<GiantPineTreeConfig> context, BlockPos startPos, BlockPos endPos, boolean generateDebug)
    {
        GiantPineTree pineTree = new GiantPineTree(context, new StraightLine.StraightLineParameters(startPos, endPos), 2 + context.getRandom().nextInt(2));
        pineTree.generate(false, generateDebug);
        pineTree = null;
    }

    private static class GiantPineTree extends ClassicGiantTrunk
    {
        private final boolean largeTrunk;
        public GiantPineTree(FeatureContext<GiantPineTreeConfig> context, StraightLineParameters straightLineParams, int knotsNumber)
        {
            super(context, straightLineParams, knotsNumber, TRUNK_KNOTS_PARAMETERS, () -> context.getConfig().trunkProvider().get(context.getRandom(), context.getOrigin()).getBlock());
            this.largeTrunk = (context.getConfig().trunkMaxVerticalOffset() + context.getConfig().trunkMinVerticalOffset()) / 2 > 30;
        }

        private FeatureContext<GiantPineTreeConfig> getContext() {return (FeatureContext<GiantPineTreeConfig>)context;}

        @Override protected boolean isLarge() {return this.largeTrunk;}

        @Override public BlockPos generate(boolean stopAtAnyObstacle, boolean generateDebug)
        {
            int i = 0, maxAbsOffset = FeatureHelper.getMaxAbsoluteXYZOffset(this.straightLineParams.getStart(), this.straightLineParams.getEnd());
            int branchCooldown = 5, branchDistance = getContext().getConfig().verticalBranchSeparation();

            BlockPos.Mutable placementPos = this.straightLineParams.getStart().mutableCopy();
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

            int xOffset = (int) (xzLargeFactor * factor * context.getRandom().nextBetweenExclusive(-maxXZoffset, maxXZoffset));
            int yOffset = (int) (yLargeFactor * factor * context.getRandom().nextBetweenExclusive(meanYoffset-1, meanYoffset+2));
            int zOffset = (int) (xzLargeFactor * factor * context.getRandom().nextBetweenExclusive(-maxXZoffset, maxXZoffset));
            StraightPineBranch branch = new StraightPineBranch(getContext(), new StraightLineParameters(startPos, startPos.add(xOffset, yOffset, zOffset)));
            branch.generate(true, false);
            branch = new StraightPineBranch(getContext(), new StraightLineParameters(startPos, startPos.add(-xOffset, yOffset, -zOffset)));
            branch.generate(true, false);

            for (int i=2; i<randomBranchCount; i++)
            {
                xOffset = (int) (xzLargeFactor * factor * context.getRandom().nextBetweenExclusive(-maxXZoffset, maxXZoffset));
                yOffset = (int) (yLargeFactor * factor * context.getRandom().nextBetweenExclusive(meanYoffset-1, meanYoffset+2));
                zOffset = (int) (xzLargeFactor * factor * context.getRandom().nextBetweenExclusive(-maxXZoffset, maxXZoffset));
                BlockPos endPos = startPos.add(xOffset, yOffset, zOffset);
                branch = new StraightPineBranch(getContext(), new StraightLineParameters(startPos, endPos));
                branch.generate(true, false);
            }
            branch = null;
        }

        private int getRandomBranchCount()
        {
            return (this.isLarge() ? getContext().getConfig().branchQuantity() / 2 : 0) + this.context.getRandom().nextBetweenExclusive(getContext().getConfig().branchQuantity() / 2, getContext().getConfig().branchQuantity());
        }

        @Override protected boolean tryPlacingBlocks(BlockPos.Mutable pos, int step, int maxStep)
        {
            if (step >= maxStep * 7 / 8) {return tryPlacingPineTopBlocks(pos, step >= maxStep * 15 / 16);}
            else {return super.tryPlacingBlocks(pos, step, maxStep);}
        }

        protected boolean tryPlacingPineTopBlocks(BlockPos.Mutable pos, boolean isVeryTop)
        {
            if (this.isLarge() && !isVeryTop) {return tryPlacingTopBlocksSphere(pos, 2);}
            else {return tryPlacingPineTopBlocksCross(pos);}
        }

        protected boolean tryPlacingPineTopBlocksCross(BlockPos.Mutable pos)
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

        protected boolean tryPlacingTopBlocksSphere(BlockPos.Mutable pos, int radius)
        {
            boolean onePlaced = false;
            BlockPos.Mutable placementPos = pos.mutableCopy();
            for (int x=-radius; x<=radius; x++)
            {
                for (int y=-radius; y<=radius; y++)
                {
                    for (int z=-radius; z<=radius; z++)
                    {
                        int p = x*x + y*y + z*z;
                        if (p <= radius*radius / 4)
                        {
                            placementPos.set(pos.add(x,y,z));
                            onePlaced = tryPlacingBlock(placementPos) || onePlaced;
                        }
                        else if (p <= radius*radius)
                        {
                            placementPos.set(pos.add(x,y,z));
                            onePlaced = tryPlacingLeavesBlock(placementPos) || onePlaced;
                        }
                    }
                }
            }
            return onePlaced;
        }

        protected boolean tryPlacingLeavesBlock(BlockPos.Mutable pos)
        {
            StructureWorldAccess world = context.getWorld();
            if (isReplaceableByLeaves(world, pos)) {world.setBlockState(pos, getLeavesStateForPlacement(pos), 2); return true;}
            else {return false;}
        }

        @Override public BlockState getStateForPlacement(BlockPos pos) {return ((GiantPineTreeConfig)context.getConfig()).trunkProvider().get(context.getRandom(), pos);}
        public BlockState getLeavesStateForPlacement(BlockPos pos) {return ((GiantPineTreeConfig)context.getConfig()).foliageProvider().get(context.getRandom(), pos).with(LeavesBlock.DISTANCE, 1);}

        protected boolean isReplaceableByLeaves(StructureWorldAccess reader, BlockPos blockPos)
        {
            BlockState previousBlock = reader.getBlockState(blockPos);
            return super.isReplaceable(reader, blockPos) || previousBlock.isIn(AerialHellTags.Blocks.STELLAR_DIRT);
        }
    }

    private static class StraightPineBranch extends StraightLine
    {
        private final boolean isLarge;
        public StraightPineBranch(FeatureContext<GiantPineTreeConfig> context, StraightLineParameters straightLineParams)
        {
            super(context, straightLineParams, () -> context.getConfig().trunkProvider().get(context.getRandom(), context.getOrigin()).getBlock());
            this.isLarge = (context.getConfig().trunkMaxVerticalOffset() + context.getConfig().trunkMinVerticalOffset()) / 2 > 22 && straightLineParams.getStart().getSquaredDistance(straightLineParams.getEnd()) > 64;
        }

        @Override protected boolean tryPlacingBlocks(BlockPos.Mutable pos, int step, int maxStep)
        {
            if (pos.getY() < context.getOrigin().getY()) {return false;}
            if (this.isLarge)
            {
                if (step < maxStep / 6) {return tryPlacingBlocksSphere(pos, 2);}
                else {return tryPlacingBlocksCross(pos);}
            }
            else {return tryPlacingBlocksCross(pos);}
        }

        @Override protected boolean tryPlacingBlocksCross(BlockPos.Mutable pos)
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

        @Override protected boolean tryPlacingBlocksSphere(BlockPos.Mutable pos, int radius)
        {
            boolean onePlaced = false;
            BlockPos.Mutable placementPos = pos.mutableCopy();
            for (int x=-radius; x<=radius; x++)
            {
                for (int y=-radius; y<=radius; y++)
                {
                    for (int z=-radius; z<=radius; z++)
                    {
                        int p = x*x + y*y + z*z;
                        if (p <= radius*radius / 4)
                        {
                            placementPos.set(pos.add(x,y,z));
                            onePlaced = tryPlacingBlock(placementPos) || onePlaced;
                        }
                        else if (p <= radius*radius)
                        {
                            placementPos.set(pos.add(x,y,z));
                            onePlaced = tryPlacingLeavesBlock(placementPos) || onePlaced;
                        }
                    }
                }
            }
            return onePlaced;
        }

        @Override protected boolean tryPlacingBlock(BlockPos.Mutable pos)
        {
            boolean isThisBlockAlready = context.getWorld().getBlockState(pos).isOf(getStateForPlacement(pos).getBlock()) || (this.generatePosList != null && this.generatePosList.contains(new BlockPos(pos)));
            boolean placed = super.tryPlacingBlock(pos);
            return placed || isThisBlockAlready;
        }

        protected boolean tryPlacingLeavesBlock(BlockPos.Mutable pos)
        {
            boolean isTreeBlockAlready = context.getWorld().getBlockState(pos).isOf(getStateForPlacement(pos).getBlock()) || context.getWorld().getBlockState(pos).isOf(getLeavesStateForPlacement(pos).getBlock());
            StructureWorldAccess world = context.getWorld(); boolean isPlaceable = isReplaceableByLeaves(world, pos);
            if (isPlaceable) world.setBlockState(pos, getLeavesStateForPlacement(pos), 2);
            return isPlaceable || isTreeBlockAlready;
        }

        @Override public BlockState getStateForPlacement(BlockPos pos) {return ((GiantPineTreeConfig)context.getConfig()).trunkProvider().get(context.getRandom(), pos);}
        public BlockState getLeavesStateForPlacement(BlockPos pos) {return ((GiantPineTreeConfig)context.getConfig()).foliageProvider().get(context.getRandom(), pos).with(LeavesBlock.DISTANCE, 1);}

        @Override protected boolean isReplaceable(StructureWorldAccess world, BlockPos blockPos)
        {
            BlockState previousBlock = world.getBlockState(blockPos);
            return super.isReplaceable(world, blockPos) || previousBlock.isIn(AerialHellTags.Blocks.LEAVES);
        }

        protected boolean isReplaceableByLeaves(StructureWorldAccess reader, BlockPos blockPos) {return super.isReplaceable(reader, blockPos);}
    }
}