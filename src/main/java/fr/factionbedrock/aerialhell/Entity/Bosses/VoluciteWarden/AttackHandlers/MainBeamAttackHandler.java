package fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.AttackHandlers;

import fr.factionbedrock.aerialhell.Entity.AI.VoluciteWarden.VoluciteWardenMainBeamAttackGoal;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.VoluciteWardenEntity;
import fr.factionbedrock.aerialhell.Entity.MultipartEntity.PartInfo;

import java.util.function.Supplier;

public class MainBeamAttackHandler
{
    public final VoluciteWardenEntity warden;
    public final VoluciteWardenMainBeamAttackGoal goal;
    public final Supplier<PartInfo> headInfo;
    private int inactiveTicks;
    private int cooldown = 40;

    public MainBeamAttackHandler(VoluciteWardenEntity warden, Supplier<PartInfo> headInfo, VoluciteWardenMainBeamAttackGoal goal)
    {
        this.warden = warden;
        this.goal = goal;
        this.headInfo = headInfo;
    }

    public void tick()
    {
        if (this.warden.tickCount % 200 == 0) {this.cooldown = this.warden.getRandom().nextInt(200);}

        if (!this.warden.level().isClientSide())
        {
            boolean isBeaming = this.goal.isActive();

            if (isBeaming) {this.inactiveTicks = 0;}
            else
            {
                this.inactiveTicks++;
                if (this.inactiveTicks > this.cooldown && this.canTrigger())
                {
                    this.goal.trigger();
                }
            }
        }
    }

    private boolean canTrigger()
    {
        return this.warden.getTarget() != null; //TODO && !this.warden.isInPhaseThatAuthorizeMainBeam();
    }
}