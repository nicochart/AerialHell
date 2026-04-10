package fr.factionbedrock.aerialhell.Block.Ores;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class AerialHellOreBlock extends DropExperienceBlock
{
	public AerialHellOreBlock(int minExpDropped, int maxExpDropped, BlockBehaviour.Properties settings)
	{
		super(UniformInt.of(minExpDropped, maxExpDropped), settings);
	}
}
