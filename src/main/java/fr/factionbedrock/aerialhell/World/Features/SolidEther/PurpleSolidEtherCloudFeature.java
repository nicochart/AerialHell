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

public record PurpleSolidEtherCloudFeature() implements AbstractSolidEtherCloudFeature
{
	public static int getMinGenHeigh() {return 60;} public static int getMaxGenHeigh() {return 200;}
	@Override public int getBasicMinSize() {return 4;} @Override public int getBasicMaxSize() {return 6;}
	@Override public int getSmallMinSize() {return 2;} @Override public int getSmallMaxSize() {return 3;}
	@Override public Block getEtherBlock() {return AerialHellBlocks.PURPLE_SOLID_ETHER.get();}

	public static final MapCodec<PurpleSolidEtherCloudFeature> CODEC = MapCodec.unit(PurpleSolidEtherCloudFeature::new);

	@Override public MapCodec<PurpleSolidEtherCloudFeature> codec() {return CODEC;}

	@Override public List<ResourceKey<Feature>> getAssociatedConfiguredFeatures() {return AerialHellConfiguredFeatures.Lists.PURPLE_SOLID_ETHER_LIST;}

	@Override public boolean place(WorldGenLevel level, ChunkGenerator chunkGenerator, RandomSource random, BlockPos origin)
	{
		if (!this.isDungeonSensitiveValid(level, origin)) {return false;}
    	
		BlockPos generatePos = origin;
		if (origin.getY() < getMinGenHeigh() || origin.getY() >  getMaxGenHeigh()) {generatePos = getRandomHeighGenerationPos(origin.getX(), getMinGenHeigh(), getMaxGenHeigh(), origin.getZ(), random);}
    	int sizeX = chooseRandomSize(this.getBasicMinSize(), this.getBasicMaxSize(), random);
        int sizeZ = chooseRandomSize(this.getBasicMinSize(), this.getBasicMaxSize(), random);
    	generateFirstEllipsis(level, random, origin, sizeX, sizeZ, generatePos);
    	return true;
    }
}