package fr.factionbedrock.aerialhell.World.Features.Util.GiantTree;

import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.World.Features.Util.SplineKnotsDeformedStraightLine;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.function.Supplier;

public class ClassicGiantTrunk extends SplineKnotsDeformedStraightLine
{
    public ClassicGiantTrunk(FeatureContext<?> context, StraightLineParameters straightLineParams, int knotsNumber, KnotsParameters knotsParameters, Supplier<Block> block) {super(context, straightLineParams, knotsNumber, knotsParameters, block);}

    protected boolean isLarge() {return false;}

    @Override protected boolean isReplaceable(StructureWorldAccess level, BlockPos blockPos)
    {
        BlockState previousBlock = level.getBlockState(blockPos);
        return super.isReplaceable(level, blockPos) || previousBlock.isIn(AerialHellTags.Blocks.LEAVES) || previousBlock.isIn(AerialHellTags.Blocks.STELLAR_DIRT);
    }

    @Override protected boolean tryPlacingBlocks(BlockPos.Mutable pos, int step, int maxStep)
    {
        if (this.isLarge())
        {
            if (step < maxStep / 8) {return tryPlacingBlocksSphere(pos, 3);}
            else {return tryPlacingBlocksSphere(pos, 2);}
        }
        else
        {
            if (step < maxStep / 8) {return tryPlacingBlocksSphere(pos, 2);}
            else {return tryPlacingBlocksCross(pos);}
        }
    }
}
