package fr.factionbedrock.aerialhell.Block.CollisionCondition;

import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public abstract class CollisionConditionHalfTransparentBlock extends TranslucentBlock
{
	protected final static double default_living_entity_xz_delta_movement_factor = 0.96, default_non_living_entity_xz_delta_movement_factor = 0.85, default_y_delta_movement_factor = 0.002;
	protected final static VoxelShape EMPTY_SHAPE = VoxelShapes.empty();
	protected final static VoxelShape FULL_COLLISION_SHAPE = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 16.0);

	public CollisionConditionHalfTransparentBlock(AbstractBlock.Settings settings)
	{
		super(settings.solidBlock((state, blockGetter, pos) -> false).suffocates((state, blockGetter, pos) -> false).blockVision((state, blockGetter, pos) -> true));
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
		double y_delta_movement_factor = entity.getVelocity().y < 0.1D ? 0.8D : default_y_delta_movement_factor;
		EntityHelper.multiplyDeltaMovement(entity, default_living_entity_xz_delta_movement_factor, y_delta_movement_factor);
	}

	public void nonLivingEntityInside(BlockState state, World world, BlockPos pos, Entity entity)
	{
		EntityHelper.multiplyDeltaMovement(entity, default_non_living_entity_xz_delta_movement_factor, default_y_delta_movement_factor);
	}

	@Override public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context)
	{
		if (context instanceof EntityShapeContext entityShapeContext && entityShapeContext.getEntity() != null)
		{
			Entity entity = entityShapeContext.getEntity();
			if (canEntityCollide(entity)) {return getCollidingShape();}
			else {return EMPTY_SHAPE;}
		}
		else
		{
			return super.getCollisionShape(state, world, pos, context);
		}
	}

	protected abstract boolean canEntityCollide(Entity entity);
	protected abstract VoxelShape getCollidingShape();

	@Override public VoxelShape getCameraCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {return VoxelShapes.empty();}
}