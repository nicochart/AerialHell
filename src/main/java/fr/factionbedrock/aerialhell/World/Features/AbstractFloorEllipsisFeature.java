package fr.factionbedrock.aerialhell.World.Features;

import java.util.Random;

import com.mojang.serialization.Codec;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public abstract class AbstractFloorEllipsisFeature extends Feature<NoFeatureConfig>
{
	public AbstractFloorEllipsisFeature(Codec<NoFeatureConfig> codec) {super(codec);}
	
	abstract protected int getEllipsisSizeX(Random rand);
	abstract protected int getEllipsisSizeZ(Random rand);
	abstract protected boolean canGenerate(ISeedReader reader, BlockPos pos);
	abstract protected boolean isFloor(BlockState blockState);
	abstract protected BlockState getBlockStateForPlacement(BlockState previousBlockState, BlockPos centerPos, BlockPos placementPos, int elipsisSizeX, int elipsisSizeZ, Random rand);
	
	public boolean generate(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config)
	{
		BlockPos placementPos;
		
		if (canGenerate(reader, pos))
	    {
	    	int x,y,z;
	    	int sizeX = getEllipsisSizeX(rand);
	        int sizeZ = getEllipsisSizeZ(rand);
	        
	    	for(x = pos.getX() - sizeX; x < pos.getX() + sizeX+1; x++)
	        {
	            for(z = pos.getZ() - sizeZ; z < pos.getZ() + sizeZ+1; z++)
	            {
	            	for (y = pos.getY() - 3; y < pos.getY() + 4; y++) //searching the surface
	            	{
	            		placementPos = new BlockPos(x, y, z);
	            		BlockState previousBlockState = reader.getBlockState(placementPos);
	            		if (isFloor(previousBlockState)) //checking for surface
	            		{
	            			BlockState placementBlockState = getBlockStateForPlacement(previousBlockState, pos, placementPos, sizeX, sizeZ, rand);
	            			reader.setBlockState(placementPos, placementBlockState, 2);
	            			y += 7; //if we found stellar grass block <=> we found surface, skip other y values
	            		}
	            	}
	            }
	        }
	    	return true;
	    }
		else {return false;}
	}
	
	protected boolean hasAnyBlockAbove(BlockPos pos, ISeedReader reader)
	{
		for (BlockPos blockpos1 = pos.up(); blockpos1.getY() < 250; blockpos1 = blockpos1.up())
		{
			if (!reader.isAirBlock(blockpos1)) {return true;}
		}
		return false;
	}
}
