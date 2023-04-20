package fr.factionbedrock.aerialhell.World.Structure;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Util.StructureHelper;
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

public abstract class AbstractClassicLittleStructure extends AbstractAerialHellStructure
{
	private static final List<MobSpawnInfo.Spawners> monstersSpawnList = ImmutableList.of();
	private static final List<MobSpawnInfo.Spawners> creaturesSpawnList = ImmutableList.of();

    public AbstractClassicLittleStructure(Codec<NoFeatureConfig> codec) {super(codec);}
    
    @Override public List<MobSpawnInfo.Spawners> getDefaultSpawnList() {return monstersSpawnList;}
    @Override public List<MobSpawnInfo.Spawners> getDefaultCreatureSpawnList() {return creaturesSpawnList;}
    
    @Override protected boolean func_230363_a_(ChunkGenerator chunkGenerator, BiomeProvider biomeSource, long seed, SharedSeedRandom chunkRandom, int chunkX, int chunkZ, Biome biome, ChunkPos chunkPos, NoFeatureConfig featureConfig) //isFeatureChunk (check if can spawn)
    {
    	//cannot spawn next to another structure
    	if (StructureHelper.hasDungeonNearby(chunkGenerator, seed, chunkRandom, chunkX, chunkZ, 6)) {return false;}
    	
        BlockPos centerOfChunk = new BlockPos(chunkX * 16, 0, chunkZ * 16);
        
        int landHeight = chunkGenerator.getHeight(centerOfChunk.getX(), centerOfChunk.getZ(), Heightmap.Type.WORLD_SURFACE_WG);
        return landHeight > Start.getMinY() && landHeight < Start.getMaxY();
    }
    
    public abstract static class Start extends AbstractAerialHellStructure.Start
    {

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
                    new VillageConfig(() -> dynamicRegistryManager.getRegistry(Registry.JIGSAW_POOL_KEY).getOrDefault(this.getStartPool()), this.getSize()),
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

            if (landHeight <= getMinY() || landHeight >= getMaxY())
            {	//moveInsideHeights - this case should never happen (because isFeatureChunk verifies that minY < landHeight < maxY)
                this.func_214626_a(this.rand, getMinY(), getMaxY());
            }
            else
            {
                this.func_214626_a(this.rand, landHeight + getYOffsetForPlacement(), landHeight + getYOffsetForPlacement());
            }
        }

        protected abstract ResourceLocation getStartPool();
        protected abstract int getSize();
        protected abstract int getYOffsetForPlacement();
        protected static int getMinY() {return 50;}
        protected static int getMaxY() {return 190;}
    }
}