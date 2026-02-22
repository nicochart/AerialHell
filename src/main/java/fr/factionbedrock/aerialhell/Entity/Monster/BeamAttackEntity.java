package fr.factionbedrock.aerialhell.Entity.Monster;

import fr.factionbedrock.aerialhell.Entity.AI.BeamingPhases;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.control.LookControl;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

public interface BeamAttackEntity
{
    Mob getSelf();

    SynchedEntityData getEntityData();
    EntityDataAccessor<Integer> getBeamTargetEntityIdData();
    EntityDataAccessor<Integer> getBeamingPhaseData();
    EntityDataAccessor<Boolean> getBeamTargetPosNeedsSyncData();

    @Nullable LivingEntity getClientSideCachedAttackTarget();
    @Nullable Vec3 getBeamTargetPos();
    @Nullable Vec3 getPrevBeamTargetPos();
    @Nullable Vec3 getBeamEndPos();
    @Nullable Vec3 getPrevBeamEndPos();

    void setClientSideCachedAttackTarget(@Nullable LivingEntity entity);
    void setBeamTargetPos(Vec3 pos);
    void setPrevBeamTargetPos(Vec3 pos);
    void setBeamEndPos(Vec3 pos);
    void setPrevBeamEndPos(Vec3 pos);

    default void makeBeamStartSound(int currentBeamingTime) {this.makeBeamSound(true, currentBeamingTime);}
    default void makeBeamSound(int currentBeamingTime) {this.makeBeamSound(false, currentBeamingTime);}
    default void makeBeamSound(boolean beamStart, int currentBeamingTime) {if (this.shouldPlayBeamSound(currentBeamingTime)) {this.playBeamSound(beamStart);}}
    default void playBeamSound(boolean start) {this.getLevel().playSound(null, this.getSelf().getX(), this.getSelf().getY(), this.getSelf().getZ(), this.getBeamSound(start), this.getSelf().getSoundSource(), 0.5F, 1.0F);}
    default boolean shouldPlayBeamSound(int currentBeamingTime) {return !this.isBeamSilent() && currentBeamingTime % this.getBeamSoundLength() == 0;}
    default SoundEvent getBeamSound(boolean beamStart) {return beamStart ? AerialHellSoundEvents.ENTITY_VOLUCITE_GOLEM_BEAM_START.get() : AerialHellSoundEvents.ENTITY_VOLUCITE_GOLEM_BEAM_LOOP.get();}
    default int getBeamSoundLength() {return 35;}
    default boolean isBeamSilent() {return this.getSelf().isSilent();}

    default float getMaxBeamLength() {return 30.0F;}
    default Vec3 getBeamStartPos() {return this.getSelf().getEyePosition();}
    default Vec3 getBeamTargetPosOffset(Entity target) {return new Vec3(0, target.getBoundingBox().getYsize() * 0.75F, 0);}

    default LivingEntity getTarget() {return this.getSelf().getTarget();}
    default LookControl getLookControl() {return this.getSelf().getLookControl();}
    default void setNeedsSync() {this.getSelf().needsSync = true;}
    default Level getLevel() {return this.getSelf().level();}

    default int getBeamTargetEntityId() {return this.getEntityData().get(this.getBeamTargetEntityIdData());}
    default boolean hasBeamTargetEntityId() {return this.getEntityData().get(this.getBeamTargetEntityIdData()) != 0;}
    default void setBeamTargetEntityId(int entityTargetId) {this.getEntityData().set(this.getBeamTargetEntityIdData(), entityTargetId);}
    default boolean isBeaming() {return this.getEntityData().get(this.getBeamingPhaseData()) != 0;}
    default int getBeamingPhase() {return this.getEntityData().get(this.getBeamingPhaseData());}
    default void setBeamingPhase(int phaseId) {this.getEntityData().set(this.getBeamingPhaseData(), phaseId);}
    default boolean beamingTargetPosNeedsSync() {return this.getEntityData().get(this.getBeamTargetPosNeedsSyncData());}
    default void setBeamingTargetPosNeedsSync() {this.getEntityData().set(this.getBeamTargetPosNeedsSyncData(), true);}

    default boolean canBeamHitEntity(LivingEntity entity) {return true;}
    default Entity getImmediateBeamSource() {return this.getSelf();}
    default Entity getTrueBeamSource() {return this.getSelf();}
    default void onStartBeaming(int beamingDuration) {this.getSelf().addEffect(new MobEffectInstance(MobEffects.SLOWNESS, beamingDuration, 2, false, false));}
    default void onStopBeaming() {this.getSelf().removeEffect(MobEffects.SLOWNESS);}

