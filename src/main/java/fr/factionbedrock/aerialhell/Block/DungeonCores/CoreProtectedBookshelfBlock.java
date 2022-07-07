package fr.factionbedrock.aerialhell.Block.DungeonCores;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;

public class CoreProtectedBookshelfBlock extends CoreProtectedBlock
{
	public CoreProtectedBookshelfBlock(Properties properties)
	{
		super(properties);
	}
	
	@Override
	public float getEnchantPowerBonus(BlockState state, IWorldReader world, BlockPos pos)
    {
        return 1F;
    }
}
