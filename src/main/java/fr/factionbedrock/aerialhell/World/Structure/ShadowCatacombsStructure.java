package fr.factionbedrock.aerialhell.World.Structure;

import com.google.common.collect.ImmutableList;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellStructures;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.heightprovider.HeightProvider;
import net.minecraft.world.gen.structure.Structure;
import net.minecraft.world.gen.structure.StructureType;

import java.util.List;
import java.util.Optional;

public class ShadowCatacombsStructure extends AbstractAerialHellStructure
{
    public static final MapCodec<ShadowCatacombsStructure> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(ShadowCatacombsStructure.configCodecBuilder(instance),
                    StructurePool.REGISTRY_CODEC.fieldOf("start_pool").forGetter(structure -> structure.startPool),
                    Identifier.CODEC.optionalFieldOf("start_jigsaw_name").forGetter(structure -> structure.startJigsawName),
                    Codec.intRange(0, 30).fieldOf("size").forGetter(structure -> structure.size),
                    HeightProvider.CODEC.fieldOf("start_height").forGetter(structure -> structure.startHeight),
                    Heightmap.Type.CODEC.optionalFieldOf("project_start_to_heightmap").forGetter(structure -> structure.projectStartToHeightmap),
                    Codec.intRange(1, 128).fieldOf("max_distance_from_center").forGetter(structure -> structure.maxDistanceFromCenter)
            ).apply(instance, ShadowCatacombsStructure::new));
    
    public ShadowCatacombsStructure(Structure.Config config, RegistryEntry<StructurePool> startPool, Optional<Identifier> startJigsawName, int size, HeightProvider startHeight, Optional<Heightmap.Type> projectStartToHeightmap, int maxDistanceFromCenter)
    {
        super(config, startPool, startJigsawName, size, startHeight, projectStartToHeightmap, maxDistanceFromCenter, List.of()); //TODO : empty list ?
    }

    @Override protected boolean isStructureChunk(Structure.Context context)
    {
        int landHeight, checkDistance=30;
        int highGroundCount = 0;

        BlockPos centerOfChunk = context.chunkPos().getCenterAtY(0);
        List<BlockPos> posToCheck = ImmutableList.of(centerOfChunk, centerOfChunk.north(checkDistance), centerOfChunk.south(checkDistance), centerOfChunk.east(checkDistance), centerOfChunk.west(checkDistance));
        for (BlockPos pos : posToCheck)
        {
            landHeight = getTerrainHeight(context, pos.getX(), pos.getZ());
            /* biomeSource.getNoiseBiome(x,y,z) doesn't return the right biome. Do not use this method for biome check.Biome posBiome = biomeSource.getNoiseBiome(pos.getX(), pos.getY(), pos.getZ());
            if (!FeatureHelper.isShadowBiome(posBiome)) {notShadowBiomeCount++;}*/
            if (landHeight > 50) {highGroundCount++;}
        }
        return highGroundCount > 3; //&& notShadowBiomeCount <= 1;
    }

    @Override public StructureType<?> getType() {return AerialHellStructures.SHADOW_CATACOMBS_STRUCTURE;}
}