package fr.factionbedrock.aerialhell.Block.Mimics;

import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;
import fr.factionbedrock.aerialhell.Entity.Monster.BarrelMimic.AbstractBarrelMimicEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.BarrelMimic.ShadowPineBarrelMimicEntity;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class BarrelMimicBlock extends RotatedPillarBlock
{
	public BarrelMimicBlock(BlockBehaviour.Properties settings) {super(settings);}

	@Override
	public InteractionResult useWithoutItem(BlockState state, Level world, BlockPos pos, Player player, BlockHitResult hit)
	{
		if (world.isClientSide()) {addSpawnParticle(world, pos);}
		else
		{
			world.playSound(null, pos, SoundEvents.BARREL_OPEN, SoundSource.BLOCKS, 0.5F, 0.7F + 0.5F * world.getRandom().nextFloat());
			revealMimic(state, world, pos);
			world.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
		}
		return InteractionResult.SUCCESS;
	}

	@Override
	public void spawnAfterBreak(BlockState state, ServerLevel world, BlockPos pos, ItemStack stack, boolean dropExperience)
	{
		super.spawnAfterBreak(state, world, pos, stack, dropExperience);
		revealMimic(state, world, pos);
	}

	private void revealMimic(BlockState state, Level world, BlockPos pos)
	{
		AbstractBarrelMimicEntity barrelMimic = getNewBarrelMimicEntity(world);
		barrelMimic.absSnapTo(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, 0.0F, 0.0F);
		world.addFreshEntity(barrelMimic);
	}

	public void addSpawnParticle(Level world, BlockPos pos)
	{
		for(int i = 0; i < 20; ++i)
		{
			double dx = world.getRandom().nextGaussian() * 0.04D, dy = world.getRandom().nextGaussian() * 0.04D, dz = world.getRandom().nextGaussian() * 0.04D;
			double x = pos.getX() + 0.5F + dx * 10.0D, y = pos.getY() + 0.5F + dy * 10.0D, z = pos.getZ() + 0.5F + dz * 10.0D;
			world.addParticle(this.getMimicSpawnParticle(), x, y, z, dx * 10.0D, dy * 10.0D, dz * 10.0D);
		}
	}

	private ParticleOptions getMimicSpawnParticle()
	{
		/*if (this == AerialHellBlocksAndItems.SHADOW_PINE_BARREL_MIMIC.get())*/ {return AerialHellParticleTypes.SHADOW_PARTICLE;}
	}

	private AbstractBarrelMimicEntity getNewBarrelMimicEntity(Level world)
	{
		/*if (this == AerialHellBlocksAndItems.SHADOW_PINE_BARREL_MIMIC.get())*/ {return new ShadowPineBarrelMimicEntity(AerialHellEntities.SHADOW_PINE_MIMIC, world);}
	}
}