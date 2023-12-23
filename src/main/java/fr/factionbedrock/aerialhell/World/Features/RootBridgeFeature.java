package fr.factionbedrock.aerialhell.World.Features;

import com.mojang.serialization.Codec;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Util.FeatureHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import javax.annotation.Nullable;

public class RootBridgeFeature extends Feature<NoneFeatureConfiguration>
{
    private final int MIN_ABS_XZ_OFFSET = 15, MAX_ABS_XZ_OFFSET = 23; //max bridge start-end xz distance from center of worldgen feature
    private final int MIN_ABS_Y_OFFSET = 5, MAX_ABS_Y_OFFSET = 15; //max bridge start-end y distance from center of worldgen feature

    public RootBridgeFeature(Codec<NoneFeatureConfiguration> codec) {super(codec);}

    @Override public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context)
    {
        WorldGenLevel reader = context.level(); RandomSource rand = context.random();
        BlockPos centerOfFeature = FeatureHelper.getFeatureCenter(context);

        BlockPos bridgeStart = getRandomBridgeStart(reader, rand, centerOfFeature, 10);
        if (bridgeStart == null) {return false;}
        BlockPos bridgeEnd = getRandomBridgeEnd(reader, rand, centerOfFeature, bridgeStart, 1);

        boolean generatesInDungeon = FeatureHelper.isFeatureGeneratingNextToDungeon(context);

        if (!generatesInDungeon)
        {
            generateBridge(context, bridgeStart, bridgeEnd);
            generateDebug(reader, bridgeStart, bridgeEnd, centerOfFeature);
        	return true;
        }
        return false;
    }

    protected void generateBridge(FeaturePlaceContext<NoneFeatureConfiguration> context, BlockPos bridgeStart, BlockPos bridgeEnd)
    {
        WorldGenLevel reader = context.level();
        int xOffset = bridgeEnd.getX() - bridgeStart.getX();
        int yOffset = bridgeEnd.getY() - bridgeStart.getY();
        int zOffset = bridgeEnd.getZ() - bridgeStart.getZ();

        int maxAbsOffset = Math.max(Math.max(Math.abs(xOffset), Math.abs(yOffset)), Math.abs(zOffset));

        float xStep = (float) xOffset / maxAbsOffset;
        float yStep = (float) yOffset / maxAbsOffset;
        float zStep = (float) zOffset / maxAbsOffset;

    	BlockPos.MutableBlockPos placementPos = new BlockPos.MutableBlockPos();
        placementPos.set(bridgeStart);

        int i = 0;
        while(!placementPos.equals(bridgeEnd) && i < maxAbsOffset) //i < maxAbsOffset is necessary atm, because sometimes I get infinite loops and crash, for unknown reason
        {
            BlockPos pos = new BlockPos((int) (i * xStep), (int) (i * yStep), (int) (i * zStep));
            placementPos.set(bridgeStart.offset(pos));
            tryPlacingRootBlock(reader, placementPos);
            placementPos.move(1, 0, 0);
            tryPlacingRootBlock(reader, placementPos);
            placementPos.move(-2, 0, 0);
            tryPlacingRootBlock(reader, placementPos);
            placementPos.move(1, 1, 0);
            tryPlacingRootBlock(reader, placementPos);
            placementPos.move(0, -2, 0);
            tryPlacingRootBlock(reader, placementPos);
            placementPos.move(0, 1, 1);
            tryPlacingRootBlock(reader, placementPos);
            placementPos.move(0, 0, -2);
            tryPlacingRootBlock(reader, placementPos);
            i++;
        }
    }

    protected void generateDebug(WorldGenLevel reader, BlockPos bridgeStart, BlockPos bridgeEnd, BlockPos centerOfFeature)
    {
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
    }

    private void tryPlacingRootBlock(WorldGenLevel reader, BlockPos.MutableBlockPos pos)
    {
        if (isReplaceable(reader, pos)) {reader.setBlock(pos, AerialHellBlocksAndItems.AERIAL_TREE_LOG.get().defaultBlockState(), 0);}
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

    protected BlockPos getRandomBridgeEnd(WorldGenLevel reader, RandomSource rand, BlockPos centerOfFeature, BlockPos bridgeStart, int tries)
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
        return potentialBridgeEnd;
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
        for (int distance = 1; distance < 4; distance++)
        {
            if (reader.getBlockState(pos.north(distance)).getBlock().equals(Blocks.AIR) || reader.getBlockState(pos.south(distance)).getBlock().equals(Blocks.AIR) || reader.getBlockState(pos.west(distance)).getBlock().equals(Blocks.AIR) || reader.getBlockState(pos.east(distance)).getBlock().equals(Blocks.AIR)) {return true;}
        }
        return false;
    }

    private boolean isReplaceable(WorldGenLevel reader, BlockPos blockPos)
    {
    	BlockState previousBlock = reader.getBlockState(blockPos);
        return previousBlock.isAir() || previousBlock.is(AerialHellTags.Blocks.FEATURE_CAN_REPLACE) || previousBlock.is(AerialHellBlocksAndItems.STELLAR_STONE.get());
    }
}