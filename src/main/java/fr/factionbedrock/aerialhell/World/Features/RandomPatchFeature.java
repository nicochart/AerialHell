package fr.factionbedrock.aerialhell.World.Features;

import com.mojang.serialization.Codec;
import fr.factionbedrock.aerialhell.World.Features.Config.RandomPatchConfiguration;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class RandomPatchFeature extends Feature<RandomPatchConfiguration>
{
    public RandomPatchFeature(Codec<RandomPatchConfiguration> codec) {super(codec);}

    public boolean place(FeaturePlaceContext<RandomPatchConfiguration> context)
    {
        RandomPatchConfiguration randompatchconfiguration = context.config();
        RandomSource randomsource = context.random();
        BlockPos blockpos = context.origin();
        WorldGenLevel worldgenlevel = context.level();
        int i = 0;
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
        int j = randompatchconfiguration.xzSpread() + 1;
        int k = randompatchconfiguration.ySpread() + 1;

        for(int tryi = 0; tryi < randompatchconfiguration.tries(); ++tryi)
        {
            blockpos$mutableblockpos.setWithOffset(blockpos, randomsource.nextInt(j) - randomsource.nextInt(j), randomsource.nextInt(k) - randomsource.nextInt(k), randomsource.nextInt(j) - randomsource.nextInt(j));
            if (((PlacedFeature)randompatchconfiguration.feature().value()).place(worldgenlevel, context.chunkGenerator(), randomsource, blockpos$mutableblockpos)) {++i;}
        }
        return i > 0;
    }
}
