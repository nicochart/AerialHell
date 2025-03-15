package fr.factionbedrock.aerialhell.Block.Furnaces;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.joml.Vector3d;

public abstract class AbstractAerialHellFurnaceBlock extends AbstractFurnaceBlock
{
    protected AbstractAerialHellFurnaceBlock(AbstractBlock.Settings settings) {super(settings);}

    @Override public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type)
    {
        return validateTicker(world, type, this.getTickerBlockEntity());
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random rand)
    {
        if (state.get(LIT))
        {
            Vector3d pPos = getParticlePos(pos, state.get(FACING));
            Vector3d pPosOffset = getParticleRandomOffset(rand);
            Vector3d pVelocity = getParticleRandomVelocity(rand);

            for (int i = 0; i < this.getParticleCount(); i++)
            {
                world.addParticle(this.getParticleType(), pPos.x + pPosOffset.x, pPos.y + pPosOffset.y, pPos.z + pPosOffset.z, pVelocity.x, pVelocity.y, pVelocity.z);
            }

            if (rand.nextDouble() < 0.1)
            {
                world.playSound(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, this.getLitSound(), SoundCategory.BLOCKS, 1.0F, 1.0F, false);
            }
        }
    }

    public abstract BlockEntityType<? extends AbstractFurnaceBlockEntity> getTickerBlockEntity();
    public abstract SoundEvent getLitSound();
    public abstract Vector3d getParticlePos(BlockPos pos, Direction direction);
    public abstract Vector3d getParticleRandomOffset(Random rand);
    public abstract Vector3d getParticleRandomVelocity(Random rand);
    public abstract ParticleEffect getParticleType();
    public abstract int getParticleCount();
}
