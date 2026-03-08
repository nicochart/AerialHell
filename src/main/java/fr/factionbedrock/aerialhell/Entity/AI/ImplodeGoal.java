package fr.factionbedrock.aerialhell.Entity.AI;

import fr.factionbedrock.aerialhell.Entity.ImplodingEntity;
import net.minecraft.world.entity.ai.goal.Goal;

public class ImplodeGoal extends Goal
{
    private final ImplodingEntity goalOwner;

    public ImplodeGoal(ImplodingEntity entity) {this.goalOwner = entity;}

    @Override public boolean canUse() {return true;}

    @Override public void start() {resetTask();}

    @Override public void stop()
    {
        resetTask();
        this.goalOwner.setImploding(false);
    }

    @Override public boolean requiresUpdateEveryTick() {return true;}

    @Override public void tick()
    {
        this.goalOwner.incrementImplodingCooldownTicks();
        if (this.willStartImplodingSoon()) {this.playStartImplodingSound();}
        if (this.shouldStartImploding()) {this.startImploding(); this.goalOwner.onImplodingStart();}

        if (this.goalOwner.isImploding())
        {
            this.goalOwner.incrementImplodingCastTicks();
            this.goalOwner.onImplodingCastTick();
            if (this.shouldFinishImploding()) {this.finishImploding();}
        }
    }

    protected void startImploding()
    {
        this.goalOwner.setImploding(true);
        this.goalOwner.setImplodingCastTicks(0);
    }

    protected void finishImploding()
    {
        this.goalOwner.implode();
        this.resetTask();
        this.goalOwner.setImploding(false);
    }

    protected void playStartImplodingSound() {this.goalOwner.playImplodingSound();}

    public int getSoundOffset() {return this.goalOwner.getImplodingSoundOffset();}
    public int getImplodeCooldownThreshold() {return this.goalOwner.getImplodingCooldownDuration();}
    public int getImplosionCastDuration() {return this.goalOwner.getImplodingCastDuration();}
    protected boolean shouldStartImploding() {return !this.goalOwner.isImploding() && this.goalOwner.getImplodingCooldownTicks() >= this.getImplodeCooldownThreshold();}
    protected boolean shouldFinishImploding() {return this.goalOwner.getImplodingCastTicks() >= this.getImplosionCastDuration();}
    protected boolean willStartImplodingSoon() {return this.goalOwner.getImplodingCooldownTicks() == this.getImplodeCooldownThreshold() + this.getSoundOffset();}

    protected void resetTask()
    {
        this.goalOwner.setImplodingCooldownTicks(0);
        this.goalOwner.setImplodingCastTicks(0);
    }
}
