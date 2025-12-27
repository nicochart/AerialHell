package fr.factionbedrock.aerialhell.Client.Particle;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.util.math.random.Random;

public class ShadowParticle extends BillboardParticle
{
	private final SpriteProvider spriteProvider;
	protected ShadowParticle(ClientWorld world, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, SpriteProvider spriteProvider)
	{
		super(world, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn, spriteProvider.getFirst());
		this.spriteProvider = spriteProvider;

		this.red = this.blue = this.green = 1.0F;
		this.gravityStrength = -0.2F;
		this.scale *= 1.5F;
		this.maxAge =(int) (32.0F + 4.0F * Math.random());
	}

	public static class Factory implements ParticleFactory<SimpleParticleType>
	{
		private final SpriteProvider spriteProvider;

		public Factory(SpriteProvider spriteProvider) {this.spriteProvider = spriteProvider;}

		public Particle createParticle(SimpleParticleType simpleParticleType, ClientWorld clientWorld, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, Random random)
		{
			return new ShadowParticle(clientWorld, x, y, z, xSpeed, ySpeed, zSpeed, this.spriteProvider);
		}
	}

	@Override public void tick()
	{
		super.tick();
		this.updateSprite(this.spriteProvider);
	}

	@Override protected RenderType getRenderType() {return RenderType.PARTICLE_ATLAS_TRANSLUCENT;}
}
