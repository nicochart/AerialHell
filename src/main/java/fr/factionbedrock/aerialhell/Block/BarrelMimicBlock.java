package fr.factionbedrock.aerialhell.Block;

import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;
import fr.factionbedrock.aerialhell.Entity.AbstractBarrelMimicEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.BarrelMimic.ShadowPineBarrelMimicEntity;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.level.block.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class BarrelMimicBlock extends RotatedPillarBlock
{
	public BarrelMimicBlock(Properties properties) {super(properties);}

	@Override
	public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit)
	{
		if (worldIn.isClientSide()) {addSpawnParticle(worldIn, pos);}
		else
		{
			worldIn.playSound(null, pos, SoundEvents.BARREL_OPEN, SoundSource.BLOCKS, 0.5F, 0.7F + 0.5F * worldIn.random.nextFloat());
			revealMimic(state, worldIn, pos);
			worldIn.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
		}
		return InteractionResult.SUCCESS;
	}

	@Override
	public void spawnAfterBreak(BlockState state, ServerLevel level, BlockPos pos, ItemStack stack, boolean bool)
	{
		super.spawnAfterBreak(state, level, pos, stack, bool);
		revealMimic(state, level, pos);
	}

	private void revealMimic(BlockState state, Level worldIn, BlockPos pos)
	{
		AbstractBarrelMimicEntity barrelMimic = getNewBarrelMimicEntity(worldIn);
		barrelMimic.absMoveTo(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, 0.0F, 0.0F);
		worldIn.addFreshEntity(barrelMimic);
	}

	public void addSpawnParticle(Level worldIn, BlockPos pos)
	{
		for(int i = 0; i < 20; ++i)
		{
			double dx = worldIn.random.nextGaussian() * 0.04D, dy = worldIn.random.nextGaussian() * 0.04D, dz = worldIn.random.nextGaussian() * 0.04D;
			double x = pos.getX() + 0.5F + dx * 10.0D, y = pos.getY() + 0.5F + dy * 10.0D, z = pos.getZ() + 0.5F + dz * 10.0D;
			worldIn.addParticle(this.getMimicSpawnParticle(), x, y, z, dx * 10.0D, dy * 10.0D, dz * 10.0D);
		}
	}

	private SimpleParticleType getMimicSpawnParticle()
	{
		/*if (this == AerialHellBlocksAndItems.SHADOW_PINE_BARREL_MIMIC.get())*/ {return AerialHellParticleTypes.SHADOW_PARTICLE.get();}
	}

	private AbstractBarrelMimicEntity getNewBarrelMimicEntity(Level worldIn)
	{
		/*if (this == AerialHellBlocksAndItems.SHADOW_PINE_BARREL_MIMIC.get())*/ {return new ShadowPineBarrelMimicEntity(AerialHellEntities.SHADOW_PINE_MIMIC.get(), worldIn);}
	}
}