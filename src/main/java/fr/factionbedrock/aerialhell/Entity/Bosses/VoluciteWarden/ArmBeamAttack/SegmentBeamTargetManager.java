package fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.ArmBeamAttack;

import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.VoluciteWardenArmSegmentEntity;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.VoluciteWardenEntity;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class SegmentBeamTargetManager
{
    private final VoluciteWardenEntity warden;
    private final Predicate<Entity> targetPredicate;
    private final Map<LivingEntity, TargetAssignment> targetPool = new HashMap<>();

    private int scanCooldown = 0;
    private static final int SCAN_INTERVAL = 200; //10 seconds

    public SegmentBeamTargetManager(VoluciteWardenEntity warden, Predicate<Entity> targetPredicate) {this.warden = warden; this.targetPredicate = targetPredicate;}

    public void tick()
    {
        if (--this.scanCooldown <= 0)
        {
            this.scanForTargets();
            this.scanCooldown = SCAN_INTERVAL;
        }

        //validating and cleaning existing targets
        this.validateAndCleanTargets();

        // 3. Affectation automatique si les bras attaquent
        // (À adapter selon comment tu stockes tes variables d'état de bras)
        if (this.warden.isRightArmBeaming())
        {
            this.assignTargetsForArm(this.warden.getRightArm(), true);
        }
        if (this.warden.isLeftArmBeaming())
        {
            this.assignTargetsForArm(this.warden.getLeftArm(), false);
        }
    }

    //heavy scan (updating the list of potential targets
    private void scanForTargets()
    {
        List<LivingEntity> foundEntities = EntityHelper.getTargetableLivingEntitiesInInflatedBoundingBox(this.warden, VoluciteWardenArmSegmentEntity.MAX_BEAM_LENGTH, targetPredicate);

        for (LivingEntity entity : foundEntities)
        {
            this.targetPool.putIfAbsent(entity, new TargetAssignment());
        }
    }

    private void validateAndCleanTargets()
    {
        Iterator<Map.Entry<LivingEntity, TargetAssignment>> iterator = this.targetPool.entrySet().iterator();

        while (iterator.hasNext())
        {
            Map.Entry<LivingEntity, TargetAssignment> entry = iterator.next();
            LivingEntity target = entry.getKey();

            if (!targetPredicate.test(target))
            {
                TargetAssignment duo = entry.getValue();
                //managing target loss on segment
                if (duo.getRightSegment() != null) duo.getRightSegment().setTarget(null);
                if (duo.getLeftSegment() != null) duo.getLeftSegment().setTarget(null);

                iterator.remove();
            }
        }
    }

    private void assignTargetsForArm(List<VoluciteWardenEntity.ArmPartInfo> arm, boolean isRightArm)
    {
        for (VoluciteWardenEntity.ArmPartInfo armPartInfo : arm)
        {
            if (armPartInfo.getPart() == null || !(armPartInfo.getPart().getSelf() instanceof VoluciteWardenArmSegmentEntity segment)) {continue;}
            //if has valid target
            if (segment.getBeamAttackTarget() != null) {continue;}

            //else
            LivingEntity bestTarget = null;

            for (Map.Entry<LivingEntity, TargetAssignment> entry : this.targetPool.entrySet())
            {
                if (entry.getValue().canAccept(isRightArm))
                {
                    bestTarget = entry.getKey();
                    entry.getValue().assignSegment(isRightArm, segment);
                    break;
                }
            }

            segment.setTarget(bestTarget);
        }
    }

    //call when beam goal is disabled
    public void clearArmTargets(boolean isRightArm)
    {
        for (TargetAssignment duo : this.targetPool.values())
        {
            duo.removeSegment(isRightArm);
        }
    }
}