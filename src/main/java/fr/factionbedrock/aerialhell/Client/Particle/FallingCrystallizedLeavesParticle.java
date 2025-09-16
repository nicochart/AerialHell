package fr.factionbedrock.aerialhell.Client.Particle;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;

public class FallingCrystallizedLeavesParticle extends LeavesParticle
{
	public FallingCrystallizedLeavesParticle(ClientWorld world, double x, double y, double z, SpriteProvider spriteProvider, float gravity, float windBig, boolean swirl, boolean flowAway, float size, float initialYVelocity)
	{
		super(world, x, y, z, spriteProvider, gravity, windBig, swirl, flowAway, size, initialYVelocity);
	}

	@Override public ParticleTextureSheet getType() {return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;}

	public static class Factory implements ParticleFactory<SimpleParticleType>
	{
		private final SpriteProvider spriteSet;

		public Factory(SpriteProvider spriteSet) {this.spriteSet = spriteSet;}

		public Particle createParticle(SimpleParticleType particleType, ClientWorld world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed)
		{
			return new FallingCrystallizedLeavesParticle(world, x, y, z, this.spriteSet, 0.25F, 2.0F, false, true, 2.0F, 0.0F);
		}
	}
}
