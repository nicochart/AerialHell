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
import net.minecraft.world.level.block.MushroomBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;

public class AerialHellMushroomBlock extends MushroomBlock
{
	public AerialHellMushroomBlock(ResourceKey<ConfiguredFeature<?, ?>> featureKey, BlockBehaviour.Properties settings) {super(featureKey, settings);}

	@Override
	public void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {}

	@Override protected boolean mayPlaceOn(BlockState floor, BlockGetter world, BlockPos pos)
	{
		return floor.is(AerialHellTags.Blocks.STELLAR_DIRT) || floor.is(BlockTags.OVERRIDES_MUSHROOM_LIGHT_REQUIREMENT);
	}

	@Override public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos)
	{
		BlockState blockstate = world.getBlockState(pos.below());
        return blockstate.is(AerialHellTags.Blocks.STELLAR_DIRT) || blockstate.is(BlockTags.OVERRIDES_MUSHROOM_LIGHT_REQUIREMENT);
	}

	private static enum HugeGenerationDirections{NONE, NORTH_WEST, NORTH_EAST, SOUTH_WEST, SOUTH_EAST}

	@Override public boolean growMushroom(ServerLevel world, BlockPos pos, BlockState state, RandomSource rand)
	{
		BlockPos generationPos = pos;
		ConfiguredFeature<?, ?> configuredfeature;
		HugeGenerationDirections hugeShroomDirection = this.getHugeShroomDirection(world, pos, state);
		if (this == AerialHellBlocks.VERDIGRIS_AGARIC)
		{
			if (hugeShroomDirection != HugeGenerationDirections.NONE)
			{
				generationPos = this.getOffsetPosForHugeShroom(pos, hugeShroomDirection);
				configuredfeature = world.registryAccess().lookupOrThrow(Registries.CONFIGURED_FEATURE).get(AerialHellConfiguredFeatures.HUGE_VERDIGRIS_AGARIC).orElse(null).value(); //TODO check
			}
			else {configuredfeature = world.registryAccess().lookupOrThrow(Registries.CONFIGURED_FEATURE).get(AerialHellConfiguredFeatures.GIANT_VERDIGRIS_AGARIC).orElse(null).value();}
		} else {return false;}

		world.removeBlock(generationPos, false);
		if (configuredfeature.place(world, world.getChunkSource().getGenerator(), rand, generationPos)) {return true;} else {world.setBlockAndUpdate(pos, state); return false;}
	}

	//Returns the direction of the generation of the huge shroom (NONE if there is no direction)
	public HugeGenerationDirections getHugeShroomDirection(ServerLevel world, BlockPos pos, BlockState state) {
		AerialHellMushroomBlock mushroomBlock = (AerialHellMushroomBlock) this.asBlock();
		if (world.getBlockState(pos.north()).getBlock() == mushroomBlock) {
			if (world.getBlockState(pos.west()).getBlock() == mushroomBlock && world.getBlockState(pos.north().west()).getBlock() == mushroomBlock) {return HugeGenerationDirections.NORTH_WEST;}
			if (world.getBlockState(pos.east()).getBlock() == mushroomBlock && world.getBlockState(pos.north().east()).getBlock() == mushroomBlock) {return HugeGenerationDirections.NORTH_EAST;}
		}
		if (world.getBlockState(pos.south()).getBlock() == mushroomBlock) {
			if (world.getBlockState(pos.west()).getBlock() == mushroomBlock && world.getBlockState(pos.south().west()).getBlock() == mushroomBlock) {return HugeGenerationDirections.SOUTH_WEST;}
			if (world.getBlockState(pos.east()).getBlock() == mushroomBlock && world.getBlockState(pos.south().east()).getBlock() == mushroomBlock) {return HugeGenerationDirections.SOUTH_EAST;}
		}
		return HugeGenerationDirections.NONE;
	}

	public BlockPos getOffsetPosForHugeShroom(BlockPos basePos, HugeGenerationDirections generationDirection) {
		if (generationDirection == HugeGenerationDirections.NORTH_WEST) {return basePos.north().west();} else if (generationDirection == HugeGenerationDirections.NORTH_EAST) {return basePos.north();} else if (generationDirection == HugeGenerationDirections.SOUTH_WEST) {return basePos.west();} else /*(generationDirection == HugeGenerationDirections.SOUTH_EAST)*/ {return basePos;}
	}
}
