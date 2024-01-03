package fr.factionbedrock.aerialhell.World.Features.Util.GiantTree;

import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.World.Features.Util.SplineKnotsDeformedStraightLine;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

import java.util.function.Supplier;

public class ClassicGiantTrunk extends SplineKnotsDeformedStraightLine
{
    public ClassicGiantTrunk(FeaturePlaceContext<?> context, StraightLineParameters straightLineParams, int knotsNumber, KnotsParameters knotsParameters, Supplier<Block> block) {super(context, straightLineParams, knotsNumber, knotsParameters, block);}

    protected boolean isLarge() {return false;}

    @Override protected boolean isReplaceable(WorldGenLevel level, BlockPos blockPos)
    {
        BlockState previousBlock = level.getBlockState(blockPos);
        return super.isReplaceable(level, blockPos) || previousBlock.is(AerialHellTags.Blocks.STELLAR_DIRT);
    }

    @Override protected void tryPlacingBlocks(BlockPos.MutableBlockPos pos, int step, int maxStep)
    {
        if (this.isLarge())
        {
            if (step < maxStep / 8) {tryPlacingBlocksSphere(pos, 3);}
            else {tryPlacingBlocksSphere(pos, 2);}
        }
        else
        {
            if (step < maxStep / 8) {tryPlacingBlocksSphere(pos, 2);}
            else {tryPlacingBlocksCross(pos);}
        }
    }
}
