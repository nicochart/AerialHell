package fr.factionbedrock.aerialhell.Block.DirtAndVariants;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellPlacedFeatures;
import fr.factionbedrock.aerialhell.Util.BlockHelper;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.feature.PlacedFeature;

import java.util.Optional;

public class StellarGrassBlock extends AerialHellGrassBlock
{
	public StellarGrassBlock(AbstractBlock.Settings settings) {super(settings);}

	@Override protected Optional<RegistryEntry.Reference<PlacedFeature>> getBonemealFeature(ServerWorld world)
	{
		return world.getRegistryManager().get(RegistryKeys.PLACED_FEATURE).getEntry(AerialHellPlacedFeatures.STELLAR_GRASS_BONEMEAL);
	}

	@Override
	public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random rand)
	{
		if (!BlockHelper.canBeGrass(state, world, pos))
		{
			world.setBlockState(pos, AerialHellBlocks.STELLAR_DIRT.getDefaultState());
		}
		else
		{
			if (world.getLightLevel(pos.up()) >= 9)
			{
				for(int i = 0; i < 4; ++i)
				{
					BlockPos blockpos = pos.add(rand.nextInt(3) - 1, rand.nextInt(5) - 3, rand.nextInt(3) - 1);
					BlockState blockstate;
					if (world.getBlockState(blockpos).getBlock() == AerialHellBlocks.STELLAR_DIRT)
					{
						blockstate = AerialHellBlocks.STELLAR_GRASS_BLOCK.getDefaultState();
					}
					else //CHISELED_STELLAR_DIRT
					{
						blockstate = AerialHellBlocks.CHISELED_STELLAR_GRASS_BLOCK.getDefaultState();
					}
					
					if ((world.getBlockState(blockpos).isOf(AerialHellBlocks.STELLAR_DIRT) || world.getBlockState(blockpos).isOf(AerialHellBlocks.CHISELED_STELLAR_DIRT)) && BlockHelper.grassCanPropagate(blockstate, world, blockpos))
					{
						world.setBlockState(blockpos, blockstate.with(SNOWY, world.getBlockState(blockpos.up()).isOf(Blocks.SNOW)));
					}
				}
			}
		}
	}
}