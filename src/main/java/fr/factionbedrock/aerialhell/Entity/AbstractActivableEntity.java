package fr.factionbedrock.aerialhell.Entity;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public abstract class AbstractActivableEntity extends Monster
{
	protected int timeClosePlayer;
	protected int timeWithoutAnyTarget;
	public static final EntityDataAccessor<Boolean> ACTIVE = SynchedEntityData.defineId(AbstractActivableEntity.class, EntityDataSerializers.BOOLEAN);

	public AbstractActivableEntity(EntityType<? extends Monster> type, Level world)
	{
		super(type, world);
		this.timeWithoutAnyTarget = 0; this.timeClosePlayer = 0;
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder)
	{
		super.defineSynchedData(builder);
		builder.define(ACTIVE, false);
	}

    public void setActive(boolean isActive) {this.entityData.set(ACTIVE, isActive);}
	public boolean isActive() {return this.entityData.get(ACTIVE);}

	@Override
	public boolean hurtServer(ServerLevel level, DamageSource source, float amount)
	{
		boolean flag = super.hurtServer(level, source, amount);
		if (flag)
		{
			this.setActive(true);
			this.lastHurtByPlayerTime = 100;
			this.timeWithoutAnyTarget = 0;
		}
		return flag;
	}

	@Override
	public void tick()
	{
		super.tick();
		if (this.level().getNearestPlayer(this.getX(), this.getY(), this.getZ(), this.getMinDistanceToActivate(), EntitySelector.NO_CREATIVE_OR_SPECTATOR) != null)
		{
			if (!this.isActive() && this.timeClosePlayer >= this.getMinTimeToActivate())
			{
				this.setActive(true);
				this.timeWithoutAnyTarget = 0;
			}
			else {this.timeClosePlayer++;}

			if (this.isActive() && this.timeWithoutAnyTarget > 0) {this.timeWithoutAnyTarget--;}
		}
		else if (this.level().getNearestPlayer(this.getX(), this.getY(), this.getZ(), this.getMinDistanceToDeactivate(), EntitySelector.NO_CREATIVE_OR_SPECTATOR) == null)
		{			
			if (timeWithoutAnyTarget < 120) {timeWithoutAnyTarget++;}
			else if (this.lastHurtByPlayerTime <= 0 && timeWithoutAnyTarget == 120)
			{
				this.setActive(false);
				this.timeClosePlayer = 0;
			}
		}
	}

	public abstract int getMinTimeToActivate();
	public abstract double getMinDistanceToActivate();
	public abstract double getMinDistanceToDeactivate();
}
