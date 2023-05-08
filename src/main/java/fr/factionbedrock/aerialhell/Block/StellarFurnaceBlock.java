package fr.factionbedrock.aerialhell.Block;

import java.util.Random;

import fr.factionbedrock.aerialhell.BlockEntity.FreezerBlockEntity;
import fr.factionbedrock.aerialhell.BlockEntity.StellarFurnaceBlockEntity;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.stats.Stats;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class StellarFurnaceBlock extends AbstractFurnaceBlock
{
	public StellarFurnaceBlock(Properties properties)
	{
		super(properties);
	}

	@Override public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {return new StellarFurnaceBlockEntity(pos, state);}

	protected void openContainer(Level worldIn, BlockPos pos, Player player)
	{
		BlockEntity blockentity = worldIn.getBlockEntity(pos);
		if (blockentity instanceof StellarFurnaceBlockEntity)
		{
			player.openMenu((MenuProvider)blockentity);
			player.awardStat(Stats.INTERACT_WITH_FURNACE);
		}
	}
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState stateIn, Level worldIn, BlockPos pos, Random rand)
	{
		if (stateIn.getValue(LIT))
		{
	    	double d0 = (double)pos.getX() + 0.5D;
	        double d1 = (double)pos.getY();
	        double d2 = (double)pos.getZ() + 0.5D;
	        if (rand.nextDouble() < 0.1D)
	        {
	        	worldIn.playLocalSound(d0, d1, d2, SoundEvents.FURNACE_FIRE_CRACKLE, SoundSource.BLOCKS, 1.0F, 1.0F, false);
	        }

	        Direction direction = stateIn.getValue(FACING);
	        Direction.Axis direction$axis = direction.getAxis();
	        double d4 = rand.nextDouble() * 0.6D - 0.3D;
	        double d5 = direction$axis == Direction.Axis.X ? (double)direction.getStepX() * 0.52D : d4;
	        double d6 = rand.nextDouble() * 6.0D / 16.0D;
	        double d7 = direction$axis == Direction.Axis.Z ? (double)direction.getStepZ() * 0.52D : d4;
	        worldIn.addParticle(ParticleTypes.SMOKE, d0 + d5, d1 + d6, d2 + d7, 0.0D, 0.0D, 0.0D);
	        worldIn.addParticle(ParticleTypes.FLAME, d0 + d5, d1 + d6, d2 + d7, 0.0D, 0.0D, 0.0D);
		}
	 }
}
