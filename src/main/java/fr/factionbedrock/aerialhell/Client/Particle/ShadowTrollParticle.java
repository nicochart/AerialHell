package fr.factionbedrock.aerialhell.Client.Particle;

import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.IParticleRenderType;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.SpriteTexturedParticle;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ShadowTrollParticle extends SpriteTexturedParticle
{
	protected ShadowTrollParticle(ClientWorld worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn)
	{
		super(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
		
		this.particleRed = this.particleBlue = this.particleGreen = 1.0F;
		this.particleGravity = -0.2F;
		this.particleScale *= 1.5F;
		this.setMaxAge((int) (32.0F + 4.0F * this.rand.nextFloat()));
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class Factory implements IParticleFactory<BasicParticleType>
	{
		private final IAnimatedSprite spriteSet;
		
		public Factory(IAnimatedSprite spriteSetIn)
		{
			this.spriteSet = spriteSetIn;
		}
		
		@Override
		public Particle makeParticle(BasicParticleType typeIn, ClientWorld worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed)
		{
			 ShadowTrollParticle shadowTrollParticle = new ShadowTrollParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed);
	         shadowTrollParticle.selectSpriteRandomly(this.spriteSet);
	         return shadowTrollParticle;
		}
	}

	@Override
	public IParticleRenderType getRenderType()
	{
		return IParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
	}
}
