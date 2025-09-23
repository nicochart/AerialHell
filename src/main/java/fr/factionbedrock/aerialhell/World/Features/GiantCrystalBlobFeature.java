package fr.factionbedrock.aerialhell.World.Features;

import com.mojang.serialization.Codec;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellConfiguredFeatures;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.List;

public class GiantCrystalBlobFeature extends Feature<DefaultFeatureConfig> implements DungeonSensitiveFeatureCheck
{
	public GiantCrystalBlobFeature(Codec<DefaultFeatureConfig> codec) {super(codec);}

	@Override public List<RegistryKey<ConfiguredFeature<?, ?>>> getAssociatedConfiguredFeatures() {return AerialHellConfiguredFeatures.Lists.GIANT_CRYSTAL_BLOB_LIST;}

	@Override public boolean generate(FeatureContext<DefaultFeatureConfig> context)
	{
		BlockPos pos = context.getOrigin(); StructureWorldAccess world = context.getWorld(); Random rand = context.getRandom();
		if (!world.isAir(pos)) {return false;}
		else
		{
			BlockState blockstate = world.getBlockState(pos.down());
		    if (!blockstate.isIn(AerialHellTags.Blocks.STELLAR_DIRT) || !this.isDungeonSensitiveValid(context)) {return false;}
		    else
		    {
				place(pos, world, rand);
		    	return true;
		    }
		}
	}

	private void place(BlockPos pos, ServerWorldAccess world, Random rand)
	{
		world.setBlockState(pos, AerialHellBlocks.CRYSTAL_BLOCK.getDefaultState(), 2);

		BlockPos blockpos;
		for(int i = 0; i < 3000; ++i)
		{

			if (i < 1000)
			{
				blockpos = pos.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(6), rand.nextInt(8) - rand.nextInt(8));
			}
			else if (i < 1500)
			{
				blockpos = pos.add(rand.nextInt(7) - rand.nextInt(7), 6 + rand.nextInt(6), rand.nextInt(7) - rand.nextInt(7));
			}
			else
			{
				blockpos = pos.add(rand.nextInt(9) - rand.nextInt(9), rand.nextInt(4) - rand.nextInt(8), rand.nextInt(9) - rand.nextInt(9));
			}
			if (world.getBlockState(blockpos).isOf(Blocks.AIR) || world.getBlockState(blockpos).isIn(AerialHellTags.Blocks.STELLAR_DIRT) || world.getBlockState(blockpos).isIn(AerialHellTags.Blocks.STELLAR_STONE))
			{
				int j = 0;

				for(Direction direction : Direction.values())
				{
					if (world.getBlockState(blockpos.offset(direction)).isOf(AerialHellBlocks.CRYSTAL_BLOCK))
					{
						++j;
					}

					if (j > 1) {break;}
				}

				if (j == 1 || j == 2 && rand.nextInt(25) == 0)
				{
					world.setBlockState(blockpos, AerialHellBlocks.CRYSTAL_BLOCK.getDefaultState(), 2);
				}
			}
		}
		for(int i = 0; i < 100; ++i)
		{
			blockpos = pos.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(12), rand.nextInt(8) - rand.nextInt(8));
			if (world.getBlockState(blockpos).isOf(Blocks.AIR) && (world.getBlockState(blockpos.down()).isOf(AerialHellBlocks.CRYSTAL_BLOCK)))
			{
				world.setBlockState(blockpos, AerialHellBlocks.CRYSTALLIZED_FIRE.getDefaultState(), 2);
			}
		}
	}
}
