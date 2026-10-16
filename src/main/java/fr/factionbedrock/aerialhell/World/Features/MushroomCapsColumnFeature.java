package fr.factionbedrock.aerialhell.World.Features;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellConfiguredFeatures;
import fr.factionbedrock.aerialhell.Util.FeatureHelper;
import fr.factionbedrock.aerialhell.World.Features.GiantTree.AbstractGiantTreeFeature;
import fr.factionbedrock.aerialhell.World.Features.Util.Ellipsoid;
import fr.factionbedrock.aerialhell.World.Features.Util.GiantTree.ClassicGiantTrunk;
import fr.factionbedrock.aerialhell.World.Features.Util.SplineKnots;
import fr.factionbedrock.aerialhell.World.Features.Util.SplineKnotsDeformedStraightLine;
import fr.factionbedrock.aerialhell.World.Features.Util.StraightLine;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

import javax.annotation.Nullable;
import java.util.List;

public record MushroomCapsColumnFeature(int stemMaxHorizontalOffset, int stemMinVerticalOffset, int stemMaxVerticalOffset, int capMeanSize, int verticalCapSeparation, Holder<BlockStateProvider> stemProvider, Holder<BlockStateProvider> capProvider, Holder<BlockStateProvider> lightProvider) implements AbstractGiantTreeFeature
{
    private static final SplineKnotsDeformedStraightLine.KnotsParameters STEM_KNOTS_PARAMETERS = new SplineKnots.KnotsParameters(8, 16, 0.3F, 5, 20);

    public static final MapCodec<MushroomCapsColumnFeature> CODEC = RecordCodecBuilder.mapCodec(
        i -> i.group(
                ExtraCodecs.POSITIVE_INT.fieldOf("stem_max_horizontal_offset").forGetter(MushroomCapsColumnFeature::stemMaxHorizontalOffset),
                ExtraCodecs.POSITIVE_INT.fieldOf("stem_min_vertical_offset").forGetter(MushroomCapsColumnFeature::stemMinVerticalOffset),
                ExtraCodecs.POSITIVE_INT.fieldOf("stem_max_vertical_offset").forGetter(MushroomCapsColumnFeature::stemMaxVerticalOffset),
                ExtraCodecs.POSITIVE_INT.fieldOf("cap_mean_size").forGetter(MushroomCapsColumnFeature::capMeanSize),
                ExtraCodecs.POSITIVE_INT.fieldOf("vertical_cap_separation").forGetter(MushroomCapsColumnFeature::verticalCapSeparation),
                BlockStateProvider.CODEC.fieldOf("stem_provider").forGetter(MushroomCapsColumnFeature::stemProvider),
                BlockStateProvider.CODEC.fieldOf("cap_provider").forGetter(MushroomCapsColumnFeature::capProvider),
                BlockStateProvider.CODEC.fieldOf("light_provider").forGetter(MushroomCapsColumnFeature::lightProvider)
            )
            .apply(i, MushroomCapsColumnFeature::new)
    );

    @Override public MapCodec<MushroomCapsColumnFeature> codec() {return CODEC;}

    @Override public List<ResourceKey<Feature>> getAssociatedConfiguredFeatures() {return AerialHellConfiguredFeatures.Lists.MUSHROOM_CAPS_COLUMN_LIST;}

    @Override public boolean place(WorldGenLevel level, ChunkGenerator chunkGenerator, RandomSource random, BlockPos origin)
    {
        if (!canPlace(level, origin) || !this.isDungeonSensitiveValid(level, origin)) {return false;}
        else
        {
            int maxXZdistance=this.stemMaxHorizontalOffset, minYdistance=this.stemMinVerticalOffset, maxYdistance=this.stemMaxVerticalOffset;
            BlockPos trunkStart = origin.below(2);
            int xOffset = random.nextInt(-maxXZdistance, maxXZdistance), yOffset = random.nextInt(minYdistance, maxYdistance), zOffset = random.nextInt(-maxXZdistance, maxXZdistance);
            if (!FeatureHelper.isBelowMaxBuildHeight(level, origin.above(yOffset))) {return false;}
            BlockPos trunkEnd = origin.offset(xOffset, yOffset, zOffset);
            generate(level, random, origin, trunkStart, trunkEnd, false);
            return true;
        }
    }

    protected void generate(WorldGenLevel level, RandomSource random, BlockPos origin, BlockPos startPos, BlockPos endPos, boolean generateDebug)
    {
        GiantPineTree pineTree = new GiantPineTree(this, level, random, origin, new StraightLine.StraightLineParameters(startPos, endPos), 2 + random.nextInt(2));
        pineTree.generate(false, generateDebug);
        pineTree = null;
    }

    private static class GiantPineTree extends ClassicGiantTrunk
    {
        private final MushroomCapsColumnFeature feature;
        private final boolean largeTrunk;
        public GiantPineTree(MushroomCapsColumnFeature feature, WorldGenLevel level, RandomSource random, BlockPos origin, StraightLineParameters straightLineParams, int knotsNumber)
        {
            super(level, random, origin, straightLineParams, knotsNumber, STEM_KNOTS_PARAMETERS, () -> feature.stemProvider().value().getState(level, random, origin).getBlock());
            this.feature = feature;
            this.largeTrunk = (feature.stemMaxVerticalOffset() + feature.stemMinVerticalOffset()) / 2 > 30;
        }

