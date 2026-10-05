package fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.AttackHandlers;

import fr.factionbedrock.aerialhell.Entity.AI.Phase.InactivePhase;
import fr.factionbedrock.aerialhell.Entity.AI.Phase.Phase;
import fr.factionbedrock.aerialhell.Entity.AI.Phase.PhaseType;
import fr.factionbedrock.aerialhell.Entity.AI.VoluciteWarden.VoluciteWardenArmSummonAllyGoal;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.AttackHandlers.Arm.SummonAllyHandler;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.VoluciteWardenEntity;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.List;

//general handler, including right arm handler and left arm handler
public class ArmsSummonAllyHandler
{
    private final VoluciteWardenEntity warden;

    private final SummonAllyHandler rightArmHandler;
    private final SummonAllyHandler leftArmHandler;

    public ArmsSummonAllyHandler(VoluciteWardenEntity warden, List<VoluciteWardenEntity.ArmPartInfo> rightArm, VoluciteWardenArmSummonAllyGoal rightGoal, List<VoluciteWardenEntity.ArmPartInfo> leftArm, VoluciteWardenArmSummonAllyGoal leftGoal)
    {
        this.warden = warden;
        this.rightArmHandler = new SummonAllyHandler(warden, rightArm, rightGoal, this);
        this.leftArmHandler = new SummonAllyHandler(warden, leftArm, leftGoal, this);
    }

    public boolean isClientSide() {return this.warden.level().isClientSide();}

    @Nullable public SummonAllyHandler getOtherArmHandler(SummonAllyHandler askingHandler)
    {
        if (askingHandler == this.leftArmHandler) {return this.rightArmHandler;}
        else if (askingHandler == this.rightArmHandler) {return this.leftArmHandler;}
        return null;
    }

    public void tick()
    {
        this.rightArmHandler.tick();
        this.leftArmHandler.tick();
    }

    public List<Phase> getAttackSequence(boolean isRightArm)
    {
        return isRightArm ? this.rightArmHandler.attackSequence : this.leftArmHandler.attackSequence;
    }

    public List<Phase> createAttackSequence(List<VoluciteWardenEntity.ArmPartInfo> arm)
    {
        int sideFactor = arm.getFirst().isRightArm ? 1 : -1;
        return List.of(
                new Phase(PhaseType.PREPARE, () -> this.getRelativePreparePos0(sideFactor), 1.0D, 1),
                new Phase(PhaseType.PREPARE, () -> this.getRelativePreparePos1(sideFactor), 1.0D, 1),
                new Phase(PhaseType.PREPARE, () -> this.getRelativePreparePos2(sideFactor), 1.0D, 1),
                new Phase(PhaseType.ACTION, () -> this.getRelativePreparePos3(sideFactor), 1.0D, 20)
                        .onEnd((goal) -> {if (goal instanceof VoluciteWardenArmSummonAllyGoal summonAllyGoal) {summonAllyGoal.summonAlly();}}),
                new Phase(PhaseType.RECOVERY, () -> this.getRelativeSummoningPos(sideFactor), 2.0D, 20),
                new Phase(PhaseType.RECOVERY, () -> this.getRelativeRecoveryPos(sideFactor), 0.4D, 1),
                new InactivePhase()
        );
    }

    private Vec3 getRelativePreparePos0(int sideFactor) {return new Vec3(sideFactor * 12.0F, 10.0F, 0.0F);}
    private Vec3 getRelativePreparePos1(int sideFactor) {return new Vec3(sideFactor * 18.0F, 17.0F, 2.0F);}
    private Vec3 getRelativePreparePos2(int sideFactor) {return new Vec3(sideFactor * 24.0F, 23.0F, 4.0F);}
    private Vec3 getRelativePreparePos3(int sideFactor) {return new Vec3(sideFactor * 28.0F, 26.5F, 5.0F);}
    private Vec3 getRelativeSummoningPos(int sideFactor) {return this.getRelativePreparePos3(sideFactor);}
    private Vec3 getRelativeRecoveryPos(int sideFactor) {return new Vec3(sideFactor * 9.5F, 5.5F, 0.0F);}
}