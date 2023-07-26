package fr.factionbedrock.aerialhell.World.Features;

import java.util.Random;
import java.util.function.Supplier;

import com.mojang.serialization.Codec;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class StellarStoneCrystalBlobFeature extends Feature<NoneFeatureConfiguration>
{
	public Supplier<Block> crystalBlock;
	public StellarStoneCrystalBlobFeature(Supplier<Block> block, Codec<NoneFeatureConfiguration> codec) {super(codec); crystalBlock=block;}

	@Override public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context)
	{
		BlockPos pos = context.origin(); WorldGenLevel world = context.level(); RandomSource rand = context.random();
		int x = pos.getX(), y=10, z=pos.getZ();
		int ymax = 160;
		BlockPos blockpos;
		BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();
		mutablePos.set(new BlockPos(x, y, z));
		boolean hasSupport = false;
		while (!hasSupport && y < ymax)
		{
			if (hasSupportToGenerate(mutablePos, world)) {hasSupport = true;}
			else {y++; mutablePos.set(new BlockPos(x, y, z));}
		}
		if (y==ymax) {return false;}
		if (!squareHasRoof(mutablePos, world)) {return false;}
		pos = mutablePos;
		
		if (rand.nextInt(160) < y) {return false;}

		world.setBlock(pos, crystalBlock.get().defaultBlockState(), 2);
        for(int i = 0; i < 300; ++i)
        {
        	blockpos = pos.offset(rand.nextInt(2) - rand.nextInt(2), rand.nextInt(5), rand.nextInt(2) - rand.nextInt(2)); //55855

            if (world.isEmptyBlock(blockpos))
            {
            	int j = 0;

	            for(Direction direction : Direction.values())
	            {
		            if (world.getBlockState(blockpos.relative(direction)).is(crystalBlock.get())) {++j;}

		            if (j > 1) {break;}
	            }

	            if (j == 1) {world.setBlock(blockpos, crystalBlock.get().defaultBlockState(), 2);}
            }
        }
	    return true;
	}
	
	private boolean hasSupportToGenerate(BlockPos pos, WorldGenLevel reader)
	{
		BlockState blockstateDown = reader.getBlockState(pos.below());
		if (isValidFloorState(blockstateDown) && hasAirColumnAbove(pos, reader, 4)) {return true;}
		else {return false;}
	}
		
	private boolean isValidFloorState(BlockState state)
	{
		return state.is(AerialHellTags.Blocks.STELLAR_DIRT) || state.is(AerialHellBlocksAndItems.SLIPPERY_SAND.get());
	}
	
	private boolean squareHasRoof(BlockPos pos, WorldGenLevel reader)
	{
		int x,z;
		BlockPos blockpos;
		for (x=-4;x<5;x++)
		{
			for (z=-4;z<5;z++)
			{
				blockpos = pos.offset(x, 0, z);
				if (!hasAnyBlockAbove(blockpos, reader)) {return false;}
			}
		}
		return true;
	}
	
	private boolean hasAnyBlockAbove(BlockPos pos, WorldGenLevel reader)
	{
		for (BlockPos blockpos = pos.above(); blockpos.getY() < 250; blockpos = blockpos.above())
		{
			if (!reader.isEmptyBlock(blockpos)) {return true;}
		}
		return false;
	}
	
	private boolean hasAirColumnAbove(BlockPos pos, WorldGenLevel reader, int dy)
	{
		for (BlockPos blockpos = pos.above(); blockpos.getY() < pos.getY()+dy; blockpos = blockpos.above())
		{
			if (!reader.isEmptyBlock(blockpos)) {return false;}
		}
		return true;
	}
}
