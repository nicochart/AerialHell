package fr.factionbedrock.aerialhell.Entity.AI;

import fr.factionbedrock.aerialhell.Entity.Passive.GlidingTurtleEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

public class GlideGoal extends Goal
{
    private final GlidingTurtleEntity goalOwner;
    private int jumpTimer;
    private int randomTimerBonus;

    public GlideGoal(GlidingTurtleEntity entity) {this.goalOwner = entity;}

    @Override public boolean canUse() {return true;}
    @Override public void start() {this.resetTask();}
    @Override public void stop() {goalOwner.setGliding(false);}
    @Override public boolean requiresUpdateEveryTick() {return true;}

    @Override public void tick()
    {
        if (this.goalOwner.isGliding()) {this.setGlidingMotion();}
        else if (this.shouldStartGliding()) {this.goalOwner.setGliding(true);}

        if (this.shouldJump())
        {
            this.glideJump();
            this.resetTask();
        }
        if (this.shouldIncrementJumpTimer()) {this.incrementJumpTimer();}
        if (this.shouldPanicBonusIncrementJumpTimer()) {for (int i=0; i<3; i++) {this.incrementJumpTimer();}}

        if (this.shouldStopGliding()) {this.goalOwner.setGliding(false);}
    }

    private void setGlidingMotion()
    {
        if (this.goalOwner.getDeltaMovement().y < 0.0D)
        {
            Vec3 forward = Vec3.directionFromRotation(this.goalOwner.getRotationVector());
            if (this.hasBlockUnder(this.goalOwner.getBlockPos().offset((int)(2.5*forward.x), (int)forward.y, (int)(2.5*forward.z)), 20)) //goal owner has block forward (1 - 2 blocks in front of him)
            {
                this.glideForward(forward);
            }
            else //goal owner has no block forward
            {
                if (this.hasBlockUnder(this.goalOwner.getBlockPos(), 25)) //goal owner has block under himself = he is safe
                {
                    this.slowDownMovement(); //to not fall in the void (because no block forward)
                }
                else //goal owner has no block under himself = he may be falling in the void
                {
                    this.speedUpMovement(forward); //hoping to reach land and not end up in the void
                }
            }
        }
    }

    private void glideForward(Vec3 forward) {this.goalOwner.setDeltaMovement(this.goalOwner.getDeltaMovement().multiply(1.0D, 0.6D, 1.0D).add(forward.x/100, 0, forward.z/100));}
    private void slowDownMovement() {this.goalOwner.setDeltaMovement(this.goalOwner.getDeltaMovement().multiply(0.9D, 0.6D, 0.9D));}
    private void speedUpMovement(Vec3 direction) {this.goalOwner.setDeltaMovement(this.goalOwner.getDeltaMovement().multiply(1.0D, 0.5D, 1.0D).add(direction.x/70, 0, direction.z/70));}

    protected boolean hasBlockUnder(BlockPos pos, int yBlocksDistance)
    {
        for (int dy=0; dy<yBlocksDistance; dy++) {if (!this.goalOwner.level().isEmptyBlock(pos.below(dy))) {return true;}}
        return false;
    }

    public void glideJump()
    {
        double jumpPower = this.goalOwner.isBaby() ? 1.1D : 1.5D;
        this.goalOwner.setDeltaMovement(this.goalOwner.getDeltaMovement().add(0.0D, jumpPower, 0.0D));
    }

    protected boolean shouldJump() {return this.jumpTimer > this.getJumpTimerTargetValue() && !this.goalOwner.isGliding() && this.goalOwner.onGround();}
    protected boolean shouldStartGliding() {return this.goalOwner.getDeltaMovement().y < -0.3D;}
    protected boolean shouldStopGliding() {return this.goalOwner.isGliding() && this.goalOwner.onGround() && this.goalOwner.getDeltaMovement().y>=-0.1F;}
    protected boolean shouldIncrementJumpTimer() {return this.goalOwner.onGround() && !this.goalOwner.isGliding();}
    protected boolean shouldPanicBonusIncrementJumpTimer() {return this.goalOwner.getLastHurtByMob() != null && this.goalOwner.tickCount - this.goalOwner.getLastHurtByMobTimestamp() < 40;}
    protected void incrementJumpTimer() {this.jumpTimer += 1 + this.goalOwner.getRandom().nextInt(randomTimerBonus);}
    protected int getJumpTimerTargetValue() {return 1200;}
    protected void resetTask() {this.jumpTimer = 0; this.randomTimerBonus = 1 + this.goalOwner.getRandom().nextInt(10);}
}
