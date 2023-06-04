package fr.factionbedrock.aerialhell.World.Features;

import com.mojang.serialization.Codec;

import fr.factionbedrock.aerialhell.Util.FeatureHelper;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.Random;

public abstract class AbstractSolidEtherCloudFeature extends Feature<NoneFeatureConfiguration>
{
    public BlockPos getRandomHeighGenerationPos(int x, int minY, int maxY, int z, Random rand) {return new BlockPos(x, minY + rand.nextInt(maxY- minY), z);}
	protected abstract int getBasicMinSize(); protected abstract int getBasicMaxSize();
	protected abstract int getSmallMinSize(); protected abstract int getSmallMaxSize();
	protected abstract Block getEtherBlock();
	protected BlockState getEtherBlockState() {return this.getEtherBlock().defaultBlockState();}

	public int chooseRandomSize(int minSize, int maxSize, Random rand)
	{
		return minSize + (int)(rand.nextDouble() * ((minSize - maxSize) + 1));
	}
	
    public AbstractSolidEtherCloudFeature(Codec<NoneFeatureConfiguration> codec)
    {
        super(codec);
    }
    
    protected void generateFourLayersFirstEllipsis(int sizeX, int sizeZ, WorldGenLevel reader, Random rand, BlockPos pos)
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
                		reader.setBlock(newPos, this.getEtherBlockState(), 0);
                	// ~y=+1
                	Block previousTop1Block = reader.getBlockState(newPos.above()).getBlock();
                	if (previousTop1Block == Blocks.AIR)
                		reader.setBlock(newPos.above(), this.getEtherBlockState(), 0);
                	// ~y=+2
                	Block previousTop2Block = reader.getBlockState(newPos.above().above()).getBlock();
                	if (previousTop2Block == Blocks.AIR)
                		reader.setBlock(newPos.above().above(), this.getEtherBlockState(), 0);
                	// ~y=-1
                	Block previousBottomBlock = reader.getBlockState(newPos.below()).getBlock();
                	if (previousBottomBlock == Blocks.AIR)
                		reader.setBlock(newPos.below(), this.getEtherBlockState(), 0);
                }
                else
                {
                	// ~y=0
                	if (reader.getBlockState(newPos).getBlock() != this.getEtherBlock() && rand.nextDouble() > 0.8)
                	{
                    	int newSizeX = chooseRandomSize(this.getBasicMinSize(), this.getBasicMaxSize(), rand);
                        int newSizeZ = chooseRandomSize(this.getBasicMinSize(), this.getBasicMaxSize(), rand);
                		generateSecondEllipsis(newSizeX, newSizeZ, reader, rand, newPos);
                	}
                	// ~y=+1
                	if (reader.getBlockState(newPos.above()).getBlock() != this.getEtherBlock() && rand.nextDouble() > 0.8)
                	{
                    	int newSizeX = chooseRandomSize(this.getBasicMinSize(), this.getBasicMaxSize(), rand);
                        int newSizeZ = chooseRandomSize(this.getBasicMinSize(), this.getBasicMaxSize(), rand);
                		generateSecondEllipsis(newSizeX, newSizeZ, reader, rand, newPos.above());
                	}
                	// ~y=+2
                	if (reader.getBlockState(newPos.above().above()).getBlock() != this.getEtherBlock() && rand.nextDouble() > 0.8)
                	{
                    	int newSizeX = chooseRandomSize(this.getSmallMinSize(), this.getSmallMaxSize(), rand);
                        int newSizeZ = chooseRandomSize(this.getSmallMinSize(), this.getSmallMaxSize(), rand);
                		generateLastEllipsis(newSizeX, newSizeZ, reader, rand, newPos.above().above());
                	}
                	// ~y=-1
                	if (reader.getBlockState(newPos.below()).getBlock() != this.getEtherBlock() && rand.nextDouble() > 0.8)
                	{
                    	int newSizeX = chooseRandomSize(this.getSmallMinSize(), this.getSmallMaxSize(), rand);
                        int newSizeZ = chooseRandomSize(this.getSmallMinSize(), this.getSmallMaxSize(), rand);
                		generateLastEllipsis(newSizeX, newSizeZ, reader, rand, newPos.below());
                	}
                }
            }
        }
    }
    
    protected void generateFirstEllipsis(int sizeX, int sizeZ, WorldGenLevel reader, Random rand, BlockPos pos)
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
                		reader.setBlock(newPos, this.getEtherBlockState(), 0);
                }
                else
                {
                	// ~y=0
                	if (reader.getBlockState(newPos).getBlock() != this.getEtherBlock() && rand.nextDouble() > 0.8)
                	{
                    	int newSizeX = chooseRandomSize(this.getBasicMinSize(), this.getBasicMaxSize(), rand);
                        int newSizeZ = chooseRandomSize(this.getBasicMinSize(), this.getBasicMaxSize(), rand);
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
                	Block previousTop1Block = reader.getBlockState(newPos.above()).getBlock();
                	if (previousTop1Block == Blocks.AIR)
                		reader.setBlock(newPos.above(), this.getEtherBlockState(), 0);
                	// ~y=-1
                	Block previousBottomBlock = reader.getBlockState(newPos.below()).getBlock();
                	if (previousBottomBlock == Blocks.AIR)
                		reader.setBlock(newPos.below(), this.getEtherBlockState(), 0);
                }
            	else
            	{
                	// ~y=+1
                	if (reader.getBlockState(newPos.above()).getBlock() != this.getEtherBlock() && rand.nextDouble() > 0.95)
                	{
                		int newSizeX = chooseRandomSize(this.getSmallMinSize(), this.getSmallMaxSize(), rand);
                        int newSizeZ = chooseRandomSize(this.getSmallMinSize(), this.getSmallMaxSize(), rand);
                        generateLastEllipsis(newSizeX,newSizeZ, reader, rand, newPos.above());
                	}
                	// ~y=-1
                	if (reader.getBlockState(newPos.below()).getBlock() != this.getEtherBlock() && rand.nextDouble() > 0.95)
                	{
                    	int newSizeX = chooseRandomSize(this.getSmallMinSize(), this.getSmallMaxSize(), rand);
                        int newSizeZ = chooseRandomSize(this.getSmallMinSize(), this.getSmallMaxSize(), rand);
                		generateLastEllipsis(newSizeX,newSizeZ, reader, rand, newPos.below());
                	}
                }
            }
        }
    }
    
    protected void generateSecondEllipsis(int sizeX, int sizeZ, WorldGenLevel reader, Random rand, BlockPos pos)
    {
    	for(int x = pos.getX() - sizeX; x < pos.getX() + sizeX+1; x++)
        {
            for(int z = pos.getZ() - sizeZ; z < pos.getZ() + sizeZ+1; z++)
            {
                BlockPos newPos = new BlockPos(x, pos.getY(), z);

                if((x - pos.getX()) * (x - pos.getX()) + (z - pos.getZ()) * (z - pos.getZ()) < sizeX*sizeZ-1+rand.nextInt(4))
                {
                	Block previousBlock = reader.getBlockState(newPos).getBlock();
                	if (previousBlock == Blocks.AIR)
                		reader.setBlock(newPos, this.getEtherBlockState(), 0);
                }
                else
                {
                	if (reader.getBlockState(newPos).getBlock() != this.getEtherBlock() && rand.nextDouble() > 0.95)
                	{
                    	int newSizeX = chooseRandomSize(this.getSmallMinSize(), this.getSmallMaxSize(), rand);
                        int newSizeZ = chooseRandomSize(this.getSmallMinSize(), this.getSmallMaxSize(), rand);
                		generateThirdEllipsis(newSizeX, newSizeZ, reader, rand, newPos);
                	}
                }
            }
        }
    }
    
    protected void generateThirdEllipsis(int sizeX, int sizeZ, WorldGenLevel reader, Random rand, BlockPos pos)
    {
    	for(int x = pos.getX() - sizeX; x < pos.getX() + sizeX+1; x++)
        {
            for(int z = pos.getZ() - sizeZ; z < pos.getZ() + sizeZ+1; z++)
            {
                BlockPos newPos = new BlockPos(x, pos.getY(), z);

                if((x - pos.getX()) * (x - pos.getX()) + (z - pos.getZ()) * (z - pos.getZ()) < sizeX*sizeZ-1+rand.nextInt(4))
                {
                	Block previousBlock = reader.getBlockState(newPos).getBlock();
                	if (previousBlock == Blocks.AIR)
                		reader.setBlock(newPos, this.getEtherBlockState(), 0);
                }
                else
                {
                	if (reader.getBlockState(newPos).getBlock() != this.getEtherBlock() && rand.nextDouble() > 0.95)
                	{
                    	int newSizeX = chooseRandomSize(this.getSmallMinSize(), this.getSmallMaxSize(), rand);
                        int newSizeZ = chooseRandomSize(this.getSmallMinSize(), this.getSmallMaxSize(), rand);
                		generateLastEllipsis(newSizeX, newSizeZ, reader, rand, newPos);
                	}
                }
            }
        }
    }
    
    protected void generateLastEllipsis(int sizeX, int sizeZ, WorldGenLevel reader, Random rand, BlockPos pos)
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
                		reader.setBlock(newPos, this.getEtherBlockState(), 0);
                }
            }
        }
    }
}