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

public class GoldenSolidEtherCloudFeature extends AbstractSolidEtherCloudFeature
{
	protected int getBasicMinSize() {return 5;} protected int getBasicMaxSize() {return 7;}
	protected int getSmallMinSize() {return 3;} protected int getSmallMaxSize() {return 5;}
	protected Block getEtherBlock() {return AerialHellBlocksAndItems.GOLDEN_SOLID_ETHER.get();}
	
    public GoldenSolidEtherCloudFeature(Codec<NoneFeatureConfiguration> codec) {super(codec);}

	@Override public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context)
	{
		BlockPos pos = context.origin(); WorldGenLevel reader = context.level(); Random rand = context.random(); ChunkGenerator generator = context.chunkGenerator();
		if (FeatureHelper.generatesInAnyDungeon(generator, reader, pos)) {return false;}
    	
		BlockPos generatePos = pos;
    	if (pos.getY() <  175 || pos.getY() >  220) {generatePos = new BlockPos(generatePos.getX(), 175 + rand.nextInt(50), generatePos.getZ());}
    	int sizeX = chooseRandomSize(this.getBasicMinSize(), this.getBasicMaxSize(), rand);
        int sizeZ = chooseRandomSize(this.getBasicMinSize(), this.getBasicMaxSize(), rand);
    	generateFirstEllipsis(sizeX, sizeZ, reader, rand, generatePos);
    	return false;
    }
}