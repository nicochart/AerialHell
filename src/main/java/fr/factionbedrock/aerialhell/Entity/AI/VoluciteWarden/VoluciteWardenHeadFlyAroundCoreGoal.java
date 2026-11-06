package fr.factionbedrock.aerialhell.Entity.AI.VoluciteWarden;

import fr.factionbedrock.aerialhell.Entity.AI.GhastLike.RandomFlyGoal;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.VoluciteWardenEntity;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.VoluciteWardenHeadEntity;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.phys.Vec3;

//final phase : the semi detached head flies randomly around the core (instead of around itself), like a ghast
//obstacles are handled by the head move control (FlyMoveHelperController) : if the way to the chosen position is blocked, the head stops and a new position is chosen
public class VoluciteWardenHeadFlyAroundCoreGoal extends RandomFlyGoal
{
    private static final float HORIZONTAL_RANGE = 14.0F; //max offset (x and z) of the chosen position, around the core
    private static final float MIN_RELATIVE_HEIGHT = -4.0F, MAX_RELATIVE_HEIGHT = 10.0F; //head position (bottom of the head) relative to the core center
    private static final int MAX_TICKS_PER_DESTINATION = 100; //a new position is chosen if the head did not reach the previous one in time (the move control ignores entity hitboxes, like the lower chest)

    private final VoluciteWardenHeadEntity head;
    private int lastChoiceTick;

    public VoluciteWardenHeadFlyAroundCoreGoal(VoluciteWardenHeadEntity head)
    {
        super(head);
        this.head = head;
    }

    @Override public boolean canUse() {return this.head.canFlyAroundCore() && (super.canUse() || this.head.tickCount - this.lastChoiceTick > MAX_TICKS_PER_DESTINATION);}

    @Override public void start()
    {
        if (!(this.head.getMaster() != null && this.head.getMaster().getSelf() instanceof VoluciteWardenEntity warden)) {return;}

        RandomSource random = this.head.getRandom();
        Vec3 coreCenter = warden.getCoreCenterPos();
        double x = coreCenter.x + Mth.nextFloat(random, -HORIZONTAL_RANGE, HORIZONTAL_RANGE);
        double y = coreCenter.y + Mth.nextFloat(random, MIN_RELATIVE_HEIGHT, MAX_RELATIVE_HEIGHT);
        double z = coreCenter.z + Mth.nextFloat(random, -HORIZONTAL_RANGE, HORIZONTAL_RANGE);
        this.head.getMoveControl().setWantedPosition(x, y, z, 1.0D);
        this.lastChoiceTick = this.head.tickCount;
    }
}
