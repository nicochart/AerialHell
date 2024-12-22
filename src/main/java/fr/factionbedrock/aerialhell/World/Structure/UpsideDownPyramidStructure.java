package fr.factionbedrock.aerialhell.World.Structure;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellStructures;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.HeightContext;
import net.minecraft.world.gen.chunk.VerticalBlockSample;
import net.minecraft.world.gen.heightprovider.HeightProvider;
import net.minecraft.world.gen.structure.Structure;
import net.minecraft.world.gen.structure.StructureType;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class UpsideDownPyramidStructure extends AbstractAerialHellStructure
{
    private static final int MIN_GEN_HEIGHT = 30;

    public static final MapCodec<UpsideDownPyramidStructure> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(UpsideDownPyramidStructure.configCodecBuilder(instance),
                    StructurePool.REGISTRY_CODEC.fieldOf("start_pool").forGetter(structure -> structure.startPool),
                    Identifier.CODEC.optionalFieldOf("start_jigsaw_name").forGetter(structure -> structure.startJigsawName),
                    Codec.intRange(0, 30).fieldOf("size").forGetter(structure -> structure.size),
                    HeightProvider.CODEC.fieldOf("start_height").forGetter(structure -> structure.startHeight),
                    Heightmap.Type.CODEC.optionalFieldOf("project_start_to_heightmap").forGetter(structure -> structure.projectStartToHeightmap),
                    Codec.intRange(1, 128).fieldOf("max_distance_from_center").forGetter(structure -> structure.maxDistanceFromCenter)
            ).apply(instance, UpsideDownPyramidStructure::new));
    
    public UpsideDownPyramidStructure(Structure.Config config, RegistryEntry<StructurePool> startPool, Optional<Identifier> startJigsawName, int size, HeightProvider startHeight, Optional<Heightmap.Type> projectStartToHeightmap, int maxDistanceFromCenter)
    {
        super(config, startPool, startJigsawName, size, startHeight, projectStartToHeightmap, maxDistanceFromCenter, List.of());
    }

    @Override public StructureType<?> getType() {return AerialHellStructures.UPSIDE_DOWN_PYRAMID;}

    protected static int getGenerationHeight(Structure.Context context) //WIP
    {
        ChunkPos chunkPos = context.chunkPos();

        VerticalBlockSample minColumn = context.chunkGenerator().getColumnSample(chunkPos.getStartX(), chunkPos.getStartZ(), context.world(), context.noiseConfig());
        VerticalBlockSample maxColumn = context.chunkGenerator().getColumnSample(chunkPos.getEndX(), chunkPos.getEndZ(), context.world(), context.noiseConfig());
        VerticalBlockSample middleColumn = context.chunkGenerator().getColumnSample(chunkPos.getCenterX(), chunkPos.getCenterZ(), context.world(), context.noiseConfig());

        int minHeight = getTerrainHeight(context, ChunkCoordinateType.CHUNK_MIN_COORDINATE); //not actual min, just min chunk coordinates
        int maxHeight = getTerrainHeight(context, ChunkCoordinateType.CHUNK_MAX_COORDINATE); //not actual max, just max chunk coordinates
        int middleHeight = getTerrainHeight(context, ChunkCoordinateType.CHUNK_MIDDLE_COORDINATE);

        int y = middleHeight;
        while (y > MIN_GEN_HEIGHT)
        {
            if (middleColumn.getState(y).isAir()) {return y;}
            y--;
        }
        return -1;
    }

    @Override protected boolean isStructureChunk(Structure.Context context) {return getTerrainHeight(context) > MIN_GEN_HEIGHT;}

    @Override @Nullable protected BlockPos findStructureCenter(Structure.Context context)
    {
        int sampledStartHeight = this.startHeight.get(context.random(), new HeightContext(context.chunkGenerator(), context.world()));

        int startY = this.projectStartToHeightmap.isPresent() ? getGenerationHeight(context) + sampledStartHeight : sampledStartHeight;
        if (startY < MIN_GEN_HEIGHT) {return null;}

        return new BlockPos(context.chunkPos().getCenterX(), startY, context.chunkPos().getCenterZ());
    }
}