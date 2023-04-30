package fr.factionbedrock.aerialhell.World.Tree;

import java.util.Random;

import javax.annotation.Nullable;

import fr.factionbedrock.aerialhell.Registry.AerialHellConfiguredFeatures;
import net.minecraft.block.trees.BigTree;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;

public class PurpleShadowPine extends BigTree
{
	/* 
	This class is useful only to generate a tree from a sapling.
	The tree generated when a new chunk is loaded is in the class aerialhell/Setup/Registration
	*/
	
	@Nullable
	@Override	
	protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getTreeFeature(Random randomIn, boolean largeHive)
	{
		return Feature.TREE.withConfiguration(AerialHellConfiguredFeatures.Configs.PURPLE_SHADOW_PINE_CONFIG);
	}
	
	@Nullable
	@Override
	protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getHugeTreeFeature(Random rand)
	{
		return Feature.TREE.withConfiguration(AerialHellConfiguredFeatures.Configs.MEGA_PURPLE_SHADOW_PINE_CONFIG);
	}
}
