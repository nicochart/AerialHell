package fr.factionbedrock.aerialhell.Block.Plants;

import java.util.Random;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellFeatures;
import fr.factionbedrock.aerialhell.Registry.AerialHellTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.MushroomBlock;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.server.ServerWorld;

public class AerialHellMushroomBlock extends MushroomBlock
{
	public AerialHellMushroomBlock(Properties properties) {super(properties);}
	
	@Override
	public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {}
	
	@Override protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos)
	{
		return state.isIn(AerialHellTags.Blocks.STELLAR_DIRT) || state.isIn(BlockTags.MUSHROOM_GROW_BLOCK);
	}

	@Override public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos)
	{
	    BlockState blockstate = worldIn.getBlockState(pos.down());
	    if (blockstate.isIn(AerialHellTags.Blocks.STELLAR_DIRT) || blockstate.isIn(BlockTags.MUSHROOM_GROW_BLOCK)) {return true;}
	    else {return false;}
	}

	@Override public boolean grow(ServerWorld world, BlockPos pos, BlockState state, Random rand)
	{
	      world.removeBlock(pos, false);
	      ConfiguredFeature<?, ?> configuredfeature;
	      if (this == AerialHellBlocksAndItems.VERDIGRIS_AGARIC.get()) {configuredfeature = AerialHellFeatures.GIANT_VERDIGRIS_AGARIC_PLANTED;}
	      else {return false;}

	      if (configuredfeature.generate(world, world.getChunkProvider().getChunkGenerator(), rand, pos)) {return true;}
	      else {world.setBlockState(pos, state, 3); return false;}
	}
}
