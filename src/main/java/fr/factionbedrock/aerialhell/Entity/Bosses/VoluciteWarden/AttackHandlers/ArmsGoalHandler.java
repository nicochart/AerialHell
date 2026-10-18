package fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.AttackHandlers;

import fr.factionbedrock.aerialhell.Entity.AI.Phase.PhaseType;
import fr.factionbedrock.aerialhell.Entity.AI.VoluciteWarden.VoluciteWardenArmGoal;
import fr.factionbedrock.aerialhell.Entity.Bosses.BossPhase;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.VoluciteWardenEntity;
import org.jetbrains.annotations.Nullable;

//global handler of arm goals : chooses which goal each arm uses, using weighted probabilities (armGoalWeights, updated depending on boss phase)
public class ArmsGoalHandler
{
    private final VoluciteWardenEntity warden;
    private final ArmInfo rightArmInfo = new ArmInfo(true);
    private final ArmInfo leftArmInfo = new ArmInfo(false);

    private final ArmGoalWeights armGoalWeights = new ArmGoalWeights();
    @Nullable private BossPhase armGoalWeightsPhase = null; //phase used to compute current armGoalWeights

    public ArmsGoalHandler(VoluciteWardenEntity warden) {this.warden = warden;}

    public void tick()
    {
        if (this.warden.level().isClientSide()) {return;}

        this.tickArmGoalWeights();

        this.tickArm(this.rightArmInfo);
        this.tickArm(this.leftArmInfo);
    }

    public ArmGoalWeights getArmGoalWeights() {return this.armGoalWeights;}

    private void tickArmGoalWeights()
    {
        BossPhase phase = this.warden.getPhase();
        if (phase != this.armGoalWeightsPhase)
        {
            this.armGoalWeightsPhase = phase;
            this.updateArmGoalWeights(phase);
        }
    }

    //weighted probabilities of each arm goal, depending on boss phase. a goal with weight 0 is never used
    protected void updateArmGoalWeights(BossPhase phase)
    {
        this.armGoalWeights.clear();
        if (phase == BossPhase.FIRST_PHASE)
        {
            this.armGoalWeights.set(ArmGoalType.STRIKE, 5).set(ArmGoalType.BEAM, 1).set(ArmGoalType.SUMMON_ALLY, 1);
        }
        else if (phase == BossPhase.SECOND_PHASE)
        {
            this.armGoalWeights.set(ArmGoalType.STRIKE, 1).set(ArmGoalType.BEAM, 5).set(ArmGoalType.SUMMON_ALLY, 2);
        }
        //transition, dying & dead phases : no arm goal
    }

    private void tickArm(ArmInfo armInfo)
    {
        if (this.warden.isArmActive(armInfo.isRightArm)) {armInfo.inactiveTicks = 0; return;}

        armInfo.inactiveTicks++;
        if (armInfo.inactiveTicks > armInfo.cooldown && this.canArmTrigger(armInfo.isRightArm))
        {
            @Nullable ArmGoalType goalType = this.getArmGoalWeights().pick(this.warden.getRandom(), type -> this.canTriggerGoal(type, armInfo.isRightArm));
            if (goalType != null && this.triggerGoal(goalType, armInfo.isRightArm))
            {
                armInfo.lastGoal = goalType;
                armInfo.cooldown = this.rollCooldown();
            }
        }
    }

    public boolean triggerGoal(ArmGoalType goalType, boolean isRightArm)
    {
        @Nullable VoluciteWardenArmGoal goal = this.warden.getArmGoal(goalType, isRightArm);
        return goal != null && goal.trigger();
    }

    //conditions common to all goals
    private boolean canArmTrigger(boolean isRightArm)
    {
        return this.warden.getTarget() != null && !this.warden.isArmActive(isRightArm) && !this.isArmPreparing(!isRightArm);
    }

    //goal-specific conditions
    private boolean canTriggerGoal(ArmGoalType goalType, boolean isRightArm)
    {
        return switch (goalType)
        {
            case BEAM -> this.warden.armsBeamAttackHandler.canTrigger(isRightArm);
            case STRIKE -> this.warden.armsStrikeAttackHandler.canTrigger(isRightArm);
            case SUMMON_ALLY -> this.warden.armsSummonAllyHandler.canTrigger(isRightArm);
        };
    }

    public boolean isArmPreparing(boolean isRightArm)
    {
        for (ArmGoalType goalType : ArmGoalType.values())
        {
            @Nullable VoluciteWardenArmGoal goal = this.warden.getArmGoal(goalType, isRightArm);
            if (goal != null && goal.getPhaseType() == PhaseType.PREPARE) {return true;}
        }
        return false;
    }

    @Nullable public ArmGoalType getLastGoal(boolean isRightArm) {return this.getArmInfo(isRightArm).lastGoal;}

    private ArmInfo getArmInfo(boolean isRightArm) {return isRightArm ? this.rightArmInfo : this.leftArmInfo;}

    private int rollCooldown() {return this.warden.getRandom().nextInt(200);}

    private static class ArmInfo
    {
        private final boolean isRightArm;
        private int inactiveTicks = 0;
        private int cooldown = 40;
        @Nullable private ArmGoalType lastGoal = null;

        private ArmInfo(boolean isRightArm) {this.isRightArm = isRightArm;}
    }
}
