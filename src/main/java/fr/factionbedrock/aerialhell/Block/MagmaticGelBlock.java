package fr.factionbedrock.aerialhell.Block;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.BreakableBlock;
import net.minecraft.block.FireBlock;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.MagmaBlock;
import net.minecraft.block.SoulFireBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellTags;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;

public class MagmaticGelBlock extends BreakableBlock
{
	public MagmaticGelBlock(AbstractBlock.Properties properties)
	{
		super(properties);
	}
	
	@Override
	public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving)
	{
		for (int x = -2; x < 3; x++)
		{
			for (int y = -2; y < 3; y++)
			{
				for (int z = -2; z < 3; z++)
				{
					BlockPos newPos = pos.add(x, y, z);
					BlockState newPosState = worldIn.getBlockState(newPos);
					Block block = newPosState.getBlock();
					if (block instanceof FlowingFluidBlock)
					{
						FluidState fluidState = newPosState.getFluidState();
						if (fluidState.isTagged(FluidTags.WATER))
						{
							worldIn.setBlockState(newPos, Blocks.ICE.getDefaultState());
						}
						else if (fluidState.isTagged(FluidTags.LAVA))
						{
							worldIn.setBlockState(newPos, AerialHellBlocksAndItems.CRYSTAL_BLOCK.get().getDefaultState());
							worldIn.playSound(null, newPos, SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.BLOCKS, 1.0F, 1.0F);
						}
						else if (fluidState.isTagged(AerialHellTags.Fluids.LIQUID_OF_THE_GODS))
						{
							worldIn.setBlockState(newPos, Blocks.OBSIDIAN.getDefaultState());
							worldIn.playSound(null, newPos, SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.BLOCKS, 1.0F, 1.0F);
						}
					}
					else
					{
						if (block instanceof FireBlock || block instanceof SoulFireBlock)
						{
							worldIn.setBlockState(newPos, Blocks.AIR.getDefaultState());
							worldIn.playSound(null, newPos, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 1.0F, 1.0F);
						}
						if (block instanceof MagmaBlock)
						{
							worldIn.setBlockState(newPos, AerialHellBlocksAndItems.MAGMATIC_GEL_ORE.get().getDefaultState());
							worldIn.playSound(null, newPos, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 1.0F, 1.0F);
						}
					}
				}
			}
		}
	}
	
	@Override
	public void onEntityWalk(World world, BlockPos pos, Entity entity)
	{
		boolean creaPlayer = (entity instanceof PlayerEntity && ((PlayerEntity) entity).isCreative());
		if (!world.isRemote() && entity instanceof LivingEntity && !creaPlayer)
		{
			((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.SLOWNESS, 32, 1));
		}
	}
	
	@Override public boolean isTransparent(BlockState state) {return true;}
	@Override public int getOpacity(BlockState state, IBlockReader worldIn, BlockPos pos) {return 3;}
	@Override public VoxelShape getRayTraceShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) {return VoxelShapes.empty();}
}
