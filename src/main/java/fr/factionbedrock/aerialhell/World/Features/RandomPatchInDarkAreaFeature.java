package fr.factionbedrock.aerialhell.World.Features;

import com.mojang.serialization.MapCodec;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellConfiguredFeatures;
import fr.factionbedrock.aerialhell.Util.BlockHelper;
import fr.factionbedrock.aerialhell.World.Features.Config.RandomPatchConfiguration;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.Feature;
import fr.factionbedrock.aerialhell.World.Features.Util.FeaturePlaceContext;

import java.util.List;

public class RandomPatchInDarkAreaFeature extends RandomPatchFeature implements DungeonSensitiveFeatureCheck
{
    public static final MapCodec<RandomPatchInDarkAreaFeature> CODEC = RandomPatchConfiguration.CODEC.xmap(RandomPatchInDarkAreaFeature::new, RandomPatchInDarkAreaFeature::config);

    public RandomPatchInDarkAreaFeature(RandomPatchConfiguration config) {super(config);}

    @Override public MapCodec<? extends Feature> codec() {return CODEC;}

    @Override public List<ResourceKey<Feature>> getAssociatedConfiguredFeatures() {return AerialHellConfiguredFeatures.Lists.RANDOM_PATCH_IN_DARK_AREA_LIST;}

    @Override public boolean place(FeaturePlaceContext<RandomPatchConfiguration> context)
    {
        if (!BlockHelper.hasAnySolidSurfaceAbove(context.level(), context.origin(), 3) || !this.isDungeonSensitiveValid(context)) {return false;}
        else {return super.place(context);}
    }
}
