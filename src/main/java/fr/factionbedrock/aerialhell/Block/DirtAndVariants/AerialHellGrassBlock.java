package fr.factionbedrock.aerialhell.Block.DirtAndVariants;

import net.minecraft.block.*;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.RandomPatchFeatureConfig;

import java.util.List;
import java.util.Optional;

public abstract class AerialHellGrassBlock extends GrassBlock implements Fertilizable
{
	public static final BooleanProperty SHIFTED_RENDER = BooleanProperty.of("shifted_render"); //only used for render purposes

	public AerialHellGrassBlock(AbstractBlock.Settings settings)
	{
		super(settings);
		this.setDefaultState(this.getDefaultState().with(SNOWY, false).with(SHIFTED_RENDER, false));
	}

	@Override protected void appendProperties(StateManager.Builder<Block, BlockState> builder)
	{
		super.appendProperties(builder);
		builder.add(SHIFTED_RENDER);
	}

	@Override public void grow(ServerWorld world, Random rand, BlockPos pos, BlockState state)
	{
		BlockPos blockpos = pos.up();
		Optional<RegistryEntry.Reference<PlacedFeature>> optional = this.getBonemealFeature(world);


		label46:
		for(int i = 0; i < 128; ++i)
		{
			BlockPos blockpos1 = blockpos;

			for(int j = 0; j < i / 16; ++j)
			{
				blockpos1 = blockpos1.add(rand.nextInt(3) - 1, (rand.nextInt(3) - 1) * rand.nextInt(3) / 2, rand.nextInt(3) - 1);
				if (!world.getBlockState(blockpos1.down()).isOf(this) || world.getBlockState(blockpos1).isFullCube(world, blockpos1)) {continue label46;}
			}

			BlockState blockstate2 = world.getBlockState(blockpos1);
			if (blockstate2.isOf(this) && rand.nextInt(10) == 0)
			{
				((Fertilizable)this.getDefaultState().getBlock()).grow(world, rand, blockpos1, blockstate2);
			}

			if (blockstate2.isAir())
			{
				RegistryEntry registryEntry;
				if (rand.nextInt(8) == 0)
				{
					List<ConfiguredFeature<?, ?>> list = world.getBiome(blockpos1).value().getGenerationSettings().getFlowerFeatures();
					if (list.isEmpty()) {continue;}
					registryEntry = ((RandomPatchFeatureConfig)(list.get(0)).getConfig()).feature();
				}
				else
				{
					if (!optional.isPresent()) {continue;}
					registryEntry = optional.get();
				}
				((PlacedFeature)registryEntry.value()).generateUnregistered(world, world.getChunkManager().getChunkGenerator(), rand, blockpos1);
			}
		}
	}

	protected abstract Optional<RegistryEntry.Reference<PlacedFeature>> getBonemealFeature(ServerWorld world);

	/*@Override @Nullable TODO : use mixins to intercept onItemUse (Axe, Shovel, Hoe..)
	public BlockState getToolModifiedState(BlockState state, UseOnContext context, ItemAbility itemAbility, boolean simulate)
	{
		if (!context.getItemInHand().canPerformAction(itemAbility)) {return null;}
		if (state.getBlock() == AerialHellBlocksAndItems.STELLAR_GRASS_BLOCK.get() || state.getBlock() == AerialHellBlocksAndItems.CHISELED_STELLAR_GRASS_BLOCK.get())
		{
			if (ItemAbilities.HOE_TILL == itemAbility) {return AerialHellBlocksAndItems.STELLAR_FARMLAND.get().getDefaultState();}
			if (ItemAbilities.SHOVEL_FLATTEN == itemAbility) {return AerialHellBlocksAndItems.STELLAR_DIRT_PATH.get().getDefaultState();}
		}
		return super.getToolModifiedState(state, context, itemAbility, simulate);
	}*/
}