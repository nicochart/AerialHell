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
	
	private static enum HugeGenerationDirections{NONE, NORTH_WEST, NORTH_EAST, SOUTH_WEST, SOUTH_EAST}
	
	@Override public boolean grow(ServerWorld world, BlockPos pos, BlockState state, Random rand)
	{
		BlockPos generationPos = pos;
	    ConfiguredFeature<?, ?> configuredfeature;
	    HugeGenerationDirections hugeShroomDirection = this.getHugeShroomDirection(world, pos, state);
	    if (this == AerialHellBlocksAndItems.VERDIGRIS_AGARIC.get())
	    {
	    	if (hugeShroomDirection != HugeGenerationDirections.NONE)
	    	{
	    		generationPos = this.getOffsetPosForHugeShroom(pos, hugeShroomDirection);
	    		configuredfeature = AerialHellFeatures.HUGE_VERDIGRIS_AGARIC_PLANTED;
	    		
	    	}
	    	else {configuredfeature = AerialHellFeatures.GIANT_VERDIGRIS_AGARIC_PLANTED;}
	    }
	    else {return false;}
	    
	    world.removeBlock(generationPos, false);	    
	    if (configuredfeature.generate(world, world.getChunkProvider().getChunkGenerator(), rand, generationPos)) {return true;}
	    else {world.setBlockState(pos, state, 3); return false;}
	}
	
	//Returns the direction of the generation of the huge shroom (NONE if there is no direction)
	public HugeGenerationDirections getHugeShroomDirection(ServerWorld world, BlockPos pos, BlockState state)
	{
		AerialHellMushroomBlock mushroomBlock = (AerialHellMushroomBlock) this.getBlock();
		if (world.getBlockState(pos.north()).getBlock() == mushroomBlock)
		{
			if (world.getBlockState(pos.west()).getBlock() == mushroomBlock && world.getBlockState(pos.north().west()).getBlock() == mushroomBlock) {return HugeGenerationDirections.NORTH_WEST;}
			if (world.getBlockState(pos.east()).getBlock() == mushroomBlock && world.getBlockState(pos.north().east()).getBlock() == mushroomBlock) {return HugeGenerationDirections.NORTH_EAST;}
		}
		if (world.getBlockState(pos.south()).getBlock() == mushroomBlock)
		{
			if (world.getBlockState(pos.west()).getBlock() == mushroomBlock && world.getBlockState(pos.south().west()).getBlock() == mushroomBlock) {return HugeGenerationDirections.SOUTH_WEST;}
			if (world.getBlockState(pos.east()).getBlock() == mushroomBlock && world.getBlockState(pos.south().east()).getBlock() == mushroomBlock) {return HugeGenerationDirections.SOUTH_EAST;}
		}
		return HugeGenerationDirections.NONE;
	}
	
	public BlockPos getOffsetPosForHugeShroom(BlockPos basePos, HugeGenerationDirections generationDirection)
	{
		if (generationDirection == HugeGenerationDirections.NORTH_WEST) {return basePos.north().west();}
		else if (generationDirection == HugeGenerationDirections.NORTH_EAST) {return basePos.north();}
		else if (generationDirection == HugeGenerationDirections.SOUTH_WEST) {return basePos.west();}
		else /*(generationDirection == HugeGenerationDirections.SOUTH_EAST)*/ {return basePos;}
	}
}
