package fr.factionbedrock.aerialhell.World.Features;

import com.mojang.serialization.Codec;
import fr.factionbedrock.aerialhell.Util.BlockHelper;
import net.minecraft.world.gen.feature.RandomPatchFeature;
import net.minecraft.world.gen.feature.RandomPatchFeatureConfig;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class RandomPatchInDarkAreaFeature extends RandomPatchFeature
{
    public RandomPatchInDarkAreaFeature(Codec<RandomPatchFeatureConfig> config) {super(config);}

    @Override public boolean generate(FeatureContext<RandomPatchFeatureConfig> context)
    {
        if (!BlockHelper.hasAnySolidSurfaceAbove(context.getWorld(), context.getOrigin(), 3)) {return false;}
        else {return super.generate(context);}
    }
}
