package fr.factionbedrock.aerialhell.Entity.Projectile;

import fr.factionbedrock.aerialhell.Registry.AerialHellEntities;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractFireballEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;

import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

public class PoisonballEntity extends AbstractFireballEntity
{
	private int ticksInAir;

	public PoisonballEntity(EntityType<? extends PoisonballEntity> type, World worldIn)
	{
		super(type, worldIn);
	}

	@OnlyIn(Dist.CLIENT)
	public PoisonballEntity(World worldIn, double x, double y, double z, double accX, double accY, double accZ)
	{
		super(AerialHellEntities.POISONBALL.get(), x, y, z, accX, accY, accZ, worldIn);
	}

	public PoisonballEntity(World worldIn, LivingEntity shooter, double accX, double accY, double accZ)
	{
		super(AerialHellEntities.POISONBALL.get(), shooter, accX, accY, accZ, worldIn);
	}

	@Override protected boolean isFireballFiery() {return false;}
	@Override public float getBrightness() {return 0.0F;}
	
	@Override
	protected void onImpact(RayTraceResult result)
	{
		super.onImpact(result);
		if (result.getType() == RayTraceResult.Type.ENTITY)
		{
			Entity entity = ((EntityRayTraceResult)result).getEntity();
			if (entity instanceof LivingEntity && !EntityHelper.isLivingEntityShadowImmune((LivingEntity) entity))
			{
				LivingEntity livingEntity = (LivingEntity)entity;

				if (!livingEntity.isActiveItemStackBlocking())
				{
					entity.setMotion(this.getMotion().x * 0.3, entity.getMotion().y + 0.1, this.getMotion().z * 0.3);
				}
				else
				{
					ItemStack activeItemStack = livingEntity.getActiveItemStack();
					activeItemStack.damageItem(1, livingEntity, p -> p.sendBreakAnimation(activeItemStack.getEquipmentSlot()));

					if (activeItemStack.getCount() <= 0)
					{
						world.playSound((PlayerEntity)null, entity.getPosition(), SoundEvents.ITEM_SHIELD_BREAK, SoundCategory.PLAYERS, 1.0F, 0.8F + this.world.rand.nextFloat() * 0.4F);
					}
					else
					{
						world.playSound((PlayerEntity)null, entity.getPosition(), SoundEvents.ITEM_SHIELD_BLOCK, SoundCategory.PLAYERS, 1.0F, 0.8F + this.world.rand.nextFloat() * 0.4F);
					}
				}
				livingEntity.addPotionEffect(new EffectInstance(Effects.POISON, 160, 0));
			}
		}
		this.remove();
	}

	@Override
	protected void registerData()
	{
		super.registerData();
	}

	@SuppressWarnings("deprecation")
	@Override
	public void tick()
	{
		if (this.world.isRemote || (this.func_234616_v_() == null || this.func_234616_v_().isAlive()) && this.world.isBlockLoaded(new BlockPos(this.getPosition())))
		{
			if (this.isFireballFiery())
			{
				this.setFire(1);
			}

			++this.ticksInAir;
			RayTraceResult raytraceresult = ProjectileHelper.func_234618_a_(this, this::func_230298_a_);
			if (raytraceresult.getType() != RayTraceResult.Type.MISS && !net.minecraftforge.event.ForgeEventFactory.onProjectileImpact(this, raytraceresult))
			{
				this.onImpact(raytraceresult);
			}

			Vector3d Vector3d = this.getMotion();
			double d0 = this.getPosX() + Vector3d.x;
			double d1 = this.getPosY() + Vector3d.y;
			double d2 = this.getPosZ() + Vector3d.z;
			ProjectileHelper.rotateTowardsMovement(this, 0.2F);
			float f = this.getMotionFactor();
			if (this.isInWater())
			{
				for (int i = 0; i < 4; ++i)
				{
					this.world.addParticle(ParticleTypes.BUBBLE, d0 - Vector3d.x * 0.25D, d1 - Vector3d.y * 0.25D, d2 - Vector3d.z * 0.25D, Vector3d.x, Vector3d.y, Vector3d.z);
				}

				f = 0.8F;
			}

			this.setMotion(Vector3d.add(this.accelerationX, this.accelerationY, this.accelerationZ).scale(f));
			IParticleData particle = this.getParticle();
			if (particle != null)
			{
				this.world.addParticle(this.getParticle(), d0, d1 + 0.5D, d2, 0.0D, 0.0D, 0.0D);
			}
			this.setPosition(d0, d1, d2);
		}
		else
		{
			this.remove();
			return;
		}
		
		if (!this.onGround)
		{
			++this.ticksInAir;
		}

		if (this.ticksInAir > 500)
		{
			this.remove();
		}
	}

	@Override
	protected IParticleData getParticle() {return null;}

	@Override
	public IPacket<?> createSpawnPacket() {return NetworkHooks.getEntitySpawningPacket(this);}
}
