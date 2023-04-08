package fr.factionbedrock.aerialhell.Entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityPredicates;
import net.minecraft.world.World;

public abstract class AbstractActivableEntity extends MonsterEntity
{
	protected int timeClosePlayer;
	protected int timeWithoutAnyTarget;
	public static final DataParameter<Boolean> ACTIVE = EntityDataManager.createKey(AbstractActivableEntity.class, DataSerializers.BOOLEAN);

	public AbstractActivableEntity(EntityType<? extends MonsterEntity> type, World world)
	{
		super(type, world);
		this.timeWithoutAnyTarget = 0; this.timeClosePlayer = 0;
	}

	@Override
	protected void registerData()
	{
		super.registerData();
		this.dataManager.register(ACTIVE, false);
	}
    
    public void setActive(boolean isActive) {this.dataManager.set(ACTIVE, isActive);}
	public boolean isActive() {return this.dataManager.get(ACTIVE);}
	
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount)
	{
		boolean flag = super.attackEntityFrom(source, amount);
		if (flag)
		{
			this.setActive(true);
			this.recentlyHit = 100;
		}
		return flag;
	}
	
	@Override
	public void tick()
	{		
		super.tick();		
		if (this.world.getClosestPlayer(this.getPosX(), this.getPosY(), this.getPosZ(), this.getMinDistanceToActivate(), EntityPredicates.CAN_AI_TARGET) != null)
		{
			if (!this.isActive() && this.timeClosePlayer >= this.getMinTimeToActivate())
			{
				this.setActive(true);
				this.timeWithoutAnyTarget = 0;
			}
			else {this.timeClosePlayer++;}
		}
		else if (this.world.getClosestPlayer(this.getPosX(), this.getPosY(), this.getPosZ(), this.getMinDistanceToDeactivate(), EntityPredicates.CAN_AI_TARGET) == null)
		{			
			if (timeWithoutAnyTarget < 120) {timeWithoutAnyTarget++;}
			else if (this.recentlyHit <= 0 && timeWithoutAnyTarget == 120)
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
