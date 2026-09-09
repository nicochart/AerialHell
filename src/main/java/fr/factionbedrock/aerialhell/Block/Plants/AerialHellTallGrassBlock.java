package fr.factionbedrock.aerialhell.Block.Plants;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.TallGrassBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;

import java.util.List;
import java.util.Optional;

public class AerialHellTallGrassBlock extends TallGrassBlock
{
	public AerialHellTallGrassBlock(Properties properties)
	{
		super(properties);
	}
	
	@Override public void performBonemeal(ServerLevel level, RandomSource rand, BlockPos pos, BlockState state)
	{
	    DoublePlantBlock tall_plant;
	    if (this == AerialHellBlocksAndItems.STELLAR_FERN.get())
	    {
	    	tall_plant = (DoublePlantBlock) AerialHellBlocksAndItems.STELLAR_TALL_FERN.get();
	    	placePlant(level, pos, tall_plant);
	    }
		else if (this == AerialHellBlocksAndItems.BLUISH_FERN.get())
		{
			tall_plant = (DoublePlantBlock) AerialHellBlocksAndItems.TALL_BLUISH_FERN.get();
			placePlant(level, pos, tall_plant);
		}
		else if (this == AerialHellBlocksAndItems.POLYCHROME_FERN.get())
		{
			tall_plant = (DoublePlantBlock) AerialHellBlocksAndItems.TALL_POLYCHROME_FERN.get();
			placePlant(level, pos, tall_plant);
		}
	    else if (this == AerialHellBlocksAndItems.STELLAR_GRASS.get())
	    {
	    	tall_plant = (DoublePlantBlock) AerialHellBlocksAndItems.STELLAR_TALL_GRASS.get();
	    	placePlant(level, pos, tall_plant);
	    }
		else if (this == AerialHellBlocksAndItems.STELLAR_CLOVERS.get())
		{
			findSpreadableNeighbourPos(level, pos, state).ifPresent((blockPos) -> level.setBlockAndUpdate(blockPos, this.defaultBlockState()));
		}
	}

	@Override public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state)
	{
		if (this == AerialHellBlocksAndItems.STELLAR_CLOVERS.get())
		{
			return hasSpreadableNeighbourPos(level, pos, state);
		}
		return super.isValidBonemealTarget(level, pos, state);
	}

	protected void placePlant(ServerLevel worldIn, BlockPos pos, DoublePlantBlock plantIn)
	{
		if (plantIn.defaultBlockState().canSurvive(worldIn, pos) && worldIn.isEmptyBlock(pos.above()))
	    {
	         plantIn.placeAt(worldIn, plantIn.defaultBlockState(), pos, 2);
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
