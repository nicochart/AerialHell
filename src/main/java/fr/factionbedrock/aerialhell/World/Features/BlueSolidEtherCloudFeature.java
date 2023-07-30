package fr.factionbedrock.aerialhell.World.Features;

import com.mojang.serialization.Codec;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Util.FeatureHelper;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class BlueSolidEtherCloudFeature extends AbstractSolidEtherCloudFeature
{
	public static int getMinGenHeigh() {return 80;} public static int getMaxGenHeigh() {return 180;}
	protected int getBasicMinSize() {return 5;} protected int getBasicMaxSize() {return 8;}
	protected int getSmallMinSize() {return 3;} protected int getSmallMaxSize() {return 5;}

	protected Block getEtherBlock() {return AerialHellBlocksAndItems.BLUE_SOLID_ETHER.get();}

	public BlueSolidEtherCloudFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

	@Override public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context)
    {
		BlockPos pos = context.origin(); WorldGenLevel reader = context.level(); RandomSource rand = context.random();
		if (FeatureHelper.isFeatureGeneratingNextToDungeon(context)) {return false;}

		BlockPos generatePos = pos;
		if (pos.getY() < getMinGenHeigh() || pos.getY() > getMaxGenHeigh()) {generatePos = getRandomHeighGenerationPos(pos.getX(), getMinGenHeigh(), getMaxGenHeigh(), pos.getZ(), rand);}
		int sizeX = chooseRandomSize(this.getBasicMinSize(), this.getBasicMaxSize(), rand);
        int sizeZ = chooseRandomSize(this.getBasicMinSize(), this.getBasicMaxSize(), rand);
		generateFirstEllipsis(sizeX, sizeZ, reader, rand, generatePos);
		return false;
    }
}