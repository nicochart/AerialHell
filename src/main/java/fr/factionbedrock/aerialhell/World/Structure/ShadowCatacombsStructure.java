package fr.factionbedrock.aerialhell.World.Structure;

import com.google.common.collect.ImmutableList;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellStructures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.heightproviders.HeightProvider;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

import java.util.List;
import java.util.Optional;

public class ShadowCatacombsStructure extends AbstractAerialHellStructure
{
    public static final Codec<ShadowCatacombsStructure> CODEC = RecordCodecBuilder.<ShadowCatacombsStructure>mapCodec(instance ->
            instance.group(ShadowCatacombsStructure.settingsCodec(instance),
                    StructureTemplatePool.CODEC.fieldOf("start_pool").forGetter(structure -> structure.startPool),
                    ResourceLocation.CODEC.optionalFieldOf("start_jigsaw_name").forGetter(structure -> structure.startJigsawName),
                    Codec.intRange(MIN_STRUCTURE_SIZE, MAX_STRUCTURE_SIZE).fieldOf("size").forGetter(structure -> structure.size),
                    HeightProvider.CODEC.fieldOf("start_height").forGetter(structure -> structure.startHeight),
                    Heightmap.Types.CODEC.optionalFieldOf("project_start_to_heightmap").forGetter(structure -> structure.projectStartToHeightmap),
                    Codec.intRange(MIN_STRUCTURE_DISTANCE_FROM_CENTER, MAX_STRUCTURE_DISTANCE_FROM_CENTER).fieldOf("max_distance_from_center").forGetter(structure -> structure.maxDistanceFromCenter)
            ).apply(instance, ShadowCatacombsStructure::new)).codec();

    public ShadowCatacombsStructure(Structure.StructureSettings config, Holder<StructureTemplatePool> startPool, Optional<ResourceLocation> startJigsawName, int size, HeightProvider startHeight, Optional<Heightmap.Types> projectStartToHeightmap, int maxDistanceFromCenter)
    {
        super(config, startPool, startJigsawName, size, startHeight, projectStartToHeightmap, maxDistanceFromCenter);
    }

    @Override protected boolean isStructureChunk(Structure.GenerationContext context)
    {
        int landHeight, checkDistance=30;
        int highGroundCount = 0;

        BlockPos centerOfChunk = context.chunkPos().getMiddleBlockPosition(0);
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

    @Override public StructureType<?> type() {return AerialHellStructures.SHADOW_CATACOMBS_STRUCTURE.get();}
}