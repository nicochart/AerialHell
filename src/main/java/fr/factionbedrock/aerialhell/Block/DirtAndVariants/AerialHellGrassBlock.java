package fr.factionbedrock.aerialhell.Block.DirtAndVariants;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.AerialHellStateProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.GrassBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public abstract class AerialHellGrassBlock extends GrassBlock implements BonemealableBlock
{
	public AerialHellGrassBlock(Properties properties)
	{
		super(properties);
		this.registerDefaultState(this.defaultBlockState().setValue(SNOWY, false).setValue(AerialHellStateProperties.SHIFTED_RENDER, false));
	}

	@Override protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
	{
		super.createBlockStateDefinition(builder);
		builder.add(AerialHellStateProperties.SHIFTED_RENDER);
	}

	@Override public void performBonemeal(ServerLevel level, RandomSource rand, BlockPos pos, BlockState state)
	{
	      BlockPos blockpos = pos.above();
		  Optional<Holder.Reference<PlacedFeature>> optional = this.getBonemealFeature(level);

	      label46:
	      for(int i = 0; i < 128; ++i)
	      {
	    	  BlockPos blockpos1 = blockpos;

	    	  for(int j = 0; j < i / 16; ++j)
	    	  {
	    		  blockpos1 = blockpos1.offset(rand.nextInt(3) - 1, (rand.nextInt(3) - 1) * rand.nextInt(3) / 2, rand.nextInt(3) - 1);
	    		  if (!level.getBlockState(blockpos1.below()).is(this) || level.getBlockState(blockpos1).isCollisionShapeFullBlock(level, blockpos1))
	    		  {
	    			  continue label46;
	    		  }
	         }

	         BlockState blockstate2 = level.getBlockState(blockpos1);
	         if (blockstate2.is(this) && rand.nextInt(10) == 0)
	         {
	        	 ((BonemealableBlock)this.defaultBlockState().getBlock()).performBonemeal(level, rand, blockpos1, blockstate2);
	         }

	         if (blockstate2.isAir())
	         {
				 Holder<PlacedFeature> holder;
	        	 if (rand.nextInt(8) == 0)
	        	 {
	        		 List<ConfiguredFeature<?, ?>> list = level.getBiome(blockpos1).value().getGenerationSettings().getFlowerFeatures();
	        		 if (list.isEmpty()) {continue;}
					 holder = ((RandomPatchConfiguration)list.get(0).config()).feature();
				 }
				 else
				 {
					 if (!optional.isPresent()) {continue;}
					 holder = optional.get();
				 }
				 holder.value().place(level, level.getChunkSource().getGenerator(), rand, blockpos1);
	         }
	      }
	}

	protected abstract Optional<Holder.Reference<PlacedFeature>> getBonemealFeature(ServerLevel level);

	@Override @Nullable
	public BlockState getToolModifiedState(BlockState state, UseOnContext context, ItemAbility itemAbility, boolean simulate)
	{
		if (!context.getItemInHand().canPerformAction(itemAbility)) {return null;}
		if (state.getBlock() == AerialHellBlocks.STELLAR_GRASS_BLOCK.get() || state.getBlock() == AerialHellBlocks.CHISELED_STELLAR_GRASS_BLOCK.get())
		{
			if (ItemAbilities.HOE_TILL == itemAbility) {return AerialHellBlocks.STELLAR_FARMLAND.get().defaultBlockState();}
			if (ItemAbilities.SHOVEL_FLATTEN == itemAbility) {return AerialHellBlocks.STELLAR_DIRT_PATH.get().defaultBlockState();}
		}
		return super.getToolModifiedState(state, context, itemAbility, simulate);
	}
}