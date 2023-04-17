package fr.factionbedrock.aerialhell.World.Structure;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Registry.AerialHellBiomes;
import fr.factionbedrock.aerialhell.Registry.AerialHellEntities;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.jigsaw.JigsawManager;
import net.minecraft.world.gen.feature.structure.AbstractVillagePiece;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.VillageConfig;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.List;

public class ShadowCatacombsStructure extends AbstractAerialHellStructure
{
	private static final List<MobSpawnInfo.Spawners> monstersSpawnList = ImmutableList.of(new MobSpawnInfo.Spawners(AerialHellEntities.SHADOW_TROLL.get(), 2, 5, 5), new MobSpawnInfo.Spawners(AerialHellEntities.SHADOW_SPIDER.get(), 2, 5, 5));
	private static final List<MobSpawnInfo.Spawners> creaturesSpawnList = ImmutableList.of();

    public ShadowCatacombsStructure(Codec<NoFeatureConfig> codec)
    {
        super(codec);
    }
    
    @Override public List<MobSpawnInfo.Spawners> getDefaultSpawnList() {return monstersSpawnList;}
    @Override public List<MobSpawnInfo.Spawners> getDefaultCreatureSpawnList() {return creaturesSpawnList;}

    @Override
    public IStartFactory<NoFeatureConfig> getStartFactory() {return ShadowCatacombsStructure.Start::new;}

    @Override
    protected boolean func_230363_a_(ChunkGenerator chunkGenerator, BiomeProvider biomeSource, long seed, SharedSeedRandom chunkRandom, int chunkX, int chunkZ, Biome biome, ChunkPos chunkPos, NoFeatureConfig featureConfig) //isFeatureChunk (check if can spawn)
    {
        BlockPos centerOfChunk = new BlockPos(chunkX * 16, 80, chunkZ * 16);

        int landHeight = chunkGenerator.getHeight(centerOfChunk.getX(), centerOfChunk.getZ(), Heightmap.Type.WORLD_SURFACE_WG);
        int notShadowBiomeCount = 0;
        int highGroundCount = 0;

        if (landHeight > 80) {highGroundCount++;}
        List<BlockPos> checkShadowBiomePos = ImmutableList.of(centerOfChunk.north(20), centerOfChunk.south(20), centerOfChunk.east(20), centerOfChunk.west(20));
        for (BlockPos pos : checkShadowBiomePos)
        {
            landHeight = chunkGenerator.getHeight(pos.getX(), pos.getZ(), Heightmap.Type.WORLD_SURFACE_WG);
            Biome posBiome = biomeSource.getNoiseBiome(pos.getX(), pos.getY(), pos.getZ());
            if (landHeight > 80) {highGroundCount++;}
            if (!(posBiome.getRegistryName().equals(AerialHellBiomes.SHADOW_PLAIN.getLocation()) || posBiome.getRegistryName().equals(AerialHellBiomes.SHADOW_FOREST.getLocation()))) {notShadowBiomeCount++;}
        }

        return notShadowBiomeCount <= 1 && highGroundCount != 0;
    }

    public static class Start extends AbstractAerialHellStructure.Start
    {
        public Start(Structure<NoFeatureConfig> structureIn, int chunkX, int chunkZ, MutableBoundingBox mutableBoundingBox, int referenceIn, long seedIn)
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
                    new VillageConfig(() -> dynamicRegistryManager.getRegistry(Registry.JIGSAW_POOL_KEY).getOrDefault(new ResourceLocation(AerialHell.MODID, "shadow_catacombs/floors/first_basement_start_pattern")), 50),
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

            if (yPos.getY() <= 75 || yPos.getY() >= 95)
            {	//moveInsideHeights
                this.func_214626_a(this.rand, 75, 95);
            }
            else {
                this.func_214626_a(this.rand, yPos.getY(), yPos.getY());
            }
        }
    }
}