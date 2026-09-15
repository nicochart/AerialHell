package fr.factionbedrock.aerialhell.Block.Mimics;

import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;
import fr.factionbedrock.aerialhell.Entity.Monster.ChestMimic.AbstractChestMimicEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.Level;

import fr.factionbedrock.aerialhell.Entity.Monster.ChestMimic.*;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlockEntities;
import fr.factionbedrock.aerialhell.BlockEntity.ChestMimicBlockEntity;

import static fr.factionbedrock.aerialhell.Registry.AerialHellStateProperties.CORE_PROTECTED;

public class ChestMimicBlock extends ChestBlock
{
	public ChestMimicBlock(BlockBehaviour.Properties builder)
	{
		super(AerialHellBlockEntities.CHEST_MIMIC::get, SoundEvents.CHEST_OPEN, SoundEvents.CHEST_CLOSE, builder);
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(TYPE, ChestType.SINGLE).setValue(WATERLOGGED, Boolean.valueOf(false)));
	}

	@Override public BlockState getStateForPlacement(BlockPlaceContext context)
	{
		BlockState state = super.getStateForPlacement(context);

		if (state.hasProperty(ChestBlock.TYPE) && state.getValue(ChestBlock.TYPE) != ChestType.SINGLE)
		{
			return state.setValue(ChestBlock.TYPE, ChestType.SINGLE);
		}
		return state;
	}

	@Override public InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hit)
	{
		if (!ChestBlock.isChestBlockedAt(level, pos))
		{
			if (level.isClientSide()) {addSpawnParticle(level, pos);}
			else
			{
				level.playSound(null, pos, SoundEvents.CHEST_OPEN, SoundSource.BLOCKS, 0.5F, 0.7F + 0.5F * level.getRandom().nextFloat());
				revealMimic(state, level, pos);
				level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
			}
		}
		return InteractionResult.SUCCESS;
	}

	@Override public void spawnAfterBreak(BlockState state, ServerLevel level, BlockPos pos, ItemStack stack, boolean bool)
	{
		super.spawnAfterBreak(state, level, pos, stack, bool);
		revealMimic(state, level, pos);
	}

	private void revealMimic(BlockState state, Level level, BlockPos pos)
	{
		float angle = state.getValue(FACING).toYRot();
		AbstractChestMimicEntity chestMimic = getNewChestMimicEntity(level);
		chestMimic.absSnapTo(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, angle, 0.0F);
		chestMimic.setYHeadRot(angle);
		level.addFreshEntity(chestMimic);
	}

	public void addSpawnParticle(Level level, BlockPos pos)
	{
		for(int i = 0; i < 20; ++i)
		{
			double dx = level.getRandom().nextGaussian() * 0.04D, dy = level.getRandom().nextGaussian() * 0.04D, dz = level.getRandom().nextGaussian() * 0.04D;
			double x = pos.getX() + 0.5F + dx * 10.0D, y = pos.getY() + 0.5F + dy * 10.0D, z = pos.getZ() + 0.5F + dz * 10.0D;
			level.addParticle(this.getMimicSpawnParticle(), x, y, z, dx * 10.0D, dy * 10.0D, dz * 10.0D);
		}
	}

	private SimpleParticleType getMimicSpawnParticle()
	{
		if (this == AerialHellBlocks.AERIAL_TREE_CHEST_MIMIC.get()) {return AerialHellParticleTypes.OSCILLATOR.get();}
		else if (this == AerialHellBlocks.COPPER_PINE_CHEST_MIMIC.get()) {return AerialHellParticleTypes.COPPER_PINE_LEAVES.get();}
		else if (this == AerialHellBlocks.GOLDEN_BEECH_CHEST_MIMIC.get()) {return AerialHellParticleTypes.LUNATIC_PARTICLE.get();}
		else /*if (this == AerialHellBlocksAndItems.SKY_CACTUS_FIBER_CHEST_MIMIC.get())*/ {return AerialHellParticleTypes.LUNATIC_PARTICLE.get();}
	}

	private AbstractChestMimicEntity getNewChestMimicEntity(Level worldIn)
	{
		if (this == AerialHellBlocks.AERIAL_TREE_CHEST_MIMIC.get()) {return new AerialTreeChestMimicEntity(AerialHellEntities.AERIAL_TREE_MIMIC.get(), worldIn);}
		else if (this == AerialHellBlocks.COPPER_PINE_CHEST_MIMIC.get()) {return new CopperPineChestMimicEntity(AerialHellEntities.COPPER_PINE_MIMIC.get(), worldIn);}
		else if (this == AerialHellBlocks.GOLDEN_BEECH_CHEST_MIMIC.get()) {return new GoldenBeechChestMimicEntity(AerialHellEntities.GOLDEN_BEECH_MIMIC.get(), worldIn);}
		else /*if (this == AerialHellBlocksAndItems.SKY_CACTUS_FIBER_CHEST_MIMIC.get())*/ {return new SkyCactusFiberChestMimicEntity(AerialHellEntities.SKY_CACTUS_FIBER_MIMIC.get(), worldIn);}
	}

	@Override public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {return new ChestMimicBlockEntity(pos, state);}
	@Override public BlockState rotate(BlockState state, Rotation rot) {return state.setValue(FACING, rot.rotate(state.getValue(FACING)));}
	@Override public BlockState mirror(BlockState state, Mirror mirrorIn) {return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));}
}