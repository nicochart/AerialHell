package fr.factionbedrock.aerialhell.World.Features;

import com.mojang.serialization.Codec;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;

public class CrystallizedFireFeature extends Feature<DefaultFeatureConfig>
{
	public CrystallizedFireFeature(Codec<DefaultFeatureConfig> codec) {super(codec);}

	@Override public boolean generate(FeatureContext<DefaultFeatureConfig> context)
	{
		BlockPos pos = context.getOrigin(); StructureWorldAccess reader = context.getWorld(); Random rand = context.getRandom();
		if (!reader.isAir(pos)) {return false;}
		else
		{
			BlockState blockstate = reader.getBlockState(pos.down());
		    if (!blockstate.isIn(AerialHellTags.Blocks.STELLAR_DIRT)) {return false;}
		    else
		    {
		    	reader.setBlockState(pos, AerialHellBlocks.CRYSTALLIZED_FIRE.getDefaultState(), 2);
		    	
		    	int neighbor_number = 4 + rand.nextInt(3);
		    	for(int i = 0; i < neighbor_number; ++i)
		        {
		    		BlockPos blockpos = pos.add(rand.nextInt(8) - rand.nextInt(8), 0, rand.nextInt(8) - rand.nextInt(8));
		    		int j = 0;
		    		if (reader.getBlockState(blockpos).isOf(Blocks.AIR))
		    		{
		    			while (reader.getBlockState(blockpos.down()).isOf(Blocks.AIR) && j < 10)
			    		{
		    				blockpos = blockpos.down();
		    				j++;
			    		}
		    			if (reader.getBlockState(blockpos.down()).isIn(AerialHellTags.Blocks.STELLAR_DIRT))
			    		{
		    				reader.setBlockState(blockpos, AerialHellBlocks.CRYSTALLIZED_FIRE.getDefaultState(), 2);
			    		}
		    		}
		    		else
		    		{
		    			while (!reader.getBlockState(blockpos).isOf(Blocks.AIR) && j < 10)
			    		{
		    				blockpos = blockpos.up();
		    				j++;
			    		}
		    			if (reader.getBlockState(blockpos).isOf(Blocks.AIR) && reader.getBlockState(blockpos.down()).isIn(AerialHellTags.Blocks.STELLAR_DIRT))
			    		{
		    				reader.setBlockState(blockpos, AerialHellBlocks.CRYSTALLIZED_FIRE.getDefaultState(), 2);
			    		}
		    		}
		        }

		    return true;
		    }
		}
	}
}
