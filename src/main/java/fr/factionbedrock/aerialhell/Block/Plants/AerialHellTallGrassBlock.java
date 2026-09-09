package fr.factionbedrock.aerialhell.Block.Plants;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.TallGrassBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;
import java.util.Optional;

public class AerialHellTallGrassBlock extends TallGrassBlock
{
	public AerialHellTallGrassBlock(BlockBehaviour.Properties settings)
	{
		super(settings);
	}

	@Override public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state)
	{
		DoublePlantBlock tall_plant;
	    if (this == AerialHellBlocks.STELLAR_FERN)
	    {
	    	tall_plant = (DoublePlantBlock) AerialHellBlocks.STELLAR_TALL_FERN;
	    	placePlant(level, pos, tall_plant);
	    }
		else if (this == AerialHellBlocks.BLUISH_FERN)
		{
			tall_plant = (DoublePlantBlock) AerialHellBlocks.TALL_BLUISH_FERN;
			placePlant(level, pos, tall_plant);
		}
		else if (this == AerialHellBlocks.POLYCHROME_FERN)
		{
			tall_plant = (DoublePlantBlock) AerialHellBlocks.TALL_POLYCHROME_FERN;
			placePlant(level, pos, tall_plant);
		}
	    else if (this == AerialHellBlocks.STELLAR_GRASS)
	    {
	    	tall_plant = (DoublePlantBlock) AerialHellBlocks.STELLAR_TALL_GRASS;
	    	placePlant(level, pos, tall_plant);
	    }
		else if (this == AerialHellBlocks.STELLAR_CLOVERS)
		{
			findSpreadableNeighbourPos(level, pos, state).ifPresent((blockPos) -> level.setBlockAndUpdate(blockPos, this.defaultBlockState()));
		}
	}

	@Override public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state)
	{
		if (this == AerialHellBlocks.STELLAR_CLOVERS)
		{
			return hasSpreadableNeighbourPos(level, pos, state);
		}
		return super.isValidBonemealTarget(level, pos, state);
	}

	protected void placePlant(ServerLevel world, BlockPos pos, DoublePlantBlock plantIn)
	{
		if (plantIn.defaultBlockState().canSurvive(world, pos) && world.isEmptyBlock(pos.above()))
	    {
	         plantIn.placeAt(world, plantIn.defaultBlockState(), pos, 2);
	    }
	}

	//backport of net.minecraft.world.level.BonemealableBlock methods
	public static boolean hasSpreadableNeighbourPos(LevelReader level, BlockPos pos, BlockState blockToPlace)
	{
		return getSpreadableNeighbourPos(Direction.Plane.HORIZONTAL.stream().toList(), level, pos, blockToPlace).isPresent();
	}

	public static Optional<BlockPos> findSpreadableNeighbourPos(Level level, BlockPos pos, BlockState blockToPlace)
	{
		return getSpreadableNeighbourPos(Direction.Plane.HORIZONTAL.shuffledCopy(level.getRandom()), level, pos, blockToPlace);
	}

	private static Optional<BlockPos> getSpreadableNeighbourPos(List<Direction> directions, LevelReader level, BlockPos pos, BlockState blockToPlace)
	{
		for (Direction direction : directions)
		{
			BlockPos neighbourPos = pos.relative(direction);
			if (level.isEmptyBlock(neighbourPos) && blockToPlace.canSurvive(level, neighbourPos)) {return Optional.of(neighbourPos);}
		}
		return Optional.empty();
	}
}
