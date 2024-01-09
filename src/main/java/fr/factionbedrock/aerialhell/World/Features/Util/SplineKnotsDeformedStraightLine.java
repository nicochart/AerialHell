package fr.factionbedrock.aerialhell.World.Features.Util;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Util.FeatureHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import org.joml.Vector3f;

import java.util.function.Supplier;

public class SplineKnotsDeformedStraightLine extends StraightLine implements SplineKnots
{
    private final int knotsNumber;
    private final KnotsParameters knotsParams;
    private final BlockPos[] knots;

    public SplineKnotsDeformedStraightLine(FeaturePlaceContext<?> context, StraightLineParameters straightLineParams, int knotsNumber, KnotsParameters knotsParameters, Supplier<Block> block)
    {
        super(context, straightLineParams, block);
        this.knotsNumber = knotsNumber;
        this.knotsParams = knotsParameters;
        this.knots = new BlockPos[knotsNumber];
        for (int i=0; i<knotsNumber; i++)
        {
            this.knots[i] = createRandomKnot(context);
        }
    }

    public int getKnotsNumber() {return knotsNumber;}
    public KnotsParameters getKnotsParameters() {return knotsParams;}
    public BlockPos[] getKnots() {return knots;}

    @Override public BlockPos getRandomKnotPos()
    {
        int maxAbsOffset = FeatureHelper.getMaxAbsoluteXYZOffset(straightLineParams.getStart(), straightLineParams.getEnd());
        int randomStep = maxAbsOffset > 1 ? context.random().nextInt(1, maxAbsOffset) : 1;
        Vector3f offsetVector = this.getRandomOrthogonalToStraightLineNormalizedVector(knotsParams.getRandomKnotDistance(context.random()));
        return this.getOffsetPosFromStart(randomStep).offset((int) offsetVector.x, (int) offsetVector.y, (int) offsetVector.z);
    }

    @Override public BlockPos generate(boolean stopAtAnyObstacle, boolean generateDebug)
    {
        int i = 0, maxAbsOffset = FeatureHelper.getMaxAbsoluteXYZOffset(this.straightLineParams.getStart(), this.straightLineParams.getEnd());

        BlockPos.MutableBlockPos placementPos = this.straightLineParams.getStart().mutable();
        while(!placementPos.equals(this.straightLineParams.getEnd()) && i <= maxAbsOffset * straightLineParams.getPrecisionMultiplicator())
        {
            placementPos.set(getKnotsDeformedPos(getOffsetPosFromStart(i), this.knots, this.knotsNumber, this.knotsParams));
            boolean onePlaced = tryPlacingBlocks(placementPos, i, maxAbsOffset * straightLineParams.getPrecisionMultiplicator());
            if (stopAtAnyObstacle && !onePlaced) {return placementPos;}
            i++;
        }
        if (generateDebug) {this.generateDebug();}
        return new BlockPos(placementPos);
    }

    @Override public void generateDebug()
    {
        super.generateDebug();
        //spline knots position
        for (int i=0; i<knotsNumber; i++)
        {
            this.context.level().setBlock(knots[i], AerialHellBlocksAndItems.VIBRANT_SKY_CACTUS_FIBER_LANTERN.get().defaultBlockState(), 0);
        }
    }
}