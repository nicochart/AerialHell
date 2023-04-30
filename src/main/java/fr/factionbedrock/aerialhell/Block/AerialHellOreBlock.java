package fr.factionbedrock.aerialhell.Block;

import java.util.Random;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.OreBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class AerialHellOreBlock extends OreBlock
{
	public AerialHellOreBlock(int minExpDropped, int maxExpDropped, BlockBehaviour.Properties properties)
	{
		super(properties, UniformInt.of(minExpDropped, maxExpDropped));
	}
}
