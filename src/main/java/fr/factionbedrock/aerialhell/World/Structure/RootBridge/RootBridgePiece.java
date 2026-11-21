package fr.factionbedrock.aerialhell.World.Structure.RootBridge;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellStructurePieces;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;

/*
 * ~32 blocks long part of a root bridge. The points are computed once for the whole bridge (RootBridgePath), so this piece
 * only places blocks inside the chunk being generated (chunkBox) and never uses the postProcess RandomSource (it depends
 * on the chunk) : the bridge stays continuous across chunks.
 */
public class RootBridgePiece extends StructurePiece
{
    private final int[] points; //x, y, z of each path point
    private final int[] radiuses; //tenths of blocks, one per point
    private final int placeFrom, placeTo; //placed points (the others are only used to compute the root axis at the piece ends)
    private final long seed;

    public RootBridgePiece(int[] points, int[] radiuses, int placeFrom, int placeTo, long seed)
    {
        super(AerialHellStructurePieces.ROOT_BRIDGE_SEGMENT, 0, makeBoundingBox(points, radiuses, placeFrom, placeTo));
        this.setOrientation(null);
        this.points = points; this.radiuses = radiuses;
        this.placeFrom = placeFrom; this.placeTo = placeTo;
        this.seed = seed;
    }

    public RootBridgePiece(CompoundTag tag)
    {
        super(AerialHellStructurePieces.ROOT_BRIDGE_SEGMENT, tag);
        this.points = tag.getIntArray("Points").orElse(new int[0]);
        this.radiuses = tag.getIntArray("Radiuses").orElse(new int[0]);
        int pointsNumber = Math.min(this.points.length / 3, this.radiuses.length);
        this.placeFrom = Math.max(0, tag.getIntOr("PlaceFrom", 0));
        this.placeTo = Math.min(pointsNumber - 1, tag.getIntOr("PlaceTo", pointsNumber - 1));
        this.seed = tag.getLongOr("Seed", 0L);
    }

    private static BoundingBox makeBoundingBox(int[] points, int[] radiuses, int placeFrom, int placeTo)
    {
        int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE, minZ = Integer.MAX_VALUE, maxX = Integer.MIN_VALUE, maxY = Integer.MIN_VALUE, maxZ = Integer.MIN_VALUE;
        for (int i = placeFrom; i <= placeTo; i++)
        {
            int radius = Mth.ceil(radiuses[i] / 10.0F);
            minX = Math.min(minX, points[3 * i] - radius); maxX = Math.max(maxX, points[3 * i] + radius);
            minY = Math.min(minY, points[3 * i + 1] - radius); maxY = Math.max(maxY, points[3 * i + 1] + radius);
            minZ = Math.min(minZ, points[3 * i + 2] - radius); maxZ = Math.max(maxZ, points[3 * i + 2] + radius);
        }
        return new BoundingBox(minX, minY, minZ, maxX, maxY, maxZ);
    }

    @Override protected void addAdditionalSaveData(StructurePieceSerializationContext context, CompoundTag tag)
    {
        tag.putIntArray("Points", this.points);
        tag.putIntArray("Radiuses", this.radiuses);
        tag.putInt("PlaceFrom", this.placeFrom);
        tag.putInt("PlaceTo", this.placeTo);
        tag.putLong("Seed", this.seed);
    }

    @Override public void postProcess(WorldGenLevel level, StructureManager structureManager, ChunkGenerator generator, RandomSource random, BoundingBox chunkBox, ChunkPos chunkPos, BlockPos referencePos)
    {
        BlockState rootState = AerialHellBlocks.GIANT_ROOT.defaultBlockState();
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

        for (int i = this.placeFrom; i <= this.placeTo; i++)
        {
            int x = this.points[3 * i], y = this.points[3 * i + 1], z = this.points[3 * i + 2];
            float radius = this.radiuses[i] / 10.0F;
            int blockRadius = Mth.ceil(radius);
            if (x + blockRadius < chunkBox.minX() || x - blockRadius > chunkBox.maxX() || z + blockRadius < chunkBox.minZ() || z - blockRadius > chunkBox.maxZ()) {continue;}

            BlockState state = rootState.setValue(RotatedPillarBlock.AXIS, this.getRootAxis(i));
            float radiusSq = radius * radius, innerRadius = Math.max(0.0F, radius - 0.8F), innerRadiusSq = innerRadius * innerRadius;
            for (int dx = -blockRadius; dx <= blockRadius; dx++)
            {
                for (int dy = -blockRadius; dy <= blockRadius; dy++)
                {
                    for (int dz = -blockRadius; dz <= blockRadius; dz++)
                    {
                        int distanceSq = dx * dx + dy * dy + dz * dz;
                        if (distanceSq > radiusSq) {continue;}
                        pos.set(x + dx, y + dy, z + dz);
                        if (!chunkBox.isInside(pos)) {continue;}
                        if (distanceSq > innerRadiusSq && this.isRoughnessHole(pos)) {continue;}
                        this.placeBlock(level, state, pos.getX(), pos.getY(), pos.getZ(), chunkBox);
                    }
                }
            }
        }
    }

    //same replaceable blocks as RootBridgeFeature
    @Override protected boolean canBeReplaced(LevelReader level, int x, int y, int z, BoundingBox chunkBox)
    {
        BlockState previousBlock = level.getBlockState(new BlockPos(x, y, z));
        return previousBlock.isAir() || previousBlock.is(AerialHellTags.Blocks.FEATURE_CAN_REPLACE) || previousBlock.is(AerialHellTags.Blocks.STELLAR_DIRT) || previousBlock.is(AerialHellBlocks.STELLAR_STONE);
    }

    //dominant direction of the path around point i
    private Direction.Axis getRootAxis(int i)
    {
        int pointsNumber = this.points.length / 3;
        int before = Math.max(0, i - 1), after = Math.min(pointsNumber - 1, i + 1);
        int dx = Math.abs(this.points[3 * after] - this.points[3 * before]);
        int dy = Math.abs(this.points[3 * after + 1] - this.points[3 * before + 1]);
        int dz = Math.abs(this.points[3 * after + 2] - this.points[3 * before + 2]);
        if (dy > dx && dy > dz) {return Direction.Axis.Y;}
        return dx >= dz ? Direction.Axis.X : Direction.Axis.Z;
    }

    //positional hash (same result whatever the chunk being generated) : a few missing blocks on the root surface
    private boolean isRoughnessHole(BlockPos pos)
    {
        long hash = Mth.getSeed(pos.getX(), pos.getY(), pos.getZ()) ^ this.seed;
        hash = hash * 0x5DEECE66DL + 11L;
        return ((hash >>> 24) & 7L) == 0L;
    }
}
