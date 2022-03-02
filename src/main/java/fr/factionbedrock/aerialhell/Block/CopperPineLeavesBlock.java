package fr.factionbedrock.aerialhell.Block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.ParticleStatus;
import net.minecraft.entity.EntityType;
import net.minecraft.state.StateContainer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;

import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;

public class CopperPineLeavesBlock extends LeavesBlock
{
	public CopperPineLeavesBlock(AbstractBlock.Properties properties)
	{
		super(properties.setAllowsSpawn((state, reader, pos, entity) -> (entity == EntityType.OCELOT || entity == EntityType.PARROT)).setSuffocates((state, reader, pos) -> false).setBlocksVision((state, reader, pos) -> false));
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
	{
		super.fillStateContainer(builder);
	}
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand)
	{
		super.animateTick(stateIn, worldIn, pos, rand);
		
		if (Minecraft.getInstance().gameSettings.particles != ParticleStatus.MINIMAL)
		{
			if (worldIn.isRemote)
			{
				if (rand.nextInt(10) == 0)
				{
					for (int i = 0; i < 15; i++)
					{
						double x = pos.getX() + (rand.nextFloat() - 0.5) * 10.0;
						double y = pos.getY() + (rand.nextFloat() - 0.5) * 6.0;
						double z = pos.getZ() + (rand.nextFloat() - 0.5) * 10.0;
						double dx = (rand.nextFloat() - 0.5) * 0.5;
						double dy = (rand.nextFloat() - 0.5) * 0.5;
						double dz = (rand.nextFloat() - 0.5) * 0.5;
						worldIn.addParticle(AerialHellParticleTypes.COPPER_PINE_LEAVES.get(), x, y, z, dx, dy, dz);
					}
				}
			}
		}
	}
}