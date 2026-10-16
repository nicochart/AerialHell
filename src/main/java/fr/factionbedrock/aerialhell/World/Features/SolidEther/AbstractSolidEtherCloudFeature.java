package fr.factionbedrock.aerialhell.World.Features.SolidEther;

import fr.factionbedrock.aerialhell.Util.FeatureHelper;
import fr.factionbedrock.aerialhell.World.Features.DungeonSensitiveFeatureCheck;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.Feature;

//shared behaviour of solid ether cloud features (same pattern as vanilla AbstractHugeMushroomFeature)
public interface AbstractSolidEtherCloudFeature extends Feature, DungeonSensitiveFeatureCheck
{
    default BlockPos getRandomHeighGenerationPos(int x, int minY, int maxY, int z, RandomSource random) {return new BlockPos(x, minY + random.nextInt(maxY- minY), z);}
	int getBasicMinSize(); int getBasicMaxSize();
	int getSmallMinSize(); int getSmallMaxSize();
	Block getEtherBlock();
	default BlockState getEtherBlockState() {return this.getEtherBlock().defaultBlockState();}

	default int chooseRandomSize(int minSize, int maxSize, RandomSource random)
	{
		return minSize + (int)(random.nextDouble() * ((minSize - maxSize) + 1));
	}

    
    default void generateFourLayersFirstEllipsis(WorldGenLevel level, RandomSource random, BlockPos origin, int sizeX, int sizeZ, BlockPos pos)
    {
    	for(int x = pos.getX() - sizeX; x < pos.getX() + sizeX+1; x++)
        {
            for(int z = pos.getZ() - sizeZ; z < pos.getZ() + sizeZ+1; z++)
            {
                BlockPos newPos = new BlockPos(x, pos.getY(), z);

                if((x - pos.getX()) * (x - pos.getX()) + (z - pos.getZ()) * (z - pos.getZ()) < sizeX*sizeZ-1+random.nextInt(5))
                {
                	// ~y=0
                	Block previousBlock = level.getBlockState(newPos).getBlock();
                	if (previousBlock == Blocks.AIR)
                		level.setBlock(newPos, this.getEtherBlockState(), 0);
                	// ~y=+1
                	Block previousTop1Block = level.getBlockState(newPos.above()).getBlock();
                	if (previousTop1Block == Blocks.AIR)
                		level.setBlock(newPos.above(), this.getEtherBlockState(), 0);
                	// ~y=+2
                	Block previousTop2Block = level.getBlockState(newPos.above().above()).getBlock();
                	if (previousTop2Block == Blocks.AIR)
                		level.setBlock(newPos.above().above(), this.getEtherBlockState(), 0);
                	// ~y=-1
                	Block previousBottomBlock = level.getBlockState(newPos.below()).getBlock();
                	if (previousBottomBlock == Blocks.AIR)
                		level.setBlock(newPos.below(), this.getEtherBlockState(), 0);
                }
                else
                {
                	// ~y=0
                	if (level.getBlockState(newPos).getBlock() != this.getEtherBlock() && random.nextDouble() > 0.8)
                	{
                    	int newSizeX = chooseRandomSize(this.getBasicMinSize(), this.getBasicMaxSize(), random);
                        int newSizeZ = chooseRandomSize(this.getBasicMinSize(), this.getBasicMaxSize(), random);
                		generateSecondEllipsis(level, random, origin, newSizeX, newSizeZ, newPos);
                	}
                	// ~y=+1
                	if (level.getBlockState(newPos.above()).getBlock() != this.getEtherBlock() && random.nextDouble() > 0.8)
                	{
                    	int newSizeX = chooseRandomSize(this.getBasicMinSize(), this.getBasicMaxSize(), random);
                        int newSizeZ = chooseRandomSize(this.getBasicMinSize(), this.getBasicMaxSize(), random);
                		generateSecondEllipsis(level, random, origin, newSizeX, newSizeZ, newPos.above());
                	}
                	// ~y=+2
                	if (level.getBlockState(newPos.above().above()).getBlock() != this.getEtherBlock() && random.nextDouble() > 0.8)
                	{
                    	int newSizeX = chooseRandomSize(this.getSmallMinSize(), this.getSmallMaxSize(), random);
                        int newSizeZ = chooseRandomSize(this.getSmallMinSize(), this.getSmallMaxSize(), random);
                		generateLastEllipsis(level, random, origin, newSizeX, newSizeZ, newPos.above().above());
                	}
                	// ~y=-1
                	if (level.getBlockState(newPos.below()).getBlock() != this.getEtherBlock() && random.nextDouble() > 0.8)
                	{
                    	int newSizeX = chooseRandomSize(this.getSmallMinSize(), this.getSmallMaxSize(), random);
                        int newSizeZ = chooseRandomSize(this.getSmallMinSize(), this.getSmallMaxSize(), random);
                		generateLastEllipsis(level, random, origin, newSizeX, newSizeZ, newPos.below());
                	}
                }
            }
        }
    }
    