        @Override protected boolean isLarge() {return this.largeTrunk;}

        @Override public BlockPos generate(boolean stopAtAnyObstacle, boolean generateDebug)
        {
            int i = 0, maxAbsOffset = FeatureHelper.getMaxAbsoluteXYZOffset(this.straightLineParams.getStart(), this.straightLineParams.getEnd());
            int capCooldown = this.feature.verticalCapSeparation() * 2, capDistance = this.feature.verticalCapSeparation();

            BlockPos.MutableBlockPos placementPos = this.straightLineParams.getStart().mutable();
            while(!placementPos.equals(this.straightLineParams.getEnd()) && i <= maxAbsOffset * straightLineParams.getPrecisionMultiplicator())
            {
                placementPos.set(getKnotsDeformedPos(getOffsetPosFromStart(i), this.getKnots(), this.getKnotsNumber(), this.getKnotsParameters()));
                boolean onePlaced = tryPlacingBlocks(placementPos, i, maxAbsOffset * straightLineParams.getPrecisionMultiplicator());

                if (capCooldown-- <= 0)
                {
                    generateCap(placementPos.immutable(), i, maxAbsOffset * straightLineParams.getPrecisionMultiplicator());
                    capCooldown = capDistance;
                }

                if (stopAtAnyObstacle && !onePlaced) {return placementPos;}
                i++;
            }
            if (generateDebug) {this.generateDebug();}
            return placementPos.immutable();
        }

        private void generateCap(BlockPos pos, int step, int maxStep)
        {
            float factor = 0.6F + 0.9F * (maxStep - step) / maxStep;

            int capRadius = (int) (factor * this.feature.capMeanSize());
            int capHeight = capRadius / 2;
            GiantCap cap = new GiantCap(this.feature, this.level, this.random, this.origin, createEllipsoidParameters(capRadius, capHeight, 1), pos);
            cap.generateOutsideBorder();
            cap.generateLight(1);
            cap = null;
        }

        private Ellipsoid.EllipsoidParameters createEllipsoidParameters(int xzSize, int ySize, int bonus)
        {
            return new Ellipsoid.EllipsoidParameters(xzSize, ySize, xzSize, - xzSize - bonus, xzSize + bonus, 0, ySize + bonus, - xzSize - bonus, xzSize + bonus);
        }

        @Override public BlockState getStateForPlacement(BlockPos pos) {return this.feature.stemProvider().value().getState(this.level, this.random, pos);}
    }

    private static class GiantCap extends Ellipsoid
    {
        private final MushroomCapsColumnFeature feature;

        public GiantCap(MushroomCapsColumnFeature feature, WorldGenLevel level, RandomSource random, BlockPos origin, Ellipsoid.EllipsoidParameters parameters, BlockPos centerPos)
        {
            super(level, random, origin, () -> feature.capProvider().value().getState(level, random, FeatureHelper.getFeatureCenter(origin)).getBlock(), parameters, centerPos, Ellipsoid.Types.CENTER_1x1);
            this.feature = feature;
        }

        public void generateLight(int number)
        {
            for (int i=0; i<number; i++)
            {
                BlockPos offset = findLightPos(25);
                if (offset != null)
                {
                    BlockPos generatePos = this.centerPos.offset(offset);
                    tryPlacingLightBlock(generatePos.mutable(), offset);
                }
            }
        }

        @Nullable public BlockPos findLightPos(int maxTries)
        {
            RandomSource rand = this.random;
            boolean foundPos = false; int i=0;
            while(!foundPos && i++ <= maxTries)
            {
                int x = rand.nextInt(ellipsoidParams.xForMin(), ellipsoidParams.xForMax());
                int y = rand.nextInt(ellipsoidParams.yForMin(), ellipsoidParams.yForMax());
                int z = rand.nextInt(ellipsoidParams.zForMin(), ellipsoidParams.zForMax());
                if (this.isPosAtEllipsoidInsideBorder(x, y, z))
                {
                    return new BlockPos(x, y, z);
                }
            }
            return null;
        }

        protected boolean tryPlacingLightBlock(BlockPos.MutableBlockPos placementPos, BlockPos ellipsoidPos) //ellipsoidPos = offset from centerPos
        {
            boolean isPlaceable = isReplaceable(this.level, placementPos);
            if (isPlaceable) {this.level.setBlock(placementPos, this.getLightStateForPlacement(ellipsoidPos), 2);}
            return isPlaceable;
        }

        @Override public BlockState getStateForPlacement(BlockPos pos)
        {
            return this.feature.capProvider().value().getState(this.level, this.random, pos);
        }

        public BlockState getLightStateForPlacement(BlockPos pos)
        {
            return this.feature.lightProvider().value().getState(this.level, this.random, pos);
        }
    }
}
