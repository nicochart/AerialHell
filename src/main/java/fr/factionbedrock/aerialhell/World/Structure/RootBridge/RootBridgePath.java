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
//the root can split in several sub roots in the middle of the bridge (they join again further), and fork near its ends to grip other anchors
public class RootBridgePath
{
    private static final int SEGMENT_LENGTH = 32; //path points per piece
    private static final int MAX_RADIUS_BLOCKS = 4;

    private static final SplineKnots.KnotsParameters KNOTS_PARAMETERS = new SplineKnots.KnotsParameters(12, 28, 0.45F, 8, 36, 0.5F); //knots 12 to 28 blocks away from the root line, pulling the points less than 36 blocks away
    private static final int KNOTS_SPACING = 28; //one knot every ~28 blocks
    private static final int KNOTS_FADE_DISTANCE = 12; //knots deformation fades out near the anchors, to keep the root ends in the islands

    //middle split : the root splits in 2 or 3 sub roots twisting around the root center line (the main root is one of them), then they join again
    private static final float SPLIT_RADIUS_FACTOR = 0.65F; //sub roots radius factor, compared to the main root radius

    private final List<RootLine> rootLines = new ArrayList<>(); //main root first, then the sub roots
    private final BlockPos middlePos;
    private final long seed;

    public RootBridgePath(BlockPos bridgeStart, BlockPos bridgeEnd, List<BlockPos> startExtraAnchors, List<BlockPos> endExtraAnchors, RandomSource random)
    {
        float length = (float) Math.sqrt(bridgeStart.distSqr(bridgeEnd));

        //sag (or slight arc), and spline knots, as in SplineKnotsDeformedStraightLine
        KnottedLine knottedLine = new KnottedLine(bridgeStart, bridgeEnd, random);
        float sag = length * (random.nextInt(10) < 7 ? random.nextFloat() * 0.05F + 0.02F : - random.nextFloat() * 0.03F);
        BlockPos[] knots = new BlockPos[Math.max(2, (int) length / KNOTS_SPACING + random.nextInt(2))];
        for (int i = 0; i < knots.length; i++) {knots[i] = knottedLine.getRandomKnotPos();}

        //points
        List<BlockPos> points = new ArrayList<>();
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
            if (points.isEmpty() || !points.getLast().equals(pos)) {points.add(pos);}
        }

        //radius for each point
        float middleRadius = 1.6F + random.nextFloat() * 0.6F, endRadius = middleRadius + 0.8F + random.nextFloat() * 0.6F;
        int[] radiuses = new int[points.size()];
        for (int i = 0; i < radiuses.length; i++)
        {
            float u = radiuses.length > 1 ? 2.0F * i / (radiuses.length - 1) - 1.0F : 0.0F;
            radiuses[i] = Mth.floor(10.0F * Math.min(MAX_RADIUS_BLOCKS, middleRadius + (endRadius - middleRadius) * u * u));
        }
        this.middlePos = points.get(points.size() / 2);

        //middle split
        List<RootLine> splitSubRoots = new ArrayList<>();
        RootLine mainRoot = random.nextFloat() < 0.8F ? splitMiddle(points, radiuses, splitSubRoots, random) : new RootLine(points, radiuses);
        this.rootLines.add(mainRoot);
        this.rootLines.addAll(splitSubRoots);

        //anchor sub roots
        for (BlockPos anchor : startExtraAnchors) {this.addAnchorSubRoot(points, radiuses, true, anchor, random);}
        for (BlockPos anchor : endExtraAnchors) {this.addAnchorSubRoot(points, radiuses, false, anchor, random);}

