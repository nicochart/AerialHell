package fr.factionbedrock.aerialhell.World.Tree;

import fr.factionbedrock.aerialhell.Registry.AerialHellConfiguredFeatures;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import javax.annotation.Nullable;
import java.util.Random;

public class GoldenBeechTree extends AbstractTreeGrower
{
	@Nullable @Override
	protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(Random randomIn, boolean largeHive)
	{
		return AerialHellConfiguredFeatures.GOLDEN_BEECH.getHolder().get();
	}
}
