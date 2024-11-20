package fr.factionbedrock.aerialhell.Block.DirtAndVariants;

import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellPlacedFeatures;
import fr.factionbedrock.aerialhell.Util.BlockHelper;
import net.minecraft.block.AbstractBlock;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.block.BonemealableBlock;

import java.util.List;
import java.util.Optional;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;
import org.jetbrains.annotations.Nullable;

public class StellarGrassBlock extends AerialHellGrassBlock
{
	public StellarGrassBlock(AbstractBlock.Settings settings)
	{
		super(settings);
		this.registerDefaultState(this.defaultBlockState());
	}

	@Override protected Optional<Holder.Reference<PlacedFeature>> getBonemealFeature(ServerLevel level)
	{
		return level.registryAccess().registryOrThrow(Registries.PLACED_FEATURE).getHolder(AerialHellPlacedFeatures.STELLAR_GRASS_BONEMEAL);
	}

	@Override
	public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource rand)
	{
		if (!BlockHelper.canBeGrass(state, level, pos))
		{
			if (!level.isAreaLoaded(pos, 3)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light and spreading
			level.setBlockAndUpdate(pos, AerialHellBlocksAndItems.STELLAR_DIRT.get().defaultBlockState());
		}
		else
		{
			if (level.getMaxLocalRawBrightness(pos.above()) >= 9)
			{
				for(int i = 0; i < 4; ++i)
				{
					BlockPos blockpos = pos.offset(rand.nextInt(3) - 1, rand.nextInt(5) - 3, rand.nextInt(3) - 1);
					BlockState blockstate;
					if (level.getBlockState(blockpos).getBlock() == AerialHellBlocksAndItems.STELLAR_DIRT.get())
					{
						blockstate = AerialHellBlocksAndItems.STELLAR_GRASS_BLOCK.get().defaultBlockState();
					}
					else //CHISELED_STELLAR_DIRT
					{
						blockstate = AerialHellBlocksAndItems.CHISELED_STELLAR_GRASS_BLOCK.get().defaultBlockState();
					}
					
					if ((level.getBlockState(blockpos).is(AerialHellBlocksAndItems.STELLAR_DIRT.get()) || level.getBlockState(blockpos).is(AerialHellBlocksAndItems.CHISELED_STELLAR_DIRT.get())) && BlockHelper.grassCanPropagate(blockstate, level, blockpos))
					{
						level.setBlockAndUpdate(blockpos, blockstate.setValue(SNOWY, level.getBlockState(blockpos.above()).is(Blocks.SNOW)));
					}
				}
			}
		}
	}
}