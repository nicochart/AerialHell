package fr.factionbedrock.aerialhell.World.Structure;

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Registry.AerialHellEntities;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.core.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.gen.feature.jigsaw.JigsawManager;
import net.minecraft.world.gen.feature.structure.AbstractVillagePiece;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.VillageConfig;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;

public class GoldenNetherPrisonStructure extends AbstractAerialHellStructure
{
	private static final List<MobSpawnSettings.SpawnerData> monstersSpawnList = ImmutableList.of(new MobSpawnInfo.Spawners(AerialHellEntities.TORN_SPIRIT.get(), 2, 3, 15));
	private static final List<MobSpawnSettings.SpawnerData> creaturesSpawnList = ImmutableList.of();
	
    public GoldenNetherPrisonStructure(Codec<NoneFeatureConfiguration> codec, PieceGeneratorSupplier<NoneFeatureConfiguration> pieceGeneratorSupplier)
    {
        super(codec, pieceGeneratorSupplier);
    }
    
    @Override public List<MobSpawnSettings.SpawnerData> getDefaultSpawnList() {return monstersSpawnList;}
    @Override public List<MobSpawnSettings.SpawnerData> getDefaultCreatureSpawnList() {return creaturesSpawnList;}

    @Override
    public IStartFactory<NoneFeatureConfiguration> getStartFactory()
    {
        return GoldenNetherPrisonStructure.Start::new;
    }
    
    @Override
    protected boolean func_230363_a_(ChunkGenerator chunkGenerator, BiomeProvider biomeSource, long seed, SharedSeedRandom chunkRandom, int chunkX, int chunkZ, Biome biome, ChunkPos chunkPos, NoFeatureConfig featureConfig) //isFeatureChunk (check if can spawn)
    {
        BlockPos centerOfChunk = new BlockPos(chunkX * 16, 0, chunkZ * 16);
        
        int landHeight = chunkGenerator.getHeight(centerOfChunk.getX(), centerOfChunk.getZ(), Heightmap.Type.WORLD_SURFACE_WG);
        return landHeight < 120;
    }
    
    public static class Start extends AbstractAerialHellStructure.Start
    {

        public Start(Structure<NoneFeatureConfiguration> structureIn, int chunkX, int chunkZ, MutableBoundingBox mutableBoundingBox, int referenceIn, long seedIn)
        {
            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
        }

        @Override //generatePieces
        public void func_230364_a_(DynamicRegistries dynamicRegistryManager, ChunkGenerator chunkGenerator, TemplateManager structureManager, int x, int z, Biome biome, NoFeatureConfig NoFeatureConfig)
        {
            BlockPos yPos = getHighestLand(chunkGenerator);
            BlockPos blockPos = new BlockPos(x * 16, 0, z * 16);

            JigsawManager.func_242837_a //addPieces
            (
                    dynamicRegistryManager,
                    new VillageConfig(() -> dynamicRegistryManager.getRegistry(Registry.JIGSAW_POOL_KEY).getOrDefault(new ResourceLocation(AerialHell.MODID, "golden_nether_prison/boss_room_center_center_pool")), 50),
                    AbstractVillagePiece::new,
                    chunkGenerator,
                    structureManager,
                    blockPos,
                    this.components,
                    this.rand,
                    true,
                    false
            );

            this.recalculateStructureSize();

            if (yPos.getY() <= 90 || yPos.getY() >= 155)
            {	//moveInsideHeights
                this.func_214626_a(this.rand, 90, 155);
            }
            else {
                this.func_214626_a(this.rand, yPos.getY(), yPos.getY());
            }
        }
    }
}