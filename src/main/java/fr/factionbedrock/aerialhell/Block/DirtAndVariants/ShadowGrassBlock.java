package fr.factionbedrock.aerialhell.Block.DirtAndVariants;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellPlacedFeatures;
import fr.factionbedrock.aerialhell.Util.BlockHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class ShadowGrassBlock extends AerialHellGrassBlock
{
	public ShadowGrassBlock(Properties settings) {super(settings);}

	@Override protected ResourceKey<PlacedFeature> getBonemealFeature()
	{
		return AerialHellPlacedFeatures.SHADOW_GRASS_BONEMEAL;
	}

	@Override
	public void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource rand)
	{
		if (!BlockHelper.canBeGrass(state, world, pos))
		{
			world.setBlockAndUpdate(pos, AerialHellBlocks.STELLAR_DIRT.defaultBlockState());
		}
		else if (BlockHelper.isCorrupted(world, pos) && BlockHelper.surroundingsPreventCorruption(world, pos, BlockHelper.CorruptionType.GRASS))
		{
			BlockHelper.uncorrupt(world, pos);
		}
		else
		{
			for(int i = 0; i < 4; ++i)
			{
				BlockPos blockpos = pos.offset(rand.nextInt(3) - 1, rand.nextInt(5) - 3, rand.nextInt(3) - 1);
				BlockState blockstate = AerialHellBlocks.SHADOW_GRASS_BLOCK.defaultBlockState();

				if (world.getMaxLocalRawBrightness(pos.above()) >= 9 && world.getBlockState(blockpos).is(AerialHellBlocks.STELLAR_DIRT) && BlockHelper.grassCanPropagate(blockstate, world, blockpos))
				{
					world.setBlockAndUpdate(blockpos, blockstate.setValue(SNOWY, world.getBlockState(blockpos.above()).is(Blocks.SNOW)));
					BlockHelper.corruptBiome(world, blockpos, 1);
				}
				else
				{
					if (!BlockHelper.isCorrupted(world, blockpos))
					{
						BlockHelper.tryCorrupt(world, blockpos, rand);
					}
					else //isCorrupted
					{
						if (BlockHelper.surroundingsPreventCorruption(world, blockpos, BlockHelper.CorruptionType.ANY)) {BlockHelper.uncorrupt(world, blockpos);}
					}
				}
			}
		}
	}
}