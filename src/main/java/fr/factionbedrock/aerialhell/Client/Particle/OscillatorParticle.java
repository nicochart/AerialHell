package fr.factionbedrock.aerialhell.Client.Particle;

import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.PortalParticle;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;

public class OscillatorParticle extends PortalParticle
{
    protected OscillatorParticle(ClientWorld world, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn)
    {
        super(world, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);

        this.red = 0.17F;//0.30F;
        this.green = 0.32F;//0.64F;
        this.blue = 0.27F;//0.55F;
    }

    public static class Factory implements ParticleFactory<SimpleParticleType>
    {
        private final SpriteProvider spriteSet;

        public Factory(SpriteProvider spriteSetIn)
        {
            this.spriteSet = spriteSetIn;
        }

        @Override
        public Particle createParticle(SimpleParticleType typeIn, ClientWorld world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed)
        {
            OscillatorParticle oscillatorParticle = new OscillatorParticle(world, x, y, z, xSpeed, ySpeed, zSpeed);
            oscillatorParticle.setSprite(this.spriteSet);
            return oscillatorParticle;
        }
    }
}