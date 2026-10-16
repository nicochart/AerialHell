package fr.factionbedrock.aerialhell.World.Features.GiantTree;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
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
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

import java.util.List;

public record GiantPineTreeFeature(int trunkMaxHorizontalOffset, int trunkMinVerticalOffset, int trunkMaxVerticalOffset, int branchQuantity, int verticalBranchSeparation, Holder<BlockStateProvider> trunkProvider, Holder<BlockStateProvider> foliageProvider) implements AbstractGiantTreeFeature
{
    private static final SplineKnotsDeformedStraightLine.KnotsParameters TRUNK_KNOTS_PARAMETERS = new SplineKnots.KnotsParameters(8, 16, 0.3F, 5, 20);

    public static final MapCodec<GiantPineTreeFeature> CODEC = RecordCodecBuilder.mapCodec(
        i -> i.group(
                ExtraCodecs.POSITIVE_INT.fieldOf("trunk_max_horizontal_offset").forGetter(GiantPineTreeFeature::trunkMaxHorizontalOffset),
                ExtraCodecs.POSITIVE_INT.fieldOf("trunk_min_vertical_offset").forGetter(GiantPineTreeFeature::trunkMinVerticalOffset),
                ExtraCodecs.POSITIVE_INT.fieldOf("trunk_max_vertical_offset").forGetter(GiantPineTreeFeature::trunkMaxVerticalOffset),
                ExtraCodecs.POSITIVE_INT.fieldOf("branch_quantity").forGetter(GiantPineTreeFeature::branchQuantity),
                ExtraCodecs.POSITIVE_INT.fieldOf("vertical_branch_separation").forGetter(GiantPineTreeFeature::verticalBranchSeparation),
                BlockStateProvider.CODEC.fieldOf("trunk_provider").forGetter(GiantPineTreeFeature::trunkProvider),
                BlockStateProvider.CODEC.fieldOf("foliage_provider").forGetter(GiantPineTreeFeature::foliageProvider)
            )
            .apply(i, GiantPineTreeFeature::new)
    );

    @Override public MapCodec<GiantPineTreeFeature> codec() {return CODEC;}

    @Override public List<ResourceKey<Feature>> getAssociatedConfiguredFeatures() {return AerialHellConfiguredFeatures.Lists.GIANT_PINE_TREE_LIST;}

    @Override public boolean place(WorldGenLevel level, ChunkGenerator chunkGenerator, RandomSource random, BlockPos origin)
    {
        if (!canPlace(level, origin)) {return false;}
        else
        {
            int maxXZdistance=this.trunkMaxHorizontalOffset, minYdistance=this.trunkMinVerticalOffset, maxYdistance=this.trunkMaxVerticalOffset;
            BlockPos trunkStart = origin.below(2);
            int xOffset = random.nextInt(-maxXZdistance, maxXZdistance), yOffset = random.nextInt(minYdistance, maxYdistance), zOffset = random.nextInt(-maxXZdistance, maxXZdistance);
            if (!FeatureHelper.isBelowMaxBuildHeight(level, origin.above(yOffset))) {return false;}
            BlockPos trunkEnd = origin.offset(xOffset, yOffset, zOffset);
            generate(level, random, origin, trunkStart, trunkEnd, false);
            return true;
        }
    }

    private void generate(WorldGenLevel level, RandomSource random, BlockPos origin, BlockPos startPos, BlockPos endPos, boolean generateDebug)
    {
        GiantPineTree pineTree = new GiantPineTree(this, level, random, origin, new StraightLine.StraightLineParameters(startPos, endPos), 2 + random.nextInt(2));
        pineTree.generate(false, generateDebug);
        pineTree = null;
    }

