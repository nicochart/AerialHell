package fr.factionbedrock.aerialhell.Block;

import java.util.Random;

import fr.factionbedrock.aerialhell.BlockEntity.FreezerBlockEntity;
import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

import net.minecraft.world.level.block.state.BlockBehaviour;

public class FreezerBlock extends AbstractFurnaceBlock
{
	public FreezerBlock(BlockBehaviour.Properties properties)
	{
		super(properties);
	}

	@Override public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {return new FreezerBlockEntity(pos, state);}
	
	@Override
	protected void openContainer(Level worldIn, BlockPos pos, Player player)
	{
		BlockEntity blockentity = worldIn.getBlockEntity(pos);
		if (blockentity instanceof FreezerBlockEntity)
		{
			player.openMenu((MenuProvider)blockentity);
		}
	}
	
	@Override
	public void animateTick(BlockState state, Level world, BlockPos pos, Random rand)
	{
		if (state.getValue(LIT))
		{
			float x = pos.getX() + 0.5F;
			float y = pos.getY() + 0.7F + (rand.nextFloat() * 6.0F) / 16.0F;
			float z = pos.getZ() + 0.5F;

			world.addParticle(ParticleTypes.SMOKE, x, y, z, 0.0, 0.0, 0.0);

			for (int i = 0; i < 4; ++i)
			{
				world.addParticle(AerialHellParticleTypes.SNOWFLAKE.get(), x + 1.5F * (rand.nextFloat() - 0.5F), y, z + 1.5F * (rand.nextFloat() - 0.5F), 0.4 * (rand.nextFloat() - 0.5F), 0.0F, 0.4 * (rand.nextFloat() - 0.5F));
			}

			world.playLocalSound(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, SoundEvents.FURNACE_FIRE_CRACKLE, SoundSource.BLOCKS, 1.0F, 1.0F, false);
		}
	}
}
