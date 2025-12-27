package fr.factionbedrock.aerialhell.Client.Particle;

import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.PortalParticle;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.util.math.random.Random;

public class OscillatorParticle extends PortalParticle
{
    private final SpriteProvider spriteProvider;
    protected OscillatorParticle(ClientWorld world, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, SpriteProvider spriteProvider)
    {
        super(world, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn, spriteProvider.getFirst());
        this.spriteProvider = spriteProvider;

        this.red = 0.17F;//0.30F;
        this.green = 0.32F;//0.64F;
        this.blue = 0.27F;//0.55F;
    }

    public static class Factory implements ParticleFactory<SimpleParticleType>
    {
        private final SpriteProvider spriteProvider;

        public Factory(SpriteProvider spriteProvider) {this.spriteProvider = spriteProvider;}

        public Particle createParticle(SimpleParticleType simpleParticleType, ClientWorld clientWorld, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, Random random)
        {
            return new OscillatorParticle(clientWorld, x, y, z, xSpeed, ySpeed, zSpeed, this.spriteProvider);
        }
    }

    @Override public void tick()
    {
        super.tick();
        this.updateSprite(this.spriteProvider);
    }
}