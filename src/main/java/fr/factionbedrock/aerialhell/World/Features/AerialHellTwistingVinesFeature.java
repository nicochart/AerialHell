package fr.factionbedrock.aerialhell.World.Features;

import com.mojang.serialization.Codec;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Util.BlockHelper;
import fr.factionbedrock.aerialhell.World.Features.Config.AerialHellTwistingVinesConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

import java.util.function.Supplier;

//copy of TwistingVinesFeature class ; editing placeWeepingVinesColumn and isInvalidPlacementLocation to adapt to Aerial Hell
public class AerialHellTwistingVinesFeature extends Feature<AerialHellTwistingVinesConfig>
{
    private Supplier<Block> headBlock, bodyBlock;

    public AerialHellTwistingVinesFeature(Codec<AerialHellTwistingVinesConfig> codec, Supplier<Block> headBlock, Supplier<Block> bodyBlock) {super(codec); this.headBlock = headBlock; this.bodyBlock = bodyBlock;}

    public boolean place(FeaturePlaceContext<AerialHellTwistingVinesConfig> context)
    {
        boolean needsRoof =  context.config().needsRoof().equals("true");
        WorldGenLevel worldgenlevel = context.level(); BlockPos blockpos = context.origin();
        if (isInvalidPlacementLocation(worldgenlevel, blockpos, needsRoof)) {return false;}
        else
        {
            RandomSource random = context.random();
            AerialHellTwistingVinesConfig twistingvinesconfig = context.config();
            int i = twistingvinesconfig.spreadWidth();
            int j = twistingvinesconfig.spreadHeight();
            int k = twistingvinesconfig.maxHeight();
            BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

            for(int l = 0; l < i * i; ++l)
            {
                blockpos$mutableblockpos.set(blockpos).move(Mth.nextInt(random, -i, i), Mth.nextInt(random, -j, j), Mth.nextInt(random, -i, i));
                if (findFirstAirBlockAboveGround(worldgenlevel, blockpos$mutableblockpos) && !isInvalidPlacementLocation(worldgenlevel, blockpos$mutableblockpos, false))
                {
                    int i1 = Mth.nextInt(random, 1, k);
                    if (random.nextInt(6) == 0) {i1 *= 2;}
                    if (random.nextInt(5) == 0) {i1 = 1;}
                    placeWeepingVinesColumn(worldgenlevel, random, blockpos$mutableblockpos, i1, 17, 25, this.headBlock.get(), this.bodyBlock.get());
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

    public static void placeWeepingVinesColumn(LevelAccessor level, RandomSource rand, BlockPos.MutableBlockPos mutablePos, int height, int minAge, int maxAge, Block headBlock, Block bodyBlock)
    {
        for(int i = 1; i <= height; ++i)
        {
            if (level.isEmptyBlock(mutablePos))
            {
                if (i == height || !level.isEmptyBlock(mutablePos.above())) {level.setBlock(mutablePos, headBlock.defaultBlockState().setValue(GrowingPlantHeadBlock.AGE, Integer.valueOf(Mth.nextInt(rand, minAge, maxAge))), 2); break;}
                level.setBlock(mutablePos, bodyBlock.defaultBlockState(), 2);
            }
            mutablePos.move(Direction.UP);
        }

    }

    private static boolean isInvalidPlacementLocation(LevelAccessor level, BlockPos pos, boolean needsRoof)
    {
        if (!level.isEmptyBlock(pos)) {return true;}
        else
        {
            if (needsRoof && !BlockHelper.hasAnySolidSurfaceAbove(level, pos, 3)) {return true;}
            BlockState blockstate = level.getBlockState(pos.below());
            return !blockstate.is(AerialHellTags.Blocks.STELLAR_DIRT) && !blockstate.is(AerialHellBlocks.SLIPPERY_SAND.get()) && !blockstate.is(Blocks.NETHERRACK) && !blockstate.is(Blocks.WARPED_NYLIUM) && !blockstate.is(Blocks.WARPED_WART_BLOCK);
        }
    }
}
