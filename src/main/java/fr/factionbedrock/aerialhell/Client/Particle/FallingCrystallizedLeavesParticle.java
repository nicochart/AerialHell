package fr.factionbedrock.aerialhell.Client.Particle;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.util.math.random.Random;

public class FallingCrystallizedLeavesParticle extends LeavesParticle
{
	private final SpriteProvider spriteProvider;
	public FallingCrystallizedLeavesParticle(ClientWorld world, double x, double y, double z, SpriteProvider spriteProvider, float gravity, float windBig, boolean swirl, boolean flowAway, float size, float initialYVelocity)
	{
		super(world, x, y, z, spriteProvider.getFirst(), gravity, windBig, swirl, flowAway, size, initialYVelocity);
		this.spriteProvider = spriteProvider;
	}

	public static class Factory implements ParticleFactory<SimpleParticleType>
	{
		private final SpriteProvider spriteProvider;

		public Factory(SpriteProvider spriteProvider) {this.spriteProvider = spriteProvider;}

		public Particle createParticle(SimpleParticleType simpleParticleType, ClientWorld clientWorld, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, Random random)
		{
			return new FallingCrystallizedLeavesParticle(clientWorld, x, y, z, this.spriteProvider, 0.25F, 2.0F, false, true, 2.0F, 0.0F);
		}
	}

	@Override public RenderType getRenderType() {return RenderType.PARTICLE_ATLAS_TRANSLUCENT;}

	@Override public void tick()
	{
		super.tick();
		this.updateSprite(this.spriteProvider);
	}
}
