package fr.factionbedrock.aerialhell.Entity.Projectile;

import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class PoisonballEntity extends FireballEntity
{
	public PoisonballEntity(EntityType<? extends PoisonballEntity> type, World world)
	{
		super(type, world);
	}

	public PoisonballEntity(World world, double x, double y, double z, double accX, double accY, double accZ)
	{
		super(AerialHellEntities.POISONBALL, x, y, z, new Vec3d(accX, accY, accZ), world);
	}

	public PoisonballEntity(World world, LivingEntity shooter, double accX, double accY, double accZ)
	{
		super(AerialHellEntities.POISONBALL, shooter, new Vec3d(accX, accY, accZ), world);
	}

	@Override public boolean isFireImmune() {return true;}
	@Override protected boolean isBurning() {return false;}
	@Override public float getLightLevelDependentMagicValue() {return 0.0F;}

	@Override
	protected void onCollision(HitResult result)
	{
		super.onCollision(result);
		if (result.getType() == HitResult.Type.ENTITY)
		{
			Entity entity = ((EntityHitResult)result).getEntity();
			if (entity instanceof LivingEntity && !EntityHelper.isLivingEntityShadowImmune((LivingEntity) entity))
			{
				LivingEntity livingEntity = (LivingEntity)entity;

				if (!livingEntity.isBlocking())
				{
					entity.setVelocity(this.getVelocity().x * 0.3, entity.getVelocity().y + 0.1, this.getVelocity().z * 0.3);
				}
				else //TODO
				{
					ItemStack activeItemStack = livingEntity.getActiveItem();
					//activeItemStack.damage(1, livingEntity, p -> p.broadcastBreakEvent(activeItemStack.getEquipmentSlot()));
					this.getWorld().playSound((PlayerEntity) null, entity.getBlockPos(), SoundEvents.ITEM_SHIELD_BREAK, SoundCategory.PLAYERS, 1.0F, 0.8F + this.getWorld().random.nextFloat() * 0.4F);
				}
				livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 160, 0));
			}
		}
		this.discard();
	}

	@Override protected void initDataTracker(DataTracker.Builder builder) {super.initDataTracker(builder);}

	@Override public void tick()
	{
		Entity entity = this.getOwner();
		if (this.getWorld().isClient || (entity == null || !entity.isRemoved()) && this.getWorld().isChunkLoaded(this.getBlockPos()))
		{
			HitResult raytraceresult = ProjectileUtil.getCollision(this, this::canHit);
			if (raytraceresult.getType() != HitResult.Type.MISS)
			{
				this.onCollision(raytraceresult);
			}

			Vec3d movement = this.getVelocity();
			double d0 = this.getX() + movement.x; double d1 = this.getY() + movement.y; double d2 = this.getZ() + movement.z;
			ProjectileUtil.setRotationFromVelocity(this, 0.2F);

			this.setVelocity(this.getVelocity().add(this.getVelocity().normalize().multiply(this.accelerationPower)).multiply(this.getDrag()));
			this.setPos(d0, d1, d2);
		}
		else {this.discard(); return;}
		
		if (this.isInLava() || this.isTouchingWater() || this.age > 500) {this.discard();}
	}

	//@Override public Packet<ClientGamePacketListener> getAddEntityPacket() {return ForgeHooks.getEntitySpawnPacket(this);} crashes the game
}
