package fr.factionbedrock.aerialhell.Block.Plants;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellConfiguredFeatures;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
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

	private static enum HugeGenerationDirections{NONE, NORTH_WEST, NORTH_EAST, SOUTH_WEST, SOUTH_EAST}

	@Override public boolean growMushroom(ServerLevel world, BlockPos pos, BlockState state, RandomSource rand)
	{
		BlockPos generationPos = pos;
		ConfiguredFeature<?, ?> configuredfeature;
		HugeGenerationDirections hugeShroomDirection = this.getHugeShroomDirection(world, pos, state);
		if (this == AerialHellBlocks.VERDIGRIS_AGARIC.get())
		{
			if (hugeShroomDirection != HugeGenerationDirections.NONE)
			{
				generationPos = this.getOffsetPosForHugeShroom(pos, hugeShroomDirection);
				configuredfeature = world.registryAccess().lookupOrThrow(Registries.CONFIGURED_FEATURE).get(AerialHellConfiguredFeatures.HUGE_VERDIGRIS_AGARIC).orElse(null).value();

			}
			else {configuredfeature = world.registryAccess().lookupOrThrow(Registries.CONFIGURED_FEATURE).get(AerialHellConfiguredFeatures.GIANT_VERDIGRIS_AGARIC).orElse(null).value();;}
		}
		else {return false;}

		//removing shrooms before placing feature
		BlockState airState = net.minecraft.world.level.block.Blocks.AIR.defaultBlockState();
		world.setBlock(pos, airState, 4);
		if (hugeShroomDirection != HugeGenerationDirections.NONE) {this.setMushroomBlocks(world, pos, hugeShroomDirection, airState, 4);}

		if (configuredfeature.place(world, world.getChunkSource().getGenerator(), rand, generationPos)) {return true;}
		else
		{
			//if generation fails
			world.setBlock(pos, state, 3); // Flag 3 = update normal du bloc
			if (hugeShroomDirection != HugeGenerationDirections.NONE)
			{
				this.setMushroomBlocks(world, pos, hugeShroomDirection, state, 3);
			}
			return false;
		}
	}

	//Returns the direction of the generation of the huge shroom (NONE if there is no direction)
	public HugeGenerationDirections getHugeShroomDirection(ServerLevel world, BlockPos pos, BlockState state)
	{
		AerialHellMushroomBlock mushroomBlock = (AerialHellMushroomBlock) this.asBlock();
		if (world.getBlockState(pos.north()).is(mushroomBlock))
		{
			if (world.getBlockState(pos.west()).is(mushroomBlock) && world.getBlockState(pos.north().west()).is(mushroomBlock)) {return HugeGenerationDirections.NORTH_WEST;}
			if (world.getBlockState(pos.east()).is(mushroomBlock) && world.getBlockState(pos.north().east()).is(mushroomBlock)) {return HugeGenerationDirections.NORTH_EAST;}
		}
		if (world.getBlockState(pos.south()).is(mushroomBlock))
		{
			if (world.getBlockState(pos.west()).is(mushroomBlock) && world.getBlockState(pos.south().west()).is(mushroomBlock)) {return HugeGenerationDirections.SOUTH_WEST;}
			if (world.getBlockState(pos.east()).is(mushroomBlock) && world.getBlockState(pos.south().east()).is(mushroomBlock)) {return HugeGenerationDirections.SOUTH_EAST;}
		}
		return HugeGenerationDirections.NONE;
	}

	private void setMushroomBlocks(ServerLevel world, BlockPos pos, HugeGenerationDirections dir, BlockState state, int flags)
	{
		if (dir == HugeGenerationDirections.NORTH_WEST)
		{
			world.setBlock(pos.north(), state, flags);
			world.setBlock(pos.west(), state, flags);
			world.setBlock(pos.north().west(), state, flags);
		}
		else if (dir == HugeGenerationDirections.NORTH_EAST)
		{
			world.setBlock(pos.north(), state, flags);
			world.setBlock(pos.east(), state, flags);
			world.setBlock(pos.north().east(), state, flags);
		}
		else if (dir == HugeGenerationDirections.SOUTH_WEST)
		{
			world.setBlock(pos.south(), state, flags);
			world.setBlock(pos.west(), state, flags);
			world.setBlock(pos.south().west(), state, flags);
		}
		else if (dir == HugeGenerationDirections.SOUTH_EAST)
		{
			world.setBlock(pos.south(), state, flags);
			world.setBlock(pos.east(), state, flags);
			world.setBlock(pos.south().east(), state, flags);
		}
	}

	public BlockPos getOffsetPosForHugeShroom(BlockPos basePos, HugeGenerationDirections generationDirection)
	{
		if (generationDirection == HugeGenerationDirections.NORTH_WEST) {return basePos.north().west();} else if (generationDirection == HugeGenerationDirections.NORTH_EAST) {return basePos.north();} else if (generationDirection == HugeGenerationDirections.SOUTH_WEST) {return basePos.west();} else /*(generationDirection == HugeGenerationDirections.SOUTH_EAST)*/ {return basePos;}
	}
}
