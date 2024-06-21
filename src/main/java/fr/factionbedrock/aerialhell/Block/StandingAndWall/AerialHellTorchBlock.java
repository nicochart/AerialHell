package fr.factionbedrock.aerialhell.Block.StandingAndWall;

import java.util.Random;

import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.Level;

/*Copy of net.minecraft.block.TorchBlock, removing smoke particles, and editing the way particles are added*/

public class AerialHellTorchBlock extends Block
{
	protected static final VoxelShape SHAPE = Block.box(6.0D, 0.0D, 6.0D, 10.0D, 10.0D, 10.0D);

	public AerialHellTorchBlock(BlockBehaviour.Properties properties)
	{
    	super(properties);
	}

	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context)
	{
		return SHAPE;
	}

	public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos currentPos, BlockPos facingPos)
	{
		return facing == Direction.DOWN && !this.canSurvive(stateIn, worldIn, currentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
	}

	@Override public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos)
   	{
		return canSupportCenter(worldIn, pos.below(), Direction.UP);
   	}

	public void animateTick(BlockState stateIn, Level worldIn, BlockPos pos, Random rand)
	{
		double d0 = (double)pos.getX() + 0.5D;
      	double d1 = (double)pos.getY() + 0.7D;
      	double d2 = (double)pos.getZ() + 0.5D;
      	if (this == AerialHellBlocksAndItems.FLUORITE_TORCH.get() && rand.nextInt(5) == 0)
      	{
      		worldIn.addParticle(AerialHellParticleTypes.OSCILLATOR.get(), d0 + 0.5 * (rand.nextFloat() - 0.5), d1 - 0.2 * rand.nextFloat(), d2 + 0.5 * (rand.nextFloat() - 0.5), rand.nextFloat() - 0.5, rand.nextFloat() - 0.5, rand.nextFloat() - 0.5);
      	}
		else if (this == AerialHellBlocksAndItems.SHADOW_TORCH.get() && rand.nextInt(5) == 0)
		{
			worldIn.addParticle(AerialHellParticleTypes.SHADOW_LIGHT.get(), d0 + 0.5 * (rand.nextFloat() - 0.5), d1 - 0.2 * rand.nextFloat(), d2 + 0.5 * (rand.nextFloat() - 0.5), rand.nextFloat() - 0.5, 0.1, rand.nextFloat() - 0.5);
		}
	}
}
