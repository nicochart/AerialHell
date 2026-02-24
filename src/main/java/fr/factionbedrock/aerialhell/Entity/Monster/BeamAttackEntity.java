package fr.factionbedrock.aerialhell.Entity.Monster;

import fr.factionbedrock.aerialhell.Entity.AI.BeamingPhases;
import fr.factionbedrock.aerialhell.Entity.BaseMobEntityInterface;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import org.jspecify.annotations.Nullable;

public interface BeamAttackEntity extends BaseMobEntityInterface
{
    /* ---------------------------------------------------- */
    /* ---------- Methods needing implementation ---------- */
    /* ---------------------------------------------------- */
    TrackedData<Integer> getBeamTargetEntityIdData();
    TrackedData<Integer> getBeamingPhaseData();
    TrackedData<Boolean> getBeamTargetPosNeedsSyncData();

    @Nullable LivingEntity getClientSideCachedAttackTarget();
    @Nullable Vec3d getBeamTargetPos();
    @Nullable Vec3d getPrevBeamTargetPos();
    @Nullable Vec3d getBeamEndPos();
    @Nullable Vec3d getPrevBeamEndPos();

    void setClientSideCachedAttackTarget(@Nullable LivingEntity entity);
    void setBeamTargetPos(Vec3d pos);
    void setPrevBeamTargetPos(Vec3d pos);
    void setBeamEndPos(Vec3d pos);
    void setPrevBeamEndPos(Vec3d pos);
    /* ---------------------------------------------------- */
    /* ---------------------------------------------------- */
    /* ---------------------------------------------------- */

    /* ----------------------------------------------- */
    /* -------- Delegate methods needing call -------- */
    /* ----------------------------------------------- */
    default void beamAttackTick() //call this in entity tick() method
    {
        boolean beamingSyncFlag = this.beamingTargetPosNeedsSync();
        if (beamingSyncFlag)
        {
            this.getEntityData().set(this.getBeamTargetPosNeedsSyncData(), false);

            //forcing client side beam target (entity and pos) update
            this.setClientSideCachedAttackTarget(null);
            this.setBeamTargetPos(null);
            this.setPrevBeamTargetPos(null);
            this.setBeamEndPos(null);
            this.setPrevBeamEndPos(null);
            if (!this.getLevel().isClient() && this.getTarget() != null)
            {
                this.setBeamTargetEntityId(this.getTarget().getId());
            }
        }
        else if (this.isBeaming())
        {
            //initializing beam pos if not done yet
            boolean needsInitialization = this.getBeamTargetPos() == null;
            if (needsInitialization && this.getBeamAttackTarget() != null && this.hasBeamTargetEntityId())
            {
                Vec3d targetInitialPos = this.getBeamAttackTarget().getEntityPos().add(getBeamTargetPosOffset(this.getBeamAttackTarget()));
                this.setBeamTargetPos(targetInitialPos);
                this.setPrevBeamTargetPos(targetInitialPos);
            }

            this.updateBeamPositions();
            this.displayParticles();
        }
    }
    /* ----------------------------------------------- */
    /* ----------------------------------------------- */
    /* ----------------------------------------------- */

    /* ---------------------------------------------------------------------------- */
    /* -------- Other methods to eventually override for specific behavior -------- */
    /* ---------------------------------------------------------------------------- */
    default SoundEvent getBeamSound(boolean beamStart) {return beamStart ? AerialHellSoundEvents.ENTITY_VOLUCITE_GOLEM_BEAM_START : AerialHellSoundEvents.ENTITY_VOLUCITE_GOLEM_BEAM_LOOP;}
    default int getBeamSoundLength() {return 35;}
    default boolean isBeamSilent() {return this.getSelf().isSilent();}

