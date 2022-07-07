package fr.factionbedrock.aerialhell.Block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;

public class AerialHellBookshelfBlock extends Block
{
	public AerialHellBookshelfBlock(Properties properties)
	{
		super(properties);
	}
	
	@Override
	public float getEnchantPowerBonus(BlockState state, IWorldReader world, BlockPos pos)
    {
        return 1;
    }
}
