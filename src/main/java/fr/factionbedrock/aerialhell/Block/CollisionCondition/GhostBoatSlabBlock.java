
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
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class GhostBoatSlabBlock extends SlabBlock
{
	public GhostBoatSlabBlock(Properties properties)
	{
		super(properties);
		this.registerDefaultState(this.defaultBlockState());
	}

	@Override public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity, InsideBlockEffectApplier effectApplier, boolean intersects)
	{
		entity.fallDistance = 0.0F;
		if (entity.getDeltaMovement().y < 0.0)
		{
			if (entity instanceof LivingEntity livingEntity) {this.livingEntityInside(state, level, pos, livingEntity);}
			else {this.nonLivingEntityInside(state, level, pos, entity);}
		}
	}

	public void livingEntityInside(BlockState state, Level level, BlockPos pos, LivingEntity entity)
	{
		if (!canEntityCollide(entity))
		{
			double y_delta_movement_factor = entity.getDeltaMovement().y < 0.1D ? 0.8D : CollisionConditionHalfTransparentBlock.default_y_delta_movement_factor;
			EntityHelper.multiplyDeltaMovement(entity, CollisionConditionHalfTransparentBlock.default_living_entity_xz_delta_movement_factor, y_delta_movement_factor);
		}
	}

	public void nonLivingEntityInside(BlockState state, Level level, BlockPos pos, Entity entity)
	{
		EntityHelper.multiplyDeltaMovement(entity, CollisionConditionHalfTransparentBlock.default_non_living_entity_xz_delta_movement_factor, CollisionConditionHalfTransparentBlock.default_y_delta_movement_factor);
	}

	@Override public boolean useShapeForLightOcclusion(BlockState state) {return true;}

	@Override public VoxelShape getCollisionShape(BlockState state, BlockGetter blockGetter, BlockPos pos, CollisionContext context)
	{
		if (context instanceof EntityCollisionContext entityCollisionContext && entityCollisionContext.getEntity() != null)
		{
			Entity entity = entityCollisionContext.getEntity();
			if (canEntityCollide(entity)) {return super.getCollisionShape(state, blockGetter, pos, context);}
		}
		return CollisionConditionHalfTransparentBlock.EMPTY_SHAPE;
	}

	protected boolean canEntityCollide(Entity entity) {return !EntityHelper.isImmuneToGhostBlockCollision(entity);}

	@Override public VoxelShape getVisualShape(BlockState state, BlockGetter blockGetter, BlockPos pos, CollisionContext context) {return Shapes.empty();}

	@Override public boolean skipRendering(BlockState state1, BlockState state2, Direction direction)
	{
		if (!state1.is(AerialHellBlocks.GHOST_BOAT_SLAB.get())) {return super.skipRendering(state1, state2, direction);}

		if (direction == Direction.UP) {return areVerticalSkipRenderingSlabStates(state1, state2);}
		else if (direction == Direction.DOWN) {return areVerticalSkipRenderingSlabStates(state2, state1);}
		else /*if (direction != Direction.UP && direction != Direction.DOWN)*/ {return areHorizontalSkipRenderingSlabStates(state1, state2);}
	}

	public boolean areHorizontalSkipRenderingSlabStates(BlockState state1, BlockState state2)
	{
		boolean state1IsSlab = state1.is(this), state2IsSlab = state2.is(this);
		boolean state2IsFullBlock = state2.is(AerialHellBlocks.GHOST_BOAT_PLANKS.get()) || state2IsSlab && state2.getValue(TYPE) == SlabType.DOUBLE;
		boolean areCompatibleSlabStates = state1IsSlab && state2IsSlab && state1.getValue(TYPE) == state2.getValue(TYPE);
		if (!state2.is(this)) {return state2IsFullBlock;}
		return state2IsFullBlock || areCompatibleSlabStates;
	}

	public boolean areVerticalSkipRenderingSlabStates(BlockState belowState, BlockState topState)
	{
		boolean belowIsSlab = belowState.is(this), topIsSlab = topState.is(this);
		boolean isValidBelowState = belowIsSlab ? belowState.getValue(TYPE) != SlabType.BOTTOM : belowState.is(AerialHellBlocks.GHOST_BOAT_PLANKS.get());
		boolean isValidTopState = topIsSlab ? topState.getValue(TYPE) != SlabType.TOP : topState.is(AerialHellBlocks.GHOST_BOAT_PLANKS.get());
		return isValidBelowState && isValidTopState;
	}
}