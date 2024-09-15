package fr.factionbedrock.aerialhell.Block;

import fr.factionbedrock.aerialhell.Util.BlockHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;

public class ShadowStoneBlock extends Block
{
	public ShadowStoneBlock(Properties properties)
	{
		super(properties);
		this.registerDefaultState(this.defaultBlockState());
	}

	@Override public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource rand)
	{
		System.out.println(level.getBiome(pos));
		if (BlockHelper.isCorrupted(level, pos) && BlockHelper.surroundingsPreventCorruption(level, pos, BlockHelper.CorruptionType.STONE) && level.isAreaLoaded(pos, 3)) {BlockHelper.uncorrupt(level, pos);}
		else
		{
			for(int i = 0; i < 4; ++i)
			{
				BlockPos blockpos = pos.offset(rand.nextInt(3) - 1, rand.nextInt(3) - 1, rand.nextInt(3) - 1);
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