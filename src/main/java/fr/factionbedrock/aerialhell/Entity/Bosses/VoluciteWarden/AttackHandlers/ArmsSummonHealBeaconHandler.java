package fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.AttackHandlers;

import fr.factionbedrock.aerialhell.Entity.AI.Phase.InactivePhase;
import fr.factionbedrock.aerialhell.Entity.AI.Phase.Phase;
import fr.factionbedrock.aerialhell.Entity.AI.Phase.PhaseType;
import fr.factionbedrock.aerialhell.Entity.AI.VoluciteWarden.VoluciteWardenArmSummonHealBeaconGoal;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.AttackHandlers.Arm.SummonHealBeaconHandler;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.VoluciteWardenEntity;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.VoluciteWardenHealBeaconEntity;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.List;

//general handler, including right arm handler and left arm handler
//the arm places 1 or 2 heal beacons on its side, then the beacons heal the warden by themselves (see VoluciteWardenHealBeaconEntity)
public class ArmsSummonHealBeaconHandler
{
    //beacon positions (unrotated relative positions of the beacon bottom). angle 0 = in front of the warden, 90 = arm side, more than 90 = slightly behind
    public static final float MIN_BEACON_DISTANCE = 16.0F, MAX_BEACON_DISTANCE = 26.0F; //horizontal distance to the warden
    public static final float MIN_BEACON_HEIGHT = VoluciteWardenEntity.CORE_BOTTOM_RELATIVE_HEIGHT - 1.0F; //relative to the warden
    public static final float MAX_BEACON_HEIGHT = MIN_BEACON_HEIGHT + 10.0F;
    public static final float MIN_BEACON_ANGLE = 25.0F, MAX_BEACON_ANGLE = 115.0F;
    public static final float MIN_DISTANCE_BETWEEN_BEACONS = 8.0F; //between beacons placed by the same attack
    public static final int MAX_BEACONS_PER_ATTACK = 2; //1 to MAX_BEACONS_PER_ATTACK beacons per attack
    public static final int MAX_NEARBY_BEACONS = 4; //no attack (or less beacons) if there are already this many beacons in heal range of the warden
    public static final int BEACON_POS_ATTEMPTS = 20; //random positions tested per attack (positions in blocks are skipped)

    //hand positions, from the beacon position
    private static final float HAND_TO_BEACON_DISTANCE = 4.0F; //horizontal, hand placed between the warden and the beacon (hand and beacon must not overlap)
    private static final float AIM_PULL_BACK_DISTANCE = 2.5F, AIM_RAISE_HEIGHT = 3.0F; //aim pos : hand closer to the warden and higher than placing pos
    private static final int AIM_DURATION = 15; //ticks at aim pos, before placing the beacon
    private static final int PAUSE_AFTER_PLACING = 10; //ticks
    private static final double PLACING_SPEED = 0.6D;

    private final VoluciteWardenEntity warden;

    private final SummonHealBeaconHandler rightArmHandler;
    private final SummonHealBeaconHandler leftArmHandler;

    public ArmsSummonHealBeaconHandler(VoluciteWardenEntity warden, List<VoluciteWardenEntity.ArmPartInfo> rightArm, VoluciteWardenArmSummonHealBeaconGoal rightGoal, List<VoluciteWardenEntity.ArmPartInfo> leftArm, VoluciteWardenArmSummonHealBeaconGoal leftGoal)
    {
        this.warden = warden;
        this.rightArmHandler = new SummonHealBeaconHandler(warden, rightArm, rightGoal, this);
        this.leftArmHandler = new SummonHealBeaconHandler(warden, leftArm, leftGoal, this);
    }

    @Nullable public SummonHealBeaconHandler getOtherArmHandler(SummonHealBeaconHandler askingHandler)
    {
        if (askingHandler == this.leftArmHandler) {return this.rightArmHandler;}
        else if (askingHandler == this.rightArmHandler) {return this.leftArmHandler;}
        return null;
    }

    public boolean canTrigger(boolean isRightArm) {return (isRightArm ? this.rightArmHandler : this.leftArmHandler).canTrigger();}

    public List<Phase> getAttackSequence(boolean isRightArm)
    {
        return isRightArm ? this.rightArmHandler.attackSequence : this.leftArmHandler.attackSequence;
    }

