package fr.factionbedrock.aerialhell.Client.Particle;

import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.PortalParticle;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;

public class AerialHellPortalParticle extends PortalParticle
{
	protected AerialHellPortalParticle(ClientWorld world, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn)
	{
		super(world, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
		
		this.red = this.blue = 0.2F * this.random.nextFloat();
		this.green = 0.85F;
		this.gravityStrength = -1.0F;
		this.scale *= 1.25F;
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
			 AerialHellPortalParticle aerialHellPortalparticle = new AerialHellPortalParticle(world, x, y, z, xSpeed, ySpeed, zSpeed);
	         aerialHellPortalparticle.setSprite(this.spriteSet);
	         return aerialHellPortalparticle;
		}
	}
}
