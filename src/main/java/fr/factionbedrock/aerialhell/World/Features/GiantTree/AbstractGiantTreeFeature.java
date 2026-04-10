package fr.factionbedrock.aerialhell.World.Features.GiantTree;

import com.mojang.serialization.Codec;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Util.FeatureHelper;
import fr.factionbedrock.aerialhell.World.Features.DungeonSensitiveFeatureCheck;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public abstract class AbstractGiantTreeFeature<FC extends FeatureConfiguration> extends Feature<FC> implements DungeonSensitiveFeatureCheck
{
    public AbstractGiantTreeFeature(Codec<FC> config) {super(config);}

    @Override public boolean place(FeaturePlaceContext<FC> context) {return false;}

    protected boolean canPlace(FeaturePlaceContext<FC> context)
    {
        return isValidTreePos(context.level(), context.origin()) && !this.isDungeonSensitiveValid(context);
    }

    protected boolean isValidTreePos(WorldGenLevel world, BlockPos pos) {return isValidTreeSupport(world.getBlockState(pos.below())) && (world.isEmptyBlock(pos) || world.getBlockState(pos).is(AerialHellTags.Blocks.AERIALHELL_SAPLINGS)) && thereIsAirAbovePosition(world, pos);}
    protected boolean isValidTreeSupport(BlockState state) {return state.is(AerialHellTags.Blocks.STELLAR_DIRT);}
    protected boolean thereIsAirAbovePosition(WorldGenLevel world, BlockPos pos) {return thereIsAirColumnAbovePos(world, pos);}

    protected boolean thereIsAirColumnAbovePos(WorldGenLevel reader, BlockPos pos)
    {
        for (int y=1; y<=8; y++) {if (!reader.getBlockState(pos.above(y)).isAir()) {return false;}} return true;
    }
}
