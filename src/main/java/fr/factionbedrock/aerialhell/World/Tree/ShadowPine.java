package fr.factionbedrock.aerialhell.World.Tree;

import javax.annotation.Nullable;

import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellConfiguredFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractMegaTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class ShadowPine extends AbstractMegaTreeGrower
{
	@Nullable @Override
	protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource randomIn, boolean largeHive)
	{
		return AerialHellConfiguredFeatures.SHADOW_PINE;
	}

	@Nullable @Override
	protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredMegaFeature(RandomSource randomIn)
	{
		return AerialHellConfiguredFeatures.MEGA_SHADOW_PINE;
	}
}