package fr.factionbedrock.aerialhell.World.Structure.RootBridge;

import fr.factionbedrock.aerialhell.Util.FeatureHelper;
import fr.factionbedrock.aerialhell.World.Features.Util.SplineKnots;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePiecesBuilder;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;

//whole root bridge path from bridge start to bridge end, like RootBridgeFeature.RootBridge
public class RootBridgePath
{
    private static final int SEGMENT_LENGTH = 32; //path points per piece
    private static final int MAX_RADIUS_BLOCKS = 4;

    private static final SplineKnots.KnotsParameters KNOTS_PARAMETERS = new SplineKnots.KnotsParameters(8, 20, 0.3F, 6, 26);
    private static final int KNOTS_SPACING = 28; //one knot every ~28 blocks
    private static final int KNOTS_FADE_DISTANCE = 12; //knots deformation fades out near the anchors, to keep the root ends in the islands

    private final List<BlockPos> points; //one point every ~block, from the bridge start (inside island 1) to the bridge end (inside island 2)
    private final int[] radiuses; //tenths of blocks
    private final long seed;

    public RootBridgePath(BlockPos bridgeStart, BlockPos bridgeEnd, RandomSource random)
    {
        float length = (float) Math.sqrt(bridgeStart.distSqr(bridgeEnd));

        //sag (or slight arc), and spline knots, as in SplineKnotsDeformedStraightLine
        KnottedLine knottedLine = new KnottedLine(bridgeStart, bridgeEnd, random);
        float sag = length * (random.nextInt(10) < 7 ? random.nextFloat() * 0.05F + 0.02F : - random.nextFloat() * 0.03F);
        BlockPos[] knots = new BlockPos[Math.max(2, (int) length / KNOTS_SPACING + random.nextInt(2))];
        for (int i = 0; i < knots.length; i++) {knots[i] = knottedLine.getRandomKnotPos();}

        //points
        this.points = new ArrayList<>();
        int steps = Math.max(1, Mth.ceil(length));
        for (int i = 0; i <= steps; i++)
        {
            float s = (float) i / steps;
            float x = Mth.lerp(s, bridgeStart.getX(), bridgeEnd.getX());
            float y = Mth.lerp(s, bridgeStart.getY(), bridgeEnd.getY()) - sag * 4.0F * s * (1.0F - s); //sag : parabola, 0 at both ends, max in the middle
            float z = Mth.lerp(s, bridgeStart.getZ(), bridgeEnd.getZ());
            BlockPos basePos = BlockPos.containing(x + 0.5F, y + 0.5F, z + 0.5F);
            BlockPos pos = basePos;
            float fade = Mth.clamp(Math.min(s, 1.0F - s) * length / KNOTS_FADE_DISTANCE, 0.0F, 1.0F);
            Vector3f deformation = knottedLine.getPosKnotsDeformationVector(basePos, knots, knots.length, KNOTS_PARAMETERS).mul(fade);
            if (Float.isFinite(deformation.x) && Float.isFinite(deformation.y) && Float.isFinite(deformation.z)) {pos = basePos.offset(Math.round(deformation.x), Math.round(deformation.y), Math.round(deformation.z));}
            if (this.points.isEmpty() || !this.points.getLast().equals(pos)) {this.points.add(pos);}
        }

        //radius for each point
        float middleRadius = 1.6F + random.nextFloat() * 0.6F, endRadius = middleRadius + 0.8F + random.nextFloat() * 0.6F;
        this.radiuses = new int[this.points.size()];
        for (int i = 0; i < this.radiuses.length; i++)
        {
            float u = this.radiuses.length > 1 ? 2.0F * i / (this.radiuses.length - 1) - 1.0F : 0.0F;
            this.radiuses[i] = Mth.floor(10.0F * Math.min(MAX_RADIUS_BLOCKS, middleRadius + (endRadius - middleRadius) * u * u));
        }
        this.seed = random.nextLong();
    }

    public BlockPos getMiddlePos() {return this.points.get(this.points.size() / 2);}

    public void addPieces(StructurePiecesBuilder builder)
    {
        int pointsNumber = this.points.size();
        for (int from = 0; from < pointsNumber - 1; from += SEGMENT_LENGTH)
        {
            int to = Math.min(from + SEGMENT_LENGTH, pointsNumber - 1);
            //one extra point on each side (not placed), so that the root axis is computed the same way as in the neighbour pieces
            int contextFrom = Math.max(0, from - 1), contextTo = Math.min(pointsNumber - 1, to + 1);
            int size = contextTo - contextFrom + 1;
            int[] piecePoints = new int[3 * size], pieceRadiuses = new int[size];
            for (int i = 0; i < size; i++)
            {
                BlockPos pos = this.points.get(contextFrom + i);
                piecePoints[3 * i] = pos.getX(); piecePoints[3 * i + 1] = pos.getY(); piecePoints[3 * i + 2] = pos.getZ();
                pieceRadiuses[i] = this.radiuses[contextFrom + i];
            }
            builder.addPiece(new RootBridgePiece(piecePoints, pieceRadiuses, from - contextFrom, to - contextFrom, this.seed));
        }
    }

    //like SplineKnotsDeformedStraightLine but with no feature region limit
    private static class KnottedLine implements SplineKnots
    {
        private final BlockPos start, end;
        private final RandomSource random;

        public KnottedLine(BlockPos start, BlockPos end, RandomSource random) {this.start = start; this.end = end; this.random = random;}

        @Override public BlockPos getRandomKnotPos()
        {
            float s = this.random.nextFloat() * 0.8F + 0.1F;
            Vector3f offset = FeatureHelper.getRandomOrthogonalVectorToLineDefinedWith2Points(this.start, this.end, this.random).normalize(KNOTS_PARAMETERS.getRandomKnotDistance(this.random));
            BlockPos basePos = BlockPos.containing(Mth.lerp(s, this.start.getX(), this.end.getX()), Mth.lerp(s, this.start.getY(), this.end.getY()), Mth.lerp(s, this.start.getZ(), this.end.getZ()));
            return basePos.offset((int) offset.x, (int) offset.y, (int) offset.z);
        }
    }
}
