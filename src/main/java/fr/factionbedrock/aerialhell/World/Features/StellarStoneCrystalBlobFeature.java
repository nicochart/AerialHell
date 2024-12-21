package fr.factionbedrock.aerialhell.World.Features;

import java.util.function.Supplier;

import com.mojang.serialization.Codec;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class StellarStoneCrystalBlobFeature extends Feature<DefaultFeatureConfig>
{
	public Supplier<Block> crystalBlock;
	public StellarStoneCrystalBlobFeature(Supplier<Block> block, Codec<DefaultFeatureConfig> codec) {super(codec); crystalBlock=block;}

	@Override public boolean generate(FeatureContext<DefaultFeatureConfig> context)
	{
		BlockPos pos = context.getOrigin(); StructureWorldAccess world = context.getWorld(); Random rand = context.getRandom();
		int x = pos.getX(), y=10, z=pos.getZ();
		int ymax = 160;
		BlockPos blockpos;
		BlockPos.Mutable mutablePos = new BlockPos.Mutable();
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

		world.setBlockState(pos, crystalBlock.get().getDefaultState(), 2);
        for(int i = 0; i < 300; ++i)
        {
        	blockpos = pos.add(rand.nextInt(2) - rand.nextInt(2), rand.nextInt(5), rand.nextInt(2) - rand.nextInt(2)); //55855

            if (world.isAir(blockpos))
            {
            	int j = 0;

	            for(Direction direction : Direction.values())
	            {
		            if (world.getBlockState(blockpos.offset(direction)).isOf(crystalBlock.get())) {++j;}

		            if (j > 1) {break;}
	            }

	            if (j == 1) {world.setBlockState(blockpos, crystalBlock.get().getDefaultState(), 2);}
            }
        }
	    return true;
	}
	
	private boolean hasSupportToGenerate(BlockPos pos, StructureWorldAccess reader)
	{
		BlockState blockstateDown = reader.getBlockState(pos.down());
		if (isValidFloorState(blockstateDown) && hasAirColumnAbove(pos, reader, 4)) {return true;}
		else {return false;}
	}
		
	private boolean isValidFloorState(BlockState state)
	{
		return state.isIn(AerialHellTags.Blocks.STELLAR_DIRT) || state.isOf(AerialHellBlocks.SLIPPERY_SAND);
	}
	
	private boolean squareHasRoof(BlockPos pos, StructureWorldAccess reader)
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
	
	private boolean hasAnyBlockAbove(BlockPos pos, StructureWorldAccess reader)
	{
		for (BlockPos blockpos = pos.up(); blockpos.getY() < 250; blockpos = blockpos.up())
		{
			if (!reader.isAir(blockpos)) {return true;}
		}
		return false;
	}
	
	private boolean hasAirColumnAbove(BlockPos pos, StructureWorldAccess reader, int dy)
	{
		for (BlockPos blockpos = pos.up(); blockpos.getY() < pos.getY()+dy; blockpos = blockpos.up())
		{
			if (!reader.isAir(blockpos)) {return false;}
		}
		return true;
	}
}
