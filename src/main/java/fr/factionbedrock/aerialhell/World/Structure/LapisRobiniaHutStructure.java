package fr.factionbedrock.aerialhell.World.Structure;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import fr.factionbedrock.aerialhell.AerialHell;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.core.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;

import java.util.List;

public class LapisRobiniaHutStructure extends AbstractClassicLittleStructure
{
    public LapisRobiniaHutStructure(Codec<NoneFeatureConfiguration> codec, PieceGeneratorSupplier<NoneFeatureConfiguration> pieceGeneratorSupplier) {super(codec, pieceGeneratorSupplier);}

    @Override protected boolean func_230363_a_(ChunkGenerator chunkGenerator, BiomeProvider biomeSource, long seed, SharedSeedRandom chunkRandom, int chunkX, int chunkZ, Biome biome, ChunkPos chunkPos, NoFeatureConfig featureConfig) //isFeatureChunk (check if can spawn)
    {
        BlockPos centerOfStructure = new BlockPos(chunkX * 16 - 6, 80, chunkZ * 16 - 6);
        int landHeightAtCenter = chunkGenerator.getHeight(centerOfStructure.getX(), centerOfStructure.getZ(), Heightmap.Type.WORLD_SURFACE_WG), checkDistance=12;;
        if (landHeightAtCenter <= AbstractClassicLittleStructure.Start.getMinY() || landHeightAtCenter >= AbstractClassicLittleStructure.Start.getMaxY()) {return false;}

        int landHeightAtPos;

        List<BlockPos> posToCheck = ImmutableList.of(centerOfStructure.north(checkDistance), centerOfStructure.south(checkDistance), centerOfStructure.east(checkDistance), centerOfStructure.west(checkDistance));
        for (BlockPos pos : posToCheck)
        {
            landHeightAtPos = chunkGenerator.getHeight(pos.getX(), pos.getZ(), Heightmap.Type.WORLD_SURFACE_WG);
            if (Math.abs(landHeightAtCenter - landHeightAtPos) > 4) {return false;}
        }
        return super.func_230363_a_(chunkGenerator, biomeSource, seed, chunkRandom, chunkX, chunkZ, biome, chunkPos, featureConfig);
    }

    @Override public IStartFactory<NoneFeatureConfiguration> getStartFactory() {return LapisRobiniaHutStructure.Start::new;}
    
    public static class Start extends AbstractClassicLittleStructure.Start
    {

        public Start(Structure<NoneFeatureConfiguration> structureIn, int chunkX, int chunkZ, MutableBoundingBox mutableBoundingBox, int referenceIn, long seedIn)
        {
            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
        }

        @Override protected ResourceLocation getStartPool() {return new ResourceLocation(AerialHell.MODID, "lapis_robinia_hut/lapis_robinia_hut");}
        @Override protected int getSize() {return 30;}
        @Override protected int getYOffsetForPlacement() {return -1;}
    }
}