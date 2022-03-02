package fr.factionbedrock.aerialhell.Block;

import java.util.Random;

import net.minecraft.block.OreBlock;
import net.minecraft.util.math.MathHelper;

import net.minecraft.block.AbstractBlock;

public class AerialHellOreBlock extends OreBlock
{
	private final int minExpDropped;
	private final int maxExpDropped;
	
	public AerialHellOreBlock(int minExpDropped, int maxExpDropped, AbstractBlock.Properties properties)
	{
		super(properties);
		this.minExpDropped = minExpDropped;
		this.maxExpDropped = maxExpDropped;
	}
	
	@Override
	protected int getExperience(Random rand)
	{
		return MathHelper.nextInt(rand, minExpDropped, maxExpDropped);
	}
}
