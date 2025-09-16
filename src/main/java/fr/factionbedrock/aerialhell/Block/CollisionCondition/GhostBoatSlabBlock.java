
package fr.factionbedrock.aerialhell.Block.CollisionCondition;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.block.*;
import net.minecraft.block.enums.SlabType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCollisionHandler;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class GhostBoatSlabBlock extends SlabBlock
{
	public GhostBoatSlabBlock(AbstractBlock.Settings settings)
	{
		super(settings);
		this.setDefaultState(this.getDefaultState());
	}

	@Override public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity, EntityCollisionHandler handler)
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
		if (!state.isOf(AerialHellBlocks.GHOST_BOAT_SLAB)) {return super.isSideInvisible(state, adjacentBlockState, direction);}

		if (direction == Direction.UP) {return areVerticalSkipRenderingSlabStates(state, adjacentBlockState);}
		else if (direction == Direction.DOWN) {return areVerticalSkipRenderingSlabStates(adjacentBlockState, state);}
		else /*if (direction != Direction.UP && direction != Direction.DOWN)*/ {return areHorizontalSkipRenderingSlabStates(state, adjacentBlockState);}
	}

	public boolean areHorizontalSkipRenderingSlabStates(BlockState state1, BlockState state2)
	{
		boolean state1IsSlab = state1.isOf(this), state2IsSlab = state2.isOf(this);
		boolean state2IsFullBlock = state2.isOf(AerialHellBlocks.GHOST_BOAT_PLANKS) || state2IsSlab && state2.get(TYPE) == SlabType.DOUBLE;
		boolean areCompatibleSlabStates = state1IsSlab && state2IsSlab && state1.get(TYPE) == state2.get(TYPE);
		if (!state2.isOf(this)) {return state2IsFullBlock;}
		return state2IsFullBlock || areCompatibleSlabStates;
	}

	public boolean areVerticalSkipRenderingSlabStates(BlockState belowState, BlockState topState)
	{
		boolean belowIsSlab = belowState.isOf(this), topIsSlab = topState.isOf(this);
		boolean isValidBelowState = belowIsSlab ? belowState.get(TYPE) != SlabType.BOTTOM : belowState.isOf(AerialHellBlocks.GHOST_BOAT_PLANKS);
		boolean isValidTopState = topIsSlab ? topState.get(TYPE) != SlabType.TOP : topState.isOf(AerialHellBlocks.GHOST_BOAT_PLANKS);
		return isValidBelowState && isValidTopState;
	}
}