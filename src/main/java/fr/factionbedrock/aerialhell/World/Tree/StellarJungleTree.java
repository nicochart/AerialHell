package fr.factionbedrock.aerialhell.World.Tree;

import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellConfiguredFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractMegaTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import javax.annotation.Nullable;

public class StellarJungleTree extends AbstractMegaTreeGrower
{
	@Nullable @Override
	protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource randomIn, boolean largeHive)
	{
		return AerialHellConfiguredFeatures.STELLAR_JUNGLE_TREE;
	}

	@Nullable @Override
	protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredMegaFeature(RandomSource randomIn)
	{
		return AerialHellConfiguredFeatures.MEGA_STELLAR_JUNGLE_TREE;
	}
}