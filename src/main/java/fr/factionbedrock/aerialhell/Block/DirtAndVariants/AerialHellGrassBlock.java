package fr.factionbedrock.aerialhell.Block.DirtAndVariants;

import fr.factionbedrock.aerialhell.Registry.AerialHellBooleanProperties;
import fr.factionbedrock.aerialhell.World.Features.Config.RandomPatchConfiguration;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.GrassBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import java.util.List;
import java.util.Optional;

public abstract class AerialHellGrassBlock extends GrassBlock implements BonemealableBlock
{
	public AerialHellGrassBlock(BlockBehaviour.Properties settings)
	{
		super(settings);
		this.registerDefaultState(this.defaultBlockState().setValue(SNOWY, false).setValue(AerialHellBooleanProperties.SHIFTED_RENDER, false));
	}

	@Override protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
	{
		super.createBlockStateDefinition(builder);
		builder.add(AerialHellBooleanProperties.SHIFTED_RENDER);
	}

	@Override public void performBonemeal(ServerLevel world, RandomSource rand, BlockPos pos, BlockState state)
	{
		BlockPos blockpos = pos.above();
		Optional<Holder.Reference<PlacedFeature>> optional = this.getBonemealFeature(world);


		label46:
		for(int i = 0; i < 128; ++i)
		{
			BlockPos blockpos1 = blockpos;

			for(int j = 0; j < i / 16; ++j)
			{
				blockpos1 = blockpos1.offset(rand.nextInt(3) - 1, (rand.nextInt(3) - 1) * rand.nextInt(3) / 2, rand.nextInt(3) - 1);
				if (!world.getBlockState(blockpos1.below()).is(this) || world.getBlockState(blockpos1).isCollisionShapeFullBlock(world, blockpos1)) {continue label46;}
			}

			BlockState blockstate2 = world.getBlockState(blockpos1);
			if (blockstate2.is(this) && rand.nextInt(10) == 0)
			{
				((BonemealableBlock)this.defaultBlockState().getBlock()).performBonemeal(world, rand, blockpos1, blockstate2);
			}

			if (blockstate2.isAir())
			{
				Holder registryEntry;
				if (rand.nextInt(8) == 0)
				{
					List<ConfiguredFeature<?, ?>> list = world.getBiome(blockpos1).value().getGenerationSettings().getBoneMealFeatures();
					if (list.isEmpty()) {continue;}
					registryEntry = ((RandomPatchConfiguration)list.get(0).config()).feature();
				}
				else
				{
					if (!optional.isPresent()) {continue;}
					registryEntry = optional.get();
				}
				((PlacedFeature)registryEntry.value()).place(world, world.getChunkSource().getGenerator(), rand, blockpos1);
			}
		}
	}

	protected abstract Optional<Holder.Reference<PlacedFeature>> getBonemealFeature(ServerLevel world);
}