package fr.factionbedrock.aerialhell.Entity.AI;

import fr.factionbedrock.aerialhell.Entity.AI.Phase.Phase;
import fr.factionbedrock.aerialhell.Entity.AI.Phase.PhaseType;
import fr.factionbedrock.aerialhell.Entity.StrikeAttackEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Supplier;

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

    @Override public boolean canUse()
    {
        this.onCanUse();
        return this.isActive();
    }

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

    public static class StrikeInfo
    {
        private final Supplier<LivingEntity> entityUsedToStrikeSupplier;
        private final float explosionRadius;
        private final float bonusDamageAmount;
        private final float bonusDamageRange;
        private final float knockbackScale;
        private final boolean destroyBlocks;

        public StrikeInfo(Supplier<LivingEntity> entityUsedToStrikeSupplier, float explosionRadius, boolean destroyBlocks) {this(entityUsedToStrikeSupplier, explosionRadius, 0.0F, 0.0F, 0.0F, destroyBlocks);}
        public StrikeInfo(Supplier<LivingEntity> entityUsedToStrikeSupplier, float explosionRadius, float bonusDamageAmount, float bonusDamageRange, float knockbackScale, boolean destroyBlocks)
        {
            this.entityUsedToStrikeSupplier = entityUsedToStrikeSupplier;
            this.explosionRadius = explosionRadius;
            this.bonusDamageAmount = bonusDamageAmount;
            this.bonusDamageRange = bonusDamageRange;
            this.knockbackScale = knockbackScale;
            this.destroyBlocks = destroyBlocks;
        }
    }
}