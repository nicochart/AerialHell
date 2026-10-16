package fr.factionbedrock.aerialhell.World.Features;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellConfiguredFeatures;
import fr.factionbedrock.aerialhell.Util.BlockHelper;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

//copy of TwistingVinesFeature class ; editing placeWeepingVinesColumn and isInvalidPlacementLocation to adapt to Aerial Hell
public record AerialHellTwistingVinesFeature(int spreadWidth, int spreadHeight, int maxHeight, Holder<BlockStateProvider> headBlockProvider, Holder<BlockStateProvider> bodyBlockProvider, String needsRoof) implements Feature, DungeonSensitiveFeatureCheck
{
    public static final MapCodec<AerialHellTwistingVinesFeature> CODEC = RecordCodecBuilder.mapCodec(
        i -> i.group(
                ExtraCodecs.POSITIVE_INT.fieldOf("spread_width").forGetter(AerialHellTwistingVinesFeature::spreadWidth),
                ExtraCodecs.POSITIVE_INT.fieldOf("spread_height").forGetter(AerialHellTwistingVinesFeature::spreadHeight),
                ExtraCodecs.POSITIVE_INT.fieldOf("max_height").forGetter(AerialHellTwistingVinesFeature::maxHeight),
                BlockStateProvider.CODEC.fieldOf("head_state_provider").forGetter(AerialHellTwistingVinesFeature::headBlockProvider),
                BlockStateProvider.CODEC.fieldOf("body_state_provider").forGetter(AerialHellTwistingVinesFeature::bodyBlockProvider),
                ExtraCodecs.NON_EMPTY_STRING.fieldOf("needs_roof").forGetter(AerialHellTwistingVinesFeature::needsRoof)
            )
            .apply(i, AerialHellTwistingVinesFeature::new)
    );

    @Override public MapCodec<AerialHellTwistingVinesFeature> codec() {return CODEC;}

    @Override public List<ResourceKey<Feature>> getAssociatedConfiguredFeatures() {return AerialHellConfiguredFeatures.Lists.TWISTING_ROOTS_LIST;}

    @Override public boolean place(WorldGenLevel level, ChunkGenerator chunkGenerator, RandomSource random, BlockPos origin)
    {
        if (isInvalidPlacementLocation(level, origin, this.needsRoof.equals("true")) || !this.isDungeonSensitiveValid(level, origin)) {return false;}
        else
        {
            int i = this.spreadWidth;
            int j = this.spreadHeight;
            int k = this.maxHeight;
            BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

            for(int l = 0; l < i * i; ++l)
            {
                blockpos$mutableblockpos.set(origin).move(Mth.nextInt(random, -i, i), Mth.nextInt(random, -j, j), Mth.nextInt(random, -i, i));
                if (findFirstAirBlockAboveGround(level, blockpos$mutableblockpos) && !isInvalidPlacementLocation(level, blockpos$mutableblockpos, false))
                {
                    int i1 = Mth.nextInt(random, 1, k);
                    if (random.nextInt(6) == 0) {i1 *= 2;}
                    if (random.nextInt(5) == 0) {i1 = 1;}
                    placeWeepingVinesColumn(level, random, blockpos$mutableblockpos, i1, 17, 25, this.headBlockProvider.value(), this.bodyBlockProvider.value());
                }
            }
            return true;
        }
    }

    private static boolean findFirstAirBlockAboveGround(LevelAccessor world, BlockPos.MutableBlockPos mutablePos)
    {
        do {mutablePos.move(0, -1, 0); if (world.isOutsideBuildHeight(mutablePos)) {return false;}} while(world.getBlockState(mutablePos).isAir());
        mutablePos.move(0, 1, 0);
        return true;
    }

    public static void placeWeepingVinesColumn(WorldGenLevel world, RandomSource rand, BlockPos.MutableBlockPos mutablePos, int height, int minAge, int maxAge, BlockStateProvider headProvider, BlockStateProvider bodyProvider)
    {
        for(int i = 1; i <= height; ++i)
        {
            if (world.isEmptyBlock(mutablePos))
            {
                if (i == height || !world.isEmptyBlock(mutablePos.above())) {world.setBlock(mutablePos, headProvider.getState(world, rand, mutablePos).setValue(GrowingPlantHeadBlock.AGE, Integer.valueOf(Mth.nextInt(rand, minAge, maxAge))), 2); break;}
                world.setBlock(mutablePos, bodyProvider.getState(world, rand, mutablePos), 2);
            }
            mutablePos.move(Direction.UP);
        }
    }

    private static boolean isInvalidPlacementLocation(LevelReader world, BlockPos pos, boolean needsRoof)
    {
        if (!world.isEmptyBlock(pos)) {return true;}
        else
        {
            if (needsRoof && !BlockHelper.hasAnySolidSurfaceAbove(world, pos, 3)) {return true;}
            BlockState blockstate = world.getBlockState(pos.below());
            return !blockstate.is(AerialHellTags.Blocks.STELLAR_DIRT) && !blockstate.is(AerialHellBlocks.SLIPPERY_SAND) && !blockstate.is(Blocks.NETHERRACK) && !blockstate.is(Blocks.WARPED_NYLIUM) && !blockstate.is(Blocks.WARPED_WART_BLOCK);
        }
    }
}
