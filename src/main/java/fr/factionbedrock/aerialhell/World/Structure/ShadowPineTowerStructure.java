package fr.factionbedrock.aerialhell.World.Structure;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Registry.AerialHellEntities;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;

import java.util.List;

public class ShadowPineTowerStructure extends AbstractClassicLittleStructure
{
    public ShadowPineTowerStructure(Codec<NoFeatureConfig> codec) {super(codec);}

    private static final List<MobSpawnInfo.Spawners> monstersSpawnList = ImmutableList.of(new MobSpawnInfo.Spawners(AerialHellEntities.SHADOW_AUTOMATON.get(), 1, 1, 2));
    @Override public List<MobSpawnInfo.Spawners> getDefaultSpawnList() {return monstersSpawnList;}

    @Override public IStartFactory<NoFeatureConfig> getStartFactory() {return ShadowPineTowerStructure.Start::new;}
    
    public static class Start extends AbstractClassicLittleStructure.Start
    {

        public Start(Structure<NoFeatureConfig> structureIn, int chunkX, int chunkZ, MutableBoundingBox mutableBoundingBox, int referenceIn, long seedIn)
        {
            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
        }

        @Override protected ResourceLocation getStartPool() {return new ResourceLocation(AerialHell.MODID, "shadow_pine_tower/shadow_pine_tower");}
        @Override protected int getSize() {return 30;}
        @Override protected int getYOffsetForPlacement() {return -1;}
    }
}