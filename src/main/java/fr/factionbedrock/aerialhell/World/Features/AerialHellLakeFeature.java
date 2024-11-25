package fr.factionbedrock.aerialhell.World.Features;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

//copy of vanilla LakeFeature, but removing some conditions (see below)
//
public class AerialHellLakeFeature extends Feature<AerialHellLakeFeature.Configuration>
{
    private static final BlockState AIR = Blocks.CAVE_AIR.getDefaultState();

    public AerialHellLakeFeature(Codec<AerialHellLakeFeature.Configuration> codec) {super(codec);}

    @Override
    public boolean place(FeaturePlaceContext<AerialHellLakeFeature.Configuration> context)
    {
        BlockPos origin = context.origin(); WorldGenLevel level = context.level(); RandomSource randomsource = context.random(); AerialHellLakeFeature.Configuration config = context.config();

        if (level.getBlockState(origin).isAir() || level.getBlockState(origin.below(3)).isAir() || origin.getY() <= level.getMinBuildHeight() + 4) {return false;}
        else
        {
            origin = origin.below(4);
            boolean[] aboolean = new boolean[2048];
            int i = randomsource.nextInt(4) + 4;

            for (int j = 0; j < i; j++)
            {
                double d0 = randomsource.nextDouble() * 6.0 + 3.0;
                double d1 = randomsource.nextDouble() * 4.0 + 2.0;
                double d2 = randomsource.nextDouble() * 6.0 + 3.0;
                double d3 = randomsource.nextDouble() * (16.0 - d0 - 2.0) + 1.0 + d0 / 2.0;
                double d4 = randomsource.nextDouble() * (8.0 - d1 - 4.0) + 2.0 + d1 / 2.0;
                double d5 = randomsource.nextDouble() * (16.0 - d2 - 2.0) + 1.0 + d2 / 2.0;

                for (int l = 1; l < 15; l++)
                {
                    for (int i1 = 1; i1 < 15; i1++)
                    {
                        for (int j1 = 1; j1 < 7; j1++)
                        {
                            double d6 = ((double)l - d3) / (d0 / 2.0);
                            double d7 = ((double)j1 - d4) / (d1 / 2.0);
                            double d8 = ((double)i1 - d5) / (d2 / 2.0);
                            double d9 = d6 * d6 + d7 * d7 + d8 * d8;
                            if (d9 < 1.0) {aboolean[(l * 16 + i1) * 8 + j1] = true;}
                        }
                    }
                }
            }

            BlockState fluidState = config.fluid().getState(randomsource, origin);
            BlockState barrierState = config.barrier().getState(randomsource, origin);

            for (int k1 = 0; k1 < 16; k1++)
            {
                for (int k = 0; k < 16; k++)
                {
                    for (int l2 = 0; l2 < 8; l2++)
                    {
                        boolean flag = !aboolean[(k1 * 16 + k) * 8 + l2] &&
                        (
                            k1 < 15 && aboolean[((k1 + 1) * 16 + k) * 8 + l2]
                                || k1 > 0 && aboolean[((k1 - 1) * 16 + k) * 8 + l2]
                                || k < 15 && aboolean[(k1 * 16 + k + 1) * 8 + l2]
                                || k > 0 && aboolean[(k1 * 16 + (k - 1)) * 8 + l2]
                                || l2 < 7 && aboolean[(k1 * 16 + k) * 8 + l2 + 1]
                                || l2 > 0 && aboolean[(k1 * 16 + k) * 8 + (l2 - 1)]
                        );
                        if (flag)
                        {
                            //removed conditions
                            //BlockState blockstate3 = level.getBlockState(origin.offset(k1, l2, k));
                            //if (l2 >= 4 && blockstate3.liquid()) {return false;}
                            //if (l2 < 4 && !blockstate3.isSolid() && worldgenlevel.getBlockState(blockpos.offset(k1, l2, k)) != fluidState) {return false;}
                        }
                    }
                }
            }

            for (int l1 = 0; l1 < 16; l1++)
            {
                for (int i2 = 0; i2 < 16; i2++)
                {
                    for (int i3 = 0; i3 < 8; i3++)
                    {
                        if (aboolean[(l1 * 16 + i2) * 8 + i3])
                        {
                            BlockPos liquidPlacementPos = origin.offset(l1, i3, i2);
                            if (this.canReplaceBlock(level.getBlockState(liquidPlacementPos)))
                            {
                                boolean shouldBecomeAir = i3 >= 4;
                                level.setBlockState(liquidPlacementPos, shouldBecomeAir ? AIR : fluidState, 2);
                                if (shouldBecomeAir)
                                {
                                    level.scheduleTick(liquidPlacementPos, AIR.getBlock(), 0);
                                    this.markAboveForPostProcessing(level, liquidPlacementPos);
                                }
                            }
                        }
                    }
                }
            }

            //edited barrier placement
            if (!barrierState.isAir())
            {
                for (int dx = 0; dx < 16; dx++)
                {
                    for (int dz = 0; dz < 16; dz++)
                    {
                        for (int dy = 0; dy < 8; dy++)
                        {
                            BlockPos scannedPos = origin.offset(dx, dy, dz);
                            if (level.getBlockState(scannedPos).is(fluidState.getBlock()))
                            {
                                placeBarrierIfNeeded(level, scannedPos.offset(-1, 0, 0), barrierState);
                                placeBarrierIfNeeded(level, scannedPos.offset(1, 0, 0), barrierState);
                                placeBarrierIfNeeded(level, scannedPos.offset(0, 0, -1), barrierState);
                                placeBarrierIfNeeded(level, scannedPos.offset(0, 0, 1), barrierState);
                                placeBarrierIfNeeded(level, scannedPos.offset(0, -1, 0), barrierState);
                            }
                        }
                    }
                }
            }
            return true;

            //removed water freezing (worldgenlevel.getBiome(..) is crashing the game)
        }
    }

    private void placeBarrierIfNeeded(WorldGenLevel level, BlockPos pos, BlockState barrierState)
    {
        if (level.getBlockState(pos).isAir()) {level.setBlockState(pos, barrierState, 2);}
    }

    private boolean canReplaceBlock(BlockState state) {return !state.isIn(BlockTags.FEATURES_CANNOT_REPLACE);}

    public record Configuration(BlockStateProvider fluid, BlockStateProvider barrier) implements FeatureConfiguration
    {
        public static final Codec<AerialHellLakeFeature.Configuration> CODEC = RecordCodecBuilder.create
        (
            config -> config.group
                (
                    BlockStateProvider.CODEC.fieldOf("fluid").forGetter(AerialHellLakeFeature.Configuration::fluid),
                    BlockStateProvider.CODEC.fieldOf("barrier").forGetter(AerialHellLakeFeature.Configuration::barrier)
                )
                .apply(config, AerialHellLakeFeature.Configuration::new)
        );
    }
}
