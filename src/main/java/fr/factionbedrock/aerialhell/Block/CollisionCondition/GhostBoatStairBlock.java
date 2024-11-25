
package fr.factionbedrock.aerialhell.Block.CollisionCondition;

import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class GhostBoatStairBlock extends StairsBlock
{
	public GhostBoatStairBlock(BlockState state, AbstractBlock.Settings settings)
	{
		super(state, settings);
	}

	@Override public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity)
	{
		entity.fallDistance = 0.0F;
		if (entity.getVelocity().y < 0.0)
		{
			if (entity instanceof LivingEntity livingEntity) {this.livingEntityInside(state, world, pos, livingEntity);}
			else {this.nonLivingEntityInside(state, world, pos, entity);}
		}
	}

	public void livingEntityInside(BlockState state, World world, BlockPos pos, LivingEntity entity)
	{
		if (!canEntityCollide(entity))
		{
			double y_delta_movement_factor = entity.getVelocity().y < 0.1D ? 0.8D : CollisionConditionHalfTransparentBlock.default_y_delta_movement_factor;
			EntityHelper.multiplyDeltaMovement(entity, CollisionConditionHalfTransparentBlock.default_living_entity_xz_delta_movement_factor, y_delta_movement_factor);
		}
	}

	public void nonLivingEntityInside(BlockState state, World world, BlockPos pos, Entity entity)
	{
		EntityHelper.multiplyDeltaMovement(entity, CollisionConditionHalfTransparentBlock.default_non_living_entity_xz_delta_movement_factor, CollisionConditionHalfTransparentBlock.default_y_delta_movement_factor);
	}

	@Override public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context)
	{
		if (context instanceof EntityShapeContext entityShapeContext && entityShapeContext.getEntity() != null)
		{
			Entity entity = entityShapeContext.getEntity();
			if (canEntityCollide(entity)) {return super.getCollisionShape(state, world, pos, context);}
		}
		return CollisionConditionHalfTransparentBlock.EMPTY_SHAPE;
	}

	protected boolean canEntityCollide(Entity entity) {return !EntityHelper.isImmuneToGhostBlockCollision(entity);}

	@Override public VoxelShape getCameraCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {return VoxelShapes.empty();}

	@Override protected boolean isSideInvisible(BlockState state, BlockState adjacentBlockState, Direction direction)
	{
		if (adjacentBlockState.isOf(this)) {return state.get(FACING) == adjacentBlockState.get(FACING) && state.get(HALF) == adjacentBlockState.get(HALF) && state.get(SHAPE) == adjacentBlockState.get(SHAPE);}
		else {return super.isSideInvisible(state, adjacentBlockState, direction);}
	}
}