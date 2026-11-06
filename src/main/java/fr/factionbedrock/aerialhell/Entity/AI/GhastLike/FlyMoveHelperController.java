package fr.factionbedrock.aerialhell.Entity.AI.GhastLike;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.Ghast;

import java.util.function.BooleanSupplier;

/* vanilla net.minecraft.world.entity.monster.Ghast.GhastMoveControl (usable by any mob), not careful (careful = happy ghast behavior) */
//the mob flies straight to the wanted position. the way is checked against blocks (precise collision shapes, not entities. fluids avoided unless already in) : if blocked, the mob stops (Operation.WAIT)
//speed : impulse of Attributes.FLYING_SPEED * 5 / 3 toward the wanted position, every 2 to 6 ticks. the mob needs the FLYING_SPEED attribute (0.06 for vanilla ghast)
public class FlyMoveHelperController extends Ghast.GhastMoveControl<Mob>
{
    public FlyMoveHelperController(Mob flyingMob) {this(flyingMob, () -> false);}
    public FlyMoveHelperController(Mob flyingMob, BooleanSupplier shouldBeStopped) {super(flyingMob, false, shouldBeStopped);} //the mob is stopped in place while shouldBeStopped returns true
}
