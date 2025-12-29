package fr.factionbedrock.aerialhell.Entity.Projectile;

import fr.factionbedrock.aerialhell.Registry.AerialHellDamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.phys.HitResult;

public abstract class AbstractShurikenEntity extends ThrowableItemProjectile
{
	public float shurikenZRot;

	public AbstractShurikenEntity(EntityType<? extends ThrowableItemProjectile> type, Level level, LivingEntity shooter, double accelX, double accelY, double accelZ, float velocity, float inaccuracy, ItemStack itemStack)
	{
		super(type, shooter, level, itemStack);
		this.shoot(accelX, accelY, accelZ, velocity, inaccuracy);
	}

	public AbstractShurikenEntity(EntityType<? extends AbstractShurikenEntity> entityTypeIn, Level worldIn)
	{
		super(entityTypeIn, worldIn);
		this.shurikenZRot = -135;
	}

	public AbstractShurikenEntity(EntityType<? extends AbstractShurikenEntity> type, double x, double y, double z, Level worldIn, ItemStack itemStack)
	{
		super(type, x, y, z, worldIn, itemStack);
		this.shurikenZRot = -135;
	}

	public AbstractShurikenEntity(EntityType<? extends AbstractShurikenEntity> type, LivingEntity shooter, Level worldIn, ItemStack itemStack)
	{
		super(type, shooter, worldIn, itemStack);
		this.shurikenZRot = -135;
	}

	/*public AbstractShurikenEntity(EntityType<? extends AbstractShurikenEntity> type, PlayMessages.SpawnEntity packet, Level worldIn)
	{
		super(type, worldIn);
		this.shurikenZRot = -135;
	}*/
	
	@Override
	public void addAdditionalSaveData(ValueOutput valueOutput)
	{
		super.addAdditionalSaveData(valueOutput);
		
		valueOutput.putShort("shurikenZRot", (short)this.shurikenZRot);
	}
	
	@Override
	public void readAdditionalSaveData(ValueInput valueInput)
	{
	    super.readAdditionalSaveData(valueInput);
		this.shurikenZRot = valueInput.getShortOr("shurikenZRot", (short)0);
	}
	
	@Override
	protected void onHit(HitResult result)
	{
		if (this.level().isClientSide()) {return;}
		if (result != null && result.getType() != HitResult.Type.MISS && this.level() instanceof ServerLevel && result.getType() == HitResult.Type.ENTITY)
		{
            Entity entity = ((EntityHitResult)result).getEntity();
            entity.hurt(AerialHellDamageTypes.getDamageSource(this.level(), AerialHellDamageTypes.SHURIKEN_HIT, this, this.getOwner()), this.getKnifeDamage());
            entity.setDeltaMovement(entity.getDeltaMovement().add(this.getDeltaMovement().x / 2, 0.12F, this.getDeltaMovement().z / 2));
            this.applyEntityImpactEffet(entity);
		}
		this.discard();
	}

	@Override protected double getDefaultGravity() {return 0.04F;}
	
	abstract protected float getKnifeDamage();
	abstract protected void applyEntityImpactEffet(Entity entity);
	
	//@Override public Packet<ClientGamePacketListener> getAddEntityPacket() {return ForgeHooks.getEntitySpawnPacket(this);}

	@Override abstract protected Item getDefaultItem();
}
