package fr.factionbedrock.aerialhell.Entity.AI;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Vec3d;

public abstract class SummonEntitiesGoal extends Goal
{
    private final MobEntity goalOwner;
    protected final double yMotion; //Summoned entities y motion
    public int summonTimer;

    public SummonEntitiesGoal(MobEntity entity, double yMotionForEntitiesSummon) {this.goalOwner = entity; yMotion = yMotionForEntitiesSummon;}

    public MobEntity getGoalOwner() {return this.goalOwner;}

    @Override public boolean canStart() {return this.goalOwner.getTarget() != null;}
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
        this.setEntityPosToSummonPos(entity); entity.setVelocity(this.getSpawnMotionVec3());
        this.getGoalOwner().getWorld().spawnEntity(entity);
    }

    protected void setEntityPosToSummonPos(Entity entity) {entity.setPos(this.goalOwner.getX(), this.goalOwner.getY(), this.goalOwner.getZ());}

    protected void playEffect() //default effect, override for custom summon effect
    {
        //particles can't be added directly in this method. Need to use broadcastEntityEvent. See MudCycleMage class
        this.getGoalOwner().playSound(SoundEvents.ENTITY_EVOKER_PREPARE_SUMMON, 1.5F, 0.95F + this.goalOwner.getRandom().nextFloat() * 0.1F);
    }

    protected Vec3d getSpawnMotionVec3()
    {
        return new Vec3d(getRandomHorizontalMotion(), yMotion, getRandomHorizontalMotion());
    }

    protected double getRandomHorizontalMotion() {return (this.goalOwner.getRandom().nextDouble() - 0.5D) * 2;}

    protected void resetTask() {this.summonTimer = 0;} //restart

    protected boolean customSummonConditionMet() {return false;}

    public abstract Entity createEntity();
    protected abstract int getSummonTimerTargetValue();
}
