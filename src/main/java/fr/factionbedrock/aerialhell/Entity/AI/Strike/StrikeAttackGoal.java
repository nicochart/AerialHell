package fr.factionbedrock.aerialhell.Entity.AI.Strike;

import fr.factionbedrock.aerialhell.Entity.AI.Phase.Phase;
import fr.factionbedrock.aerialhell.Entity.AI.Phase.PhaseType;
import fr.factionbedrock.aerialhell.Entity.AI.PhaseGoal;
import fr.factionbedrock.aerialhell.Entity.StrikeAttackEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class StrikeAttackGoal extends Goal implements PhaseGoal
{
    public final StrikeAttackEntity goalOwner;
    private final StrikeInfo strikeInfo;
    public final PhaseInfo phaseInfo;

    public StrikeAttackGoal(StrikeAttackEntity goalOwner, float distanceOffsetTolerance, StrikeInfo strikeInfo)
    {
        this.goalOwner = goalOwner;
        this.strikeInfo = strikeInfo;
        this.phaseInfo = new PhaseInfo(distanceOffsetTolerance, this.goalOwner.getSelf());
    }

    @Override public Goal getSelf() {return this;}

    @Override public PhaseInfo getPhaseInfo() {return this.phaseInfo;}

    @Override public List<Phase> getPhases() {return this.goalOwner.getStrikeAttackSequenceInternal(this.getEntityUsedToStrike());}

    @Override public boolean canUse() {return this.canUsePhaseGoal();}

    //can automatically trigger
    @Override public boolean shouldTrigger()
    {
        return this.goalOwner.getTarget() != null && this.goalOwner.shouldTriggerStrikeAttack();
    }

    @Override public boolean canContinueToUse() {return this.canContinueToUsePhaseGoal();}

    @Override public void start() {this.onStart();}
    @Override public void stop() {this.onStop();}

    @Override public boolean requiresUpdateEveryTick() {return true;}

    @Override public void tick() {this.tickPhase();}

    @Override public LivingEntity getGuide() {return this.getEntityUsedToStrike();}

    @Nullable public LivingEntity getEntityUsedToStrike() {return this.strikeInfo.entityUsedToStrikeSupplier.get();}

    public boolean isStriking() {return this.getPhaseType() == PhaseType.ACTION;}

    public void strike()
    {
        if (this.getEntityUsedToStrike() != null)
        {
            this.goalOwner.strike(this.goalOwner.fromUnrotatedRelativeToLevelPos(this.getCurrentPhase().getUnrotatedRelativeTargetPos()), this.getEntityUsedToStrike(), this.strikeInfo.explosionRadius, this.strikeInfo.bonusDamageAmount, this.strikeInfo.bonusDamageRange, this.strikeInfo.knockbackScale, this.strikeInfo.destroyBlocks);
        }
    }
}