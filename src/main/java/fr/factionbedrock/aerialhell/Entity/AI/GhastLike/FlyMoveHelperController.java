package fr.factionbedrock.aerialhell.Entity.AI.GhastLike;

import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

/* Same as net.minecraft.world.entity.monster.Ghast.GhastMoveControl but changed Ghast to MobEntity */
public class FlyMoveHelperController extends MoveControl
{
    private final MobEntity parentEntity;
    private int courseChangeCooldown;

    public FlyMoveHelperController(MobEntity flyingMob)
    {
        super(flyingMob);
        this.parentEntity = flyingMob;
    }

    @Override public void tick()
    {
        if (this.state == State.MOVE_TO)
        {
            if (this.courseChangeCooldown-- <= 0)
            {
                this.courseChangeCooldown += this.parentEntity.getRandom().nextInt(5) + 2;
                Vec3d vec = new Vec3d(this.targetX - this.parentEntity.getX(), this.targetY - this.parentEntity.getY(), this.targetZ - this.parentEntity.getZ());
                double d0 = vec.length();
                vec = vec.normalize();
                if (this.canReach(vec, MathHelper.ceil(d0))) {this.parentEntity.setVelocity(this.parentEntity.getVelocity().add(vec.multiply(0.1)));}
                else {this.state = State.WAIT;}
            }
        }
    }

    private boolean canReach(Vec3d pos, int distance)
    {
        Box boundingBox = this.parentEntity.getBoundingBox();

        for (int i = 1; i < distance; ++i)
        {
            boundingBox = boundingBox.offset(pos);
            if (!this.parentEntity.getEntityWorld().isSpaceEmpty(this.parentEntity, boundingBox)) {return false;}
        }
        return true;
    }
}
