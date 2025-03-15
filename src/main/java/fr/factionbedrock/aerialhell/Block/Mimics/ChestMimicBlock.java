package fr.factionbedrock.aerialhell.Block.Mimics;

import fr.factionbedrock.aerialhell.BlockEntity.IntangibleTemporaryBlockEntity;
import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;
import fr.factionbedrock.aerialhell.Entity.Monster.ChestMimic.AbstractChestMimicEntity;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.enums.ChestType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import fr.factionbedrock.aerialhell.Entity.Monster.ChestMimic.*;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlockEntities;
import fr.factionbedrock.aerialhell.BlockEntity.ChestMimicBlockEntity;

public class ChestMimicBlock extends ChestBlock
{
	public ChestMimicBlock(AbstractBlock.Settings settings)
	{
		super(() -> AerialHellBlockEntities.CHEST_MIMIC, settings);
		this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH).with(CHEST_TYPE, ChestType.SINGLE).with(WATERLOGGED, Boolean.valueOf(false)));
	}

	@Override
	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit)
	{
		if (!ChestBlock.isChestBlocked(world, pos))
		{
			if (world.isClient()) {addSpawnParticle(world, pos);}
			else
			{
				world.playSound(null, pos, SoundEvents.BLOCK_BARREL_OPEN, SoundCategory.BLOCKS, 0.5F, 0.7F + 0.5F * world.random.nextFloat());
				revealMimic(state, world, pos);
				world.setBlockState(pos, Blocks.AIR.getDefaultState());
			}
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
		float angle = state.get(FACING).getPositiveHorizontalDegrees();
		AbstractChestMimicEntity chestMimic = getNewChestMimicEntity(world);
		chestMimic.updatePositionAndAngles(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, angle, 0.0F);
		chestMimic.setHeadYaw(angle);
		world.spawnEntity(chestMimic);
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
		if (this == AerialHellBlocks.AERIAL_TREE_CHEST_MIMIC) {return AerialHellParticleTypes.OSCILLATOR;}
		else if (this == AerialHellBlocks.COPPER_PINE_CHEST_MIMIC) {return AerialHellParticleTypes.COPPER_PINE_LEAVES;}
		else if (this == AerialHellBlocks.GOLDEN_BEECH_CHEST_MIMIC) {return AerialHellParticleTypes.LUNATIC_PARTICLE;}
		else /*if (this == AerialHellBlocks.SKY_CACTUS_FIBER_CHEST_MIMIC)*/ {return AerialHellParticleTypes.LUNATIC_PARTICLE;}
	}

	private AbstractChestMimicEntity getNewChestMimicEntity(World world)
	{
		if (this == AerialHellBlocks.AERIAL_TREE_CHEST_MIMIC) {return new AerialTreeChestMimicEntity(AerialHellEntities.AERIAL_TREE_MIMIC, world);}
		else if (this == AerialHellBlocks.COPPER_PINE_CHEST_MIMIC) {return new CopperPineChestMimicEntity(AerialHellEntities.COPPER_PINE_MIMIC, world);}
		else if (this == AerialHellBlocks.GOLDEN_BEECH_CHEST_MIMIC) {return new GoldenBeechChestMimicEntity(AerialHellEntities.GOLDEN_BEECH_MIMIC, world);}
		else /*if (this == AerialHellBlocks.SKY_CACTUS_FIBER_CHEST_MIMIC)*/ {return new SkyCactusFiberChestMimicEntity(AerialHellEntities.SKY_CACTUS_FIBER_MIMIC, world);}
	}

	@Override public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {return new ChestMimicBlockEntity(pos, state);}
	@Override public BlockState rotate(BlockState state, BlockRotation rot) {return state.with(FACING, rot.rotate(state.get(FACING)));}
	@Override public BlockState mirror(BlockState state, BlockMirror mirror) {return state.rotate(mirror.getRotation(state.get(FACING)));}
}