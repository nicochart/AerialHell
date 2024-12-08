package fr.factionbedrock.aerialhell.Client.Particle;

import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.PortalParticle;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;

public class SnowFlakeParticle extends PortalParticle
{
	protected SnowFlakeParticle(ClientWorld world, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn)
	{
		super(world, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
		
		this.red = this.blue = this.green = 1.0F;
		this.gravityStrength = 0.2F;
		this.scale *= (0.8F + 0.5*Math.random());
		this.maxAge = ((int)(16.0F / (Math.random() * 0.9F + 0.1F)));
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
			 SnowFlakeParticle particle = new SnowFlakeParticle(world, x, y, z, xSpeed, ySpeed, zSpeed);
	         particle.setSprite(this.spriteSet);
	         return particle;
		}
	}
}
