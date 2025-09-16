package fr.factionbedrock.aerialhell.Block.ShadowSpreader;

import fr.factionbedrock.aerialhell.Block.ShiftableLeavesBlock;
import fr.factionbedrock.aerialhell.BlockEntity.BiomeShifter;
import fr.factionbedrock.aerialhell.Client.Event.Listeners.BlocksAndItemsColorHandler;
import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticlesMode;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class ShadowLeavesWithParticlesBlock extends ShadowLeavesBlock
{
    public ShadowLeavesWithParticlesBlock(AbstractBlock.Settings settings, Supplier<ShiftableLeavesBlock> shiftedVariant, BiomeShifter.ShiftType shiftType) {super(settings, shiftedVariant, shiftType);}

    @Nullable protected ParticleEffect getParticle()
    {
        return BlocksAndItemsColorHandler.isShadowBindEnabled() ? null : AerialHellParticleTypes.SHADOW_PARTICLE;
    }

    protected int getParticleNumber() {return 1;}

    @Override public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random rand)
    {
        super.randomDisplayTick(state, world, pos, rand);

        @Nullable ParticleEffect particleType = this.getParticle();
        if (particleType == null) {return;}

        if (MinecraftClient.getInstance().options.getParticles().getValue() != ParticlesMode.MINIMAL)
        {
            if (world.isClient())
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
                        world.addParticleClient(particleType, x, y, z, dx, dy, dz);
                    }
                }
            }
        }
    }
}
