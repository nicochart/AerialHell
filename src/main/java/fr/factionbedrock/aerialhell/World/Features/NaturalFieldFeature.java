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
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

import java.util.List;

public record NaturalFieldFeature(int maxVerticalOffset, float baseChance, float acceptOffsetChance, Holder<BlockStateProvider> cropStateProvider) implements Feature, DungeonSensitiveFeatureCheck
{
    public static final MapCodec<NaturalFieldFeature> CODEC = RecordCodecBuilder.mapCodec(
        i -> i.group(
                ExtraCodecs.POSITIVE_INT.fieldOf("max_vertical_offset").forGetter(NaturalFieldFeature::maxVerticalOffset),
                ExtraCodecs.POSITIVE_FLOAT.fieldOf("base_chance").forGetter(NaturalFieldFeature::baseChance),
                ExtraCodecs.POSITIVE_FLOAT.fieldOf("accept_offset_chance").forGetter(NaturalFieldFeature::acceptOffsetChance),
                BlockStateProvider.CODEC.fieldOf("crop_state_provider").forGetter(NaturalFieldFeature::cropStateProvider)
            )
            .apply(i, NaturalFieldFeature::new)
    );

    @Override public MapCodec<NaturalFieldFeature> codec() {return CODEC;}

    @Override public List<ResourceKey<Feature>> getAssociatedConfiguredFeatures() {return AerialHellConfiguredFeatures.Lists.NATURAL_FIELD_LIST;}

    @Override public boolean place(WorldGenLevel level, ChunkGenerator chunkGenerator, RandomSource random, BlockPos origin)
    {
		boolean canGenerate = isAboveSurfaceBlockPos(level, origin) && !BlockHelper.hasAnySolidSurfaceAbove(level, origin.above(2), 3);
		
        if (canGenerate && this.isDungeonSensitiveValid(level, origin))
        {
        	generateField(level, random, origin);
        	return true;
        }
        return false;
    }
    
    protected void generateField(WorldGenLevel level, RandomSource random, BlockPos origin)
    {
    	BlockPos.MutableBlockPos placementPos = new BlockPos.MutableBlockPos();
    	boolean isBig = random.nextDouble() > 0.8;
        int radiusX = getRandomRadius(random, isBig);
        int radiusZ = getRandomRadius(random, isBig);

        for(int x = -radiusX; x <= radiusX; x++)
        {
            for(int z = -radiusZ; z <= radiusZ; z++)
            {
                BlockPos pos = new BlockPos(x, 0, z);
                if (isPosInsideEllipsis(pos, radiusX, radiusZ) && level.getRandom().nextFloat() < this.baseChance)
                {
                    placementPos.set(origin.offset(pos));
                    findHeightForPlacement(level, placementPos);
                    if (isValidBlockPosForStellarWheat(level, placementPos))
                    {
                        level.setBlock(placementPos.below(), AerialHellBlocks.STELLAR_FARMLAND.defaultBlockState(), 2);
                        BlockState placementState = this.cropStateProvider.value().getState(level, random, placementPos);
                        if (placementState.getBlock() instanceof CropBlock)
                        {
                            placementState = placementState.setValue(CropBlock.AGE, level.getRandom().nextInt(4, 8));
                        }
                        level.setBlock(placementPos, placementState, 2);
                    }
                }
            }
        }
    }

    protected void findHeightForPlacement(WorldGenLevel level, BlockPos.MutableBlockPos blockPos)
    {
        int step = 0;
        while (step < this.maxVerticalOffset && !isAboveSurfaceBlockPos(level, blockPos))
        {
            float probabilityToMove = this.acceptOffsetChance / (step + 1);
            if (level.getRandom().nextFloat() > probabilityToMove) {return;}

            if (level.getBlockState(blockPos).isAir()) {blockPos.move(Direction.DOWN);}
            else {blockPos.move(Direction.UP);}
            step++;
        }
    }

    private static boolean isAboveSurfaceBlockPos(WorldGenLevel world, BlockPos blockPos)
    {
        return world.getBlockState(blockPos).isAir() && !world.getBlockState(blockPos.below()).isAir();
    }

    private static boolean isValidBlockPosForStellarWheat(WorldGenLevel world, BlockPos blockPos)
    {
        return world.getBlockState(blockPos).isAir() && world.getBlockState(blockPos.below()).is(AerialHellTags.Blocks.STELLAR_DIRT);
    }
    
    private int getRandomRadius(RandomSource rand, boolean isBig)
    {
    	return isBig ? (int) (5 + rand.nextFloat() * 5) : (int) (3 + rand.nextFloat() * 4);
    }
    
    private boolean isPosInsideEllipsis(BlockPos pos, float a, float c)
    {
        float x = pos.getX() - 0.5F;
        float z = pos.getZ() - 0.5F;
        return x*x/(a*a) + z*z/(c*c) < 1.0F;
    }
}