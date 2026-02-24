package fr.factionbedrock.aerialhell.Entity.AI;

import fr.factionbedrock.aerialhell.Entity.Monster.BeamAttackEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellDamageTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class BeamAttackGoal extends Goal
{
    private final BeamAttackEntity entity;
    private final int beamingLoadDuration;
    private final int beamingOverheatDuration;
    private final int beamingTotalDuration;
    private final int beamingCooldownDuration;
    private int currentBeamingTime;
    private int beamingCooldown;

    public BeamAttackGoal(BeamAttackEntity entity, int beamingLoadDuration, int beamingOverheatDuration, int beamingTotalDuration, int cooldownDuration)
    {
        this.entity = entity;
        this.beamingLoadDuration = beamingLoadDuration;
        this.beamingOverheatDuration = beamingOverheatDuration;
        this.beamingTotalDuration = beamingTotalDuration;
        this.beamingCooldownDuration = cooldownDuration;
        this.currentBeamingTime = 0;
        this.beamingCooldown = 0;
        //this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK)); //can't disable move and look flags because they are needed to avoid parasite head position change by move controls.. will need to separate head and body.
    }

    @Override public boolean canStart()
    {
        if (this.beamingCooldown > 0) {this.beamingCooldown--; return false;}
        LivingEntity livingentity = this.entity.getTarget();
        return livingentity != null && livingentity.isAlive();
    }

    @Override public boolean shouldContinue()
    {
        return super.shouldContinue() && (this.entity.getTarget() != null /*&& this.entity.distanceToSqr(this.entity.getTarget()) < (double)480.0F*/);
    }

    @Override public void start()
    {
        this.currentBeamingTime = 0;
        this.entity.setBeamingPhaseToLoading();
        this.entity.onStartBeaming(this.beamingTotalDuration);
        //this.entity.getNavigation().stop(); slowness for the duration ?
        LivingEntity livingentity = this.entity.getTarget();
        if (livingentity != null)
        {
            this.entity.getLookControl().lookAt(livingentity, 90.0F, 90.0F);
        }

        this.entity.makeBeamStartSound(this.currentBeamingTime);
        this.entity.setNeedsSync();
        this.entity.setBeamingTargetPosNeedsSync();
    }

    @Override public void stop()
    {
        this.entity.setBeamTargetEntityId(0);
        this.entity.setBeamingPhaseToOff();
        this.entity.onStopBeaming();
        //this.entity.setTarget((LivingEntity)null);
        this.beamingCooldown = beamingCooldownDuration;
        this.currentBeamingTime = 0;
        //this.entity.randomStrollGoal.trigger();
        this.entity.setBeamingTargetPosNeedsSync();
    }

    @Override public boolean shouldRunEveryTick() {return true;}

    @Override public void tick()
    {
        ++this.currentBeamingTime;
        this.entity.makeBeamSound(this.currentBeamingTime);
        LivingEntity livingentity = this.entity.getBeamAttackTarget();
        if (livingentity != null)
        {
            Vec3d beamTargetPos = this.entity.getBeamTargetPos();
            if (beamTargetPos == null)
            {
                //search new target ?
            }
            else
            {
                this.entity.getLookControl().lookAt(beamTargetPos.x, beamTargetPos.y, beamTargetPos.z, 90.0F, 90.0F);
                float hardDifficultyDamageBonus = this.entity.getLevel().getDifficulty() == Difficulty.HARD ? 2.0F : 0.0F;

                if (this.currentBeamingTime < this.beamingLoadDuration)
                {
                    //beam loading
                }
                else if (this.currentBeamingTime < this.beamingTotalDuration - this.beamingOverheatDuration)
                {
                    //normal power
                    if (!this.entity.isBeamingNormalPhase()) {this.entity.setBeamingPhaseToNormal();}
                    this.hitEntities(4.0F + hardDifficultyDamageBonus);
                }
                else if (this.currentBeamingTime < this.beamingTotalDuration)
                {
                    //full power
                    if (!this.entity.isBeamingOverheatPhase()) {this.entity.setBeamingPhaseToOverheat();}
                    this.hitEntities(6.0F + hardDifficultyDamageBonus);
                }
                else //if (this.currentBeamingTime >= this.beamingDuration)
                {
                    this.stop();
                }
                super.tick();
            }
        }
    }

    public void hitEntities(float damage)
    {
        ServerWorld serverWorld = getServerWorld(this.entity.getSelf());
        List<Entity> hitEntities = getBeamHitEntities(this.entity.getLevel(), this.entity.getSelf(), this.entity.getBeamStartPos(), this.entity.getBeamEndPos());

        for (Entity entity : hitEntities)
        {
            if (entity instanceof LivingEntity livingHit && this.entity.canBeamHitEntity(livingHit))
            {
                livingHit.damage(serverWorld, AerialHellDamageTypes.getDamageSource(this.entity.getLevel(), AerialHellDamageTypes.GOLEM_BEAM, this.entity.getImmediateBeamSource(), this.entity.getTrueBeamSource()), damage);
                //this.entity.getSelf().tryAttack(serverWorld, livingHit); //hit animation off
            }
        }
    }

    public static List<Entity> getBeamHitEntities(World world, LivingEntity beamingEntity, Vec3d beamStart, Vec3d beamEnd)
    {
        Box boxFromBeamStartToBeamEnd = new Box(beamStart, beamEnd).expand(1.0);

        List<Entity> entitiesInBox = world.getOtherEntities(beamingEntity, boxFromBeamStartToBeamEnd, EntityPredicates.VALID_ENTITY);

        List<Entity> hits = new ArrayList<>();

        for (Entity entity : entitiesInBox)
        {
            Box hitbox = entity.getBoundingBox().expand(0.3);
            hitbox.raycast(beamStart, beamEnd).ifPresent(vec3 -> hits.add(new EntityHitResult(entity, vec3).getEntity()));
        }

        return hits;
    }
}