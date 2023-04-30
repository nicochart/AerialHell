package fr.factionbedrock.aerialhell.World.Structure;

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

import java.util.List;

public class ShadowPineTowerStructure extends AbstractClassicLittleStructure
{
    public ShadowPineTowerStructure(Codec<NoneFeatureConfiguration> codec, PieceGeneratorSupplier<NoneFeatureConfiguration> pieceGeneratorSupplier) {super(codec, pieceGeneratorSupplier);}

    private static final List<MobSpawnSettings.SpawnerData> monstersSpawnList = ImmutableList.of(new MobSpawnInfo.Spawners(AerialHellEntities.SHADOW_AUTOMATON.get(), 1, 1, 2));
    @Override public List<MobSpawnSettings.SpawnerData> getDefaultSpawnList() {return monstersSpawnList;}

    @Override public IStartFactory<NoneFeatureConfiguration> getStartFactory() {return ShadowPineTowerStructure.Start::new;}
    
    public static class Start extends AbstractClassicLittleStructure.Start
    {

        public Start(Structure<NoneFeatureConfiguration> structureIn, int chunkX, int chunkZ, MutableBoundingBox mutableBoundingBox, int referenceIn, long seedIn)
        {
            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
        }

        @Override protected ResourceLocation getStartPool() {return new ResourceLocation(AerialHell.MODID, "shadow_pine_tower/shadow_pine_tower");}
        @Override protected int getSize() {return 30;}
        @Override protected int getYOffsetForPlacement() {return -1;}
    }
}