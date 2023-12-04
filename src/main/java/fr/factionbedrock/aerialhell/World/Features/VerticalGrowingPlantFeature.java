package fr.factionbedrock.aerialhell.World.Features;

import com.mojang.serialization.Codec;
import fr.factionbedrock.aerialhell.Block.Plants.VerticalGrowingPlantBlock;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.World.Features.Config.VerticalGrowingPlantConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

import java.util.function.Supplier;

//copy of TwistingVinesFeature class with some customizations and a config with one more parameter; editing placeWeepingVinesColumn and isInvalidPlacementLocation to adapt to Aerial Hell
public class VerticalGrowingPlantFeature extends Feature<VerticalGrowingPlantConfig>
{
    private Supplier<VerticalGrowingPlantBlock> block;
    public VerticalGrowingPlantFeature(Codec<VerticalGrowingPlantConfig> codec, Supplier<VerticalGrowingPlantBlock> plantBlock) {super(codec); this.block = plantBlock;}

    public boolean place(FeaturePlaceContext<VerticalGrowingPlantConfig> context)
    {
        WorldGenLevel worldgenlevel = context.level(); BlockPos blockpos = context.origin();
        if (isInvalidPlacementLocation(worldgenlevel, blockpos)) {return false;}
        else
        {
            RandomSource random = context.random();
            VerticalGrowingPlantConfig config = context.config();
            int spreadXZ = config.spreadWidth();
            int spreadY = config.spreadHeight();
            int minHeight = config.minHeight();
            int maxHeight = config.maxHeight();
            int minTries = config.minTries();
            int maxTries = config.maxTries();
            int tries = (minTries == maxTries) ? minTries : Mth.nextInt(random, minTries, maxTries);
            BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

            for(int l = 0; l <= tries; ++l)
            {
                blockpos$mutableblockpos.set(blockpos).move(Mth.nextInt(random, -spreadXZ, spreadXZ), Mth.nextInt(random, -spreadY, spreadY), Mth.nextInt(random, -spreadXZ, spreadXZ));
                if (findFirstAirBlockAboveGround(worldgenlevel, blockpos$mutableblockpos) && !isInvalidPlacementLocation(worldgenlevel, blockpos$mutableblockpos))
                {
                    int plantHeight = (minHeight == maxHeight) ? minHeight : Mth.nextInt(random, minHeight, maxHeight);
                    placeVerticalGrowingPlantColumn(worldgenlevel, random, blockpos$mutableblockpos, plantHeight, 9, 14, block.get());
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

    public static void placeVerticalGrowingPlantColumn(LevelAccessor level, RandomSource rand, BlockPos.MutableBlockPos mutablePos, int height, int minAge, int maxAge, VerticalGrowingPlantBlock plantBlock)
    {
        for(int i = 1; i <= height; ++i)
        {
            if (level.isEmptyBlock(mutablePos))
            {
                if (i == height || !level.isEmptyBlock(mutablePos.above())) {level.setBlock(mutablePos, plantBlock.defaultBlockState().setValue(VerticalGrowingPlantBlock.AGE, Mth.nextInt(rand, minAge, maxAge)).setValue(VerticalGrowingPlantBlock.TOP, true), 2); break;}
                level.setBlock(mutablePos, plantBlock.defaultBlockState().setValue(VerticalGrowingPlantBlock.TOP, false), 2);
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
            return !blockstate.is(AerialHellTags.Blocks.STELLAR_DIRT);
        }
    }
}
