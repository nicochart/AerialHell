package fr.factionbedrock.aerialhell.Client.Particle;

import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.PortalParticle;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;

public class LightParticle extends PortalParticle
{
	protected LightParticle(ClientWorld world, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn)
	{
		super(world, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
		this.red = this.blue = this.green = 1.0F;
		this.gravityStrength = 0.5F * (-0.5F + this.random.nextFloat());
		this.scale *= 1.5F;
	}
	
	@Override public int getBrightness(float partialTick) {return 255;}

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
			 LightParticle particle = new LightParticle(world, x, y, z, xSpeed, ySpeed, zSpeed);
	         particle.setSprite(this.spriteSet);
	         return particle;
		}
	}
}
