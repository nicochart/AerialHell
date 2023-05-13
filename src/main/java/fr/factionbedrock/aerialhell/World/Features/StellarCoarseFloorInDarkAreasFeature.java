package fr.factionbedrock.aerialhell.World.Features;

import java.util.Random;

import com.mojang.serialization.Codec;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class StellarCoarseFloorInDarkAreasFeature extends AbstractFloorEllipsisFeature
{
	public StellarCoarseFloorInDarkAreasFeature(Codec<NoneFeatureConfiguration> codec) {super(codec);}

	@Override public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context)
	{
		BlockPos pos = context.origin(); WorldGenLevel reader = context.level(); Random rand = context.random(); ChunkGenerator generator = context.chunkGenerator(); NoneFeatureConfiguration config = context.config();
		return super.place(config, reader, generator, rand, pos);
	}

	@Override protected int getEllipsisSizeX(Random rand) {return 6 + (int)rand.nextDouble() * 6;}
	@Override protected int getEllipsisSizeZ(Random rand) {return 6 + (int)rand.nextDouble() * 6;}
	
	@Override
	protected boolean canGenerate(WorldGenLevel reader, BlockPos pos)
	{
		if (!reader.isEmptyBlock(pos) || !super.hasAnyBlockAbove(pos, reader)) {return false;}
		BlockState blockstateDown = reader.getBlockState(pos.below());
		if (!blockstateDown.is(AerialHellTags.Blocks.STELLAR_DIRT)) {return false;}
		return true;
	}
	
	protected boolean isFloor(BlockState blockState)
	{
		if (blockState.is(AerialHellBlocksAndItems.STELLAR_GRASS_BLOCK.get()) || blockState.is(AerialHellBlocksAndItems.SHADOW_GRASS_BLOCK.get())) {return true;}
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
			if (rdm > 0.6) {return AerialHellBlocksAndItems.STELLAR_COARSE_DIRT.get().defaultBlockState();}
			else if (rdm > 0.4) {return AerialHellBlocksAndItems.MOSSY_STELLAR_COBBLESTONE.get().defaultBlockState();}
			else {return  AerialHellBlocksAndItems.STELLAR_COBBLESTONE.get().defaultBlockState();}
		}
		else if((x - xc) * (x - xc) + (z - zc) * (z - zc) < elipsisSizeX*elipsisSizeZ-1+rand.nextInt(5) && rdm > 0.7)
		{
			if (rdm > 0.85) {return AerialHellBlocksAndItems.STELLAR_COARSE_DIRT.get().defaultBlockState();}
			else {return AerialHellBlocksAndItems.MOSSY_STELLAR_COBBLESTONE.get().defaultBlockState();}
		}
		else {return previousBlockState;}
	}
}
