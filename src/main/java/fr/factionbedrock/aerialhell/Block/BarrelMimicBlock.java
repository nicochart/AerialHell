package fr.factionbedrock.aerialhell.Block;

import fr.factionbedrock.aerialhell.Entity.Monster.BarrelMimic.ShadowPineBarrelMimicEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellEntities;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
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
		spawnMimic(state, worldIn, pos);
		return ActionResultType.SUCCESS;
	}

	@Override
	public void spawnAdditionalDrops(BlockState state, ServerWorld worldIn, BlockPos pos, ItemStack stack)
	{
		super.spawnAdditionalDrops(state, worldIn, pos, stack);
		spawnMimic(state, worldIn, pos);
	}

	private void spawnMimic(BlockState state, World worldIn, BlockPos pos)
	{
		ShadowPineBarrelMimicEntity shadow_pine_mimic = new ShadowPineBarrelMimicEntity(AerialHellEntities.SHADOW_PINE_MIMIC.get(), worldIn);
		shadow_pine_mimic.setPositionAndRotation(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, 0.0F, 0.0F);
		worldIn.addEntity(shadow_pine_mimic);
		worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
		worldIn.playSound(null, pos, SoundEvents.BLOCK_BARREL_OPEN, SoundCategory.BLOCKS, 0.5F, worldIn.rand.nextFloat() * 0.1F + 0.9F);
		shadow_pine_mimic.spawnExplosionParticle();
	}
}