package fr.factionbedrock.aerialhell.Block.Trophies;

import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import net.minecraft.block.*;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;

public class BottomSlabLikeTrophyBlock extends Block
{
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;

    public BottomSlabLikeTrophyBlock(AbstractBlock.Settings settings) {super(settings); this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH));}

    protected static final VoxelShape BOTTOM_SLAB_SHAPE = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D);

    @Override protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {return BOTTOM_SLAB_SHAPE;}

    @Override public BlockState getPlacementState(ItemPlacementContext context) {return this.getDefaultState().with(FACING, context.getHorizontalPlayerFacing());}
    @Override protected void appendProperties(StateManager.Builder<Block, BlockState> stateBuilder) {stateBuilder.add(FACING);}

    @Override public boolean canPathfindThrough(BlockState state, NavigationType type) {return false;}

    @Override public boolean canPlaceAt(BlockState state, WorldView level, BlockPos pos) {return level.getBlockState(pos.down()).isSolid() && !level.getBlockState(pos.down()).isIn(AerialHellTags.Blocks.TROPHIES);}
}
