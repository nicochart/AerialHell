package fr.factionbedrock.aerialhell.World.Tree;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.foliageplacer.FancyFoliagePlacer;
import net.minecraft.world.gen.trunkplacer.FancyTrunkPlacer;

import javax.annotation.Nullable;
import java.util.OptionalInt;
import java.util.Random;

public class GoldenBeechTree extends Tree
{
	@Nullable
	@Override
	protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getTreeFeature(Random randomIn, boolean largeHive)
	{
		return Feature.TREE.withConfiguration((new BaseTreeFeatureConfig.Builder
		(
				new SimpleBlockStateProvider(AerialHellBlocksAndItems.GOLDEN_BEECH_LOG.get().getDefaultState()),
				new SimpleBlockStateProvider(AerialHellBlocksAndItems.GOLDEN_BEECH_LEAVES.get().getDefaultState()),
				new FancyFoliagePlacer(FeatureSpread.func_242252_a(2), FeatureSpread.func_242252_a(4), 4),
				new FancyTrunkPlacer(3, 11, 0),
				new TwoLayerFeature(0, 0, 0, OptionalInt.of(4))
		)).setIgnoreVines().func_236702_a_(Heightmap.Type.MOTION_BLOCKING).build());
	}
}
