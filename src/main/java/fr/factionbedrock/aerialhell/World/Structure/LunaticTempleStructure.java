package fr.factionbedrock.aerialhell.World.Structure;

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Registry.AerialHellBiomes;
import fr.factionbedrock.aerialhell.Registry.AerialHellEntities;
import fr.factionbedrock.aerialhell.Util.FeatureHelper;
import fr.factionbedrock.aerialhell.Util.StructureHelper;
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

public class LunaticTempleStructure extends AbstractAerialHellStructure
{
	private static final List<MobSpawnSettings.SpawnerData> monstersSpawnList = ImmutableList.of(/*new MobSpawnInfo.Spawners(AerialHellEntities.CRYSTAL_SLIME.get(), 1, 3, 15), */new MobSpawnInfo.Spawners(AerialHellEntities.CRYSTAL_SPIDER.get(), 2, 5, 5));
	private static final List<MobSpawnSettings.SpawnerData> creaturesSpawnList = ImmutableList.of();
	
    public LunaticTempleStructure(Codec<NoneFeatureConfiguration> codec, PieceGeneratorSupplier<NoneFeatureConfiguration> pieceGeneratorSupplier)
    {
        super(codec, pieceGeneratorSupplier);
    }
    
    @Override public List<MobSpawnSettings.SpawnerData> getDefaultSpawnList() {return monstersSpawnList;}
    @Override public List<MobSpawnSettings.SpawnerData> getDefaultCreatureSpawnList() {return creaturesSpawnList;}

    @Override public IStartFactory<NoneFeatureConfiguration> getStartFactory()
    {
        return LunaticTempleStructure.Start::new;
    }

    @Override protected boolean func_230363_a_(ChunkGenerator chunkGenerator, BiomeProvider biomeSource, long seed, SharedSeedRandom chunkRandom, int chunkX, int chunkZ, Biome biome, ChunkPos chunkPos, NoFeatureConfig featureConfig) //isFeatureChunk (check if can spawn)
    {
        BlockPos centerOfChunk = new BlockPos(chunkX * 16, 140, chunkZ * 16);

        if (StructureHelper.hasGoldenNetherPrisonNearby(chunkGenerator, seed, chunkX, chunkZ, 6, true)) {return false;}
        /* biomeSource.getNoiseBiome(x,y,z) doesn't return the right biome. Do not use this method for biome check.
        List<BlockPos> checkShadowBiomePos = ImmutableList.of(centerOfChunk.north(20), centerOfChunk.south(20), centerOfChunk.east(20), centerOfChunk.west(20));
        for (BlockPos pos : checkShadowBiomePos)
        {
            Biome posBiome = biomeSource.getNoiseBiome(pos.getX(), pos.getY(), pos.getZ());
            if (FeatureHelper.isShadowBiome(posBiome)) {return false;}
        }*/
        return true;
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
                    new VillageConfig(() -> dynamicRegistryManager.getRegistry(Registry.JIGSAW_POOL_KEY).getOrDefault(new ResourceLocation(AerialHell.MODID, "lunatic_temple/boss_room_entrance_wall_pool")), 50),
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

            if (yPos.getY() <= 135 || yPos.getY() >= 190)
            {	//moveInsideHeights
                this.func_214626_a(this.rand, 135, 190);
            }
            else {
                this.func_214626_a(this.rand, yPos.getY(), yPos.getY());
            }
        }
    }
}