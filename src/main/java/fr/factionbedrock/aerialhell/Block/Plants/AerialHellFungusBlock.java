package fr.factionbedrock.aerialhell.Block.Plants;

import java.util.function.Supplier;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.FungusBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.HugeFungusConfig;

public class AerialHellFungusBlock extends FungusBlock
{
	public AerialHellFungusBlock(Properties properties,	Supplier<ConfiguredFeature<HugeFungusConfig, ?>> fungusFeature){super(properties, fungusFeature);}
	
	@Override protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos)
	{
		return state.isIn(AerialHellBlocksAndItems.STELLAR_GRASS_BLOCK.get()) || super.isValidGround(state, worldIn, pos);
	}
}
