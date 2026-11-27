package fr.factionbedrock.aerialhell.World;

import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.status.ChunkStatus;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class AerialHellWorldSpawnFinder
{
    private static final int SEARCH_RADIUS_IN_CHUNKS = 8; //larger search : at most 17x17 chunks (vanilla : 11x11)
    private static final int GROUND_CHECK_RADIUS = 7, GROUND_CHECK_STEP = 2; //not on an island edge : 8x8 columns checked around the chunk center, all in the chunk
    private static final int GROUND_MAX_HEIGHT_DIFFERENCE = 8; //not on an island edge
    private static final float MIN_GROUND_RATIO = 0.75F; //not on an island edge
    private static final int SPAWN_MAX_DISTANCE_TO_CHUNK_CENTER = 6; //spawn close to the chunk center

    //adapted version of MinecraftServer.setInitialSpawn
    //returns null if no ground was found at all
    @Nullable public static BlockPos findWorldSpawn(ServerLevel level)
    {
        List<ChunkPos> otherChunksWithGround = new ArrayList<>(); //fallback if no chunk is a good spawn chunk (vanilla keeps the center of the origin chunk)
        for (BlockPos chunkCoords : BlockPos.spiralAround(BlockPos.ZERO, SEARCH_RADIUS_IN_CHUNKS, Direction.EAST, Direction.SOUTH)) //vanilla : spiral around the origin chunk
        {
            ChunkAccess chunk = level.getChunk(chunkCoords.getX(), chunkCoords.getZ(), ChunkStatus.TERRAIN); //faster : terrain step only, without features (vanilla : full chunk)
            BlockPos centerGroundPos = getGroundPos(chunk, chunk.getPos().getMiddleBlockX(), chunk.getPos().getMiddleBlockZ()); //ground at the chunk center (vanilla : any column of the chunk)
            if (centerGroundPos == null) {continue;}

            if (isGoodSpawnChunk(level, chunk, centerGroundPos))
            {
                BlockPos spawnPos = findSpawnPosInFullChunk(level, chunk.getPos());
                if (spawnPos != null) {return spawnPos;} //vanilla : first valid chunk
            }
            else {otherChunksWithGround.add(chunk.getPos());}
        }

        for (ChunkPos chunkPos : otherChunksWithGround) //fallback : nearest chunk with ground
        {
            BlockPos spawnPos = findSpawnPosInFullChunk(level, chunkPos);
            if (spawnPos != null) {return spawnPos;}
        }
        return null;
    }

    //top block of the column, if a player can stand on it. null otherwise, or if the column is empty (void)
    @Nullable private static BlockPos getGroundPos(ChunkAccess chunk, int x, int z)
    {
        int groundY = chunk.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, x & 15, z & 15); //no spawn on trees : leaves ignored (vanilla : MOTION_BLOCKING)
        if (groundY < chunk.getMinY()) {return null;} //vanilla : empty column (void)
        BlockPos groundPos = new BlockPos(x, groundY, z);
        BlockState ground = chunk.getBlockState(groundPos);
        if (!ground.getFluidState().isEmpty()) {return null;} //vanilla : no liquid
        return ground.isValidSpawn(chunk, groundPos, EntityTypes.PLAYER) ? groundPos : null; //no solid ether, ghost boat block, glass... (vanilla only checks for a full top face, solid ether passes)
    }

    //good spawn chunk : criteria specific to Aerial Hell
    private static boolean isGoodSpawnChunk(ServerLevel level, ChunkAccess chunk, BlockPos centerGroundPos)
    {
        if (!chunk.getAllReferences().isEmpty()) {return false;} //no structure (dungeons, towers...) touching the chunk
        if (level.getBiome(centerGroundPos).is(AerialHellTags.Biomes.IS_SHADOW)) {return false;} //no shadow biome
        return hasEnoughGroundAround(chunk, centerGroundPos); //not on an island edge
    }

    //not on an island edge : true if most columns around the chunk center have ground at a similar height
    private static boolean hasEnoughGroundAround(ChunkAccess chunk, BlockPos centerGroundPos)
    {
        int checkedColumns = 0, groundColumns = 0;
        for (int dx = -GROUND_CHECK_RADIUS; dx <= GROUND_CHECK_RADIUS; dx += GROUND_CHECK_STEP)
        {
            for (int dz = -GROUND_CHECK_RADIUS; dz <= GROUND_CHECK_RADIUS; dz += GROUND_CHECK_STEP)
            {
                checkedColumns++;
                BlockPos groundPos = getGroundPos(chunk, centerGroundPos.getX() + dx, centerGroundPos.getZ() + dz);
                if (groundPos != null && Math.abs(groundPos.getY() - centerGroundPos.getY()) <= GROUND_MAX_HEIGHT_DIFFERENCE) {groundColumns++;}
            }
        }
        return groundColumns >= MIN_GROUND_RATIO * checkedColumns;
    }

    //spawn position on the final ground of the chunk, as close as possible to the chunk center, with enough space for a player
    @Nullable private static BlockPos findSpawnPosInFullChunk(ServerLevel level, ChunkPos chunkPos)
    {
        ChunkAccess chunk = level.getChunk(chunkPos.x(), chunkPos.z()); //vanilla : full chunk, with features (trees, solid ether clouds, lakes...)
        for (BlockPos column : BlockPos.spiralAround(chunkPos.getMiddleBlockPosition(0), SPAWN_MAX_DISTANCE_TO_CHUNK_CENTER, Direction.EAST, Direction.SOUTH)) //columns from the chunk center (vanilla : from the chunk corner)
        {
            BlockPos groundPos = getGroundPos(chunk, column.getX(), column.getZ());
            if (groundPos == null) {continue;}
            BlockPos spawnPos = groundPos.above();
            if (level.noCollision(null, EntityTypes.PLAYER.getDimensions().makeBoundingBox(Vec3.atBottomCenterOf(spawnPos)), true)) {return spawnPos;} //vanilla : enough space for a player (PlayerSpawnFinder, when the player joins)
        }
        return null;
    }
}
