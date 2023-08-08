package fr.factionbedrock.aerialhell.World.Features;

import java.util.Random;

import com.mojang.serialization.Codec;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class


StellarGrassInShadowGrassFeature extends AbstractFloorEllipsisFeature
{
	public StellarGrassInShadowGrassFeature(Codec<NoneFeatureConfiguration> codec) {super(codec);}

	@Override public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context)
	{
		BlockPos pos = context.origin(); WorldGenLevel reader = context.level(); RandomSource rand = context.random(); ChunkGenerator generator = context.chunkGenerator(); NoneFeatureConfiguration config = context.config();
		return super.place(config, reader, generator, rand, pos);
	}

	@Override protected int getEllipsisSizeX(RandomSource rand) {return 4 + (int)rand.nextDouble() * 7;}
	@Override protected int getEllipsisSizeZ(RandomSource rand) {return 4 + (int)rand.nextDouble() * 7;}
	
	@Override
	protected boolean canGenerate(WorldGenLevel reader, BlockPos pos)
	{
		if (reader.isEmptyBlock(pos) && reader.getBlockState(pos.below()).is(AerialHellTags.Blocks.STELLAR_DIRT)) {return true;}
		else {return false;}
	}
	
	@Override
	protected boolean isFloor(BlockState blockState)
	{
		if (blockState.is(AerialHellBlocksAndItems.STELLAR_GRASS_BLOCK.get()) || blockState.is(AerialHellBlocksAndItems.SHADOW_GRASS_BLOCK.get())) {return true;}
		else {return false;}
	}
	
	@Override
	protected BlockState getBlockStateForPlacement(BlockState previousBlockState, BlockPos centerPos, BlockPos placementPos, int elipsisSizeX, int elipsisSizeZ, RandomSource rand)
	{
		int x = placementPos.getX(), z = placementPos.getZ();
		int xc = centerPos.getX(), zc = centerPos.getZ();
		double rdm = rand.nextDouble();
		if((x - xc) * (x - xc) + (z - zc) * (z - zc) < elipsisSizeX*elipsisSizeZ-4)
		{
			if (rdm > 0.5) {return AerialHellBlocksAndItems.STELLAR_GRASS_BLOCK.get().defaultBlockState();}
		}
		else if((x - xc) * (x - xc) + (z - zc) * (z - zc) < elipsisSizeX*elipsisSizeZ-1+rand.nextInt(5))
		{
			if (rdm > 0.8) {return AerialHellBlocksAndItems.STELLAR_GRASS_BLOCK.get().defaultBlockState();}
		}
		return previousBlockState;
	}
}