    //arm handler is needed (instead of arm) because hand target pos depends on beacon positions
    //second beacon phases are skipped when the attack places only one beacon
    public List<Phase> createAttackSequence(SummonHealBeaconHandler armHandler)
    {
        int sideFactor = armHandler.isRightArm() ? 1 : -1;
        return List.of(
                new Phase(PhaseType.PREPARE, () -> this.getRelativePreparePos(sideFactor), 1.0D, 1)
                        .onStart((goal) -> armHandler.startAttack()), //choosing beacon positions before aiming phases : their target pos is read before their start
                new Phase(PhaseType.PREPARE, () -> this.getRelativeAimPos(armHandler.getBeaconPos(0)), 1.0D, AIM_DURATION)
                        .onTick((goal) -> armHandler.spawnAimParticles(0)),
                new Phase(PhaseType.ACTION, () -> this.getRelativePlacingPos(armHandler.getBeaconPos(0), armHandler), PLACING_SPEED, PAUSE_AFTER_PLACING)
                        .onTargetReached((goal) -> armHandler.placeBeacon(0))
                        .onEnd((goal) -> armHandler.skipToRecoveryIfNoBeacon(1)),
                new Phase(PhaseType.ACTION, () -> this.getRelativeAimPos(armHandler.getBeaconPos(1)), 1.0D, AIM_DURATION)
                        .onTick((goal) -> armHandler.spawnAimParticles(1)),
                new Phase(PhaseType.ACTION, () -> this.getRelativePlacingPos(armHandler.getBeaconPos(1), armHandler), PLACING_SPEED, PAUSE_AFTER_PLACING)
                        .onTargetReached((goal) -> armHandler.placeBeacon(1)),
                new Phase(PhaseType.RECOVERY, () -> this.getRelativeRecoveryPos(sideFactor), 0.4D, 1),
                new InactivePhase()
        );
    }

    //random beacon position on the arm side
    public Vec3 getRandomRelativeBeaconPos(int sideFactor, RandomSource random)
    {
        float angle = (float) Math.toRadians(Mth.nextFloat(random, MIN_BEACON_ANGLE, MAX_BEACON_ANGLE));
        float distance = Mth.nextFloat(random, MIN_BEACON_DISTANCE, MAX_BEACON_DISTANCE);
        float height = Mth.nextFloat(random, MIN_BEACON_HEIGHT, MAX_BEACON_HEIGHT);
        return new Vec3(sideFactor * distance * Mth.sin(angle), height, distance * Mth.cos(angle));
    }

    //arm raised on its side, before aiming at the first beacon pos
    private Vec3 getRelativePreparePos(int sideFactor) {return new Vec3(sideFactor * 15.0F, 18.0F, 2.0F);}

    //hand above the placing pos, a bit closer to the warden
    private Vec3 getRelativeAimPos(Vec3 beaconPos)
    {
        return this.getRelativeHandPosNearBeacon(beaconPos, HAND_TO_BEACON_DISTANCE + AIM_PULL_BACK_DISTANCE, AIM_RAISE_HEIGHT);
    }

    //hand between the warden and the beacon, centered on the beacon core height : the beacon appears right in front of the hand
    private Vec3 getRelativePlacingPos(Vec3 beaconPos, SummonHealBeaconHandler armHandler)
    {
        return this.getRelativeHandPosNearBeacon(beaconPos, HAND_TO_BEACON_DISTANCE, VoluciteWardenHealBeaconEntity.CORE_RELATIVE_HEIGHT - armHandler.goal.getHandHeight() / 2.0F);
    }

    //on the line between the warden vertical axis and the beacon, distanceToBeacon (horizontal) before the beacon
    private Vec3 getRelativeHandPosNearBeacon(Vec3 beaconPos, float distanceToBeacon, float heightOffset)
    {
        double beaconDistance = beaconPos.horizontalDistance();
        double handDistanceFactor = Math.max(0.0D, beaconDistance - distanceToBeacon) / beaconDistance;
        return new Vec3(beaconPos.x * handDistanceFactor, beaconPos.y + heightOffset, beaconPos.z * handDistanceFactor);
    }

    private Vec3 getRelativeRecoveryPos(int sideFactor) {return new Vec3(sideFactor * 9.5F, 5.5F, 0.0F);}
}
