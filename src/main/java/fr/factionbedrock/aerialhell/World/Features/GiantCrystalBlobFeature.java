package fr.factionbedrock.aerialhell.World.Features;

import com.mojang.serialization.Codec;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class GiantCrystalBlobFeature extends Feature<DefaultFeatureConfig>
{
	public GiantCrystalBlobFeature(Codec<DefaultFeatureConfig> codec) {super(codec);}

	@Override public boolean generate(FeatureContext<DefaultFeatureConfig> context)
	{
		BlockPos pos = context.getOrigin(); StructureWorldAccess reader = context.getWorld(); Random rand = context.getRandom();
		if (!reader.isAir(pos)) {return false;}
		else
		{
			BlockState blockstate = reader.getBlockState(pos.down());
		    if (!blockstate.isIn(AerialHellTags.Blocks.STELLAR_DIRT))
		    {
		    	return false;
		    }
		    else
		    {
		    	reader.setBlockState(pos, AerialHellBlocks.CRYSTAL_BLOCK.getDefaultState(), 2);
		    	
		    	BlockPos blockpos;
		        for(int i = 0; i < 3000; ++i)
		        {
		        	
		        	if (i < 1000)
		        	{
		        		blockpos = pos.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(6), rand.nextInt(8) - rand.nextInt(8));
		        	}
		        	else if (i < 1500)
		        	{
		        		blockpos = pos.add(rand.nextInt(7) - rand.nextInt(7), 6 + rand.nextInt(6), rand.nextInt(7) - rand.nextInt(7));
		        	}
		        	else
		        	{
		        		blockpos = pos.add(rand.nextInt(9) - rand.nextInt(9), rand.nextInt(4) - rand.nextInt(8), rand.nextInt(9) - rand.nextInt(9));
		        	}
		            if (reader.getBlockState(blockpos).isOf(Blocks.AIR) || reader.getBlockState(blockpos).isIn(AerialHellTags.Blocks.STELLAR_DIRT) || reader.getBlockState(blockpos).isIn(AerialHellTags.Blocks.STELLAR_STONE))
		            {
			            int j = 0;
	
			            for(Direction direction : Direction.values())
			            {
				            if (reader.getBlockState(blockpos.offset(direction)).isOf(AerialHellBlocks.CRYSTAL_BLOCK))
				            {
				            	++j;
				            }
		
				            if (j > 1) {break;}
			            }
	
			            if (j == 1 || j == 2 && rand.nextInt(25) == 0)
			            {
			            	reader.setBlockState(blockpos, AerialHellBlocks.CRYSTAL_BLOCK.getDefaultState(), 2);
			            }
		            }
		        }
		        for(int i = 0; i < 100; ++i)
		        {
		        	blockpos = pos.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(12), rand.nextInt(8) - rand.nextInt(8));
		        	if (reader.getBlockState(blockpos).isOf(Blocks.AIR) && (reader.getBlockState(blockpos.down()).isOf(AerialHellBlocks.CRYSTAL_BLOCK)))
		        	{
		        		reader.setBlockState(blockpos, AerialHellBlocks.CRYSTALLIZED_FIRE.getDefaultState(), 2);
		        	}
		        }

		    return true;
		    }
		}
	}
}
