package fr.factionbedrock.aerialhell.Block.Furnaces;

import com.mojang.serialization.MapCodec;
import fr.factionbedrock.aerialhell.BlockEntity.StellarFurnaceBlockEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlockEntities;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3d;

public class StellarFurnaceBlock extends AbstractAerialHellFurnaceBlock
{
	public static final MapCodec<StellarFurnaceBlock> CODEC = createCodec(StellarFurnaceBlock::new);

	public StellarFurnaceBlock(AbstractBlock.Settings settings)
	{
		super(settings);
	}

	@Override protected @NotNull MapCodec<? extends AbstractFurnaceBlock> getCodec() {return CODEC;}

	@Override public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {return new StellarFurnaceBlockEntity(pos, state);}

	@Override protected void openScreen(World world, BlockPos pos, PlayerEntity player)
	{
		BlockEntity blockentity = world.getBlockEntity(pos);
		if (blockentity instanceof StellarFurnaceBlockEntity stellarFurnaceBlockEntity)
		{
			player.openHandledScreen(stellarFurnaceBlockEntity);
		}
	}

	@Override public BlockEntityType<? extends AbstractFurnaceBlockEntity> getTickerBlockEntity() {return AerialHellBlockEntities.STELLAR_FURNACE;}
	@Override public SoundEvent getLitSound() {return SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE;}
	@Override public Vector3d getParticlePos(BlockPos pos, Direction direction)
	{
		Direction.Axis direction$axis = direction.getAxis();
		double x = pos.getX() + 0.5D, y = pos.getY(), z = pos.getZ() + 0.5D;
		double offset = Math.random() * 0.6D - 0.3D;
		double posX = direction$axis == Direction.Axis.X ? (double)direction.getOffsetX() * 0.52D : offset;
		double posZ = direction$axis == Direction.Axis.Z ? (double)direction.getOffsetZ() * 0.52D : offset;
		return new Vector3d(x + posX, y, z + posZ);
	}
	@Override public Vector3d getParticleRandomOffset(Random rand) {return new Vector3d(0.0D, rand.nextDouble() * 6.0D / 16.0D, 0.0D);}
	@Override public Vector3d getParticleRandomVelocity(Random rand) {return new Vector3d(0.0D, 0.0D, 0.0D);}
	@Override public ParticleEffect getParticleType() {return Math.random() > 0.5D ? ParticleTypes.SMOKE : ParticleTypes.FLAME;}
	@Override public int getParticleCount() {return 1;}
}
