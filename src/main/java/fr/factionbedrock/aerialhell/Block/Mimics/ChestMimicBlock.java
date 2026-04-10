package fr.factionbedrock.aerialhell.Block.Mimics;

import fr.factionbedrock.aerialhell.BlockEntity.IntangibleTemporaryBlockEntity;
import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;
import fr.factionbedrock.aerialhell.Entity.Monster.ChestMimic.*;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.minecraft.world.phys.BlockHitResult;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlockEntities;
import fr.factionbedrock.aerialhell.BlockEntity.ChestMimicBlockEntity;

public class ChestMimicBlock extends ChestBlock
{
	public ChestMimicBlock(BlockBehaviour.Properties settings)
	{
		super(() -> AerialHellBlockEntities.CHEST_MIMIC, SoundEvents.CHEST_OPEN, SoundEvents.CHEST_CLOSE, settings);
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(TYPE, ChestType.SINGLE).setValue(WATERLOGGED, Boolean.valueOf(false)));
	}

	@Override
	public InteractionResult useWithoutItem(BlockState state, Level world, BlockPos pos, Player player, BlockHitResult hit)
	{
		if (!ChestBlock.isChestBlockedAt(world, pos))
		{
			if (world.isClientSide()) {addSpawnParticle(world, pos);}
			else
			{
				world.playSound(null, pos, SoundEvents.BARREL_OPEN, SoundSource.BLOCKS, 0.5F, 0.7F + 0.5F * world.random.nextFloat());
				revealMimic(state, world, pos);
				world.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
			}
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
		float angle = state.getValue(FACING).toYRot();
		AbstractChestMimicEntity chestMimic = getNewChestMimicEntity(world);
		chestMimic.absSnapTo(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, angle, 0.0F);
		chestMimic.setYHeadRot(angle);
		world.addFreshEntity(chestMimic);
	}

	public void addSpawnParticle(Level world, BlockPos pos)
	{
		for(int i = 0; i < 20; ++i)
		{
			double dx = world.random.nextGaussian() * 0.04D, dy = world.random.nextGaussian() * 0.04D, dz = world.random.nextGaussian() * 0.04D;
			double x = pos.getX() + 0.5F + dx * 10.0D, y = pos.getY() + 0.5F + dy * 10.0D, z = pos.getZ() + 0.5F + dz * 10.0D;
			world.addParticle(this.getMimicSpawnParticle(), x, y, z, dx * 10.0D, dy * 10.0D, dz * 10.0D);
		}
	}

	private ParticleOptions getMimicSpawnParticle()
	{
		if (this == AerialHellBlocks.AERIAL_TREE_CHEST_MIMIC) {return AerialHellParticleTypes.OSCILLATOR;}
		else if (this == AerialHellBlocks.COPPER_PINE_CHEST_MIMIC) {return AerialHellParticleTypes.COPPER_PINE_LEAVES;}
		else if (this == AerialHellBlocks.GOLDEN_BEECH_CHEST_MIMIC) {return AerialHellParticleTypes.LUNATIC_PARTICLE;}
		else /*if (this == AerialHellBlocks.SKY_CACTUS_FIBER_CHEST_MIMIC)*/ {return AerialHellParticleTypes.LUNATIC_PARTICLE;}
	}

	private AbstractChestMimicEntity getNewChestMimicEntity(Level world)
	{
		if (this == AerialHellBlocks.AERIAL_TREE_CHEST_MIMIC) {return new AerialTreeChestMimicEntity(AerialHellEntities.AERIAL_TREE_MIMIC, world);}
		else if (this == AerialHellBlocks.COPPER_PINE_CHEST_MIMIC) {return new CopperPineChestMimicEntity(AerialHellEntities.COPPER_PINE_MIMIC, world);}
		else if (this == AerialHellBlocks.GOLDEN_BEECH_CHEST_MIMIC) {return new GoldenBeechChestMimicEntity(AerialHellEntities.GOLDEN_BEECH_MIMIC, world);}
		else /*if (this == AerialHellBlocks.SKY_CACTUS_FIBER_CHEST_MIMIC)*/ {return new SkyCactusFiberChestMimicEntity(AerialHellEntities.SKY_CACTUS_FIBER_MIMIC, world);}
	}

	@Override public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {return new ChestMimicBlockEntity(pos, state);}
	@Override public BlockState rotate(BlockState state, Rotation rot) {return state.setValue(FACING, rot.rotate(state.getValue(FACING)));}
	@Override public BlockState mirror(BlockState state, Mirror mirror) {return state.rotate(mirror.getRotation(state.getValue(FACING)));}
}