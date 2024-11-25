package fr.factionbedrock.aerialhell.World.Features.Util.GiantTree;

import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.World.Features.Util.SplineKnotsDeformedStraightLine;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

import java.util.function.Supplier;

public class ClassicGiantBranch extends SplineKnotsDeformedStraightLine
{
    public ClassicGiantBranch(FeaturePlaceContext<?> context, StraightLineParameters straightLineParams, int knotsNumber, KnotsParameters knotsParameters, Supplier<Block> block) {super(context, straightLineParams, knotsNumber, knotsParameters, block);}

    protected boolean isLarge() {return false;}

    @Override protected boolean tryPlacingBlocks(BlockPos.MutableBlockPos pos, int step, int maxStep)
    {
        if (this.isLarge()) {return super.tryPlacingBlocks(pos, step, maxStep);}
        else {return tryPlacingBlock(pos);}
    }

    @Override protected boolean isReplaceable(WorldGenLevel level, BlockPos blockPos)
    {
        BlockState previousBlock = level.getBlockState(blockPos);
        return previousBlock.is(AerialHellTags.Blocks.LEAVES) || super.isReplaceable(level, blockPos) || previousBlock.is(AerialHellTags.Blocks.STELLAR_DIRT);
    }

    @Override protected boolean tryPlacingBlock(BlockPos.MutableBlockPos pos)
    {
        if (!context.level().isEmptyBlock(pos.up())) {return super.tryPlacingBlock(pos);}
        else {return false;}
    }
}
