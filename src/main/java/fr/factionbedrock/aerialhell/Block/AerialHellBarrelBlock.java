package fr.factionbedrock.aerialhell.Block;

import fr.factionbedrock.aerialhell.BlockEntity.AerialHellBarrelBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BarrelBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class AerialHellBarrelBlock extends BarrelBlock
{
	/*
	Same as BarrelBlock but with AerialHellBarrelBlockEntity instead of BarrelBlockEntity
	Because in BarrelBlockEntity, the barrel can close only if the barrel blockstate isIn(Blocks.BARREL).
	*/
	
	public AerialHellBarrelBlock(BlockBehaviour.Properties settings)
	{
		super(settings);
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state)
	{
		return new AerialHellBarrelBlockEntity(pos, state);
	}

	@Override
	public InteractionResult useWithoutItem(BlockState state, Level world, BlockPos pos, Player player, BlockHitResult hit)
	{
		if (world.isClientSide())
		{
			return InteractionResult.SUCCESS;
		}
		else
		{
			BlockEntity blockEntity = world.getBlockEntity(pos);
			if (blockEntity instanceof AerialHellBarrelBlockEntity barrelBlockEntity)
			{
				player.openMenu(barrelBlockEntity);
				player.awardStat(Stats.OPEN_BARREL);
				if (world instanceof ServerLevel serverWorld) {PiglinAi.angerNearbyPiglins(serverWorld, player, true);}
			}

			return InteractionResult.CONSUME;
		}
	}

	@Override public void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource rand)
	{
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity instanceof AerialHellBarrelBlockEntity barrelBlockEntity)
		{
			barrelBlockEntity.recheckOpen();
		}
	}

	@Override public RenderShape getRenderShape(BlockState state) {return RenderShape.MODEL;}

	/*@Override TODO where is set the custom name ?
	public void setPlacedBy(Level world, BlockPos pos, BlockState state, @Nullable LivingEntity placer,	ItemStack stack)
	{
		if (stack.hasCustomHoverName())
		{
			BlockEntity blockEntity = world.getBlockEntity(pos);
			if (blockEntity instanceof AerialHellBarrelBlockEntity)
			{
				((AerialHellBarrelBlockEntity) blockEntity).setCustomName(stack.getHoverName());
			}
		}
	}*/
}
