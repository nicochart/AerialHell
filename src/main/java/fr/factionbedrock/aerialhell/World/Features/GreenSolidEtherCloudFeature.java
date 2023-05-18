package fr.factionbedrock.aerialhell.World.Features;

import com.mojang.serialization.Codec;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Util.FeatureHelper;
import net.minecraft.world.level.block.Block;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.Random;

public class GreenSolidEtherCloudFeature extends AbstractSolidEtherCloudFeature
{
	protected int getBasicMinSize() {return 4;} protected int getBasicMaxSize() {return 7;}
	protected int getSmallMinSize() {return 2;} protected int getSmallMaxSize() {return 4;}
	protected Block getEtherBlock() {return AerialHellBlocksAndItems.GREEN_SOLID_ETHER.get();}
	
	public GreenSolidEtherCloudFeature(Codec<NoneFeatureConfiguration> codec) {super(codec);}

	@Override public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context)
	{
		BlockPos pos = context.origin(); WorldGenLevel reader = context.level(); Random rand = context.random(); ChunkGenerator generator = context.chunkGenerator();
		if (FeatureHelper.generatesInAnyDungeon(generator, reader, pos)) {return false;}
    	
		BlockPos generatePos = pos;
    	if (pos.getY() <  115 || pos.getY() >  210) {generatePos = new BlockPos(pos.getX(), 115 + rand.nextInt(90), pos.getZ());}
    	int sizeX = chooseRandomSize(this.getBasicMinSize(), this.getBasicMaxSize(), rand);
        int sizeZ = chooseRandomSize(this.getBasicMinSize(), this.getBasicMaxSize(), rand);
    	generateFirstEllipsis(sizeX, sizeZ, reader, rand, generatePos);
    	return false;
    }
}