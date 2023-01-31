package fr.factionbedrock.aerialhell.Block.SolidEther;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellPotionEffects;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BreakableBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
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
	protected final static VoxelShape SOLID_ETHER_COLLISION_SHAPE = Block.makeCuboidShape(0.0, 0.0, 0.0, 16.0, 0.02, 16.0);
	protected final static VoxelShape EMPTY_SHAPE = VoxelShapes.empty();
	
	public SolidEtherBlock(AbstractBlock.Properties properties)
	{
		super(properties.setOpaque((state, reader, pos) -> false).setSuffocates((state, reader, pos) -> false).setBlocksVision((state, reader, pos) -> false));
		this.setDefaultState(this.getDefaultState());
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
	{
		super.fillStateContainer(builder);
	}
	
	@Override
	public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity)
	{
		entity.fallDistance = 0.0F;
		if (entity.getMotion().y < 0.0)
		{
			if (entity instanceof LivingEntity) {entity.setMotion(entity.getMotion().mul(0.96, 0.002, 0.96));}
			else {entity.setMotion(entity.getMotion().mul(0.85, 0.002, 0.85));}
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
	public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
	{
		Entity entity = context.getEntity();
		if (canEntityCollide(entity)) {return SOLID_ETHER_COLLISION_SHAPE;}
		else {return EMPTY_SHAPE;}
	}
	
	protected boolean canEntityCollide(Entity entity)
	{
		if (entity instanceof LivingEntity)
		{
			LivingEntity livingEntity = (LivingEntity) entity;
			if (livingEntity.isPotionActive(AerialHellPotionEffects.HEAD_IN_THE_CLOUDS.get())) {return true;}
			Iterable<ItemStack> stuff = livingEntity.getArmorInventoryList();
			for (ItemStack armorStack : stuff)
			{
				if (armorStack.getItem() == AerialHellBlocksAndItems.MAGMATIC_GEL_BOOTS.get()) {return true;}
			}
			return false;
		}
		else {return true;}
	}

	@Override
	public VoxelShape getRayTraceShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) {return VoxelShapes.empty();} //getVisualShape
}