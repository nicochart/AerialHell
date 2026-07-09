package fr.factionbedrock.aerialhell.World.Features;

import com.mojang.serialization.Codec;
import fr.factionbedrock.aerialhell.Util.BlockHelper;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.RandomPatchFeature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;

public class RandomPatchInDarkAreaFeature extends RandomPatchFeature
{
    public RandomPatchInDarkAreaFeature(Codec<RandomPatchConfiguration> config) {super(config);}

    @Override public boolean place(FeaturePlaceContext<RandomPatchConfiguration> context)
    {
        if (!BlockHelper.hasAnySolidSurfaceAbove(context.level(), context.origin(), 3)) {return false;}
        else {return super.place(context);}
    }
}
