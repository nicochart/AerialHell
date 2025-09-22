package fr.factionbedrock.aerialhell.World.Features.SolidEther;

import com.mojang.serialization.Codec;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellConfiguredFeatures;
import fr.factionbedrock.aerialhell.Util.FeatureHelper;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.List;

public class GoldenSolidEtherCloudFeature extends AbstractSolidEtherCloudFeature
{
	public static int getMinGenHeigh() {return 150;} public static int getMaxGenHeigh() {return 240;}
	protected int getBasicMinSize() {return 5;} protected int getBasicMaxSize() {return 7;}
	protected int getSmallMinSize() {return 3;} protected int getSmallMaxSize() {return 5;}
	protected Block getEtherBlock() {return AerialHellBlocks.GOLDEN_SOLID_ETHER.get();}
	
    public GoldenSolidEtherCloudFeature(Codec<NoneFeatureConfiguration> codec) {super(codec);}

	@Override protected List<ResourceKey<ConfiguredFeature<?, ?>>> getAssociatedConfiguredFeatures() {return AerialHellConfiguredFeatures.Lists.GOLDEN_SOLID_ETHER_LIST;}

	@Override public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context)
	{
		if (!super.place(context)) {return false;}
		BlockPos pos = context.origin(); WorldGenLevel reader = context.level(); RandomSource rand = context.random(); ChunkGenerator generator = context.chunkGenerator();
		if (FeatureHelper.isFeatureGeneratingNextToDungeon(context)) {return false;}
    	
		BlockPos generatePos = pos;
		if (pos.getY() < getMinGenHeigh() || pos.getY() > getMaxGenHeigh()) {generatePos = getRandomHeighGenerationPos(pos.getX(), getMinGenHeigh(), getMaxGenHeigh(), pos.getZ(), rand);}
    	int sizeX = chooseRandomSize(this.getBasicMinSize(), this.getBasicMaxSize(), rand);
        int sizeZ = chooseRandomSize(this.getBasicMinSize(), this.getBasicMaxSize(), rand);
    	generateFirstEllipsis(context, sizeX, sizeZ, generatePos);
    	return true;
    }
}