package fr.factionbedrock.aerialhell.World.Features.Util;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.function.Supplier;

public class SplineKnotsDeformedEllipsoid extends Ellipsoid implements SplineKnots
{
    private final int knotsNumber;
    private final KnotsParameters knotsParams;
    private final BlockPos[] knots;
    private final boolean generateBothDeformedAndNonDeformed;

    public SplineKnotsDeformedEllipsoid(FeatureContext<?> context, Supplier<Block> block, EllipsoidParameters ellipsoidParams, BlockPos centerPos, Ellipsoid.Types.EllipsoidType type, int knotsNumber, KnotsParameters knotsParams, boolean generateBothDeformedAndNonDeformed)
    {
        super(context, block, ellipsoidParams, centerPos, type);
        this.knotsNumber = knotsNumber;
        this.knotsParams = knotsParams;
        this.generateBothDeformedAndNonDeformed = generateBothDeformedAndNonDeformed;
        this.knots = new BlockPos[knotsNumber];
        for (int i=0; i<knotsNumber; i++)
        {
            this.knots[i] = createRandomKnot(context);
        }
    }

    @Override public BlockPos getRandomKnotPos()
    {
        int i=0, max_tries = 5, x = knotsParams.getRandomKnotDistance(context.getRandom()), y = knotsParams.getRandomKnotDistance(context.getRandom()), z = knotsParams.getRandomKnotDistance(context.getRandom());
        while (this.isPosInsideGeneratedEllipsoidPart(x, y, z) && i++ <= max_tries)
        {
            x = knotsParams.getRandomKnotDistance(context.getRandom()); y = knotsParams.getRandomKnotDistance(context.getRandom()); z = knotsParams.getRandomKnotDistance(context.getRandom());
        }
        return getLevelPosFromEllipsoidPos(x, y, z);
    }

    @Override protected void generateInnerLoop(BlockPos.Mutable placementPos, int x, int y, int z, boolean generateBorder)
    {
        if (this.generateBothDeformedAndNonDeformed) {super.generateInnerLoop(placementPos, x, y, z, generateBorder);} //generating non deformed
        if (randomlyChooseToNotPlaceBlock(generateBorder)) {return;}
        if (generateBorder)
        {
            if (isPosAtEllipsoidOutsideBorder(x, y, z)) //if pos is at ellipsis border : try to place block
            {
                placementPos.set(getKnotsDeformedPos(getLevelPosFromEllipsoidPos(x, y, z), this.knots, this.knotsNumber, this.knotsParams));
                tryPlacingBlock(placementPos, getEllipsoidPosFromLevelPos(placementPos));
            }
        }
        else
        {
            if (this.isPosInsideEllipsoid(x, y, z))
            {
                placementPos.set(getKnotsDeformedPos(getLevelPosFromEllipsoidPos(x, y, z), this.knots, this.knotsNumber, this.knotsParams));
                tryPlacingBlock(placementPos, getEllipsoidPosFromLevelPos(placementPos));
            }
        }
    }
}
