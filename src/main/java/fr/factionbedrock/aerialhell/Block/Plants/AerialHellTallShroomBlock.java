package fr.factionbedrock.aerialhell.Block.Plants;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Util.BlockHelper;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShortPlantBlock;
import net.minecraft.block.TallPlantBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

public class AerialHellTallShroomBlock extends ShortPlantBlock
{
	private final boolean needsDarkness;

	public AerialHellTallShroomBlock(AbstractBlock.Settings settings, boolean needsDarkness) {super(settings); this.needsDarkness = needsDarkness;}

	@Override public void grow(ServerWorld world, Random rand, BlockPos pos, BlockState state)
	{
		TallPlantBlock tall_plant;
		if (this == AerialHellBlocks.GLOWING_BOLETUS)
		{
			tall_plant = (TallPlantBlock) AerialHellBlocks.TALL_GLOWING_BOLETUS;
			placePlant(world, pos, tall_plant);
		}
	}

	protected void placePlant(ServerWorld world, BlockPos pos, TallPlantBlock plantIn)
	{
		if (plantIn.getDefaultState().canPlaceAt(world, pos) && world.isAir(pos.up()))
		{
			plantIn.placeAt(world, plantIn.getDefaultState(), pos, 2);
		}
	}

	@Override public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos)
	{
		BlockState belowState = world.getBlockState(pos.down());
		if (this.canPlantOnTop(belowState, world, pos))
		{
			if (!this.needsDarkness) {return true;}
			else
			{
				boolean brightnessFlag = world.getLightLevel(pos, 0) < 13;
				boolean solidSurfaceAbove = BlockHelper.hasAnySolidSurfaceAbove(world, pos, 3);
				return brightnessFlag && solidSurfaceAbove;
			}
		}
		else {return false;}
	}

	@Override protected boolean canPlantOnTop(BlockState state, BlockView world, BlockPos pos)
	{
		if (this == AerialHellBlocks.GIANT_ROOT_SHROOM && state.isOf(AerialHellBlocks.GIANT_ROOT)) {return true;}
		return state.isIn(BlockTags.DIRT) || state.isOf(AerialHellBlocks.STELLAR_COARSE_DIRT) || state.isIn(AerialHellTags.Blocks.STELLAR_STONE_AND_DERIVATIVES) || state.isIn(BlockTags.MUSHROOM_GROW_BLOCK);
	}

	@Override public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity)
	{
		if (this == AerialHellBlocks.GIANT_ROOT_SHROOM && entity instanceof LivingEntity livingEntity)
		{
			livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 100, 0, false, false, true));
		}
	}
}
