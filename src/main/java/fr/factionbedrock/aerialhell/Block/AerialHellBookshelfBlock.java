package fr.factionbedrock.aerialhell.Block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;

public class AerialHellBookshelfBlock extends Block
{
	public AerialHellBookshelfBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	public float getEnchantPowerBonus(BlockState state, LevelReader world, BlockPos pos)
    {
        return 1;
    }
}
