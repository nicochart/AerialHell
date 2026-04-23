package fr.factionbedrock.aerialhell.Block.Plants;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Util.BlockHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.TallGrassBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class AerialHellTallShroomBlock extends TallGrassBlock
{
	private final boolean needsDarkness;

	public AerialHellTallShroomBlock(BlockBehaviour.Properties settings, boolean needsDarkness) {super(settings); this.needsDarkness = needsDarkness;}

	@Override public void performBonemeal(ServerLevel world, RandomSource rand, BlockPos pos, BlockState state)
	{
		DoublePlantBlock tall_plant;
		if (this == AerialHellBlocks.GLOWING_BOLETUS)
		{
			tall_plant = (DoublePlantBlock) AerialHellBlocks.TALL_GLOWING_BOLETUS;
			placePlant(world, pos, tall_plant);
		}
	}

	protected void placePlant(ServerLevel world, BlockPos pos, DoublePlantBlock plantIn)
	{
		if (plantIn.defaultBlockState().canSurvive(world, pos) && world.isEmptyBlock(pos.above()))
		{
			plantIn.placeAt(world, plantIn.defaultBlockState(), pos, 2);
		}
	}

	@Override public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos)
	{
		BlockState belowState = world.getBlockState(pos.below());
		if (this.mayPlaceOn(belowState, world, pos))
		{
			if (!this.needsDarkness) {return true;}
			else
			{
				boolean brightnessFlag = world.getMaxLocalRawBrightness(pos, 0) < 13;
				boolean solidSurfaceAbove = BlockHelper.hasAnySolidSurfaceAbove(world, pos, 3);
				return brightnessFlag && solidSurfaceAbove;
			}
		}
		else {return false;}
	}

	@Override protected boolean mayPlaceOn(BlockState state, BlockGetter world, BlockPos pos)
	{
		if (this == AerialHellBlocks.GIANT_ROOT_SHROOM && state.is(AerialHellBlocks.GIANT_ROOT)) {return true;}
		return state.is(BlockTags.DIRT) || state.is(AerialHellBlocks.STELLAR_COARSE_DIRT) || state.is(AerialHellTags.Blocks.STELLAR_STONE_AND_DERIVATIVES) || state.is(BlockTags.OVERRIDES_MUSHROOM_LIGHT_REQUIREMENT);
	}

	@Override public void entityInside(BlockState state, Level world, BlockPos pos, Entity entity, InsideBlockEffectApplier handler, boolean intersects)
	{
		if (this == AerialHellBlocks.GIANT_ROOT_SHROOM && entity instanceof LivingEntity livingEntity)
		{
			livingEntity.addEffect(new MobEffectInstance(MobEffects.SPEED, 100, 0, false, false, true));
		}
	}
}
