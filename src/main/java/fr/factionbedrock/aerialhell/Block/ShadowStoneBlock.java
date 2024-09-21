package fr.factionbedrock.aerialhell.Block;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Util.BlockHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

import javax.annotation.Nullable;

public class ShadowStoneBlock extends Block
{
	public static final BooleanProperty CAN_SPREAD = BooleanProperty.create("can_spread");

	public ShadowStoneBlock(Properties properties)
	{
		super(properties);
		this.registerDefaultState(this.defaultBlockState().setValue(CAN_SPREAD, true));
	}

	@Override protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {builder.add(CAN_SPREAD);}

	@Override protected boolean isRandomlyTicking(BlockState state) {return state.getValue(CAN_SPREAD);}

	@Override protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock, BlockPos neighborPos, boolean movedByPiston)
	{
		super.neighborChanged(state, level, pos, neighborBlock, neighborPos, movedByPiston);
		if (BlockHelper.canBeCorrupted(level, neighborPos, BlockHelper.CorruptionType.ANY))
		{
			level.setBlock(pos, state.setValue(CAN_SPREAD, true), 2);
		}
	}

	@Override public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource rand)
	{
		if (BlockHelper.isCorrupted(level, pos) && BlockHelper.surroundingsPreventCorruption(level, pos, BlockHelper.CorruptionType.STONE))
		{
			if (!level.isAreaLoaded(pos, 3)) {return;}
			BlockHelper.uncorrupt(level, pos);
			return;
		}
		else
		{
			for(int i = 0; i < 4; ++i)
			{
				BlockPos blockpos = pos.offset(rand.nextInt(3) - 1, rand.nextInt(3) - 1, rand.nextInt(3) - 1);
				if (!BlockHelper.isCorrupted(level, blockpos))
				{
					if (level.getBlockState(blockpos).is(AerialHellBlocksAndItems.STELLAR_DIRT))
					{
						@Nullable BlockPos maybeGrassPos = lookForAboveGrassBlock(level, blockpos);
						if (maybeGrassPos != null && BlockHelper.tryCorrupt(level, maybeGrassPos, rand, 1.2F)) {return;}
					}
					else if (BlockHelper.tryCorrupt(level, blockpos, rand)) {return;}
				}
				else //isCorrupted
				{
					if (BlockHelper.surroundingsPreventCorruption(level, blockpos, BlockHelper.CorruptionType.ANY)) {BlockHelper.uncorrupt(level, blockpos);}
				}
			}
		}

		if (BlockHelper.isSurroundingCorrupted(level, pos))
		{
			level.setBlock(pos, state.setValue(CAN_SPREAD, false), 2);
		}
	}

	@Nullable private static BlockPos lookForAboveGrassBlock(LevelReader level, BlockPos origin)
	{
		BlockPos blockpos = origin;
		int j=0;
		while(j++ < 4 && level.getBlockState(blockpos).is(AerialHellTags.Blocks.STELLAR_DIRT)) {blockpos = blockpos.above();}

		if (level.getBlockState(blockpos).isAir() && level.getBlockState(blockpos.below()).is(AerialHellBlocksAndItems.STELLAR_GRASS_BLOCK)) {return blockpos.below();}
		else {return null;}
	}
}