    default boolean canBeamHitEntity(LivingEntity entity) {return true;}
    default Entity getImmediateBeamSource() {return this.getSelf();}
    default Entity getTrueBeamSource() {return this.getSelf();}
    default void onStartBeaming(int beamingDuration) {this.getSelf().addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, beamingDuration, 2, false, false));}
    default void onStopBeaming() {this.getSelf().removeStatusEffect(StatusEffects.SLOWNESS);}

    default float getMaxBeamLength() {return 30.0F;}
    default Vec3d getBeamStartPos() {return this.getSelf().getEyePos();}
    default Vec3d getBeamTargetPosOffset(Entity target) {return new Vec3d(0, target.getBoundingBox().getLengthY() * 0.75F, 0);}

    @Nullable default SimpleParticleType getBeamParticles()
    {
        if (this.isBeamingLoadingPhase() || this.isBeamingOffPhase()) {return null;}
        return this.isBeamingNormalPhase() ? ParticleTypes.CRIMSON_SPORE : ParticleTypes.DUST_PLUME;
    }
    default float getParticleChance() {return 0.05F;}
    /* ---------------------------------------------------------------------------- */
    /* ---------------------------------------------------------------------------- */
    /* ---------------------------------------------------------------------------- */

    default void makeBeamStartSound(int currentBeamingTime) {this.makeBeamSound(true, currentBeamingTime);}
    default void makeBeamSound(int currentBeamingTime) {this.makeBeamSound(false, currentBeamingTime);}
    default void makeBeamSound(boolean beamStart, int currentBeamingTime) {if (this.shouldPlayBeamSound(currentBeamingTime)) {this.playBeamSound(beamStart);}}
    default void playBeamSound(boolean start) {this.getLevel().playSound(null, this.getX(), this.getY(), this.getZ(), this.getBeamSound(start), this.getSelf().getSoundCategory(), 0.5F, 1.0F);}
    default boolean shouldPlayBeamSound(int currentBeamingTime) {return !this.isBeamSilent() && currentBeamingTime % this.getBeamSoundLength() == 0;}

    default int getBeamTargetEntityId() {return this.getEntityData().get(this.getBeamTargetEntityIdData());}
    default boolean hasBeamTargetEntityId() {return this.getEntityData().get(this.getBeamTargetEntityIdData()) != 0;}
    default void setBeamTargetEntityId(int entityTargetId) {this.getEntityData().set(this.getBeamTargetEntityIdData(), entityTargetId);}
    default boolean isBeaming() {return this.getEntityData().get(this.getBeamingPhaseData()) != 0;}
    default int getBeamingPhase() {return this.getEntityData().get(this.getBeamingPhaseData());}
    default void setBeamingPhase(int phaseId) {this.getEntityData().set(this.getBeamingPhaseData(), phaseId);}
    default boolean beamingTargetPosNeedsSync() {return this.getEntityData().get(this.getBeamTargetPosNeedsSyncData());}
    default void setBeamingTargetPosNeedsSync() {this.getEntityData().set(this.getBeamTargetPosNeedsSyncData(), true);}

    @Nullable default LivingEntity getBeamAttackTarget() //must be client-server sync
    {
        if (!this.hasBeamTargetEntityId()) {return null;}
        else if (this.getLevel().isClient()) //Client side
        {
            if (this.getClientSideCachedAttackTarget() != null && this.getClientSideCachedAttackTarget().getId() == this.getBeamTargetEntityId()) //if client cached target exists & is valid (same id as synced id)
            {
                return this.getClientSideCachedAttackTarget();
            }
            else //trying to update clientSideCachedAttackTarget
            {
                Entity entity = this.getLevel().getEntityById(this.getBeamTargetEntityId());
                if (entity instanceof LivingEntity livingEntity)
                {
                    this.setClientSideCachedAttackTarget(livingEntity);
                    return this.getClientSideCachedAttackTarget();
                }
                else {return null;}
            }
        }
        else //Server side
        {
            LivingEntity serverSideTarget = this.getTarget();
            if (serverSideTarget == null) {return null;}

            if (serverSideTarget.getId() != this.getBeamTargetEntityId()) //updating synced id if necessary
            {
                this.setBeamTargetEntityId(serverSideTarget.getId());
            }
            return serverSideTarget;
        }
    }

    default void updateBeamPositions() //must be deterministic to be calculated the same way on both client and server sides
    {
        LivingEntity beamTarget = this.getBeamAttackTarget(); //the active target is the only synchronized thing
        Vec3d beamTargetPos = this.getBeamTargetPos(); //beamTargetPos is not the real target pos. It is a fictitious "target" following real target.
        Vec3d prevBeamTargetPos = this.getPrevBeamTargetPos();
        Vec3d beamEndPos = this.getBeamEndPos();
        Vec3d prevBeamEndPos = this.getPrevBeamEndPos();

        if (beamTarget == null || beamTargetPos == null) {return;}

        Vec3d previousStep = beamTargetPos.subtract(prevBeamTargetPos);
        this.setPrevBeamTargetPos(beamTargetPos);
        this.setPrevBeamEndPos(beamEndPos);
        prevBeamTargetPos = beamTargetPos;
        prevBeamEndPos = beamEndPos;

        //beamTarget update - fictitious "target" following real target
        Vec3d targetEntityPos = beamTarget.getEntityPos().add(getBeamTargetPosOffset(beamTarget));
        if (this.getBeamingPhase() == BeamingPhases.BEAMING_LOAD)
        {
            this.updateBeamTargetPosLinearWithMaxDistance(targetEntityPos, prevBeamTargetPos, 0.30F);
        }
        else if (this.getBeamingPhase() == BeamingPhases.BEAMING_NORMAL)
        {
            this.updateBeamTargetPosRealisticWithInertia(previousStep, targetEntityPos, prevBeamTargetPos, 0.95F, 0.04F , 0.3F); //0.95F, 0.04F, 0.3F allows easier dodge
        }
        else //if (this.getBeamingPhase() == BeamingPhases.BEAMING_OVERHEAT)
        {
            this.updateBeamTargetPosRealisticWithInertia(previousStep, targetEntityPos, prevBeamTargetPos, 0.99F, 0.05F , 0.4F);
        }

        //beamEnd update - the pos where the beam hits a solid obstacle (or reaches max distance)
        this.updateBeamEndPos(this.getBeamTargetPos(), this.getMaxBeamLength());
    }

    private void updateBeamTargetPosLinearWithMaxDistance(Vec3d targetEntityPos, Vec3d prevBeamTargetPos, float maxVelocity)
    {
        Vec3d velocity = targetEntityPos.subtract(prevBeamTargetPos);
        this.setBeamTargetPos(velocity.length() <= maxVelocity ? targetEntityPos : prevBeamTargetPos.add(velocity.normalize().multiply(maxVelocity)));
    }

    private void updateBeamTargetPosRealisticWithInertia(Vec3d previousVelocity, Vec3d targetEntityPos, Vec3d prevBeamTargetPos, float inertiaFactor, float attractionFactor, float maxVelocity)
    {
        Vec3d linearVelocity = targetEntityPos.subtract(prevBeamTargetPos);
        Vec3d acceleration = linearVelocity.multiply(attractionFactor);

        Vec3d velocity = previousVelocity.multiply(inertiaFactor).add(acceleration);
        this.setBeamTargetPos(prevBeamTargetPos.add(velocity.length() < maxVelocity ? velocity : velocity.normalize().multiply(maxVelocity)));
    }

    private void updateBeamEndPos(Vec3d beamTargetPos, float maxDistance)
    {
        Vec3d beamStart = this.getBeamStartPos();
        Vec3d direction = beamTargetPos.subtract(beamStart).normalize();
        Vec3d furthestBeamEnd = beamStart.add(direction.multiply(maxDistance));

        BlockHitResult beamHit = this.getLevel().raycast(new RaycastContext(this.getBeamStartPos(), furthestBeamEnd, RaycastContext.ShapeType.COLLIDER, RaycastContext.FluidHandling.NONE, this.getSelf()));
        this.setBeamEndPos(beamHit.getType() == HitResult.Type.BLOCK ? beamHit.getPos() : furthestBeamEnd);
    }

    default boolean isBeamingLoadingPhase() {return this.getBeamingPhase() == BeamingPhases.BEAMING_LOAD;}
    default boolean isBeamingNormalPhase() {return this.getBeamingPhase() == BeamingPhases.BEAMING_NORMAL;}
    default boolean isBeamingOverheatPhase() {return this.getBeamingPhase() == BeamingPhases.BEAMING_OVERHEAT;}
    default boolean isBeamingOffPhase() {return this.getBeamingPhase() == BeamingPhases.OFF;}
    default void setBeamingPhaseToLoading() {this.setBeamingPhase(BeamingPhases.BEAMING_LOAD);}
    default void setBeamingPhaseToNormal() {this.setBeamingPhase(BeamingPhases.BEAMING_NORMAL);}
    default void setBeamingPhaseToOverheat() {this.setBeamingPhase(BeamingPhases.BEAMING_OVERHEAT);}
    default void setBeamingPhaseToOff() {this.setBeamingPhase(BeamingPhases.OFF);}

    private void displayParticles()
    {
        SimpleParticleType type = this.getBeamParticles();
        if (type != null && this.getBeamEndPos() != null)
        {

            Vec3d beamStart = this.getBeamStartPos();
            Vec3d beamEnd = this.getBeamEndPos();
            Vec3d delta = beamEnd.subtract(beamStart);
            double distance = delta.length();

            if (distance < 0.001D) return;

            Vec3d direction = delta.normalize();

            double particleStep = 0.2D;
            float chance = this.getParticleChance();

            for (double traveled = 0.0D; traveled <= distance; traveled += particleStep)
            {
                if (this.getSelf().getRandom().nextFloat() < chance)
                {
                    Vec3d pos = beamStart.add(direction.multiply(traveled));
                    this.getLevel().addParticleClient(type, pos.x, pos.y, pos.z, 0.0D, 0.0D, 0.0D);
                }
            }
        }
    }
}
