package fr.factionbedrock.aerialhell.Block.CorruptionProtectors;

import com.mojang.serialization.MapCodec;
import fr.factionbedrock.aerialhell.Block.CollisionCondition.IntangibleTemporaryBlock;
import fr.factionbedrock.aerialhell.BlockEntity.CorruptionProtectorBlockEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;

public class CorruptionProtectorBlock extends BaseEntityBlock
{
    public final int protection_distance;
    public static final MapCodec<IntangibleTemporaryBlock> CODEC = simpleCodec(IntangibleTemporaryBlock::new);

    public CorruptionProtectorBlock(Properties prop, int protectionDistance)
    {
        super(prop);
        this.protection_distance = protectionDistance;
    }

    @Override protected MapCodec<? extends IntangibleTemporaryBlock> codec() {return CODEC;}

    @Nullable @Override public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {return new CorruptionProtectorBlockEntity(pos, state, this.protection_distance);}

    @Override protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult)
    {
        if (level.isClientSide) {return InteractionResult.SUCCESS;}
        else
        {
            this.openContainer(level, pos, player);
            return InteractionResult.CONSUME;
        }
    }

    protected void openContainer(Level worldIn, BlockPos pos, Player player)
    {
        BlockEntity blockentity = worldIn.getBlockEntity(pos);
        if (blockentity instanceof CorruptionProtectorBlockEntity)
        {
            player.openMenu((MenuProvider)blockentity);
        }
    }

    @Override protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving)
    {
        Containers.dropContentsOnDestroy(state, newState, level, pos);
        super.onRemove(state, level, pos, newState, isMoving);
    }

    @Override protected RenderShape getRenderShape(BlockState pState) {return RenderShape.MODEL;}

    @Nullable @Override public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type)
    {
        return level.isClientSide ? null : createTickerHelper(type, AerialHellBlockEntities.CORRUPTION_PROTECTOR_BLOCK.get(), CorruptionProtectorBlockEntity::tick);
    }

    @Override protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource rand)
    {
        if (level.getBlockState(pos).getBlock() instanceof CorruptionProtectorBlock) {return;}
    }
}
