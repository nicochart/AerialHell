package fr.factionbedrock.aerialhell.Block.CorruptionProtectors;

import com.mojang.serialization.MapCodec;
import fr.factionbedrock.aerialhell.BlockEntity.BiomeShifter;
import fr.factionbedrock.aerialhell.BlockEntity.ReactorBlockEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;

public class ReactorBlock extends BiomeShifterBlock
{
    public static final MapCodec<ReactorBlock> CODEC = simpleCodec(ReactorBlock::new);

    private ReactorBlock(Properties prop) {this(prop, BiomeShifter.MAX_PROTECTION_DISTANCE);}
    public ReactorBlock(Properties prop, int fieldSize) {super(prop, fieldSize);}

    @Override protected MapCodec<? extends ReactorBlock> codec() {return CODEC;}

    @Nullable @Override public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {return new ReactorBlockEntity(pos, state, this.fieldSize);}

    @Override protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult)
    {
        if (level.isClientSide) {return InteractionResult.SUCCESS;}
        else
        {
            this.openContainer(level, pos, player);
            return InteractionResult.CONSUME;
        }
    }

    protected void openContainer(Level level, BlockPos pos, Player player)
    {
        BlockEntity blockentity = level.getBlockEntity(pos);
        if (blockentity instanceof ReactorBlockEntity)
        {
            player.openMenu((MenuProvider)blockentity);
        }
    }

    @Override protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving)
    {
        Containers.dropContentsOnDestroy(state, newState, level, pos);
        super.onRemove(state, level, pos, newState, isMoving);
    }

    @Nullable @Override public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type)
    {
        return level.isClientSide ? null : createTickerHelper(type, AerialHellBlockEntities.REACTOR.get(), ReactorBlockEntity::tick);
    }
}
