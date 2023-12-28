package fr.factionbedrock.aerialhell.World.Features;

import com.mojang.serialization.Codec;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Util.FeatureHelper;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class SlipperySandFeature extends Feature<NoneFeatureConfiguration>
{

    public SlipperySandFeature(Codec<NoneFeatureConfiguration> codec) {super(codec);}

    @Override public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context)
    {
        BlockPos blockPos = context.origin(); WorldGenLevel reader = context.level(); RandomSource rand = context.random(); ChunkGenerator generator = context.chunkGenerator();
		boolean canGenerate = (
                (reader.getBlockState(blockPos.north(3)).getBlock().equals(Blocks.AIR) || reader.getBlockState(blockPos.south(3)).getBlock().equals(Blocks.AIR) || reader.getBlockState(blockPos.west(3)).getBlock().equals(Blocks.AIR) || reader.getBlockState(blockPos.east(3)).getBlock().equals(Blocks.AIR)) &&
                (reader.getBlockState(blockPos).is(AerialHellTags.Blocks.STELLAR_STONE) || reader.getBlockState(blockPos).getBlock() == AerialHellBlocksAndItems.STELLAR_DIRT.get()));

		boolean generatesInDungeon = FeatureHelper.isFeatureGeneratingNextToDungeon(context);
		
        if (canGenerate && !generatesInDungeon)
        {
        	generateSlipperySand(reader, rand, blockPos);
        	return true;
        }
        return false;
    }
    
    protected void generateSlipperySand(WorldGenLevel reader, RandomSource rand, BlockPos blockPos)
    {
    	BlockPos.MutableBlockPos placementPos = new BlockPos.MutableBlockPos();
    	boolean isBig = rand.nextDouble() > 0.8;
        int radiusX = getRandomRadius(rand, isBig);
        int radiusY = 2;
        int radiusZ = getRandomRadius(rand, isBig);
        int offsetY = rand.nextInt(6) == 0 ? 1 : 0;
        
        /*Slippery Sand placement*/
        for(int x = -radiusX; x <= radiusX; x++)
        {
        	for (int y = 0; y <= radiusY; y++)
        	{
        		for(int z = -radiusZ; z <= radiusZ; z++)
                {
                    BlockPos pos = new BlockPos(x, y, z);
                    if (isPosInsideEllipsis(pos, radiusX, radiusY, radiusZ))
                    {
                    	placementPos.set(blockPos.offset(pos));
                    	if (isReplaceable(reader, placementPos))
                    	{
                    		boolean isInsideInnerEllipsis = isPosInsideEllipsis(pos, radiusX-1, radiusY, radiusZ-1);
                    		if (isInsideInnerEllipsis) {reader.setBlock(placementPos, AerialHellBlocksAndItems.SLIPPERY_SAND.get().defaultBlockState(), 0);}
                    		else if (!(rand.nextInt(3) == 0)) {reader.setBlock(placementPos, AerialHellBlocksAndItems.SLIPPERY_SAND.get().defaultBlockState(), 0);}
                    	}
                    }
                }
        	}
        }
    }
    
    private boolean isReplaceable(WorldGenLevel reader, BlockPos blockPos)
    {
    	BlockState previousBlock = reader.getBlockState(blockPos);
    	if (previousBlock.isAir() || previousBlock.is(AerialHellTags.Blocks.FEATURE_CAN_REPLACE) || previousBlock.is(AerialHellTags.Blocks.STELLAR_DIRT)) {return true;}
    	else {return false;}
    }
    
    private int getRandomRadius(RandomSource rand, boolean isBig)
    {
    	return isBig ? (int) (5 + rand.nextFloat() * 5) : (int) (3 + rand.nextFloat() * 4);
    }
    
    private boolean isPosInsideEllipsis(BlockPos pos, float a, float b, float c)
    {
        float x = pos.getX() - 0.5F;
        float y = pos.getY();
        float z = pos.getZ() - 0.5F;
        return x*x/(a*a) + y*y/(b*b) + z*z/(c*c) < 1.0F;
    }
}