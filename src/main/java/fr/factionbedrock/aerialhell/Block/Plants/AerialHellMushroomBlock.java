package fr.factionbedrock.aerialhell.Block.Plants;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellConfiguredFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.MushroomBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;

public class AerialHellMushroomBlock extends MushroomBlock
{
	public AerialHellMushroomBlock(ResourceKey<ConfiguredFeature<?, ?>> featureKey, BlockBehaviour.Properties settings) {super(featureKey, settings);}

	@Override
	public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {}

	@Override protected boolean mayPlaceOn(BlockState floor, BlockGetter world, BlockPos pos)
	{
		return floor.is(AerialHellTags.Blocks.STELLAR_DIRT) || floor.is(BlockTags.OVERRIDES_MUSHROOM_LIGHT_REQUIREMENT);
	}

	@Override public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos)
	{
		BlockState blockstate = level.getBlockState(pos.below());
        return blockstate.is(AerialHellTags.Blocks.STELLAR_DIRT) || blockstate.is(BlockTags.OVERRIDES_MUSHROOM_LIGHT_REQUIREMENT);
	}

	public enum HugeGenerationDirections{NONE, NORTH_WEST, NORTH_EAST, SOUTH_WEST, SOUTH_EAST}

	@Override public boolean growMushroom(ServerLevel level, BlockPos pos, BlockState state, RandomSource rand)
	{
		BlockPos generationPos = pos;
		ConfiguredFeature<?, ?> configuredfeature;
		HugeGenerationDirections hugeShroomDirection = getHugeShroomDirection(level, pos, this.asBlock());
		if (state.is(AerialHellBlocks.VERDIGRIS_AGARIC))
		{
			if (hugeShroomDirection != HugeGenerationDirections.NONE)
			{
				generationPos = getOffsetPosForHugeShroom(pos, hugeShroomDirection);
				configuredfeature = level.registryAccess().lookupOrThrow(Registries.CONFIGURED_FEATURE).get(AerialHellConfiguredFeatures.HUGE_VERDIGRIS_AGARIC).orElse(null).value();
			}
			else {configuredfeature = level.registryAccess().lookupOrThrow(Registries.CONFIGURED_FEATURE).get(AerialHellConfiguredFeatures.GIANT_VERDIGRIS_AGARIC).orElse(null).value();}
		}
		else {return false;}

		//removing shrooms before placing feature
		level.setBlock(pos, Blocks.AIR.defaultBlockState(), 4);
		if (hugeShroomDirection != HugeGenerationDirections.NONE) {setFourBlocks(level, generationPos, hugeShroomDirection, Blocks.AIR.defaultBlockState(), 4);}

		if (configuredfeature.place(level, level.getChunkSource().getGenerator(), rand, generationPos)) {return true;}
		else
		{
			//if generation fails
			level.setBlock(pos, state, 3);
			if (hugeShroomDirection != HugeGenerationDirections.NONE) {setFourBlocks(level, generationPos, hugeShroomDirection, state, 3);}
			return false;
		}
	}

	//Returns the direction of the generation of the huge shroom (NONE if there is no direction)
	public static HugeGenerationDirections getHugeShroomDirection(ServerLevel world, BlockPos pos, Block block)
	{
		if (world.getBlockState(pos.north()).is(block))
		{
			if (world.getBlockState(pos.west()).is(block) && world.getBlockState(pos.north().west()).is(block)) {return HugeGenerationDirections.NORTH_WEST;}
			if (world.getBlockState(pos.east()).is(block) && world.getBlockState(pos.north().east()).is(block)) {return HugeGenerationDirections.NORTH_EAST;}
		}
		if (world.getBlockState(pos.south()).is(block))
		{
			if (world.getBlockState(pos.west()).is(block) && world.getBlockState(pos.south().west()).is(block)) {return HugeGenerationDirections.SOUTH_WEST;}
			if (world.getBlockState(pos.east()).is(block) && world.getBlockState(pos.south().east()).is(block)) {return HugeGenerationDirections.SOUTH_EAST;}
		}
		return HugeGenerationDirections.NONE;
	}

	//needs generation pos as parameter
	public static void setFourBlocks(ServerLevel level, BlockPos genPos, HugeGenerationDirections dir, BlockState state, int flags)
	{
		level.setBlock(genPos, state, flags);
		level.setBlock(genPos.south(), state, flags);
		level.setBlock(genPos.east(), state, flags);
		level.setBlock(genPos.south().east(), state, flags);
	}

	public static BlockPos getOffsetPosForHugeShroom(BlockPos basePos, HugeGenerationDirections generationDirection)
	{
		if (generationDirection == HugeGenerationDirections.NORTH_WEST) {return basePos.north().west();} else if (generationDirection == HugeGenerationDirections.NORTH_EAST) {return basePos.north();} else if (generationDirection == HugeGenerationDirections.SOUTH_WEST) {return basePos.west();} else /*(generationDirection == HugeGenerationDirections.SOUTH_EAST)*/ {return basePos;}
	}
}
