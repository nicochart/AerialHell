package fr.factionbedrock.aerialhell.Block.Trophies;

import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BottomSlabLikeTrophyBlock extends Block
{
    public static final EnumProperty<Direction> FACING = HorizontalDirectionalBlock.FACING;

    public BottomSlabLikeTrophyBlock(Properties prop) {super(prop); this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));}

    protected static final VoxelShape BOTTOM_SLAB_SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D);

    @Override public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {return BOTTOM_SLAB_SHAPE;}

    @Override public BlockState getStateForPlacement(BlockPlaceContext context) {return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection());}
    @Override protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {stateBuilder.add(FACING);}

    @Override public boolean isPathfindable(BlockState pState, PathComputationType pPathComputationType) {return false;}

    @Override public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {return level.getBlockState(pos.below()).isSolid() && !level.getBlockState(pos.below()).is(AerialHellTags.Blocks.TROPHIES);}
}
