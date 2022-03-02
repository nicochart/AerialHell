package fr.factionbedrock.aerialhell.Block.SolidEther;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GoldenSolidEtherBlock extends SolidEtherBlock
{
	public GoldenSolidEtherBlock(AbstractBlock.Properties properties)
	{
		super(properties);
	}
	
	@Override
	public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity)
	{
		entity.fallDistance = 0.0F;
		if (entity.getMotion().y < 0.0)
		{
			entity.setMotion(entity.getMotion().mul(1.0, 0.003, 1.0));
		}
	}
}