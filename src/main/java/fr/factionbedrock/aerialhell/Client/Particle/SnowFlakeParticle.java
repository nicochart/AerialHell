package fr.factionbedrock.aerialhell.Client.Particle;

import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.PortalParticle;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

public class SnowFlakeParticle extends PortalParticle
{
	private final SpriteSet sprites;
	protected SnowFlakeParticle(ClientLevel worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, SpriteSet sprites)
	{
		super(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn, sprites.first());
		this.sprites = sprites;

		this.rCol = this.bCol = this.gCol = 1.0F;
		this.gravity = 0.2F;
		this.quadSize *= (0.8F + 0.5*Math.random());
		this.lifetime = ((int)(16.0F / (Math.random() * 0.9F + 0.1F)));
	}

	@OnlyIn(Dist.CLIENT)
	public static class Provider implements ParticleProvider<SimpleParticleType>
	{
		private final SpriteSet sprites;

		public Provider(SpriteSet sprites)
		{
			this.sprites = sprites;
		}

		public Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, RandomSource rand)
		{
			return new SnowFlakeParticle(level, x, y, z, xSpeed, ySpeed, zSpeed, this.sprites);
		}
	}

	@Override public void tick()
	{
		super.tick();
		this.setSpriteFromAge(this.sprites);
	}
}
