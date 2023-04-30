package fr.factionbedrock.aerialhell.World.Features;

import java.util.Random;

import com.mojang.serialization.Codec;

import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public abstract class AbstractFloorEllipsisFeature extends Feature<NoneFeatureConfiguration>
{
	public AbstractFloorEllipsisFeature(Codec<NoneFeatureConfiguration> codec) {super(codec);}
	
	abstract protected int getEllipsisSizeX(Random rand);
	abstract protected int getEllipsisSizeZ(Random rand);
	abstract protected boolean canGenerate(WorldGenLevel reader, BlockPos pos);
	abstract protected boolean isFloor(BlockState blockState);
	abstract protected BlockState getBlockStateForPlacement(BlockState previousBlockState, BlockPos centerPos, BlockPos placementPos, int elipsisSizeX, int elipsisSizeZ, Random rand);

	@Override public boolean place(NoneFeatureConfiguration config, WorldGenLevel reader, ChunkGenerator generator, Random rand, BlockPos pos)
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
	            			reader.setBlock(placementPos, placementBlockState, 2);
	            			y += 7; //if we found stellar grass block <=> we found surface, skip other y values
	            		}
	            	}
	            }
	        }
	    	return true;
	    }
		else {return false;}
	}
	
	protected boolean hasAnyBlockAbove(BlockPos pos, WorldGenLevel reader)
	{
		for (BlockPos blockpos1 = pos.above(); blockpos1.getY() < 250; blockpos1 = blockpos1.above())
		{
			if (!reader.isEmptyBlock(blockpos1)) {return true;}
		}
		return false;
	}
}
