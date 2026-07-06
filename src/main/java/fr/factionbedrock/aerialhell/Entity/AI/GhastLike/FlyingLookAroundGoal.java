package fr.factionbedrock.aerialhell.Entity.AI.GhastLike;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

/* Same as net.minecraft.world.entity.monster.Ghast.GhastLookGoal but changed GhastEntity to Mob */
public class FlyingLookAroundGoal extends Goal
{
    private final Mob parentEntity;

    public FlyingLookAroundGoal(Mob flyingMob)
    {
        this.parentEntity = flyingMob;
        this.setFlags(EnumSet.of(Flag.LOOK));
    }

    public Mob getParentEntity() {return parentEntity;}

    @Override public boolean canUse() {return true;}

    @Override public void tick()
    {
        if (this.parentEntity.getTarget() == null)
        {
            Vec3 vec = this.parentEntity.getDeltaMovement();
            this.parentEntity.setYRot(-((float) Mth.atan2(vec.x, vec.z)) * (180F / (float) Math.PI));
            this.parentEntity.yBodyRot = this.parentEntity.getYRot();
        }
        else
        {
            LivingEntity livingentity = this.parentEntity.getTarget();
            if (livingentity.distanceToSqr(this.parentEntity) < 64 * 64)
            {
                double x = livingentity.getX() - this.parentEntity.getX();
                double z = livingentity.getZ() - this.parentEntity.getZ();
                this.parentEntity.setYRot(-((float) Mth.atan2(x, z)) * (180F / (float) Math.PI));
                this.parentEntity.yBodyRot = this.parentEntity.getYRot();
            }
        }
    }
}
