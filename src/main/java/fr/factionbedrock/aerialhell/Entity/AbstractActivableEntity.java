package fr.factionbedrock.aerialhell.Entity;

import fr.factionbedrock.aerialhell.Util.FieldAccessor;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public abstract class AbstractActivableEntity extends Monster implements ActivableEntity
{
	public static final EntityDataAccessor<Boolean> ACTIVE = SynchedEntityData.defineId(AbstractActivableEntity.class, EntityDataSerializers.BOOLEAN);
	public final ActivableEntityInfo ACTIVABLE_INFO = new ActivableEntityInfo(ACTIVE, new FieldAccessor<>(() -> this.lastHurtByPlayerMemoryTime, value -> this.lastHurtByPlayerMemoryTime = value), this.getMinTimeToActivate(), 120, 600 , this.getMinDistanceToActivate(), this.getMinDistanceToDeactivate());
	public AbstractActivableEntity(EntityType<? extends Monster> type, Level world) {super(type, world);}

	@Override public ActivableEntityInfo getActivableInfo() {return this.ACTIVABLE_INFO;}
	@Override public Mob getSelf() {return this;}

	@Override protected void defineSynchedData(SynchedEntityData.Builder builder)
	{
		super.defineSynchedData(builder);
		builder.define(ACTIVE, false);
	}

	@Override public boolean hurtServer(ServerLevel level, DamageSource source, float amount)
	{
		boolean flag = super.hurtServer(level, source, amount);
		this.activableHurtServer(flag, level, source, amount);
		return flag;
	}

	@Override public void tick()
	{
		super.tick();
		this.activableEntityTick();
	}

	public abstract int getMinTimeToActivate();
	public abstract double getMinDistanceToActivate();
	public abstract double getMinDistanceToDeactivate();
}
