package fr.factionbedrock.aerialhell.World.Features;

import com.mojang.serialization.Codec;


import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Util.FeatureHelper;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class SlipperySandFeature extends Feature<DefaultFeatureConfig>
{

    public SlipperySandFeature(Codec<DefaultFeatureConfig> codec) {super(codec);}

    @Override public boolean generate(FeatureContext<DefaultFeatureConfig> context)
    {
        BlockPos blockPos = context.getOrigin(); StructureWorldAccess reader = context.getWorld(); Random rand = context.getRandom(); ChunkGenerator generator = context.chunkGenerator();
		boolean canGenerate = (
                (reader.getBlockState(blockPos.north(3)).getBlock().equals(Blocks.AIR) || reader.getBlockState(blockPos.south(3)).getBlock().equals(Blocks.AIR) || reader.getBlockState(blockPos.west(3)).getBlock().equals(Blocks.AIR) || reader.getBlockState(blockPos.east(3)).getBlock().equals(Blocks.AIR)) &&
                (reader.getBlockState(blockPos).isIn(AerialHellTags.Blocks.STELLAR_STONE) || reader.getBlockState(blockPos).getBlock() == AerialHellBlocks.STELLAR_DIRT));

		boolean generatesInDungeon = FeatureHelper.isFeatureGeneratingNextToDungeon(context);
		
        if (canGenerate && !generatesInDungeon)
        {
        	generateSlipperySand(reader, rand, blockPos);
        	return true;
        }
        return false;
    }
    
    protected void generateSlipperySand(StructureWorldAccess reader, Random rand, BlockPos blockPos)
    {
    	BlockPos.Mutable placementPos = new BlockPos.Mutable();
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
                    	placementPos.set(blockPos.add(pos));
                    	if (isReplaceable(reader, placementPos))
                    	{
                    		boolean isInsideInnerEllipsis = isPosInsideEllipsis(pos, radiusX-1, radiusY, radiusZ-1);
                    		if (isInsideInnerEllipsis) {reader.setBlockState(placementPos, AerialHellBlocks.SLIPPERY_SAND.getDefaultState(), 0);}
                    		else if (!(rand.nextInt(3) == 0)) {reader.setBlockState(placementPos, AerialHellBlocks.SLIPPERY_SAND.getDefaultState(), 0);}
                    	}
                    }
                }
        	}
        }
    }
    
    private boolean isReplaceable(StructureWorldAccess reader, BlockPos blockPos)
    {
    	BlockState previousBlock = reader.getBlockState(blockPos);
    	if (previousBlock.isAir() || previousBlock.isIn(AerialHellTags.Blocks.FEATURE_CAN_REPLACE) || previousBlock.isIn(AerialHellTags.Blocks.STELLAR_DIRT)) {return true;}
    	else {return false;}
    }
    
    private int getRandomRadius(Random rand, boolean isBig)
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