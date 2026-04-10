package fr.factionbedrock.aerialhell.Entity;

import fr.factionbedrock.aerialhell.Entity.Util.ActivableEntityInfo;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

public abstract class AbstractActivableEntity extends Monster implements ActivableEntity, GoalConditionEntity.GoalSimpleConditionEntity
{
	/* -- ActivableEntity fields -- */
	public static final EntityDataAccessor<Boolean> ACTIVE = SynchedEntityData.defineId(AbstractActivableEntity.class, EntityDataSerializers.BOOLEAN);
	public final ActivableEntityInfo.ActivationMethod AERIAL_HELL_ACTIVABLE_ACTIVATION_METHOD = ActivableEntity.DEFAULT_ACTIVATION_METHOD.copy().activationThreshold(this.getTicksToActivate()).targetSearchDistance(this.getMinDistanceToActivate(), this.getMinDistanceToDeactivate());
	public final ActivableEntityInfo ACTIVABLE_INFO = new ActivableEntityInfo(ACTIVE, AERIAL_HELL_ACTIVABLE_ACTIVATION_METHOD);
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

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder)
	{
		super.defineSynchedData(builder);

		/* -- ActivableEntity synched data -- */
		builder.define(ACTIVE, false);
		/* ---------------------------------- */
	}

	/* ---------------------------------------------------------------------------------------------- */
	/* ---------- ActivableEntity : Superclass methods Overridden to delegate to interface ---------- */
	/* ---------------------------------------------------------------------------------------------- */
	@Override public boolean hurtServer(ServerLevel world, DamageSource source, float amount)
	{
		boolean flag = super.hurtServer(world, source, amount);
		this.activableDamage(flag, world, source, amount);
		return flag;
	}

	@Override public void tick()
	{
		super.tick();
		this.activableEntityTick();
	}

	@Override protected void addAdditionalSaveData(ValueOutput view)
	{
		super.addAdditionalSaveData(view);
		this.activableWriteCustomData(view);
	}

	@Override protected void readAdditionalSaveData(ValueInput view)
	{
		super.readAdditionalSaveData(view);
		this.activableReadCustomData(view);
	}
	/* ---------------------------------------------------------------------------------------------- */
	/* ---------------------------------------------------------------------------------------------- */
	/* ---------------------------------------------------------------------------------------------- */

	public abstract int getTicksToActivate();
	public abstract double getMinDistanceToActivate();
	public abstract double getMinDistanceToDeactivate();
}
