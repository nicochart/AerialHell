package fr.factionbedrock.aerialhell.World.Features.Util;

import fr.factionbedrock.aerialhell.Util.FeatureHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.feature.util.FeatureContext;
import org.joml.Vector3f;

public interface SplineKnots
{
    default BlockPos getKnotsDeformedPos(BlockPos basePos, BlockPos[] knots, int knotsNumber, KnotsParameters knotsParams)
    {
        Vector3f knotsDeformationVector = getPosKnotsDeformationVector(basePos, knots, knotsNumber, knotsParams);
        return basePos.add((int) knotsDeformationVector.x, (int) knotsDeformationVector.y, (int) knotsDeformationVector.z);
    }

    default BlockPos createRandomKnot(FeatureContext<?> context)
    {
        BlockPos centerOfFeature = FeatureHelper.getFeatureCenter(context);
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
        for (int i=0; i<knotsNumber; i++)
        {
            singleKnotDeformationVec = getKnotDeformationVector(deformedPos, knots[i], knotsParams);
            deformationVector.add(singleKnotDeformationVec.x, singleKnotDeformationVec.y, singleKnotDeformationVec.z);
        }
        return deformationVector;
    }

    default Vector3f getKnotDeformationVector(BlockPos deformedPos, BlockPos knot, KnotsParameters knotsParams)
    {
        float knotDeformationFactor = knotsParams.getKnotDeformationFactor((float) Math.sqrt(deformedPos.getSquaredDistance(knot)));
        return new Vector3f(knot.getX() - deformedPos.getX(), knot.getY() - deformedPos.getY(), knot.getZ() - deformedPos.getZ()).normalize(knotDeformationFactor * knotsParams.getKnotDeformation());
    }

    class KnotsParameters
    {
        private final int KNOT_MIN_DISTANCE_TO_POS, KNOT_MAX_DISTANCE_TO_POS; //knot position min and max distance to base "undeformed" pos
        private final float KNOT_DEFORMATION_MULTIPLIER;
        private final int KNOT_DEFORMATION_MIN_DISTANCE, KNOT_DEFORMATION_MAX_DISTANCE; //max = distance at which deformation start, min = distance at which deformation is maximum

        public KnotsParameters(int knotMinDistance, int knotMaxDistance, float knotDeformationMultiplier, int knotDeformationMinDistance, int knotDeformationMaxDistance)
        {
            this.KNOT_MIN_DISTANCE_TO_POS = knotMinDistance; this.KNOT_MAX_DISTANCE_TO_POS = knotMaxDistance;
            this.KNOT_DEFORMATION_MULTIPLIER = knotDeformationMultiplier;
            this.KNOT_DEFORMATION_MIN_DISTANCE = knotDeformationMinDistance; this.KNOT_DEFORMATION_MAX_DISTANCE = knotDeformationMaxDistance;
        }

        public int getKnotMinDistance() {return KNOT_MIN_DISTANCE_TO_POS;}
        public int getKnotMaxDistance() {return KNOT_MAX_DISTANCE_TO_POS;}
        public float getKnotDeformation() {return KNOT_DEFORMATION_MULTIPLIER;}
        public int getKnotDeformationMinDistance() {return KNOT_DEFORMATION_MIN_DISTANCE;}
        public int getKnotDeformationMaxDistance() {return KNOT_DEFORMATION_MAX_DISTANCE;}

        public int getRandomKnotDistance(Random rand)
        {
            int sign = rand.nextInt(2) == 0 ? -1 : 1;
            return sign * rand.nextBetweenExclusive(KNOT_MIN_DISTANCE_TO_POS, KNOT_MAX_DISTANCE_TO_POS);
        }

        public float getKnotDeformationFactor(float distanceToKnot)
        {
            if (distanceToKnot <= KNOT_DEFORMATION_MIN_DISTANCE) {return KNOT_DEFORMATION_MAX_DISTANCE - KNOT_DEFORMATION_MIN_DISTANCE;}
            else if (distanceToKnot <= KNOT_DEFORMATION_MAX_DISTANCE) {return KNOT_DEFORMATION_MAX_DISTANCE - distanceToKnot;}
            else /*if (distanceToKnot > KNOT_DEFORMATION_MAX_DISTANCE)*/ {return 0;}
        }
    }
}
