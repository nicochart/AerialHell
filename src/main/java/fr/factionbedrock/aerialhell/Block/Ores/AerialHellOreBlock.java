package fr.factionbedrock.aerialhell.Block.Ores;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.util.math.intprovider.UniformIntProvider;

public class AerialHellOreBlock extends ExperienceDroppingBlock
{
	public AerialHellOreBlock(int minExpDropped, int maxExpDropped, AbstractBlock.Settings settings)
	{
		super(UniformIntProvider.create(minExpDropped, maxExpDropped), settings);
	}
}
