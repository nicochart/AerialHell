package fr.factionbedrock.aerialhell.Block.Plants;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellConfiguredFeatures;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.MushroomPlantBlock;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;
import net.minecraft.world.gen.feature.ConfiguredFeature;

public class AerialHellMushroomBlock extends MushroomPlantBlock
{
	public AerialHellMushroomBlock(RegistryKey<ConfiguredFeature<?, ?>> featureKey, AbstractBlock.Settings settings) {super(featureKey, settings);}

	@Override
	public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {}

	@Override protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos)
	{
		return floor.isIn(AerialHellTags.Blocks.STELLAR_DIRT) || floor.isIn(BlockTags.MUSHROOM_GROW_BLOCK);
	}

	@Override public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos)
	{
		BlockState blockstate = world.getBlockState(pos.down());
        return blockstate.isIn(AerialHellTags.Blocks.STELLAR_DIRT) || blockstate.isIn(BlockTags.MUSHROOM_GROW_BLOCK);
	}

	private static enum HugeGenerationDirections{NONE, NORTH_WEST, NORTH_EAST, SOUTH_WEST, SOUTH_EAST}

	@Override public boolean trySpawningBigMushroom(ServerWorld world, BlockPos pos, BlockState state, Random rand)
	{
		BlockPos generationPos = pos;
		ConfiguredFeature<?, ?> configuredfeature;
		HugeGenerationDirections hugeShroomDirection = this.getHugeShroomDirection(world, pos, state);
		if (this == AerialHellBlocks.VERDIGRIS_AGARIC)
		{
			if (hugeShroomDirection != HugeGenerationDirections.NONE)
			{
				generationPos = this.getOffsetPosForHugeShroom(pos, hugeShroomDirection);
				configuredfeature = world.getRegistryManager().get(RegistryKeys.CONFIGURED_FEATURE).getEntry(AerialHellConfiguredFeatures.HUGE_VERDIGRIS_AGARIC).orElse(null).get().value(); //TODO check
			}
			else {configuredfeature = world.getRegistryManager().get(RegistryKeys.CONFIGURED_FEATURE).getEntry(AerialHellConfiguredFeatures.GIANT_VERDIGRIS_AGARIC).orElse(null).get().value();}
		} else {return false;}

		world.removeBlock(generationPos, false);
		if (configuredfeature.generate(world, world.getChunkManager().getChunkGenerator(), rand, generationPos)) {return true;} else {world.setBlockState(pos, state); return false;}
	}

	//Returns the direction of the generation of the huge shroom (NONE if there is no direction)
	public HugeGenerationDirections getHugeShroomDirection(ServerWorld world, BlockPos pos, BlockState state) {
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
