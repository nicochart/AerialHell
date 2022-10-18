package fr.factionbedrock.aerialhell.World.Features;

import java.util.Random;

import com.mojang.serialization.Codec;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellTags;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class StellarCoarseFloorEllipsoidFeature extends Feature<NoFeatureConfig>
{
	public StellarCoarseFloorEllipsoidFeature(Codec<NoFeatureConfig> codec) {super(codec);}

	public boolean generate(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config)
	{
		if (!reader.isAirBlock(pos) || !hasAnyBlockAbove(pos, reader)) {return false;}
		BlockPos newPos;
		BlockState blockstate = reader.getBlockState(pos.down());
		
	    if (!blockstate.isIn(AerialHellTags.Blocks.STELLAR_DIRT)) {return false;}
	    else
	    {
	    	int x,y,z;
	    	int sizeX = 6 + (int)rand.nextDouble() * 6;
	        int sizeZ = 6 + (int)rand.nextDouble() * 6;
	        
	    	for(x = pos.getX() - sizeX; x < pos.getX() + sizeX+1; x++)
	        {
	            for(z = pos.getZ() - sizeZ; z < pos.getZ() + sizeZ+1; z++)
	            {
	            	for (y = pos.getY() - 3; y < pos.getY() + 4; y++) //searching the surface
	            	{
	            		newPos = new BlockPos(x, y, z);
	            		if (reader.getBlockState(newPos).isIn(AerialHellBlocksAndItems.STELLAR_GRASS_BLOCK.get()) || reader.getBlockState(newPos).isIn(AerialHellBlocksAndItems.SHADOW_GRASS_BLOCK.get())) //checking for surface
	            		{
	            			double rdm = rand.nextDouble();
	            			if((x - pos.getX()) * (x - pos.getX()) + (z - pos.getZ()) * (z - pos.getZ()) < sizeX*sizeZ-4 && rdm > 0.2)
	            			{
	            				if (rdm > 0.6)
	            				{
	            					reader.setBlockState(newPos, AerialHellBlocksAndItems.STELLAR_COARSE_DIRT.get().getDefaultState(), 2);
	            				}
	            				else if (rdm > 0.4)
	            				{
	            					reader.setBlockState(newPos, AerialHellBlocksAndItems.MOSSY_STELLAR_COBBLESTONE.get().getDefaultState(), 2);
	            				}
	            				else
	            				{
	            					reader.setBlockState(newPos, AerialHellBlocksAndItems.STELLAR_COBBLESTONE.get().getDefaultState(), 2);
	            				}
	            			}
	            			else if((x - pos.getX()) * (x - pos.getX()) + (z - pos.getZ()) * (z - pos.getZ()) < sizeX*sizeZ-1+rand.nextInt(5) && rdm > 0.7)
	            			{
	            				if (rdm > 0.85)
	            				{
	            					reader.setBlockState(newPos, AerialHellBlocksAndItems.STELLAR_COARSE_DIRT.get().getDefaultState(), 2);
	            				}
	            				else
	            				{
	            					reader.setBlockState(newPos, AerialHellBlocksAndItems.MOSSY_STELLAR_COBBLESTONE.get().getDefaultState(), 2);
	            				}
	            			}
	            			y += 7; //if we found stellar grass block <=> we found surface, skip other y values
	            		}
	            	}
	            }
	        }
	    	return true;
	    }
	}
	
	private boolean hasAnyBlockAbove(BlockPos pos, ISeedReader reader)
	{
		for (BlockPos blockpos1 = pos.up(); blockpos1.getY() < 250; blockpos1 = blockpos1.up())
		{
			if (!reader.isAirBlock(blockpos1)) {return true;}
		}
		return false;
	}
}
