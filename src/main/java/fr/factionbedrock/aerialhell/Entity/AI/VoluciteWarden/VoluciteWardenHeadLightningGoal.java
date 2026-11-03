package fr.factionbedrock.aerialhell.Entity.AI.VoluciteWarden;

import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.AttackHandlers.ArmsLightningAttackHandler;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.VoluciteWardenHeadEntity;
import fr.factionbedrock.aerialhell.Entity.Util.VoluciteLightningHelper;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

//final phase : the semi detached head strikes lightning below itself : some of them target a player (if there is one), the others are random in a zone around the head
public class VoluciteWardenHeadLightningGoal extends Goal
{
    private static final float LIGHTNING_DAMAGE = 5.0F;
    private static final float LIGHTNING_HIT_RADIUS = 1.5F; //entities this close to the lightning impact are hurt
    private static final float START_POS_SPREAD = 1.5F; //max offset (x and z) of the lightning start pos, around the bottom of the head
    private static final float LIGHTNING_RATE =0.75F / ArmsLightningAttackHandler.LIGHTNING_INTERVAL; //lightning per tick : 0.75 * arm lightning rate (during arm lightning attack)
    private static final float TARGET_CHANCE = 0.5F; //probability for a lightning to target a player below the head (if there is one)
    private static final float TARGET_HORIZONTAL_RANGE = 16.0F; //players below the head, in this horizontal radius, can be targeted
    private static final float RANDOM_LIGHTNING_SPREAD = 10.0F; //max horizontal offset (x and z) of random lightning, around the head
    private static final float LIGHTNING_MAX_DEPTH = 40.0F; //max distance below the head (random lightning ends in the air if there is no ground)

    private final VoluciteWardenHeadEntity head;
    private float lightningProgress; //a lightning is struck each time it reaches 1

    public VoluciteWardenHeadLightningGoal(VoluciteWardenHeadEntity head) {this.head = head;}

    @Override public boolean canUse() {return this.head.canStrikeLightning();}
    @Override public boolean canContinueToUse() {return this.canUse();}

    @Override public void start() {this.lightningProgress = 0.0F;}

    @Override public boolean requiresUpdateEveryTick() {return true;}

    @Override public void tick()
    {
        this.lightningProgress += LIGHTNING_RATE;
        while (this.lightningProgress >= 1.0F)
        {
            this.strikeLightning();
            this.lightningProgress -= 1.0F;
        }
    }

    private void strikeLightning()
    {
        if (!(this.head.level() instanceof ServerLevel level)) {return;}

        VoluciteLightningHelper.LightningInfo lightningInfo = VoluciteLightningHelper.voluciteMobLightningInfo(this.head, LIGHTNING_DAMAGE, LIGHTNING_HIT_RADIUS);
        VoluciteLightningHelper.strikeLightningBelow(level, this.getRandomStartPos(), TARGET_CHANCE, target -> target instanceof Player && lightningInfo.canHurt().test(target), TARGET_HORIZONTAL_RANGE, RANDOM_LIGHTNING_SPREAD, LIGHTNING_MAX_DEPTH, false, lightningInfo);
    }

    //bottom of the head, with a random offset (up to START_POS_SPREAD on x and z)
    private Vec3 getRandomStartPos()
    {
        RandomSource random = this.head.getRandom();
        return this.head.position().add(Mth.nextFloat(random, -START_POS_SPREAD, START_POS_SPREAD), 0.0F, Mth.nextFloat(random, -START_POS_SPREAD, START_POS_SPREAD));
    }
}
