package fr.factionbedrock.aerialhell.World.Tree;

import java.util.Random;

import javax.annotation.Nullable;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureSpread;
import net.minecraft.world.gen.feature.TwoLayerFeature;
import net.minecraft.world.gen.foliageplacer.SpruceFoliagePlacer;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;

public class CopperPine extends Tree
{	
	@Nullable
	@Override
	protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getTreeFeature(Random randomIn, boolean largeHive)
	{
		return Feature.TREE.withConfiguration
		((
				new BaseTreeFeatureConfig.Builder
				(
					new SimpleBlockStateProvider(AerialHellBlocksAndItems.COPPER_PINE_LOG.get().getDefaultState()),
		            new SimpleBlockStateProvider(AerialHellBlocksAndItems.COPPER_PINE_LEAVES.get().getDefaultState()),
		            new SpruceFoliagePlacer(FeatureSpread.func_242253_a(3, 1), FeatureSpread.func_242253_a(0, 2), FeatureSpread.func_242253_a(1, 1)), //rayon,décalage,hauteur		func_242252_a()=fixed()   2,1 0,2 1,1 sapin basique
		            new StraightTrunkPlacer(5, 2, 1), //hauteur de base, randomizer1, randomizer2
		            new TwoLayerFeature(3, 0, 2)
		)).setIgnoreVines().build());
	}
}
