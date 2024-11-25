package fr.factionbedrock.aerialhell.Block.Mimics;

import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;
import fr.factionbedrock.aerialhell.Entity.Monster.BarrelMimic.AbstractBarrelMimicEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.BarrelMimic.ShadowPineBarrelMimicEntity;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.PillarBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BarrelMimicBlock extends PillarBlock
{
	public BarrelMimicBlock(AbstractBlock.Settings settings) {super(settings);}

	@Override
	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit)
	{
		if (world.isClient()) {addSpawnParticle(world, pos);}
		else
		{
			world.playSound(null, pos, SoundEvents.BLOCK_BARREL_OPEN, SoundCategory.BLOCKS, 0.5F, 0.7F + 0.5F * world.random.nextFloat());
			revealMimic(state, world, pos);
			world.setBlockState(pos, Blocks.AIR.getDefaultState());
		}
		return ActionResult.SUCCESS;
	}

	@Override
	public void onStacksDropped(BlockState state, ServerWorld world, BlockPos pos, ItemStack stack, boolean dropExperience)
	{
		super.onStacksDropped(state, world, pos, stack, dropExperience);
		revealMimic(state, world, pos);
	}

	private void revealMimic(BlockState state, World world, BlockPos pos)
	{
		AbstractBarrelMimicEntity barrelMimic = getNewBarrelMimicEntity(world);
		barrelMimic.updatePositionAndAngles(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, 0.0F, 0.0F);
		world.spawnEntity(barrelMimic);
	}

	public void addSpawnParticle(World world, BlockPos pos)
	{
		for(int i = 0; i < 20; ++i)
		{
			double dx = world.random.nextGaussian() * 0.04D, dy = world.random.nextGaussian() * 0.04D, dz = world.random.nextGaussian() * 0.04D;
			double x = pos.getX() + 0.5F + dx * 10.0D, y = pos.getY() + 0.5F + dy * 10.0D, z = pos.getZ() + 0.5F + dz * 10.0D;
			world.addParticle(this.getMimicSpawnParticle(), x, y, z, dx * 10.0D, dy * 10.0D, dz * 10.0D);
		}
	}

	private ParticleEffect getMimicSpawnParticle()
	{
		/*if (this == AerialHellBlocksAndItems.SHADOW_PINE_BARREL_MIMIC.get())*/ {return AerialHellParticleTypes.SHADOW_PARTICLE;}
	}

	private AbstractBarrelMimicEntity getNewBarrelMimicEntity(World world)
	{
		/*if (this == AerialHellBlocksAndItems.SHADOW_PINE_BARREL_MIMIC.get())*/ {return new ShadowPineBarrelMimicEntity(AerialHellEntities.SHADOW_PINE_MIMIC, world);}
	}
}