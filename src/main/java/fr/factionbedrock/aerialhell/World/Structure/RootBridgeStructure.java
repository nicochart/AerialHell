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

import javax.annotation.Nullable;
import java.util.Optional;

//Like RootBridgeFeature but a lot bigger, because structures generate in a 17 x 17 chunks (instead of 3x3 chunks for features)
public class RootBridgeStructure extends Structure
{
    public static final MapCodec<RootBridgeStructure> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(RootBridgeStructure.settingsCodec(instance),
                    //bridge start and end x and z distance from the structure center (random pos of the start chunk), on each axis
                    //max 112 : pieces can only be placed up to 8 chunks away from the start chunk (at least 128 blocks from the structure center),
                    //minus margins for the root radius (4 blocks) and the knots deformation (12 blocks)
                    Codec.intRange(0, 112).fieldOf("min_xz_distance").forGetter(structure -> structure.minXZDistance), //min abs xz offset
                    Codec.intRange(0, 112).fieldOf("max_xz_distance").forGetter(structure -> structure.maxXZDistance), //max abs xz offset
                    Codec.INT.fieldOf("min_y").forGetter(structure -> structure.minY), //anchors (start and end) pos min y, at least 4 blocks (max root radius) above the dimension bottom
                    Codec.INT.fieldOf("max_y").forGetter(structure -> structure.maxY) //anchors (start and end) pos max y, at least 4 blocks (max root radius) below the dimension top
            ).apply(instance, RootBridgeStructure::new));

    private static final int VERTICAL_BRIDGE_MAX_XZ_OFFSET = 8; //vertical bridge end max x and z distance from the bridge start

    private enum RootBridgeType
    {
        HORIZONTAL(4, true, 0, 40), //far horizontally, small height difference
        VERTICAL(4, false, 60, 256), //almost no horizontal distance, big height difference
        DIAGONAL(2, true, 41, 256); //far horizontally, big height difference

        private final int weight;
        private final boolean farEnd;
        private final int minYDifference, maxYDifference;

        RootBridgeType(int weight, boolean farEnd, int minYDifference, int maxYDifference)
        {
            this.weight = weight; this.farEnd = farEnd;
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

    @Override public StructureType<?> type() {return AerialHellStructures.ROOT_BRIDGE.get();}

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

        //computing root bridge path
        //no dungeon check. Place this structure in local_modifications step to avoid it generating in dungeons
        RootBridgePath bridge = new RootBridgePath(bridgeStart, bridgeEnd, random);
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
            BlockPos potentialBridgeEnd = bridgeType.farEnd ? getRandomFarBridgeEndPos(rand, centerOfStructure, bridgeStart, y) : getRandomCloseBridgeEndPos(rand, centerOfStructure, bridgeStart, y);
            if (isValidBridgeStartOrEnd(context, potentialBridgeEnd)) {return potentialBridgeEnd;}
        }
        return null;
    }

    //far end (horizontal and diagonal bridges) : if bridge start offset was positive, bridge end offset will be negative
    private BlockPos getRandomFarBridgeEndPos(RandomSource rand, BlockPos centerOfStructure, BlockPos bridgeStart, int y)
    {
        int xOffset = rand.nextInt(this.minXZDistance, this.maxXZDistance + 1), zOffset = rand.nextInt(this.minXZDistance, this.maxXZDistance + 1);
        if (bridgeStart.getX() >= centerOfStructure.getX()) {xOffset = -xOffset;}
        if (bridgeStart.getZ() >= centerOfStructure.getZ()) {zOffset = -zOffset;}
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
