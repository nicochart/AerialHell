package fr.factionbedrock.aerialhell.World.Features;

import com.mojang.serialization.Codec;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellConfiguredFeatures;
import fr.factionbedrock.aerialhell.Util.BlockHelper;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.RandomPatchFeature;
import net.minecraft.world.gen.feature.RandomPatchFeatureConfig;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.List;

public class RandomPatchInDarkAreaFeature extends RandomPatchFeature implements DungeonSensitiveFeatureCheck
{
    public RandomPatchInDarkAreaFeature(Codec<RandomPatchFeatureConfig> config) {super(config);}

    @Override public List<RegistryKey<ConfiguredFeature<?, ?>>> getAssociatedConfiguredFeatures() {return AerialHellConfiguredFeatures.Lists.RANDOM_PATCH_IN_DARK_AREA_LIST;}

    @Override public boolean generate(FeatureContext<RandomPatchFeatureConfig> context)
    {
        if (!BlockHelper.hasAnySolidSurfaceAbove(context.getWorld(), context.getOrigin(), 3) || !this.isDungeonSensitiveValid(context)) {return false;}
        else {return super.generate(context);}
    }
}
