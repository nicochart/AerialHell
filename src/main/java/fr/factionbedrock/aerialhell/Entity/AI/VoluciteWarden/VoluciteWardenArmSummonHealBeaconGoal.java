package fr.factionbedrock.aerialhell.Entity.AI.VoluciteWarden;

import fr.factionbedrock.aerialhell.Entity.AI.Phase.Phase;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.VoluciteWardenEntity;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.VoluciteWardenHealBeaconEntity;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;
import java.util.function.Supplier;

public class VoluciteWardenArmSummonHealBeaconGoal extends VoluciteWardenArmGoal
{
    private static final DustParticleOptions HEAL_PARTICLE = new DustParticleOptions(VoluciteWardenHealBeaconEntity.ACTIVE_COLOR, 1.5F); //same color as the beacon active layer

    public VoluciteWardenArmSummonHealBeaconGoal(VoluciteWardenEntity entity, Supplier<List<VoluciteWardenEntity.ArmPartInfo>> arm, float distanceOffsetTolerance) {super(entity, arm, distanceOffsetTolerance);}

    @Override public List<Phase> getPhases() {return this.goalOwner.armsSummonHealBeaconHandler.getAttackSequence(this.arm.get().getFirst().isRightArm);}

    @Override public boolean shouldTrigger() {return this.goalOwner.getTarget() != null && this.goalOwner.shouldTriggerArmSummonHealBeacon();}

    public Vec3 getHandLevelPos() {return this.goalOwner.fromUnrotatedRelativeToLevelPos(this.getCachedUnrotatedRelativePos());}

    public float getHandHeight() {return this.getGuide() != null ? this.getGuide().getBbHeight() : 0.0F;}

    //beacons in heal range of the warden (placed by this warden or not)
    public int countNearbyHealBeacons()
    {
        Vec3 coreCenterPos = VoluciteWardenHealBeaconEntity.getWardenCoreCenterPos(this.goalOwner, 1.0F);
        return this.goalOwner.level().getEntitiesOfClass(VoluciteWardenHealBeaconEntity.class, new AABB(coreCenterPos, coreCenterPos).inflate(VoluciteWardenHealBeaconEntity.HEAL_RANGE), LivingEntity::isAlive).size();
    }

    //no block (nor collidable entity, like other beacons or warden parts) where the beacon would be
    public boolean isFreeBeaconPos(Vec3 unrotatedRelativeBeaconPos)
    {
        Vec3 beaconPos = this.goalOwner.fromUnrotatedRelativeToLevelPos(unrotatedRelativeBeaconPos);
        return this.goalOwner.level().noCollision(AerialHellEntities.VOLUCITE_WARDEN_HEAL_BEACON.get().getDimensions().makeBoundingBox(beaconPos));
    }

    //while aiming : particles on the hand, and where the beacon will appear
    public void spawnAimParticles(Vec3 unrotatedRelativeBeaconPos)
    {
        if (this.goalOwner.level() instanceof ServerLevel level)
        {
            Vec3 handCenterPos = this.getHandLevelPos().add(0.0D, this.getHandHeight() / 2.0F, 0.0D);
            Vec3 beaconCorePos = this.goalOwner.fromUnrotatedRelativeToLevelPos(unrotatedRelativeBeaconPos).add(0.0D, VoluciteWardenHealBeaconEntity.CORE_RELATIVE_HEIGHT, 0.0D);
            level.sendParticles(HEAL_PARTICLE, handCenterPos.x, handCenterPos.y, handCenterPos.z, 3, 1.0D, 1.0D, 1.0D, 0.0D);
            level.sendParticles(HEAL_PARTICLE, beaconCorePos.x, beaconCorePos.y, beaconCorePos.z, 2, 0.6D, 0.6D, 0.6D, 0.0D);
        }
    }

    //the beacon finds the nearest warden by itself (see VoluciteWardenHealBeaconFindWardenGoal)
    public void placeHealBeacon(Vec3 unrotatedRelativeBeaconPos)
    {
        if (!(this.goalOwner.level() instanceof ServerLevel level)) {return;}
        if (!this.isFreeBeaconPos(unrotatedRelativeBeaconPos)) {return;} //checked again : the warden may have turned since the attack start

        VoluciteWardenHealBeaconEntity beacon = AerialHellEntities.VOLUCITE_WARDEN_HEAL_BEACON.get().create(level, EntitySpawnReason.MOB_SUMMONED);
        if (beacon == null) {return;}

        Vec3 beaconPos = this.goalOwner.fromUnrotatedRelativeToLevelPos(unrotatedRelativeBeaconPos);
        beacon.snapTo(beaconPos.x, beaconPos.y, beaconPos.z, this.goalOwner.getYRot(), 0.0F);
        level.addFreshEntity(beacon);

        Vec3 beaconCorePos = beacon.getCoreCenterPos(1.0F);
        level.sendParticles(HEAL_PARTICLE, beaconCorePos.x, beaconCorePos.y, beaconCorePos.z, 40, 1.2D, 1.2D, 1.2D, 0.0D);
        level.playSound(null, beaconCorePos.x, beaconCorePos.y, beaconCorePos.z, SoundEvents.STONE_PLACE, this.goalOwner.getSoundSource(), 3.0F, 0.5F);
    }
}
