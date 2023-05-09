package fr.factionbedrock.aerialhell.World.Tree;

import java.util.Random;

import javax.annotation.Nullable;

import fr.factionbedrock.aerialhell.Registry.AerialHellConfiguredFeatures;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.world.level.block.grower.AbstractMegaTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class ShadowPine extends AbstractMegaTreeGrower
{
	@Nullable @Override
	protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(Random randomIn, boolean largeHive)
	{
		return TreeFeatures.ACACIA;//AerialHellConfiguredFeatures.SHADOW_PINE.getHolder().get(); TODO
	}

	@Nullable @Override
	protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredMegaFeature(Random randomIn)
	{
		return TreeFeatures.ACACIA;//AerialHellConfiguredFeatures.MEGA_SHADOW_PINE.getHolder().get(); TODO
	}
}