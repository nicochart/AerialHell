package fr.factionbedrock.aerialhell.World.Features;

import com.mojang.serialization.Codec;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Util.FeatureHelper;
import net.minecraft.world.level.block.Block;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.Random;

public class WhiteSolidEtherCloudFeature extends AbstractSolidEtherCloudFeature
{
	protected int getBasicMinSize() {return 5;} protected int getBasicMaxSize() {return 8;}
	protected int getSmallMinSize() {return 4;} protected int getSmallMaxSize() {return 7;}
	protected Block getEtherBlock() {return AerialHellBlocksAndItems.WHITE_SOLID_ETHER.get();}
    
	public WhiteSolidEtherCloudFeature(Codec<NoneFeatureConfiguration> codec) {super(codec);}

	@Override public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context)
	{
		BlockPos pos = context.origin(); WorldGenLevel world = context.level(); Random rand = context.random();
		if (FeatureHelper.generatesInAnyDungeon(world, pos)) {return false;}
    	
    	BlockPos generatePos = pos;
    	if (pos.getY() <  40 || pos.getY() >  80 ) {generatePos = new BlockPos(pos.getX(), 30 + rand.nextInt(50), pos.getZ());}
    	int sizeX = chooseRandomSize(this.getBasicMinSize(), this.getBasicMaxSize(), rand);
        int sizeZ = chooseRandomSize(this.getBasicMinSize(), this.getBasicMaxSize(), rand);
        this.generateFourLayersFirstEllipsis(sizeX, sizeZ, world, rand, generatePos);
    	return false;
    }
}