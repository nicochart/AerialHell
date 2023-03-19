package fr.factionbedrock.aerialhell.World.Features;

import java.util.Random;
import java.util.function.Supplier;

import com.mojang.serialization.Codec;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class CrystalBlobFeature extends Feature<NoFeatureConfig>
{
	public Supplier<Block> crystalBlock;
	public CrystalBlobFeature(Supplier<Block> block, Codec<NoFeatureConfig> codec) {super(codec); this.crystalBlock=block;}

	@Override
	public boolean generate(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config)
	{
		if (!reader.isAirBlock(pos)) {return false;}
		else
		{
			BlockState blockstate = reader.getBlockState(pos.down());
		    if (!blockstate.isIn(AerialHellTags.Blocks.STELLAR_DIRT)) {return false;}
		    else
		    {
		    	reader.setBlockState(pos, crystalBlock.get().getDefaultState(), 2);

		        for(int i = 0; i < 1700; ++i)
		        {
		        	BlockPos blockpos;
		        	if (i < 1400)
		        	{
		        		blockpos = pos.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(12), rand.nextInt(8) - rand.nextInt(8));
		        	}
		        	else
		        	{
		        		blockpos = pos.add(rand.nextInt(9) - rand.nextInt(9), - rand.nextInt(3), rand.nextInt(9) - rand.nextInt(9));
		        	}
		            if (reader.getBlockState(blockpos).isAir(reader, blockpos))
		            {
			            int j = 0;
	
			            for(Direction direction : Direction.values())
			            {
				            if (reader.getBlockState(blockpos.offset(direction)).isIn(crystalBlock.get())) {++j;}
		
				            if (j > 1) {break;}
			            }
	
			            if (j == 1) {reader.setBlockState(blockpos, crystalBlock.get().getDefaultState(), 2);}
		            }
		        }

		    return true;
		    }
		}
	}
}
