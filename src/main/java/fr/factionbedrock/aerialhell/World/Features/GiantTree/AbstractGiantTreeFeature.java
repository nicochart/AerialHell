package fr.factionbedrock.aerialhell.World.Features.GiantTree;

import com.mojang.serialization.Codec;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Util.FeatureHelper;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class AbstractGiantTreeFeature<FC extends FeatureConfig> extends Feature<FC>
{
    public AbstractGiantTreeFeature(Codec<FC> config) {super(config);}

    @Override public boolean generate(FeatureContext<FC> context) {return false;}

    protected boolean canPlace(FeatureContext<FC> context)
    {
        boolean generatesInDungeon = FeatureHelper.isFeatureGeneratingNextToDungeon(context);
        return isValidTreePos(context.getWorld(), context.getOrigin()) && !generatesInDungeon;
    }

    protected boolean isValidTreePos(StructureWorldAccess world, BlockPos pos) {return isValidTreeSupport(world.getBlockState(pos.down())) && (world.isAir(pos) || world.getBlockState(pos).isIn(AerialHellTags.Blocks.AERIALHELL_SAPLINGS)) && thereIsAirAbovePosition(world, pos);}
    protected boolean isValidTreeSupport(BlockState state) {return state.isIn(AerialHellTags.Blocks.STELLAR_DIRT);}
    protected boolean thereIsAirAbovePosition(StructureWorldAccess world, BlockPos pos) {return thereIsAirColumnAbovePos(world, pos);}

    protected boolean thereIsAirColumnAbovePos(StructureWorldAccess reader, BlockPos pos)
    {
        for (int y=1; y<=8; y++) {if (!reader.getBlockState(pos.up(y)).isAir()) {return false;}} return true;
    }
}
