package fr.factionbedrock.aerialhell.Block.StandingAndWall;

import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import net.minecraft.block.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.tick.ScheduledTickView;

/*Copy of net.minecraft.block.TorchBlock, removing smoke particles, and editing the way particles are added*/

public class AerialHellTorchBlock extends Block
{
	protected static final VoxelShape SHAPE = Block.createCuboidShape(6.0D, 0.0D, 6.0D, 10.0D, 10.0D, 10.0D);

	public AerialHellTorchBlock(AbstractBlock.Settings settings)
	{
    	super(settings);
	}

	@Override protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {return SHAPE;}

	@Override protected BlockState getStateForNeighborUpdate(BlockState state, WorldView world, ScheduledTickView tickView, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, Random random)
	{
		return direction == Direction.DOWN && !this.canPlaceAt(state, world, pos) ? Blocks.AIR.getDefaultState() : super.getStateForNeighborUpdate(state, world, tickView, pos, direction, neighborPos, neighborState, random);
	}

	@Override public boolean canPlaceAt(BlockState state, WorldView worldIn, BlockPos pos)
   	{
		return sideCoversSmallSquare(worldIn, pos.down(), Direction.UP);
   	}

	public void randomDisplayTick(BlockState stateIn, World world, BlockPos pos, Random rand)
	{
		double d0 = (double)pos.getX() + 0.5D;
      	double d1 = (double)pos.getY() + 0.7D;
      	double d2 = (double)pos.getZ() + 0.5D;
      	if (this == AerialHellBlocks.FLUORITE_TORCH && rand.nextInt(5) == 0)
      	{
      		world.addParticle(AerialHellParticleTypes.OSCILLATOR, d0 + 0.5 * (rand.nextFloat() - 0.5), d1 - 0.2 * rand.nextFloat(), d2 + 0.5 * (rand.nextFloat() - 0.5), rand.nextFloat() - 0.5, rand.nextFloat() - 0.5, rand.nextFloat() - 0.5);
      	}
		else if (this == AerialHellBlocks.SHADOW_TORCH && rand.nextInt(5) == 0)
		{
			world.addParticle(AerialHellParticleTypes.SHADOW_LIGHT, d0 + 0.5 * (rand.nextFloat() - 0.5), d1 - 0.2 * rand.nextFloat(), d2 + 0.5 * (rand.nextFloat() - 0.5), rand.nextFloat() - 0.5, 0.1, rand.nextFloat() - 0.5);
		}
	}
}
