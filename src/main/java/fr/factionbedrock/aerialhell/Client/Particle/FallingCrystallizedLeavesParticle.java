package fr.factionbedrock.aerialhell.Client.Particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;

public class FallingCrystallizedLeavesParticle extends FallingLeavesParticle
{
	private final SpriteSet spriteProvider;
	public FallingCrystallizedLeavesParticle(ClientLevel world, double x, double y, double z, SpriteSet spriteProvider, float gravity, float windBig, boolean swirl, boolean flowAway, float size, float initialYVelocity)
	{
		super(world, x, y, z, spriteProvider.first(), gravity, windBig, swirl, flowAway, size, initialYVelocity);
		this.spriteProvider = spriteProvider;
	}

	public static class Factory implements ParticleProvider<SimpleParticleType>
	{
		private final SpriteSet spriteProvider;

		public Factory(SpriteSet spriteProvider) {this.spriteProvider = spriteProvider;}

		public Particle createParticle(SimpleParticleType simpleParticleType, ClientLevel clientWorld, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, RandomSource random)
		{
			return new FallingCrystallizedLeavesParticle(clientWorld, x, y, z, this.spriteProvider, 0.25F, 2.0F, false, true, 2.0F, 0.0F);
		}
	}

	@Override public Layer getLayer() {return Layer.TRANSLUCENT;}

	@Override public void tick()
	{
		super.tick();
		this.setSpriteFromAge(this.spriteProvider);
	}
}