    private static class GiantPineTree extends ClassicGiantTrunk
    {
        private final GiantPineTreeFeature feature;
        private final boolean largeTrunk;
        public GiantPineTree(GiantPineTreeFeature feature, WorldGenLevel level, RandomSource random, BlockPos origin, StraightLineParameters straightLineParams, int knotsNumber)
        {
            super(level, random, origin, straightLineParams, knotsNumber, TRUNK_KNOTS_PARAMETERS, () -> feature.trunkProvider().value().getState(level, random, origin).getBlock());
            this.feature = feature;
            this.largeTrunk = (feature.trunkMaxVerticalOffset() + feature.trunkMinVerticalOffset()) / 2 > 30;
        }

        @Override protected boolean isLarge() {return this.largeTrunk;}

        @Override public BlockPos generate(boolean stopAtAnyObstacle, boolean generateDebug)
        {
            int i = 0, maxAbsOffset = FeatureHelper.getMaxAbsoluteXYZOffset(this.straightLineParams.getStart(), this.straightLineParams.getEnd());
            int branchCooldown = 5, branchDistance = this.feature.verticalBranchSeparation();

            BlockPos.MutableBlockPos placementPos = this.straightLineParams.getStart().mutable();
            while(!placementPos.equals(this.straightLineParams.getEnd()) && i <= maxAbsOffset * straightLineParams.getPrecisionMultiplicator())
            {
                placementPos.set(getKnotsDeformedPos(getOffsetPosFromStart(i), this.getKnots(), this.getKnotsNumber(), this.getKnotsParameters()));
                boolean onePlaced = tryPlacingBlocks(placementPos, i, maxAbsOffset * straightLineParams.getPrecisionMultiplicator());

                if (branchCooldown-- <= 0 && i <= maxAbsOffset * straightLineParams.getPrecisionMultiplicator() * 7 / 8)
                {
                    generateBranches(placementPos.immutable(), i, maxAbsOffset * straightLineParams.getPrecisionMultiplicator());
                    branchCooldown = branchDistance;
                }

                if (stopAtAnyObstacle && !onePlaced) {return placementPos;}
                i++;
            }
            if (generateDebug) {this.generateDebug();}
            return placementPos.immutable();
        }

        private void generateBranches(BlockPos startPos, int step, int maxStep)
        {
            int maxXZoffset = 15, meanYoffset = -10;
            //float factor = 0.3F + 3 * (step > maxStep / 8 ? (float)(maxStep - step)/maxStep : (float)step/maxStep) / 4;
            float xzLargeFactor = this.isLarge() ? 1.2F : 1; float yLargeFactor = this.isLarge() ? 1.5F : 1;
            float factor = 0.25F + (step > maxStep / 8 ? (float)(maxStep - step)/maxStep : (float)step/maxStep) / 2;
            int randomBranchCount = getRandomBranchCount();

            int xOffset = (int) (xzLargeFactor * factor * this.random.nextInt(-maxXZoffset, maxXZoffset));
            int yOffset = (int) (yLargeFactor * factor * this.random.nextInt(meanYoffset-1, meanYoffset+2));
            int zOffset = (int) (xzLargeFactor * factor * this.random.nextInt(-maxXZoffset, maxXZoffset));
            StraightPineBranch branch = new StraightPineBranch(this.feature, this.level, this.random, this.origin, new StraightLineParameters(startPos, startPos.offset(xOffset, yOffset, zOffset)));
            branch.generate(true, false);
            branch = new StraightPineBranch(this.feature, this.level, this.random, this.origin, new StraightLineParameters(startPos, startPos.offset(-xOffset, yOffset, -zOffset)));
            branch.generate(true, false);

            for (int i=2; i<randomBranchCount; i++)
            {
                xOffset = (int) (xzLargeFactor * factor * this.random.nextInt(-maxXZoffset, maxXZoffset));
                yOffset = (int) (yLargeFactor * factor * this.random.nextInt(meanYoffset-1, meanYoffset+2));
                zOffset = (int) (xzLargeFactor * factor * this.random.nextInt(-maxXZoffset, maxXZoffset));
                BlockPos endPos = startPos.offset(xOffset, yOffset, zOffset);
                branch = new StraightPineBranch(this.feature, this.level, this.random, this.origin, new StraightLineParameters(startPos, endPos));
                branch.generate(true, false);
            }
            branch = null;
        }

