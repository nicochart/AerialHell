package fr.factionbedrock.aerialhell.Entity.AI;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;

public abstract class SummonEntitiesGoal extends Goal
{
    private final Mob goalOwner;
    protected final double yMotion; //Summoned entities y motion
    public int summonTimer;

    public SummonEntitiesGoal(Mob entity, double yMotionForEntitiesSummon) {this.goalOwner = entity; yMotion = yMotionForEntitiesSummon;}

    public Mob getGoalOwner() {return this.goalOwner;}

    @Override public boolean canUse() {return this.goalOwner.getTarget() != null;}
    @Override public void start() {resetTask();}
    @Override public void stop() {resetTask();}

    @Override public void tick()
    {
        if (++this.summonTimer >= this.getSummonTimerTargetValue() || this.customSummonConditionMet())
        {
            this.summonEntities();
            this.playEffect();
            this.resetTask();
        }
    }

    protected void summonEntities()
    {
        Entity entity = createEntity();
        this.setEntityPosToSummonPos(entity); entity.setDeltaMovement(this.getSpawnMotionVec3());
        this.getGoalOwner().level().addFreshEntity(entity);
    }

    protected void setEntityPosToSummonPos(Entity entity) {entity.setPos(this.goalOwner.getX(), this.goalOwner.getY(), this.goalOwner.getZ());}

    protected void playEffect() //default effect, override for custom summon effect
    {
        //particles can't be added directly in this method. Need to use broadcastEntityEvent. See MudCycleMage class
        this.getGoalOwner().playSound(SoundEvents.EVOKER_PREPARE_SUMMON, 1.5F, 0.95F + this.goalOwner.getRandom().nextFloat() * 0.1F);
    }

    protected Vec3 getSpawnMotionVec3()
    {
        return new Vec3(getRandomHorizontalMotion(), yMotion, getRandomHorizontalMotion());
    }

    protected double getRandomHorizontalMotion() {return (this.goalOwner.getRandom().nextDouble() - 0.5D) * 2;}

    protected void resetTask() {this.summonTimer = 0;} //restart

    protected boolean customSummonConditionMet() {return false;}

    public abstract Entity createEntity();
    protected abstract int getSummonTimerTargetValue();
}
