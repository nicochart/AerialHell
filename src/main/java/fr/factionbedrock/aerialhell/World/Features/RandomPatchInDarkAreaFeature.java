package fr.factionbedrock.aerialhell.World.Features;

import com.mojang.serialization.Codec;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellConfiguredFeatures;
import fr.factionbedrock.aerialhell.Util.BlockHelper;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.RandomPatchFeature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;

import java.util.List;

public class RandomPatchInDarkAreaFeature extends RandomPatchFeature implements DungeonSensitiveFeatureCheck
{
    public RandomPatchInDarkAreaFeature(Codec<RandomPatchConfiguration> config) {super(config);}

    @Override public List<ResourceKey<ConfiguredFeature<?, ?>>> getAssociatedConfiguredFeatures() {return AerialHellConfiguredFeatures.Lists.RANDOM_PATCH_IN_DARK_AREA_LIST;}

    @Override public boolean place(FeaturePlaceContext<RandomPatchConfiguration> context)
    {
        if (!BlockHelper.hasAnySolidSurfaceAbove(context.level(), context.origin(), 3) || !this.isDungeonSensitiveValid(context)) {return false;}
        else {return super.place(context);}
    }
}
