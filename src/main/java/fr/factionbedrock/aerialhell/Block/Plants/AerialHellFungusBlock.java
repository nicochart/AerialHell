package fr.factionbedrock.aerialhell.Block.Plants;

import java.util.function.Supplier;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellConfiguredFeatures;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FungusBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.HugeFungusConfiguration;

public class AerialHellFungusBlock extends FungusBlock
{
	public AerialHellFungusBlock(ResourceKey<ConfiguredFeature<?, ?>> fungusFeature, Block floorBlock, Properties properties) {super(fungusFeature, floorBlock, properties);}

	@Override protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos)
	{
		return state.is(AerialHellBlocksAndItems.STELLAR_GRASS_BLOCK.get()) || super.mayPlaceOn(state, level, pos);
	}

	@Override public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state)
	{
		if (state.is(AerialHellBlocksAndItems.CORTINARIUS_VIOLACEUS))
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
