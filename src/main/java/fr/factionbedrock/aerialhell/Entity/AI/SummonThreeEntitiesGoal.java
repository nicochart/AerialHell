package fr.factionbedrock.aerialhell.Entity.AI;

import com.google.common.collect.ImmutableList;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.math.Vec3d;

import java.util.List;

public abstract class SummonThreeEntitiesGoal extends SummonEntitiesGoal
{
    public SummonThreeEntitiesGoal(MobEntity entity, double yMotionForEntitiesSummon) {super(entity, yMotionForEntitiesSummon);}

    @Override protected void summonEntities()
    {
        for (Vec3d vector : getSpawnMotionVec3s())
        {
            Entity entity = createEntity();
            this.setEntityPosToSummonPos(entity); entity.setVelocity(vector);
            this.getGoalOwner().getEntityWorld().spawnEntity(entity);
        }
    }

    private List<Vec3d> getSpawnMotionVec3s()
    {
        return ImmutableList.of(new Vec3d(0.5D, yMotion, 0.0D), new Vec3d(-0.2500001125833550D, yMotion, 0.4333882291756956D), new Vec3d(-0.250000112583355D, yMotion, -0.4333882291756956D));
    }
}
