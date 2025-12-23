package fr.factionbedrock.aerialhell.Client.Particle;

import net.minecraft.client.particle.*;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

public class ShadowParticle extends SingleQuadParticle
{
	private final SpriteSet sprites;
	public ShadowParticle(ClientLevel level, double x, double y, double z, float xSeedMultiplier, float ySpeedMultiplier, float zSpeedMultiplier, double xSpeed, double ySpeed, double zSpeed, float quadSizeMultiplier, SpriteSet sprites)
	{
		super(level, x, y, z, (double) 0.0F, (double) 0.0F, (double) 0.0F, sprites.first());
		this.sprites = sprites;

		this.rCol = this.bCol = this.gCol = 1.0F;
		this.gravity = -0.2F;
		this.quadSize *= 1.5F;
		this.lifetime =(int) (32.0F + 4.0F * Math.random());
	}

	@OnlyIn(Dist.CLIENT)
	public static class Provider implements ParticleProvider<SimpleParticleType>
	{
		private final SpriteSet sprites;

		public Provider(SpriteSet sprites) {this.sprites = sprites;}

		public Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, RandomSource rand)
		{
			return new ShadowParticle(level, x, y, z, 1.0F, 1.0F, 1.0F, xSpeed, ySpeed, zSpeed, 1.0F, this.sprites);
		}
	}

	@Override public SingleQuadParticle.Layer getLayer() {return Layer.TRANSLUCENT;}

	@Override public void tick()
	{
		super.tick();
		this.setSpriteFromAge(this.sprites);
	}
}
