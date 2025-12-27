package fr.factionbedrock.aerialhell.Client.Particle;

import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.PortalParticle;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.util.math.random.Random;

public class SnowFlakeParticle extends PortalParticle
{
	private final SpriteProvider spriteProvider;
	protected SnowFlakeParticle(ClientWorld world, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, SpriteProvider spriteProvider)
	{
		super(world, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn, spriteProvider.getFirst());
		this.spriteProvider = spriteProvider;

		this.red = this.blue = this.green = 1.0F;
		this.gravityStrength = 0.2F;
		this.scale *= (0.8F + 0.5*Math.random());
		this.maxAge = ((int)(16.0F / (Math.random() * 0.9F + 0.1F)));
	}

	public static class Factory implements ParticleFactory<SimpleParticleType>
	{
		private final SpriteProvider spriteProvider;

		public Factory(SpriteProvider spriteProvider) {this.spriteProvider = spriteProvider;}

		public Particle createParticle(SimpleParticleType simpleParticleType, ClientWorld clientWorld, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, Random random)
		{
			return new SnowFlakeParticle(clientWorld, x, y, z, xSpeed, ySpeed, zSpeed, this.spriteProvider);
		}
	}

	@Override public void tick()
	{
		super.tick();
		this.updateSprite(this.spriteProvider);
	}
}
