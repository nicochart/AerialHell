package fr.factionbedrock.aerialhell.Block.Plants;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Util.BlockHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.TallGrassBlock;
import net.minecraft.world.level.block.state.BlockState;

public class AerialHellTallShroomBlock extends TallGrassBlock
{
	public AerialHellTallShroomBlock(Properties properties) {super(properties);}

	@Override public void performBonemeal(ServerLevel level, RandomSource rand, BlockPos pos, BlockState state)
	{
		DoublePlantBlock tall_plant;
		if (this == AerialHellBlocksAndItems.GLOWING_BOLETUS.get())
		{
			tall_plant = (DoublePlantBlock) AerialHellBlocksAndItems.TALL_GLOWING_BOLETUS.get();
			placePlant(level, pos, tall_plant);
		}
	}

	protected void placePlant(ServerLevel level, BlockPos pos, DoublePlantBlock plantIn)
	{
		if (plantIn.defaultBlockState().canSurvive(level, pos) && level.isEmptyBlock(pos.above()))
		{
			plantIn.placeAt(level, plantIn.defaultBlockState(), pos, 2);
		}
	}

	@Override public boolean canSurvive(BlockState state, LevelReader reader, BlockPos pos)
	{
		BlockState belowState = reader.getBlockState(pos.below());
		if (belowState.is(BlockTags.DIRT) || belowState.is(AerialHellBlocksAndItems.STELLAR_COARSE_DIRT.get()) || belowState.is(AerialHellTags.Blocks.STELLAR_STONE_AND_DERIVATIVES) || belowState.is(BlockTags.MUSHROOM_GROW_BLOCK))
		{
			boolean brightnessFlag = reader.getRawBrightness(pos, 0) < 13;
			boolean solidSurfaceAbove = BlockHelper.hasAnySolidSurfaceAbove(reader, pos, 3);
			return brightnessFlag && solidSurfaceAbove;
		}
		else {return false;}
	}

	@Override protected boolean mayPlaceOn(BlockState state, BlockGetter getter, BlockPos pos)
	{
		return state.is(BlockTags.DIRT) || state.is(AerialHellBlocksAndItems.STELLAR_COARSE_DIRT.get()) || state.is(AerialHellTags.Blocks.STELLAR_STONE_AND_DERIVATIVES) || state.is(BlockTags.MUSHROOM_GROW_BLOCK);
	}
}
