package fr.factionbedrock.aerialhell.World.Features;

import java.util.Random;
import java.util.function.Supplier;

import com.mojang.serialization.Codec;

import fr.factionbedrock.aerialhell.Registry.AerialHellTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class CrystalBlobFeature extends Feature<NoneFeatureConfiguration>
{
	public Supplier<Block> crystalBlock;
	public CrystalBlobFeature(Supplier<Block> block, Codec<NoneFeatureConfiguration> codec) {super(codec); this.crystalBlock=block;}

	@Override public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context)
	{
		BlockPos pos = context.origin(); WorldGenLevel reader = context.level(); Random rand = context.random();
		if (!reader.isEmptyBlock(pos)) {return false;}
		else
		{
			BlockState blockstate = reader.getBlockState(pos.below());
		    if (!blockstate.is(AerialHellTags.Blocks.STELLAR_DIRT)) {return false;}
		    else
		    {
		    	reader.setBlock(pos, crystalBlock.get().defaultBlockState(), 2);

		        for(int i = 0; i < 1700; ++i)
		        {
		        	BlockPos blockpos;
		        	if (i < 1400)
		        	{
		        		blockpos = pos.offset(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(12), rand.nextInt(8) - rand.nextInt(8));
		        	}
		        	else
		        	{
		        		blockpos = pos.offset(rand.nextInt(9) - rand.nextInt(9), - rand.nextInt(3), rand.nextInt(9) - rand.nextInt(9));
		        	}
		            if (reader.getBlockState(blockpos).isAir())
		            {
			            int j = 0;
	
			            for(Direction direction : Direction.values())
			            {
				            if (reader.getBlockState(blockpos.relative(direction)).is(crystalBlock.get())) {++j;}
		
				            if (j > 1) {break;}
			            }
	
			            if (j == 1) {reader.setBlock(blockpos, crystalBlock.get().defaultBlockState(), 2);}
		            }
		        }

		    return true;
		    }
		}
	}
}
