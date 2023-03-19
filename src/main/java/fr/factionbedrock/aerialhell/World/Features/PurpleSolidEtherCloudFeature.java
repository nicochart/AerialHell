package fr.factionbedrock.aerialhell.World.Features;

import com.mojang.serialization.Codec;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

public class PurpleSolidEtherCloudFeature extends AbstractSolidEtherCloudFeature
{
	protected int getBasicMinSize() {return 4;} protected int getBasicMaxSize() {return 6;}
	protected int getSmallMinSize() {return 2;} protected int getSmallMaxSize() {return 3;}
	protected Block getEtherBlock() {return AerialHellBlocksAndItems.PURPLE_SOLID_ETHER.get();}

	public PurpleSolidEtherCloudFeature(Codec<NoFeatureConfig> codec) {super(codec);}
	
	@Override
    public boolean generate(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config)
    {
		if (this.generatesInAnyDungeon(reader, pos)) {return false;}
    	
		BlockPos generatePos = pos;
    	if (pos.getY() <  115 || pos.getY() >  210) {generatePos = new BlockPos(pos.getX(), 115 + rand.nextInt(90), pos.getZ());}
    	int sizeX = chooseRandomSize(this.getBasicMinSize(), this.getBasicMaxSize(), rand);
        int sizeZ = chooseRandomSize(this.getBasicMinSize(), this.getBasicMaxSize(), rand);
    	generateFirstEllipsis(sizeX, sizeZ, reader, rand, generatePos);
    	return false;
    }
}