package fr.factionbedrock.aerialhell.Entity.AI;

import com.google.common.collect.ImmutableList;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public abstract class SummonThreeEntitiesGoal extends Goal
{
    private final Mob goalOwner;
    private static double yMotion; //Summoned entities y motion
    public int summonTimer;

    public SummonThreeEntitiesGoal(Mob entity, double yMotionForEntitiesSummon) {this.goalOwner = entity; yMotion = yMotionForEntitiesSummon;}

    public Mob getGoalOwner() {return this.goalOwner;}

    @Override public boolean canUse() {return this.goalOwner.getTarget() != null;}
    @Override public void start() {resetTask();}
    @Override public void stop() {resetTask();}

    @Override public void tick()
    {
        ++this.summonTimer;
        if (this.summonTimer >= this.getSummonTimerTargetValue() || this.customSummonConditionMet())
        {
            this.summonEntities();
            this.playEffect();
            this.resetTask();
        }
    }

    private void summonEntities()
    {
        for (Vec3 vector : getSpawnMotionVec3s())
        {
            Entity entity = createEntitiy(this.goalOwner.level());
            this.setEntityPosToSummonPos(entity); entity.setDeltaMovement(vector);
            this.goalOwner.level().addFreshEntity(entity);
        }
    }

    protected void setEntityPosToSummonPos(Entity entity) {entity.setPos(this.goalOwner.getX(), this.goalOwner.getY(), this.goalOwner.getZ());}

    protected void playEffect() //default effect, override for custom summon effect
    {
        //particles can't be added directly in this method. Need to use broadcastEntityEvent. See MudCycleMage class
        this.getGoalOwner().playSound(SoundEvents.EVOKER_PREPARE_SUMMON, 1.5F, 0.95F + this.goalOwner.getRandom().nextFloat() * 0.1F);
    }

    private static List<Vec3> getSpawnMotionVec3s()
    {
        return ImmutableList.of(new Vec3(0.5D, yMotion, 0.0D), new Vec3(-0.2500001125833550D, yMotion, 0.4333882291756956D), new Vec3(-0.250000112583355D, yMotion, -0.4333882291756956D));
    }

    protected void resetTask() {this.summonTimer = 0;} //restart

    protected boolean customSummonConditionMet() {return false;}

    public abstract Entity createEntitiy(Level level);
    protected abstract int getSummonTimerTargetValue();
}
