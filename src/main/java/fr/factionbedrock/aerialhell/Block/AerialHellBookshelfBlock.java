package fr.factionbedrock.aerialhell.Block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;

public class AerialHellBookshelfBlock extends Block
{
	public AerialHellBookshelfBlock(AbstractBlock.Settings settings)
	{
		super(settings);
	}

	@Override
	public float getEnchantPowerBonus(BlockState state, LevelReader world, BlockPos pos)
    {
        return 1;
    }
}
