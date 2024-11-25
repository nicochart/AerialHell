package fr.factionbedrock.aerialhell.Block;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.*;
import net.minecraft.block.enums.BlockHalf;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;

public class LargeDeadLogBlock extends Block
{
    public static final MapCodec<LargeDeadLogBlock> CODEC = RecordCodecBuilder.mapCodec((instance) -> {
        return instance.group(BlockState.CODEC.fieldOf("base_state").forGetter((largeDeadLogBlock) -> {return largeDeadLogBlock.baseState;}), createSettingsCodec()).apply(instance, LargeDeadLogBlock::new);
    });

    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    public static final EnumProperty<BlockHalf> HALF = Properties.BLOCK_HALF;
    protected static final VoxelShape TOP = Block.createCuboidShape(0.0D, 14.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape BOTTOM = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D);
    protected static final VoxelShape NORTH = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 2.0D);
    protected static final VoxelShape SOUTH = Block.createCuboidShape(0.0D, 0.0D, 14.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape EAST = Block.createCuboidShape(14.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape WEST = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 2.0D, 16.0D, 16.0D);
    private final Block base;
    protected final BlockState baseState;

    @Override public MapCodec<? extends LargeDeadLogBlock> getCodec() {return CODEC;}

    public LargeDeadLogBlock(BlockState state, AbstractBlock.Settings settings)
    {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH).with(HALF, BlockHalf.BOTTOM));
        this.base = state.getBlock();
        this.baseState = state;
    }

    @Override public VoxelShape getOutlineShape(BlockState state, BlockView blockGetter, BlockPos pos, ShapeContext context)
    {
        BlockHalf half = state.get(HALF);
        Direction facing = state.get(FACING);
        VoxelShape BottomOrTopShape = half == BlockHalf.BOTTOM ? BOTTOM : TOP;
        VoxelShape FacingShape = facing == Direction.NORTH ? NORTH : facing == Direction.SOUTH ? SOUTH : facing == Direction.WEST ? WEST : EAST;
        return VoxelShapes.union(BottomOrTopShape, FacingShape);
    }

    @Override public BlockState getPlacementState(ItemPlacementContext placeContext)
    {
        Direction direction = placeContext.getSide();
        BlockPos blockpos = placeContext.getBlockPos();
        BlockState blockstate = getStateForPlacement(placeContext.getHorizontalPlayerFacing(), direction != Direction.DOWN && (direction == Direction.UP || !(placeContext.getBlockPos().getY() - (double)blockpos.getY() > 0.5D)) ? BlockHalf.BOTTOM : BlockHalf.TOP);
        return blockstate;
    }

    public BlockState getStateForPlacement(Direction direction, BlockHalf half)
    {
        return this.getDefaultState().with(FACING, direction).with(HALF, half);
    }

    @Override public BlockState getStateForNeighborUpdate(BlockState previousState, Direction direction, BlockState newState, WorldAccess level, BlockPos pos1, BlockPos pos2)
    {
        return direction.getAxis().isHorizontal() ? previousState : super.getStateForNeighborUpdate(previousState, direction, newState, level, pos1, pos2);
    }

    @Override public BlockState rotate(BlockState state, BlockRotation rotation)
    {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override public BlockState mirror(BlockState state, BlockMirror mirror)
    {
        Direction direction = state.get(FACING);
        switch (mirror) {
            case LEFT_RIGHT:
                if (direction.getAxis() == Direction.Axis.Z) {return state.rotate(BlockRotation.CLOCKWISE_180);}
                break;
            case FRONT_BACK:
                if (direction.getAxis() == Direction.Axis.X) {return state.rotate(BlockRotation.CLOCKWISE_180);}
        }
        return super.mirror(state, mirror);
    }

    @Override protected void appendProperties(StateManager.Builder<Block, BlockState> state) {state.add(FACING, HALF);}

    @Override public boolean canPathfindThrough(BlockState state, NavigationType type) {return false;}
}
