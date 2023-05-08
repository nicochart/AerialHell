package fr.factionbedrock.aerialhell.Block;

import java.util.Random;

import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;
import fr.factionbedrock.aerialhell.BlockEntity.OscillatorBlockEntity;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class OscillatorBlock extends AbstractFurnaceBlock
{
	public OscillatorBlock(BlockBehaviour.Properties properties)
	{
		super(properties);
	}

	@Override public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {return new OscillatorBlockEntity(pos, state);}

	protected void openContainer(Level worldIn, BlockPos pos, Player player)
	{
		BlockEntity blockentity = worldIn.getBlockEntity(pos);
		if (blockentity instanceof OscillatorBlockEntity)
		{
			player.openMenu((MenuProvider)blockentity);
		}
	}
	
	@Override
	public void animateTick(BlockState state, Level world, BlockPos pos, Random rand)
	{
		if (state.getValue(LIT))
		{
			double x = pos.getX() + 0.5;
			double y = pos.getY() + 1.01;
			double z = pos.getZ() + 0.5;
			
			world.addParticle(AerialHellParticleTypes.OSCILLATOR.get(), x + (rand.nextFloat() - 0.5F), y + (rand.nextFloat() * 6.0) / 16.0, z + (rand.nextFloat() - 0.5F), 0.0, 0.00, 0.0);
			
			if (rand.nextDouble() < 0.1)
			{
				world.playLocalSound(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, SoundEvents.FURNACE_FIRE_CRACKLE, SoundSource.BLOCKS, 1.0F, 1.0F, false);
			}
		}
	}
}
