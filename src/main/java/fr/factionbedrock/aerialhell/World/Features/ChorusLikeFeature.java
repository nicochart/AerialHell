package fr.factionbedrock.aerialhell.World.Features;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fr.factionbedrock.aerialhell.Block.Plants.ChorusFlowerLikeBlock;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellConfiguredFeatures;
import fr.factionbedrock.aerialhell.Util.BlockHelper;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;

import java.util.List;

public record ChorusLikeFeature(String needsRoof) implements Feature, DungeonSensitiveFeatureCheck
{
    public static final MapCodec<ChorusLikeFeature> CODEC = RecordCodecBuilder.mapCodec(
        i -> i.group(
                ExtraCodecs.NON_EMPTY_STRING.fieldOf("needs_roof").forGetter(ChorusLikeFeature::needsRoof)
            )
            .apply(i, ChorusLikeFeature::new)
    );

    @Override public MapCodec<ChorusLikeFeature> codec() {return CODEC;}

    @Override public List<ResourceKey<Feature>> getAssociatedConfiguredFeatures() {return AerialHellConfiguredFeatures.Lists.FULL_MOON_PLANT_LIST;}

    @Override public boolean place(WorldGenLevel level, ChunkGenerator chunkGenerator, RandomSource random, BlockPos origin)
    {
        if (this.needsRoof.equals("true") && !BlockHelper.hasAnySolidSurfaceAbove(level, origin, 3)) {return false;}
        if (level.isEmptyBlock(origin) && level.getBlockState(origin.below()).is(AerialHellTags.Blocks.STELLAR_DIRT))
        {
            if (!this.isDungeonSensitiveValid(level, origin)) {return false;}
            ChorusFlowerLikeBlock.generatePlant(level, origin, random, 8);
            return true;
        }
        else {return false;}
    }
}
