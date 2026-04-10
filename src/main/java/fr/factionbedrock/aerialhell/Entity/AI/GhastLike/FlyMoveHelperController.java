package fr.factionbedrock.aerialhell.Entity.AI.GhastLike;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

/* Same as net.minecraft.world.entity.monster.Ghast.GhastMoveControl but changed Ghast to MobEntity */
public class FlyMoveHelperController extends MoveControl
{
    private final Mob parentEntity;
    private int courseChangeCooldown;

    public FlyMoveHelperController(Mob flyingMob)
    {
        super(flyingMob);
        this.parentEntity = flyingMob;
    }

    @Override public void tick()
    {
        if (this.operation == Operation.MOVE_TO)
        {
            if (this.courseChangeCooldown-- <= 0)
            {
                this.courseChangeCooldown += this.parentEntity.getRandom().nextInt(5) + 2;
                Vec3 vec = new Vec3(this.wantedX - this.parentEntity.getX(), this.wantedY - this.parentEntity.getY(), this.wantedZ - this.parentEntity.getZ());
                double d0 = vec.length();
                vec = vec.normalize();
                if (this.canReach(vec, Mth.ceil(d0))) {this.parentEntity.setDeltaMovement(this.parentEntity.getDeltaMovement().add(vec.scale(0.1)));}
                else {this.operation = Operation.WAIT;}
            }
        }
    }

    private boolean canReach(Vec3 pos, int distance)
    {
        AABB boundingBox = this.parentEntity.getBoundingBox();

        for (int i = 1; i < distance; ++i)
        {
            boundingBox = boundingBox.move(pos);
            if (!this.parentEntity.level().noCollision(this.parentEntity, boundingBox)) {return false;}
        }
        return true;
    }
}
