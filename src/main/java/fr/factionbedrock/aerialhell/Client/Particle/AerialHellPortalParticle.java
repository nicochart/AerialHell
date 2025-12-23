package fr.factionbedrock.aerialhell.Client.Particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

public class AerialHellPortalParticle extends PortalParticle
{
	private final SpriteSet sprites;
	protected AerialHellPortalParticle(ClientLevel worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, SpriteSet sprites)
	{
		super(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn, sprites.first());
		this.sprites = sprites;

		this.rCol = this.bCol = 0.2F * this.random.nextFloat();
		this.gCol = 0.85F;
		this.gravity = -1.0F;
		this.quadSize *= 1.25F;
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
			return new AerialHellPortalParticle(level, x, y, z, xSpeed, ySpeed, zSpeed, this.sprites);
		}
	}

	@Override public void tick()
	{
		super.tick();
		this.setSpriteFromAge(this.sprites);
	}
}
