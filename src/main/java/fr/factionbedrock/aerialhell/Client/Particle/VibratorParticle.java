package fr.factionbedrock.aerialhell.Client.Particle;

import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.PortalParticle;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class VibratorParticle extends PortalParticle
{
    protected VibratorParticle(ClientWorld worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn)
    {
        super(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);

        this.particleRed = 0.17F;//0.30F;
        this.particleGreen = 0.32F;//0.64F;
        this.particleBlue = 0.27F;//0.55F;
    }

    @OnlyIn(Dist.CLIENT)
    public static class Factory implements IParticleFactory<BasicParticleType>
    {
        private final IAnimatedSprite spriteSet;

        public Factory(IAnimatedSprite spriteSetIn)
        {
            this.spriteSet = spriteSetIn;
        }

        @Override
        public Particle makeParticle(BasicParticleType typeIn, ClientWorld worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed)
        {
            VibratorParticle vibratorParticle = new VibratorParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed);
            vibratorParticle.selectSpriteRandomly(this.spriteSet);
            return vibratorParticle;
        }
    }
}