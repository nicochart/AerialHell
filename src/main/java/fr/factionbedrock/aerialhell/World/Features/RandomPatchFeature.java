package fr.factionbedrock.aerialhell.World.Features;

import com.mojang.datafixers.util.Function4;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class RandomPatchFeature implements Feature
{
    public static final MapCodec<RandomPatchFeature> CODEC = makeCodec(RandomPatchFeature::new);
    protected final int tries;
    protected final int xzSpread;
    protected final int ySpread;
    protected final Holder<PlacedFeature> feature;

    protected static <T extends RandomPatchFeature> MapCodec<T> makeCodec(Function4<Integer, Integer, Integer, Holder<PlacedFeature>, T> constructor)
    {
        return RecordCodecBuilder.mapCodec(
            i -> i.group(
                    ExtraCodecs.POSITIVE_INT.fieldOf("tries").orElse(128).forGetter(f -> f.tries),
                    ExtraCodecs.NON_NEGATIVE_INT.fieldOf("xz_spread").orElse(7).forGetter(f -> f.xzSpread),
                    ExtraCodecs.NON_NEGATIVE_INT.fieldOf("y_spread").orElse(3).forGetter(f -> f.ySpread),
                    PlacedFeature.CODEC.fieldOf("feature").forGetter(f -> f.feature)
                )
                .apply(i, constructor)
        );
    }

    public RandomPatchFeature(int tries, int xzSpread, int ySpread, Holder<PlacedFeature> feature)
    {
        this.tries = tries;
        this.xzSpread = xzSpread;
        this.ySpread = ySpread;
        this.feature = feature;
    }

    @Override public MapCodec<? extends RandomPatchFeature> codec() {return CODEC;}

    @Override public boolean place(WorldGenLevel level, ChunkGenerator chunkGenerator, RandomSource random, BlockPos origin)
    {
        int placed = 0;
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
        int xzRange = this.xzSpread + 1;
        int yRange = this.ySpread + 1;
        for (int tryi = 0; tryi < this.tries; ++tryi)
        {
            pos.setWithOffset(origin, random.nextInt(xzRange) - random.nextInt(xzRange), random.nextInt(yRange) - random.nextInt(yRange), random.nextInt(xzRange) - random.nextInt(xzRange));
            if (this.feature.value().place(level, chunkGenerator, random, pos)) {++placed;}
        }
        return placed > 0;
    }
}
