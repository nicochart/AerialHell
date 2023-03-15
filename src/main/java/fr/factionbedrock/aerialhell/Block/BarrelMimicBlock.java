package fr.factionbedrock.aerialhell.Block;

import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;
import fr.factionbedrock.aerialhell.Entity.AbstractBarrelMimicEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.BarrelMimic.ShadowPineBarrelMimicEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellEntities;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class BarrelMimicBlock extends RotatedPillarBlock
{
	public BarrelMimicBlock(Properties properties) {super(properties);}

	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit)
	{
		if (worldIn.isRemote) {addSpawnParticle(worldIn, pos);}
		else
		{
			worldIn.playSound(null, pos, SoundEvents.BLOCK_BARREL_OPEN, SoundCategory.BLOCKS, 0.5F, 0.7F + 0.5F * worldIn.rand.nextFloat());
			revealMimic(state, worldIn, pos);
			worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
		}
		return ActionResultType.SUCCESS;
	}

	@Override
	public void spawnAdditionalDrops(BlockState state, ServerWorld worldIn, BlockPos pos, ItemStack stack)
	{
		super.spawnAdditionalDrops(state, worldIn, pos, stack);
		revealMimic(state, worldIn, pos);
	}

	private void revealMimic(BlockState state, World worldIn, BlockPos pos)
	{
		AbstractBarrelMimicEntity barrelMimic = getNewBarrelMimicEntity(worldIn);
		barrelMimic.setPositionAndRotation(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, 0.0F, 0.0F);
		worldIn.addEntity(barrelMimic);
	}

	public void addSpawnParticle(World worldIn, BlockPos pos)
	{
		for(int i = 0; i < 20; ++i)
		{
			double dx = worldIn.rand.nextGaussian() * 0.04D, dy = worldIn.rand.nextGaussian() * 0.04D, dz = worldIn.rand.nextGaussian() * 0.04D;
			double x = pos.getX() + 0.5F + dx * 10.0D, y = pos.getY() + 0.5F + dy * 10.0D, z = pos.getZ() + 0.5F + dz * 10.0D;
			worldIn.addParticle(this.getMimicSpawnParticle(), x, y, z, dx * 10.0D, dy * 10.0D, dz * 10.0D);
		}
	}

	private BasicParticleType getMimicSpawnParticle()
	{
		/*if (this == AerialHellBlocksAndItems.SHADOW_PINE_BARREL_MIMIC.get())*/ {return AerialHellParticleTypes.SHADOW_PARTICLE.get();}
	}

	private AbstractBarrelMimicEntity getNewBarrelMimicEntity(World worldIn)
	{
		/*if (this == AerialHellBlocksAndItems.SHADOW_PINE_BARREL_MIMIC.get())*/ {return new ShadowPineBarrelMimicEntity(AerialHellEntities.SHADOW_PINE_MIMIC.get(), worldIn);}
	}
}