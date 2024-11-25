package fr.factionbedrock.aerialhell.Block.CollisionCondition.SolidEther;

import fr.factionbedrock.aerialhell.Block.CollisionCondition.CollisionConditionHalfTransparentBlock;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.World;

public class SolidEtherBlock extends CollisionConditionHalfTransparentBlock
{
	protected final static VoxelShape SOLID_ETHER_COLLISION_SHAPE = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 0.02, 16.0);
	
	public SolidEtherBlock(AbstractBlock.Settings settings)
	{
		super(settings);
	}

	@Override public void livingEntityInside(BlockState state, World world, BlockPos pos, LivingEntity entity)
	{
		if (!EntityHelper.isFeatheryEntity(entity)) {super.livingEntityInside(state, world, pos, entity);}
	}

	@Override public BlockState onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player)
	{
		if (this != AerialHellBlocks.WHITE_SOLID_ETHER)
		{
			world.setBlockState(pos, AerialHellBlocks.WHITE_SOLID_ETHER.getDefaultState());
		}
		return super.onBreak(world, pos, state, player);
    }

	@Override protected boolean canEntityCollide(Entity entity) {return !EntityHelper.isImmuneToSolidEtherCollision(entity);}
	@Override protected VoxelShape getCollidingShape() {return SOLID_ETHER_COLLISION_SHAPE;}
}