package fr.factionbedrock.aerialhell.World.Features;

import com.mojang.serialization.Codec;
import fr.factionbedrock.aerialhell.Block.Plants.VerticalGrowingPlantBlock;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.World.Features.Config.VerticalGrowingPlantConfig;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.function.Supplier;

//copy of TwistingVinesFeature class with some customizations and a config with one more parameter; editing placeWeepingVinesColumn and isInvalidPlacementLocation to adapt to Aerial Hell
public class VerticalGrowingPlantFeature extends Feature<VerticalGrowingPlantConfig>
{
    private Supplier<VerticalGrowingPlantBlock> block;
    public VerticalGrowingPlantFeature(Codec<VerticalGrowingPlantConfig> codec, Supplier<VerticalGrowingPlantBlock> plantBlock) {super(codec); this.block = plantBlock;}

    public boolean generate(FeatureContext<VerticalGrowingPlantConfig> context)
    {
        StructureWorldAccess worldgenlevel = context.getWorld(); BlockPos blockpos = context.getOrigin();
        if (isInvalidPlacementLocation(worldgenlevel, blockpos)) {return false;}
        else
        {
            Random random = context.getRandom();
            VerticalGrowingPlantConfig config = context.getConfig();
            int spreadXZ = config.spreadWidth();
            int spreadY = config.spreadHeight();
            int minHeight = config.minHeight();
            int maxHeight = config.maxHeight();
            int minTries = config.minTries();
            int maxTries = config.maxTries();
            int tries = (minTries == maxTries) ? minTries : MathHelper.nextInt(random, minTries, maxTries);
            BlockPos.Mutable blockpos$mutableblockpos = new BlockPos.Mutable();

            for(int l = 0; l <= tries; ++l)
            {
                blockpos$mutableblockpos.set(blockpos).move(MathHelper.nextInt(random, -spreadXZ, spreadXZ), MathHelper.nextInt(random, -spreadY, spreadY), MathHelper.nextInt(random, -spreadXZ, spreadXZ));
                if (findFirstAirBlockAboveGround(worldgenlevel, blockpos$mutableblockpos) && !isInvalidPlacementLocation(worldgenlevel, blockpos$mutableblockpos))
                {
                    int plantHeight = (minHeight == maxHeight) ? minHeight : MathHelper.nextInt(random, minHeight, maxHeight);
                    placeVerticalGrowingPlantColumn(worldgenlevel, random, blockpos$mutableblockpos, plantHeight, 9, 14, block.get());
                }
            }
            return true;
        }
    }

    private static boolean findFirstAirBlockAboveGround(WorldAccess level, BlockPos.Mutable mutablePos)
    {
        do {mutablePos.move(0, -1, 0); if (level.isOutOfHeightLimit(mutablePos)) {return false;}} while(level.getBlockState(mutablePos).isAir());
        mutablePos.move(0, 1, 0);
        return true;
    }

    public static void placeVerticalGrowingPlantColumn(WorldAccess level, Random rand, BlockPos.Mutable mutablePos, int height, int minAge, int maxAge, VerticalGrowingPlantBlock plantBlock)
    {
        for(int i = 1; i <= height; ++i)
        {
            if (level.isAir(mutablePos))
            {
                if (i == height || !level.isAir(mutablePos.up())) {level.setBlockState(mutablePos, plantBlock.getDefaultState().with(VerticalGrowingPlantBlock.AGE, MathHelper.nextInt(rand, minAge, maxAge)).with(VerticalGrowingPlantBlock.TOP, true), 2); break;}
                level.setBlockState(mutablePos, plantBlock.getDefaultState().with(VerticalGrowingPlantBlock.TOP, false), 2);
            }
            mutablePos.move(Direction.UP);
        }
    }

    private static boolean isInvalidPlacementLocation(WorldAccess level, BlockPos pos)
    {
        if (!level.isAir(pos)) {return true;}
        else
        {
            BlockState blockstate = level.getBlockState(pos.down());
            return !blockstate.isIn(AerialHellTags.Blocks.STELLAR_DIRT);
        }
    }
}
