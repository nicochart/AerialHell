package fr.factionbedrock.aerialhell.Client.Particle;

import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.PortalParticle;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SnowFlakeParticle extends PortalParticle
{
	protected SnowFlakeParticle(ClientLevel worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn)
	{
		super(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
		
		this.rCol = this.bCol = this.gCol = 1.0F;
		this.gravity = 0.2F;
		this.quadSize *= (0.8F + 0.5*Math.random());
		this.lifetime = ((int)(16.0F / (Math.random() * 0.9F + 0.1F)));
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class Factory implements ParticleProvider<SimpleParticleType>
	{
		private final SpriteSet spriteSet;
		
		public Factory(SpriteSet spriteSetIn)
		{
			this.spriteSet = spriteSetIn;
		}
		
		@Override
		public Particle createParticle(SimpleParticleType typeIn, ClientLevel worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed)
		{
			 SnowFlakeParticle particle = new SnowFlakeParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed);
	         particle.pickSprite(this.spriteSet);
	         return particle;
		}
	}
}
