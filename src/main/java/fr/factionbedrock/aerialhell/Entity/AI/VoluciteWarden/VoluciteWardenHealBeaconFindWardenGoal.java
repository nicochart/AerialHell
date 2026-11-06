package fr.factionbedrock.aerialhell.Entity.AI.VoluciteWarden;

import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.VoluciteWardenEntity;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.VoluciteWardenHealBeaconEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.Comparator;
import java.util.EnumSet;

//heal beacon target : nearest volucite warden in heal range. the target is kept while it is valid (alive, in range, healable phase, see VoluciteWardenHealBeaconEntity.isValidHealTarget)
public class VoluciteWardenHealBeaconFindWardenGoal extends Goal
{
    private static final int SCAN_INTERVAL = 20; //ticks

    public final VoluciteWardenHealBeaconEntity goalOwner;
    @Nullable private VoluciteWardenEntity foundWarden;
    private int scanCooldown = 0;

    public VoluciteWardenHealBeaconFindWardenGoal(VoluciteWardenHealBeaconEntity entity)
    {
        this.goalOwner = entity;
        this.setFlags(EnumSet.of(Flag.TARGET));
    }

    @Override public boolean canUse()
    {
        if (this.goalOwner.getTarget() != null || --this.scanCooldown > 0) {return false;}
        this.scanCooldown = SCAN_INTERVAL;
        this.foundWarden = this.findNearestWarden();
        return this.foundWarden != null;
    }

    @Override public boolean canContinueToUse() {return this.goalOwner.getHealTarget() != null;}

    @Override public void start() {this.goalOwner.setTarget(this.foundWarden);}

    @Override public void stop()
    {
        this.goalOwner.setTarget(null);
        this.foundWarden = null;
    }

    @Nullable private VoluciteWardenEntity findNearestWarden()
    {
        Vec3 corePos = this.goalOwner.getCoreCenterPos(1.0F);
        return this.goalOwner.level().getEntitiesOfClass(VoluciteWardenEntity.class, this.goalOwner.getBoundingBox().inflate(VoluciteWardenHealBeaconEntity.HEAL_RANGE), this.goalOwner::isValidHealTarget)
                .stream().min(Comparator.comparingDouble(warden -> VoluciteWardenHealBeaconEntity.getWardenCoreCenterPos(warden, 1.0F).distanceToSqr(corePos))).orElse(null);
    }
}
