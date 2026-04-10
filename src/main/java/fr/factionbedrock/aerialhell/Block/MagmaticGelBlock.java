package fr.factionbedrock.aerialhell.Block;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.HalfTransparentBlock;
import net.minecraft.world.level.block.MagmaBlock;
import net.minecraft.world.level.block.SoulFireBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;

public class MagmaticGelBlock extends HalfTransparentBlock
{
	public MagmaticGelBlock(BlockBehaviour.Properties settings)
	{
		super(settings);
	}
	
	@Override
	public void onPlace(BlockState state, Level world, BlockPos pos, BlockState oldState, boolean notify)
	{
		for (int x = -2; x < 3; x++)
		{
			for (int y = -2; y < 3; y++)
			{
				for (int z = -2; z < 3; z++)
				{
					BlockPos newPos = pos.offset(x, y, z);
					BlockState newPosState = world.getBlockState(newPos);
					Block block = newPosState.getBlock();
					if (block instanceof BucketPickup)
					{
						FluidState fluidState = newPosState.getFluidState();
						if (fluidState.is(FluidTags.WATER))
						{
							world.setBlockAndUpdate(newPos, Blocks.ICE.defaultBlockState());
						}
						else if (fluidState.is(FluidTags.LAVA))
						{
							world.setBlockAndUpdate(newPos, AerialHellBlocks.CRYSTAL_BLOCK.defaultBlockState());
							world.playSound(null, newPos, SoundEvents.LAVA_EXTINGUISH, SoundSource.BLOCKS, 1.0F, 1.0F);
						}
						else if (fluidState.is(AerialHellTags.Fluids.LIQUID_OF_THE_GODS))
						{
							world.setBlockAndUpdate(newPos, Blocks.OBSIDIAN.defaultBlockState());
							world.playSound(null, newPos, SoundEvents.LAVA_EXTINGUISH, SoundSource.BLOCKS, 1.0F, 1.0F);
						}
					}
					else
					{
						if (block instanceof FireBlock || block instanceof SoulFireBlock)
						{
							world.setBlockAndUpdate(newPos, Blocks.AIR.defaultBlockState());
							world.playSound(null, newPos, SoundEvents.LAVA_EXTINGUISH, SoundSource.BLOCKS, 1.0F, 1.0F);
						}
						if (block instanceof MagmaBlock)
						{
							world.setBlockAndUpdate(newPos, AerialHellBlocks.MAGMATIC_GEL_ORE.defaultBlockState());
							world.playSound(null, newPos, SoundEvents.LAVA_EXTINGUISH, SoundSource.BLOCKS, 1.0F, 1.0F);
						}
					}
				}
			}
		}
	}
	
	@Override
	public void stepOn(Level world, BlockPos pos, BlockState state, Entity entity)
	{
		boolean creaPlayer = (entity instanceof Player player && player.isCreative());
		if (!world.isClientSide() && entity instanceof LivingEntity && !creaPlayer)
		{
			((LivingEntity) entity).addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 32, 1));
		}
	}
}
