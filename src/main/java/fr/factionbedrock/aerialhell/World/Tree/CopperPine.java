package fr.factionbedrock.aerialhell.World.Tree;

import java.util.Random;

import javax.annotation.Nullable;

import fr.factionbedrock.aerialhell.Registry.AerialHellFeatures;
import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;

public class CopperPine extends Tree
{	
	@Nullable
	@Override
	protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getTreeFeature(Random randomIn, boolean largeHive)
	{
		return Feature.TREE.withConfiguration(AerialHellFeatures.Configs.COPPER_PINE_CONFIG);
	}
}
