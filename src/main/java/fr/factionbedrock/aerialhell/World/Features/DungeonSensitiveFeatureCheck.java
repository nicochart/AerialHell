package fr.factionbedrock.aerialhell.World.Features;

import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Util.FeatureHelper;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

import java.util.List;

public interface DungeonSensitiveFeatureCheck
{
    default boolean isDungeonSensitiveValid(FeaturePlaceContext<? extends FeatureConfiguration> context)
    {
        Registry<ConfiguredFeature<?, ?>> registry = context.level().registryAccess().lookupOrThrow(Registries.CONFIGURED_FEATURE);

        for (ResourceKey<ConfiguredFeature<?, ?>> key : this.getAssociatedConfiguredFeatures())
        {
            Holder<ConfiguredFeature<?, ?>> holder = registry.getOrThrow(key);
            if (holder.is(AerialHellTags.ConfiguredFeatures.CANT_PLACE_IN_DUNGEONS))
            {
                if (FeatureHelper.isFeatureGeneratingNextToDungeon(context)) {return false;}
                else {return true;} //if the feature is not generating next to a dungeon, it can generate, whatever is the configured feature
            }
        }
        return true;
    }

    List<ResourceKey<ConfiguredFeature<?, ?>>> getAssociatedConfiguredFeatures();
}
