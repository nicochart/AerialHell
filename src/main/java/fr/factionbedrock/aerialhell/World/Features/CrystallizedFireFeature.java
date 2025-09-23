package fr.factionbedrock.aerialhell.World.Features;

import com.mojang.serialization.Codec;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellConfiguredFeatures;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;

import java.util.List;

public class CrystallizedFireFeature extends Feature<DefaultFeatureConfig> implements DungeonSensitiveFeatureCheck
{
	public CrystallizedFireFeature(Codec<DefaultFeatureConfig> codec) {super(codec);}

	@Override public List<RegistryKey<ConfiguredFeature<?, ?>>> getAssociatedConfiguredFeatures() {return AerialHellConfiguredFeatures.Lists.CRYSTALLIZED_FIRE_LIST;}

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
				place(pos, rand, world);

		    	return true;
		    }
		}
	}

	private void place(BlockPos pos, Random rand, StructureWorldAccess world)
	{
		world.setBlockState(pos, AerialHellBlocks.CRYSTALLIZED_FIRE.getDefaultState(), 2);

		int neighbor_number = 4 + rand.nextInt(3);
		for(int i = 0; i < neighbor_number; ++i)
		{
			BlockPos blockpos = pos.add(rand.nextInt(8) - rand.nextInt(8), 0, rand.nextInt(8) - rand.nextInt(8));
			int j = 0;
			if (world.getBlockState(blockpos).isOf(Blocks.AIR))
			{
				while (world.getBlockState(blockpos.down()).isOf(Blocks.AIR) && j < 10)
				{
					blockpos = blockpos.down();
					j++;
				}
				if (world.getBlockState(blockpos.down()).isIn(AerialHellTags.Blocks.STELLAR_DIRT))
				{
					world.setBlockState(blockpos, AerialHellBlocks.CRYSTALLIZED_FIRE.getDefaultState(), 2);
				}
			}
			else
			{
				while (!world.getBlockState(blockpos).isOf(Blocks.AIR) && j < 10)
				{
					blockpos = blockpos.up();
					j++;
				}
				if (world.getBlockState(blockpos).isOf(Blocks.AIR) && world.getBlockState(blockpos.down()).isIn(AerialHellTags.Blocks.STELLAR_DIRT))
				{
					world.setBlockState(blockpos, AerialHellBlocks.CRYSTALLIZED_FIRE.getDefaultState(), 2);
				}
			}
		}
	}
}
