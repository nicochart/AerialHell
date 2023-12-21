package fr.factionbedrock.aerialhell.World.Features;

import com.mojang.serialization.Codec;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Util.FeatureHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
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
		boolean canGenerate = true;

		boolean generatesInDungeon = FeatureHelper.isFeatureGeneratingNextToDungeon(context);

        //temporary
        BlockPos bridgeEnd = bridgeStart.offset(10 + rand.nextInt(10), 10 + rand.nextInt(10), 10 + rand.nextInt(10));

        if (canGenerate && !generatesInDungeon)
        {
        	generateBridge(reader, rand, bridgeStart, bridgeEnd);
        	return true;
        }
        return false;
    }
    
    protected void generateBridge(WorldGenLevel reader, RandomSource rand, BlockPos bridgeStart, BlockPos bridgeEnd)
    {
        int xRange = Math.abs(bridgeStart.getX() - bridgeEnd.getX());
        int yRange = Math.abs(bridgeStart.getY() - bridgeEnd.getY());
        int zRange = Math.abs(bridgeStart.getZ() - bridgeEnd.getZ());

        int maxRange = Math.max(Math.max(xRange, yRange), zRange);

        float xStep = (float) xRange / maxRange;
        float yStep = (float) yRange / maxRange;
        float zStep = (float) zRange / maxRange;

    	BlockPos.MutableBlockPos placementPos = new BlockPos.MutableBlockPos();
        placementPos.set(bridgeStart);

        int i = 0;
        while(!placementPos.equals(bridgeEnd))
        {
            BlockPos pos = new BlockPos((int) (i * xStep), (int) (i * yStep), (int) (i * zStep));
            placementPos.set(bridgeStart.offset(pos));
            if (isReplaceable(reader, placementPos))
            {
                reader.setBlock(placementPos, AerialHellBlocksAndItems.AERIAL_TREE_LOG.get().defaultBlockState(), 0);
            }
            i++;
        }
    }
    
    private boolean isReplaceable(WorldGenLevel reader, BlockPos blockPos)
    {
    	BlockState previousBlock = reader.getBlockState(blockPos);
    	if (previousBlock.isAir() || previousBlock.is(AerialHellTags.Blocks.FEATURE_CAN_REPLACE)) {return true;}
    	else {return false;}
    }
}