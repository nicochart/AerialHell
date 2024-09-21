package fr.factionbedrock.aerialhell.Block.DirtAndVariants;

import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellPlacedFeatures;
import fr.factionbedrock.aerialhell.Util.BlockHelper;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.server.level.ServerLevel;

import java.util.List;
import java.util.Optional;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;
import org.jetbrains.annotations.Nullable;

public class ShadowGrassBlock extends GrassBlock
{
	public ShadowGrassBlock(BlockBehaviour.Properties properties)
	{
		super(properties);
		this.registerDefaultState(this.defaultBlockState());
	}
	
	@Override
	public void performBonemeal(ServerLevel level, RandomSource rand, BlockPos pos, BlockState state)
	{
	      BlockPos blockpos = pos.above();
	      BlockState blockstate = AerialHellBlocksAndItems.SHADOW_GRASS.get().defaultBlockState();
		  Optional<Holder.Reference<PlacedFeature>> optional = level.registryAccess().registryOrThrow(Registries.PLACED_FEATURE).getHolder(AerialHellPlacedFeatures.SHADOW_GRASS_BONEMEAL);

	      label46:
	      for(int i = 0; i < 128; ++i)
	      {
	    	  BlockPos blockpos1 = blockpos;

	    	  for(int j = 0; j < i / 16; ++j)
	    	  {
	    		  blockpos1 = blockpos1.offset(rand.nextInt(3) - 1, (rand.nextInt(3) - 1) * rand.nextInt(3) / 2, rand.nextInt(3) - 1);
	    		  if (!level.getBlockState(blockpos1.below()).is(this) || level.getBlockState(blockpos1).isCollisionShapeFullBlock(level, blockpos1)) {continue label46;}
	         }

	         BlockState blockstate2 = level.getBlockState(blockpos1);
	         if (blockstate2.is(blockstate.getBlock()) && rand.nextInt(10) == 0)
	         {
	        	 ((BonemealableBlock)blockstate.getBlock()).performBonemeal(level, rand, blockpos1, blockstate2);
	         }

			  if (blockstate2.isAir())
			  {
				  BlockState blockstate1;
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

	@Override
	public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource rand)
	{
		if (!BlockHelper.canBeGrass(state, level, pos))
		{
			if (!level.isAreaLoaded(pos, 3)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light and spreading
			level.setBlockAndUpdate(pos, AerialHellBlocksAndItems.STELLAR_DIRT.get().defaultBlockState());
		}
		else if (BlockHelper.isCorrupted(level, pos) && BlockHelper.surroundingsPreventCorruption(level, pos, BlockHelper.CorruptionType.GRASS))
		{
			if (!level.isAreaLoaded(pos, 3)) return;
			BlockHelper.uncorrupt(level, pos);
		}
		else
		{
			for(int i = 0; i < 4; ++i)
			{
				BlockPos blockpos = pos.offset(rand.nextInt(3) - 1, rand.nextInt(5) - 3, rand.nextInt(3) - 1);
				BlockState blockstate = AerialHellBlocksAndItems.SHADOW_GRASS_BLOCK.get().defaultBlockState();

				if (level.getMaxLocalRawBrightness(pos.above()) >= 9 && level.getBlockState(blockpos).is(AerialHellBlocksAndItems.STELLAR_DIRT.get()) && BlockHelper.grassCanPropagate(blockstate, level, blockpos))
				{
					level.setBlockAndUpdate(blockpos, blockstate.setValue(SNOWY, level.getBlockState(blockpos.above()).is(Blocks.SNOW)));
					BlockHelper.corruptBiome(level, blockpos, 1);
				}
				else
				{
					if (!BlockHelper.isCorrupted(level, blockpos))
					{
						BlockHelper.tryCorrupt(level, blockpos, rand);
					}
					else //isCorrupted
					{
						if (BlockHelper.surroundingsPreventCorruption(level, blockpos, BlockHelper.CorruptionType.ANY)) {BlockHelper.uncorrupt(level, blockpos);}
					}
				}
			}
		}
	}

	@Override @Nullable
	public BlockState getToolModifiedState(BlockState state, UseOnContext context, ItemAbility itemAbility, boolean simulate)
	{
		if (!context.getItemInHand().canPerformAction(itemAbility)) {return null;}
		if (state.getBlock() == AerialHellBlocksAndItems.SHADOW_GRASS_BLOCK.get())
		{
			if (ItemAbilities.HOE_TILL == itemAbility) {return AerialHellBlocksAndItems.STELLAR_FARMLAND.get().defaultBlockState();}
			else if (ItemAbilities.SHOVEL_FLATTEN == itemAbility) {return AerialHellBlocksAndItems.STELLAR_DIRT_PATH.get().defaultBlockState();}
		}
		return super.getToolModifiedState(state, context, itemAbility, simulate);
	}
}