    default void generateFirstEllipsis(WorldGenLevel level, RandomSource random, BlockPos origin, int sizeX, int sizeZ, BlockPos pos)
    {
    	for(int x = pos.getX() - sizeX; x < pos.getX() + sizeX+1; x++)
        {
            for(int z = pos.getZ() - sizeZ; z < pos.getZ() + sizeZ+1; z++)
            {
                BlockPos newPos = new BlockPos(x, pos.getY(), z);

                if((x - pos.getX()) * (x - pos.getX()) + (z - pos.getZ()) * (z - pos.getZ()) < sizeX*sizeZ-1+random.nextInt(5))
                {
                	// ~y=0
                	Block previousBlock = level.getBlockState(newPos).getBlock();
                	if (previousBlock == Blocks.AIR)
                		level.setBlock(newPos, this.getEtherBlockState(), 0);
                }
                else
                {
                	// ~y=0
                	if (level.getBlockState(newPos).getBlock() != this.getEtherBlock() && random.nextDouble() > 0.8)
                	{
                    	int newSizeX = chooseRandomSize(this.getBasicMinSize(), this.getBasicMaxSize(), random);
                        int newSizeZ = chooseRandomSize(this.getBasicMinSize(), this.getBasicMaxSize(), random);
                        generateLastEllipsis(level, random, origin, newSizeX,newSizeZ, newPos);
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
                
                if((x - pos.getX()) * (x - pos.getX()) + (z - pos.getZ()) * (z - pos.getZ()) < sizeX*sizeZ-1+random.nextInt(5))
                {
                	// ~y=+1
                	Block previousTop1Block = level.getBlockState(newPos.above()).getBlock();
                	if (previousTop1Block == Blocks.AIR)
                		level.setBlock(newPos.above(), this.getEtherBlockState(), 0);
                	// ~y=-1
                	Block previousBottomBlock = level.getBlockState(newPos.below()).getBlock();
                	if (previousBottomBlock == Blocks.AIR)
                		level.setBlock(newPos.below(), this.getEtherBlockState(), 0);
                }
            	else
            	{
                	// ~y=+1
                	if (level.getBlockState(newPos.above()).getBlock() != this.getEtherBlock() && random.nextDouble() > 0.95)
                	{
                		int newSizeX = chooseRandomSize(this.getSmallMinSize(), this.getSmallMaxSize(), random);
                        int newSizeZ = chooseRandomSize(this.getSmallMinSize(), this.getSmallMaxSize(), random);
                        generateLastEllipsis(level, random, origin, newSizeX, newSizeZ, newPos.above());
                	}
                	// ~y=-1
                	if (level.getBlockState(newPos.below()).getBlock() != this.getEtherBlock() && random.nextDouble() > 0.95)
                	{
                    	int newSizeX = chooseRandomSize(this.getSmallMinSize(), this.getSmallMaxSize(), random);
                        int newSizeZ = chooseRandomSize(this.getSmallMinSize(), this.getSmallMaxSize(), random);
                		generateLastEllipsis(level, random, origin, newSizeX,newSizeZ, newPos.below());
                	}
                }
            }
        }
    }
    
    default void generateSecondEllipsis(WorldGenLevel level, RandomSource random, BlockPos origin, int sizeX, int sizeZ, BlockPos pos)
    {
    	for(int x = pos.getX() - sizeX; x < pos.getX() + sizeX+1; x++)
        {
            for(int z = pos.getZ() - sizeZ; z < pos.getZ() + sizeZ+1; z++)
            {
                BlockPos newPos = new BlockPos(x, pos.getY(), z);

                if((x - pos.getX()) * (x - pos.getX()) + (z - pos.getZ()) * (z - pos.getZ()) < sizeX*sizeZ-1+random.nextInt(4))
                {
                	Block previousBlock = level.getBlockState(newPos).getBlock();
                	if (previousBlock == Blocks.AIR)
                		level.setBlock(newPos, this.getEtherBlockState(), 0);
                }
                else
                {
                	if (level.getBlockState(newPos).getBlock() != this.getEtherBlock() && random.nextDouble() > 0.95)
                	{
                    	int newSizeX = chooseRandomSize(this.getSmallMinSize(), this.getSmallMaxSize(), random);
                        int newSizeZ = chooseRandomSize(this.getSmallMinSize(), this.getSmallMaxSize(), random);
                		generateThirdEllipsis(level, random, origin, newSizeX, newSizeZ, newPos);
                	}
                }
            }
        }
    }
    
    default void generateThirdEllipsis(WorldGenLevel level, RandomSource random, BlockPos origin, int sizeX, int sizeZ, BlockPos pos)
    {
    	for(int x = pos.getX() - sizeX; x < pos.getX() + sizeX+1; x++)
        {
            for(int z = pos.getZ() - sizeZ; z < pos.getZ() + sizeZ+1; z++)
            {
                BlockPos newPos = new BlockPos(x, pos.getY(), z);

                if((x - pos.getX()) * (x - pos.getX()) + (z - pos.getZ()) * (z - pos.getZ()) < sizeX*sizeZ-1+random.nextInt(4))
                {
                	Block previousBlock = level.getBlockState(newPos).getBlock();
                	if (previousBlock == Blocks.AIR)
                		level.setBlock(newPos, this.getEtherBlockState(), 0);
                }
                else
                {
                	if (level.getBlockState(newPos).getBlock() != this.getEtherBlock() && random.nextDouble() > 0.95)
                	{
                    	int newSizeX = chooseRandomSize(this.getSmallMinSize(), this.getSmallMaxSize(), random);
                        int newSizeZ = chooseRandomSize(this.getSmallMinSize(), this.getSmallMaxSize(), random);
                		generateLastEllipsis(level, random, origin, newSizeX, newSizeZ, newPos);
                	}
                }
            }
        }
    }
    
    default void generateLastEllipsis(WorldGenLevel level, RandomSource random, BlockPos origin, int sizeX, int sizeZ, BlockPos pos)
    {
    	for(int x = pos.getX() - sizeX; x < pos.getX() + sizeX+1; x++)
        {
            for(int z = pos.getZ() - sizeZ; z < pos.getZ() + sizeZ+1; z++)
            {
                BlockPos newPos = new BlockPos(x, pos.getY(), z);

                if((x - pos.getX()) * (x - pos.getX()) + (z - pos.getZ()) * (z - pos.getZ()) < sizeX*sizeZ-1+random.nextInt(3))
                {
                	Block previousBlock = level.getBlockState(newPos).getBlock();
                	if (previousBlock == Blocks.AIR && FeatureHelper.isBlockPosInFeatureRegion(FeatureHelper.getFeatureCenter(origin), newPos))
                    {
                        level.setBlock(newPos, this.getEtherBlockState(), 0);
                    }
                }
            }
        }
    }
}