        private int getRandomBranchCount()
        {
            return (this.isLarge() ? this.feature.branchQuantity() / 2 : 0) + this.random.nextInt(this.feature.branchQuantity() / 2, this.feature.branchQuantity());
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
            if (isReplaceableByLeaves(this.level, pos)) {this.level.setBlock(pos, getLeavesStateForPlacement(pos), 2); return true;}
            else {return false;}
        }

        @Override public BlockState getStateForPlacement(BlockPos pos) {return this.feature.trunkProvider().value().getState(this.level, this.random, pos);}
        public BlockState getLeavesStateForPlacement(BlockPos pos) {return this.feature.foliageProvider().value().getState(this.level, this.random, pos).setValue(LeavesBlock.DISTANCE, 1);}

        protected boolean isReplaceableByLeaves(WorldGenLevel reader, BlockPos blockPos)
        {
            BlockState previousBlock = reader.getBlockState(blockPos);
            return super.isReplaceable(reader, blockPos) || previousBlock.is(AerialHellTags.Blocks.STELLAR_DIRT);
        }
    }

    private static class StraightPineBranch extends StraightLine
    {
        private final GiantPineTreeFeature feature;
        private final boolean isLarge;
        public StraightPineBranch(GiantPineTreeFeature feature, WorldGenLevel level, RandomSource random, BlockPos origin, StraightLineParameters straightLineParams)
        {
            super(level, random, origin, straightLineParams, () -> feature.trunkProvider().value().getState(level, random, origin).getBlock());
            this.feature = feature;
            this.isLarge = (feature.trunkMaxVerticalOffset() + feature.trunkMinVerticalOffset()) / 2 > 22 && straightLineParams.getStart().distSqr(straightLineParams.getEnd()) > 64;
        }

        @Override protected boolean tryPlacingBlocks(BlockPos.MutableBlockPos pos, int step, int maxStep)
        {
            if (pos.getY() < this.origin.getY()) {return false;}
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
            boolean isThisBlockAlready = this.level.getBlockState(pos).is(getStateForPlacement(pos).getBlock()) || (this.generatePosList != null && this.generatePosList.contains(pos.immutable()));
            boolean placed = super.tryPlacingBlock(pos);
            return placed || isThisBlockAlready;
        }

        protected boolean tryPlacingLeavesBlock(BlockPos.MutableBlockPos pos)
        {
            boolean isTreeBlockAlready = this.level.getBlockState(pos).is(getStateForPlacement(pos).getBlock()) || this.level.getBlockState(pos).is(getLeavesStateForPlacement(pos).getBlock());
            boolean isPlaceable = isReplaceableByLeaves(this.level, pos);
            if (isPlaceable) this.level.setBlock(pos, getLeavesStateForPlacement(pos), 2);
            return isPlaceable || isTreeBlockAlready;
        }

        @Override public BlockState getStateForPlacement(BlockPos pos) {return this.feature.trunkProvider().value().getState(this.level, this.random, pos);}
        public BlockState getLeavesStateForPlacement(BlockPos pos) {return this.feature.foliageProvider().value().getState(this.level, this.random, pos).setValue(LeavesBlock.DISTANCE, 1);}

        @Override protected boolean isReplaceable(WorldGenLevel world, BlockPos blockPos)
        {
            BlockState previousBlock = world.getBlockState(blockPos);
            return super.isReplaceable(world, blockPos) || previousBlock.is(AerialHellTags.Blocks.LEAVES);
        }

        protected boolean isReplaceableByLeaves(WorldGenLevel reader, BlockPos blockPos) {return super.isReplaceable(reader, blockPos);}
    }
}