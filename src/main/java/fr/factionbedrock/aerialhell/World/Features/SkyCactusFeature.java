package fr.factionbedrock.aerialhell.World.Features;

import java.util.Random;

import com.mojang.serialization.Codec;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class SkyCactusFeature extends Feature<NoFeatureConfig>
{
	public SkyCactusFeature(Codec<NoFeatureConfig> codec)
	{
		super(codec);
	}

	@Override
	public boolean generate(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config) 
	{
		BlockState ground = reader.getBlockState(pos.down());
		BlockState here = reader.getBlockState(pos);
		BlockState top = reader.getBlockState(pos.up());
		if (!ground.isIn(AerialHellBlocksAndItems.SLIPPERY_SAND.get()) || !here.isAir()) {return false;}
		
		for(Direction direction : Direction.Plane.HORIZONTAL)
	    {
			BlockState blockstate = reader.getBlockState(pos.offset(direction));
	        Material material = blockstate.getMaterial();
	        if (material.isSolid())
	        {
	        	return false;
	        }
	    }
		reader.setBlockState(pos, AerialHellBlocksAndItems.SKY_CACTUS.get().getDefaultState(), 1);
		if (Math.random() > 0.5 && top.isAir())
		{
			reader.setBlockState(pos.up(), AerialHellBlocksAndItems.SKY_CACTUS.get().getDefaultState(), 1);
		}
		return true;
	}
}