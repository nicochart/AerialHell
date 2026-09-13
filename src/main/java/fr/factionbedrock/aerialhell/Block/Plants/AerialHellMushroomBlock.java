package fr.factionbedrock.aerialhell.Block.Plants;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellConfiguredFeatures;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.MushroomBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.tags.BlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.server.level.ServerLevel;

public class AerialHellMushroomBlock extends MushroomBlock
{
	public AerialHellMushroomBlock(ResourceKey<ConfiguredFeature<?, ?>> featureKey, Properties properties) {super(featureKey, properties);}

	@Override
	public void randomTick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource random) {}

	@Override protected boolean mayPlaceOn(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return state.is(AerialHellTags.Blocks.STELLAR_DIRT) || state.is(BlockTags.OVERRIDES_MUSHROOM_LIGHT_REQUIREMENT);
	}

	@Override public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos) {
		BlockState blockstate = worldIn.getBlockState(pos.below());
		if (blockstate.is(AerialHellTags.Blocks.STELLAR_DIRT) || blockstate.is(BlockTags.OVERRIDES_MUSHROOM_LIGHT_REQUIREMENT)) {return true;} else {return false;}
	}

	public enum HugeGenerationDirections{NONE, NORTH_WEST, NORTH_EAST, SOUTH_WEST, SOUTH_EAST}

	@Override public boolean growMushroom(ServerLevel world, BlockPos pos, BlockState state, RandomSource rand)
	{
		BlockPos generationPos = pos;
		ConfiguredFeature<?, ?> configuredfeature;
		HugeGenerationDirections hugeShroomDirection = getHugeShroomDirection(world, pos, this.asBlock());
		if (state.is(AerialHellBlocks.VERDIGRIS_AGARIC.get()))
		{
			if (hugeShroomDirection != HugeGenerationDirections.NONE)
			{
				generationPos = getOffsetPosForHugeShroom(pos, hugeShroomDirection);
				configuredfeature = world.registryAccess().lookupOrThrow(Registries.CONFIGURED_FEATURE).get(AerialHellConfiguredFeatures.HUGE_VERDIGRIS_AGARIC).orElse(null).value();
			}
			else {configuredfeature = world.registryAccess().lookupOrThrow(Registries.CONFIGURED_FEATURE).get(AerialHellConfiguredFeatures.GIANT_VERDIGRIS_AGARIC).orElse(null).value();}
		}
		else {return false;}

		//removing shrooms before placing feature
		world.setBlock(pos, Blocks.AIR.defaultBlockState(), 4);
		if (hugeShroomDirection != HugeGenerationDirections.NONE) {setFourBlocks(world, generationPos, hugeShroomDirection, Blocks.AIR.defaultBlockState(), 4);}

		if (configuredfeature.place(world, world.getChunkSource().getGenerator(), rand, generationPos)) {return true;}
		else
		{
			//if generation fails
			world.setBlock(pos, state, 3);
			if (hugeShroomDirection != HugeGenerationDirections.NONE) {setFourBlocks(world, generationPos, hugeShroomDirection, state, 3);}
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
