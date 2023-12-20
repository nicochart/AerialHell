package fr.factionbedrock.aerialhell.Block.Furnaces;

import com.mojang.serialization.MapCodec;
import fr.factionbedrock.aerialhell.BlockEntity.StellarFurnaceBlockEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlockEntities;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.stats.Stats;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3d;

public class StellarFurnaceBlock extends AbstractAerialHellFurnaceBlock
{
	public static final MapCodec<StellarFurnaceBlock> CODEC = simpleCodec(StellarFurnaceBlock::new);

	public StellarFurnaceBlock(Properties properties)
	{
		super(properties);
	}

	@Override protected @NotNull MapCodec<? extends AbstractFurnaceBlock> codec() {return CODEC;}

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

	@Override public BlockEntityType<? extends AbstractFurnaceBlockEntity> getTickerBlockEntity() {return AerialHellBlockEntities.STELLAR_FURNACE.get();}
	@Override public SoundEvent getLitSound() {return SoundEvents.FURNACE_FIRE_CRACKLE;}
	@Override public Vector3d getParticlePos(BlockPos pos, Direction direction)
	{
		Direction.Axis direction$axis = direction.getAxis();
		double x = pos.getX() + 0.5D, y = pos.getY(), z = pos.getZ() + 0.5D;
		double offset = Math.random() * 0.6D - 0.3D;
		double posX = direction$axis == Direction.Axis.X ? (double)direction.getStepX() * 0.52D : offset;
		double posZ = direction$axis == Direction.Axis.Z ? (double)direction.getStepZ() * 0.52D : offset;
		return new Vector3d(x + posX, y, z + posZ);
	}
	@Override public Vector3d getParticleRandomOffset(RandomSource rand) {return new Vector3d(0.0D, rand.nextDouble() * 6.0D / 16.0D, 0.0D);}
	@Override public Vector3d getParticleRandomVelocity(RandomSource rand) {return new Vector3d(0.0D, 0.0D, 0.0D);}
	@Override public ParticleOptions getParticleType() {return Math.random() > 0.5D ? ParticleTypes.SMOKE : ParticleTypes.FLAME;}
	@Override public int getParticleCount() {return 1;}
}
