package fr.factionbedrock.aerialhell.World.Features;

import java.util.Random;

import com.mojang.serialization.Codec;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellTags;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class StellarGrassInShadowGrassFeature extends AbstractFloorEllipsisFeature
{
	public StellarGrassInShadowGrassFeature(Codec<NoFeatureConfig> codec) {super(codec);}

	@Override protected int getEllipsisSizeX(Random rand) {return 4 + (int)rand.nextDouble() * 7;}
	@Override protected int getEllipsisSizeZ(Random rand) {return 4 + (int)rand.nextDouble() * 7;}
	
	@Override
	protected boolean canGenerate(ISeedReader reader, BlockPos pos)
	{
		if (reader.isAirBlock(pos) && reader.getBlockState(pos.down()).isIn(AerialHellTags.Blocks.STELLAR_DIRT)) {return true;}
		else {return false;}
	}
	
	@Override
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
		if((x - xc) * (x - xc) + (z - zc) * (z - zc) < elipsisSizeX*elipsisSizeZ-4)
		{
			if (rdm > 0.5) {return AerialHellBlocksAndItems.STELLAR_GRASS_BLOCK.get().getDefaultState();}
		}
		else if((x - xc) * (x - xc) + (z - zc) * (z - zc) < elipsisSizeX*elipsisSizeZ-1+rand.nextInt(5))
		{
			if (rdm > 0.8) {return AerialHellBlocksAndItems.STELLAR_GRASS_BLOCK.get().getDefaultState();}
		}
		return previousBlockState;
	}
}
