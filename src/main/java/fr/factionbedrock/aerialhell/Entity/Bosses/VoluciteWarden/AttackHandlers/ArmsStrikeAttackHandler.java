package fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.AttackHandlers;

import fr.factionbedrock.aerialhell.Entity.AI.StrikeAttackGoal;
import fr.factionbedrock.aerialhell.Entity.AI.VoluciteWarden.VoluciteWardenArmStrikeAttackGoal;
import fr.factionbedrock.aerialhell.Entity.AI.Phase.Phase;
import fr.factionbedrock.aerialhell.Entity.AI.Phase.PhaseType;
import fr.factionbedrock.aerialhell.Entity.AI.Phase.InactivePhase;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.AttackHandlers.Arm.ArmStrikeAttackHandler;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.VoluciteWardenEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

//general handler, including right arm handler and left arm handler
public class ArmsStrikeAttackHandler
{
    private final VoluciteWardenEntity warden;
    private final ArmStrikeAttackHandler rightArmHandler;
    private final ArmStrikeAttackHandler leftArmHandler;
    private Vec3 strikeTargetPos = Vec3.ZERO;

    public ArmsStrikeAttackHandler(VoluciteWardenEntity warden, List<VoluciteWardenEntity.ArmPartInfo> rightArm, VoluciteWardenArmStrikeAttackGoal rightGoal, List<VoluciteWardenEntity.ArmPartInfo> leftArm, VoluciteWardenArmStrikeAttackGoal leftGoal)
    {
        this.warden = warden;
        this.rightArmHandler = new ArmStrikeAttackHandler(warden, rightArm, rightGoal, this);
        this.leftArmHandler = new ArmStrikeAttackHandler(warden, leftArm, leftGoal, this);
    }

    public boolean isClientSide() {return this.warden.level().isClientSide();}

    @Nullable public ArmStrikeAttackHandler getOtherArmHandler(ArmStrikeAttackHandler askingHandler)
    {
        if (askingHandler == this.leftArmHandler) {return this.rightArmHandler;}
        else if (askingHandler == this.rightArmHandler) {return this.leftArmHandler;}
        return null;
    }

    public void tick()
    {
        //updating hasStrikeActive and isStriking sync data (from server side)
        //those data are used client side to udate pos (below)
        if (!this.isClientSide())
        {
            this.warden.getEntityData().set(VoluciteWardenEntity.HAS_STRIKE_ACTIVE, this.isRightArmStriking() || this.isLeftArmStriking());
            this.warden.getEntityData().set(VoluciteWardenEntity.IS_STRIKING, this.rightArmHandler.goal.isStriking() || this.leftArmHandler.goal.isStriking());
        }

        //Update target position (only if warden is preparing strike & target is not null)
        //done both on server and client side, because client will need target pos to display particles
        if (this.warden.getEntityData().get(VoluciteWardenEntity.HAS_STRIKE_ACTIVE)) //using hasStrikeActive and isStriking synced data because goals do not exist client side
        {
            if (this.warden.getSyncedTarget() != null && !this.warden.getEntityData().get(VoluciteWardenEntity.IS_STRIKING))
            {
                this.strikeTargetPos = this.warden.toUnrotatedRelativePos(this.warden.getSyncedTarget().position());
            }
        }

        this.rightArmHandler.tick();
        this.leftArmHandler.tick();
    }

    public boolean isRightArmStriking() {return this.rightArmHandler.goal.isActive();}
    public boolean isLeftArmStriking() {return this.leftArmHandler.goal.isActive();}

    public ArmStrikeAttackHandler getRightArmHandler() {return this.rightArmHandler;}
    public ArmStrikeAttackHandler getLeftArmHandler() {return this.leftArmHandler;}

    public Vec3 getStrikeTargetPos() {return this.strikeTargetPos;}

    public List<Phase> getStrikeAttackSequence(@NotNull LivingEntity entityUsedToStrike)
    {
        if (this.rightArmHandler.getHandPart() == entityUsedToStrike) {return this.rightArmHandler.attackSequence;}
        else if (this.leftArmHandler.getHandPart() == entityUsedToStrike) {return this.leftArmHandler.attackSequence;}
        return this.rightArmHandler.attackSequence;
    }

    public List<Phase> createAttackSequence(List<VoluciteWardenEntity.ArmPartInfo> arm)
    {
        int sideFactor = arm.getFirst().isRightArm ? 1 : -1;
        return List.of(
                new Phase(PhaseType.PREPARE, () -> this.getRelativeWindupPos0(sideFactor), 1.0D, 1),
                new Phase(PhaseType.PREPARE, () -> this.getRelativeWindupPos1(sideFactor), 1.0D, 1),
                new Phase(PhaseType.PREPARE, () -> this.getRelativeWindupPos2(sideFactor), 1.0D, 1),
                new Phase(PhaseType.PREPARE, () -> this.getRelativeWindupPos3(sideFactor), 1.0D, 40),
                new Phase(PhaseType.ACTION, this::getRelativeStrikePos, 2.0D, 5)
                        .onTargetReached((goal) -> {if (goal instanceof StrikeAttackGoal strikeGoal) {strikeGoal.strike();}}),
                new Phase(PhaseType.RECOVERY, () -> this.getRelativeRecoveryPos(sideFactor), 0.4D, 1),
                new InactivePhase()
        );
    }

    private Vec3 getRelativeWindupPos0(int sideFactor) {return new Vec3(sideFactor * 12.0F, 8.5F, 4.0F);}
    private Vec3 getRelativeWindupPos1(int sideFactor) {return new Vec3(sideFactor * 20.0F, 18.0F, 8.0F);}
    private Vec3 getRelativeWindupPos2(int sideFactor) {return new Vec3(sideFactor * 31.0F, 31.0F, 4.0F);}
    private Vec3 getRelativeWindupPos3(int sideFactor) {return new Vec3(sideFactor * 10.0F, 37.0F, 0.0F);}

    public Vec3 getRelativeStrikePos() {return this.strikeTargetPos;}

    private Vec3 getRelativeRecoveryPos(int sideFactor) {return new Vec3(sideFactor * 9.5F, 5.5F, 0.0F);}
}