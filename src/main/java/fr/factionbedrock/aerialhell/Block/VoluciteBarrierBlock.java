package fr.factionbedrock.aerialhell.Block;

import fr.factionbedrock.aerialhell.BlockEntity.VoluciteBarrierBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.TransparentBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.redstone.Orientation;
import org.jetbrains.annotations.Nullable;

public class VoluciteBarrierBlock extends TransparentBlock implements EntityBlock
{
    public VoluciteBarrierBlock(Properties properties) {super(properties);}

    @Nullable @Override public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {return new VoluciteBarrierBlockEntity(pos, state);}

    //a neighbor changed : the visible faces of the block entity renderer must be computed again (see VoluciteBarrierBlockEntity.onNeighborUpdate)
    @Override protected BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess ticks, BlockPos pos, Direction directionToNeighbour, BlockPos neighbourPos, BlockState neighbourState, RandomSource random)
    {
        notifyNeighborUpdate(level, pos);
        return super.updateShape(state, level, ticks, pos, directionToNeighbour, neighbourPos, neighbourState, random);
    }

    @Override protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, @Nullable Orientation orientation, boolean movedByPiston)
    {
        notifyNeighborUpdate(level, pos);
        super.neighborChanged(state, level, pos, block, orientation, movedByPiston);
    }

    //only in a real level (not during world generation)
    private static void notifyNeighborUpdate(LevelReader level, BlockPos pos)
    {
        if (level instanceof Level realLevel && realLevel.getBlockEntity(pos) instanceof VoluciteBarrierBlockEntity barrier) {barrier.onNeighborUpdate();}
    }
}
