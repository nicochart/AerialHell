package fr.factionbedrock.aerialhell.Block.Plants;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellConfiguredFeatures;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.NetherFungusBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import java.util.Optional;

public class AerialHellFungusBlock extends NetherFungusBlock
{
	public AerialHellFungusBlock(ResourceKey<ConfiguredFeature<?, ?>> fungusFeature, Block requiredBlock, TagKey<Block> supportBlocks, Properties properties) {super(fungusFeature, requiredBlock, supportBlocks, properties);}

	@Override protected boolean mayPlaceOn(BlockState state, BlockGetter worldIn, BlockPos pos)
	{
		return state.is(AerialHellBlocks.STELLAR_GRASS_BLOCK.get()) || super.mayPlaceOn(state, worldIn, pos);
	}

	@Override public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state)
	{
		if (state.is(AerialHellBlocks.CORTINARIUS_VIOLACEUS))
		{
			AerialHellMushroomBlock.HugeGenerationDirections hugeShroomDirection = AerialHellMushroomBlock.getHugeShroomDirection(level, pos, this.asBlock());
			if (hugeShroomDirection != AerialHellMushroomBlock.HugeGenerationDirections.NONE)
			{
				BlockPos generationPos = AerialHellMushroomBlock.getOffsetPosForHugeShroom(pos, hugeShroomDirection);
				ConfiguredFeature<?, ?> configuredfeature = level.registryAccess().lookupOrThrow(Registries.CONFIGURED_FEATURE).get(AerialHellConfiguredFeatures.CORTINARIUS_VIOLACEUS_MUSHROOM_CAPS_COLUMN).orElse(null).value();
				AerialHellMushroomBlock.setFourBlocks(level, generationPos, hugeShroomDirection, Blocks.AIR.defaultBlockState(), 4);

				if (configuredfeature.place(level, level.getChunkSource().getGenerator(), random, generationPos)) {return;}
				else {AerialHellMushroomBlock.setFourBlocks(level, generationPos, hugeShroomDirection, state, 3);}
			}
		}
		super.performBonemeal(level, random, pos, state);
	}
}
