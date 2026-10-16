package fr.factionbedrock.aerialhell.World.Features;

import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Util.FeatureHelper;
import java.util.List;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.Feature;

public interface DungeonSensitiveFeatureCheck
{
    default boolean isDungeonSensitiveValid(WorldGenLevel level, BlockPos origin)
    {
        Registry<Feature> registry = level.registryAccess().lookupOrThrow(Registries.FEATURE);

        for (ResourceKey<Feature> key : this.getAssociatedConfiguredFeatures())
        {
            Holder<Feature> holder = registry.getOrThrow(key);
            if (holder.is(AerialHellTags.ConfiguredFeatures.CANT_PLACE_IN_DUNGEONS))
            {
                if (FeatureHelper.isFeatureGeneratingNextToDungeon(level, origin)) {return false;}
                else {return true;} //if the feature is not generating next to a dungeon, it can generate, whatever is the configured feature
            }
        }
        return true;
    }

    List<ResourceKey<Feature>> getAssociatedConfiguredFeatures();
}
