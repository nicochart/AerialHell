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

public class RootBridgeFeature extends Feature<NoneFeatureConfiguration>
{
    public RootBridgeFeature(Codec<NoneFeatureConfiguration> codec) {super(codec);}

    @Override public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context)
    {
        BlockPos bridgeStart = context.origin(); WorldGenLevel reader = context.level(); RandomSource rand = context.random(); ChunkGenerator generator = context.chunkGenerator();
        boolean canGenerate =
                (reader.getBlockState(bridgeStart).is(AerialHellTags.Blocks.STELLAR_STONE) || reader.getBlockState(bridgeStart).getBlock() == AerialHellBlocksAndItems.STELLAR_DIRT.get()) &&
                (reader.getBlockState(bridgeStart.north(2)).getBlock().equals(Blocks.AIR) || reader.getBlockState(bridgeStart.south(2)).getBlock().equals(Blocks.AIR) || reader.getBlockState(bridgeStart.west(2)).getBlock().equals(Blocks.AIR) || reader.getBlockState(bridgeStart.east(2)).getBlock().equals(Blocks.AIR));


		boolean generatesInDungeon = FeatureHelper.isFeatureGeneratingNextToDungeon(context);

        int xzOffsetMinAbs = 10, xzOffsetMaxAbs = 22;
        int yOffsetMinAbs = 5, yOffsetMaxAbs = 15;
        int xOffset = getRandomOffset(rand, xzOffsetMinAbs, xzOffsetMaxAbs);
        int yOffset = getRandomOffset(rand, yOffsetMinAbs, yOffsetMaxAbs);
        int zOffset = getRandomOffset(rand, xzOffsetMinAbs, xzOffsetMaxAbs);
        BlockPos bridgeEnd = bridgeStart.offset(xOffset, yOffset, zOffset);

        if (canGenerate && !generatesInDungeon)
        {
        	generateBridge(reader, rand, bridgeStart, bridgeEnd);
        	return true;
        }
        return false;
    }
    
    protected void generateBridge(WorldGenLevel reader, RandomSource rand, BlockPos bridgeStart, BlockPos bridgeEnd)
    {
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

        //tmp, to check bridge end position.. (trying to find why I get infinite loops)
        reader.setBlock(bridgeEnd, AerialHellBlocksAndItems.ARSONIST_BLOCK.get().defaultBlockState(), 0);
    }

    private void tryPlacingRootBlock(WorldGenLevel reader, BlockPos.MutableBlockPos pos)
    {
        if (isReplaceable(reader, pos)) {reader.setBlock(pos, AerialHellBlocksAndItems.AERIAL_TREE_LOG.get().defaultBlockState(), 0);}
    }

    private int getRandomOffset(RandomSource rand, int minAbs, int maxAbs)
    {
        int sign = rand.nextInt(2) == 0 ? -1 : 1;
        return sign * (minAbs + rand.nextInt(maxAbs - minAbs));
    }

    private boolean isReplaceable(WorldGenLevel reader, BlockPos blockPos)
    {
    	BlockState previousBlock = reader.getBlockState(blockPos);
    	if (previousBlock.isAir() || previousBlock.is(AerialHellTags.Blocks.FEATURE_CAN_REPLACE)) {return true;}
    	else {return false;}
    }
}