package fr.factionbedrock.aerialhell.Block;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class AerialHellOreBlock extends DropExperienceBlock
{
	public AerialHellOreBlock(int minExpDropped, int maxExpDropped, BlockBehaviour.Properties properties)
	{
		super(UniformInt.of(minExpDropped, maxExpDropped), properties);
	}
}
