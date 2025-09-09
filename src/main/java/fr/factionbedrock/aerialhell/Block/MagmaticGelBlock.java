package fr.factionbedrock.aerialhell.Block;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.*;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.tags.FluidTags;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;

public class MagmaticGelBlock extends HalfTransparentBlock
{
	public MagmaticGelBlock(BlockBehaviour.Properties properties)
	{
		super(properties);
	}
	
	@Override
	public void onPlace(BlockState state, Level worldIn, BlockPos pos, BlockState oldState, boolean isMoving)
	{
		for (int x = -2; x < 3; x++)
		{
			for (int y = -2; y < 3; y++)
			{
				for (int z = -2; z < 3; z++)
				{
					BlockPos newPos = pos.offset(x, y, z);
					BlockState newPosState = worldIn.getBlockState(newPos);
					Block block = newPosState.getBlock();
					if (block instanceof LiquidBlock)
					{
						FluidState fluidState = newPosState.getFluidState();
						if (fluidState.is(FluidTags.WATER))
						{
							worldIn.setBlockAndUpdate(newPos, Blocks.ICE.defaultBlockState());
						}
						else if (fluidState.is(FluidTags.LAVA))
						{
							worldIn.setBlockAndUpdate(newPos, AerialHellBlocks.CRYSTAL_BLOCK.get().defaultBlockState());
							worldIn.playSound(null, newPos, SoundEvents.LAVA_EXTINGUISH, SoundSource.BLOCKS, 1.0F, 1.0F);
						}
						else if (fluidState.is(AerialHellTags.Fluids.LIQUID_OF_THE_GODS))
						{
							worldIn.setBlockAndUpdate(newPos, Blocks.OBSIDIAN.defaultBlockState());
							worldIn.playSound(null, newPos, SoundEvents.LAVA_EXTINGUISH, SoundSource.BLOCKS, 1.0F, 1.0F);
						}
					}
					else
					{
						if (block instanceof FireBlock || block instanceof SoulFireBlock)
						{
							worldIn.setBlockAndUpdate(newPos, Blocks.AIR.defaultBlockState());
							worldIn.playSound(null, newPos, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 1.0F, 1.0F);
						}
						if (block instanceof MagmaBlock)
						{
							worldIn.setBlockAndUpdate(newPos, AerialHellBlocks.MAGMATIC_GEL_ORE.get().defaultBlockState());
							worldIn.playSound(null, newPos, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 1.0F, 1.0F);
						}
					}
				}
			}
		}
	}
	
	@Override
	public void stepOn(Level world, BlockPos pos, BlockState state, Entity entity)
	{
		boolean creaPlayer = (entity instanceof Player && ((Player) entity).isCreative());
		if (!world.isClientSide() && entity instanceof LivingEntity && !creaPlayer)
		{
			((LivingEntity) entity).addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 32, 1));
		}
	}
}
