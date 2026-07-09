package fr.factionbedrock.aerialhell.Block.Plants;

import fr.factionbedrock.aerialhell.Block.DirtAndVariants.AerialHellGrassBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;

public class ShiftableRenderTallGrassBlock extends AerialHellTallGrassBlock
{
	public ShiftableRenderTallGrassBlock(BlockBehaviour.Properties settings)
	{
		super(settings);
		this.registerDefaultState(this.defaultBlockState().setValue(AerialHellGrassBlock.SHIFTED_RENDER, false));
	}

	@Override protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {builder.add(AerialHellGrassBlock.SHIFTED_RENDER);}
}
