package fr.factionbedrock.aerialhell.World.Features;

import java.util.Random;

import com.mojang.serialization.Codec;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellTags;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class StellarCoarseFloorInDarkAreasFeature extends AbstractFloorEllipsisFeature
{
	public StellarCoarseFloorInDarkAreasFeature(Codec<NoFeatureConfig> codec) {super(codec);}

	@Override protected int getEllipsisSizeX(Random rand) {return 6 + (int)rand.nextDouble() * 6;}
	@Override protected int getEllipsisSizeZ(Random rand) {return 6 + (int)rand.nextDouble() * 6;}
	
	@Override
	protected boolean canGenerate(ISeedReader reader, BlockPos pos)
	{
		if (!reader.isAirBlock(pos) || !super.hasAnyBlockAbove(pos, reader)) {return false;}
		BlockState blockstateDown = reader.getBlockState(pos.down());
		if (!blockstateDown.isIn(AerialHellTags.Blocks.STELLAR_DIRT)) {return false;}
		return true;
	}
	
	protected boolean isFloor(BlockState blockState)
	{
		if (blockState.isIn(AerialHellBlocksAndItems.STELLAR_GRASS_BLOCK.get()) || blockState.isIn(AerialHellBlocksAndItems.SHADOW_GRASS_BLOCK.get())) {return true;}
		else {return false;}
	}
	
	@Override
	protected BlockState getBlockStateForPlacement(BlockState previousBlockState, BlockPos centerPos, BlockPos placementPos, int elipsisSizeX, int elipsisSizeZ, Random rand)
	{
		int x = placementPos.getX(), z = placementPos.getZ();
		int xc = centerPos.getX(), zc = centerPos.getZ();
		double rdm = rand.nextDouble();
		if((x - xc) * (x - xc) + (z - zc) * (z - zc) < elipsisSizeX*elipsisSizeZ-4 && rdm > 0.2)
		{
			if (rdm > 0.6) {return AerialHellBlocksAndItems.STELLAR_COARSE_DIRT.get().getDefaultState();}
			else if (rdm > 0.4) {return AerialHellBlocksAndItems.MOSSY_STELLAR_COBBLESTONE.get().getDefaultState();}
			else {return  AerialHellBlocksAndItems.STELLAR_COBBLESTONE.get().getDefaultState();}
		}
		else if((x - xc) * (x - xc) + (z - zc) * (z - zc) < elipsisSizeX*elipsisSizeZ-1+rand.nextInt(5) && rdm > 0.7)
		{
			if (rdm > 0.85) {return AerialHellBlocksAndItems.STELLAR_COARSE_DIRT.get().getDefaultState();}
			else {return AerialHellBlocksAndItems.MOSSY_STELLAR_COBBLESTONE.get().getDefaultState();}
		}
		else {return previousBlockState;}
	}
}
