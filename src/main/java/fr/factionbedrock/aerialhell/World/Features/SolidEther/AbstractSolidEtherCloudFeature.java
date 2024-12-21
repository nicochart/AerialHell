package fr.factionbedrock.aerialhell.World.Features.SolidEther;

import com.mojang.serialization.Codec;

import fr.factionbedrock.aerialhell.Util.FeatureHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public abstract class AbstractSolidEtherCloudFeature extends Feature<DefaultFeatureConfig>
{
    public BlockPos getRandomHeighGenerationPos(int x, int minY, int maxY, int z, Random rand) {return new BlockPos(x, minY + rand.nextInt(maxY- minY), z);}
	protected abstract int getBasicMinSize(); protected abstract int getBasicMaxSize();
	protected abstract int getSmallMinSize(); protected abstract int getSmallMaxSize();
	protected abstract Block getEtherBlock();
	protected BlockState getEtherBlockState() {return this.getEtherBlock().getDefaultState();}

	public int chooseRandomSize(int minSize, int maxSize, Random rand)
	{
		return minSize + (int)(rand.nextDouble() * ((minSize - maxSize) + 1));
	}
	
    public AbstractSolidEtherCloudFeature(Codec<DefaultFeatureConfig> codec)
    {
        super(codec);
    }
    
    protected void generateFourLayersFirstEllipsis(FeatureContext<DefaultFeatureConfig> context, int sizeX, int sizeZ, BlockPos pos)
    {
        StructureWorldAccess reader = context.getWorld(); Random rand = context.getRandom();
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
                		reader.setBlockState(newPos, this.getEtherBlockState(), 0);
                	// ~y=+1
                	Block previousTop1Block = reader.getBlockState(newPos.up()).getBlock();
                	if (previousTop1Block == Blocks.AIR)
                		reader.setBlockState(newPos.up(), this.getEtherBlockState(), 0);
                	// ~y=+2
                	Block previousTop2Block = reader.getBlockState(newPos.up().up()).getBlock();
                	if (previousTop2Block == Blocks.AIR)
                		reader.setBlockState(newPos.up().up(), this.getEtherBlockState(), 0);
                	// ~y=-1
                	Block previousBottomBlock = reader.getBlockState(newPos.down()).getBlock();
                	if (previousBottomBlock == Blocks.AIR)
                		reader.setBlockState(newPos.down(), this.getEtherBlockState(), 0);
                }
                else
                {
                	// ~y=0
                	if (reader.getBlockState(newPos).getBlock() != this.getEtherBlock() && rand.nextDouble() > 0.8)
                	{
                    	int newSizeX = chooseRandomSize(this.getBasicMinSize(), this.getBasicMaxSize(), rand);
                        int newSizeZ = chooseRandomSize(this.getBasicMinSize(), this.getBasicMaxSize(), rand);
                		generateSecondEllipsis(context, newSizeX, newSizeZ, newPos);
                	}
                	// ~y=+1
                	if (reader.getBlockState(newPos.up()).getBlock() != this.getEtherBlock() && rand.nextDouble() > 0.8)
                	{
                    	int newSizeX = chooseRandomSize(this.getBasicMinSize(), this.getBasicMaxSize(), rand);
                        int newSizeZ = chooseRandomSize(this.getBasicMinSize(), this.getBasicMaxSize(), rand);
                		generateSecondEllipsis(context, newSizeX, newSizeZ, newPos.up());
                	}
                	// ~y=+2
                	if (reader.getBlockState(newPos.up().up()).getBlock() != this.getEtherBlock() && rand.nextDouble() > 0.8)
                	{
                    	int newSizeX = chooseRandomSize(this.getSmallMinSize(), this.getSmallMaxSize(), rand);
                        int newSizeZ = chooseRandomSize(this.getSmallMinSize(), this.getSmallMaxSize(), rand);
                		generateLastEllipsis(context, newSizeX, newSizeZ, newPos.up().up());
                	}
                	// ~y=-1
                	if (reader.getBlockState(newPos.down()).getBlock() != this.getEtherBlock() && rand.nextDouble() > 0.8)
                	{
                    	int newSizeX = chooseRandomSize(this.getSmallMinSize(), this.getSmallMaxSize(), rand);
                        int newSizeZ = chooseRandomSize(this.getSmallMinSize(), this.getSmallMaxSize(), rand);
                		generateLastEllipsis(context, newSizeX, newSizeZ, newPos.down());
                	}
                }
            }
        }
    }
    
    protected void generateFirstEllipsis(FeatureContext<DefaultFeatureConfig> context, int sizeX, int sizeZ, BlockPos pos)
    {
        StructureWorldAccess reader = context.getWorld(); Random rand = context.getRandom();
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
                		reader.setBlockState(newPos, this.getEtherBlockState(), 0);
                }
                else
                {
                	// ~y=0
                	if (reader.getBlockState(newPos).getBlock() != this.getEtherBlock() && rand.nextDouble() > 0.8)
                	{
                    	int newSizeX = chooseRandomSize(this.getBasicMinSize(), this.getBasicMaxSize(), rand);
                        int newSizeZ = chooseRandomSize(this.getBasicMinSize(), this.getBasicMaxSize(), rand);
                        generateLastEllipsis(context, newSizeX,newSizeZ, newPos);
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
                		reader.setBlockState(newPos.up(), this.getEtherBlockState(), 0);
                	// ~y=-1
                	Block previousBottomBlock = reader.getBlockState(newPos.down()).getBlock();
                	if (previousBottomBlock == Blocks.AIR)
                		reader.setBlockState(newPos.down(), this.getEtherBlockState(), 0);
                }
            	else
            	{
                	// ~y=+1
                	if (reader.getBlockState(newPos.up()).getBlock() != this.getEtherBlock() && rand.nextDouble() > 0.95)
                	{
                		int newSizeX = chooseRandomSize(this.getSmallMinSize(), this.getSmallMaxSize(), rand);
                        int newSizeZ = chooseRandomSize(this.getSmallMinSize(), this.getSmallMaxSize(), rand);
                        generateLastEllipsis(context, newSizeX, newSizeZ, newPos.up());
                	}
                	// ~y=-1
                	if (reader.getBlockState(newPos.down()).getBlock() != this.getEtherBlock() && rand.nextDouble() > 0.95)
                	{
                    	int newSizeX = chooseRandomSize(this.getSmallMinSize(), this.getSmallMaxSize(), rand);
                        int newSizeZ = chooseRandomSize(this.getSmallMinSize(), this.getSmallMaxSize(), rand);
                		generateLastEllipsis(context, newSizeX,newSizeZ, newPos.down());
                	}
                }
            }
        }
    }
    
    protected void generateSecondEllipsis(FeatureContext<DefaultFeatureConfig> context, int sizeX, int sizeZ, BlockPos pos)
    {
        StructureWorldAccess reader = context.getWorld(); Random rand = context.getRandom();
    	for(int x = pos.getX() - sizeX; x < pos.getX() + sizeX+1; x++)
        {
            for(int z = pos.getZ() - sizeZ; z < pos.getZ() + sizeZ+1; z++)
            {
                BlockPos newPos = new BlockPos(x, pos.getY(), z);

                if((x - pos.getX()) * (x - pos.getX()) + (z - pos.getZ()) * (z - pos.getZ()) < sizeX*sizeZ-1+rand.nextInt(4))
                {
                	Block previousBlock = reader.getBlockState(newPos).getBlock();
                	if (previousBlock == Blocks.AIR)
                		reader.setBlockState(newPos, this.getEtherBlockState(), 0);
                }
                else
                {
                	if (reader.getBlockState(newPos).getBlock() != this.getEtherBlock() && rand.nextDouble() > 0.95)
                	{
                    	int newSizeX = chooseRandomSize(this.getSmallMinSize(), this.getSmallMaxSize(), rand);
                        int newSizeZ = chooseRandomSize(this.getSmallMinSize(), this.getSmallMaxSize(), rand);
                		generateThirdEllipsis(context, newSizeX, newSizeZ, newPos);
                	}
                }
            }
        }
    }
    
    protected void generateThirdEllipsis(FeatureContext<DefaultFeatureConfig> context, int sizeX, int sizeZ, BlockPos pos)
    {
        StructureWorldAccess reader = context.getWorld(); Random rand = context.getRandom();
    	for(int x = pos.getX() - sizeX; x < pos.getX() + sizeX+1; x++)
        {
            for(int z = pos.getZ() - sizeZ; z < pos.getZ() + sizeZ+1; z++)
            {
                BlockPos newPos = new BlockPos(x, pos.getY(), z);

                if((x - pos.getX()) * (x - pos.getX()) + (z - pos.getZ()) * (z - pos.getZ()) < sizeX*sizeZ-1+rand.nextInt(4))
                {
                	Block previousBlock = reader.getBlockState(newPos).getBlock();
                	if (previousBlock == Blocks.AIR)
                		reader.setBlockState(newPos, this.getEtherBlockState(), 0);
                }
                else
                {
                	if (reader.getBlockState(newPos).getBlock() != this.getEtherBlock() && rand.nextDouble() > 0.95)
                	{
                    	int newSizeX = chooseRandomSize(this.getSmallMinSize(), this.getSmallMaxSize(), rand);
                        int newSizeZ = chooseRandomSize(this.getSmallMinSize(), this.getSmallMaxSize(), rand);
                		generateLastEllipsis(context, newSizeX, newSizeZ, newPos);
                	}
                }
            }
        }
    }
    
    protected void generateLastEllipsis(FeatureContext<DefaultFeatureConfig> context, int sizeX, int sizeZ, BlockPos pos)
    {
        StructureWorldAccess reader = context.getWorld(); Random rand = context.getRandom();
    	for(int x = pos.getX() - sizeX; x < pos.getX() + sizeX+1; x++)
        {
            for(int z = pos.getZ() - sizeZ; z < pos.getZ() + sizeZ+1; z++)
            {
                BlockPos newPos = new BlockPos(x, pos.getY(), z);

                if((x - pos.getX()) * (x - pos.getX()) + (z - pos.getZ()) * (z - pos.getZ()) < sizeX*sizeZ-1+rand.nextInt(3))
                {
                	Block previousBlock = reader.getBlockState(newPos).getBlock();
                	if (previousBlock == Blocks.AIR && FeatureHelper.isBlockPosInFeatureRegion(context, newPos))
                    {
                        reader.setBlockState(newPos, this.getEtherBlockState(), 0);
                    }
                }
            }
        }
    }
}