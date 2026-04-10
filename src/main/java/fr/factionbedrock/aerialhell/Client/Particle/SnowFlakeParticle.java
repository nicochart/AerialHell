package fr.factionbedrock.aerialhell.Client.Particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.PortalParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;

public class SnowFlakeParticle extends PortalParticle
{
	private final SpriteSet spriteProvider;
	protected SnowFlakeParticle(ClientLevel world, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, SpriteSet spriteProvider)
	{
		super(world, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn, spriteProvider.first());
		this.spriteProvider = spriteProvider;

		this.rCol = this.bCol = this.gCol = 1.0F;
		this.gravity = 0.2F;
		this.quadSize *= (0.8F + 0.5*Math.random());
		this.lifetime = ((int)(16.0F / (Math.random() * 0.9F + 0.1F)));
	}

	public static class Factory implements ParticleProvider<SimpleParticleType>
	{
		private final SpriteSet spriteProvider;

		public Factory(SpriteSet spriteProvider) {this.spriteProvider = spriteProvider;}

		public Particle createParticle(SimpleParticleType simpleParticleType, ClientLevel clientWorld, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, RandomSource random)
		{
			return new SnowFlakeParticle(clientWorld, x, y, z, xSpeed, ySpeed, zSpeed, this.spriteProvider);
		}
	}

	@Override public void tick()
	{
		super.tick();
		this.setSpriteFromAge(this.spriteProvider);
	}
}
