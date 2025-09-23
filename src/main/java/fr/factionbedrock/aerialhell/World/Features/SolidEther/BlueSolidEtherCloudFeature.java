package fr.factionbedrock.aerialhell.World.Features.SolidEther;

import com.mojang.serialization.Codec;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellConfiguredFeatures;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.List;

public class BlueSolidEtherCloudFeature extends AbstractSolidEtherCloudFeature
{
	public static int getMinGenHeigh() {return 80;} public static int getMaxGenHeigh() {return 180;}
	protected int getBasicMinSize() {return 5;} protected int getBasicMaxSize() {return 8;}
	protected int getSmallMinSize() {return 3;} protected int getSmallMaxSize() {return 5;}

	protected Block getEtherBlock() {return AerialHellBlocks.BLUE_SOLID_ETHER;}

	public BlueSolidEtherCloudFeature(Codec<DefaultFeatureConfig> codec) {
        super(codec);
    }

	@Override public List<RegistryKey<ConfiguredFeature<?, ?>>> getAssociatedConfiguredFeatures() {return AerialHellConfiguredFeatures.Lists.BLUE_SOLID_ETHER_LIST;}

	@Override public boolean generate(FeatureContext<DefaultFeatureConfig> context)
    {
		if (!this.isDungeonSensitiveValid(context)) {return false;}
		BlockPos pos = context.getOrigin(); StructureWorldAccess reader = context.getWorld(); Random rand = context.getRandom();

		BlockPos generatePos = pos;
		if (pos.getY() < getMinGenHeigh() || pos.getY() > getMaxGenHeigh()) {generatePos = getRandomHeighGenerationPos(pos.getX(), getMinGenHeigh(), getMaxGenHeigh(), pos.getZ(), rand);}
		int sizeX = chooseRandomSize(this.getBasicMinSize(), this.getBasicMaxSize(), rand);
        int sizeZ = chooseRandomSize(this.getBasicMinSize(), this.getBasicMaxSize(), rand);
		generateFirstEllipsis(context, sizeX, sizeZ, generatePos);
		return true;
    }
}