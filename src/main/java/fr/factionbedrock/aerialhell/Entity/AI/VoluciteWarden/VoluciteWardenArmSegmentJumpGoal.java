package fr.factionbedrock.aerialhell.Entity.AI.VoluciteWarden;

import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.VoluciteWardenArmSegmentEntity;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;

//final phase : semi detached arm segments jump around like giant magma cubes, and hurt nearby entities when landing (see VoluciteWardenArmSegmentEntity.onJumpLanding)
//jump destination (independent of the warden) : toward the segment own target (see VoluciteWardenArmSegmentTargetGoal), else random
//without target, random jumps go back toward the goal start position (detach position) when too far from it : semi detached parts die beyond VoluciteWardenPartEntity.SEMI_DETACHED_MAX_DISTANCE_TO_MASTER
public class VoluciteWardenArmSegmentJumpGoal extends Goal
{
    private static final int MIN_JUMP_DELAY = 60, MAX_JUMP_DELAY = 120; //ticks on ground between two jumps, with a target (random : segments don't jump all together)
    private static final int NO_TARGET_JUMP_DELAY_FACTOR = 2; //without target, the jump delay is counted down 2 times slower
    private static final double JUMP_VERTICAL_SPEED = 1.1D; //~7 blocks high, ~27 ticks in the air
    private static final double MIN_HORIZONTAL_SPEED = 0.2D, MAX_HORIZONTAL_SPEED = 1.0D; //up to ~10 blocks per jump
    private static final double HORIZONTAL_DISTANCE_PER_SPEED = 10.2D; //horizontal distance of a jump, per horizontal speed unit (air friction 0.91 per tick, ~27 ticks in the air)
    private static final float MAX_DIRECTION_OFFSET = 30.0F * Mth.DEG_TO_RAD; //random offset of the jump direction
    private static final double RANDOM_JUMP_DISTANCE = 6.0D;
    private static final double MAX_RANDOM_JUMP_DISTANCE_FROM_START = 20.0D; //horizontal

    private final VoluciteWardenArmSegmentEntity armSegment;
    private Vec3 startPos = Vec3.ZERO;
    private int jumpDelay;
    private boolean isJumping;
    private int airTicks;

    public VoluciteWardenArmSegmentJumpGoal(VoluciteWardenArmSegmentEntity armSegment)
    {
        this.armSegment = armSegment;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.JUMP));
    }

    @Override public boolean canUse() {return this.armSegment.canJumpAround();}
    @Override public boolean canContinueToUse() {return this.canUse();}

    @Override public void start()
    {
        this.startPos = this.armSegment.position();
        this.resetJumpDelay();
        this.isJumping = false;
        this.airTicks = 0;
    }

    @Override public boolean requiresUpdateEveryTick() {return true;}

    @Override public void tick()
    {
        if (this.isJumping)
        {
            if (!this.armSegment.onGround()) {this.airTicks++;}
            else if (this.airTicks > 0) //landed
            {
                this.isJumping = false;
                this.armSegment.onJumpLanding();
                this.resetJumpDelay();
            }
        }
        else if (this.armSegment.onGround())
        {
            this.jumpDelay -= this.armSegment.getTarget() != null ? NO_TARGET_JUMP_DELAY_FACTOR : 1; //a target found (or lost) while waiting changes the remaining delay
            if (this.jumpDelay <= 0) {this.jump();}
        }
    }

    private void resetJumpDelay() {this.jumpDelay = Mth.nextInt(this.armSegment.getRandom(), MIN_JUMP_DELAY, MAX_JUMP_DELAY) * NO_TARGET_JUMP_DELAY_FACTOR;}

    private void jump()
    {
        RandomSource random = this.armSegment.getRandom();
        Vec3 destination = this.getJumpDestination(random);
        double xOffset = destination.x - this.armSegment.getX(), zOffset = destination.z - this.armSegment.getZ();
        double horizontalDistance = Math.sqrt(xOffset * xOffset + zOffset * zOffset);

        float angle = horizontalDistance < 1.0E-4D ? random.nextFloat() * Mth.TWO_PI : (float) Mth.atan2(zOffset, xOffset);
        angle += Mth.nextFloat(random, -MAX_DIRECTION_OFFSET, MAX_DIRECTION_OFFSET);
        double horizontalSpeed = Mth.clamp(horizontalDistance / HORIZONTAL_DISTANCE_PER_SPEED, MIN_HORIZONTAL_SPEED, MAX_HORIZONTAL_SPEED);

        this.armSegment.setDeltaMovement(Mth.cos(angle) * horizontalSpeed, JUMP_VERTICAL_SPEED, Mth.sin(angle) * horizontalSpeed);
        this.armSegment.needsSync = true; //immediate motion sync
        this.isJumping = true;
        this.airTicks = 0;
    }

    private Vec3 getJumpDestination(RandomSource random)
    {
        @Nullable LivingEntity target = this.armSegment.getTarget();
        if (target != null && target.isAlive()) {return target.position();}

        double xToStart = this.startPos.x - this.armSegment.getX(), zToStart = this.startPos.z - this.armSegment.getZ();
        if (xToStart * xToStart + zToStart * zToStart > MAX_RANDOM_JUMP_DISTANCE_FROM_START * MAX_RANDOM_JUMP_DISTANCE_FROM_START) {return this.startPos;}

        float angle = random.nextFloat() * Mth.TWO_PI;
        return this.armSegment.position().add(Mth.cos(angle) * RANDOM_JUMP_DISTANCE, 0.0D, Mth.sin(angle) * RANDOM_JUMP_DISTANCE);
    }
}
