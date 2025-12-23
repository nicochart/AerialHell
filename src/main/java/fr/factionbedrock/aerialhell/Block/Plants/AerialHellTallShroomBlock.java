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
import net.minecraft.world.level.block.state.BlockState;

public class AerialHellTallShroomBlock extends TallGrassBlock
{
	private final boolean needsDarkness;

	public AerialHellTallShroomBlock(Properties properties, boolean needsDarkness) {super(properties); this.needsDarkness = needsDarkness;}

	@Override public void performBonemeal(ServerLevel level, RandomSource rand, BlockPos pos, BlockState state)
	{
		DoublePlantBlock tall_plant;
		if (this == AerialHellBlocks.GLOWING_BOLETUS.get())
		{
			tall_plant = (DoublePlantBlock) AerialHellBlocks.TALL_GLOWING_BOLETUS.get();
			placePlant(level, pos, tall_plant);
		}
	}

	protected void placePlant(ServerLevel level, BlockPos pos, DoublePlantBlock plantIn)
	{
		if (plantIn.defaultBlockState().canSurvive(level, pos) && level.isEmptyBlock(pos.above()))
		{
			plantIn.placeAt(level, plantIn.defaultBlockState(), pos, 2);
		}
	}

	@Override public boolean canSurvive(BlockState state, LevelReader reader, BlockPos pos)
	{
		BlockState belowState = reader.getBlockState(pos.below());
		if (this.mayPlaceOn(belowState, reader, pos))
		{
			if (!this.needsDarkness) {return true;}
			else
			{
				boolean brightnessFlag = reader.getRawBrightness(pos, 0) < 13;
				boolean solidSurfaceAbove = BlockHelper.hasAnySolidSurfaceAbove(reader, pos, 3);
				return brightnessFlag && solidSurfaceAbove;
			}
		}
		else {return false;}
	}

	@Override protected boolean mayPlaceOn(BlockState state, BlockGetter getter, BlockPos pos)
	{
		if (this == AerialHellBlocks.GIANT_ROOT_SHROOM.get() && state.is(AerialHellBlocks.GIANT_ROOT.get())) {return true;}
		return state.is(BlockTags.DIRT) || state.is(AerialHellBlocks.STELLAR_COARSE_DIRT.get()) || state.is(AerialHellTags.Blocks.STELLAR_STONE_AND_DERIVATIVES) || state.is(BlockTags.MUSHROOM_GROW_BLOCK);
	}

	@Override public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity, InsideBlockEffectApplier effectApplier, boolean intersects)
	{
		if (this == AerialHellBlocks.GIANT_ROOT_SHROOM.get() && entity instanceof LivingEntity livingEntity)
		{
			livingEntity.addEffect(new MobEffectInstance(MobEffects.SPEED, 100, 0, false, false, true));
		}
	}
}
