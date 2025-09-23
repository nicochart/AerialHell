package fr.factionbedrock.aerialhell.World.Features.SolidEther;

import com.mojang.serialization.Codec;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellConfiguredFeatures;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.List;

public class WhiteSolidEtherCloudFeature extends AbstractSolidEtherCloudFeature
{
	public static int getMinGenHeigh() {return 5;} public static int getMaxGenHeigh() {return 60;}
	protected int getBasicMinSize() {return 5;} protected int getBasicMaxSize() {return 8;}
	protected int getSmallMinSize() {return 4;} protected int getSmallMaxSize() {return 7;}
	protected Block getEtherBlock() {return AerialHellBlocks.WHITE_SOLID_ETHER;}
    
	public WhiteSolidEtherCloudFeature(Codec<DefaultFeatureConfig> codec) {super(codec);}

	@Override public List<RegistryKey<ConfiguredFeature<?, ?>>> getAssociatedConfiguredFeatures() {return AerialHellConfiguredFeatures.Lists.WHITE_SOLID_ETHER_LIST;}

	@Override public boolean generate(FeatureContext<DefaultFeatureConfig> context)
	{
		if (!this.isDungeonSensitiveValid(context)) {return false;}
		BlockPos pos = context.getOrigin(); Random rand = context.getRandom(); ChunkGenerator generator = context.getGenerator();
    	
    	BlockPos generatePos = pos;
    	if (pos.getY() < getMinGenHeigh() || pos.getY() >  getMaxGenHeigh()) {generatePos = getRandomHeighGenerationPos(pos.getX(), getMinGenHeigh(), getMaxGenHeigh(), pos.getZ(), rand);}
    	int sizeX = chooseRandomSize(this.getBasicMinSize(), this.getBasicMaxSize(), rand);
        int sizeZ = chooseRandomSize(this.getBasicMinSize(), this.getBasicMaxSize(), rand);
        this.generateFourLayersFirstEllipsis(context, sizeX, sizeZ,generatePos);
    	return true;
    }
}