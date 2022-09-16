package fr.factionbedrock.aerialhell.Block.Plants;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.ParticleStatus;
import net.minecraft.entity.EntityType;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.state.StateContainer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;

import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;

public class LeavesWithAmbientParticlesBlock extends LeavesBlock
{
	public LeavesWithAmbientParticlesBlock(AbstractBlock.Properties properties)
	{
		super(properties.setAllowsSpawn((state, reader, pos, entity) -> (entity == EntityType.OCELOT || entity == EntityType.PARROT)).setSuffocates((state, reader, pos) -> false).setBlocksVision((state, reader, pos) -> false));
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
	{
		super.fillStateContainer(builder);
	}
	
	protected BasicParticleType getParticle()
	{
		if (this == AerialHellBlocksAndItems.COPPER_PINE_LEAVES.get())
		{
			return AerialHellParticleTypes.COPPER_PINE_LEAVES.get();
		}
		else //if (this == AerialHellBlocksAndItems.SHADOW_PINE_LEAVES.get())
		{
			return AerialHellParticleTypes.SHADOW_PARTICLE.get();
		}
	}
	
	protected int getParticleNumber()
	{
		if (this == AerialHellBlocksAndItems.COPPER_PINE_LEAVES.get()) {return 15;}
		else {return 1;}//if (this == AerialHellBlocksAndItems.SHADOW_PINE_LEAVES.get())
	}
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand)
	{
		super.animateTick(stateIn, worldIn, pos, rand);
		
		BasicParticleType particleType = this.getParticle();
		
		if (Minecraft.getInstance().gameSettings.particles != ParticleStatus.MINIMAL)
		{
			if (worldIn.isRemote)
			{
				if (rand.nextInt(10) == 0)
				{
					for (int i = 0; i < getParticleNumber(); i++)
					{
						double x = pos.getX() + (rand.nextFloat() - 0.5) * 10.0;
						double y = pos.getY() + (rand.nextFloat() - 0.5) * 6.0;
						double z = pos.getZ() + (rand.nextFloat() - 0.5) * 10.0;
						double dx = (rand.nextFloat() - 0.5) * 0.5;
						double dy = (rand.nextFloat() - 0.5) * 0.5;
						double dz = (rand.nextFloat() - 0.5) * 0.5;
						worldIn.addParticle(particleType, x, y, z, dx, dy, dz);
					}
				}
			}
		}
	}
}