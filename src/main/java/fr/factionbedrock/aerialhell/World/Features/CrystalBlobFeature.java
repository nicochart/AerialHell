package fr.factionbedrock.aerialhell.World.Features;

import com.mojang.serialization.Codec;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellConfiguredFeatures;
import fr.factionbedrock.aerialhell.World.Features.Config.CrystalBlobConfig;
import net.minecraft.block.BlockState;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

import java.util.List;

public class CrystalBlobFeature extends Feature<CrystalBlobConfig> implements DungeonSensitiveFeatureCheck
{
	public CrystalBlobFeature(Codec<CrystalBlobConfig> codec) {super(codec);}

	@Override public List<RegistryKey<ConfiguredFeature<?, ?>>> getAssociatedConfiguredFeatures() {return AerialHellConfiguredFeatures.Lists.CRYSTAL_BLOB_LIST;}

	@Override public boolean generate(FeatureContext<CrystalBlobConfig> context)
	{
		BlockStateProvider blockProvider = context.getConfig().crystalStateProvider();
		BlockPos pos = context.getOrigin(); StructureWorldAccess world = context.getWorld(); Random rand = context.getRandom();
		if (!world.isAir(pos)) {return false;}
		else
		{
			BlockState blockstate = world.getBlockState(pos.down());
		    if (!blockstate.isIn(AerialHellTags.Blocks.STELLAR_DIRT) || !this.isDungeonSensitiveValid(context)) {return false;}
		    else
		    {
				place(pos, rand, world, blockProvider);
		    	return true;
		    }
		}
	}

	private void place(BlockPos pos, Random rand, StructureWorldAccess world, BlockStateProvider blockProvider)
	{
		world.setBlockState(pos, blockProvider.get(rand, pos), 2);

		for(int i = 0; i < 1700; ++i)
		{
			BlockPos blockpos;
			if (i < 1400)
			{
				blockpos = pos.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(12), rand.nextInt(8) - rand.nextInt(8));
			}
			else
			{
				blockpos = pos.add(rand.nextInt(9) - rand.nextInt(9), - rand.nextInt(3), rand.nextInt(9) - rand.nextInt(9));
			}
			if (world.getBlockState(blockpos).isAir())
			{
				int j = 0;

				for(Direction direction : Direction.values())
				{
					if (world.getBlockState(blockpos.offset((direction))).isIn(AerialHellTags.Blocks.NATURAL_CRYSTAL_BLOCK)) {++j;}

					if (j > 1) {break;}
				}

				if (j == 1) {world.setBlockState(blockpos, blockProvider.get(rand, blockpos), 2);}
			}
		}
	}
}
