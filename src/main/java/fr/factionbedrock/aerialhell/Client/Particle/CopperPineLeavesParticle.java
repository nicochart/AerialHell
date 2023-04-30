package fr.factionbedrock.aerialhell.Client.Particle;

import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.PortalParticle;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CopperPineLeavesParticle extends PortalParticle
{
    protected CopperPineLeavesParticle(ClientLevel worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn)
    {
        super(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);

        this.rCol = 0.86F;
        this.gCol = 0.46F;
        this.bCol = 0.19F;
    }

    @OnlyIn(Dist.CLIENT)
    public static class Factory implements ParticleProvider<SimpleParticleType>
    {
        private final SpriteSet spriteSet;

        public Factory(SpriteSet spriteSetIn)
        {
            this.spriteSet = spriteSetIn;
        }

        @Override
        public Particle createParticle(SimpleParticleType typeIn, ClientLevel worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed)
        {
            CopperPineLeavesParticle copperPineLeavesParticle = new CopperPineLeavesParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed);
            copperPineLeavesParticle.pickSprite(this.spriteSet);
            return copperPineLeavesParticle;
        }
    }
}