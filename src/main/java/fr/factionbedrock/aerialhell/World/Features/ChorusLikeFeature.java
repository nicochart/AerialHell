package fr.factionbedrock.aerialhell.World.Features;

import com.mojang.serialization.Codec;
import fr.factionbedrock.aerialhell.Block.Plants.ChorusFlowerLikeBlock;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Util.BlockHelper;
import fr.factionbedrock.aerialhell.World.Features.Config.ChorusLikePlantConfig;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class ChorusLikeFeature extends Feature<ChorusLikePlantConfig>
{
    public ChorusLikeFeature(Codec<ChorusLikePlantConfig> config) {super(config);}

    public boolean generate(FeatureContext<ChorusLikePlantConfig> context)
    {
        boolean needsRoof =  context.getConfig().needsRoof().equals("true");
        StructureWorldAccess level = context.getWorld();
        BlockPos pos = context.getOrigin();
        Random random = context.getRandom();
        if (needsRoof && !BlockHelper.hasAnySolidSurfaceAbove(level, pos, 3)) {return false;}
        if (level.isAir(pos) && level.getBlockState(pos.down()).isIn(AerialHellTags.Blocks.STELLAR_DIRT))
        {
            ChorusFlowerLikeBlock.generatePlant(level, pos, random, 8);
            return true;
        }
        else {return false;}
    }
}
