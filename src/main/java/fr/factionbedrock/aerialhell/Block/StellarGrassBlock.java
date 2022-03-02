package fr.factionbedrock.aerialhell.Block;

import net.minecraft.block.*;
import net.minecraft.state.StateContainer;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.FlowersFeature;
import net.minecraft.world.lighting.LightEngine;
import net.minecraft.world.server.ServerWorld;

import java.util.List;
import java.util.Random;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;

public class StellarGrassBlock extends GrassBlock
{
	public StellarGrassBlock(AbstractBlock.Properties properties)
	{
		super(properties);
		this.setDefaultState(this.getDefaultState());
	}
	
	@Override
	public void onPlantGrow(BlockState state, IWorld world, BlockPos pos, BlockPos source)
	{
		if (state.getBlock().equals(AerialHellBlocksAndItems.STELLAR_DIRT.get()))
		{
			world.setBlockState(pos, AerialHellBlocksAndItems.STELLAR_GRASS_BLOCK.get().getDefaultState(), 2);
		}
		else if (state.getBlock().equals(AerialHellBlocksAndItems.CHISELED_STELLAR_DIRT.get()))
		{
			world.setBlockState(pos, AerialHellBlocksAndItems.CHISELED_STELLAR_GRASS_BLOCK.get().getDefaultState(), 2);
		}
	}
	
	@Override
	public void grow(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state)
	{
	      BlockPos blockpos = pos.up();
	      BlockState blockstate = AerialHellBlocksAndItems.STELLAR_GRASS.get().getDefaultState();

	      label48:
	      for(int i = 0; i < 128; ++i)
	      {
	    	  BlockPos blockpos1 = blockpos;

	    	  for(int j = 0; j < i / 16; ++j)
	    	  {
	    		  blockpos1 = blockpos1.add(rand.nextInt(3) - 1, (rand.nextInt(3) - 1) * rand.nextInt(3) / 2, rand.nextInt(3) - 1);
	    		  if (!worldIn.getBlockState(blockpos1.down()).isIn(this) || worldIn.getBlockState(blockpos1).hasOpaqueCollisionShape(worldIn, blockpos1))
	    		  {
	    			  continue label48;
	    		  }
	         }

	         BlockState blockstate2 = worldIn.getBlockState(blockpos1);
	         if (blockstate2.isIn(blockstate.getBlock()) && rand.nextInt(10) == 0)
	         {
	        	 ((IGrowable)blockstate.getBlock()).grow(worldIn, rand, blockpos1, blockstate2);
	         }

	         if (blockstate2.isAir())
	         {
	        	 BlockState blockstate1;
	        	 if (rand.nextInt(8) == 0)
	        	 {
	        		 List<ConfiguredFeature<?, ?>> list = worldIn.getBiome(blockpos1).getGenerationSettings().getFlowerFeatures();
	        		 if (list.isEmpty())
	        		 {
	        			 continue;
	        		 }

	              ConfiguredFeature<?, ?> configuredfeature = list.get(0);
	              FlowersFeature flowersfeature = (FlowersFeature)configuredfeature.feature;
	              blockstate1 = flowersfeature.getFlowerToPlace(rand, blockpos1, configuredfeature.getConfig());
	              }
	        	 else
	        	 {
	               blockstate1 = blockstate;
	             }

	             if (blockstate1.isValidPosition(worldIn, blockpos1))
	             {
	               worldIn.setBlockState(blockpos1, blockstate1, 3);
	             }
	         }
	      }
	}
	
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
	{
		super.fillStateContainer(builder);
	}
	

	@Override
	public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random)
	{
		if (!isSnowyConditions(state, worldIn, pos))
		{
			if (!worldIn.isAreaLoaded(pos, 3)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light and spreading
			worldIn.setBlockState(pos, AerialHellBlocksAndItems.STELLAR_DIRT.get().getDefaultState());
		}
		else
		{
			if (worldIn.getLight(pos.up()) >= 9)
			{
				for(int i = 0; i < 4; ++i)
				{
					BlockPos blockpos = pos.add(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
					BlockState blockstate;
					if (worldIn.getBlockState(blockpos).getBlock() == AerialHellBlocksAndItems.STELLAR_DIRT.get())
					{
						blockstate = AerialHellBlocksAndItems.STELLAR_GRASS_BLOCK.get().getDefaultState();
					}
					else //CHISELED_STELLAR_DIRT
					{
						blockstate = AerialHellBlocksAndItems.CHISELED_STELLAR_GRASS_BLOCK.get().getDefaultState();
					}
					
					if ((worldIn.getBlockState(blockpos).isIn(AerialHellBlocksAndItems.STELLAR_DIRT.get()) || worldIn.getBlockState(blockpos).isIn(AerialHellBlocksAndItems.CHISELED_STELLAR_DIRT.get())) && isSnowyAndNotUnderwater(blockstate, worldIn, blockpos))
					{
						worldIn.setBlockState(blockpos, blockstate.with(SNOWY, worldIn.getBlockState(blockpos.up()).isIn(Blocks.SNOW)));
					}
				}
			}
		}
	}
	
	
	/* ---- Functions copied from SpreadableSnowyDirtBlock class ---- */
	
	private static boolean isSnowyConditions(BlockState state, IWorldReader worldReader, BlockPos pos) 
	{
	     BlockPos blockpos = pos.up();
	     BlockState blockstate = worldReader.getBlockState(blockpos);
	     if (blockstate.isIn(Blocks.SNOW) && blockstate.get(SnowBlock.LAYERS) == 1) {return true;}
	     else if (blockstate.getFluidState().getLevel() == 8) {return false;}
	     else
	     {
	        int i = LightEngine.func_215613_a(worldReader, state, pos, blockstate, blockpos, Direction.UP, blockstate.getOpacity(worldReader, blockpos));
	        return i < worldReader.getMaxLightLevel();
	     }
	 }
	
	private static boolean isSnowyAndNotUnderwater(BlockState state, IWorldReader worldReader, BlockPos pos)
	{
	    BlockPos blockpos = pos.up();
	    return isSnowyConditions(state, worldReader, pos) && !worldReader.getFluidState(blockpos).isTagged(FluidTags.WATER);
	}
}