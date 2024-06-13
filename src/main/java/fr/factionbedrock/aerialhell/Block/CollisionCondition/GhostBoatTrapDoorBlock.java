
package fr.factionbedrock.aerialhell.Block.CollisionCondition;

import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class GhostBoatTrapDoorBlock extends TrapDoorBlock
{
	public GhostBoatTrapDoorBlock(BlockSetType type, Properties properties)
	{
		super(properties.isRedstoneConductor((state, blockGetter, pos) -> false).isSuffocating((state, blockGetter, pos) -> false), type);
	}

	@Override public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit)
	{
		return (!canEntityCollide(player) && !player.isCreative()) ? InteractionResult.SUCCESS : super.use(state, level, pos, player, handIn, hit);
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
		if (context instanceof EntityCollisionContext)
		{
			Entity entity = ((EntityCollisionContext)context).getEntity();
			if (canEntityCollide(entity)) {return super.getCollisionShape(state, blockGetter, pos, context);}
		}
		return CollisionConditionHalfTransparentBlock.EMPTY_SHAPE;
	}

	protected boolean canEntityCollide(Entity entity) {return !EntityHelper.isImmuneToGhostBlockCollision(entity);}

	@Override public VoxelShape getVisualShape(BlockState state, BlockGetter blockGetter, BlockPos pos, CollisionContext context) {return Shapes.empty();}
}