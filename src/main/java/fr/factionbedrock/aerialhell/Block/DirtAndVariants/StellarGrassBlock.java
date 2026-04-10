package fr.factionbedrock.aerialhell.Block.DirtAndVariants;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellPlacedFeatures;
import fr.factionbedrock.aerialhell.Util.BlockHelper;
import java.util.Optional;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class StellarGrassBlock extends AerialHellGrassBlock
{
	public StellarGrassBlock(BlockBehaviour.Properties settings) {super(settings);}

	@Override protected Optional<Holder.Reference<PlacedFeature>> getBonemealFeature(ServerLevel world)
	{
		return world.registryAccess().lookupOrThrow(Registries.PLACED_FEATURE).get(AerialHellPlacedFeatures.STELLAR_GRASS_BONEMEAL);
	}

	@Override
	public void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource rand)
	{
		if (!BlockHelper.canBeGrass(state, world, pos))
		{
			world.setBlockAndUpdate(pos, AerialHellBlocks.STELLAR_DIRT.defaultBlockState());
		}
		else
		{
			if (world.getMaxLocalRawBrightness(pos.above()) >= 9)
			{
				for(int i = 0; i < 4; ++i)
				{
					BlockPos blockpos = pos.offset(rand.nextInt(3) - 1, rand.nextInt(5) - 3, rand.nextInt(3) - 1);
					BlockState blockstate;
					if (world.getBlockState(blockpos).getBlock() == AerialHellBlocks.STELLAR_DIRT)
					{
						blockstate = AerialHellBlocks.STELLAR_GRASS_BLOCK.defaultBlockState();
					}
					else //CHISELED_STELLAR_DIRT
					{
						blockstate = AerialHellBlocks.CHISELED_STELLAR_GRASS_BLOCK.defaultBlockState();
					}
					
					if ((world.getBlockState(blockpos).is(AerialHellBlocks.STELLAR_DIRT) || world.getBlockState(blockpos).is(AerialHellBlocks.CHISELED_STELLAR_DIRT)) && BlockHelper.grassCanPropagate(blockstate, world, blockpos))
					{
						world.setBlockAndUpdate(blockpos, blockstate.setValue(SNOWY, world.getBlockState(blockpos.above()).is(Blocks.SNOW)));
					}
				}
			}
		}
	}
}