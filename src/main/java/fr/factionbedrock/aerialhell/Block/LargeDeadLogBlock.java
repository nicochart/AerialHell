package fr.factionbedrock.aerialhell.Block;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class LargeDeadLogBlock extends Block
{
    public static final MapCodec<LargeDeadLogBlock> CODEC = RecordCodecBuilder.mapCodec((instance) -> {
        return instance.group(BlockState.CODEC.fieldOf("base_state").forGetter((largeDeadLogBlock) -> {return largeDeadLogBlock.baseState;}), propertiesCodec()).apply(instance, LargeDeadLogBlock::new);
    });

    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final EnumProperty<Half> HALF = BlockStateProperties.HALF;
    protected static final VoxelShape TOP = Block.box(0.0D, 14.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape BOTTOM = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D);
    protected static final VoxelShape NORTH = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 2.0D);
    protected static final VoxelShape SOUTH = Block.box(0.0D, 0.0D, 14.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape EAST = Block.box(14.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape WEST = Block.box(0.0D, 0.0D, 0.0D, 2.0D, 16.0D, 16.0D);
    private final Block base;
    protected final BlockState baseState;

    @Override public MapCodec<? extends LargeDeadLogBlock> codec() {return CODEC;}

    public LargeDeadLogBlock(BlockState state, BlockBehaviour.Properties prop)
    {
        super(prop);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(HALF, Half.BOTTOM));
        this.base = state.getBlock();
        this.baseState = state;
    }

    @Override public boolean useShapeForLightOcclusion(BlockState state) {return true;}

    @Override public VoxelShape getShape(BlockState state, BlockGetter blockGetter, BlockPos pos, CollisionContext context)
    {
        Half half = state.getValue(HALF);
        Direction facing = state.getValue(FACING);
        VoxelShape BottomOrTopShape = half == Half.BOTTOM ? BOTTOM : TOP;
        VoxelShape FacingShape = facing == Direction.NORTH ? NORTH : facing == Direction.SOUTH ? SOUTH : facing == Direction.WEST ? WEST : EAST;
        return Shapes.or(BottomOrTopShape, FacingShape);
    }

    @Override public float getExplosionResistance() {return this.base.getExplosionResistance();}

    @Override public BlockState getStateForPlacement(BlockPlaceContext placeContext)
    {
        Direction direction = placeContext.getClickedFace();
        BlockPos blockpos = placeContext.getClickedPos();
        BlockState blockstate = this.defaultBlockState().setValue(FACING, placeContext.getHorizontalDirection()).setValue(HALF, direction != Direction.DOWN && (direction == Direction.UP || !(placeContext.getClickLocation().y - (double)blockpos.getY() > 0.5D)) ? Half.BOTTOM : Half.TOP);
        return blockstate;
    }

    @Override public BlockState updateShape(BlockState previousState, Direction direction, BlockState newState, LevelAccessor level, BlockPos pos1, BlockPos pos2)
    {
        return direction.getAxis().isHorizontal() ? previousState : super.updateShape(previousState, direction, newState, level, pos1, pos2);
    }

    @Override public BlockState rotate(BlockState state, Rotation rotation)
    {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override public BlockState mirror(BlockState state, Mirror mirror)
    {
        Direction direction = state.getValue(FACING);
        switch (mirror) {
            case LEFT_RIGHT:
                if (direction.getAxis() == Direction.Axis.Z) {return state.rotate(Rotation.CLOCKWISE_180);}
                break;
            case FRONT_BACK:
                if (direction.getAxis() == Direction.Axis.X) {return state.rotate(Rotation.CLOCKWISE_180);}
        }
        return super.mirror(state, mirror);
    }

    @Override protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> state) {state.add(FACING, HALF);}

    @Override public boolean isPathfindable(BlockState state, BlockGetter blockGetter, BlockPos pos, PathComputationType pathComputationType) {return false;}
}
