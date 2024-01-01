package fr.factionbedrock.aerialhell.World.Features;

import com.mojang.serialization.Codec;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Util.FeatureHelper;
import fr.factionbedrock.aerialhell.World.Features.Util.Ellipsoid;
import fr.factionbedrock.aerialhell.World.Features.Util.SplineKnotsDeformedStraightLine;
import fr.factionbedrock.aerialhell.World.Features.Util.StraightLine;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class GiantTreeFeature extends Feature<NoneFeatureConfiguration>
{
    private static final SplineKnotsDeformedStraightLine.KnotsParameters KNOTS_PARAMETERS = new SplineKnotsDeformedStraightLine.KnotsParameters(8, 16, 0.3F, 5, 20);

    public GiantTreeFeature(Codec<NoneFeatureConfiguration> codec) {super(codec);}

    @Override public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context)
    {
        WorldGenLevel level = context.level(); RandomSource rand = context.random();
        BlockPos origin = context.origin();

        boolean generatesInDungeon = FeatureHelper.isFeatureGeneratingNextToDungeon(context);

        if (!isValidTreePos(level,origin) || generatesInDungeon) {return false;}
        else
        {
            int maxXZdistance=3, minYdistance=15, maxYdistance=25;
            BlockPos trunkStart = origin.below(2);
            BlockPos trunkEnd = origin.offset(rand.nextInt(-maxXZdistance, maxXZdistance), rand.nextInt(minYdistance, maxYdistance), rand.nextInt(-maxXZdistance, maxXZdistance));
            int yFoliageSize = (trunkEnd.getY() - trunkStart.getY()) / 4;
            BlockPos foliageCenter = generateTrunk(context, trunkStart, trunkEnd, false);
            generateFoliage(context, foliageCenter, (int) (yFoliageSize * 1.5), yFoliageSize);
            return true;
        }
    }

    protected BlockPos generateTrunk(FeaturePlaceContext<NoneFeatureConfiguration> context, BlockPos trunkStart, BlockPos trunkEnd, boolean generateDebug)
    {
        GiantTrunk trunkSpline = new GiantTrunk(context, new StraightLine.StraightLineParameters(trunkStart, trunkEnd), 2 + context.random().nextInt(2));
        BlockPos lastPos = trunkSpline.generate(generateDebug);
        trunkSpline = null;
        return lastPos;
    }

    protected void generateFoliage(FeaturePlaceContext<NoneFeatureConfiguration> context, BlockPos centerPos, int xzSize, int ySize)
    {
        GiantFoliage foliage = new GiantFoliage(context, createEllipsoidParameters(xzSize, ySize));
        foliage.generate(centerPos);
        foliage = null;
    }

    private boolean isValidTreePos(WorldGenLevel level, BlockPos pos) {return isValidTreeSupport(level.getBlockState(pos.below())) && level.isEmptyBlock(pos) && thereIsAirAbovePosition(level, pos);}
    private boolean isValidTreeSupport(BlockState state) {return state.is(AerialHellTags.Blocks.STELLAR_DIRT);}
    private boolean thereIsAirAbovePosition(WorldGenLevel level, BlockPos pos) {return thereIsAirColumnAbovePos(level, pos);}

    private boolean thereIsAirColumnAbovePos(WorldGenLevel reader, BlockPos pos)
    {
        for (int y=1; y<=8; y++) {if (!reader.getBlockState(pos.above(y)).isAir()) {return false;}} return true;
    }

    private static class GiantTrunk extends SplineKnotsDeformedStraightLine
    {
        public GiantTrunk(FeaturePlaceContext<?> context, StraightLineParameters straightLineParams, int knotsNumber) {super(context, straightLineParams, knotsNumber, KNOTS_PARAMETERS, () -> AerialHellBlocksAndItems.AERIAL_TREE_LOG.get());}

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
        return new Ellipsoid.EllipsoidParameters(xzSize, ySize, xzSize, - xzSize, xzSize, 0, ySize, - xzSize, xzSize, 1);
    }

    private static class GiantFoliage extends Ellipsoid
    {
        public GiantFoliage(FeaturePlaceContext<?> context, Ellipsoid.EllipsoidParameters parameters)
        {
            super(context, () -> AerialHellBlocksAndItems.AERIAL_TREE_LEAVES.get(), parameters, Ellipsoid.Types.CENTER_1x1);
        }

        @Override public BlockState getStateToPlace(BlockPos pos)
        {
            return AerialHellBlocksAndItems.AERIAL_TREE_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1);
        }
    }
}