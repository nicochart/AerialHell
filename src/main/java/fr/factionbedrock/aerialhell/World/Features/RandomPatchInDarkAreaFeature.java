package fr.factionbedrock.aerialhell.World.Features;

import com.mojang.serialization.MapCodec;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellConfiguredFeatures;
import fr.factionbedrock.aerialhell.Util.BlockHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.List;

public class RandomPatchInDarkAreaFeature extends RandomPatchFeature implements DungeonSensitiveFeatureCheck
{
    public static final MapCodec<RandomPatchInDarkAreaFeature> CODEC = makeCodec(RandomPatchInDarkAreaFeature::new);

    public RandomPatchInDarkAreaFeature(int tries, int xzSpread, int ySpread, Holder<PlacedFeature> feature) {super(tries, xzSpread, ySpread, feature);}

    @Override public MapCodec<RandomPatchInDarkAreaFeature> codec() {return CODEC;}

    @Override public List<ResourceKey<Feature>> getAssociatedConfiguredFeatures() {return AerialHellConfiguredFeatures.Lists.RANDOM_PATCH_IN_DARK_AREA_LIST;}

    @Override public boolean place(WorldGenLevel level, ChunkGenerator chunkGenerator, RandomSource random, BlockPos origin)
    {
        if (!BlockHelper.hasAnySolidSurfaceAbove(level, origin, 3) || !this.isDungeonSensitiveValid(level, origin)) {return false;}
        else {return super.place(level, chunkGenerator, random, origin);}
    }
}
