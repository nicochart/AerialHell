package fr.factionbedrock.aerialhell.World.Structure;

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Registry.AerialHellEntities;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;

public class SlipperySandOceanAbandonnedStructure extends AbstractClassicLittleStructure
{
	private static final List<MobSpawnInfo.Spawners> monstersSpawnList = ImmutableList.of(new MobSpawnInfo.Spawners(AerialHellEntities.EVIL_COW.get(), 2, 3, 5));
	
    public SlipperySandOceanAbandonnedStructure(Codec<NoFeatureConfig> codec) {super(codec);}
    
    @Override public List<MobSpawnInfo.Spawners> getDefaultSpawnList() {return monstersSpawnList;}

    @Override public IStartFactory<NoFeatureConfig> getStartFactory() {return SlipperySandOceanAbandonnedStructure.Start::new;}
    
    public static class Start extends AbstractClassicLittleStructure.Start
    {
        public Start(Structure<NoFeatureConfig> structureIn, int chunkX, int chunkZ, MutableBoundingBox mutableBoundingBox, int referenceIn, long seedIn) {super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);}

        @Override protected ResourceLocation getStartPool() {return new ResourceLocation(AerialHell.MODID, "slippery_sand_ocean_abandonned_structure/slippery_sand_ocean_abandonned_structure_pool");}
        @Override protected int getSize() {return 20;}
        @Override protected int getYOffsetForPlacement() {return -3;}
    }
}