        this.seed = random.nextLong();
    }

    public BlockPos getMiddlePos() {return this.middlePos;}

    //middle split : between two path points (split part centered on the bridge middle), the root is replaced by 2 or 3 sub roots, evenly spread around the root center line and twisting around it
    //the first sub root is the main root (it keeps the root before and after the split part), the others are added in subRoots
    private static RootLine splitMiddle(List<BlockPos> points, int[] radiuses, List<RootLine> subRoots, RandomSource random)
    {
        int pointsNumber = points.size();
        int splitLength = getRandomMiddleSplitLength(random, pointsNumber);
        if (splitLength < 1) {return new RootLine(points, radiuses);} //bridge too short to split
        int splitStart = (pointsNumber - 1 - splitLength) / 2, splitEnd = splitStart + splitLength; //split part centered on the bridge middle

        //two directions perpendicular to the split part, to move the sub roots around the root center line
        Vector3f direction = new Vector3f(points.get(splitEnd).getX() - points.get(splitStart).getX(), points.get(splitEnd).getY() - points.get(splitStart).getY(), points.get(splitEnd).getZ() - points.get(splitStart).getZ());
        if (direction.lengthSquared() < 1.0E-4F) {return new RootLine(points, radiuses);}
        direction.normalize();
        Vector3f side1 = Math.abs(direction.y) < 0.9F ? new Vector3f(direction).cross(0.0F, 1.0F, 0.0F) : new Vector3f(direction).cross(1.0F, 0.0F, 0.0F);
        side1.normalize();
        Vector3f side2 = new Vector3f(direction).cross(side1);

        int subRootsNumber = random.nextInt(2, 4);
        float firstAngle = random.nextFloat() * Mth.TWO_PI, twist = (random.nextFloat() * 2.0F - 1.0F) * 1.5F * Mth.PI;
        RootLine mainRoot = null;
        for (int subRoot = 0; subRoot < subRootsNumber; subRoot++)
        {
            float angle = firstAngle + subRoot * Mth.TWO_PI / subRootsNumber;
            float spread = getRandomSubRootSpread(random, (float) splitLength / pointsNumber);
            List<BlockPos> subRootPoints = new ArrayList<>();
            List<Integer> subRootRadiuses = new ArrayList<>();
            if (subRoot == 0) {for (int i = 0; i < splitStart; i++) {addPoint(subRootPoints, subRootRadiuses, points.get(i), radiuses[i]);}}

            int steps = 2 * splitLength; //2 points per path point : the sub roots also move sideways
            for (int i = 0; i <= steps; i++)
            {
                float s = (float) i / steps;
                float index = splitStart + s * splitLength;
                Vector3f pos = getPathPos(points, index);
                float subRootAngle = angle + twist * s, offset = spread * Mth.sin(Mth.PI * s); //no offset at both ends of the split part : the sub roots join the root
                pos.add(new Vector3f(side1).mul(Mth.cos(subRootAngle) * offset)).add(new Vector3f(side2).mul(Mth.sin(subRootAngle) * offset));
                float radiusFactor = 1.0F - (1.0F - SPLIT_RADIUS_FACTOR) * Mth.sin(Mth.PI * s); //thinner sub roots in the middle of the split part
                addPoint(subRootPoints, subRootRadiuses, BlockPos.containing(pos.x + 0.5F, pos.y + 0.5F, pos.z + 0.5F), Math.max(10, Mth.floor(getPathRadius(radiuses, index) * radiusFactor)));
            }

            if (subRoot == 0)
            {
                for (int i = splitEnd + 1; i < pointsNumber; i++) {addPoint(subRootPoints, subRootRadiuses, points.get(i), radiuses[i]);}
                mainRoot = new RootLine(subRootPoints, toArray(subRootRadiuses));
            }
            else {subRoots.add(new RootLine(subRootPoints, toArray(subRootRadiuses)));}
        }
        return mainRoot;
    }

    private static int getRandomMiddleSplitLength(RandomSource random, int pointsNumber)
    {
        float minLengthRatio = 0.4F, maxLengthRatio = 0.6F; //40 to 60 % of the root length
        return Mth.floor(pointsNumber * (minLengthRatio + random.nextFloat() * (maxLengthRatio - minLengthRatio)));
    }
    
    private static float getRandomSubRootSpread(RandomSource random, float splitLengthRatio) //max distance (in blocks) between each sub root (main root included) and the root center line (initial path)
    {
        float minSpread = 5.0F, maxSpread = 11.0F;
        return (minSpread + random.nextFloat() * (maxSpread - minSpread)) * splitLengthRatio; //multiplied by the split part length ratio : longer split parts spread more
    }

    //anchor sub root : forks from the root a few blocks before its end, then goes to another anchor
    //quadratic bezier curve : it leaves the root in the root direction (control point between the fork and the root end), then bends to its anchor
    private void addAnchorSubRoot(List<BlockPos> points, int[] radiuses, boolean bridgeStartSide, BlockPos anchor, RandomSource random)
    {
        int pointsNumber = points.size();
        int forkDistance = getRandomForkDistance(random, pointsNumber);
        int forkIndex = bridgeStartSide ? forkDistance : pointsNumber - 1 - forkDistance;
        BlockPos fork = points.get(forkIndex), rootEnd = points.get(bridgeStartSide ? 0 : pointsNumber - 1);
        Vector3f start = new Vector3f(fork.getX(), fork.getY(), fork.getZ()), end = new Vector3f(anchor.getX(), anchor.getY(), anchor.getZ());
        Vector3f control = new Vector3f(fork.getX() + rootEnd.getX(), fork.getY() + rootEnd.getY(), fork.getZ() + rootEnd.getZ()).mul(0.5F);
        int radius = Math.max(10, Mth.floor(radiuses[forkIndex] * SPLIT_RADIUS_FACTOR));

        List<BlockPos> subRootPoints = new ArrayList<>();
        List<Integer> subRootRadiuses = new ArrayList<>();
        int steps = Math.max(2, 2 * Mth.ceil(start.distance(control) + control.distance(end))); //2 points per block
        for (int i = 0; i <= steps; i++)
        {
            float t = (float) i / steps;
            Vector3f pos = new Vector3f(start).mul((1.0F - t) * (1.0F - t)).add(new Vector3f(control).mul(2.0F * (1.0F - t) * t)).add(new Vector3f(end).mul(t * t));
            addPoint(subRootPoints, subRootRadiuses, BlockPos.containing(pos.x + 0.5F, pos.y + 0.5F, pos.z + 0.5F), radius);
        }
        this.rootLines.add(new RootLine(subRootPoints, toArray(subRootRadiuses)));
    }

    private static int getRandomForkDistance(RandomSource random, int pointsNumber)
    {
        float minDistanceRatio = 0.10F, maxDistanceRatio = 0.15F; //10 to 15 % of the root length
        return Mth.floor(pointsNumber * (minDistanceRatio + random.nextFloat() * (maxDistanceRatio - minDistanceRatio)));
    }

    //position on the path, between two path points (index with a decimal part)
    private static Vector3f getPathPos(List<BlockPos> points, float index)
    {
        int i0 = Mth.clamp(Mth.floor(index), 0, points.size() - 1), i1 = Math.min(i0 + 1, points.size() - 1);
        float t = index - i0;
        BlockPos pos0 = points.get(i0), pos1 = points.get(i1);
        return new Vector3f(Mth.lerp(t, pos0.getX(), pos1.getX()), Mth.lerp(t, pos0.getY(), pos1.getY()), Mth.lerp(t, pos0.getZ(), pos1.getZ()));
    }

    private static float getPathRadius(int[] radiuses, float index)
    {
        int i0 = Mth.clamp(Mth.floor(index), 0, radiuses.length - 1), i1 = Math.min(i0 + 1, radiuses.length - 1);
        return Mth.lerp(index - i0, radiuses[i0], radiuses[i1]);
    }

    private static void addPoint(List<BlockPos> points, List<Integer> radiuses, BlockPos pos, int radius)
    {
        if (points.isEmpty() || !points.getLast().equals(pos)) {points.add(pos); radiuses.add(radius);}
    }

    private static int[] toArray(List<Integer> list)
    {
        int[] array = new int[list.size()];
        for (int i = 0; i < array.length; i++) {array[i] = list.get(i);}
        return array;
    }

    public void addPieces(StructurePiecesBuilder builder)
    {
        for (RootLine rootLine : this.rootLines) {this.addRootLinePieces(builder, rootLine);}
    }

    private void addRootLinePieces(StructurePiecesBuilder builder, RootLine rootLine)
    {
        List<BlockPos> points = rootLine.points();
        int[] radiuses = rootLine.radiuses();
        int pointsNumber = points.size();
        for (int from = 0; from < pointsNumber - 1; from += SEGMENT_LENGTH)
        {
            int to = Math.min(from + SEGMENT_LENGTH, pointsNumber - 1);
            //one extra point on each side (not placed), so that the root axis is computed the same way as in the neighbour pieces
            int contextFrom = Math.max(0, from - 1), contextTo = Math.min(pointsNumber - 1, to + 1);
            int size = contextTo - contextFrom + 1;
            int[] piecePoints = new int[3 * size], pieceRadiuses = new int[size];
            for (int i = 0; i < size; i++)
            {
                BlockPos pos = points.get(contextFrom + i);
                piecePoints[3 * i] = pos.getX(); piecePoints[3 * i + 1] = pos.getY(); piecePoints[3 * i + 2] = pos.getZ();
                pieceRadiuses[i] = radiuses[contextFrom + i];
            }
            builder.addPiece(new RootBridgePiece(piecePoints, pieceRadiuses, from - contextFrom, to - contextFrom, this.seed));
        }
    }

    //one root line : points from one end to the other, and the root radius at each point (tenths of blocks)
    private record RootLine(List<BlockPos> points, int[] radiuses) {}

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
