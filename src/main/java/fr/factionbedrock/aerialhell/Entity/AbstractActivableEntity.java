package fr.factionbedrock.aerialhell.Entity;

import fr.factionbedrock.aerialhell.Entity.Util.ActivableEntityInfo;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.world.World;

public abstract class AbstractActivableEntity extends HostileEntity implements ActivableEntity, GoalConditionEntity.GoalSimpleConditionEntity
{
	/* -- ActivableEntity fields -- */
	public static final TrackedData<Boolean> ACTIVE = DataTracker.registerData(AbstractActivableEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
	public final ActivableEntityInfo.ActivationMethod AERIAL_HELL_ACTIVABLE_ACTIVATION_METHOD = ActivableEntity.DEFAULT_ACTIVATION_METHOD.copy().activationThreshold(this.getTicksToActivate()).targetSearchDistance(this.getMinDistanceToActivate(), this.getMinDistanceToDeactivate());
	public final ActivableEntityInfo ACTIVABLE_INFO = new ActivableEntityInfo(ACTIVE, AERIAL_HELL_ACTIVABLE_ACTIVATION_METHOD);
	/* ---------------------------- */
	public AbstractActivableEntity(EntityType<? extends HostileEntity> type, World world) {super(type, world);}

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
	@Override public PathAwareEntity getSelf() {return this;}

	@Override public boolean canUseGoalsAdditionalCondition() {return this.isActive();}
	/* -------------------------------------------------------------------- */
	/* -------------------------------------------------------------------- */
	/* -------------------------------------------------------------------- */

	@Override
	protected void initDataTracker(DataTracker.Builder builder)
	{
		super.initDataTracker(builder);

		/* -- ActivableEntity synched data -- */
		builder.add(ACTIVE, false);
		/* ---------------------------------- */
	}

	/* ---------------------------------------------------------------------------------------------- */
	/* ---------- ActivableEntity : Superclass methods Overridden to delegate to interface ---------- */
	/* ---------------------------------------------------------------------------------------------- */
	@Override public boolean damage(ServerWorld world, DamageSource source, float amount)
	{
		boolean flag = super.damage(world, source, amount);
		this.activableDamage(flag, world, source, amount);
		return flag;
	}

	@Override public void tick()
	{
		super.tick();
		this.activableEntityTick();
	}

	@Override protected void writeCustomData(WriteView view)
	{
		super.writeCustomData(view);
		this.activableWriteCustomData(view);
	}

	@Override protected void readCustomData(ReadView view)
	{
		super.readCustomData(view);
		this.activableReadCustomData(view);
	}
	/* ---------------------------------------------------------------------------------------------- */
	/* ---------------------------------------------------------------------------------------------- */
	/* ---------------------------------------------------------------------------------------------- */

	public abstract int getTicksToActivate();
	public abstract double getMinDistanceToActivate();
	public abstract double getMinDistanceToDeactivate();
}
