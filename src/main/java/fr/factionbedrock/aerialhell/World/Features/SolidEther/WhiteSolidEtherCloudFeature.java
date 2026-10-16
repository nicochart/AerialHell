package fr.factionbedrock.aerialhell.World.Features.SolidEther;

import com.mojang.serialization.MapCodec;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellConfiguredFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;

import java.util.List;

public record WhiteSolidEtherCloudFeature() implements AbstractSolidEtherCloudFeature
{
	@Override public List<ResourceKey<Feature>> getAssociatedConfiguredFeatures() {return AerialHellConfiguredFeatures.Lists.WHITE_SOLID_ETHER_LIST;}

	public static int getMinGenHeigh() {return 5;} public static int getMaxGenHeigh() {return 60;}
	@Override public int getBasicMinSize() {return 5;} @Override public int getBasicMaxSize() {return 8;}
	@Override public int getSmallMinSize() {return 4;} @Override public int getSmallMaxSize() {return 7;}
	@Override public Block getEtherBlock() {return AerialHellBlocks.WHITE_SOLID_ETHER.get();}
    
	public static final MapCodec<WhiteSolidEtherCloudFeature> CODEC = MapCodec.unit(WhiteSolidEtherCloudFeature::new);

	@Override public MapCodec<WhiteSolidEtherCloudFeature> codec() {return CODEC;}

	@Override public boolean place(WorldGenLevel level, ChunkGenerator chunkGenerator, RandomSource random, BlockPos origin)
	{
		if (!this.isDungeonSensitiveValid(level, origin)) {return false;}
    	
    	BlockPos generatePos = origin;
    	if (origin.getY() < getMinGenHeigh() || origin.getY() >  getMaxGenHeigh()) {generatePos = getRandomHeighGenerationPos(origin.getX(), getMinGenHeigh(), getMaxGenHeigh(), origin.getZ(), random);}
    	int sizeX = chooseRandomSize(this.getBasicMinSize(), this.getBasicMaxSize(), random);
        int sizeZ = chooseRandomSize(this.getBasicMinSize(), this.getBasicMaxSize(), random);
        this.generateFourLayersFirstEllipsis(level, random, origin, sizeX, sizeZ,generatePos);
    	return true;
    }
}