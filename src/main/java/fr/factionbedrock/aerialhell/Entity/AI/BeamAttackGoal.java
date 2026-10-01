package fr.factionbedrock.aerialhell.Entity.AI;

import fr.factionbedrock.aerialhell.Entity.Monster.BeamAttackEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellDamageTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

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

    public int beamingLoadDuration() {return this.beamingLoadDuration;}
    public int currentBeamingTime() {return this.currentBeamingTime;}
    public int beamingTotalDuration() {return this.beamingTotalDuration;}

    public BeamAttackEntity getGoalOwner() {return this.entity;}

    @Override public boolean canUse()
    {
        if (!this.decreaseAndCheckCooldown()) {return false;}
        LivingEntity target = this.entity.getTarget();
        return target != null && target.isAlive() && this.entity.getSelf().distanceToSqr(target) <= this.entity.getMaxBeamLength() * this.entity.getMaxBeamLength();
    }

    @Override public boolean canContinueToUse()
    {
        LivingEntity target = this.entity.getTarget();
        boolean isFinished = this.currentBeamingTime >= this.beamingTotalDuration;
        return !isFinished && super.canContinueToUse() && (target != null && this.entity.getSelf().distanceToSqr(target) <= this.entity.getMaxBeamLength() * this.entity.getMaxBeamLength());
    }

    //if beam is under cooldown : decreases the cooldown and return false
    //else : return true
    public boolean decreaseAndCheckCooldown()
    {
        if (this.beamingCooldown > 0) {this.beamingCooldown--; return false;}
        return true;
    }

    @Override public void start()
    {
        this.currentBeamingTime = 0;
        this.entity.setBeamingPhaseToLoading();
        this.entity.onStartBeaming(this.beamingTotalDuration);
        //this.entity.getNavigation().stop(); slowness for the duration ?

        this.entity.setNeedsSync();
    }

    @Override public void stop()
    {
        this.entity.setBeamingPhaseToOff();
        this.entity.onStopBeaming();
        //this.entity.setTarget((LivingEntity)null);
        this.beamingCooldown = beamingCooldownDuration;
        this.currentBeamingTime = 0;
        //this.entity.randomStrollGoal.trigger();
    }

    @Override public boolean requiresUpdateEveryTick() {return true;}

    @Override public void tick()
    {
        ++this.currentBeamingTime;
        this.tickBeamSounds(this.currentBeamingTime, this.beamingLoadDuration);
        Vec3 beamTargetPos = this.entity.getBeamTargetPos();
        if (beamTargetPos == null)
        {
            //search new target ?
        }
        else
        {
            this.setLookAt(beamTargetPos);
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
            else //(this.currentBeamingTime < this.beamingTotalDuration)
            {
                //full power
                if (!this.entity.isBeamingOverheatPhase()) {this.entity.setBeamingPhaseToOverheat();}
                this.hitEntities(6.0F + hardDifficultyDamageBonus);
            }
            super.tick();
        }
    }

    //it is possible to disable setLookAt by overriding this method
    public void setLookAt(@NotNull Vec3 beamTargetPos)
    {
        this.entity.getLookControl().setLookAt(beamTargetPos.x, beamTargetPos.y, beamTargetPos.z, 90.0F, 90.0F);
    }

    public void tickBeamSounds(int currentBeamingTime, int loadDuration)
    {
        this.entity.tickBeamSounds(this.currentBeamingTime, this.beamingLoadDuration, this.beamingTotalDuration);
    }

    public void hitEntities(float damage)
    {
        ServerLevel serverlevel = getServerLevel(this.entity.getSelf());
        List<Entity> hitEntities = getBeamHitEntities(this.entity.getLevel(), this.entity.getSelf(), this.entity.getBeamStartPos(), this.entity.getBeamEndPos(), this.entity.getBeamScale());

        for (Entity entity : hitEntities)
        {
            if (entity instanceof LivingEntity livingHit && this.entity.canBeamHitEntity(livingHit))
            {
                livingHit.hurtServer(serverlevel, AerialHellDamageTypes.getDamageSource(this.entity.getLevel(), AerialHellDamageTypes.GOLEM_BEAM, this.entity.getImmediateBeamSource(), this.entity.getTrueBeamSource()), damage);
                //this.entity.getSelf().doHurtTarget(serverlevel, livingHit); //hit animation off
            }
        }
    }

    public static List<Entity> getBeamHitEntities(Level level, LivingEntity beamingEntity, Vec3 beamStart, Vec3 beamEnd, float beamScale)
    {
        float hitRadius = 0.105F * beamScale;

        AABB boxFromBeamStartToBeamEnd = new AABB(beamStart, beamEnd).inflate(1.0F + hitRadius);

        List<Entity> entitiesInBox = level.getEntities(beamingEntity, boxFromBeamStartToBeamEnd, EntitySelector.ENTITY_STILL_ALIVE);
        List<Entity> hits = new ArrayList<>();

        for (Entity entity : entitiesInBox)
        {
            AABB hitbox = entity.getBoundingBox().inflate(hitRadius);
            hitbox.clip(beamStart, beamEnd).ifPresent(vec3 -> hits.add(new EntityHitResult(entity, vec3).getEntity()));
        }

        return hits;
    }
}