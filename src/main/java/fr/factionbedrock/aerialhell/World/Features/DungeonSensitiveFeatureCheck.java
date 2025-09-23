package fr.factionbedrock.aerialhell.World.Features;

import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Util.FeatureHelper;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.List;

public interface DungeonSensitiveFeatureCheck
{
    default boolean isDungeonSensitiveValid(FeatureContext<? extends FeatureConfig> context)
    {
        Registry<ConfiguredFeature<?, ?>> registry = context.getWorld().getRegistryManager().getOrThrow(RegistryKeys.CONFIGURED_FEATURE);

        for (RegistryKey<ConfiguredFeature<?, ?>> key : this.getAssociatedConfiguredFeatures())
        {
            RegistryEntry.Reference<ConfiguredFeature<?, ?>> holder = registry.getOrThrow(key);
            if (holder.isIn(AerialHellTags.ConfiguredFeatures.CANT_PLACE_IN_DUNGEONS))
            {
                if (FeatureHelper.isFeatureGeneratingNextToDungeon(context)) {return false;}
                else {return true;} //if the feature is not generating next to a dungeon, it can generate, whatever is the configured feature
            }
        }
        return true;
    }

    List<RegistryKey<ConfiguredFeature<?, ?>>> getAssociatedConfiguredFeatures();
}
