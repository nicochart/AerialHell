package fr.factionbedrock.aerialhell.Block.ShadowSpreader;

import fr.factionbedrock.aerialhell.Block.ShiftableLeavesBlock;
import fr.factionbedrock.aerialhell.BlockEntity.BiomeShifter;
import fr.factionbedrock.aerialhell.Client.Event.Listeners.BlocksAndItemsColorHandler;
import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.server.level.ParticleStatus;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class ShadowLeavesWithParticlesBlock extends ShadowLeavesBlock
{
    public ShadowLeavesWithParticlesBlock(BlockBehaviour.Properties settings, Supplier<ShiftableLeavesBlock> shiftedVariant, BiomeShifter.ShiftType shiftType) {super(settings, shiftedVariant, shiftType);}

    @Nullable protected ParticleOptions getParticle()
    {
        return BlocksAndItemsColorHandler.isShadowBindEnabled() ? null : AerialHellParticleTypes.SHADOW_PARTICLE;
    }

    protected int getParticleNumber() {return 1;}

    @Override public void animateTick(BlockState state, Level world, BlockPos pos, RandomSource rand)
    {
        super.animateTick(state, world, pos, rand);

        @Nullable ParticleOptions particleType = this.getParticle();
        if (particleType == null) {return;}

        if (Minecraft.getInstance().options.particles().get() != ParticleStatus.MINIMAL)
        {
            if (world.isClientSide())
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
                        world.addParticle(particleType, x, y, z, dx, dy, dz);
                    }
                }
            }
        }
    }
}
