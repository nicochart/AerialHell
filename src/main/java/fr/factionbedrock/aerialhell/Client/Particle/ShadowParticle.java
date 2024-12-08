package fr.factionbedrock.aerialhell.Client.Particle;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;

public class ShadowParticle extends SpriteBillboardParticle
{
	protected ShadowParticle(ClientWorld world, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn)
	{
		super(world, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
		
		this.red = this.blue = this.green = 1.0F;
		this.gravityStrength = -0.2F;
		this.scale *= 1.5F;
		this.maxAge =(int) (32.0F + 4.0F * Math.random());
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
			 ShadowParticle shadowTrollParticle = new ShadowParticle(world, x, y, z, xSpeed, ySpeed, zSpeed);
	         shadowTrollParticle.setSprite(this.spriteSet);
	         return shadowTrollParticle;
		}
	}

	@Override
	public ParticleTextureSheet getType()
	{
		return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
	}
}
