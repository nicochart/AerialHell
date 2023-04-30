package fr.factionbedrock.aerialhell.Client.Particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.PortalParticle;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class AerialHellPortalParticle extends PortalParticle
{
	protected AerialHellPortalParticle(ClientLevel worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn)
	{
		super(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
		
		this.rCol = this.bCol = 0.2F * this.random.nextFloat();
		this.gCol = 0.85F;
		this.gravity = -1.0F;
		this.quadSize *= 1.25F;
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
			 AerialHellPortalParticle aerialHellPortalparticle = new AerialHellPortalParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed);
	         aerialHellPortalparticle.pickSprite(this.spriteSet);
	         return aerialHellPortalparticle;
		}
	}
}
