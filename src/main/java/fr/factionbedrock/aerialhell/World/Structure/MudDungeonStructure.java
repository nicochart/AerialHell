package fr.factionbedrock.aerialhell.World.Structure;

import java.util.List;
import java.util.Optional;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellStructures;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.util.Identifier;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.chunk.VerticalBlockSample;
import net.minecraft.world.gen.heightprovider.HeightProvider;
import net.minecraft.world.gen.structure.Structure;
import net.minecraft.world.gen.structure.StructureType;

public class MudDungeonStructure extends AbstractAerialHellStructure
{
    private final static int MIN_GEN_HEIGHT = 20, MAX_GEN_HEIGHT = 50;

    public static final MapCodec<MudDungeonStructure> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(MudDungeonStructure.configCodecBuilder(instance),
                    StructurePool.REGISTRY_CODEC.fieldOf("start_pool").forGetter(structure -> structure.startPool),
                    Identifier.CODEC.optionalFieldOf("start_jigsaw_name").forGetter(structure -> structure.startJigsawName),
                    Codec.intRange(0, 30).fieldOf("size").forGetter(structure -> structure.size),
                    HeightProvider.CODEC.fieldOf("start_height").forGetter(structure -> structure.startHeight),
                    Heightmap.Type.CODEC.optionalFieldOf("project_start_to_heightmap").forGetter(structure -> structure.projectStartToHeightmap),
                    Codec.intRange(1, 128).fieldOf("max_distance_from_center").forGetter(structure -> structure.maxDistanceFromCenter)
            ).apply(instance, MudDungeonStructure::new));

    public MudDungeonStructure(Structure.Config config, RegistryEntry<StructurePool> startPool, Optional<Identifier> startJigsawName, int size, HeightProvider startHeight, Optional<Heightmap.Type> projectStartToHeightmap, int maxDistanceFromCenter)
    {
        super(config, startPool, startJigsawName, size, startHeight, projectStartToHeightmap, maxDistanceFromCenter, List.of()); //TODO : empty list ?
    }

    @Override protected boolean isStructureChunk(Structure.Context context)
    {
        int x=context.chunkPos().getEndX(), z=context.chunkPos().getEndZ();
        if (getTerrainHeight(context, x, z) < MAX_GEN_HEIGHT) {return false;}

        VerticalBlockSample column = context.chunkGenerator().getColumnSample(x, z, context.world(), context.noiseConfig());
        return columnHasPercentOfNonAirBlocks(column, 0.25F);
    }

    private static boolean columnHasPercentOfNonAirBlocks(VerticalBlockSample column, float part)
    {
        int count = 0;
        for (int y=MIN_GEN_HEIGHT; y<MAX_GEN_HEIGHT; y++)
        {
            if (!column.getState(y).isAir())
            {
                count++;
                if (count > (MAX_GEN_HEIGHT - MIN_GEN_HEIGHT) * part) {return true;}
            }
        }
        return false;
    }

    @Override public StructureType<?> getType() {return AerialHellStructures.MUD_DUNGEON_STRUCTURE;}
}