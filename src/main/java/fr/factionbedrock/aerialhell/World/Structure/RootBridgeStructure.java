package fr.factionbedrock.aerialhell.World.Structure;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellStructures;
import fr.factionbedrock.aerialhell.World.Structure.RootBridge.RootBridgePath;
import net.minecraft.core.BlockPos;
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
        BlockPos bridgeEnd = getRandomFarBridgeEnd(context, random, centerOfStructure, bridgeStart, 50, random.nextInt(64) == 0);
        if (bridgeEnd == null) {bridgeEnd = getRandomBridgeEnd(context, random, centerOfStructure, bridgeStart, 20, random.nextInt(32) == 0);}
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

    @Nullable protected BlockPos getRandomFarBridgeEnd(GenerationContext context, RandomSource rand, BlockPos centerOfStructure, BlockPos bridgeStart, int tries, boolean forceFarBridge)
    {
        //if bridge start offset was positive, bridge end offset will be negative
        int xMinEndOffset, zMinEndOffset, xMaxEndOffset, zMaxEndOffset;
        if (bridgeStart.getX() < centerOfStructure.getX()) {xMinEndOffset = this.minXZDistance; xMaxEndOffset = this.maxXZDistance;}
        else {xMinEndOffset = -this.maxXZDistance; xMaxEndOffset = -this.minXZDistance;}

        if (bridgeStart.getZ() < centerOfStructure.getZ()) {zMinEndOffset = this.minXZDistance; zMaxEndOffset = this.maxXZDistance;}
        else {zMinEndOffset = -this.maxXZDistance; zMaxEndOffset = -this.minXZDistance;}

        BlockPos potentialBridgeEnd = centerOfStructure;
        for (int t = 0; t < tries; t++)
        {
            potentialBridgeEnd = new BlockPos(centerOfStructure.getX() + rand.nextInt(xMinEndOffset, xMaxEndOffset + 1), rand.nextInt(this.minY, this.maxY + 1), centerOfStructure.getZ() + rand.nextInt(zMinEndOffset, zMaxEndOffset + 1));
            if (isValidBridgeStartOrEnd(context, potentialBridgeEnd)) {return potentialBridgeEnd;}
        }
        return forceFarBridge ? potentialBridgeEnd : null;
    }

    @Nullable protected BlockPos getRandomBridgeEnd(GenerationContext context, RandomSource rand, BlockPos centerOfStructure, BlockPos bridgeStart, int tries, boolean forceNonNullReturn)
    {
        BlockPos potentialBridgeEnd = centerOfStructure;
        for (int t = 0; t < tries; t++)
        {
            int xOffset = getRandomOffset(rand, this.minXZDistance, this.maxXZDistance);
            int y = rand.nextInt(this.minY, this.maxY + 1);
            int zOffset = getRandomOffset(rand, this.minXZDistance, this.maxXZDistance);
            potentialBridgeEnd = new BlockPos(centerOfStructure.getX() + xOffset, y, centerOfStructure.getZ() + zOffset);
            if (isValidBridgeStartOrEnd(context, potentialBridgeEnd) && Math.sqrt(bridgeStart.distSqr(potentialBridgeEnd)) > 16) {return potentialBridgeEnd;}
        }
        return forceNonNullReturn ? potentialBridgeEnd : null;
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
