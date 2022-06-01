package fr.factionbedrock.aerialhell.World.Structure;

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Registry.AerialHellEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.jigsaw.JigsawManager;
import net.minecraft.world.gen.feature.structure.AbstractVillagePiece;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.VillageConfig;
import net.minecraft.world.gen.feature.template.TemplateManager;

public class LunaticTempleStructure extends AbstractAerialHellStructure
{
	private static final List<MobSpawnInfo.Spawners> spawnList = ImmutableList.of(new MobSpawnInfo.Spawners(AerialHellEntities.CRYSTAL_SLIME.get(), 1, 3, 15), new MobSpawnInfo.Spawners(AerialHellEntities.CRYSTAL_SPIDER.get(), 2, 5, 5));
	
    public LunaticTempleStructure(Codec<NoFeatureConfig> codec)
    {
        super(codec);
    }
    
    @Override
    public List<MobSpawnInfo.Spawners> getSpawnList()
    {
        return spawnList;
    }

    @Override
    public IStartFactory<NoFeatureConfig> getStartFactory()
    {
        return LunaticTempleStructure.Start::new;
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