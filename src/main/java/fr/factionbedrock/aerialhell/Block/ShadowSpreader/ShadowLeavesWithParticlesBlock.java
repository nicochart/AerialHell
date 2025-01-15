package fr.factionbedrock.aerialhell.Block.ShadowSpreader;

import fr.factionbedrock.aerialhell.Block.ShiftableLeavesBlock;
import fr.factionbedrock.aerialhell.BlockEntity.BiomeShifter;
import fr.factionbedrock.aerialhell.Client.Event.Listeners.BlocksAndItemsColorHandler;
import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.server.level.ParticleStatus;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class ShadowLeavesWithParticlesBlock extends ShadowLeavesBlock
{
    public ShadowLeavesWithParticlesBlock(Properties properties, Supplier<ShiftableLeavesBlock> shiftedVariant, BiomeShifter.ShiftType shiftType) {super(properties, shiftedVariant, shiftType);}

    @Nullable protected SimpleParticleType getParticle()
    {
        return BlocksAndItemsColorHandler.isCurrentPlayerInstanceShadowBind() ? null : AerialHellParticleTypes.SHADOW_PARTICLE.get();
    }

    protected int getParticleNumber() {return 1;}

    @Override public void animateTick(BlockState stateIn, Level level, BlockPos pos, RandomSource rand)
    {
        super.animateTick(stateIn, level, pos, rand);

        @Nullable SimpleParticleType particleType = this.getParticle();
        if (particleType == null) {return;}

        if (Minecraft.getInstance().options.particles().get() != ParticleStatus.MINIMAL)
        {
            if (level.isClientSide())
            {
                if (rand.nextInt(10) == 0)
                {
                    for (int i = 0; i < getParticleNumber(); i++)
                    {
                        double x = pos.getX() + (rand.nextFloat() - 0.5) * 10.0;
                        double y = pos.getY() + (rand.nextFloat() - 0.5) * 6.0;
                        double z = pos.getZ() + (rand.nextFloat() - 0.5) * 10.0;
                        double dx = (rand.nextFloat() - 0.5) * 0.5;
                        double dy = (rand.nextFloat() - 0.5) * 0.5;
                        double dz = (rand.nextFloat() - 0.5) * 0.5;
                        level.addParticle(particleType, x, y, z, dx, dy, dz);
                    }
                }
            }
        }
    }
}
