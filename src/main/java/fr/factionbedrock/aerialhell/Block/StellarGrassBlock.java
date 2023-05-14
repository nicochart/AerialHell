package fr.factionbedrock.aerialhell.Block;

import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.tags.FluidTags;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.lighting.LayerLightEngine;
import net.minecraft.world.level.block.BonemealableBlock;

import java.util.List;
import java.util.Random;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;

public class StellarGrassBlock extends GrassBlock implements BonemealableBlock
{
	public StellarGrassBlock(BlockBehaviour.Properties properties)
	{
		super(properties);
		this.registerDefaultState(this.defaultBlockState());
	}

	@Override
	public void performBonemeal(ServerLevel worldIn, Random rand, BlockPos pos, BlockState state)
	{
	      BlockPos blockpos = pos.above();
	      BlockState blockstate = AerialHellBlocksAndItems.STELLAR_GRASS.get().defaultBlockState();

	      label46:
	      for(int i = 0; i < 128; ++i)
	      {
	    	  BlockPos blockpos1 = blockpos;

	    	  for(int j = 0; j < i / 16; ++j)
	    	  {
	    		  blockpos1 = blockpos1.offset(rand.nextInt(3) - 1, (rand.nextInt(3) - 1) * rand.nextInt(3) / 2, rand.nextInt(3) - 1);
	    		  if (!worldIn.getBlockState(blockpos1.below()).is(this) || worldIn.getBlockState(blockpos1).isCollisionShapeFullBlock(worldIn, blockpos1))
	    		  {
	    			  continue label46;
	    		  }
	         }

	         BlockState blockstate2 = worldIn.getBlockState(blockpos1);
	         if (blockstate2.is(blockstate.getBlock()) && rand.nextInt(10) == 0)
	         {
	        	 ((BonemealableBlock)blockstate.getBlock()).performBonemeal(worldIn, rand, blockpos1, blockstate2);
	         }

	         if (blockstate2.isAir())
	         {
	        	 BlockState blockstate1;
				 Holder<PlacedFeature> holder;
	        	 if (rand.nextInt(8) == 0)
	        	 {
	        		 List<ConfiguredFeature<?, ?>> list = worldIn.getBiome(blockpos1).value().getGenerationSettings().getFlowerFeatures();
	        		 if (list.isEmpty()) {continue;}
					 holder = ((RandomPatchConfiguration)list.get(0).config()).feature();
				 }
				 else
				 {
					 holder = VegetationPlacements.GRASS_BONEMEAL;
				 }
				 holder.value().place(worldIn, worldIn.getChunkSource().getGenerator(), rand, blockpos1);
	         }
	      }
	}
	
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
	{
		super.createBlockStateDefinition(builder);
	}
	

	@Override
	public void randomTick(BlockState state, ServerLevel worldIn, BlockPos pos, Random random)
	{
		if (!isSnowyConditions(state, worldIn, pos))
		{
			if (!worldIn.isAreaLoaded(pos, 3)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light and spreading
			worldIn.setBlockAndUpdate(pos, AerialHellBlocksAndItems.STELLAR_DIRT.get().defaultBlockState());
		}
		else
		{
			if (worldIn.getMaxLocalRawBrightness(pos.above()) >= 9)
			{
				for(int i = 0; i < 4; ++i)
				{
					BlockPos blockpos = pos.offset(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
					BlockState blockstate;
					if (worldIn.getBlockState(blockpos).getBlock() == AerialHellBlocksAndItems.STELLAR_DIRT.get())
					{
						blockstate = AerialHellBlocksAndItems.STELLAR_GRASS_BLOCK.get().defaultBlockState();
					}
					else //CHISELED_STELLAR_DIRT
					{
						blockstate = AerialHellBlocksAndItems.CHISELED_STELLAR_GRASS_BLOCK.get().defaultBlockState();
					}
					
					if ((worldIn.getBlockState(blockpos).is(AerialHellBlocksAndItems.STELLAR_DIRT.get()) || worldIn.getBlockState(blockpos).is(AerialHellBlocksAndItems.CHISELED_STELLAR_DIRT.get())) && isSnowyAndNotUnderwater(blockstate, worldIn, blockpos))
					{
						worldIn.setBlockAndUpdate(blockpos, blockstate.setValue(SNOWY, worldIn.getBlockState(blockpos.above()).is(Blocks.SNOW)));
					}
				}
			}
		}
	}
	
	
	/* ---- Functions copied from SpreadableSnowyDirtBlock class ---- */
	
	private static boolean isSnowyConditions(BlockState state, LevelReader worldReader, BlockPos pos) //canBeGrass in official mappings
	{
	     BlockPos blockpos = pos.above();
	     BlockState blockstate = worldReader.getBlockState(blockpos);
	     if (blockstate.is(Blocks.SNOW) && blockstate.getValue(SnowLayerBlock.LAYERS) == 1) {return true;}
	     else if (blockstate.getFluidState().getAmount() == 8) {return false;}
	     else
	     {
	        int i = LayerLightEngine.getLightBlockInto(worldReader, state, pos, blockstate, blockpos, Direction.UP, blockstate.getLightBlock(worldReader, blockpos));
	        return i < worldReader.getMaxLightLevel();
	     }
	 }
	
	private static boolean isSnowyAndNotUnderwater(BlockState state, LevelReader worldReader, BlockPos pos)
	{
	    BlockPos blockpos = pos.above();
	    return isSnowyConditions(state, worldReader, pos) && !worldReader.getFluidState(blockpos).is(FluidTags.WATER);
	}
}