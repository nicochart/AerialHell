package fr.factionbedrock.aerialhell.Block.CollisionCondition;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class IntangibleTemporaryBlock extends CollisionConditionHalfTransparentBlock
{
    @Nullable private BlockState beforeState;
    private final int lifetime = 1;
    private int tickcount;

    public IntangibleTemporaryBlock(Properties properties)
    {
        super(properties);
        this.tickcount = 0;
        this.beforeState = null;
    }

    @Override protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource rand)
    {
        if (state.getBlock() != this) {this.setBeforeState(state);}
    }

    @Override protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource rand)
    {
        this.tickcount++;
        if (this.tickcount >= this.lifetime)
        {
            level.setBlock(pos, this.getNextBlockState(), 2);
        }
    }

    @Override protected boolean canEntityCollide(Entity entity) {return false;}

    @Override protected VoxelShape getCollidingShape() {return FULL_COLLISION_SHAPE;}

    public void setBeforeState(@Nullable BlockState state)
    {
        System.out.println("Setting before state "+state);
        beforeState = state;
    }

    public void resetTickCount() {this.tickcount = 0;}

    private BlockState getNextBlockState()
    {
        return beforeState == null || beforeState.getBlock() != AerialHellBlocksAndItems.INTANGIBLE_TEMPORARY_BLOCK.get() ? Blocks.AIR.defaultBlockState() : beforeState;
    }
}
