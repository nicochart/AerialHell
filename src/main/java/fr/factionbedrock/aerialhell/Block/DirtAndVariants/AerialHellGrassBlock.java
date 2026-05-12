package fr.factionbedrock.aerialhell.Block.DirtAndVariants;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.AerialHellStateProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Util;
import net.minecraft.world.item.context.UseOnContext;
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
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public abstract class AerialHellGrassBlock extends GrassBlock implements BonemealableBlock
{
	public AerialHellGrassBlock(Properties properties)
	{
		super(properties);
		this.registerDefaultState(this.defaultBlockState().setValue(SNOWY, false).setValue(AerialHellStateProperties.SHIFTED_RENDER, false));
	}

	@Override protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
	{
		super.createBlockStateDefinition(builder);
		builder.add(AerialHellStateProperties.SHIFTED_RENDER);
	}

	@Override public void performBonemeal(ServerLevel serverLevel, RandomSource randomSource, BlockPos blockPos, BlockState blockState)
	{
		BlockPos blockpos = blockPos.above();
		BlockState blockstate = Blocks.SHORT_GRASS.defaultBlockState();
		Optional<Holder.Reference<PlacedFeature>> optional = serverLevel.registryAccess().lookupOrThrow(Registries.PLACED_FEATURE).get(this.getBonemealFeature());

		label51:
		for(int i = 0; i < 128; ++i)
		{
			BlockPos blockpos1 = blockpos;

			for(int j = 0; j < i / 16; ++j)
			{
				blockpos1 = blockpos1.offset(randomSource.nextInt(3) - 1, (randomSource.nextInt(3) - 1) * randomSource.nextInt(3) / 2, randomSource.nextInt(3) - 1);
				if (!serverLevel.getBlockState(blockpos1.below()).is(this) || serverLevel.getBlockState(blockpos1).isCollisionShapeFullBlock(serverLevel, blockpos1))
				{
					continue label51;
				}
			}

			BlockState blockstate1 = serverLevel.getBlockState(blockpos1);
			if (blockstate1.is(blockstate.getBlock()) && randomSource.nextInt(10) == 0)
			{
				BonemealableBlock bonemealableblock = (BonemealableBlock)blockstate.getBlock();
				if (bonemealableblock.isValidBonemealTarget(serverLevel, blockpos1, blockstate1))
				{
					bonemealableblock.performBonemeal(serverLevel, randomSource, blockpos1, blockstate1);
				}
			}

			if (blockstate1.isAir())
			{
				Holder<PlacedFeature> holder;
				if (randomSource.nextInt(8) == 0)
				{
					List<ConfiguredFeature<?, ?>> list = ((Biome)serverLevel.getBiome(blockpos1).value()).getGenerationSettings().getFlowerFeatures();
					if (list.isEmpty()) {continue;}

					int k = randomSource.nextInt(list.size());
					holder = ((RandomPatchConfiguration)((ConfiguredFeature)list.get(k)).config()).feature();
				} else
				{
					if (!optional.isPresent()) {continue;}
					holder = optional.get();
				}
				(holder.value()).place(serverLevel, serverLevel.getChunkSource().getGenerator(), randomSource, blockpos1);
			}
		}
	}

	protected abstract ResourceKey<PlacedFeature> getBonemealFeature();

	@Override @Nullable
	public BlockState getToolModifiedState(BlockState state, UseOnContext context, ItemAbility itemAbility, boolean simulate)
	{
		if (!context.getItemInHand().canPerformAction(itemAbility)) {return null;}
		if (state.getBlock() == AerialHellBlocks.STELLAR_GRASS_BLOCK.get() || state.getBlock() == AerialHellBlocks.CHISELED_STELLAR_GRASS_BLOCK.get())
		{
			if (ItemAbilities.HOE_TILL == itemAbility) {return AerialHellBlocks.STELLAR_FARMLAND.get().defaultBlockState();}
			if (ItemAbilities.SHOVEL_FLATTEN == itemAbility) {return AerialHellBlocks.STELLAR_DIRT_PATH.get().defaultBlockState();}
		}
		return super.getToolModifiedState(state, context, itemAbility, simulate);
	}
}