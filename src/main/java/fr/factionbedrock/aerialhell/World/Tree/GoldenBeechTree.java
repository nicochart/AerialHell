package fr.factionbedrock.aerialhell.World.Tree;

import fr.factionbedrock.aerialhell.Registry.AerialHellFeatures;
import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.*;

import javax.annotation.Nullable;
import java.util.Random;

public class GoldenBeechTree extends Tree
{
	@Nullable
	@Override
	protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getTreeFeature(Random randomIn, boolean largeHive)
	{
		return Feature.TREE.withConfiguration(AerialHellFeatures.Configs.GOLDEN_BEECH_CONFIG);
	}
}
