package fr.factionbedrock.aerialhell.World.Structure;

import java.util.List;
import java.util.Optional;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellStructures;
import fr.factionbedrock.aerialhell.Util.StructureHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.heightproviders.HeightProvider;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

public class LunaticTempleStructure extends AbstractAerialHellStructure
{
    public static final MapCodec<LunaticTempleStructure> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(LunaticTempleStructure.settingsCodec(instance),
                    StructureTemplatePool.CODEC.fieldOf("start_pool").forGetter(structure -> structure.startPool),
                    ResourceLocation.CODEC.optionalFieldOf("start_jigsaw_name").forGetter(structure -> structure.startJigsawName),
                    Codec.intRange(0, 30).fieldOf("size").forGetter(structure -> structure.size),
                    HeightProvider.CODEC.fieldOf("start_height").forGetter(structure -> structure.startHeight),
                    Heightmap.Types.CODEC.optionalFieldOf("project_start_to_heightmap").forGetter(structure -> structure.projectStartToHeightmap),
                    Codec.intRange(1, 128).fieldOf("max_distance_from_center").forGetter(structure -> structure.maxDistanceFromCenter)
            ).apply(instance, LunaticTempleStructure::new));
    
    public LunaticTempleStructure(Structure.StructureSettings config, Holder<StructureTemplatePool> startPool, Optional<ResourceLocation> startJigsawName, int size, HeightProvider startHeight, Optional<Heightmap.Types> projectStartToHeightmap, int maxDistanceFromCenter)
    {
        super(config, startPool, startJigsawName, size, startHeight, projectStartToHeightmap, maxDistanceFromCenter, List.of()); //TODO : empty list ?
    }

    @Override protected boolean isStructureChunk(Structure.GenerationContext context)
    {
        ChunkGenerator chunkGenerator = context.chunkGenerator();
        ChunkPos chunkpos = context.chunkPos();
        LevelHeightAccessor level = context.heightAccessor();
        long seed = context.seed();
        BlockPos centerOfChunk = chunkpos.getMiddleBlockPosition(0);

        if (StructureHelper.hasGoldenNetherPrisonNearby(chunkGenerator, seed, chunkpos.x, chunkpos.z, 6, true)) {return false;}
        /* biomeSource.getNoiseBiome(x,y,z) doesn't return the right biome. Do not use this method for biome check.
        List<BlockPos> checkShadowBiomePos = ImmutableList.of(centerOfChunk.north(20), centerOfChunk.south(20), centerOfChunk.east(20), centerOfChunk.west(20));
        for (BlockPos pos : checkShadowBiomePos)
        {
            Biome posBiome = context.biomeSource().getNoiseBiome(pos.getX(), pos.getY(), pos.getZ());
            if (FeatureHelper.isShadowBiome(posBiome)) {return false;}
        }*/
        return true;
    }

    @Override public StructureType<?> type() {return AerialHellStructures.LUNATIC_TEMPLE_STRUCTURE.get();}
}