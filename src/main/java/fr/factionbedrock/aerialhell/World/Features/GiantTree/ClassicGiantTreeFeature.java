package fr.factionbedrock.aerialhell.World.Features.GiantTree;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellConfiguredFeatures;
import fr.factionbedrock.aerialhell.Util.FeatureHelper;
import fr.factionbedrock.aerialhell.World.Features.Util.*;
import fr.factionbedrock.aerialhell.World.Features.Util.GiantTree.ClassicGiantBranch;
import fr.factionbedrock.aerialhell.World.Features.Util.GiantTree.ClassicGiantFoliage;
import fr.factionbedrock.aerialhell.World.Features.Util.GiantTree.ClassicGiantTrunk;
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

public record ClassicGiantTreeFeature(int trunkMaxHorizontalOffset, int trunkMinVerticalOffset, int trunkMaxVerticalOffset, float xzFoliageSizeFactor, float yFoliageSizeFactor, Holder<BlockStateProvider> trunkProvider, Holder<BlockStateProvider> foliageProvider) implements AbstractGiantTreeFeature
{
    private static final SplineKnotsDeformedStraightLine.KnotsParameters TRUNK_KNOTS_PARAMETERS = new SplineKnots.KnotsParameters(8, 16, 0.3F, 5, 20);
    private static final SplineKnotsDeformedStraightLine.KnotsParameters FOLIAGE_KNOTS_PARAMETERS = new SplineKnots.KnotsParameters(8, 18, 0.4F, 6, 19);

    public static final MapCodec<ClassicGiantTreeFeature> CODEC = RecordCodecBuilder.mapCodec(
        i -> i.group(
                ExtraCodecs.POSITIVE_INT.fieldOf("trunk_max_horizontal_offset").forGetter(ClassicGiantTreeFeature::trunkMaxHorizontalOffset),
                ExtraCodecs.POSITIVE_INT.fieldOf("trunk_min_vertical_offset").forGetter(ClassicGiantTreeFeature::trunkMinVerticalOffset),
                ExtraCodecs.POSITIVE_INT.fieldOf("trunk_max_vertical_offset").forGetter(ClassicGiantTreeFeature::trunkMaxVerticalOffset),
                ExtraCodecs.POSITIVE_FLOAT.fieldOf("xz_foliage_size_factor").forGetter(ClassicGiantTreeFeature::xzFoliageSizeFactor),
                ExtraCodecs.POSITIVE_FLOAT.fieldOf("y_foliage_size_factor").forGetter(ClassicGiantTreeFeature::yFoliageSizeFactor),
                BlockStateProvider.CODEC.fieldOf("trunk_provider").forGetter(ClassicGiantTreeFeature::trunkProvider),
                BlockStateProvider.CODEC.fieldOf("foliage_provider").forGetter(ClassicGiantTreeFeature::foliageProvider)
            )
            .apply(i, ClassicGiantTreeFeature::new)
    );

    @Override public MapCodec<ClassicGiantTreeFeature> codec() {return CODEC;}

    @Override public List<ResourceKey<Feature>> getAssociatedConfiguredFeatures() {return AerialHellConfiguredFeatures.Lists.CLASSIC_GIANT_TREE_LIST;}

    @Override public boolean place(WorldGenLevel level, ChunkGenerator chunkGenerator, RandomSource random, BlockPos origin)
    {
        if (!canPlace(level, origin)) {return false;}
        else
        {
            int maxXZdistance=this.trunkMaxHorizontalOffset, minYdistance=this.trunkMinVerticalOffset, maxYdistance=this.trunkMaxVerticalOffset;
            BlockPos trunkStart = origin.below(2);
            int xOffset = random.nextInt(-maxXZdistance, maxXZdistance), yOffset = random.nextInt(minYdistance, maxYdistance), zOffset = random.nextInt(-maxXZdistance, maxXZdistance);
            BlockPos trunkEnd = origin.offset(xOffset, yOffset, zOffset);
            int yFoliageSize = getYFoliageSize(yOffset, minYdistance, maxYdistance);
            int xzFoliageSize = (int) (yFoliageSize * 1.6F);
            yFoliageSize = (int) (this.yFoliageSizeFactor * (float) yFoliageSize);
            xzFoliageSize = (int) (this.xzFoliageSizeFactor * (float) xzFoliageSize);
            if (!FeatureHelper.isBelowMaxBuildHeight(level, origin.above(yOffset + yFoliageSize/2))) {return false;}
            BlockPos foliageCenter = generateTrunk(level, random, origin, trunkStart, trunkEnd, false);
            if (this.yFoliageSizeFactor >= 1.3F) {foliageCenter = foliageCenter.below(this.yFoliageSizeFactor >= 1.5F ? 2 : 1);}
            generateFoliage(level, random, origin, foliageCenter, xzFoliageSize, yFoliageSize);
            generateBranches(level, random, origin, foliageCenter, xzFoliageSize, yFoliageSize);
            return true;
        }
    }

