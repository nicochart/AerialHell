package fr.factionbedrock.aerialhell.Entity.AI;

import com.google.common.collect.ImmutableList;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public abstract class SummonThreeEntitiesGoal extends SummonEntitiesGoal
{
    public SummonThreeEntitiesGoal(Mob entity, double yMotionForEntitiesSummon) {super(entity, yMotionForEntitiesSummon);}

    @Override protected void summonEntities()
    {
        for (Vec3 vector : getSpawnMotionVec3s())
        {
            Entity entity = createEntity();
            this.setEntityPosToSummonPos(entity); entity.setDeltaMovement(vector);
            this.getGoalOwner().level().addFreshEntity(entity);
        }
    }

    private List<Vec3> getSpawnMotionVec3s()
    {
        return ImmutableList.of(new Vec3(0.5D, yMotion, 0.0D), new Vec3(-0.2500001125833550D, yMotion, 0.4333882291756956D), new Vec3(-0.250000112583355D, yMotion, -0.4333882291756956D));
    }
}
