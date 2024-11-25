package fr.factionbedrock.aerialhell.Block.Furnaces;

import com.mojang.serialization.MapCodec;
import fr.factionbedrock.aerialhell.BlockEntity.FreezerBlockEntity;
import fr.factionbedrock.aerialhell.BlockEntity.ReactorBlockEntity;
import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlockEntities;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3d;

public class FreezerBlock extends AbstractAerialHellFurnaceBlock
{
	public static final MapCodec<FreezerBlock> CODEC = createCodec(FreezerBlock::new);

	public FreezerBlock(AbstractBlock.Settings settings)
	{
		super(settings);
	}

	@Override protected @NotNull MapCodec<? extends AbstractFurnaceBlock> getCodec() {return CODEC;}

	@Override public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {return new FreezerBlockEntity(pos, state);}

	@Override protected void openScreen(World world, BlockPos pos, PlayerEntity player)
	{
		BlockEntity blockentity = world.getBlockEntity(pos);
		if (blockentity instanceof FreezerBlockEntity freezerBlockEntity)
		{
			player.openHandledScreen(freezerBlockEntity);
		}
	}

	@Override public BlockEntityType<? extends AbstractFurnaceBlockEntity> getTickerBlockEntity() {return AerialHellBlockEntities.FREEZER;}
	@Override public SoundEvent getLitSound() {return SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE;}
	@Override public Vector3d getParticlePos(BlockPos pos, Direction direction) {return new Vector3d(pos.getX() + 0.5D, pos.getY() + 0.7F, pos.getZ() + 0.5D);}
	@Override public Vector3d getParticleRandomOffset(Random rand) {return new Vector3d(1.5D * (rand.nextFloat() - 0.5D), (rand.nextFloat() * 6.0D) / 16.0D, 1.5D * (rand.nextFloat() - 0.5D));}
	@Override public Vector3d getParticleRandomVelocity(Random rand) {return new Vector3d(0.4 * (rand.nextFloat() - 0.5D), 0.0D, 0.4 * (rand.nextFloat() - 0.5D));}
	@Override public ParticleEffect getParticleType() {return AerialHellParticleTypes.SNOWFLAKE;}
	@Override public int getParticleCount() {return 3;}
}
