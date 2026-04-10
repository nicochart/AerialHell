package fr.factionbedrock.aerialhell.Client.Particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;

public class ShadowParticle extends SingleQuadParticle
{
	private final SpriteSet spriteProvider;
	protected ShadowParticle(ClientLevel world, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, SpriteSet spriteProvider)
	{
		super(world, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn, spriteProvider.first());
		this.spriteProvider = spriteProvider;

		this.rCol = this.bCol = this.gCol = 1.0F;
		this.gravity = -0.2F;
		this.quadSize *= 1.5F;
		this.lifetime =(int) (32.0F + 4.0F * Math.random());
	}

	public static class Factory implements ParticleProvider<SimpleParticleType>
	{
		private final SpriteSet spriteProvider;

		public Factory(SpriteSet spriteProvider) {this.spriteProvider = spriteProvider;}

		public Particle createParticle(SimpleParticleType simpleParticleType, ClientLevel clientWorld, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, RandomSource random)
		{
			return new ShadowParticle(clientWorld, x, y, z, xSpeed, ySpeed, zSpeed, this.spriteProvider);
		}
	}

	@Override public void tick()
	{
		super.tick();
		this.setSpriteFromAge(this.spriteProvider);
	}

	@Override protected Layer getLayer() {return Layer.TRANSLUCENT;}
}
