package fr.factionbedrock.aerialhell.Entity.AI.VoluciteWarden;

import fr.factionbedrock.aerialhell.Entity.AI.Phase.Phase;
import fr.factionbedrock.aerialhell.Entity.AI.Phase.PhaseType;
import fr.factionbedrock.aerialhell.Entity.AI.Strike.StrikeInfo;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.VoluciteWardenEntity;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Supplier;

public class VoluciteWardenArmStrikeAttackGoal extends VoluciteWardenArmGoal
{
    private final StrikeInfo strikeInfo;

    public VoluciteWardenArmStrikeAttackGoal(VoluciteWardenEntity entity, Supplier<List<VoluciteWardenEntity.ArmPartInfo>> arm, Supplier<LivingEntity> entityUsedToStrikeSupplier, float distanceOffsetTolerance)
    {
        super(entity, arm, distanceOffsetTolerance);
        this.strikeInfo = new StrikeInfo(entityUsedToStrikeSupplier, 0.0F, 10.0F, 4.0F, 3.5F, false);
    }

    @Override public List<Phase> getPhases() {return this.goalOwner.getStrikeAttackSequenceInternal(this.getEntityUsedToStrike());}

    @Override public boolean shouldTrigger() {return this.goalOwner.getTarget() != null && this.goalOwner.shouldTriggerStrikeAttack();}

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
