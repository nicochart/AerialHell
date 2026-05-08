package fr.factionbedrock.aerialhell.Entity.Projectile.Shuriken;

import fr.factionbedrock.aerialhell.Registry.AerialHellDamageTypes;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class ShurikenEntity extends ThrowableProjectile
{
	public float shurikenZRot;
	public final float damage;

	public ShurikenEntity(EntityType<? extends ShurikenEntity> entityType, Level level, float damage)
	{
		super(entityType, level);
		this.damage = damage;
		this.shurikenZRot = -135;
	}

	@Override public void shoot(double x, double y, double z, float velocity, float inaccuracy)
	{
		super.shoot(x, y, z, velocity, inaccuracy);
		this.playSound(this.getShootSound(), 3, 0.875F + 0.25F * random.nextFloat());
	}
	
	@Override public void addAdditionalSaveData(ValueOutput valueOutput)
	{
		super.addAdditionalSaveData(valueOutput);
		
		valueOutput.putShort("shurikenZRot", (short)this.shurikenZRot);
	}
	
	@Override public void readAdditionalSaveData(ValueInput valueInput)
	{
	    super.readAdditionalSaveData(valueInput);
		this.shurikenZRot = valueInput.getShortOr("shurikenZRot", (short)0);
	}
	
	@Override protected void onHit(HitResult result)
	{
		if (this.level().isClientSide()) {return;}
		if (result.getType() != HitResult.Type.MISS) {this.applyEntityImpactEffect(result);}
		if (!this.level().isClientSide() && result.getType() == HitResult.Type.ENTITY)
		{
            Entity entity = ((EntityHitResult)result).getEntity();
            entity.hurt(AerialHellDamageTypes.getDamageSource(this.level(), AerialHellDamageTypes.SHURIKEN_HIT, this, this.getOwner()), this.damage);
            entity.setDeltaMovement(entity.getDeltaMovement().add(this.getDeltaMovement().x / 2, 0.12F, this.getDeltaMovement().z / 2));
		}
		this.discard();
	}

	@Override protected void defineSynchedData(SynchedEntityData.Builder builder) {}

	@Override protected double getDefaultGravity() {return 0.04F;}
	protected SoundEvent getShootSound() {return AerialHellSoundEvents.ENTITY_SHURIKEN_SHOOT;}

	protected void applyEntityImpactEffect(HitResult result) {} //override this is you want specific impact effect
}
