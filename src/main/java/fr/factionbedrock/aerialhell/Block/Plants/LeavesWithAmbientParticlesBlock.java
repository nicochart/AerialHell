package fr.factionbedrock.aerialhell.Block.Plants;

import net.minecraft.client.ParticleStatus;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;

import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;

public class LeavesWithAmbientParticlesBlock extends LeavesBlock
{
	public LeavesWithAmbientParticlesBlock(BlockBehaviour.Properties properties)
	{
		super(properties.isValidSpawn((state, reader, pos, entity) -> (entity == EntityType.OCELOT || entity == EntityType.PARROT)).isSuffocating((state, reader, pos) -> false).isViewBlocking((state, reader, pos) -> false));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
	{
		super.createBlockStateDefinition(builder);
	}
	
	protected SimpleParticleType getParticle()
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
	public void animateTick(BlockState stateIn, Level worldIn, BlockPos pos, Random rand)
	{
		super.animateTick(stateIn, worldIn, pos, rand);

		SimpleParticleType particleType = this.getParticle();
		
		if (Minecraft.getInstance().options.particles != ParticleStatus.MINIMAL)
		{
			if (worldIn.isClientSide())
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