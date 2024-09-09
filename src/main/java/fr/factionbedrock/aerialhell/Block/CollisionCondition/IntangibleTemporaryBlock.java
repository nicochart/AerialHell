package fr.factionbedrock.aerialhell.Block.CollisionCondition;

import com.mojang.serialization.MapCodec;
import fr.factionbedrock.aerialhell.BlockEntity.IntangibleTemporaryBlockEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
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

    @Nullable @Override public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type)
    {
        return level.isClientSide ? null : createTickerHelper(type, AerialHellBlockEntities.INTANGIBLE_TEMPORARY_BLOCK.get(), IntangibleTemporaryBlockEntity::tick);
    }

    @Override protected boolean canEntityCollide(Entity entity) {return false;}

    @Override protected VoxelShape getCollidingShape() {return FULL_COLLISION_SHAPE;}
}
