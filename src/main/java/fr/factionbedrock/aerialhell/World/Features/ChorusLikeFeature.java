package fr.factionbedrock.aerialhell.World.Features;

import com.mojang.serialization.Codec;
import fr.factionbedrock.aerialhell.Block.Plants.ChorusFlowerLikeBlock;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellConfiguredFeatures;
import fr.factionbedrock.aerialhell.Util.BlockHelper;
import fr.factionbedrock.aerialhell.World.Features.Config.ChorusLikePlantConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

import java.util.List;

public class ChorusLikeFeature extends AerialHellFeature<ChorusLikePlantConfig>
{
    public ChorusLikeFeature(Codec<ChorusLikePlantConfig> config) {super(config);}

    @Override protected List<ResourceKey<ConfiguredFeature<?, ?>>> getAssociatedConfiguredFeatures() {return AerialHellConfiguredFeatures.Lists.FULL_MOON_PLANT_LIST;}

    @Override public boolean place(FeaturePlaceContext<ChorusLikePlantConfig> context)
    {
        if (!super.place(context)) {return false;}
        boolean needsRoof =  context.config().needsRoof().equals("true");
        WorldGenLevel level = context.level();
        BlockPos pos = context.origin();
        RandomSource randomsource = context.random();
        if (needsRoof && !BlockHelper.hasAnySolidSurfaceAbove(level, pos, 3)) {return false;}
        if (level.isEmptyBlock(pos) && level.getBlockState(pos.below()).is(AerialHellTags.Blocks.STELLAR_DIRT))
        {
            ChorusFlowerLikeBlock.generatePlant(level, pos, randomsource, 8);
            return true;
        }
        else {return false;}
    }
}
