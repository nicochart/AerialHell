package fr.factionbedrock.aerialhell.Block.SolidEther;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BreakableBlock;
import net.minecraft.entity.Entity;
import net.minecraft.state.StateContainer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class SolidEtherBlock extends BreakableBlock
{
	protected static VoxelShape SOLID_ETHER_BASICSHAPE = Block.makeCuboidShape(0.0, 0.0, 0.0, 16.0, 0.02, 16.0);
	
	public SolidEtherBlock(AbstractBlock.Properties properties)
	{
		super(properties.setOpaque((state, reader, pos) -> false).setSuffocates((state, reader, pos) -> false).setBlocksVision((state, reader, pos) -> false));
		this.setDefaultState(this.getDefaultState());
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) //createBlockStateDefinition
	{
		super.fillStateContainer(builder);
	}
	
	@Override
	public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) //entityInside
	{
		entity.fallDistance = 0.0F;
		if (entity.getMotion().y < 0.0)
		{
			entity.setMotion(entity.getMotion().mul(1.0, 0.005, 1.0)); //mul=multiply
		}
	}
	
	@Override
	public void onPlayerDestroy(IWorld worldIn, BlockPos pos, BlockState state)
	{
		super.onPlayerDestroy(worldIn, pos, state);
		if (this.getBlock() instanceof GreenSolidEtherBlock || this.getBlock() instanceof BlueSolidEtherBlock || this.getBlock() instanceof GoldenSolidEtherBlock)
		{
			worldIn.setBlockState(pos, AerialHellBlocksAndItems.WHITE_SOLID_ETHER.get().getDefaultState(), 3);
		}
    }

	@Override
	@OnlyIn(Dist.CLIENT)
	public float getAmbientOcclusionLightValue(BlockState state, IBlockReader worldIn, BlockPos pos) {return 1.0F;}

	@Override
	public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {return true;}

	@Override
	public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {return SOLID_ETHER_BASICSHAPE;}

	@Override
	public VoxelShape getRayTraceShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) {return VoxelShapes.empty();} //getVisualShape
}