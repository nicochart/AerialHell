package fr.factionbedrock.aerialhell.Block.Trophies;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BottomSlabLikeTrophyBlock extends Block
{
    public BottomSlabLikeTrophyBlock(Properties prop) {super(prop);}

    protected static final VoxelShape BOTTOM_SLAB_SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D);

    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {return BOTTOM_SLAB_SHAPE;}
}
