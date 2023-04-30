package fr.factionbedrock.aerialhell.World.Structure;

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Registry.AerialHellEntities;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;

public class SlipperySandOceanAbandonnedStructure extends AbstractClassicLittleStructure
{
	private static final List<MobSpawnSettings.SpawnerData> monstersSpawnList = ImmutableList.of(new MobSpawnInfo.Spawners(AerialHellEntities.EVIL_COW.get(), 2, 3, 5));
	
    public SlipperySandOceanAbandonnedStructure(Codec<NoneFeatureConfiguration> codec, PieceGeneratorSupplier<NoneFeatureConfiguration> pieceGeneratorSupplier) {super(codec, pieceGeneratorSupplier);}
    
    @Override public List<MobSpawnSettings.SpawnerData> getDefaultSpawnList() {return monstersSpawnList;}

    @Override public IStartFactory<NoneFeatureConfiguration> getStartFactory() {return SlipperySandOceanAbandonnedStructure.Start::new;}
    
    public static class Start extends AbstractClassicLittleStructure.Start
    {
        public Start(Structure<NoneFeatureConfiguration> structureIn, int chunkX, int chunkZ, MutableBoundingBox mutableBoundingBox, int referenceIn, long seedIn) {super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);}

        @Override protected ResourceLocation getStartPool() {return new ResourceLocation(AerialHell.MODID, "slippery_sand_ocean_abandonned_structure/slippery_sand_ocean_abandonned_structure_pool");}
        @Override protected int getSize() {return 20;}
        @Override protected int getYOffsetForPlacement() {return -3;}
    }
}