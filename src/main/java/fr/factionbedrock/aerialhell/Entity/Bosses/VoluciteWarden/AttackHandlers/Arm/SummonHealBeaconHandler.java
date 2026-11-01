package fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.AttackHandlers.Arm;

import fr.factionbedrock.aerialhell.Entity.AI.Phase.Phase;
import fr.factionbedrock.aerialhell.Entity.AI.VoluciteWarden.VoluciteWardenArmSummonHealBeaconGoal;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.AttackHandlers.ArmsSummonHealBeaconHandler;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.VoluciteWardenEntity;
import net.minecraft.util.RandomSource;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SummonHealBeaconHandler
{
    public final VoluciteWardenEntity warden;
    public final List<VoluciteWardenEntity.ArmPartInfo> arm;
    public final VoluciteWardenArmSummonHealBeaconGoal goal;
    public final ArmsSummonHealBeaconHandler globalHandler;
    public final List<Phase> attackSequence;

    //chosen at attack start (unrotated relative positions of the beacons bottom), ordered from front to back
    private final List<Vec3> beaconPositions = new ArrayList<>();

    public SummonHealBeaconHandler(VoluciteWardenEntity warden, List<VoluciteWardenEntity.ArmPartInfo> arm, VoluciteWardenArmSummonHealBeaconGoal goal, ArmsSummonHealBeaconHandler globalHandler)
    {
        this.warden = warden;
        this.arm = arm;
        this.goal = goal;
        this.globalHandler = globalHandler;
        this.attackSequence = globalHandler.createAttackSequence(this);
    }

    public boolean isRightArm() {return this.arm.getFirst().isRightArm;}

    public boolean hasBeaconPos(int beaconIndex) {return beaconIndex < this.beaconPositions.size();}

    //last chosen pos if there is no beacon at this index (phases using it are skipped)
    public Vec3 getBeaconPos(int beaconIndex)
    {
        if (this.beaconPositions.isEmpty()) {return new Vec3((this.isRightArm() ? 1 : -1) * ArmsSummonHealBeaconHandler.MIN_BEACON_DISTANCE, ArmsSummonHealBeaconHandler.MIN_BEACON_HEIGHT, 0.0F);}
        return this.beaconPositions.get(Math.min(beaconIndex, this.beaconPositions.size() - 1));
    }

    //called at attack start : chooses 1 to MAX_BEACONS_PER_ATTACK free beacon positions. if there is none, the attack is cancelled
    public void startAttack()
    {
        this.beaconPositions.clear();

        RandomSource random = this.warden.getRandom();
        int sideFactor = this.isRightArm() ? 1 : -1;
        int beaconCount = Math.min(1 + random.nextInt(ArmsSummonHealBeaconHandler.MAX_BEACONS_PER_ATTACK), ArmsSummonHealBeaconHandler.MAX_NEARBY_BEACONS - this.goal.countNearbyHealBeacons());
        for (int attempt = 0; attempt < ArmsSummonHealBeaconHandler.BEACON_POS_ATTEMPTS && this.beaconPositions.size() < beaconCount; attempt++)
        {
            Vec3 beaconPos = this.globalHandler.getRandomRelativeBeaconPos(sideFactor, random);
            if (this.isFarFromChosenPositions(beaconPos) && this.goal.isFreeBeaconPos(beaconPos)) {this.beaconPositions.add(beaconPos);}
        }

        if (this.beaconPositions.isEmpty()) {this.goal.skipToInactivePhase(); return;} //arm did not move yet
        this.beaconPositions.sort(Comparator.comparingDouble(beaconPos -> -beaconPos.z)); //front beacon first, the hand then goes backward
    }

    private boolean isFarFromChosenPositions(Vec3 beaconPos)
    {
        for (Vec3 chosenPos : this.beaconPositions)
        {
            if (chosenPos.distanceToSqr(beaconPos) < ArmsSummonHealBeaconHandler.MIN_DISTANCE_BETWEEN_BEACONS * ArmsSummonHealBeaconHandler.MIN_DISTANCE_BETWEEN_BEACONS) {return false;}
        }
        return true;
    }

    public void spawnAimParticles(int beaconIndex)
    {
        if (this.hasBeaconPos(beaconIndex)) {this.goal.spawnAimParticles(this.getBeaconPos(beaconIndex));}
    }

    public void placeBeacon(int beaconIndex)
    {
        if (this.hasBeaconPos(beaconIndex)) {this.goal.placeHealBeacon(this.getBeaconPos(beaconIndex));}
    }

    //called at the end of a beacon placing phase : if there is no next beacon, the hand goes back to its default pos
    public void skipToRecoveryIfNoBeacon(int nextBeaconIndex)
    {
        if (!this.hasBeaconPos(nextBeaconIndex)) {this.goal.skipToRecoveryPhase();}
    }

    //goal-specific trigger conditions (common conditions and triggering are handled by ArmsGoalHandler)
    //never both arms summoning heal beacons at the same time. no heal at full health, or when there are already enough beacons around the warden
    public boolean canTrigger()
    {
        @Nullable SummonHealBeaconHandler otherArmHandler = this.globalHandler.getOtherArmHandler(this);
        if (otherArmHandler != null && otherArmHandler.goal.isActive()) {return false;}

        return this.warden.getHealth() < this.warden.getMaxHealth() && this.goal.countNearbyHealBeacons() < ArmsSummonHealBeaconHandler.MAX_NEARBY_BEACONS;
    }
}
