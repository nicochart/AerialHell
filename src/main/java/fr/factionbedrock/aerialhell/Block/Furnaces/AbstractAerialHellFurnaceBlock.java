package fr.factionbedrock.aerialhell.Block.Furnaces;

import com.mojang.math.Vector3d;
import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Random;

public abstract class AbstractAerialHellFurnaceBlock extends AbstractFurnaceBlock
{
    protected AbstractAerialHellFurnaceBlock(Properties properties) {super(properties);}

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType)
    {
        return createFurnaceTicker(level, blockEntityType, this.getTickerBlockEntity());
    }

    @Override
    public void animateTick(BlockState state, Level world, BlockPos pos, Random rand)
    {
        if (state.getValue(LIT))
        {
            Vector3d pPos = getParticlePos(pos, state.getValue(FACING));
            Vector3d pPosOffset = getParticleRandomOffset(rand);
            Vector3d pVelocity = getParticleRandomVelocity(rand);

            for (int i = 0; i < this.getParticleCount(); i++)
            {
                world.addParticle(this.getParticleType(), pPos.x + pPosOffset.x, pPos.y + pPosOffset.y, pPos.z + pPosOffset.z, pVelocity.x, pVelocity.y, pVelocity.z);
            }

            if (rand.nextDouble() < 0.1)
            {
                world.playLocalSound(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, this.getLitSound(), SoundSource.BLOCKS, 1.0F, 1.0F, false);
            }
        }
    }

    public abstract BlockEntityType<? extends AbstractFurnaceBlockEntity> getTickerBlockEntity();
    public abstract SoundEvent getLitSound();
    public abstract Vector3d getParticlePos(BlockPos pos, Direction direction);
    public abstract Vector3d getParticleRandomOffset(Random rand);
    public abstract Vector3d getParticleRandomVelocity(Random rand);
    public abstract ParticleOptions getParticleType();
    public abstract int getParticleCount();
}
