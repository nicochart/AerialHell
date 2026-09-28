package fr.factionbedrock.aerialhell.Entity.AI.VoluciteWarden;

import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.VoluciteWardenEntity;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.VoluciteWardenHeadEntity;
import fr.factionbedrock.aerialhell.Entity.MultipartEntity.PartEntity;
import fr.factionbedrock.aerialhell.Entity.MultipartEntity.PartInfo;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class VoluciteWardenMainBeamAttackGoal extends Goal
{
    public final VoluciteWardenEntity goalOwner;
    public final Supplier<PartInfo> headInfo;

    public VoluciteWardenMainBeamAttackGoal(VoluciteWardenEntity entity, Supplier<PartInfo> headInfo)
    {
        this.goalOwner = entity;
        this.headInfo = headInfo;
    }

    public void enableBeam()
    {
        VoluciteWardenHeadEntity head = this.getHeadInfo();
        if (head != null) {head.enableBeam(true);}
    }

    public void disableBeam()
    {
        VoluciteWardenHeadEntity head = this.getHeadInfo();
        if (head != null) {head.enableBeam(false);}
    }

    @Override public boolean canUse()
    {
        if (this.goalOwner.getTarget() != null && this.goalOwner.shouldTriggerMainBeamAttack()) {this.trigger();}
        return this.isActive();
    }

    @Override public boolean canContinueToUse() {return this.isActive();}

    @Override public void start() {this.setHeadTarget(this.goalOwner.getTarget());}
    @Override public void stop()
    {
        this.disableBeam();
        this.setHeadTarget(null);
    }

    private void setHeadTarget(@Nullable LivingEntity target)
    {
        VoluciteWardenHeadEntity head = this.getHeadInfo();
        if (head != null) {head.setTarget(target);}
    }

    @Override public boolean requiresUpdateEveryTick() {return true;} //TODO is it necessary ?

    @Override public void tick()
    {
        this.setMasterLookAt();
    }

    protected void setMasterLookAt()
    {
        LivingEntity lookTarget = this.goalOwner.getTarget();
        if (lookTarget != null)
        {
            this.goalOwner.getSelf().lookAt(lookTarget, 30.0F, 30.0F);
        }
    }

    public boolean isActive() {return this.getHeadInfo() instanceof VoluciteWardenHeadEntity head && head.isBeamEnabled();}
    public boolean trigger() //return true if the attack sequence is successfully triggered
    {
        if (this.isActive()) {return false;}
        this.enableBeam();
        return this.isActive();
    }

    @Nullable public VoluciteWardenHeadEntity getHeadInfo()
    {
        PartInfo headPartInfo = this.headInfo.get();
        if (headPartInfo == null) {return null;}
        PartEntity part = headPartInfo.getPart();
        if (part == null) {return null;}
        return part.getSelf() instanceof VoluciteWardenHeadEntity voluciteWardenHead ? voluciteWardenHead : null;
    }
}