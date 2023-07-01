package fr.factionbedrock.aerialhell.World.Features;

import com.mojang.serialization.Codec;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.TwistingVinesConfig;

import java.util.Random;

//copy of TwistingVinesFeature class ; editing placeWeepingVinesColumn and isInvalidPlacementLocation to adapt to Aerial Hell
public class LazuliRootsFeature extends Feature<TwistingVinesConfig>
{
    public LazuliRootsFeature(Codec<TwistingVinesConfig> codec) {super(codec);}

    public boolean place(FeaturePlaceContext<TwistingVinesConfig> context)
    {
        WorldGenLevel worldgenlevel = context.level(); BlockPos blockpos = context.origin();
        if (isInvalidPlacementLocation(worldgenlevel, blockpos)) {return false;}
        else
        {
            Random random = context.random();
            TwistingVinesConfig twistingvinesconfig = context.config();
            int i = twistingvinesconfig.spreadWidth();
            int j = twistingvinesconfig.spreadHeight();
            int k = twistingvinesconfig.maxHeight();
            BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

            for(int l = 0; l < i * i; ++l)
            {
                blockpos$mutableblockpos.set(blockpos).move(Mth.nextInt(random, -i, i), Mth.nextInt(random, -j, j), Mth.nextInt(random, -i, i));
                if (findFirstAirBlockAboveGround(worldgenlevel, blockpos$mutableblockpos) && !isInvalidPlacementLocation(worldgenlevel, blockpos$mutableblockpos))
                {
                    int i1 = Mth.nextInt(random, 1, k);
                    if (random.nextInt(6) == 0) {i1 *= 2;}
                    if (random.nextInt(5) == 0) {i1 = 1;}
                    placeWeepingVinesColumn(worldgenlevel, random, blockpos$mutableblockpos, i1, 17, 25);
                }
            }
            return true;
        }
    }

    private static boolean findFirstAirBlockAboveGround(LevelAccessor level, BlockPos.MutableBlockPos mutablePos)
    {
        do {mutablePos.move(0, -1, 0); if (level.isOutsideBuildHeight(mutablePos)) {return false;}} while(level.getBlockState(mutablePos).isAir());
        mutablePos.move(0, 1, 0);
        return true;
    }

    public static void placeWeepingVinesColumn(LevelAccessor level, Random rand, BlockPos.MutableBlockPos mutablePos, int height, int minAge, int maxAge)
    {
        for(int i = 1; i <= height; ++i)
        {
            if (level.isEmptyBlock(mutablePos))
            {
                if (i == height || !level.isEmptyBlock(mutablePos.above())) {level.setBlock(mutablePos, AerialHellBlocksAndItems.LAZULI_ROOTS.get().defaultBlockState().setValue(GrowingPlantHeadBlock.AGE, Integer.valueOf(Mth.nextInt(rand, minAge, maxAge))), 2); break;}
                level.setBlock(mutablePos, AerialHellBlocksAndItems.LAZULI_ROOTS_PLANT.get().defaultBlockState(), 2);
            }
            mutablePos.move(Direction.UP);
        }

    }

    private static boolean isInvalidPlacementLocation(LevelAccessor level, BlockPos pos)
    {
        if (!level.isEmptyBlock(pos)) {return true;}
        else
        {
            BlockState blockstate = level.getBlockState(pos.below());
            return !blockstate.is(AerialHellTags.Blocks.STELLAR_DIRT) && !blockstate.is(Blocks.NETHERRACK) && !blockstate.is(Blocks.WARPED_NYLIUM) && !blockstate.is(Blocks.WARPED_WART_BLOCK);
        }
    }
}
