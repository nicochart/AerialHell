package fr.factionbedrock.aerialhell.World.Features;

import com.mojang.serialization.Codec;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Util.FeatureHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import org.joml.Vector3f;

import javax.annotation.Nullable;

public class RootBridgeFeature extends Feature<NoneFeatureConfiguration>
{
    private final int MIN_ABS_XZ_OFFSET = 15, MAX_ABS_XZ_OFFSET = 23; //max bridge start-end xz distance from center of worldgen feature
    private final int MIN_ABS_Y_OFFSET = 5, MAX_ABS_Y_OFFSET = 15; //max bridge start-end y distance from center of worldgen feature

    private final int KNOT_MIN_DISTANCE_TO_STRAIGHT_BRIDGE = 8, KNOT_MAX_DISTANCE_TO_STRAIGHT_BRIDGE = 16; //min and max distance to bridge before deformation
    private final float KNOT_DEFORMATION_MULTIPLIER = 0.3F;
    private final int KNOT_DEFORMATION_MIN_DISTANCE = 5, KNOT_DEFORMATION_MAX_DISTANCE = 20; //max = distance at which deformation start, min = distance at which deformation is maximum

    public RootBridgeFeature(Codec<NoneFeatureConfiguration> codec) {super(codec);}

    @Override public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context)
    {
        WorldGenLevel reader = context.level(); RandomSource rand = context.random();
        BlockPos centerOfFeature = FeatureHelper.getFeatureCenter(context);


        BlockPos bridgeStart = getRandomBridgeStart(reader, rand, centerOfFeature, 50);
        if (bridgeStart == null) {return false;}
        BlockPos bridgeEnd = getRandomFarBridgeEnd(reader, rand, centerOfFeature, bridgeStart, 50, rand.nextInt(64) == 0);
        if (bridgeEnd == null) {bridgeEnd = getRandomBridgeEnd(reader, rand, centerOfFeature, bridgeStart, 20, rand.nextInt(32) == 0);}
        if (bridgeEnd == null) {return false;}

        boolean isLongBridge = bridgeStart.distSqr(bridgeEnd) > 1024;
        boolean generatesInDungeon = FeatureHelper.isFeatureGeneratingNextToDungeon(context);

        if (!generatesInDungeon)
        {
            if (isLongBridge)
            {
                BlockPos intermediatePos = getRandomIntermediatePos(context,  bridgeStart, bridgeEnd, 10);
                generateBridge(context, bridgeStart, intermediatePos);
                generateBridge(context, intermediatePos, bridgeEnd);
            }
            else
            {
                generateBridge(context, bridgeStart, bridgeEnd);
            }
        	return true;
        }
        return false;
    }

    protected void generateBridge(FeaturePlaceContext<NoneFeatureConfiguration> context, BlockPos bridgeStart, BlockPos bridgeEnd)
    {
        WorldGenLevel reader = context.level();
        Vector3f moveStepVector = getPlacementStepMoveVector(bridgeStart, bridgeEnd);
        int i = 0, maxAbsOffset = FeatureHelper.getMaxAbsoluteXYZOffset(bridgeStart, bridgeEnd);

        BlockPos knot1 = getRandomKnot(context, bridgeStart, bridgeEnd, 15), knot2 = getRandomKnot(context, bridgeStart, bridgeEnd, 15); //spline knots

    	BlockPos.MutableBlockPos placementPos = new BlockPos.MutableBlockPos();
        placementPos.set(bridgeStart);
        while(!placementPos.equals(bridgeEnd) && i < maxAbsOffset)
        {
            BlockPos pos = new BlockPos((int) (i * moveStepVector.x), (int) (i * moveStepVector.y), (int) (i * moveStepVector.z));
            placementPos.set(bridgeStart.offset(pos));

            Vector3f knotsDeformationVector = getKnotsDeformationVector(placementPos, knot1, knot2);
            placementPos.move((int) knotsDeformationVector.x, (int) knotsDeformationVector.y, (int) knotsDeformationVector.z);
            tryPlacingRootBlocks(reader, placementPos);
            i++;
        }

        generateDebug(context, bridgeStart, bridgeEnd, knot1, knot2);
    }

    protected void generateDebug(FeaturePlaceContext<NoneFeatureConfiguration> context, BlockPos bridgeStart, BlockPos bridgeEnd, BlockPos knot1, BlockPos knot2)
    {
        WorldGenLevel reader = context.level();
        BlockPos centerOfFeature = FeatureHelper.getFeatureCenter(context);
        for (int i=-50; i<=50; i++)
        {
            reader.setBlock(centerOfFeature.offset(i, 0, 0), AerialHellBlocksAndItems.RED_SLIPPERY_SAND_GLASS.get().defaultBlockState(), 0);
            reader.setBlock(centerOfFeature.offset(0, i, 0), AerialHellBlocksAndItems.RED_SLIPPERY_SAND_GLASS.get().defaultBlockState(), 0);
            reader.setBlock(centerOfFeature.offset(0, 0, i), AerialHellBlocksAndItems.RED_SLIPPERY_SAND_GLASS.get().defaultBlockState(), 0);
        }

        //tmp, to check feature center
        for (int x=-1; x<=1; x++) {for (int y=-1; y<=1; y++) {for (int z=-1; z<=1; z++) {reader.setBlock(centerOfFeature.offset(x, y, z), AerialHellBlocksAndItems.ARSONIST_BLOCK.get().defaultBlockState(), 0);}}}
        //tmp, to check bridge start and end position
        reader.setBlock(bridgeStart, AerialHellBlocksAndItems.ARSONIST_BLOCK.get().defaultBlockState(), 0);
        reader.setBlock(bridgeEnd, AerialHellBlocksAndItems.ARSONIST_BLOCK.get().defaultBlockState(), 0);

        //tmp, to check spline knots position
        reader.setBlock(knot1, AerialHellBlocksAndItems.VIBRANT_SKY_CACTUS_FIBER_LANTERN.get().defaultBlockState(), 0); //TMP
        reader.setBlock(knot2, AerialHellBlocksAndItems.VIBRANT_SKY_CACTUS_FIBER_LANTERN.get().defaultBlockState(), 0); //TMP
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

    protected BlockPos getRandomKnot(FeaturePlaceContext<NoneFeatureConfiguration> context, BlockPos bridgeStart, BlockPos bridgeEnd, int maxTries)
    {
        RandomSource rand = context.random();
        BlockPos centerOfFeature = FeatureHelper.getFeatureCenter(context);
        Vector3f moveStepVector = getPlacementStepMoveVector(bridgeStart, bridgeEnd);
        int i=0, maxAbsOffset = FeatureHelper.getMaxAbsoluteXYZOffset(bridgeStart, bridgeEnd);

        BlockPos knot;
        while (i++ < maxTries)
        {
            int randomStep = maxAbsOffset > 1 ? rand.nextInt(1, maxAbsOffset) : 1;
            int sign = rand.nextInt(2) == 0 ? -1 : 1;
            int knotOffsetFromBridge = sign * rand.nextInt(KNOT_MIN_DISTANCE_TO_STRAIGHT_BRIDGE , KNOT_MAX_DISTANCE_TO_STRAIGHT_BRIDGE);
            Vector3f offsetVector = FeatureHelper.getRandomOrthogonalVectorToLineDefinedWith2Points(bridgeStart, bridgeEnd, rand).normalize(knotOffsetFromBridge);
            knot = bridgeStart.offset((int) (randomStep * moveStepVector.x), (int) (randomStep * moveStepVector.y), (int) (randomStep * moveStepVector.z))
                    .offset((int) offsetVector.x, (int) offsetVector.y, (int) offsetVector.z);
            if (FeatureHelper.isBlockPosInFeatureRegion(centerOfFeature, knot)) {return knot;}
        }
        //can't find any knot in feature region
        return centerOfFeature;
    }

    private Vector3f getPlacementStepMoveVector(BlockPos bridgeStart, BlockPos bridgeEnd)
    {
        int maxAbsOffset = FeatureHelper.getMaxAbsoluteXYZOffset(bridgeStart, bridgeEnd);

        float xStep = (float) (bridgeEnd.getX() - bridgeStart.getX()) / maxAbsOffset;
        float yStep = (float) (bridgeEnd.getY() - bridgeStart.getY()) / maxAbsOffset;
        float zStep = (float) (bridgeEnd.getZ() - bridgeStart.getZ()) / maxAbsOffset;
        return new Vector3f(xStep, yStep, zStep);
    }

    private Vector3f getKnotsDeformationVector(BlockPos pos, BlockPos knot1, BlockPos knot2)
    {
        Vector3f deformationVector1 = getKnotDeformationVector(pos, knot1);
        Vector3f deformationVector2 = getKnotDeformationVector(pos, knot2);
        return new Vector3f(deformationVector1.x + deformationVector2.x, deformationVector1.y + deformationVector2.y, deformationVector1.z + deformationVector2.z);
    }

    private Vector3f getKnotDeformationVector(BlockPos pos, BlockPos knot)
    {
        float knotDeformationFactor = getKnotDeformationFactor((float) Math.sqrt(pos.distSqr(knot)));
        return new Vector3f(knot.getX() - pos.getX(), knot.getY() - pos.getY(), knot.getZ() - pos.getZ()).normalize(knotDeformationFactor * KNOT_DEFORMATION_MULTIPLIER);
    }

    private float getKnotDeformationFactor(float distanceToKnot)
    {
        if (distanceToKnot <= KNOT_DEFORMATION_MIN_DISTANCE) {return KNOT_DEFORMATION_MAX_DISTANCE - KNOT_DEFORMATION_MIN_DISTANCE;}
        else if (distanceToKnot <= KNOT_DEFORMATION_MAX_DISTANCE) {return KNOT_DEFORMATION_MAX_DISTANCE - distanceToKnot;}
        else /*if (distanceToKnot > KNOT_DEFORMATION_MAX_DISTANCE)*/ {return 0;}
    }

    private int getRandomOffset(RandomSource rand, int minAbs, int maxAbs)
    {
        int sign = rand.nextInt(2) == 0 ? -1 : 1;
        return sign * rand.nextInt(minAbs, maxAbs);
    }

    private boolean isValidBridgeStartOrEnd(WorldGenLevel reader, BlockPos pos) {return isValidSupportForBridge(reader.getBlockState(pos)) && thereIsAirAroundPosition(reader, pos);}
    private boolean isValidSupportForBridge(BlockState state) {return state.is(AerialHellTags.Blocks.STELLAR_STONE) || state.getBlock() == AerialHellBlocksAndItems.STELLAR_DIRT.get();}
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

    private void tryPlacingRootBlocks(WorldGenLevel reader, BlockPos.MutableBlockPos pos)
    {
        tryPlacingRootBlock(reader, pos);
        pos.move(1, 0, 0);
        tryPlacingRootBlock(reader, pos);
        pos.move(-2, 0, 0);
        tryPlacingRootBlock(reader, pos);
        pos.move(1, 1, 0);
        tryPlacingRootBlock(reader, pos);
        pos.move(0, -2, 0);
        tryPlacingRootBlock(reader, pos);
        pos.move(0, 1, 1);
        tryPlacingRootBlock(reader, pos);
        pos.move(0, 0, -2);
        tryPlacingRootBlock(reader, pos);
    }

    private void tryPlacingRootBlock(WorldGenLevel reader, BlockPos.MutableBlockPos pos)
    {
        if (isReplaceable(reader, pos)) {reader.setBlock(pos, AerialHellBlocksAndItems.AERIAL_TREE_LOG.get().defaultBlockState(), 0);}
    }

    private boolean isReplaceable(WorldGenLevel reader, BlockPos blockPos)
    {
    	BlockState previousBlock = reader.getBlockState(blockPos);
        return previousBlock.isAir() || previousBlock.is(AerialHellTags.Blocks.FEATURE_CAN_REPLACE) || previousBlock.is(AerialHellBlocksAndItems.STELLAR_STONE.get());
    }
}