package fr.factionbedrock.aerialhell.World.Features.GiantTree;

import com.mojang.serialization.Codec;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Util.FeatureHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public class AbstractGiantTreeFeature<FC extends FeatureConfiguration> extends Feature<FC>
{
    public AbstractGiantTreeFeature(Codec<FC> config) {super(config);}

    @Override public boolean place(FeaturePlaceContext<FC> context) {return false;}

    protected boolean canPlace(FeaturePlaceContext<FC> context)
    {
        boolean generatesInDungeon = FeatureHelper.isFeatureGeneratingNextToDungeon(context);
        return isValidTreePos(context.level(), context.origin()) && !generatesInDungeon;
    }

    protected boolean isValidTreePos(WorldGenLevel level, BlockPos pos) {return isValidTreeSupport(level.getBlockState(pos.down())) && (level.isEmptyBlock(pos) || level.getBlockState(pos).is(AerialHellTags.Blocks.AERIALHELL_SAPLINGS)) && thereIsAirAbovePosition(level, pos);}
    protected boolean isValidTreeSupport(BlockState state) {return state.isIn(AerialHellTags.Blocks.STELLAR_DIRT);}
    protected boolean thereIsAirAbovePosition(WorldGenLevel level, BlockPos pos) {return thereIsAirColumnAbovePos(level, pos);}

    protected boolean thereIsAirColumnAbovePos(WorldGenLevel reader, BlockPos pos)
    {
        for (int y=1; y<=8; y++) {if (!reader.getBlockState(pos.up(y)).isAir()) {return false;}} return true;
    }
}
