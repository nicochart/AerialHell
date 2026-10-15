package fr.factionbedrock.aerialhell.World.Features;

import com.mojang.serialization.MapCodec;
import fr.factionbedrock.aerialhell.Block.Plants.ChorusFlowerLikeBlock;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellConfiguredFeatures;
import fr.factionbedrock.aerialhell.Util.BlockHelper;
import fr.factionbedrock.aerialhell.World.Features.Config.ChorusLikePlantConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.Feature;
import fr.factionbedrock.aerialhell.World.Features.Util.FeaturePlaceContext;

import java.util.List;

public class ChorusLikeFeature extends AerialHellFeature<ChorusLikePlantConfig> implements DungeonSensitiveFeatureCheck
{
    public static final MapCodec<ChorusLikeFeature> CODEC = ChorusLikePlantConfig.CODEC.xmap(ChorusLikeFeature::new, ChorusLikeFeature::config);

    public ChorusLikeFeature(ChorusLikePlantConfig config) {super(config);}

    @Override public MapCodec<? extends Feature> codec() {return CODEC;}

    @Override public List<ResourceKey<Feature>> getAssociatedConfiguredFeatures() {return AerialHellConfiguredFeatures.Lists.FULL_MOON_PLANT_LIST;}

    @Override public boolean place(FeaturePlaceContext<ChorusLikePlantConfig> context)
    {
        boolean needsRoof =  context.config().needsRoof().equals("true");
        WorldGenLevel level = context.level();
        BlockPos pos = context.origin();
        RandomSource randomsource = context.random();
        if (needsRoof && !BlockHelper.hasAnySolidSurfaceAbove(level, pos, 3)) {return false;}
        if (level.isEmptyBlock(pos) && level.getBlockState(pos.below()).is(AerialHellTags.Blocks.STELLAR_DIRT))
        {
            if (!this.isDungeonSensitiveValid(context)) {return false;}
            ChorusFlowerLikeBlock.generatePlant(level, pos, randomsource, 8);
            return true;
        }
        else {return false;}
    }
}
