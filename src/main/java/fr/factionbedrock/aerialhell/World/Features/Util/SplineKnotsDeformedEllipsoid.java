package fr.factionbedrock.aerialhell.World.Features.Util;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

import java.util.function.Supplier;

public class SplineKnotsDeformedEllipsoid extends Ellipsoid implements SplineKnots
{
    private final int knotsNumber;
    private final KnotsParameters knotsParams;
    private final BlockPos[] knots;
    private final boolean generateBothDeformedAndNonDeformed;

    public SplineKnotsDeformedEllipsoid(FeaturePlaceContext<?> context, Supplier<Block> block, EllipsoidParameters ellipsoidParams, BlockPos centerPos, Ellipsoid.Types.EllipsoidType type, int knotsNumber, KnotsParameters knotsParams, boolean generateBothDeformedAndNonDeformed)
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
        int i=0, max_tries = 5, x = knotsParams.getRandomKnotDistance(context.random()), y = knotsParams.getRandomKnotDistance(context.random()), z = knotsParams.getRandomKnotDistance(context.random());
        while (this.isPosInsideGeneratedEllipsoidPart(x, y, z) && i++ <= max_tries)
        {
            x = knotsParams.getRandomKnotDistance(context.random()); y = knotsParams.getRandomKnotDistance(context.random()); z = knotsParams.getRandomKnotDistance(context.random());
        }
        return getLevelPosFromEllipsoidPos(x, y, z);
    }

    @Override protected void generateInnerLoop(BlockPos.MutableBlockPos placementPos, int x, int y, int z, boolean generateBorder)
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
