package fr.factionbedrock.aerialhell.World.Tree;

import java.util.Random;

import javax.annotation.Nullable;

import fr.factionbedrock.aerialhell.Registry.AerialHellFeatures;
import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;

public class AerialTree extends Tree
{
	/* 
	This class is useful only to generate a tree from a sapling.
	The tree generated when a new chunk is loaded is in the class aerialhell/Setup/Registration
	*/
	
	@Nullable
	@Override
	protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getTreeFeature(Random randomIn, boolean largeHive)
	{
		return Feature.TREE.withConfiguration(AerialHellFeatures.Configs.AERIAL_TREE_BASIC_CONFIG);
	}
}
