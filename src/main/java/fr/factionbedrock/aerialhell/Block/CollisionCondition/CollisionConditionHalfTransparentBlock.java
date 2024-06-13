package fr.factionbedrock.aerialhell.Block.CollisionCondition;

import com.mojang.authlib.minecraft.TelemetrySession;
import fr.factionbedrock.aerialhell.Entity.Monster.Pirate.GhostSlimePirateEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.Pirate.SlimePirateEntity;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HalfTransparentBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public abstract class CollisionConditionHalfTransparentBlock extends HalfTransparentBlock
{
	protected final static double default_living_entity_xz_delta_movement_factor = 0.96, default_non_living_entity_xz_delta_movement_factor = 0.85, default_y_delta_movement_factor = 0.002;
	protected final static VoxelShape EMPTY_SHAPE = Shapes.empty();
	protected final static VoxelShape FULL_COLLISION_SHAPE = Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 16.0);

	public CollisionConditionHalfTransparentBlock(Properties properties)
	{
		super(properties.isRedstoneConductor((state, blockGetter, pos) -> false).isSuffocating((state, blockGetter, pos) -> false).isViewBlocking((state, blockGetter, pos) -> true));
	}
	
	@Override public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity)
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
		double y_delta_movement_factor = entity.getDeltaMovement().y < 0.1D ? 0.8D : default_y_delta_movement_factor;
		EntityHelper.multiplyDeltaMovement(entity, default_living_entity_xz_delta_movement_factor, y_delta_movement_factor);
	}

	public void nonLivingEntityInside(BlockState state, Level level, BlockPos pos, Entity entity)
	{
		EntityHelper.multiplyDeltaMovement(entity, default_non_living_entity_xz_delta_movement_factor, default_y_delta_movement_factor);
	}

	@Override public boolean useShapeForLightOcclusion(BlockState state) {return true;}

	@Override public VoxelShape getCollisionShape(BlockState state, BlockGetter blockGetter, BlockPos pos, CollisionContext context)
	{
		if (context instanceof EntityCollisionContext)
		{
			Entity entity = ((EntityCollisionContext)context).getEntity();
			if (canEntityCollide(entity)) {return getCollidingShape();}
			else {return EMPTY_SHAPE;}
		}
		else
		{
			return super.getCollisionShape(state, blockGetter, pos, context);
		}
	}

	protected abstract boolean canEntityCollide(Entity entity);
	protected abstract VoxelShape getCollidingShape();

	@Override public VoxelShape getVisualShape(BlockState state, BlockGetter blockGetter, BlockPos pos, CollisionContext context) {return Shapes.empty();}
}