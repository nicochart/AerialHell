package fr.factionbedrock.aerialhell.Block.Furnaces;

import com.mojang.serialization.MapCodec;
import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;
import fr.factionbedrock.aerialhell.BlockEntity.OscillatorBlockEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlockEntities;
import net.minecraft.block.AbstractBlock;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3d;

public class OscillatorBlock extends AbstractAerialHellFurnaceBlock
{
	public static final MapCodec<OscillatorBlock> CODEC = simpleCodec(OscillatorBlock::new);

	public OscillatorBlock(AbstractBlock.Settings settings)
	{
		super(settings);
	}

	@Override protected @NotNull MapCodec<? extends AbstractFurnaceBlock> codec() {return CODEC;}

	@Override public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {return new OscillatorBlockEntity(pos, state);}

	protected void openContainer(Level worldIn, BlockPos pos, Player player)
	{
		BlockEntity blockentity = worldIn.getBlockEntity(pos);
		if (blockentity instanceof OscillatorBlockEntity)
		{
			player.openMenu((MenuProvider)blockentity);
		}
	}

	@Override public BlockEntityType<? extends AbstractFurnaceBlockEntity> getTickerBlockEntity() {return AerialHellBlockEntities.OSCILLATOR.get();}
	@Override public SoundEvent getLitSound() {return SoundEvents.FURNACE_FIRE_CRACKLE;}
	@Override public Vector3d getParticlePos(BlockPos pos, Direction direction) {return new Vector3d(pos.getX() + 0.5D, pos.getY() + 1.01D, pos.getZ() + 0.5D);}
	@Override public Vector3d getParticleRandomOffset(RandomSource rand) {return new Vector3d(rand.nextDouble() - 0.5D, (rand.nextDouble() * 6.0D) / 16.0D, rand.nextDouble() - 0.5D);}
	@Override public Vector3d getParticleRandomVelocity(RandomSource rand) {return new Vector3d(0.0D, 0.0D, 0.0D);}
	@Override public ParticleOptions getParticleType() {return AerialHellParticleTypes.OSCILLATOR.get();}
	@Override public int getParticleCount() {return 1;}
}
