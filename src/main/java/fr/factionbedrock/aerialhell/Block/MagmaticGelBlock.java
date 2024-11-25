package fr.factionbedrock.aerialhell.Block;

import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;

public class MagmaticGelBlock extends TranslucentBlock
{
	public MagmaticGelBlock(AbstractBlock.Settings settings)
	{
		super(settings);
	}
	
	@Override
	public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify)
	{
		for (int x = -2; x < 3; x++)
		{
			for (int y = -2; y < 3; y++)
			{
				for (int z = -2; z < 3; z++)
				{
					BlockPos newPos = pos.add(x, y, z);
					BlockState newPosState = world.getBlockState(newPos);
					Block block = newPosState.getBlock();
					if (block instanceof FluidDrainable)
					{
						FluidState fluidState = newPosState.getFluidState();
						if (fluidState.isOf(FluidTags.WATER))
						{
							world.setBlockState(newPos, Blocks.ICE.getDefaultState());
						}
						else if (fluidState.isOf(FluidTags.LAVA))
						{
							world.setBlockState(newPos, AerialHellBlocks.CRYSTAL_BLOCK.getDefaultState());
							world.playSound(null, newPos, SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.BLOCKS, 1.0F, 1.0F);
						}
						else if (fluidState.isIn(AerialHellTags.Fluids.LIQUID_OF_THE_GODS))
						{
							world.setBlockState(newPos, Blocks.OBSIDIAN.getDefaultState());
							world.playSound(null, newPos, SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.BLOCKS, 1.0F, 1.0F);
						}
					}
					else
					{
						if (block instanceof FireBlock || block instanceof SoulFireBlock)
						{
							world.setBlockState(newPos, Blocks.AIR.getDefaultState());
							world.playSound(null, newPos, SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.BLOCKS, 1.0F, 1.0F);
						}
						if (block instanceof MagmaBlock)
						{
							world.setBlockState(newPos, AerialHellBlocks.MAGMATIC_GEL_ORE.getDefaultState());
							world.playSound(null, newPos, SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.BLOCKS, 1.0F, 1.0F);
						}
					}
				}
			}
		}
	}
	
	@Override
	public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity)
	{
		boolean creaPlayer = (entity instanceof PlayerEntity player && player.isCreative());
		if (!world.isClient() && entity instanceof LivingEntity && !creaPlayer)
		{
			((LivingEntity) entity).addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 32, 1));
		}
	}
}
