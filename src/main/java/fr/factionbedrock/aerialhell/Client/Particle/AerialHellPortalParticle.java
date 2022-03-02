package fr.factionbedrock.aerialhell.Client.Particle;

import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.PortalParticle;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class AerialHellPortalParticle extends PortalParticle
{
	protected AerialHellPortalParticle(ClientWorld worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn)
	{
		super(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
		
		this.particleRed = this.particleBlue = 0.2F * this.rand.nextFloat();
		this.particleGreen = 0.85F;
		this.particleGravity = -1.0F;
		this.particleScale *= 1.25F;
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
			 AerialHellPortalParticle aerialHellPortalparticle = new AerialHellPortalParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed);
	         aerialHellPortalparticle.selectSpriteRandomly(this.spriteSet);
	         return aerialHellPortalparticle;
		}
	}
}
