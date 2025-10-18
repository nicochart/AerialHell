package fr.factionbedrock.aerialhell.Util;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.StructureBlockEntity;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.status.ChunkStatus;

import java.util.List;

public class LevelHelper
{
    public static void listStructureBlockEntitiesInZone(List<StructureBlockEntity> listToFill, ServerLevel level, BlockPos center, int radius)
    {
        int chunkRadius = (radius >> 4) + 1;

        int centerChunkX = center.getX() >> 4;
        int centerChunkZ = center.getZ() >> 4;

        for (int cx = centerChunkX - chunkRadius; cx <= centerChunkX + chunkRadius; cx++)
        {
            for (int cz = centerChunkZ - chunkRadius; cz <= centerChunkZ + chunkRadius; cz++)
            {
                ChunkAccess chunk = level.getChunk(cx, cz, ChunkStatus.FULL, false);
                if (chunk == null) continue;

                for (BlockPos pos : chunk.getBlockEntitiesPos())
                {
                    if (pos.closerThan(center, radius))
                    {
                        BlockEntity blockentity = chunk.getBlockEntity(pos);
                        if (blockentity instanceof StructureBlockEntity structureBlockEntity)
                        {
                            listToFill.add(structureBlockEntity);
                        }
                    }
                }
            }
        }
    }
}
