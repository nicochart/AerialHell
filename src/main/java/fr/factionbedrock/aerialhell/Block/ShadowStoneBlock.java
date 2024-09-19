package fr.factionbedrock.aerialhell.Block;

import fr.factionbedrock.aerialhell.Util.BlockHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

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
			level.setBlock(pos, state.setValue(CAN_SPREAD, true), 4);
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
					BlockHelper.tryCorrupt(level, blockpos, rand, 0.4F);
					return;
				}
				else //isCorrupted
				{
					if (BlockHelper.surroundingsPreventCorruption(level, blockpos, BlockHelper.CorruptionType.ANY)) {BlockHelper.uncorrupt(level, blockpos);}
				}
			}
		}

		if (BlockHelper.isSurroundingCorrupted(level, pos))
		{
			level.setBlock(pos, state.setValue(CAN_SPREAD, false), 4);
		}
	}
}