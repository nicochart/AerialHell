package fr.factionbedrock.aerialhell.Block;

import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import net.kyrptonaught.customportalapi.CustomPortalBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class AerialHellPortalBlock extends CustomPortalBlock
{
    public AerialHellPortalBlock(Properties settings) {super(settings);}

    @Override public void animateTick(BlockState state, Level world, BlockPos pos, RandomSource random)
    {
        if (random.nextInt(100) == 0)
        {
            world.getProfiler().push("portal");
            world.playLocalSound((double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, AerialHellSoundEvents.BLOCK_AERIAL_HELL_PORTAL_AMBIENT, SoundSource.BLOCKS, 0.6F, 0.9F + random.nextFloat() * 0.2F, false);
            world.getProfiler().pop();
        }

        for(int i = 0; i < 4; ++i)
        {
            double x = (double)pos.getX() + random.nextDouble();
            double y = (double)pos.getY() + random.nextDouble();
            double z = (double)pos.getZ() + random.nextDouble();
            double xSpeed = ((double)random.nextFloat() - 0.5D) * 0.5D;
            double ySpeed = ((double)random.nextFloat() - 0.5D) * 0.5D;
            double zSpeed = ((double)random.nextFloat() - 0.5D) * 0.5D;
            int j = random.nextInt(2) * 2 - 1;
            if (!world.getBlockState(pos.west()).is(this) && !world.getBlockState(pos.east()).is(this))
            {
                x = (double)pos.getX() + 0.5D + 0.25D * (double)j;
                xSpeed = random.nextFloat() * 2.0F * (float)j;
            }
            else
            {
                z = (double)pos.getZ() + 0.5D + 0.25D * (double)j;
                zSpeed = random.nextFloat() * 2.0F * (float)j;
            }

            world.addParticle(AerialHellParticleTypes.AERIAL_HELL_PORTAL, x, y, z, xSpeed, ySpeed, zSpeed);
        }
    }
}
