package fr.factionbedrock.aerialhell.World.Structure;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellStructures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.WorldGenerationContext;
import net.minecraft.world.level.levelgen.heightproviders.HeightProvider;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

public class UpsideDownPyramidStructure extends AbstractAerialHellStructure
{
    private static final int MIN_GEN_HEIGHT = 30;

    public static final Codec<UpsideDownPyramidStructure> CODEC = RecordCodecBuilder.<UpsideDownPyramidStructure>mapCodec(instance ->
            instance.group(UpsideDownPyramidStructure.settingsCodec(instance),
                    StructureTemplatePool.CODEC.fieldOf("start_pool").forGetter(structure -> structure.startPool),
                    ResourceLocation.CODEC.optionalFieldOf("start_jigsaw_name").forGetter(structure -> structure.startJigsawName),
                    Codec.intRange(MIN_STRUCTURE_SIZE, MAX_STRUCTURE_SIZE).fieldOf("size").forGetter(structure -> structure.size),
                    HeightProvider.CODEC.fieldOf("start_height").forGetter(structure -> structure.startHeight),
                    Heightmap.Types.CODEC.optionalFieldOf("project_start_to_heightmap").forGetter(structure -> structure.projectStartToHeightmap),
                    Codec.intRange(MIN_STRUCTURE_DISTANCE_FROM_CENTER, MAX_STRUCTURE_DISTANCE_FROM_CENTER).fieldOf("max_distance_from_center").forGetter(structure -> structure.maxDistanceFromCenter)
            ).apply(instance, UpsideDownPyramidStructure::new)).codec();

    public UpsideDownPyramidStructure(StructureSettings config, Holder<StructureTemplatePool> startPool, Optional<ResourceLocation> startJigsawName, int size, HeightProvider startHeight, Optional<Heightmap.Types> projectStartToHeightmap, int maxDistanceFromCenter)
    {
        super(config, startPool, startJigsawName, size, startHeight, projectStartToHeightmap, maxDistanceFromCenter, List.of());
    }

    @Override public StructureType<?> type() {return AerialHellStructures.UPSIDE_DOWN_PYRAMID.get();}

    protected static int getGenerationHeight(Structure.GenerationContext context) //WIP
    {
        ChunkPos chunkPos = context.chunkPos();

        NoiseColumn minColumn = context.chunkGenerator().getBaseColumn(chunkPos.getMinBlockX(), chunkPos.getMinBlockZ(), context.heightAccessor(), context.randomState());
        NoiseColumn maxColumn = context.chunkGenerator().getBaseColumn(chunkPos.getMaxBlockX(), chunkPos.getMaxBlockZ(), context.heightAccessor(), context.randomState());
        NoiseColumn middleColumn = context.chunkGenerator().getBaseColumn(chunkPos.getMiddleBlockX(), chunkPos.getMiddleBlockZ(), context.heightAccessor(), context.randomState());

        int minHeight = getTerrainHeight(context, ChunkCoordinateType.CHUNK_MIN_COORDINATE); //not actual min, just min chunk coordinates
        int maxHeight = getTerrainHeight(context, ChunkCoordinateType.CHUNK_MAX_COORDINATE); //not actual max, just max chunk coordinates
        int middleHeight = getTerrainHeight(context, ChunkCoordinateType.CHUNK_MIDDLE_COORDINATE);

        int y = middleHeight;
        while (y > MIN_GEN_HEIGHT)
        {
            if (middleColumn.getBlock(y).isAir()) {return y;}
            y--;
        }
        return -1;
    }

    @Override protected boolean isStructureChunk(GenerationContext context) {return getTerrainHeight(context) > MIN_GEN_HEIGHT;}

    @Override @Nullable protected BlockPos findStructureCenter(GenerationContext context)
    {
        int sampledStartHeight = this.startHeight.sample(context.random(), new WorldGenerationContext(context.chunkGenerator(), context.heightAccessor()));

        int startY = this.projectStartToHeightmap.isPresent() ? getGenerationHeight(context) + sampledStartHeight : sampledStartHeight;
        if (startY < MIN_GEN_HEIGHT) {return null;}

        return new BlockPos(context.chunkPos().getMiddleBlockX(), startY, context.chunkPos().getMiddleBlockZ());
    }
}