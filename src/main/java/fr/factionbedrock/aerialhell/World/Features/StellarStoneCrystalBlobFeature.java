package fr.factionbedrock.aerialhell.World.Features;

import java.util.Random;
import java.util.function.Supplier;

import com.mojang.serialization.Codec;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class StellarStoneCrystalBlobFeature extends Feature<NoFeatureConfig>
{
	public Supplier<Block> crystalBlock;
	public StellarStoneCrystalBlobFeature(Supplier<Block> block, Codec<NoFeatureConfig> codec) {super(codec); crystalBlock=block;}
	
	public boolean generate(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config)
	{
		int x = pos.getX(), y=10, z=pos.getZ();
		int ymax = 160;
		BlockPos blockpos;
		BlockPos.Mutable mutablePos = new BlockPos.Mutable();
		mutablePos.setPos(new BlockPos(x, y, z));
		boolean hasSupport = false;
		while (!hasSupport && y < ymax)
		{
			if (hasSupportToGenerate(mutablePos, reader)) {hasSupport = true;}
			else {y++; mutablePos.setPos(new BlockPos(x, y, z));}
		}
		if (y==ymax) {return false;}
		if (!squareHasRoof(mutablePos, reader)) {return false;}
		pos = mutablePos;
		
		if (rand.nextInt(160) < y) {return false;}
		
    	reader.setBlockState(pos, crystalBlock.get().getDefaultState(), 2);
        for(int i = 0; i < 300; ++i)
        {
        	blockpos = pos.add(rand.nextInt(2) - rand.nextInt(2), rand.nextInt(5), rand.nextInt(2) - rand.nextInt(2)); //55855

            if (reader.isAirBlock(blockpos))
            {
            	int j = 0;

	            for(Direction direction : Direction.values())
	            {
		            if (reader.getBlockState(blockpos.offset(direction)).isIn(crystalBlock.get())) {++j;}

		            if (j > 1) {break;}
	            }

	            if (j == 1) {reader.setBlockState(blockpos, crystalBlock.get().getDefaultState(), 2);}
            }
        }
	    return true;
	}
	
	private boolean hasSupportToGenerate(BlockPos pos, ISeedReader reader)
	{
		BlockState blockstateDown = reader.getBlockState(pos.down());
		if (isValidFloorState(blockstateDown) && hasAirColumnAbove(pos, reader, 4)) {return true;}
		else {return false;}
	}
		
	private boolean isValidFloorState(BlockState state)
	{
		return state.isIn(AerialHellTags.Blocks.STELLAR_DIRT) || state.isIn(AerialHellBlocksAndItems.SLIPPERY_SAND.get());
	}
	
	private boolean squareHasRoof(BlockPos pos, ISeedReader reader)
	{
		int x,z;
		BlockPos blockpos;
		for (x=-4;x<5;x++)
		{
			for (z=-4;z<5;z++)
			{
				blockpos = pos.add(x, 0, z);
				if (!hasAnyBlockAbove(blockpos, reader)) {return false;}
			}
		}
		return true;
	}
	
	private boolean hasAnyBlockAbove(BlockPos pos, ISeedReader reader)
	{
		for (BlockPos blockpos = pos.up(); blockpos.getY() < 250; blockpos = blockpos.up())
		{
			if (!reader.isAirBlock(blockpos)) {return true;}
		}
		return false;
	}
	
	private boolean hasAirColumnAbove(BlockPos pos, ISeedReader reader, int dy)
	{
		for (BlockPos blockpos = pos.up(); blockpos.getY() < pos.getY()+dy; blockpos = blockpos.up())
		{
			if (!reader.isAirBlock(blockpos)) {return false;}
		}
		return true;
	}
}
