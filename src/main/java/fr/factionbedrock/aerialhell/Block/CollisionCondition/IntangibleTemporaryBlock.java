package fr.factionbedrock.aerialhell.Block.CollisionCondition;

import com.mojang.serialization.MapCodec;
import fr.factionbedrock.aerialhell.BlockEntity.IntangibleTemporaryBlockEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlockEntities;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class IntangibleTemporaryBlock extends CollisionConditionHalfTransparentBlockEntity
{
    public static final MapCodec<IntangibleTemporaryBlock> CODEC = simpleCodec(IntangibleTemporaryBlock::new);

    public IntangibleTemporaryBlock(Properties properties) {super(properties);}

    @Override protected MapCodec<? extends IntangibleTemporaryBlock> codec() {return CODEC;}

    @Nullable @Override public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {return new IntangibleTemporaryBlockEntity(pos, state);}

    @Override public void livingEntityInside(BlockState state, Level level, BlockPos pos, LivingEntity entity)
    {
        EntityHelper.multiplyDeltaMovement(entity, default_living_entity_xz_delta_movement_factor, 1.0F);
        if (level.getBlockEntity(pos) instanceof IntangibleTemporaryBlockEntity blockentity) {blockentity.resetTickCount();}
        if (level.getBlockEntity(pos.above()) instanceof IntangibleTemporaryBlockEntity blockentity) {blockentity.resetTickCount();}
    }

    @Override public void nonLivingEntityInside(BlockState state, Level level, BlockPos pos, Entity entity)
    {
        EntityHelper.multiplyDeltaMovement(entity, default_non_living_entity_xz_delta_movement_factor, 0.05);
    }

    @Nullable @Override public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type)
    {
        return level.isClientSide ? null : createTickerHelper(type, AerialHellBlockEntities.INTANGIBLE_TEMPORARY_BLOCK.get(), IntangibleTemporaryBlockEntity::tick);
    }

    @Override protected boolean canEntityCollide(Entity entity) {return false;}

    @Override protected VoxelShape getCollidingShape() {return FULL_COLLISION_SHAPE;}
}
