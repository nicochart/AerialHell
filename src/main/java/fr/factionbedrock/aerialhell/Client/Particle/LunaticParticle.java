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
public class LunaticParticle extends PortalParticle
{
	protected LunaticParticle(ClientWorld worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn)
	{
		super(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
		this.particleRed = this.particleBlue = this.particleGreen = 1.0F;
		this.particleGravity = 0.5F * (-0.5F + this.rand.nextFloat());
		this.particleScale *= 1.5F;
	}
	
	@Override
	public int getBrightnessForRender(float partialTick)
	{
		return 255;
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
			 LunaticParticle aerialHellPortalparticle = new LunaticParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed);
	         aerialHellPortalparticle.selectSpriteRandomly(this.spriteSet);
	         return aerialHellPortalparticle;
		}
	}
}
