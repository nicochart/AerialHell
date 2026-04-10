package fr.factionbedrock.aerialhell.Client.Particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.PortalParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;

public class AerialHellPortalParticle extends PortalParticle
{
	private final SpriteSet spriteProvider;
	protected AerialHellPortalParticle(ClientLevel world, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, SpriteSet spriteProvider)
	{
		super(world, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn, spriteProvider.first());
		this.spriteProvider = spriteProvider;

		this.rCol = this.bCol = 0.2F * this.random.nextFloat();
		this.gCol = 0.85F;
		this.gravity = -1.0F;
		this.quadSize *= 1.25F;
	}

	public static class Factory implements ParticleProvider<SimpleParticleType>
	{
		private final SpriteSet spriteProvider;

		public Factory(SpriteSet spriteProvider) {this.spriteProvider = spriteProvider;}

		public Particle createParticle(SimpleParticleType simpleParticleType, ClientLevel clientWorld, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, RandomSource random)
		{
			return new AerialHellPortalParticle(clientWorld, x, y, z, xSpeed, ySpeed, zSpeed, this.spriteProvider);
		}
	}

	@Override public void tick()
	{
		super.tick();
		this.setSpriteFromAge(this.spriteProvider);
	}
}
