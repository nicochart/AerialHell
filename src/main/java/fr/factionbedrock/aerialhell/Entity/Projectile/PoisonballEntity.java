package fr.factionbedrock.aerialhell.Entity.Projectile;

import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Fireball;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class PoisonballEntity extends Fireball
{
	public PoisonballEntity(EntityType<? extends PoisonballEntity> type, Level worldIn)
	{
		super(type, worldIn);
	}

	public PoisonballEntity(Level worldIn, double x, double y, double z, double accX, double accY, double accZ)
	{
		super(AerialHellEntities.POISONBALL.get(), x, y, z, new Vec3(accX, accY, accZ), worldIn);
	}

	public PoisonballEntity(Level worldIn, LivingEntity shooter, double accX, double accY, double accZ)
	{
		super(AerialHellEntities.POISONBALL.get(), shooter, new Vec3(accX, accY, accZ), worldIn);
	}

	@Override public boolean fireImmune() {return true;}
	@Override protected boolean shouldBurn() {return false;}
	@Override public float getLightLevelDependentMagicValue() {return 0.0F;}

	@Override
	protected void onHit(HitResult result)
	{
		super.onHit(result);
		if (result.getType() == HitResult.Type.ENTITY)
		{
			Entity entity = ((EntityHitResult)result).getEntity();
			if (entity instanceof LivingEntity && !EntityHelper.isLivingEntityShadowImmune((LivingEntity) entity))
			{
				LivingEntity livingEntity = (LivingEntity)entity;

				if (!livingEntity.isBlocking())
				{
					entity.setDeltaMovement(this.getDeltaMovement().x * 0.3, entity.getDeltaMovement().y + 0.1, this.getDeltaMovement().z * 0.3);
				}
				else //TODO
				{
					ItemStack activeItemStack = livingEntity.getUseItem();
					//activeItemStack.hurtAndBreak(1, livingEntity, p -> p.broadcastBreakEvent(activeItemStack.getEquipmentSlot()));
					level().playSound((Player)null, entity.blockPosition(), SoundEvents.SHIELD_BREAK.value(), SoundSource.PLAYERS, 1.0F, 0.8F + this.level().random.nextFloat() * 0.4F);
				}
				if (!livingEntity.level().isClientSide()) {livingEntity.addEffect(new MobEffectInstance(MobEffects.POISON, 160, 0));}
			}
		}
		this.discard();
	}

	@Override protected void defineSynchedData(SynchedEntityData.Builder builder) {super.defineSynchedData(builder);}

	@Override public void tick()
	{
		Entity entity = this.getOwner();
		if (this.level().isClientSide() || (entity == null || !entity.isRemoved()) && this.level().hasChunkAt(this.blockPosition()))
		{
			HitResult raytraceresult = ProjectileUtil.getHitResultOnMoveVector(this, this::canHitEntity);
			if (raytraceresult.getType() != HitResult.Type.MISS && !net.neoforged.neoforge.event.EventHooks.onProjectileImpact(this, raytraceresult))
			{
				this.onHit(raytraceresult);
			}

			Vec3 movement = this.getDeltaMovement();
			double d0 = this.getX() + movement.x; double d1 = this.getY() + movement.y; double d2 = this.getZ() + movement.z;
			ProjectileUtil.rotateTowardsMovement(this, 0.2F);

			this.setDeltaMovement(this.getDeltaMovement().add(this.getDeltaMovement().normalize().scale(this.accelerationPower)).scale(this.getInertia()));
			this.setPos(d0, d1, d2);
		}
		else {this.discard(); return;}
		
		if (this.isInLava() || this.isInWater() || this.tickCount > 500) {this.discard();}
	}

	//@Override public Packet<ClientGamePacketListener> getAddEntityPacket() {return ForgeHooks.getEntitySpawnPacket(this);} crashes the game
}
