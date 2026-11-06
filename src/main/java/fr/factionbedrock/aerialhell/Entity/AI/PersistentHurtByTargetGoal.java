package fr.factionbedrock.aerialhell.Entity.AI;

import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;

//vanilla HurtByTargetGoal always uses mustSee = true (final field) and forgets an unseen target after 300 ticks (set in start(), so setUnseenMemoryTicks has no effect)
//this version keeps the attacker as target when out of sight, like a target goal with mustSee = false
//the target is still lost if out of follow range or no longer attackable (see TargetGoal.canContinueToUse)
public class PersistentHurtByTargetGoal extends HurtByTargetGoal
{
    public PersistentHurtByTargetGoal(PathfinderMob mob, Class<?>... ignoreDamageFromTheseTypes) {super(mob, ignoreDamageFromTheseTypes);}

    @Override public void start()
    {
        super.start();
        this.unseenMemoryTicks = Integer.MAX_VALUE; //never forgets an unseen target
    }
}
