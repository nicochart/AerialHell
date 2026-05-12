package fr.factionbedrock.aerialhell.Block.DirtAndVariants;

import fr.factionbedrock.aerialhell.Registry.AerialHellBooleanProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.GrassBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.List;
import java.util.Optional;

public abstract class AerialHellGrassBlock extends GrassBlock implements BonemealableBlock
{
	public AerialHellGrassBlock(Properties settings)
	{
		super(settings);
		this.registerDefaultState(this.defaultBlockState().setValue(SNOWY, false).setValue(AerialHellBooleanProperties.SHIFTED_RENDER, false));
	}

	@Override protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
	{
		super.createBlockStateDefinition(builder);
		builder.add(AerialHellBooleanProperties.SHIFTED_RENDER);
	}

	@Override public void performBonemeal(ServerLevel serverLevel, RandomSource randomSource, BlockPos blockPos, BlockState blockState)
	{
		BlockPos blockPos2 = blockPos.above();
		BlockState blockState2 = Blocks.SHORT_GRASS.defaultBlockState();
		Optional<Holder.Reference<PlacedFeature>> optional = serverLevel.registryAccess().lookupOrThrow(Registries.PLACED_FEATURE).get(this.getBonemealFeature());

		label51:
		for(int i = 0; i < 128; ++i)
		{
			BlockPos blockPos3 = blockPos2;

			for(int j = 0; j < i / 16; ++j)
			{
				blockPos3 = blockPos3.offset(randomSource.nextInt(3) - 1, (randomSource.nextInt(3) - 1) * randomSource.nextInt(3) / 2, randomSource.nextInt(3) - 1);
				if (!serverLevel.getBlockState(blockPos3.below()).is(this) || serverLevel.getBlockState(blockPos3).isCollisionShapeFullBlock(serverLevel, blockPos3))
				{
					continue label51;
				}
			}

			BlockState blockState3 = serverLevel.getBlockState(blockPos3);
			if (blockState3.is(blockState2.getBlock()) && randomSource.nextInt(10) == 0)
			{
				BonemealableBlock bonemealableBlock = (BonemealableBlock)blockState2.getBlock();
				if (bonemealableBlock.isValidBonemealTarget(serverLevel, blockPos3, blockState3))
				{
					bonemealableBlock.performBonemeal(serverLevel, randomSource, blockPos3, blockState3);
				}
			}

			if (blockState3.isAir())
			{
				Holder<PlacedFeature> holder;
				if (randomSource.nextInt(8) == 0)
				{
					List<ConfiguredFeature<?, ?>> list = ((Biome)serverLevel.getBiome(blockPos3).value()).getGenerationSettings().getFlowerFeatures();
					if (list.isEmpty()) {continue;}

					int k = randomSource.nextInt(list.size());
					holder = ((RandomPatchConfiguration)((ConfiguredFeature)list.get(k)).config()).feature();
				}
				else
				{
					if (!optional.isPresent()) {continue;}

					holder = optional.get();
				}
				holder.value().place(serverLevel, serverLevel.getChunkSource().getGenerator(), randomSource, blockPos3);
			}
		}
	}

	protected abstract ResourceKey<PlacedFeature> getBonemealFeature();
}