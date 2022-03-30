package fr.factionbedrock.aerialhell.World.Features;

import com.mojang.serialization.Codec;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

public class BlueSolidEtherCloudFeature extends Feature<NoFeatureConfig>
{
	private int bonus = 0;
	private int basicMinSize = 4+bonus;
	private int basicMaxSize = 7+bonus;
	private int littleMinSize = 3+bonus;
	private int littleMaxSize = 5+bonus;
	
	public BlueSolidEtherCloudFeature(Codec<NoFeatureConfig> codec)
	{
        super(codec);
    }
	
	@Override
    public boolean generate(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config)
    {
		BlockPos generatePos = pos;
    	if (pos.getY() <  115 || pos.getY() >  155) {generatePos = new BlockPos(pos.getX(), 115 + rand.nextInt(50), pos.getZ());}
    	int sizeX = basicMinSize + (int)(rand.nextDouble() * ((basicMaxSize - basicMinSize) + 1));
        int sizeZ = basicMinSize + (int)(rand.nextDouble() * ((basicMaxSize - basicMinSize) + 1));
    	generateFirstEllipsis(sizeX, sizeZ, reader, rand, generatePos);
    	return false;
    }
	
	private void generateFirstEllipsis(int sizeX, int sizeZ, ISeedReader reader, Random rand, BlockPos pos)
    {
    	for(int x = pos.getX() - sizeX; x < pos.getX() + sizeX+1; x++)
        {
            for(int z = pos.getZ() - sizeZ; z < pos.getZ() + sizeZ+1; z++)
            {
                BlockPos newPos = new BlockPos(x, pos.getY(), z);

                if((x - pos.getX()) * (x - pos.getX()) + (z - pos.getZ()) * (z - pos.getZ()) < sizeX*sizeZ-1+rand.nextInt(5))
                {
                	// ~y=0
                	Block previousBlock = reader.getBlockState(newPos).getBlock();
                	if (previousBlock == Blocks.AIR)
                		reader.setBlockState(newPos, AerialHellBlocksAndItems.BLUE_SOLID_ETHER.get().getDefaultState(), 0);
                }
                else
                {
                	// ~y=0
                	if (reader.getBlockState(newPos).getBlock() != AerialHellBlocksAndItems.BLUE_SOLID_ETHER.get() && rand.nextDouble() > 0.8)
                	{
                    	int newSizeX = basicMinSize + (int)(rand.nextDouble() * ((basicMaxSize - basicMinSize) + 1));
                        int newSizeZ = basicMinSize + (int)(rand.nextDouble() * ((basicMaxSize - basicMinSize) + 1));
                        generateLastEllipsis(newSizeX,newSizeZ, reader, rand, newPos);
                	}
                }
            }
        }
    	sizeX--;sizeZ--;
    	for(int x = pos.getX() - sizeX; x < pos.getX() + sizeX+1; x++)
        {
            for(int z = pos.getZ() - sizeZ; z < pos.getZ() + sizeZ+1; z++)
            {
                BlockPos newPos = new BlockPos(x, pos.getY(), z);
                
                if((x - pos.getX()) * (x - pos.getX()) + (z - pos.getZ()) * (z - pos.getZ()) < sizeX*sizeZ-1+rand.nextInt(5))
                {
                	// ~y=+1
                	Block previousTop1Block = reader.getBlockState(newPos.up()).getBlock();
                	if (previousTop1Block == Blocks.AIR)
                		reader.setBlockState(newPos.up(), AerialHellBlocksAndItems.BLUE_SOLID_ETHER.get().getDefaultState(), 0);
                	// ~y=-1
                	Block previousBottomBlock = reader.getBlockState(newPos.down()).getBlock();
                	if (previousBottomBlock == Blocks.AIR)
                		reader.setBlockState(newPos.down(), AerialHellBlocksAndItems.BLUE_SOLID_ETHER.get().getDefaultState(), 0);
                }
            	else
            	{
                	// ~y=+1
                	if (reader.getBlockState(newPos.up()).getBlock() != AerialHellBlocksAndItems.BLUE_SOLID_ETHER.get() && rand.nextDouble() > 0.95)
                	{
                		int newSizeX = littleMinSize + (int)(rand.nextDouble() * ((littleMaxSize - littleMinSize) + 1));
                        int newSizeZ = littleMinSize + (int)(rand.nextDouble() * ((littleMaxSize - littleMinSize) + 1));
                        generateLastEllipsis(newSizeX,newSizeZ, reader, rand, newPos.up());
                	}
                	// ~y=-1
                	if (reader.getBlockState(newPos.down()).getBlock() != AerialHellBlocksAndItems.BLUE_SOLID_ETHER.get() && rand.nextDouble() > 0.95)
                	{
                    	int newSizeX = littleMinSize + (int)(rand.nextDouble() * ((littleMaxSize - littleMinSize) + 1));
                        int newSizeZ = littleMinSize + (int)(rand.nextDouble() * ((littleMaxSize - littleMinSize) + 1));
                		generateLastEllipsis(newSizeX,newSizeZ, reader, rand, newPos.down());
                	}
                }
            }
        }
    }
	
	private void generateLastEllipsis(int sizeX, int sizeZ, ISeedReader reader, Random rand, BlockPos pos)
    {
    	for(int x = pos.getX() - sizeX; x < pos.getX() + sizeX+1; x++)
        {
            for(int z = pos.getZ() - sizeZ; z < pos.getZ() + sizeZ+1; z++)
            {
                BlockPos newPos = new BlockPos(x, pos.getY(), z);

                if((x - pos.getX()) * (x - pos.getX()) + (z - pos.getZ()) * (z - pos.getZ()) < sizeX*sizeZ-1+rand.nextInt(3))
                {
                	Block previousBlock = reader.getBlockState(newPos).getBlock();
                	if (previousBlock == Blocks.AIR)
                		reader.setBlockState(newPos, AerialHellBlocksAndItems.BLUE_SOLID_ETHER.get().getDefaultState(), 0);
                }
            }
        }
    }
}