package fr.factionbedrock.aerialhell.World.Tree;

import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableList;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import net.minecraft.block.trees.BigTree;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureSpread;
import net.minecraft.world.gen.feature.TwoLayerFeature;
import net.minecraft.world.gen.foliageplacer.MegaPineFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.SpruceFoliagePlacer;
import net.minecraft.world.gen.treedecorator.AlterGroundTreeDecorator;
import net.minecraft.world.gen.trunkplacer.GiantTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;

public class ShadowPine extends BigTree
{
	/* 
	This class is useful only to generate a tree from a sapling.
	The tree generated when a new chunk is loaded is in the class aerialhell/Setup/Registration
	*/
	
	@Nullable
	@Override	
	protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getTreeFeature(Random randomIn, boolean largeHive)
	{
		return Feature.TREE.withConfiguration((new BaseTreeFeatureConfig.Builder
		(
		    new SimpleBlockStateProvider(AerialHellBlocksAndItems.SHADOW_PINE_LOG.get().getDefaultState()),
            new SimpleBlockStateProvider(AerialHellBlocksAndItems.SHADOW_PINE_LEAVES.get().getDefaultState()),
            new SpruceFoliagePlacer(FeatureSpread.func_242253_a(2, 1), FeatureSpread.func_242253_a(0, 2), FeatureSpread.func_242253_a(1, 1)),
            new StraightTrunkPlacer(6, 2, 1),
            new TwoLayerFeature(3, 0, 2)
	    )).setIgnoreVines().build());
	}
	
	@Nullable
	@Override
	protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getHugeTreeFeature(Random rand)
	{
		return Feature.TREE.withConfiguration((new BaseTreeFeatureConfig.Builder
		(
	    	new SimpleBlockStateProvider(AerialHellBlocksAndItems.SHADOW_PINE_LOG.get().getDefaultState()),
	    	new SimpleBlockStateProvider(AerialHellBlocksAndItems.SHADOW_PINE_LEAVES.get().getDefaultState()),
	    	new MegaPineFoliagePlacer(FeatureSpread.func_242252_a(0), FeatureSpread.func_242252_a(0), FeatureSpread.func_242253_a(3, 4)),
	    	new GiantTrunkPlacer(13, 2, 14),
	    	new TwoLayerFeature(1, 1, 2)
	    )).setDecorators(ImmutableList.of(new AlterGroundTreeDecorator(new SimpleBlockStateProvider(AerialHellBlocksAndItems.SHADOW_GRASS_BLOCK.get().getDefaultState())))).build());
	}
}
