package fr.factionbedrock.aerialhell.World.Features.Util;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Util.FeatureHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import org.joml.Vector3f;

import java.util.function.Supplier;

public class SplineKnotsDeformedStraightLine extends StraightLine
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
            this.knots[i] = createRandomKnot();
        }
    }

    @Override public BlockPos generate(boolean generateDebug)
    {
        int i = 0, maxAbsOffset = FeatureHelper.getMaxAbsoluteXYZOffset(this.straightLineParams.getStart(), this.straightLineParams.getEnd());

        BlockPos.MutableBlockPos placementPos = new BlockPos.MutableBlockPos();
        placementPos.set(this.straightLineParams.getStart());
        while(!placementPos.equals(this.straightLineParams.getEnd()) && i <= maxAbsOffset * straightLineParams.getPrecisionMultiplicator())
        {
            placementPos.set(getOffsetPosFromStart(i));
            Vector3f knotsDeformationVector = getPosKnotsDeformationVector(placementPos);
            placementPos.move((int) knotsDeformationVector.x, (int) knotsDeformationVector.y, (int) knotsDeformationVector.z);
            tryPlacingBlocks(placementPos, i, maxAbsOffset * straightLineParams.getPrecisionMultiplicator());
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

    private Vector3f getPosKnotsDeformationVector(BlockPos deformedPos)
    {
        Vector3f deformationVector = new Vector3f(0, 0, 0);
        Vector3f singleKnotDeformationVec;
        for (int i=0; i<knotsNumber; i++)
        {
            singleKnotDeformationVec = getKnotDeformationVector(deformedPos, knots[i]);
            deformationVector.add(singleKnotDeformationVec.x, singleKnotDeformationVec.y, singleKnotDeformationVec.z);
        }
        return deformationVector;
    }

    private Vector3f getKnotDeformationVector(BlockPos deformedPos, BlockPos knot)
    {
        float knotDeformationFactor = this.knotsParams.getKnotDeformationFactor((float) Math.sqrt(deformedPos.distSqr(knot)));
        return new Vector3f(knot.getX() - deformedPos.getX(), knot.getY() - deformedPos.getY(), knot.getZ() - deformedPos.getZ()).normalize(knotDeformationFactor * this.knotsParams.getKnotDeformation());
    }

    protected BlockPos createRandomKnot()
    {
        RandomSource rand = context.random();
        BlockPos centerOfFeature = FeatureHelper.getFeatureCenter(context);
        int i=0, maxAbsOffset = FeatureHelper.getMaxAbsoluteXYZOffset(this.straightLineParams.getStart(), this.straightLineParams.getEnd());

        BlockPos knot;
        while (i++ < 15)
        {
            int randomStep = maxAbsOffset > 1 ? rand.nextInt(1, maxAbsOffset) : 1;
            Vector3f offsetVector = this.getRandomOrthogonalToStraightLineNormalizedVector(knotsParams.getRandomKnotDistance(rand));
            knot = this.getOffsetPosFromStart(randomStep).offset((int) offsetVector.x, (int) offsetVector.y, (int) offsetVector.z);
            if (FeatureHelper.isBlockPosInFeatureRegion(centerOfFeature, knot)) {return knot;}
        }
        //can't find any knot in feature region
        return centerOfFeature;
    }

    public static class KnotsParameters
    {
        private final int KNOT_MIN_DISTANCE_TO_STRAIGHT_LINE, KNOT_MAX_DISTANCE_TO_STRAIGHT_LINE; //knot position min and max distance to straight line
        private final float KNOT_DEFORMATION_MULTIPLIER;
        private final int KNOT_DEFORMATION_MIN_DISTANCE, KNOT_DEFORMATION_MAX_DISTANCE; //max = distance at which deformation start, min = distance at which deformation is maximum

        public KnotsParameters(int knotMinDistance, int knotMaxDistance, float knotDeformationMultiplier, int knotDeformationMinDistance, int knotDeformationMaxDistance)
        {
            this.KNOT_MIN_DISTANCE_TO_STRAIGHT_LINE = knotMinDistance; this.KNOT_MAX_DISTANCE_TO_STRAIGHT_LINE = knotMaxDistance;
            this.KNOT_DEFORMATION_MULTIPLIER = knotDeformationMultiplier;
            this.KNOT_DEFORMATION_MIN_DISTANCE = knotDeformationMinDistance; this.KNOT_DEFORMATION_MAX_DISTANCE = knotDeformationMaxDistance;
        }

        public int getKnotMinDistance() {return KNOT_MIN_DISTANCE_TO_STRAIGHT_LINE;}
        public int getKnotMaxDistance() {return KNOT_MAX_DISTANCE_TO_STRAIGHT_LINE;}
        public float getKnotDeformation() {return KNOT_DEFORMATION_MULTIPLIER;}
        public int getKnotDeformationMinDistance() {return KNOT_DEFORMATION_MIN_DISTANCE;}
        public int getKnotDeformationMaxDistance() {return KNOT_DEFORMATION_MAX_DISTANCE;}

        public int getRandomKnotDistance(RandomSource rand)
        {
            int sign = rand.nextInt(2) == 0 ? -1 : 1;
            return sign * rand.nextInt(KNOT_MIN_DISTANCE_TO_STRAIGHT_LINE , KNOT_MAX_DISTANCE_TO_STRAIGHT_LINE);
        }

        public float getKnotDeformationFactor(float distanceToKnot)
        {
            if (distanceToKnot <= KNOT_DEFORMATION_MIN_DISTANCE) {return KNOT_DEFORMATION_MAX_DISTANCE - KNOT_DEFORMATION_MIN_DISTANCE;}
            else if (distanceToKnot <= KNOT_DEFORMATION_MAX_DISTANCE) {return KNOT_DEFORMATION_MAX_DISTANCE - distanceToKnot;}
            else /*if (distanceToKnot > KNOT_DEFORMATION_MAX_DISTANCE)*/ {return 0;}
        }
    }
}
