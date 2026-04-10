package fr.factionbedrock.aerialhell.Block.CollisionCondition;

import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public abstract class CollisionConditionHalfTransparentBlockEntity extends BaseEntityBlock
{
    protected final static double default_living_entity_xz_delta_movement_factor = 0.96, default_non_living_entity_xz_delta_movement_factor = 0.85, default_y_delta_movement_factor = 0.002;

    protected CollisionConditionHalfTransparentBlockEntity(BlockBehaviour.Properties settings)
    {
        super(settings.isRedstoneConductor((state, blockGetter, pos) -> false).isSuffocating((state, blockGetter, pos) -> false).isViewBlocking((state, blockGetter, pos) -> true));
    }

    @Override protected RenderShape getRenderShape(BlockState pState) {return RenderShape.MODEL;}

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
        double y_delta_movement_factor = entity.getDeltaMovement().y < 0.1D ? 0.8D : default_y_delta_movement_factor;
        EntityHelper.multiplyDeltaMovement(entity, default_living_entity_xz_delta_movement_factor, y_delta_movement_factor);
    }

    public void nonLivingEntityInside(BlockState state, Level world, BlockPos pos, Entity entity)
    {
        EntityHelper.multiplyDeltaMovement(entity, default_non_living_entity_xz_delta_movement_factor, default_y_delta_movement_factor);
    }

    @Override public VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context)
    {
        if (context instanceof EntityCollisionContext)
        {
            Entity entity = ((EntityCollisionContext)context).getEntity();
            if (canEntityCollide(entity) || entity == null) {return getCollidingShape();}
            else {return CollisionConditionHalfTransparentBlock.EMPTY_SHAPE;}
        }
        else
        {
            return super.getCollisionShape(state, world, pos, context);
        }
    }

    //copy from HalfTransparentBlock
    @Override protected boolean skipRendering(BlockState state, BlockState adjacentBlockState, Direction side)
    {
        return adjacentBlockState.is(this) ? true : super.skipRendering(state, adjacentBlockState, side);
    }

    protected abstract boolean canEntityCollide(Entity entity);
    protected abstract VoxelShape getCollidingShape();

    @Override public VoxelShape getVisualShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {return Shapes.empty();}
}
