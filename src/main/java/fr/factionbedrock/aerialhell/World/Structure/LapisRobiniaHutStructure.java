package fr.factionbedrock.aerialhell.World.Structure;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellStructures;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.heightproviders.HeightProvider;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

import java.util.Optional;

public class LapisRobiniaHutStructure extends AbstractClassicLittleStructure
{
    public static final Codec<LapisRobiniaHutStructure> CODEC = RecordCodecBuilder.<LapisRobiniaHutStructure>mapCodec(instance ->
            instance.group(LapisRobiniaHutStructure.settingsCodec(instance),
                    StructureTemplatePool.CODEC.fieldOf("start_pool").forGetter(structure -> structure.startPool),
                    ResourceLocation.CODEC.optionalFieldOf("start_jigsaw_name").forGetter(structure -> structure.startJigsawName),
                    Codec.intRange(MIN_STRUCTURE_SIZE, MAX_STRUCTURE_SIZE).fieldOf("size").forGetter(structure -> structure.size),
                    HeightProvider.CODEC.fieldOf("start_height").forGetter(structure -> structure.startHeight),
                    Heightmap.Types.CODEC.optionalFieldOf("project_start_to_heightmap").forGetter(structure -> structure.projectStartToHeightmap),
                    Codec.intRange(MIN_STRUCTURE_DISTANCE_FROM_CENTER, MAX_STRUCTURE_DISTANCE_FROM_CENTER).fieldOf("max_distance_from_center").forGetter(structure -> structure.maxDistanceFromCenter)
            ).apply(instance, LapisRobiniaHutStructure::new)).codec();

    public LapisRobiniaHutStructure(Structure.StructureSettings config, Holder<StructureTemplatePool> startPool, Optional<ResourceLocation> startJigsawName, int size, HeightProvider startHeight, Optional<Heightmap.Types> projectStartToHeightmap, int maxDistanceFromCenter)
    {
        super(config, startPool, startJigsawName, size, startHeight, projectStartToHeightmap, maxDistanceFromCenter);
    }

    @Override public StructureType<?> type() {return AerialHellStructures.LAPIS_ROBINIA_HUT_STRUCTURE.get();}

    @Override protected int getMinY() {return 50;}
    @Override protected int getMaxY() {return 260;}
}