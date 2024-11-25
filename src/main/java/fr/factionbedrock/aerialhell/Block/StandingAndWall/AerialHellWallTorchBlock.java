package fr.factionbedrock.aerialhell.Block.StandingAndWall;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

import com.sun.jdi.Mirror;
import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;

import java.util.Map;

import net.minecraft.block.*;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

/*Copy of net.minecraft.block.WallTorchBlock, removing smoke particles, and editing the way particles are added*/

public class AerialHellWallTorchBlock extends AerialHellTorchBlock
{
	public static final DirectionProperty HORIZONTAL_FACING = HorizontalFacingBlock.FACING;
	private static final Map<Direction, VoxelShape> SHAPES = Maps.newEnumMap(ImmutableMap.of(Direction.NORTH, Block.createCuboidShape(5.5D, 3.0D, 11.0D, 10.5D, 13.0D, 16.0D), Direction.SOUTH, Block.createCuboidShape(5.5D, 3.0D, 0.0D, 10.5D, 13.0D, 5.0D), Direction.WEST, Block.createCuboidShape(11.0D, 3.0D, 5.5D, 16.0D, 13.0D, 10.5D), Direction.EAST, Block.createCuboidShape(0.0D, 3.0D, 5.5D, 5.0D, 13.0D, 10.5D)));

	public AerialHellWallTorchBlock(AbstractBlock.Settings settings)
	{
		super(settings);
		this.setDefaultState(this.stateManager.getDefaultState().with(HORIZONTAL_FACING, Direction.NORTH));
	}

	@Override public String getTranslationKey() {return this.asItem().getTranslationKey();}

	@Override protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context)
	{
		return getShapeForState(state);
	}

	public static VoxelShape getShapeForState(BlockState state) {return SHAPES.get(state.get(HORIZONTAL_FACING));}

	@Override public boolean canPlaceAt(BlockState state, WorldView worldIn, BlockPos pos)
	{
		Direction direction = state.get(HORIZONTAL_FACING);
		BlockPos blockpos = pos.offset(direction.getOpposite());
		BlockState blockstate = worldIn.getBlockState(blockpos);
		return blockstate.isSideSolidFullSquare(worldIn, blockpos, direction);
	}

	@Nullable
	public BlockState getPlacementState(ItemPlacementContext context)
	{
		BlockState blockstate = this.getDefaultState();
		WorldView iworldreader = context.getWorld();
		BlockPos blockpos = context.getBlockPos();
		Direction[] adirection = context.getPlacementDirections();

		for(Direction direction : adirection)
		{
			if (direction.getAxis().isHorizontal())
			{
				Direction direction1 = direction.getOpposite();
				blockstate = blockstate.with(HORIZONTAL_FACING, direction1);
				if (blockstate.canPlaceAt(iworldreader, blockpos)) {return blockstate;}
			}
		}
		return null;
	}

	public BlockState getStateForNeighborUpdate(BlockState stateIn, Direction facing, BlockState facingState, WorldAccess world, BlockPos currentPos, BlockPos facingPos)
	{
		return facing.getOpposite() == stateIn.get(HORIZONTAL_FACING) && !stateIn.canPlaceAt(world, currentPos) ? Blocks.AIR.getDefaultState() : stateIn;
	}

	public void randomDisplayTick(BlockState stateIn, World world, BlockPos pos, Random rand)
	{
		Direction direction = stateIn.get(HORIZONTAL_FACING);
		Direction direction1 = direction.getOpposite();
		double d0 = (double)pos.getX() + 0.5D + 0.27D * (double)direction1.getOffsetX();
		double d1 = (double)pos.getY() + 0.7D + 0.22D;
		double d2 = (double)pos.getZ() + 0.5D + 0.27D * (double)direction1.getOffsetZ();
		if (this == AerialHellBlocks.FLUORITE_WALL_TORCH && rand.nextInt(5) == 0)
      	{
			world.addParticle(AerialHellParticleTypes.OSCILLATOR, d0 + 0.5 * (rand.nextFloat() - 0.5), d1 - 0.2 * rand.nextFloat(), d2 + 0.5 * (rand.nextFloat() - 0.5), rand.nextFloat() - 0.5, rand.nextFloat() - 0.5, rand.nextFloat() - 0.5);
      	}
		else if (this == AerialHellBlocks.SHADOW_WALL_TORCH && rand.nextInt(5) == 0)
		{
			world.addParticle(AerialHellParticleTypes.SHADOW_LIGHT, d0 + 0.5 * (rand.nextFloat() - 0.5), d1 - 0.2 * rand.nextFloat(), d2 + 0.5 * (rand.nextFloat() - 0.5), rand.nextFloat() - 0.5, 0.1, rand.nextFloat() - 0.5);
		}
	}

	@Override protected BlockState rotate(BlockState state, BlockRotation rotation)
	{
		return state.with(HORIZONTAL_FACING, rotation.rotate(state.get(HORIZONTAL_FACING)));
	}

	@Override protected BlockState mirror(BlockState state, BlockMirror mirror)
	{
		return state.rotate(mirror.getRotation(state.get(HORIZONTAL_FACING)));
	}

	@Override protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {builder.add(HORIZONTAL_FACING);}
}
