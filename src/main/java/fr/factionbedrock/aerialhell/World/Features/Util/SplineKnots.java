package fr.factionbedrock.aerialhell.World.Features.Util;

import fr.factionbedrock.aerialhell.Util.FeatureHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import org.joml.Vector3f;

public interface SplineKnots
{
    default BlockPos getKnotsDeformedPos(BlockPos basePos, BlockPos[] knots, int knotsNumber, KnotsParameters knotsParams)
    {
        Vector3f knotsDeformationVector = getPosKnotsDeformationVector(basePos, knots, knotsNumber, knotsParams);
        return basePos.offset((int) knotsDeformationVector.x, (int) knotsDeformationVector.y, (int) knotsDeformationVector.z);
    }

    default BlockPos createRandomKnot(BlockPos origin)
    {
        BlockPos centerOfFeature = FeatureHelper.getFeatureCenter(origin);
        int i=0; BlockPos knot;
        while (i++ < 15)
        {
            knot = getRandomKnotPos();
            if (FeatureHelper.isBlockPosInFeatureRegion(centerOfFeature, knot)) {return knot;}
        }
        //can't find any knot in feature region
        return centerOfFeature;
    }

    BlockPos getRandomKnotPos();

    default Vector3f getPosKnotsDeformationVector(BlockPos deformedPos, BlockPos[] knots, int knotsNumber, KnotsParameters knotsParams)
    {
        Vector3f deformationVector = new Vector3f(0, 0, 0);
        Vector3f singleKnotDeformationVec;
        float nearestKnotDistance = Float.MAX_VALUE;
        for (int i=0; i<knotsNumber; i++)
        {
            singleKnotDeformationVec = getKnotDeformationVector(deformedPos, knots[i], knotsParams);
            deformationVector.add(singleKnotDeformationVec.x, singleKnotDeformationVec.y, singleKnotDeformationVec.z);
            nearestKnotDistance = Math.min(nearestKnotDistance, (float) Math.sqrt(deformedPos.distSqr(knots[i])));
        }

        float maxDeformation = knotsParams.getMaxDeformationToDistanceRatio() * nearestKnotDistance;
        if (deformationVector.length() > maxDeformation) {deformationVector.normalize(maxDeformation);}
        return deformationVector;
    }

    default Vector3f getKnotDeformationVector(BlockPos deformedPos, BlockPos knot, KnotsParameters knotsParams)
    {
        float knotDeformationFactor = knotsParams.getKnotDeformationFactor((float) Math.sqrt(deformedPos.distSqr(knot)));
        return new Vector3f(knot.getX() - deformedPos.getX(), knot.getY() - deformedPos.getY(), knot.getZ() - deformedPos.getZ()).normalize(knotDeformationFactor * knotsParams.getKnotDeformation());
    }

    class KnotsParameters
    {
        private final int KNOT_MIN_DISTANCE_TO_POS, KNOT_MAX_DISTANCE_TO_POS; //knot position min and max distance to base "undeformed" pos
        private final float KNOT_DEFORMATION_MULTIPLIER;
        private final int KNOT_DEFORMATION_MIN_DISTANCE, KNOT_DEFORMATION_MAX_DISTANCE; //max = distance at which deformation start, min = distance at which deformation is maximum
        private final float MAX_DEFORMATION_TO_DISTANCE_RATIO; //max total deformation of a pos (all knots), in proportion of its distance to the nearest knot (1.0F by default)

        public KnotsParameters(int knotMinDistance, int knotMaxDistance, float knotDeformationMultiplier, int knotDeformationMinDistance, int knotDeformationMaxDistance) {this(knotMinDistance, knotMaxDistance, knotDeformationMultiplier, knotDeformationMinDistance, knotDeformationMaxDistance, 1.0F);}
        public KnotsParameters(int knotMinDistance, int knotMaxDistance, float knotDeformationMultiplier, int knotDeformationMinDistance, int knotDeformationMaxDistance, float maxDeformationToDistanceRatio)
        {
            this.KNOT_MIN_DISTANCE_TO_POS = knotMinDistance; this.KNOT_MAX_DISTANCE_TO_POS = knotMaxDistance;
            this.KNOT_DEFORMATION_MULTIPLIER = knotDeformationMultiplier;
            this.KNOT_DEFORMATION_MIN_DISTANCE = knotDeformationMinDistance; this.KNOT_DEFORMATION_MAX_DISTANCE = knotDeformationMaxDistance;
            this.MAX_DEFORMATION_TO_DISTANCE_RATIO = maxDeformationToDistanceRatio;
        }

        public int getKnotMinDistance() {return KNOT_MIN_DISTANCE_TO_POS;}
        public int getKnotMaxDistance() {return KNOT_MAX_DISTANCE_TO_POS;}
        public float getKnotDeformation() {return KNOT_DEFORMATION_MULTIPLIER;}
        public int getKnotDeformationMinDistance() {return KNOT_DEFORMATION_MIN_DISTANCE;}
        public int getKnotDeformationMaxDistance() {return KNOT_DEFORMATION_MAX_DISTANCE;}
        public float getMaxDeformationToDistanceRatio() {return MAX_DEFORMATION_TO_DISTANCE_RATIO;}

        public int getRandomKnotDistance(RandomSource rand)
        {
            int sign = rand.nextInt(2) == 0 ? -1 : 1;
            return sign * rand.nextInt(KNOT_MIN_DISTANCE_TO_POS, KNOT_MAX_DISTANCE_TO_POS);
        }

        public float getKnotDeformationFactor(float distanceToKnot)
        {
            if (distanceToKnot <= KNOT_DEFORMATION_MIN_DISTANCE) {return KNOT_DEFORMATION_MAX_DISTANCE - KNOT_DEFORMATION_MIN_DISTANCE;}
            else if (distanceToKnot <= KNOT_DEFORMATION_MAX_DISTANCE) {return KNOT_DEFORMATION_MAX_DISTANCE - distanceToKnot;}
            else /*if (distanceToKnot > KNOT_DEFORMATION_MAX_DISTANCE)*/ {return 0;}
        }
    }
}
