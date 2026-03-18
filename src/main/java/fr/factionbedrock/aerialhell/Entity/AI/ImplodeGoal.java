package fr.factionbedrock.aerialhell.Entity.AI;

import fr.factionbedrock.aerialhell.Entity.ImplodingEntity;
import net.minecraft.entity.ai.goal.Goal;

public class ImplodeGoal extends Goal
{
    private final ImplodingEntity goalOwner;
    private int ticksNoTarget; //only used if imploding cooldown resets on target loss

    public ImplodeGoal(ImplodingEntity entity) {this.goalOwner = entity;}

    @Override public boolean canStart()
    {
        if (this.goalOwner.isImploding()) {return true;}
        return !this.goalOwner.needsTargetToStartImploding() || this.goalOwner.getTarget() != null;
    }

    @Override public boolean shouldContinue()
    {
        if (this.goalOwner.isImploding()) {return true;}
        if (!this.goalOwner.needsTargetToStartImploding() || this.goalOwner.getTarget() != null) {this.ticksNoTarget = 0; return true;}

        //needsTargetToStartImploding == true && target == null
        if (this.goalOwner.implodingCooldownResetsOnTargetLoss())
        {
            if (this.ticksNoTarget++ >= this.goalOwner.implodingCooldownResetThreshold()) //should reset CooldownTicks and stop using the goal
            {
                this.goalOwner.setImplodingCooldownTicks(0);
                return false;
            }
            else {return true;}
        }
        else {return false;}
    }

    @Override public void start()
    {
        this.ticksNoTarget = 0;
        if (this.goalOwner.getImplodingCooldownTicks() < 0) {this.goalOwner.setImplodingCooldownTicks(0);}
        if (this.goalOwner.getImplodingCastTicks() < 0) {this.goalOwner.setImplodingCastTicks(0);}
    }

    @Override public void stop()
    {
        this.goalOwner.setImploding(false);
    }

    @Override public boolean shouldRunEveryTick() {return true;}

    @Override public void tick()
    {
        if (this.goalOwner.isImploding()) {this.castTick();}
        else {this.cooldownTick();}
    }

    private void cooldownTick()
    {
        boolean hasTarget = this.goalOwner.getTarget() != null;
        boolean canCooldownTick = !this.goalOwner.needsTargetToStartImploding() || hasTarget;
        if (canCooldownTick)
        {
            this.goalOwner.incrementImplodingCooldownTicks();
            if (this.willStartImplodingSoon()) {this.playStartImplodingSound();}
            if (this.shouldStartImploding()) {this.startImploding();}
        }
    }

    private void castTick()
    {
        this.goalOwner.incrementImplodingCastTicks();
        this.goalOwner.onImplodingCastTick();
        if (this.shouldFinishImploding()) {this.finishImploding();}
    }

    protected void startImploding()
    {
        this.goalOwner.setImploding(true);
        this.goalOwner.setImplodingCastTicks(0);
        this.goalOwner.onImplodingStart();
    }

    protected void finishImploding()
    {
        this.goalOwner.implode();
        this.resetTicksCounters();
        this.goalOwner.setImploding(false);
    }

    protected void playStartImplodingSound() {this.goalOwner.playImplodingSound();}

    public int getSoundOffset() {return this.goalOwner.getImplodingSoundOffset();}
    public int getImplodeCooldownThreshold() {return this.goalOwner.getImplodingCooldownDuration();}
    public int getImplosionCastDuration() {return this.goalOwner.getImplodingCastDuration();}
    protected boolean shouldStartImploding() {return !this.goalOwner.isImploding() && this.goalOwner.getImplodingCooldownTicks() >= this.getImplodeCooldownThreshold();}
    protected boolean shouldFinishImploding() {return this.goalOwner.getImplodingCastTicks() >= this.getImplosionCastDuration();}
    protected boolean willStartImplodingSoon() {return this.goalOwner.getImplodingCooldownTicks() == this.getImplodeCooldownThreshold() + this.getSoundOffset();}

    protected void resetTicksCounters()
    {
        this.goalOwner.setImplodingCooldownTicks(0);
        this.goalOwner.setImplodingCastTicks(0);
    }
}
