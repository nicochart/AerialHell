package fr.factionbedrock.aerialhell.Block;

import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import net.minecraft.block.BlockState;
import net.minecraft.block.NetherPortalBlock;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class AerialHellPortalBlock extends NetherPortalBlock
{
    //TODO
    public AerialHellPortalBlock(Settings settings) {super(settings);}

    @Override public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random)
    {
        if (random.nextInt(100) == 0)
        {
            world.playSoundClient((double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, AerialHellSoundEvents.BLOCK_AERIAL_HELL_PORTAL_AMBIENT, SoundCategory.BLOCKS, 0.6F, 0.9F + random.nextFloat() * 0.2F, false);
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
            if (!world.getBlockState(pos.west()).isOf(this) && !world.getBlockState(pos.east()).isOf(this))
            {
                x = (double)pos.getX() + 0.5D + 0.25D * (double)j;
                xSpeed = random.nextFloat() * 2.0F * (float)j;
            }
            else
            {
                z = (double)pos.getZ() + 0.5D + 0.25D * (double)j;
                zSpeed = random.nextFloat() * 2.0F * (float)j;
            }

            world.addParticleClient(AerialHellParticleTypes.AERIAL_HELL_PORTAL, x, y, z, xSpeed, ySpeed, zSpeed);
        }
    }
}
