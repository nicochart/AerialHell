package fr.factionbedrock.aerialhell.Entity.AI;

import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;

public class DirectMeleeAttackGoal extends Goal
{
    private final PathfinderMob goalOwner;
    private final double speedModifier;
    private final double attackReach;
    private LivingEntity target;
    private int attackCooldown;

    public DirectMeleeAttackGoal(PathfinderMob boss, double speedModifier, double attackReach)
    {
        this.goalOwner = boss;
        this.speedModifier = speedModifier;
        this.attackReach = attackReach;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override public boolean canUse()
    {
        LivingEntity target = this.goalOwner.getTarget();
        if (!isValidTarget(target)) return false;
        this.target = target;
        return true;
    }

    @Override public boolean canContinueToUse()
    {
        return isValidTarget(this.target);
    }

    private boolean isValidTarget(@Nullable LivingEntity target)
    {
        if (target == null || !target.isAlive()) return false;
        if (EntityHelper.isCreaOrSpecPlayer(target)) {return false;} //TODO improve valid target code
        return true;
    }

    @Override public void start()
    {
        this.attackCooldown = 0;
        this.goalOwner.setAggressive(true);
    }

    @Override public void stop()
    {
        this.goalOwner.setAggressive(false);
        this.goalOwner.getNavigation().stop();
        this.target = null;
    }
    @Override public boolean requiresUpdateEveryTick() { return true; }

    @Override public void tick()
    {
        if (this.target == null) return;

        this.goalOwner.getLookControl().setLookAt(this.target, 30.0F, 30.0F);

        //this.boss.getNavigation().moveTo(this.target.getX(), this.target.getY(), this.target.getZ(), this.speedModifier); //normal pathfinding. Bug where the boss stops before reaching the player, and starts "spinning" because it considers itself "within range"
        this.goalOwner.getMoveControl().setWantedPosition(this.target.getX(), this.target.getY(), this.target.getZ(), this.speedModifier); //straight forward to target. Will hit obstacles.
        
        double dx = this.target.getX() - this.goalOwner.getX();
        double dz = this.target.getZ() - this.goalOwner.getZ();
        double horizontalDistSqr = dx * dx + dz * dz;

        this.attackCooldown--;
        if (this.attackCooldown <= 0 && horizontalDistSqr <= this.attackReach * this.attackReach)
        {
            this.attackCooldown = 20;
            this.goalOwner.swing(InteractionHand.MAIN_HAND);
            this.goalOwner.doHurtTarget(getServerLevel(this.goalOwner), this.target);
        }
    }
}