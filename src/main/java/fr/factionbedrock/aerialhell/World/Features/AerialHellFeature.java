package fr.factionbedrock.aerialhell.World.Features;

import fr.factionbedrock.aerialhell.World.Features.Util.FeaturePlaceContext;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;

//Since 26.3, a Feature is the configured feature itself (it holds its config).
//This base class keeps the old "Feature<Config> + place(context)" structure for Aerial Hell features.
public abstract class AerialHellFeature<FC> implements Feature
{
    private final FC config;

    protected AerialHellFeature(FC config) {this.config = config;}

    public FC config() {return this.config;}

    @Override public boolean place(WorldGenLevel level, ChunkGenerator chunkGenerator, RandomSource random, BlockPos origin)
    {
        return this.place(new FeaturePlaceContext<>(level, chunkGenerator, random, origin, this.config));
    }

    public abstract boolean place(FeaturePlaceContext<FC> context);
}
