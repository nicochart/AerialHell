package fr.factionbedrock.aerialhell.Entity;

import fr.factionbedrock.aerialhell.Entity.Util.ActivableEntityInfo;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

public abstract class AbstractActivableEntity extends Monster implements ActivableEntity, GoalConditionEntity.GoalSimpleConditionEntity
{
	/* -- ActivableEntity fields -- */
	public static final EntityDataAccessor<Boolean> ACTIVE = SynchedEntityData.defineId(AbstractActivableEntity.class, EntityDataSerializers.BOOLEAN);
	public final ActivableEntityInfo ACTIVABLE_INFO = new ActivableEntityInfo(ACTIVE, ActivableEntity.DEFAULT_ACTIVATION_METHOD.copy().activationThreshold(this.getTicksToActivate()).targetSearchDistance(this.getMinDistanceToActivate(), this.getMinDistanceToDeactivate()));
	/* ---------------------------- */
	public AbstractActivableEntity(EntityType<? extends Monster> type, Level world) {super(type, world);}

	/* ------------------------------------------------------------------------ */
	/* ---------- ActivableEntity : Interface methods implementation ---------- */
	/* ------------------------------------------------------------------------ */
	@Override public ActivableEntityInfo getActivableInfo() {return this.ACTIVABLE_INFO;}
	/* ------------------------------------------------------------------------ */
	/* ------------------------------------------------------------------------ */
	/* ------------------------------------------------------------------------ */

	/* -------------------------------------------------------------------- */
	/* ------ GoalConditionEntity : Interface methods implementation ------ */
	/* -------------------------------------------------------------------- */
	@Override public PathfinderMob getSelf() {return this;}

	@Override public boolean canUseGoalsAdditionalCondition() {return this.isActive();}
	/* -------------------------------------------------------------------- */
	/* -------------------------------------------------------------------- */
	/* -------------------------------------------------------------------- */

	@Override protected void defineSynchedData(SynchedEntityData.Builder builder)
	{
		super.defineSynchedData(builder);

		/* -- ActivableEntity synched data -- */
		builder.define(ACTIVE, false);
		/* ---------------------------------- */
	}

	/* ---------------------------------------------------------------------------------------------- */
	/* ---------- ActivableEntity : Superclass methods Overridden to delegate to interface ---------- */
	/* ---------------------------------------------------------------------------------------------- */
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

	@Override public void addAdditionalSaveData(ValueOutput valueOutput)
	{
		super.addAdditionalSaveData(valueOutput);
		this.activableAddAdditionalSaveData(valueOutput);
	}

	@Override public void readAdditionalSaveData(ValueInput valueInput)
	{
		super.readAdditionalSaveData(valueInput);
		this.activableReadAdditionalSaveData(valueInput);
	}
	/* ---------------------------------------------------------------------------------------------- */
	/* ---------------------------------------------------------------------------------------------- */
	/* ---------------------------------------------------------------------------------------------- */

	public abstract int getTicksToActivate();
	public abstract double getMinDistanceToActivate();
	public abstract double getMinDistanceToDeactivate();
}
