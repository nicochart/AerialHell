package fr.factionbedrock.aerialhell.Block.DungeonCores;

import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;

public class CoreProtectedBookshelfBlock extends CoreProtectedBlock
{
	public CoreProtectedBookshelfBlock(Properties properties)
	{
		super(properties);
	}

	@Override public float getEnchantPowerBonus(BlockState state, BlockGetter level, BlockPos pos) {return 1.0F;}
}
