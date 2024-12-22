package fr.factionbedrock.aerialhell.World.Features.SolidEther;

import com.mojang.serialization.Codec;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Util.FeatureHelper;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class PurpleSolidEtherCloudFeature extends AbstractSolidEtherCloudFeature
{
	public static int getMinGenHeigh() {return 60;} public static int getMaxGenHeigh() {return 200;}
	protected int getBasicMinSize() {return 4;} protected int getBasicMaxSize() {return 6;}
	protected int getSmallMinSize() {return 2;} protected int getSmallMaxSize() {return 3;}
	protected Block getEtherBlock() {return AerialHellBlocks.PURPLE_SOLID_ETHER;}

	public PurpleSolidEtherCloudFeature(Codec<DefaultFeatureConfig> codec) {super(codec);}

	@Override public boolean generate(FeatureContext<DefaultFeatureConfig> context)
	{
		BlockPos pos = context.getOrigin(); StructureWorldAccess reader = context.getWorld(); Random rand = context.getRandom(); ChunkGenerator generator = context.chunkGenerator();
		if (FeatureHelper.isFeatureGeneratingNextToDungeon(context)) {return false;}
    	
		BlockPos generatePos = pos;
		if (pos.getY() < getMinGenHeigh() || pos.getY() >  getMaxGenHeigh()) {generatePos = getRandomHeighGenerationPos(pos.getX(), getMinGenHeigh(), getMaxGenHeigh(), pos.getZ(), rand);}
    	int sizeX = chooseRandomSize(this.getBasicMinSize(), this.getBasicMaxSize(), rand);
        int sizeZ = chooseRandomSize(this.getBasicMinSize(), this.getBasicMaxSize(), rand);
    	generateFirstEllipsis(context, sizeX, sizeZ, generatePos);
    	return true;
    }
}