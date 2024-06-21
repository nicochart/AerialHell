package fr.factionbedrock.aerialhell.Client.Particle;

import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.PortalParticle;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.particles.SimpleParticleType;

public class LightParticle extends PortalParticle
{
	protected LightParticle(ClientLevel worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn)
	{
		super(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
		this.rCol = this.bCol = this.gCol = 1.0F;
		this.gravity = 0.5F * (-0.5F + this.random.nextFloat());
		this.quadSize *= 1.5F;
	}
	
	@Override public int getLightColor(float partialTick) {return 255;}

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
			 LightParticle particle = new LightParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed);
	         particle.pickSprite(this.spriteSet);
	         return particle;
		}
	}
}
