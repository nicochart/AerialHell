package fr.factionbedrock.aerialhell.Block.DirtAndVariants;

import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellPlacedFeatures;
import fr.factionbedrock.aerialhell.Util.BlockHelper;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.server.level.ServerLevel;

import java.util.Optional;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;

public class ShadowGrassBlock extends AerialHellGrassBlock
{
	public ShadowGrassBlock(BlockBehaviour.Properties properties)
	{
		super(properties);
		this.registerDefaultState(this.defaultBlockState());
	}

	@Override protected Optional<Holder.Reference<PlacedFeature>> getBonemealFeature(ServerLevel level)
	{
		return level.registryAccess().lookupOrThrow(Registries.PLACED_FEATURE).get(AerialHellPlacedFeatures.SHADOW_GRASS_BONEMEAL);
	}

	@Override
	public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource rand)
	{
		if (!BlockHelper.canBeGrass(state, level, pos))
		{
			if (!level.isAreaLoaded(pos, 3)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light and spreading
			level.setBlockAndUpdate(pos, AerialHellBlocks.STELLAR_DIRT.get().defaultBlockState());
		}
		else if (BlockHelper.isCorrupted(level, pos) && BlockHelper.surroundingsPreventCorruption(level, pos, BlockHelper.CorruptionType.GRASS))
		{
			if (!level.isAreaLoaded(pos, 3)) return;
			BlockHelper.uncorrupt(level, pos);
		}
		else
		{
			for(int i = 0; i < 4; ++i)
			{
				BlockPos blockpos = pos.offset(rand.nextInt(3) - 1, rand.nextInt(5) - 3, rand.nextInt(3) - 1);
				BlockState blockstate = AerialHellBlocks.SHADOW_GRASS_BLOCK.get().defaultBlockState();

				if (level.getMaxLocalRawBrightness(pos.above()) >= 9 && level.getBlockState(blockpos).is(AerialHellBlocks.STELLAR_DIRT.get()) && BlockHelper.grassCanPropagate(blockstate, level, blockpos))
				{
					level.setBlockAndUpdate(blockpos, blockstate.setValue(SNOWY, level.getBlockState(blockpos.above()).is(Blocks.SNOW)));
					BlockHelper.corruptBiome(level, blockpos, 1);
				}
				else
				{
					if (!BlockHelper.isCorrupted(level, blockpos))
					{
						BlockHelper.tryCorrupt(level, blockpos, rand);
					}
					else //isCorrupted
					{
						if (BlockHelper.surroundingsPreventCorruption(level, blockpos, BlockHelper.CorruptionType.ANY)) {BlockHelper.uncorrupt(level, blockpos);}
					}
				}
			}
		}
	}
}