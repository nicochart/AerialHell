package fr.factionbedrock.aerialhell.Client.Particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

public class FallingCrystallizedLeavesParticle extends FallingLeavesParticle
{
	private final SpriteSet sprites;
	public FallingCrystallizedLeavesParticle(ClientLevel level, double x, double y, double z, SpriteSet sprites, float gravityMultiplier, float windBig, boolean swirl, boolean flowAway, float size, float ySpeed)
	{
		super(level, x, y, z, sprites.first(), gravityMultiplier, windBig, swirl, flowAway, size, ySpeed);
		this.sprites = sprites;
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
			return new FallingCrystallizedLeavesParticle(level, x, y, z, this.sprites, 0.25F, 2.0F, false, true, 2.0F, 0.0F);
		}
	}

	@Override public SingleQuadParticle.Layer getLayer() {return Layer.TRANSLUCENT;}

	@Override public void tick()
	{
		super.tick();
		this.setSpriteFromAge(this.sprites);
	}
}
