package fr.factionbedrock.aerialhell.Entity.AI.GhastLike;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

import java.util.EnumSet;

/* Same as net.minecraft.world.entity.monster.Ghast.GhastLookGoal but changed GhastEntity to MobEntity */
public class FlyingLookAroundGoal extends Goal
{
    private final MobEntity parentEntity;

    public FlyingLookAroundGoal(MobEntity flyingMob)
    {
        this.parentEntity = flyingMob;
        this.setControls(EnumSet.of(Control.LOOK));
    }

    public MobEntity getParentEntity() {return parentEntity;}

    @Override public boolean canStart() {return true;}

    @Override public void tick()
    {
        if (this.parentEntity.getTarget() == null)
        {
            Vec3d vec = this.parentEntity.getVelocity();
            this.parentEntity.setYaw(-((float) MathHelper.atan2(vec.x, vec.z)) * (180F / (float) Math.PI));
            this.parentEntity.bodyYaw = this.parentEntity.getYaw();
        }
        else
        {
            LivingEntity livingentity = this.parentEntity.getTarget();
            if (livingentity.squaredDistanceTo(this.parentEntity) < 64 * 64)
            {
                double x = livingentity.getX() - this.parentEntity.getX();
                double z = livingentity.getZ() - this.parentEntity.getZ();
                this.parentEntity.setYaw(-((float) MathHelper.atan2(x, z)) * (180F / (float) Math.PI));
                this.parentEntity.bodyYaw = this.parentEntity.getYaw();
            }
        }
    }
}
