package fr.factionbedrock.aerialhell.Client.Particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

public class FallingCrystallizedLeavesParticle extends FallingLeavesParticle
{
	public FallingCrystallizedLeavesParticle(ClientLevel level, double x, double y, double z, SpriteSet sprites, float gravityMultiplier, float windBig, boolean swirl, boolean flowAway, float size, float ySpeed)
	{
		super(level, x, y, z, sprites, gravityMultiplier, windBig, swirl, flowAway, size, ySpeed);
	}

	@Override public ParticleRenderType getRenderType() {return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;}

	@OnlyIn(Dist.CLIENT)
	public static class Factory implements ParticleProvider<SimpleParticleType>
	{
		private final SpriteSet sprites;

		public Factory(SpriteSet sprites) {this.sprites = sprites;}

		public Particle createParticle(SimpleParticleType particleType, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed)
		{
			return new FallingCrystallizedLeavesParticle(level, x, y, z, this.sprites, 0.25F, 2.0F, false, true, 2.0F, 0.0F);
		}
	}
}
