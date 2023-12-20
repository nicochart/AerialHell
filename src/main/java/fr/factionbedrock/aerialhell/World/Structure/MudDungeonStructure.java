package fr.factionbedrock.aerialhell.World.Structure;

import java.util.List;
import java.util.Optional;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellStructures;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.heightproviders.HeightProvider;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

public class MudDungeonStructure extends AbstractAerialHellStructure
{
    private final static int MIN_GEN_HEIGHT = 20, MAX_GEN_HEIGHT = 50;

    public static final Codec<MudDungeonStructure> CODEC = RecordCodecBuilder.<MudDungeonStructure>mapCodec(instance ->
            instance.group(MudDungeonStructure.settingsCodec(instance),
                    StructureTemplatePool.CODEC.fieldOf("start_pool").forGetter(structure -> structure.startPool),
                    ResourceLocation.CODEC.optionalFieldOf("start_jigsaw_name").forGetter(structure -> structure.startJigsawName),
                    Codec.intRange(MIN_STRUCTURE_SIZE, MAX_STRUCTURE_SIZE).fieldOf("size").forGetter(structure -> structure.size),
                    HeightProvider.CODEC.fieldOf("start_height").forGetter(structure -> structure.startHeight),
                    Heightmap.Types.CODEC.optionalFieldOf("project_start_to_heightmap").forGetter(structure -> structure.projectStartToHeightmap),
                    Codec.intRange(MIN_STRUCTURE_DISTANCE_FROM_CENTER, MAX_STRUCTURE_DISTANCE_FROM_CENTER).fieldOf("max_distance_from_center").forGetter(structure -> structure.maxDistanceFromCenter)
            ).apply(instance, MudDungeonStructure::new)).codec();

    public MudDungeonStructure(Structure.StructureSettings config, Holder<StructureTemplatePool> startPool, Optional<ResourceLocation> startJigsawName, int size, HeightProvider startHeight, Optional<Heightmap.Types> projectStartToHeightmap, int maxDistanceFromCenter)
    {
        super(config, startPool, startJigsawName, size, startHeight, projectStartToHeightmap, maxDistanceFromCenter, List.of()); //TODO : empty list ?
    }

    @Override protected boolean isStructureChunk(Structure.GenerationContext context)
    {
        int x=context.chunkPos().getMaxBlockX(), z=context.chunkPos().getMaxBlockZ();
        if (getTerrainHeight(context, x, z) < MAX_GEN_HEIGHT) {return false;}

        NoiseColumn column = context.chunkGenerator().getBaseColumn(x, z, context.heightAccessor(), context.randomState());
        return columnHasPercentOfNonAirBlocks(column, 0.25F);
    }

    private static boolean columnHasPercentOfNonAirBlocks(NoiseColumn column, float part)
    {
        int count = 0;
        for (int y=MIN_GEN_HEIGHT; y<MAX_GEN_HEIGHT; y++)
        {
            if (!column.getBlock(y).isAir())
            {
                count++;
                if (count > (MAX_GEN_HEIGHT - MIN_GEN_HEIGHT) * part) {return true;}
            }
        }
        return false;
    }

    @Override public StructureType<?> type() {return AerialHellStructures.MUD_DUNGEON_STRUCTURE.get();}
}