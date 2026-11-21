package fr.factionbedrock.aerialhell.World.Structure;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellStructures;
import fr.factionbedrock.aerialhell.World.Structure.RootBridge.RootBridgePath;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;

import org.jetbrains.annotations.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//Like RootBridgeFeature but a lot bigger, because structures generate in a 17 x 17 chunks (instead of 3x3 chunks for features)
public class RootBridgeStructure extends Structure
{
    public static final MapCodec<RootBridgeStructure> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(RootBridgeStructure.settingsCodec(instance),
                    //bridge start and end x and z distance from the structure center (random pos of the start chunk), on each axis (diagonal bridges side end : min on one axis only)
                    //max 112 : pieces can only be placed up to 8 chunks away from the start chunk (at least 128 blocks from the structure center),
                    //minus margins for the root radius (4 blocks) and the knots deformation and middle split sub roots spread (12 blocks)
                    //anchor sub roots anchors also stay within max_xz_distance
                    Codec.intRange(0, 112).fieldOf("min_xz_distance").forGetter(structure -> structure.minXZDistance), //min abs xz offset
                    Codec.intRange(0, 112).fieldOf("max_xz_distance").forGetter(structure -> structure.maxXZDistance), //max abs xz offset
                    Codec.INT.fieldOf("min_y").forGetter(structure -> structure.minY), //anchors (start and end) pos min y, at least 4 blocks (max root radius) above the dimension bottom
                    Codec.INT.fieldOf("max_y").forGetter(structure -> structure.maxY) //anchors (start and end) pos max y, at least 4 blocks (max root radius) below the dimension top
            ).apply(instance, RootBridgeStructure::new));

    private static final int VERTICAL_BRIDGE_MAX_XZ_OFFSET = 8; //vertical bridge end max x and z distance from the bridge start
    private static final int EXTRA_ANCHOR_MAX_XZ_OFFSET = 12, EXTRA_ANCHOR_MAX_Y_OFFSET = 6, EXTRA_ANCHOR_MIN_DISTANCE = 5; //extra anchor position around the anchor, min distance to the other anchors

    private enum RootBridgeType
    {
        HORIZONTAL(5, true, false, 0, 40), //far horizontally (opposite corner), small height difference
        VERTICAL(3, false, false, 60, 256), //almost no horizontal distance, big height difference
        DIAGONAL(2, true, true, 41, 256); //far horizontally (opposite corner or opposite side), big height difference

        private final int weight;
        private final boolean farEnd, sideEnd; //sideEnd : the far end can also be on the opposite side of the structure area (other side of the center on one axis only)
        private final int minYDifference, maxYDifference;

        RootBridgeType(int weight, boolean farEnd, boolean sideEnd, int minYDifference, int maxYDifference)
        {
            this.weight = weight; this.farEnd = farEnd; this.sideEnd = sideEnd;
            this.minYDifference = minYDifference; this.maxYDifference = maxYDifference;
        }
    }

    private final int minXZDistance, maxXZDistance; //min and max bridge start-end xz distance from the structure center (abs offset, on each axis)
    private final int minY, maxY; //min and max bridge start-end y

    public RootBridgeStructure(StructureSettings settings, int minXZDistance, int maxXZDistance, int minY, int maxY)
    {
        super(settings);
        this.minXZDistance = Math.min(minXZDistance, maxXZDistance); this.maxXZDistance = maxXZDistance;
        this.minY = Math.min(minY, maxY); this.maxY = maxY;
    }

    @Override public StructureType<?> type() {return AerialHellStructures.ROOT_BRIDGE;}

    @Override protected Optional<GenerationStub> findGenerationPoint(GenerationContext context)
    {
        RandomSource random = context.random();
        ChunkPos chunkPos = context.chunkPos();

        //structure center : random pos of the start chunk
        BlockPos centerOfStructure = new BlockPos(chunkPos.getBlockX(random.nextInt(16)), 0, chunkPos.getBlockZ(random.nextInt(16))); //only x and z are used

        BlockPos bridgeStart = getRandomBridgeStart(context, random, centerOfStructure, 50);
        if (bridgeStart == null) {return Optional.empty();}
        RootBridgeType bridgeType = getRandomBridgeType(random);
        BlockPos bridgeEnd = getRandomBridgeEnd(context, random, centerOfStructure, bridgeStart, bridgeType, 50);
        if (bridgeEnd == null) {return Optional.empty();}

        //anchor sub roots : other anchors around the bridge start and end, if the terrain allows it
        List<BlockPos> startExtraAnchors = getRandomExtraAnchors(context, random, centerOfStructure, bridgeStart, bridgeEnd, 10);
        List<BlockPos> endExtraAnchors = getRandomExtraAnchors(context, random, centerOfStructure, bridgeEnd, bridgeStart, 10);

        //computing root bridge path
        //no dungeon check. Place this structure in local_modifications step to avoid it generating in dungeons
        RootBridgePath bridge = new RootBridgePath(bridgeStart, bridgeEnd, startExtraAnchors, endExtraAnchors, random);
        return Optional.of(new GenerationStub(bridge.getMiddlePos(), bridge::addPieces));
    }

    @Nullable protected BlockPos getRandomBridgeStart(GenerationContext context, RandomSource rand, BlockPos centerOfStructure, int tries)
    {
        for (int t = 0; t < tries; t++)
        {
            int xOffset = getRandomOffset(rand, this.minXZDistance, this.maxXZDistance);
            int y = rand.nextInt(this.minY, this.maxY + 1);
            int zOffset = getRandomOffset(rand, this.minXZDistance, this.maxXZDistance);
            BlockPos potentialBridgeStart = new BlockPos(centerOfStructure.getX() + xOffset, y, centerOfStructure.getZ() + zOffset);
            if (isValidBridgeStartOrEnd(context, potentialBridgeStart)) {return potentialBridgeStart;}
        }
        return null;
    }

    //random bridge type, using RootBridgeType weights (chance of a type = its weight / sum of all weights)
    private RootBridgeType getRandomBridgeType(RandomSource rand)
    {
        int totalWeight = 0;
        for (RootBridgeType bridgeType : RootBridgeType.values()) {totalWeight += bridgeType.weight;}
        int randomWeight = rand.nextInt(totalWeight);
        for (RootBridgeType bridgeType : RootBridgeType.values())
        {
            randomWeight -= bridgeType.weight;
            if (randomWeight < 0) {return bridgeType;}
        }
        return RootBridgeType.HORIZONTAL;
    }

    @Nullable protected BlockPos getRandomBridgeEnd(GenerationContext context, RandomSource rand, BlockPos centerOfStructure, BlockPos bridgeStart, RootBridgeType bridgeType, int tries)
    {
        for (int t = 0; t < tries; t++)
        {
            int y = getRandomBridgeEndY(rand, bridgeStart.getY(), bridgeType);
            if (y == Integer.MIN_VALUE) {return null;} //no height between min_y and max_y fits this bridge type
            BlockPos potentialBridgeEnd = bridgeType.farEnd ? getRandomFarBridgeEndPos(rand, centerOfStructure, bridgeStart, y, bridgeType.sideEnd) : getRandomCloseBridgeEndPos(rand, centerOfStructure, bridgeStart, y);
            if (isValidBridgeStartOrEnd(context, potentialBridgeEnd)) {return potentialBridgeEnd;}
        }
        return null;
    }

    //far end (horizontal and diagonal bridges) : if bridge start offset was positive, bridge end offset will be negative, on both axes (opposite corner of the structure area)
    //side end (diagonal bridges, 2 times out of 3) : on one axis only, any offset on the other axis (opposite side of the structure area)
    private BlockPos getRandomFarBridgeEndPos(RandomSource rand, BlockPos centerOfStructure, BlockPos bridgeStart, int y, boolean sideEnd)
    {
        int xOffset = rand.nextInt(this.minXZDistance, this.maxXZDistance + 1), zOffset = rand.nextInt(this.minXZDistance, this.maxXZDistance + 1);
        if (bridgeStart.getX() >= centerOfStructure.getX()) {xOffset = -xOffset;}
        if (bridgeStart.getZ() >= centerOfStructure.getZ()) {zOffset = -zOffset;}
        int freeAxis = sideEnd ? rand.nextInt(3) : 0; //0 : opposite corner, 1 : opposite side on the x axis (any z offset), 2 : opposite side on the z axis (any x offset)
        if (freeAxis == 1) {zOffset = rand.nextInt(-this.maxXZDistance, this.maxXZDistance + 1);}
        else if (freeAxis == 2) {xOffset = rand.nextInt(-this.maxXZDistance, this.maxXZDistance + 1);}
        return new BlockPos(centerOfStructure.getX() + xOffset, y, centerOfStructure.getZ() + zOffset);
    }

    //close end (vertical bridges) : a few blocks around the bridge start, but not farther than max_xz_distance from the structure center
    private BlockPos getRandomCloseBridgeEndPos(RandomSource rand, BlockPos centerOfStructure, BlockPos bridgeStart, int y)
    {
        int x = Mth.clamp(bridgeStart.getX() + rand.nextInt(-VERTICAL_BRIDGE_MAX_XZ_OFFSET, VERTICAL_BRIDGE_MAX_XZ_OFFSET + 1), centerOfStructure.getX() - this.maxXZDistance, centerOfStructure.getX() + this.maxXZDistance);
        int z = Mth.clamp(bridgeStart.getZ() + rand.nextInt(-VERTICAL_BRIDGE_MAX_XZ_OFFSET, VERTICAL_BRIDGE_MAX_XZ_OFFSET + 1), centerOfStructure.getZ() - this.maxXZDistance, centerOfStructure.getZ() + this.maxXZDistance);
        return new BlockPos(x, y, z);
    }

    //random bridge end y between min_y and max_y, with a height difference from the bridge start between the bridge type min and max (Integer.MIN_VALUE if there is none)
    private int getRandomBridgeEndY(RandomSource rand, int startY, RootBridgeType bridgeType)
    {
        //possible heights below the bridge start (start height included if the min difference is 0), and above it
        int belowMinY = Math.max(this.minY, startY - bridgeType.maxYDifference), belowMaxY = Math.min(this.maxY, startY - bridgeType.minYDifference);
        int aboveMinY = Math.max(this.minY, startY + Math.max(1, bridgeType.minYDifference)), aboveMaxY = Math.min(this.maxY, startY + bridgeType.maxYDifference);
        int belowCount = Math.max(0, belowMaxY - belowMinY + 1), aboveCount = Math.max(0, aboveMaxY - aboveMinY + 1);
        if (belowCount + aboveCount == 0) {return Integer.MIN_VALUE;}

        int randomIndex = rand.nextInt(belowCount + aboveCount);
        return randomIndex < belowCount ? belowMinY + randomIndex : aboveMinY + (randomIndex - belowCount);
    }

    //other anchors around a bridge anchor (0 to MAX_EXTRA_ANCHORS), for anchor sub roots. Not towards the other side of the bridge, and in the structure area
    private List<BlockPos> getRandomExtraAnchors(GenerationContext context, RandomSource rand, BlockPos centerOfStructure, BlockPos anchor, BlockPos otherBridgeAnchor, int tries)
    {
        List<BlockPos> extraAnchors = new ArrayList<>();
        int wantedAnchors = rand.nextInt(4); //max 3 extra anchors
        for (int t = 0; t < tries && extraAnchors.size() < wantedAnchors; t++)
        {
            BlockPos potentialAnchor = anchor.offset(rand.nextInt(-EXTRA_ANCHOR_MAX_XZ_OFFSET, EXTRA_ANCHOR_MAX_XZ_OFFSET + 1), rand.nextInt(-EXTRA_ANCHOR_MAX_Y_OFFSET, EXTRA_ANCHOR_MAX_Y_OFFSET + 1), rand.nextInt(-EXTRA_ANCHOR_MAX_XZ_OFFSET, EXTRA_ANCHOR_MAX_XZ_OFFSET + 1));
            if (Math.abs(potentialAnchor.getX() - centerOfStructure.getX()) > this.maxXZDistance || Math.abs(potentialAnchor.getZ() - centerOfStructure.getZ()) > this.maxXZDistance) {continue;}
            if (potentialAnchor.distSqr(anchor) < EXTRA_ANCHOR_MIN_DISTANCE * EXTRA_ANCHOR_MIN_DISTANCE || isCloseToAnyPos(extraAnchors, potentialAnchor, EXTRA_ANCHOR_MIN_DISTANCE)) {continue;}
            //dot product : the extra anchor must not be on the other side of the bridge (it can be beside the anchor, or behind it)
            int dotProduct = (potentialAnchor.getX() - anchor.getX()) * (anchor.getX() - otherBridgeAnchor.getX()) + (potentialAnchor.getY() - anchor.getY()) * (anchor.getY() - otherBridgeAnchor.getY()) + (potentialAnchor.getZ() - anchor.getZ()) * (anchor.getZ() - otherBridgeAnchor.getZ());
            if (dotProduct < 0) {continue;}
            if (isValidBridgeStartOrEnd(context, potentialAnchor)) {extraAnchors.add(potentialAnchor);}
        }
        return extraAnchors;
    }

    private boolean isCloseToAnyPos(List<BlockPos> positions, BlockPos pos, int distance)
    {
        for (BlockPos otherPos : positions) {if (otherPos.distSqr(pos) < distance * distance) {return true;}}
        return false;
    }

    //random offset between minAbs and maxAbs (included), positive or negative
    private int getRandomOffset(RandomSource rand, int minAbs, int maxAbs)
    {
        int sign = rand.nextInt(2) == 0 ? -1 : 1;
        return sign * rand.nextInt(minAbs, maxAbs + 1);
    }

    //no block is generated yet when the structure start is computed : the terrain is read from the noise (ChunkGenerator.getBaseColumn)
    private boolean isValidBridgeStartOrEnd(GenerationContext context, BlockPos pos)
    {
        NoiseColumn column = context.chunkGenerator().getBaseColumn(pos.getX(), pos.getZ(), context.heightAccessor(), context.randomState());
        return isValidSupportForBridge(column.getBlock(pos.getY())) && thereIsSolidAroundPosition(column, pos);
    }

    //the noise only places the default block (stellar stone), any solid block is a valid support
    private boolean isValidSupportForBridge(BlockState state) {return !state.isAir() && state.getFluidState().isEmpty();}

    private boolean thereIsSolidAroundPosition(NoiseColumn column, BlockPos pos)
    {
        return isThereSolidAroundPos(column, 3, 0.70f, pos); //70% solid in the 7 blocks of the column centered on pos (the anchor can be inside an island)
    }

    private boolean isThereSolidAroundPos(NoiseColumn column, int testRadius, float minSolidProportion, BlockPos pos)
    {
        int blocksNumber = 2 * testRadius + 1;
        int maxNonSolid = (int) (blocksNumber * (1.0F - minSolidProportion));
        int nonSolidCount = 0;
        for (int y=-testRadius; y<=testRadius; y++)
        {
            if (!isValidSupportForBridge(column.getBlock(pos.getY() + y))) {nonSolidCount++;}
            if (nonSolidCount > maxNonSolid) {return false;}
        }
        return true;
    }
}
