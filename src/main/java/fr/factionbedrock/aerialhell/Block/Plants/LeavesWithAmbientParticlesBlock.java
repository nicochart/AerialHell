package fr.factionbedrock.aerialhell.Block.Plants;

import fr.factionbedrock.aerialhell.Block.ShiftableLeavesBlock;
import fr.factionbedrock.aerialhell.BlockEntity.BiomeShifter;
import fr.factionbedrock.aerialhell.Client.Event.Listeners.BlocksAndItemsColorHandler;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.server.level.ParticleStatus;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class LeavesWithAmbientParticlesBlock extends ShiftableLeavesBlock
{
	public LeavesWithAmbientParticlesBlock(BlockBehaviour.Properties properties, Supplier<ShiftableLeavesBlock> shiftedVariant, BiomeShifter.ShiftType shiftType)
	{
		super(properties.isValidSpawn((state, reader, pos, entity) -> (entity == EntityType.OCELOT || entity == EntityType.PARROT)).isSuffocating((state, reader, pos) -> false).isViewBlocking((state, reader, pos) -> false), shiftedVariant, shiftType);
	}

	@Nullable protected SimpleParticleType getParticle()
	{
		return !BlocksAndItemsColorHandler.isShadowBindEnabled() ? AerialHellParticleTypes.COPPER_PINE_LEAVES.get() : null;
	}
	
	protected int getParticleNumber() {return 15;}
	
	@Override public void animateTick(BlockState stateIn, Level level, BlockPos pos, RandomSource rand)
	{
		super.animateTick(stateIn, level, pos, rand);

		@Nullable SimpleParticleType particleType = this.getParticle();
		if (particleType == null) {return;}

		if (Minecraft.getInstance().options.particles().get() != ParticleStatus.MINIMAL)
		{
			if (level.isClientSide())
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
						level.addParticle(particleType, x, y, z, dx, dy, dz);
					}
				}
			}
		}
	}
}