    @Nullable default LivingEntity getBeamAttackTarget() //must be client-server sync
    {
        if (!this.hasBeamTargetEntityId()) {return null;}
        else if (this.getLevel().isClientSide()) //Client side
        {
            if (this.getClientSideCachedAttackTarget() != null && this.getClientSideCachedAttackTarget().getId() == this.getBeamTargetEntityId()) //if client cached target exists & is valid (same id as synced id)
            {
                return this.getClientSideCachedAttackTarget();
            }
            else //trying to update clientSideCachedAttackTarget
            {
                Entity entity = this.getLevel().getEntity(this.getBeamTargetEntityId());
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
            if (!this.getLevel().isClientSide() && this.getTarget() != null)
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
                Vec3 targetInitialPos = this.getBeamAttackTarget().position().add(getBeamTargetPosOffset(this.getBeamAttackTarget()));
                this.setBeamTargetPos(targetInitialPos);
                this.setPrevBeamTargetPos(targetInitialPos);
            }

            this.updateBeamPositions();
            this.displayParticles();
        }
    }

    default void updateBeamPositions() //must be deterministic to be calculated the same way on both client and server sides
    {
        LivingEntity beamTarget = this.getBeamAttackTarget(); //the active target is the only synchronized thing
        Vec3 beamTargetPos = this.getBeamTargetPos(); //beamTargetPos is not the real target pos. It is a fictitious "target" following real target.
        Vec3 prevBeamTargetPos = this.getPrevBeamTargetPos();
        Vec3 beamEndPos = this.getBeamEndPos();
        Vec3 prevBeamEndPos = this.getPrevBeamEndPos();

        if (beamTarget == null || beamTargetPos == null) {return;}

        Vec3 previousStep = beamTargetPos.subtract(prevBeamTargetPos);
        this.setPrevBeamTargetPos(beamTargetPos);
        this.setPrevBeamEndPos(beamEndPos);
        prevBeamTargetPos = beamTargetPos;
        prevBeamEndPos = beamEndPos;

        //beamTarget update - fictitious "target" following real target
        Vec3 targetEntityPos = beamTarget.position().add(getBeamTargetPosOffset(beamTarget));
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

    private void updateBeamTargetPosLinearWithMaxDistance(Vec3 targetEntityPos, Vec3 prevBeamTargetPos, float maxVelocity)
    {
        Vec3 velocity = targetEntityPos.subtract(prevBeamTargetPos);
        this.setBeamTargetPos(velocity.length() <= maxVelocity ? targetEntityPos : prevBeamTargetPos.add(velocity.normalize().scale(maxVelocity)));
    }

    private void updateBeamTargetPosRealisticWithInertia(Vec3 previousVelocity, Vec3 targetEntityPos, Vec3 prevBeamTargetPos, float inertiaFactor, float attractionFactor, float maxVelocity)
    {
        Vec3 linearVelocity = targetEntityPos.subtract(prevBeamTargetPos);
        Vec3 acceleration = linearVelocity.scale(attractionFactor);

        Vec3 velocity = previousVelocity.scale(inertiaFactor).add(acceleration);
        this.setBeamTargetPos(prevBeamTargetPos.add(velocity.length() < maxVelocity ? velocity : velocity.normalize().scale(maxVelocity)));
    }

    private void updateBeamEndPos(Vec3 beamTargetPos, float maxDistance)
    {
        Vec3 beamStart = this.getBeamStartPos();
        Vec3 direction = beamTargetPos.subtract(beamStart).normalize();
        Vec3 furthestBeamEnd = beamStart.add(direction.scale(maxDistance));

        BlockHitResult beamHit = this.getLevel().clip(new ClipContext(this.getBeamStartPos(), furthestBeamEnd, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, this.getSelf()));
        this.setBeamEndPos(beamHit.getType() == HitResult.Type.BLOCK ? beamHit.getLocation() : furthestBeamEnd);
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
        if (!this.isBeamingLoadingPhase() && !this.isBeamingOffPhase() && this.getBeamEndPos() != null)
        {
            ParticleOptions type = this.isBeamingNormalPhase() ? ParticleTypes.CRIMSON_SPORE : ParticleTypes.DUST_PLUME;
            Vec3 beamStart = this.getBeamStartPos();
            Vec3 beamEnd = this.getBeamEndPos();
            Vec3 delta = beamEnd.subtract(beamStart);
            double distance = delta.length();

            if (distance < 0.001D) return;

            Vec3 direction = delta.normalize();

            double particleStep = 0.2D;
            float chance = 0.05F;

            for (double traveled = 0.0D; traveled <= distance; traveled += particleStep)
            {
                if (this.getSelf().getRandom().nextFloat() < chance)
                {
                    Vec3 pos = beamStart.add(direction.scale(traveled));
                    this.getLevel().addParticle(type, pos.x, pos.y, pos.z, 0.0D, 0.0D, 0.0D);
                }
            }
        }
    }
}
