package fr.factionbedrock.aerialhell.BlockEntity;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

//no data
//only used for rendering (see VoluciteBarrierRenderer)
public class VoluciteBarrierBlockEntity extends BlockEntity
{
    public VoluciteBarrierBlockEntity(BlockPos pos, BlockState state) {super(AerialHellBlockEntities.VOLUCITE_BARRIER.get(), pos, state);}

    public boolean shouldRenderFace(Direction direction)
    {
        Level level = this.getLevel();
        if (level == null) {return true;}
        BlockPos neighborPos = this.getBlockPos().relative(direction);
        return Block.shouldRenderFace(level, this.getBlockPos(), this.getBlockState(), level.getBlockState(neighborPos), direction);
    }
}
