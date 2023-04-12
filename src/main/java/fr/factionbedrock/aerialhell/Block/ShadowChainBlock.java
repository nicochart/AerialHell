package fr.factionbedrock.aerialhell.Block;

import fr.factionbedrock.aerialhell.Entity.Monster.ShadowAutomatonEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.ShadowTrollEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.ChainBlock;
import net.minecraft.pathfinding.PathType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

public class ShadowChainBlock extends ChainBlock
{
    protected final static VoxelShape EMPTY_SHAPE = VoxelShapes.empty();
    public ShadowChainBlock(Properties properties) {super(properties);}

    @Override public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        if (context.getEntity() instanceof ShadowTrollEntity || context.getEntity() instanceof ShadowAutomatonEntity) {return EMPTY_SHAPE;}
        else {return super.getCollisionShape(state, worldIn, pos, context);}
    }

    @Override public boolean allowsMovement(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type) {return true;}
}
