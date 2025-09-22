package fr.factionbedrock.aerialhell.World.Features.SolidEther;

import com.mojang.serialization.Codec;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellConfiguredFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.List;

public class WhiteSolidEtherCloudFeature extends AbstractSolidEtherCloudFeature
{
	@Override public List<ResourceKey<ConfiguredFeature<?, ?>>> getAssociatedConfiguredFeatures() {return AerialHellConfiguredFeatures.Lists.WHITE_SOLID_ETHER_LIST;}

	public static int getMinGenHeigh() {return 5;} public static int getMaxGenHeigh() {return 60;}
	protected int getBasicMinSize() {return 5;} protected int getBasicMaxSize() {return 8;}
	protected int getSmallMinSize() {return 4;} protected int getSmallMaxSize() {return 7;}
	protected Block getEtherBlock() {return AerialHellBlocks.WHITE_SOLID_ETHER.get();}
    
	public WhiteSolidEtherCloudFeature(Codec<NoneFeatureConfiguration> codec) {super(codec);}

	@Override public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context)
	{
		if (!this.isDungeonSensitiveValid(context)) {return false;}
		BlockPos pos = context.origin(); RandomSource rand = context.random(); ChunkGenerator generator = context.chunkGenerator();
    	
    	BlockPos generatePos = pos;
    	if (pos.getY() < getMinGenHeigh() || pos.getY() >  getMaxGenHeigh()) {generatePos = getRandomHeighGenerationPos(pos.getX(), getMinGenHeigh(), getMaxGenHeigh(), pos.getZ(), rand);}
    	int sizeX = chooseRandomSize(this.getBasicMinSize(), this.getBasicMaxSize(), rand);
        int sizeZ = chooseRandomSize(this.getBasicMinSize(), this.getBasicMaxSize(), rand);
        this.generateFourLayersFirstEllipsis(context, sizeX, sizeZ,generatePos);
    	return true;
    }
}