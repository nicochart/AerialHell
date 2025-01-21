package fr.factionbedrock.aerialhell.World.Features;

import com.mojang.serialization.Codec;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Util.FeatureHelper;
import fr.factionbedrock.aerialhell.World.Features.Util.SplineKnots;
import fr.factionbedrock.aerialhell.World.Features.Util.SplineKnotsDeformedStraightLine;
import fr.factionbedrock.aerialhell.World.Features.Util.StraightLine;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import javax.annotation.Nullable;

public class RootBridgeFeature extends Feature<NoneFeatureConfiguration>
{
    private final int MIN_ABS_XZ_OFFSET = 15, MAX_ABS_XZ_OFFSET = 22; //max bridge start-end xz distance from center of worldgen feature
    private final int MIN_ABS_Y_OFFSET = 5, MAX_ABS_Y_OFFSET = 15; //max bridge start-end y distance from center of worldgen feature

    private static final SplineKnots.KnotsParameters KNOTS_PARAMETERS = new SplineKnotsDeformedStraightLine.KnotsParameters(8, 16, 0.3F, 5, 20);

    public RootBridgeFeature(Codec<NoneFeatureConfiguration> codec) {super(codec);}

    @Override public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context)
    {
        WorldGenLevel reader = context.level(); RandomSource rand = context.random();
        BlockPos centerOfFeature = FeatureHelper.getFeatureCenter(context);

        boolean debugFlag = false;

        BlockPos bridgeStart = getRandomBridgeStart(reader, rand, centerOfFeature, 50);
        if (bridgeStart == null) {return false;}
        BlockPos bridgeEnd = getRandomFarBridgeEnd(reader, rand, centerOfFeature, bridgeStart, 50, rand.nextInt(64) == 0);
        if (bridgeEnd == null) {bridgeEnd = getRandomBridgeEnd(reader, rand, centerOfFeature, bridgeStart, 20, rand.nextInt(32) == 0);}
        if (bridgeEnd == null) {return false;}

        boolean isLongBridge = bridgeStart.distSqr(bridgeEnd) > 1024;
        boolean generatesInDungeon = FeatureHelper.isFeatureGeneratingNextToDungeon(context);

        if (!generatesInDungeon)
        {
            if (isLongBridge) {generateBridgeWithIntermediatePos(context, bridgeStart, bridgeEnd, debugFlag);}
            else {generateBridge(context, bridgeStart, bridgeEnd, debugFlag);}
        	return true;
        }
        return false;
    }

    protected void generateBridgeWithIntermediatePos(FeaturePlaceContext<NoneFeatureConfiguration> context, BlockPos bridgeStart, BlockPos bridgeEnd, boolean generateDebug)
    {
        BlockPos effectiveIntermediatePos1, effectiveIntermediatePos2, intermediatePos = getRandomIntermediatePos(context,  bridgeStart, bridgeEnd, 10);
        effectiveIntermediatePos1 = generateBridge(context, bridgeStart, intermediatePos, generateDebug);
        effectiveIntermediatePos2 = generateBridge(context, bridgeEnd, intermediatePos, generateDebug);
        StraightRootBridge line = new StraightRootBridge(context, new StraightLine.StraightLineParameters(effectiveIntermediatePos1, effectiveIntermediatePos2));
        line.generate(false, generateDebug);
        line = null;
    }

    protected BlockPos generateBridge(FeaturePlaceContext<NoneFeatureConfiguration> context, BlockPos bridgeStart, BlockPos bridgeEnd, boolean generateDebug)
    {
        RootBridge spline = new RootBridge(context, new StraightLine.StraightLineParameters(bridgeStart, bridgeEnd), 2 + (context.random().nextInt(8) == 0 ? 1 : 0));
        BlockPos lastPos = spline.generate(false, generateDebug);
        spline = null;
        return lastPos;
    }

    @Nullable protected BlockPos getRandomBridgeStart(WorldGenLevel reader, RandomSource rand, BlockPos centerOfFeature, int tries)
    {
        for (int t = 0; t < tries; t++)
        {
            int xOffset = getRandomOffset(rand, MIN_ABS_XZ_OFFSET, MAX_ABS_XZ_OFFSET);
            int yOffset = getRandomOffset(rand, MIN_ABS_Y_OFFSET, MAX_ABS_Y_OFFSET);
            int zOffset = getRandomOffset(rand, MIN_ABS_XZ_OFFSET, MAX_ABS_XZ_OFFSET);
            BlockPos potentialBridgeStart = centerOfFeature.offset(xOffset, yOffset, zOffset);
            if (isValidBridgeStartOrEnd(reader, potentialBridgeStart)) {return potentialBridgeStart;}
        }
        return null;
    }

    @Nullable protected BlockPos getRandomFarBridgeEnd(WorldGenLevel reader, RandomSource rand, BlockPos centerOfFeature, BlockPos bridgeStart, int tries, boolean forceFarBridge)
    {
        //if bridge start offset was positive, bridge end offset will be negative
        int xMinEndOffset, yMinEndOffset, zMinEndOffset, xMaxEndOffset, yMaxEndOffset, zMaxEndOffset;
        if (bridgeStart.getX() < centerOfFeature.getX()) {xMinEndOffset = MIN_ABS_XZ_OFFSET; xMaxEndOffset = MAX_ABS_XZ_OFFSET;}
        else {xMinEndOffset = -MAX_ABS_XZ_OFFSET; xMaxEndOffset = -MIN_ABS_XZ_OFFSET;}

        if (bridgeStart.getY() < centerOfFeature.getY()) {yMinEndOffset = MIN_ABS_Y_OFFSET; yMaxEndOffset = MAX_ABS_Y_OFFSET;}
        else {yMinEndOffset = -MAX_ABS_Y_OFFSET; yMaxEndOffset = -MIN_ABS_Y_OFFSET;}

        if (bridgeStart.getZ() < centerOfFeature.getZ()) {zMinEndOffset = MIN_ABS_XZ_OFFSET; zMaxEndOffset = MAX_ABS_XZ_OFFSET;}
        else {zMinEndOffset = -MAX_ABS_XZ_OFFSET; zMaxEndOffset = -MIN_ABS_XZ_OFFSET;}

        BlockPos potentialBridgeEnd = centerOfFeature;
        for (int t = 0; t < tries; t++)
        {
            potentialBridgeEnd = centerOfFeature.offset(rand.nextInt(xMinEndOffset, xMaxEndOffset), rand.nextInt(yMinEndOffset, yMaxEndOffset), rand.nextInt(zMinEndOffset, zMaxEndOffset));
            if (isValidBridgeStartOrEnd(reader, potentialBridgeEnd)) {return potentialBridgeEnd;}
        }
        return forceFarBridge ? potentialBridgeEnd : null;
    }

    @Nullable protected BlockPos getRandomBridgeEnd(WorldGenLevel reader, RandomSource rand, BlockPos centerOfFeature, BlockPos bridgeStart, int tries, boolean forceNonNullReturn)
    {
        BlockPos potentialBridgeEnd = centerOfFeature;
        for (int t = 0; t < tries; t++)
        {
            int xOffset = getRandomOffset(rand, MIN_ABS_XZ_OFFSET, MAX_ABS_XZ_OFFSET);
            int yOffset = getRandomOffset(rand, MIN_ABS_Y_OFFSET, MAX_ABS_Y_OFFSET);
            int zOffset = getRandomOffset(rand, MIN_ABS_XZ_OFFSET, MAX_ABS_XZ_OFFSET);
            potentialBridgeEnd = centerOfFeature.offset(xOffset, yOffset, zOffset);
            if (isValidBridgeStartOrEnd(reader, potentialBridgeEnd) && Math.sqrt(bridgeStart.distSqr(potentialBridgeEnd)) > 16) {return potentialBridgeEnd;}
        }
        return forceNonNullReturn ? potentialBridgeEnd : null;
    }

    protected BlockPos getRandomIntermediatePos(FeaturePlaceContext<NoneFeatureConfiguration> context, BlockPos bridgeStart, BlockPos bridgeEnd, int maxTries)
    {
        RandomSource rand = context.random();
        BlockPos intermediatePos, centerOfFeature = FeatureHelper.getFeatureCenter(context);
        if (rand.nextInt(10) == 0) {return centerOfFeature;}
        for (int i=0; i<maxTries; i++)
        {
            intermediatePos = bridgeStart.offset((bridgeEnd.getX() - bridgeStart.getX()) / 2, (bridgeEnd.getY() - bridgeStart.getY()) / 2, (bridgeEnd.getZ() - bridgeStart.getZ()) / 2)
                    .offset(getRandomOffset(rand, 2, 4), getRandomOffset(rand, 2, 4), getRandomOffset(rand, 2, 4));
            if (FeatureHelper.isBlockPosInFeatureRegion(centerOfFeature, intermediatePos)) {return intermediatePos;}
        }
        //can't find any intermediate pos in feature region
        return centerOfFeature;
    }

    private int getRandomOffset(RandomSource rand, int minAbs, int maxAbs)
    {
        int sign = rand.nextInt(2) == 0 ? -1 : 1;
        return sign * rand.nextInt(minAbs, maxAbs);
    }

    private boolean isValidBridgeStartOrEnd(WorldGenLevel reader, BlockPos pos) {return isValidSupportForBridge(reader.getBlockState(pos)) && thereIsAirAroundPosition(reader, pos);}
    private boolean isValidSupportForBridge(BlockState state) {return state.is(AerialHellTags.Blocks.STELLAR_STONE) || state.getBlock() == AerialHellBlocks.STELLAR_DIRT.get();}
    private boolean thereIsAirAroundPosition(WorldGenLevel reader, BlockPos pos)
    {
        for (int distance = 1; distance < 6; distance+=2)
        {
            if (thereIs3x3AirAreaAtPos(reader, pos.north(distance)) || thereIs3x3AirAreaAtPos(reader, pos.south(distance)) || thereIs3x3AirAreaAtPos(reader, pos.west(distance)) || thereIs3x3AirAreaAtPos(reader, pos.east(distance)) || thereIs3x3AirAreaAtPos(reader, pos.above(distance))) {return true;}
        }
        return false;
    }

    private boolean thereIs3x3AirAreaAtPos(WorldGenLevel reader, BlockPos pos)
    {
        for (int x=-1; x<=1; x++) {for (int y=-1; y<=1; y++) {for (int z=-1; z<=1; z++) {if (!reader.getBlockState(pos.offset(x, y, z)).isAir()) {return false;}}}} return true;
    }

    private static class StraightRootBridge extends StraightLine
    {
        public StraightRootBridge(FeaturePlaceContext<?> context, StraightLineParameters straightLineParams) {super(context, straightLineParams, () -> AerialHellBlocks.GIANT_ROOT.get());}

        @Override protected boolean isReplaceable(WorldGenLevel reader, BlockPos blockPos)
        {
            BlockState previousBlock = reader.getBlockState(blockPos);
            return super.isReplaceable(reader, blockPos) || previousBlock.is(AerialHellTags.Blocks.STELLAR_DIRT) || previousBlock.is(AerialHellBlocks.STELLAR_STONE.get());
        }
    }

    private static class RootBridge extends SplineKnotsDeformedStraightLine
    {
        public RootBridge(FeaturePlaceContext<?> context, StraightLineParameters straightLineParams, int knotsNumber) {super(context, straightLineParams, knotsNumber, KNOTS_PARAMETERS, () -> AerialHellBlocks.GIANT_ROOT.get());}

        @Override protected boolean isReplaceable(WorldGenLevel reader, BlockPos blockPos)
        {
            BlockState previousBlock = reader.getBlockState(blockPos);
            return super.isReplaceable(reader, blockPos) || previousBlock.is(AerialHellTags.Blocks.STELLAR_DIRT) || previousBlock.is(AerialHellBlocks.STELLAR_STONE.get());
        }
    }
}