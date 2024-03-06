package fr.factionbedrock.aerialhell.World.Tree;

import javax.annotation.Nullable;

import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellConfiguredFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class AerialTree extends AbstractTreeGrower
{
	@Nullable @Override
	protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource randomIn, boolean largeHive)
	{
		return AerialHellConfiguredFeatures.AERIAL_TREE;
	}
}
