package fr.factionbedrock.aerialhell.World.Features;

import com.mojang.serialization.Codec;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellConfiguredFeatures;
import fr.factionbedrock.aerialhell.Util.BlockHelper;
import fr.factionbedrock.aerialhell.Util.FeatureHelper;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.RandomPatchFeature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;

import java.util.List;

public class RandomPatchInDarkAreaFeature extends RandomPatchFeature
{
    public RandomPatchInDarkAreaFeature(Codec<RandomPatchConfiguration> config) {super(config);}

    protected List<ResourceKey<ConfiguredFeature<?, ?>>> getAssociatedConfiguredFeatures() {return AerialHellConfiguredFeatures.Lists.RANDOM_PATCH_IN_DARK_AREA_LIST;}

    @Override public boolean place(FeaturePlaceContext<RandomPatchConfiguration> context)
    {
        Registry<ConfiguredFeature<?, ?>> registry = context.level().registryAccess().lookupOrThrow(Registries.CONFIGURED_FEATURE);

        for (ResourceKey<ConfiguredFeature<?, ?>> key : this.getAssociatedConfiguredFeatures())
        {
            Holder<ConfiguredFeature<?, ?>> holder = registry.getOrThrow(key);
            if (holder.is(AerialHellTags.ConfiguredFeatures.CANT_PLACE_IN_DUNGEONS) && FeatureHelper.isFeatureGeneratingNextToDungeon(context))
            {
                return false;
            }
        }

        if (!BlockHelper.hasAnySolidSurfaceAbove(context.level(), context.origin(), 3)) {return false;}
        else {return super.place(context);}
    }
}
