package fr.factionbedrock.aerialhell.Block.SolidEther;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

public class GoldenSolidEtherBlock extends SolidEtherBlock
{
	public GoldenSolidEtherBlock(BlockBehaviour.Properties properties) {super(properties);}
	
	@Override
	public void entityInside(BlockState state, Level world, BlockPos pos, Entity entity)
	{
		super.entityInside(state, world, pos, entity);
	}
}