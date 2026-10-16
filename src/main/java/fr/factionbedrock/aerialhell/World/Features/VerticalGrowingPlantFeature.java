package fr.factionbedrock.aerialhell.World.Features;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fr.factionbedrock.aerialhell.Block.Plants.VerticalGrowingPlantBlock;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellConfiguredFeatures;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

//copy of TwistingVinesFeature class with some customizations and a config with one more parameter; editing placeWeepingVinesColumn and isInvalidPlacementLocation to adapt to Aerial Hell
public record VerticalGrowingPlantFeature(Holder<BlockStateProvider> plantStateProvider, int spreadWidth, int spreadHeight, int minHeight, int maxHeight, int minTries, int maxTries) implements Feature, DungeonSensitiveFeatureCheck
{
    public static final MapCodec<VerticalGrowingPlantFeature> CODEC = RecordCodecBuilder.mapCodec(
        i -> i.group(
                BlockStateProvider.CODEC.fieldOf("plant_state_provider").forGetter(VerticalGrowingPlantFeature::plantStateProvider),
                ExtraCodecs.POSITIVE_INT.fieldOf("spread_width").forGetter(VerticalGrowingPlantFeature::spreadWidth),
                ExtraCodecs.POSITIVE_INT.fieldOf("spread_height").forGetter(VerticalGrowingPlantFeature::spreadHeight),
                ExtraCodecs.POSITIVE_INT.fieldOf("min_height").forGetter(VerticalGrowingPlantFeature::minHeight),
                ExtraCodecs.POSITIVE_INT.fieldOf("max_height").forGetter(VerticalGrowingPlantFeature::maxHeight),
                ExtraCodecs.POSITIVE_INT.fieldOf("min_tries").forGetter(VerticalGrowingPlantFeature::minTries),
                ExtraCodecs.POSITIVE_INT.fieldOf("max_tries").forGetter(VerticalGrowingPlantFeature::maxTries)
            )
            .apply(i, VerticalGrowingPlantFeature::new)
    );

    @Override public MapCodec<VerticalGrowingPlantFeature> codec() {return CODEC;}

    @Override public List<ResourceKey<Feature>> getAssociatedConfiguredFeatures() {return AerialHellConfiguredFeatures.Lists.VERTICAL_GROWING_PLANT_LIST;}

    @Override public boolean place(WorldGenLevel level, ChunkGenerator chunkGenerator, RandomSource random, BlockPos origin)
    {
        if (isInvalidPlacementLocation(level, origin) || !this.isDungeonSensitiveValid(level, origin)) {return false;}
        else
        {
            int spreadXZ = this.spreadWidth;
            int spreadY = this.spreadHeight;
            int tries = (this.minTries == this.maxTries) ? this.minTries : Mth.nextInt(random, this.minTries, this.maxTries);
            BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

            for(int l = 0; l <= tries; ++l)
            {
                blockpos$mutableblockpos.set(origin).move(Mth.nextInt(random, -spreadXZ, spreadXZ), Mth.nextInt(random, -spreadY, spreadY), Mth.nextInt(random, -spreadXZ, spreadXZ));
                if (findFirstAirBlockAboveGround(level, blockpos$mutableblockpos) && !isInvalidPlacementLocation(level, blockpos$mutableblockpos))
                {
                    int plantHeight = (this.minHeight == this.maxHeight) ? this.minHeight : Mth.nextInt(random, this.minHeight, this.maxHeight);
                    placeVerticalGrowingPlantColumn(level, random, blockpos$mutableblockpos, plantHeight, 9, 14, this.plantStateProvider.value());
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

    public static void placeVerticalGrowingPlantColumn(WorldGenLevel level, RandomSource rand, BlockPos.MutableBlockPos mutablePos, int height, int minAge, int maxAge, BlockStateProvider plantProvider)
    {
        VerticalGrowingPlantBlock plantBlock = (VerticalGrowingPlantBlock) plantProvider.getState(level, rand, mutablePos).getBlock();
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
