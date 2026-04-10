package fr.factionbedrock.aerialhell.Block.StandingAndWall;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

import com.sun.jdi.Mirror;
import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;

import java.util.Map;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

/*Copy of net.minecraft.block.WallTorchBlock, removing smoke particles, and editing the way particles are added*/

public class AerialHellWallTorchBlock extends AerialHellTorchBlock
{
	public static final EnumProperty<Direction> HORIZONTAL_FACING = HorizontalDirectionalBlock.FACING;
	private static final Map<Direction, VoxelShape> SHAPES = Maps.newEnumMap(ImmutableMap.of(Direction.NORTH, Block.box(5.5D, 3.0D, 11.0D, 10.5D, 13.0D, 16.0D), Direction.SOUTH, Block.box(5.5D, 3.0D, 0.0D, 10.5D, 13.0D, 5.0D), Direction.WEST, Block.box(11.0D, 3.0D, 5.5D, 16.0D, 13.0D, 10.5D), Direction.EAST, Block.box(0.0D, 3.0D, 5.5D, 5.0D, 13.0D, 10.5D)));

	public AerialHellWallTorchBlock(BlockBehaviour.Properties settings)
	{
		super(settings);
		this.registerDefaultState(this.stateDefinition.any().setValue(HORIZONTAL_FACING, Direction.NORTH));
	}

	@Override protected VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context)
	{
		return getShapeForState(state);
	}

	public static VoxelShape getShapeForState(BlockState state) {return SHAPES.get(state.getValue(HORIZONTAL_FACING));}

	@Override public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos)
	{
		Direction direction = state.getValue(HORIZONTAL_FACING);
		BlockPos blockpos = pos.relative(direction.getOpposite());
		BlockState blockstate = worldIn.getBlockState(blockpos);
		return blockstate.isFaceSturdy(worldIn, blockpos, direction);
	}

	@Nullable
	public BlockState getStateForPlacement(BlockPlaceContext context)
	{
		BlockState blockstate = this.defaultBlockState();
		LevelReader iworldreader = context.getLevel();
		BlockPos blockpos = context.getClickedPos();
		Direction[] adirection = context.getNearestLookingDirections();

		for(Direction direction : adirection)
		{
			if (direction.getAxis().isHorizontal())
			{
				Direction direction1 = direction.getOpposite();
				blockstate = blockstate.setValue(HORIZONTAL_FACING, direction1);
				if (blockstate.canSurvive(iworldreader, blockpos)) {return blockstate;}
			}
		}
		return null;
	}

	public BlockState getStateForNeighborUpdate(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor world, BlockPos currentPos, BlockPos facingPos)
	{
		return facing.getOpposite() == stateIn.getValue(HORIZONTAL_FACING) && !stateIn.canSurvive(world, currentPos) ? Blocks.AIR.defaultBlockState() : stateIn;
	}

	public void animateTick(BlockState stateIn, Level world, BlockPos pos, RandomSource rand)
	{
		Direction direction = stateIn.getValue(HORIZONTAL_FACING);
		Direction direction1 = direction.getOpposite();
		double d0 = (double)pos.getX() + 0.5D + 0.27D * (double)direction1.getStepX();
		double d1 = (double)pos.getY() + 0.7D + 0.22D;
		double d2 = (double)pos.getZ() + 0.5D + 0.27D * (double)direction1.getStepZ();
		if (this == AerialHellBlocks.FLUORITE_WALL_TORCH && rand.nextInt(5) == 0)
      	{
			world.addParticle(AerialHellParticleTypes.OSCILLATOR, d0 + 0.5 * (rand.nextFloat() - 0.5), d1 - 0.2 * rand.nextFloat(), d2 + 0.5 * (rand.nextFloat() - 0.5), rand.nextFloat() - 0.5, rand.nextFloat() - 0.5, rand.nextFloat() - 0.5);
      	}
		else if (this == AerialHellBlocks.SHADOW_WALL_TORCH && rand.nextInt(5) == 0)
		{
			world.addParticle(AerialHellParticleTypes.SHADOW_LIGHT, d0 + 0.5 * (rand.nextFloat() - 0.5), d1 - 0.2 * rand.nextFloat(), d2 + 0.5 * (rand.nextFloat() - 0.5), rand.nextFloat() - 0.5, 0.1, rand.nextFloat() - 0.5);
		}
	}

	@Override protected BlockState rotate(BlockState state, Rotation rotation)
	{
		return state.setValue(HORIZONTAL_FACING, rotation.rotate(state.getValue(HORIZONTAL_FACING)));
	}

	@Override protected BlockState mirror(BlockState state, net.minecraft.world.level.block.Mirror mirror)
	{
		return state.rotate(mirror.getRotation(state.getValue(HORIZONTAL_FACING)));
	}

	@Override protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {builder.add(HORIZONTAL_FACING);}
}
