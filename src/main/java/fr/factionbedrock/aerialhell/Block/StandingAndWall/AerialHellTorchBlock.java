package fr.factionbedrock.aerialhell.Block.StandingAndWall;

import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

/*Copy of net.minecraft.block.TorchBlock, removing smoke particles, and editing the way particles are added*/

public class AerialHellTorchBlock extends Block
{
	protected static final VoxelShape SHAPE = Block.box(6.0D, 0.0D, 6.0D, 10.0D, 10.0D, 10.0D);

	public AerialHellTorchBlock(BlockBehaviour.Properties properties)
	{
    	super(properties);
	}

	@Override public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context)
	{
		return SHAPE;
	}

	@Override protected BlockState updateShape(BlockState previousState, LevelReader level, ScheduledTickAccess scheduledTickAccess, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, RandomSource random)
	{
		return direction == Direction.DOWN && !this.canSurvive(previousState, level, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(previousState, level, scheduledTickAccess, pos, direction, neighborPos, neighborState, random);
	}

	@Override public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos)
   	{
		return canSupportCenter(worldIn, pos.below(), Direction.UP);
   	}

	@Override public void animateTick(BlockState stateIn, Level worldIn, BlockPos pos, RandomSource rand)
	{
		double d0 = (double)pos.getX() + 0.5D;
      	double d1 = (double)pos.getY() + 0.7D;
      	double d2 = (double)pos.getZ() + 0.5D;
      	if (this == AerialHellBlocks.FLUORITE_TORCH.get() && rand.nextInt(5) == 0)
      	{
      		worldIn.addParticle(AerialHellParticleTypes.OSCILLATOR.get(), d0 + 0.5 * (rand.nextFloat() - 0.5), d1 - 0.2 * rand.nextFloat(), d2 + 0.5 * (rand.nextFloat() - 0.5), rand.nextFloat() - 0.5, rand.nextFloat() - 0.5, rand.nextFloat() - 0.5);
      	}
		else if (this == AerialHellBlocks.SHADOW_TORCH.get() && rand.nextInt(5) == 0)
		{
			worldIn.addParticle(AerialHellParticleTypes.SHADOW_LIGHT.get(), d0 + 0.5 * (rand.nextFloat() - 0.5), d1 - 0.2 * rand.nextFloat(), d2 + 0.5 * (rand.nextFloat() - 0.5), rand.nextFloat() - 0.5, 0.1, rand.nextFloat() - 0.5);
		}
	}
}
