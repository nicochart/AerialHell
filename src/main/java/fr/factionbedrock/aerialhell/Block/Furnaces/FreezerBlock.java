package fr.factionbedrock.aerialhell.Block.Furnaces;

import java.util.Random;

import com.mojang.math.Vector3d;
import fr.factionbedrock.aerialhell.BlockEntity.FreezerBlockEntity;
import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlockEntities;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

import net.minecraft.world.level.block.state.BlockBehaviour;

public class FreezerBlock extends AbstractAerialHellFurnaceBlock
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

	@Override public BlockEntityType<? extends AbstractFurnaceBlockEntity> getTickerBlockEntity() {return AerialHellBlockEntities.FREEZER.get();}
	@Override public SoundEvent getLitSound() {return SoundEvents.FURNACE_FIRE_CRACKLE;}
	@Override public Vector3d getParticlePos(BlockPos pos, Direction direction) {return new Vector3d(pos.getX() + 0.5D, pos.getY() + 0.7F, pos.getZ() + 0.5D);}
	@Override public Vector3d getParticleRandomOffset(Random rand) {return new Vector3d(1.5D * (rand.nextFloat() - 0.5D), (rand.nextFloat() * 6.0D) / 16.0D, 1.5D * (rand.nextFloat() - 0.5D));}
	@Override public Vector3d getParticleRandomVelocity(Random rand) {return new Vector3d(0.4 * (rand.nextFloat() - 0.5D), 0.0D, 0.4 * (rand.nextFloat() - 0.5D));}
	@Override public ParticleOptions getParticleType() {return AerialHellParticleTypes.SNOWFLAKE.get();}
	@Override public int getParticleCount() {return 3;}
}
