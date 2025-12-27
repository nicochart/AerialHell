package fr.factionbedrock.aerialhell.Entity.AI;

import fr.factionbedrock.aerialhell.Entity.Passive.GlidingTurtleEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class GlideGoal extends Goal
{
    private final GlidingTurtleEntity goalOwner;
    private int jumpTimer;
    private int randomTimerBonus;

    public GlideGoal(GlidingTurtleEntity entity) {this.goalOwner = entity;}

    @Override public boolean canStart() {return true;}
    @Override public void start() {this.resetTask();}
    @Override public void stop() {goalOwner.setTurtleGliding(false);}
    @Override public boolean shouldRunEveryTick() {return true;}

    @Override public void tick()
    {
        if (this.goalOwner.isTurtleGliding()) {this.setGlidingMotion();}
        else if (this.shouldStartGliding()) {this.goalOwner.setTurtleGliding(true);}

        if (this.shouldJump())
        {
            this.glideJump();
            this.resetTask();
        }
        if (this.shouldIncrementJumpTimer()) {this.incrementJumpTimer();}
        if (this.shouldPanicBonusIncrementJumpTimer()) {for (int i=0; i<3; i++) {this.incrementJumpTimer();}}

        if (this.shouldStopGliding()) {this.goalOwner.setTurtleGliding(false);}
    }

    private void setGlidingMotion()
    {
        if (this.goalOwner.getVelocity().y < 0.0D)
        {
            Vec3d forward = Vec3d.fromPolar(this.goalOwner.getRotationClient());
            if (this.hasBlockUnder(this.goalOwner.getBlockPos().add((int)(2.5D*forward.x), (int)forward.y, (int)(2.5D*forward.z)), 20)) //goal owner has block forward (1 - 2 blocks in front of him)
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

    private void glideForward(Vec3d forward) {this.goalOwner.setVelocity(this.goalOwner.getVelocity().multiply(1.0D, 0.6D, 1.0D).add(forward.x/100.0D, 0, forward.z/100.0D));}
    private void slowDownMovement() {this.goalOwner.setVelocity(this.goalOwner.getVelocity().multiply(0.9D, 0.6D, 0.9D));}
    private void speedUpMovement(Vec3d direction) {this.goalOwner.setVelocity(this.goalOwner.getVelocity().multiply(1.0D, 0.5D, 1.0D).add(direction.x/70.0D, 0, direction.z/70.0D));}

    protected boolean hasBlockUnder(BlockPos pos, int yBlocksDistance)
    {
        for (int dy=0; dy<yBlocksDistance; dy++) {if (!this.goalOwner.getEntityWorld().isAir(pos.down(dy))) {return true;}}
        return false;
    }

    public void glideJump()
    {
        double jumpPower = this.goalOwner.isBaby() ? 1.1D : 1.5D;
        this.goalOwner.setVelocity(this.goalOwner.getVelocity().add(0.0D, jumpPower, 0.0D));
    }

    protected boolean shouldJump() {return this.jumpTimer > this.getJumpTimerTargetValue() && !this.goalOwner.isTurtleGliding() && this.goalOwner.isOnGround();}
    protected boolean shouldStartGliding() {return this.goalOwner.getVelocity().y < -0.3D;}
    protected boolean shouldStopGliding() {return this.goalOwner.isTurtleGliding() && this.goalOwner.isOnGround() && this.goalOwner.getVelocity().y>=-0.1F;}
    protected boolean shouldIncrementJumpTimer() {return this.goalOwner.isOnGround() && !this.goalOwner.isTurtleGliding();}
    protected boolean shouldPanicBonusIncrementJumpTimer() {return this.goalOwner.getAttacker() != null && this.goalOwner.age - this.goalOwner.getLastAttackedTime() < 40;}
    protected void incrementJumpTimer() {this.jumpTimer += 1 + this.goalOwner.getRandom().nextInt(randomTimerBonus);}
    protected int getJumpTimerTargetValue() {return 1200;}
    protected void resetTask() {this.jumpTimer = 0; this.randomTimerBonus = 1 + this.goalOwner.getRandom().nextInt(10);}
}
