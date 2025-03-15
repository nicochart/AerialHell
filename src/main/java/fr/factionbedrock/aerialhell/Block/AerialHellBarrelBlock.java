package fr.factionbedrock.aerialhell.Block;

import fr.factionbedrock.aerialhell.BlockEntity.AerialHellBarrelBlockEntity;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BarrelBlock;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.mob.PiglinBrain;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class AerialHellBarrelBlock extends BarrelBlock
{
	/*
	Same as BarrelBlock but with AerialHellBarrelBlockEntity instead of BarrelBlockEntity
	Because in BarrelBlockEntity, the barrel can close only if the barrel blockstate isIn(Blocks.BARREL).
	*/
	
	public AerialHellBarrelBlock(AbstractBlock.Settings settings)
	{
		super(settings);
	}

	@Override
	public BlockEntity createBlockEntity(BlockPos pos, BlockState state)
	{
		return new AerialHellBarrelBlockEntity(pos, state);
	}

	@Override
	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit)
	{
		if (world.isClient())
		{
			return ActionResult.SUCCESS;
		}
		else
		{
			BlockEntity blockEntity = world.getBlockEntity(pos);
			if (blockEntity instanceof AerialHellBarrelBlockEntity barrelBlockEntity)
			{
				player.openHandledScreen(barrelBlockEntity);
				player.incrementStat(Stats.OPEN_BARREL);
				if (world instanceof ServerWorld serverWorld) {PiglinBrain.onGuardedBlockInteracted(serverWorld, player, true);}
			}

			return ActionResult.CONSUME;
		}
	}

	@Override public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random rand)
	{
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity instanceof AerialHellBarrelBlockEntity barrelBlockEntity)
		{
			barrelBlockEntity.recheckOpen();
		}
	}

	@Override public BlockRenderType getRenderType(BlockState state) {return BlockRenderType.MODEL;}

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
