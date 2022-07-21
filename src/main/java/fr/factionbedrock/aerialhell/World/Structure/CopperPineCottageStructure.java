package fr.factionbedrock.aerialhell.World.Structure;

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Registry.AerialHellEntities;
import fr.factionbedrock.aerialhell.Registry.AerialHellStructures;
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
import net.minecraft.world.gen.settings.StructureSeparationSettings;

public class CopperPineCottageStructure extends AbstractAerialHellStructure
{
	private static final List<MobSpawnInfo.Spawners> monstersSpawnList = ImmutableList.of(new MobSpawnInfo.Spawners(AerialHellEntities.EVIL_COW.get(), 2, 3, 5));
	private static final List<MobSpawnInfo.Spawners> creaturesSpawnList = ImmutableList.of();
	
    public CopperPineCottageStructure(Codec<NoFeatureConfig> codec)
    {
        super(codec);
    }
    
    @Override public List<MobSpawnInfo.Spawners> getDefaultSpawnList() {return monstersSpawnList;}
    @Override public List<MobSpawnInfo.Spawners> getDefaultCreatureSpawnList() {return creaturesSpawnList;}

    @Override
    public IStartFactory<NoFeatureConfig> getStartFactory()
    {
        return CopperPineCottageStructure.Start::new;
    }
    
    @Override
    protected boolean func_230363_a_(ChunkGenerator chunkGenerator, BiomeProvider biomeSource, long seed, SharedSeedRandom chunkRandom, int chunkX, int chunkZ, Biome biome, ChunkPos chunkPos, NoFeatureConfig featureConfig) //isFeatureChunk (check if can spawn)
    {
    	//cannot spawn next to another structure
    	
    	if (hasDungeonNearby(chunkGenerator, seed, chunkRandom, chunkX, chunkZ, 6)) {return false;}
    	
        BlockPos centerOfChunk = new BlockPos(chunkX * 16, 0, chunkZ * 16);
        
        int landHeight = chunkGenerator.getHeight(centerOfChunk.getX(), centerOfChunk.getZ(), Heightmap.Type.WORLD_SURFACE_WG);
        return landHeight > 50 && landHeight < 190;
    }
    
    private boolean hasDungeonNearby(ChunkGenerator chunkGenerator, long seed, SharedSeedRandom chunkRandom, int chunkX, int chunkZ, int checkRadius)
    {
    	//.func_235957_b_() = .getSettings() ; .func_236197_a_() = .getConfig()
    	StructureSeparationSettings MudDungeonConfig = chunkGenerator.func_235957_b_().func_236197_a_(AerialHellStructures.MUD_DUNGEON_STRUCTURE.get());
    	StructureSeparationSettings LunaticTempleConfig = chunkGenerator.func_235957_b_().func_236197_a_(AerialHellStructures.LUNATIC_TEMPLE_STRUCTURE.get());
    	StructureSeparationSettings GoldenNetherPrisonConfig = chunkGenerator.func_235957_b_().func_236197_a_(AerialHellStructures.GOLDEN_NETHER_PRISON_STRUCTURE.get());
    	if (MudDungeonConfig == null && LunaticTempleConfig == null && GoldenNetherPrisonConfig == null) {return false;}
    	else
    	{
    		for (int cx = chunkX - checkRadius; cx <= chunkZ + checkRadius; cx++)
        	{
                for (int cz = chunkZ - checkRadius; cz <= chunkZ + checkRadius; cz++)
                {
                	ChunkPos chunkPos;
                	if (MudDungeonConfig != null)
                	{
                		chunkPos = AerialHellStructures.MUD_DUNGEON_STRUCTURE.get().getChunkPosForStructure(MudDungeonConfig, seed, chunkRandom, cx, cz);
                        if (cx == chunkPos.x && cz == chunkPos.z) {return true;}
                	}
                	if (LunaticTempleConfig != null)
                	{
                		chunkPos = AerialHellStructures.LUNATIC_TEMPLE_STRUCTURE.get().getChunkPosForStructure(LunaticTempleConfig, seed, chunkRandom, cx, cz);
                        if (cx == chunkPos.x && cz == chunkPos.z) {return true;}
                	}
                	if (GoldenNetherPrisonConfig != null)
                	{
                		chunkPos = AerialHellStructures.GOLDEN_NETHER_PRISON_STRUCTURE.get().getChunkPosForStructure(GoldenNetherPrisonConfig, seed, chunkRandom, cx, cz);
                        if (cx == chunkPos.x && cz == chunkPos.z) {return true;}
                	}
                }
        	}
    	}
    	return false;
    }
    
    public static class Start extends AbstractAerialHellStructure.Start
    {
    	//This structure uses Stellar Bricks Tower "ground" (support) as surrounding land
        public Start(Structure<NoFeatureConfig> structureIn, int chunkX, int chunkZ, MutableBoundingBox mutableBoundingBox, int referenceIn, long seedIn)
        {
            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
        }

        @Override //generatePieces
        public void func_230364_a_(DynamicRegistries dynamicRegistryManager, ChunkGenerator chunkGenerator, TemplateManager structureManager, int x, int z, Biome biome, NoFeatureConfig NoFeatureConfig)
        {
            BlockPos blockPos = new BlockPos(x * 16, 0, z * 16);

            JigsawManager.func_242837_a //addPieces
            (
                    dynamicRegistryManager,
                    new VillageConfig(() -> dynamicRegistryManager.getRegistry(Registry.JIGSAW_POOL_KEY).getOrDefault(new ResourceLocation(AerialHell.MODID, "copper_pine_cottage/copper_pine_cottage_pool")), 50),
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
            
            int landHeight = chunkGenerator.getHeight(blockPos.getX(), blockPos.getZ(), Heightmap.Type.WORLD_SURFACE_WG);
            
            if (landHeight <= 50 || landHeight >= 190)
            {	//moveInsideHeights
                this.func_214626_a(this.rand, 50, 190);
            }
            else
            {
                this.func_214626_a(this.rand, landHeight - 10, landHeight- 10);
            }
        }
    }
}