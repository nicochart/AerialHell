package fr.factionbedrock.aerialhell.Entity.AI;

import fr.factionbedrock.aerialhell.Entity.Passive.KodamaEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import net.minecraft.world.entity.ai.goal.Goal;

public class KodamaRattleGoal extends Goal
{
    private final KodamaEntity goalOwner;
    private int rattleTimer;
    private int rattlingCount;

    public KodamaRattleGoal(KodamaEntity entity) {this.goalOwner = entity;}

    @Override public boolean canUse() {return goalOwner.level().getDayTime() % 24000 > 12000;}
    @Override public void start() {this.resetTask();}
    @Override public void stop() {this.resetTask();}
    @Override public boolean requiresUpdateEveryTick() {return true;}

    @Override public void tick()
    {
        if (!this.goalOwner.isRattling())
        {
            if (this.shouldStartRattling())
            {
                this.startRattling();
                this.goalOwner.playRattleSound();
            }
            this.rattleTimer--;
        }
        else
        {
            this.increaseRattlingTimer(this.rattlingCount > 0 ? 2 : 1);
            if (this.getRattlingTimer() > getRattlingTimerTargetValue())
            {
                if (this.rattlingCount < this.getRattlingCountTargetValue())
                {
                    this.rattlingCount++; decreaseRattlingTimer(this.getRattlingTimerDecreaseOnCountIncrease());
                }
                else {this.resetTask();}
            }
        }
    }

    protected boolean shouldStartRattling() {return this.rattleTimer <= 0;}
    protected void startRattling() {this.goalOwner.setRattling(true);}
    protected void resetRattleTimer() {this.rattleTimer += 100 + this.goalOwner.getRandom().nextInt(100);} //500 500
    protected int getRattlingCountTargetValue() {return 5;}
    protected int getRattlingTimerDecreaseOnCountIncrease() {return getRattlingTimerTargetValue() / 4;}
    protected int getRattlingTimerTargetValue() {return this.goalOwner.getMaxRattlingTiltAngle();}
    protected int getRattlingTimer() {return this.goalOwner.getRattlingTiltAngle();}
    protected void increaseRattlingTimer(int n) {this.goalOwner.setRattlingTiltAngle(this.goalOwner.getRattlingTiltAngle() + n);}
    protected void decreaseRattlingTimer(int n) {this.goalOwner.setRattlingTiltAngle(this.goalOwner.getRattlingTiltAngle() - n);}
    protected void resetTask() {this.resetRattleTimer(); this.goalOwner.setRattlingTiltAngle(0); this.rattlingCount = 0; this.goalOwner.setRattling(false);}
}