    private BlockPos generateTrunk(WorldGenLevel level, RandomSource random, BlockPos origin, BlockPos trunkStart, BlockPos trunkEnd, boolean generateDebug)
    {
        GiantTrunk trunkSpline = new GiantTrunk(this, level, random, origin, new StraightLine.StraightLineParameters(trunkStart, trunkEnd), 2 + random.nextInt(2));
        BlockPos lastPos = trunkSpline.generate(false, generateDebug);
        trunkSpline = null;
        return lastPos;
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

    private int getYFoliageSize(BlockPos trunkStart, BlockPos trunkEnd, int minTrunkHeight, int maxTrunkHeight) {return getYFoliageSize(trunkEnd.getY() - trunkStart.getY(), minTrunkHeight, maxTrunkHeight);}
    private int getYFoliageSize(int trunkHeight, int minTrunkHeight, int maxTrunkHeight) {return Math.max((minTrunkHeight + maxTrunkHeight) / 8 /*average divided by 4*/, trunkHeight / 4);}

    private boolean hasLargeTrunk() {return (this.trunkMaxVerticalOffset + this.trunkMinVerticalOffset) / 2 > 16;}

    private static class GiantTrunk extends ClassicGiantTrunk
    {
        private final ClassicGiantTreeFeature feature;
        public GiantTrunk(ClassicGiantTreeFeature feature, WorldGenLevel level, RandomSource random, BlockPos origin, StraightLineParameters straightLineParams, int knotsNumber)
        {
            super(level, random, origin, straightLineParams, knotsNumber, TRUNK_KNOTS_PARAMETERS, () -> feature.trunkProvider().value().getState(level, random, origin).getBlock());
            this.feature = feature;
        }

        @Override public BlockState getStateForPlacement(BlockPos pos)
        {
            return this.feature.trunkProvider().value().getState(this.level, this.random, pos);
        }

        @Override protected boolean isLarge() {return this.feature.hasLargeTrunk();}
    }

    private static class GiantFoliage extends ClassicGiantFoliage
    {
        private final ClassicGiantTreeFeature feature;
        public GiantFoliage(ClassicGiantTreeFeature feature, WorldGenLevel level, RandomSource random, BlockPos origin, Ellipsoid.EllipsoidParameters parameters, BlockPos centerPos, int knotsNumber)
        {
            super(level, random, origin, () -> feature.foliageProvider().value().getState(level, random, origin).getBlock(), parameters, centerPos, Ellipsoid.Types.CENTER_1x1, knotsNumber, FOLIAGE_KNOTS_PARAMETERS, true);
            this.feature = feature;
        }

        @Override public BlockState getStateForPlacement(BlockPos ellipsoidPos)
        {
            return this.feature.foliageProvider().value().getState(this.level, this.random, centerPos.offset(ellipsoidPos)).setValue(LeavesBlock.DISTANCE, getLeavesDistance(ellipsoidPos));
        }
    }

    private static class GiantBranch extends ClassicGiantBranch
    {
        private final ClassicGiantTreeFeature feature;

        public GiantBranch(ClassicGiantTreeFeature feature, WorldGenLevel level, RandomSource random, BlockPos origin, StraightLineParameters straightLineParams, int knotsNumber)
        {
            super(level, random, origin, straightLineParams, knotsNumber, TRUNK_KNOTS_PARAMETERS, () -> feature.trunkProvider().value().getState(level, random, origin).getBlock());
            this.feature = feature;
        }

        @Override protected boolean isLarge() {return this.feature.hasLargeTrunk();}

        @Override public BlockState getStateForPlacement(BlockPos pos)
        {
            return this.feature.trunkProvider().value().getState(this.level, this.random, pos);
        }
    }
}
