
package fr.factionbedrock.aerialhell.Block.CollisionCondition;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class GhostBoatSlabBlock extends SlabBlock
{
	public GhostBoatSlabBlock(BlockBehaviour.Properties settings)
	{
		super(settings);
		this.registerDefaultState(this.defaultBlockState());
	}

	@Override public void entityInside(BlockState state, Level world, BlockPos pos, Entity entity, InsideBlockEffectApplier handler, boolean intersects)
	{
		entity.fallDistance = 0.0F;
		if (entity.getDeltaMovement().y < 0.0)
		{
			if (entity instanceof LivingEntity livingEntity) {this.livingEntityInside(state, world, pos, livingEntity);}
			else {this.nonLivingEntityInside(state, world, pos, entity);}
		}
	}

	public void livingEntityInside(BlockState state, Level world, BlockPos pos, LivingEntity entity)
	{
		if (!canEntityCollide(entity))
		{
			double y_delta_movement_factor = entity.getDeltaMovement().y < 0.1D ? 0.8D : CollisionConditionHalfTransparentBlock.default_y_delta_movement_factor;
			EntityHelper.multiplyDeltaMovement(entity, CollisionConditionHalfTransparentBlock.default_living_entity_xz_delta_movement_factor, y_delta_movement_factor);
		}
	}

	public void nonLivingEntityInside(BlockState state, Level world, BlockPos pos, Entity entity)
	{
		EntityHelper.multiplyDeltaMovement(entity, CollisionConditionHalfTransparentBlock.default_non_living_entity_xz_delta_movement_factor, CollisionConditionHalfTransparentBlock.default_y_delta_movement_factor);
	}

	@Override public VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context)
	{
		if (context instanceof EntityCollisionContext entityShapeContext && entityShapeContext.getEntity() != null)
		{
			Entity entity = entityShapeContext.getEntity();
			if (canEntityCollide(entity)) {return super.getCollisionShape(state, world, pos, context);}
		}
		return CollisionConditionHalfTransparentBlock.EMPTY_SHAPE;
	}

	protected boolean canEntityCollide(Entity entity) {return !EntityHelper.isImmuneToGhostBlockCollision(entity);}

	@Override public VoxelShape getVisualShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {return Shapes.empty();}

	@Override protected boolean skipRendering(BlockState state, BlockState adjacentBlockState, Direction direction)
	{
		if (!state.is(AerialHellBlocks.GHOST_BOAT_SLAB)) {return super.skipRendering(state, adjacentBlockState, direction);}

		if (direction == Direction.UP) {return areVerticalSkipRenderingSlabStates(state, adjacentBlockState);}
		else if (direction == Direction.DOWN) {return areVerticalSkipRenderingSlabStates(adjacentBlockState, state);}
		else /*if (direction != Direction.UP && direction != Direction.DOWN)*/ {return areHorizontalSkipRenderingSlabStates(state, adjacentBlockState);}
	}

	public boolean areHorizontalSkipRenderingSlabStates(BlockState state1, BlockState state2)
	{
		boolean state1IsSlab = state1.is(this), state2IsSlab = state2.is(this);
		boolean state2IsFullBlock = state2.is(AerialHellBlocks.GHOST_BOAT_PLANKS) || state2IsSlab && state2.getValue(TYPE) == SlabType.DOUBLE;
		boolean areCompatibleSlabStates = state1IsSlab && state2IsSlab && state1.getValue(TYPE) == state2.getValue(TYPE);
		if (!state2.is(this)) {return state2IsFullBlock;}
		return state2IsFullBlock || areCompatibleSlabStates;
	}

	public boolean areVerticalSkipRenderingSlabStates(BlockState belowState, BlockState topState)
	{
		boolean belowIsSlab = belowState.is(this), topIsSlab = topState.is(this);
		boolean isValidBelowState = belowIsSlab ? belowState.getValue(TYPE) != SlabType.BOTTOM : belowState.is(AerialHellBlocks.GHOST_BOAT_PLANKS);
		boolean isValidTopState = topIsSlab ? topState.getValue(TYPE) != SlabType.TOP : topState.is(AerialHellBlocks.GHOST_BOAT_PLANKS);
		return isValidBelowState && isValidTopState;
	}
}