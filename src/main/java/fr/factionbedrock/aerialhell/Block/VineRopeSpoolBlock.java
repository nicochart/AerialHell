package fr.factionbedrock.aerialhell.Block;

import net.minecraft.block.*;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class VineRopeSpoolBlock extends PillarBlock
{
    //protected static final VoxelShape SHAPE = Block.createCuboidShape(0.1D, 0.1D, 0.1D, 15.8D, 15.8D, 15.8D);
    protected static final VoxelShape VERTICAL_BOTTOM_SUPPORT = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 3.0D, 16.0D);
    protected static final VoxelShape VERTICAL_TOP_SUPPORT = Block.createCuboidShape(0.0D, 13.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape VERTICAL_SPOOL = Block.createCuboidShape(2.0D, 3.0D, 2.0D, 14.0D, 13.0D, 14.0D);

    protected static final VoxelShape HORIZONTAL_X_BOTTOM_SUPPORT = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 3.0D, 16.0D, 16.0D);
    protected static final VoxelShape HORIZONTAL_X_TOP_SUPPORT = Block.createCuboidShape(13.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape HORIZONTAL_X_SPOOL = Block.createCuboidShape(3.0D, 2.0D, 2.0D, 13.0D, 14.0D, 14.0D);

    protected static final VoxelShape HORIZONTAL_Z_BOTTOM_SUPPORT = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 3.0D);
    protected static final VoxelShape HORIZONTAL_Z_TOP_SUPPORT = Block.createCuboidShape(0.0D, 0.0D, 13.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape HORIZONTAL_Z_SPOOL = Block.createCuboidShape(2.0D, 2.0D, 3.0D, 14.0D, 14.0D, 13.0D);

    public VineRopeSpoolBlock(AbstractBlock.Settings settings) {super(settings);}

    public VoxelShape getOutlineShape(BlockState state, BlockView level, BlockPos pos, ShapeContext context)
    {
        if (state.get(AXIS) == Direction.Axis.Y)
        {
            return VoxelShapes.union(VERTICAL_BOTTOM_SUPPORT, VERTICAL_TOP_SUPPORT, VERTICAL_SPOOL);
        }
        else if (state.get(AXIS) == Direction.Axis.X)
        {
            return VoxelShapes.union(HORIZONTAL_X_BOTTOM_SUPPORT, HORIZONTAL_X_TOP_SUPPORT, HORIZONTAL_X_SPOOL);
        }
        else //if (state.get(AXIS) == Direction.Axis.Z)
        {
            return VoxelShapes.union(HORIZONTAL_Z_BOTTOM_SUPPORT, HORIZONTAL_Z_TOP_SUPPORT, HORIZONTAL_Z_SPOOL);
        }
    }

    @Override public boolean canPathfindThrough(BlockState state, NavigationType type) {return false;}
}
