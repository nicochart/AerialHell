package fr.factionbedrock.aerialhell.World.Tree;

import java.util.Random;

import javax.annotation.Nullable;

import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellConfiguredFeatures;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.grower.AbstractMegaTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class PurpleShadowPine extends AbstractMegaTreeGrower
{
	@Nullable @Override
	protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(Random randomIn, boolean largeHive)
	{
		return AerialHellConfiguredFeatures.PURPLE_SHADOW_PINE.getHolder().get();
	}

	@Nullable @Override
	protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredMegaFeature(Random randomIn)
	{
		return AerialHellConfiguredFeatures.MEGA_PURPLE_SHADOW_PINE.getHolder().get();
	}
}
