package fr.factionbedrock.aerialhell.Block.CollisionCondition.SolidEther;

import fr.factionbedrock.aerialhell.Block.CollisionCondition.CollisionConditionHalfTransparentBlock;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SolidEtherBlock extends CollisionConditionHalfTransparentBlock
{
	protected final static VoxelShape SOLID_ETHER_COLLISION_SHAPE = Block.box(0.0, 0.0, 0.0, 16.0, 0.02, 16.0);
	
	public SolidEtherBlock(BlockBehaviour.Properties settings)
	{
		super(settings);
	}

	@Override public void livingEntityInside(BlockState state, Level world, BlockPos pos, LivingEntity entity)
	{
		if (!EntityHelper.isFeatheryEntity(entity)) {super.livingEntityInside(state, world, pos, entity);}
	}

	@Override public void destroy(LevelAccessor world, BlockPos pos, BlockState state)
	{
		if (this != AerialHellBlocks.WHITE_SOLID_ETHER)
		{
			world.setBlock(pos, AerialHellBlocks.WHITE_SOLID_ETHER.defaultBlockState(), Block.UPDATE_ALL);
		}
	}

	@Override protected boolean canEntityCollide(Entity entity) {return !EntityHelper.isImmuneToSolidEtherCollision(entity);}
	@Override protected VoxelShape getCollidingShape() {return SOLID_ETHER_COLLISION_SHAPE;}
}