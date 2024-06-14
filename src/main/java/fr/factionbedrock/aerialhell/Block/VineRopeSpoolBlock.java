package fr.factionbedrock.aerialhell.Block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class VineRopeSpoolBlock extends RotatedPillarBlock
{
    //protected static final VoxelShape SHAPE = Block.box(0.1D, 0.1D, 0.1D, 15.8D, 15.8D, 15.8D);
    protected static final VoxelShape VERTICAL_BOTTOM_SUPPORT = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 3.0D, 16.0D);
    protected static final VoxelShape VERTICAL_TOP_SUPPORT = Block.box(0.0D, 13.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape VERTICAL_SPOOL = Block.box(2.0D, 3.0D, 2.0D, 14.0D, 13.0D, 14.0D);

    protected static final VoxelShape HORIZONTAL_X_BOTTOM_SUPPORT = Block.box(0.0D, 0.0D, 0.0D, 3.0D, 16.0D, 16.0D);
    protected static final VoxelShape HORIZONTAL_X_TOP_SUPPORT = Block.box(13.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape HORIZONTAL_X_SPOOL = Block.box(3.0D, 2.0D, 2.0D, 13.0D, 14.0D, 14.0D);

    protected static final VoxelShape HORIZONTAL_Z_BOTTOM_SUPPORT = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 3.0D);
    protected static final VoxelShape HORIZONTAL_Z_TOP_SUPPORT = Block.box(0.0D, 0.0D, 13.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape HORIZONTAL_Z_SPOOL = Block.box(2.0D, 2.0D, 3.0D, 14.0D, 14.0D, 13.0D);

    public VineRopeSpoolBlock(Properties prop) {super(prop);}

    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context)
    {
        if (state.getValue(AXIS) == Direction.Axis.Y)
        {
            return Shapes.or(VERTICAL_BOTTOM_SUPPORT, VERTICAL_TOP_SUPPORT, VERTICAL_SPOOL);
        }
        else if (state.getValue(AXIS) == Direction.Axis.X)
        {
            return Shapes.or(HORIZONTAL_X_BOTTOM_SUPPORT, HORIZONTAL_X_TOP_SUPPORT, HORIZONTAL_X_SPOOL);
        }
        else //if (state.getValue(AXIS) == Direction.Axis.Z)
        {
            return Shapes.or(HORIZONTAL_Z_BOTTOM_SUPPORT, HORIZONTAL_Z_TOP_SUPPORT, HORIZONTAL_Z_SPOOL);
        }
    }

    @Override public boolean isPathfindable(BlockState state, PathComputationType pathComputationType) {return false;}
}
