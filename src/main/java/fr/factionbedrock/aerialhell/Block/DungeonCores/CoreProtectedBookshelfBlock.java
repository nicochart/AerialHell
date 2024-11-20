package fr.factionbedrock.aerialhell.Block.DungeonCores;

import net.minecraft.block.AbstractBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;

public class CoreProtectedBookshelfBlock extends CoreProtectedBlock
{
	public CoreProtectedBookshelfBlock(AbstractBlock.Settings settings)
	{
		super(settings);
	}
	
	@Override
	public float getEnchantPowerBonus(BlockState state, LevelReader world, BlockPos pos)
    {
        return 1F;
    }
}
