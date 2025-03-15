package fr.factionbedrock.aerialhell.Entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

public abstract class AbstractActivableEntity extends HostileEntity
{
	protected int timeClosePlayer;
	protected int timeWithoutAnyTarget;
	public static final TrackedData<Boolean> ACTIVE = DataTracker.registerData(AbstractActivableEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

	public AbstractActivableEntity(EntityType<? extends HostileEntity> type, World world)
	{
		super(type, world);
		this.timeWithoutAnyTarget = 0; this.timeClosePlayer = 0;
	}

	@Override
	protected void initDataTracker(DataTracker.Builder builder)
	{
		super.initDataTracker(builder);
		builder.add(ACTIVE, false);
	}

    public void setActive(boolean isActive) {this.getDataTracker().set(ACTIVE, isActive);}
	public boolean isActive() {return this.getDataTracker().get(ACTIVE);}

	@Override
	public boolean damage(ServerWorld serverWorld, DamageSource source, float amount)
	{
		boolean flag = super.damage(serverWorld, source, amount);
		if (flag)
		{
			this.setActive(true);
			this.playerHitTimer = 100;
			this.timeWithoutAnyTarget = 0;
		}
		return flag;
	}

	@Override
	public void tick()
	{
		super.tick();
		if (this.getWorld().getClosestPlayer(this.getX(), this.getY(), this.getZ(), this.getMinDistanceToActivate(), EntityPredicates.EXCEPT_CREATIVE_OR_SPECTATOR) != null)
		{
			if (!this.isActive() && this.timeClosePlayer >= this.getMinTimeToActivate())
			{
				this.setActive(true);
				this.timeWithoutAnyTarget = 0;
			}
			else {this.timeClosePlayer++;}

			if (this.isActive() && this.timeWithoutAnyTarget > 0) {this.timeWithoutAnyTarget--;}
		}
		else if (this.getWorld().getClosestPlayer(this.getX(), this.getY(), this.getZ(), this.getMinDistanceToDeactivate(), EntityPredicates.EXCEPT_CREATIVE_OR_SPECTATOR) == null)
		{			
			if (timeWithoutAnyTarget < 120) {timeWithoutAnyTarget++;}
			else if (this.playerHitTimer <= 0 && timeWithoutAnyTarget == 120)
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
