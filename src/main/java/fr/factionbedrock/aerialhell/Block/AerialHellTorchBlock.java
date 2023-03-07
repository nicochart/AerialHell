package fr.factionbedrock.aerialhell.Block;

import java.util.Random;

import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/*Copy of net.minecraft.block.TorchBlock, removing smoke particles, and editing the way particles are added*/

public class AerialHellTorchBlock extends Block
{
	protected static final VoxelShape SHAPE = Block.makeCuboidShape(6.0D, 0.0D, 6.0D, 10.0D, 10.0D, 10.0D);

	public AerialHellTorchBlock(AbstractBlock.Properties properties)
	{
    	super(properties);
	}

	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
	{
		return SHAPE;
	}

	public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos)
	{
		return facing == Direction.DOWN && !this.isValidPosition(stateIn, worldIn, currentPos) ? Blocks.AIR.getDefaultState() : super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
	}

	public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos)
   	{
		return hasEnoughSolidSide(worldIn, pos.down(), Direction.UP);
   	}

	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand)
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
			worldIn.addParticle(AerialHellParticleTypes.SHADOW_PARTICLE.get(), d0 + 0.5 * (rand.nextFloat() - 0.5), d1 - 0.2 * rand.nextFloat(), d2 + 0.5 * (rand.nextFloat() - 0.5), rand.nextFloat() - 0.5, -0.1, rand.nextFloat() - 0.5);
		}
	}
}
