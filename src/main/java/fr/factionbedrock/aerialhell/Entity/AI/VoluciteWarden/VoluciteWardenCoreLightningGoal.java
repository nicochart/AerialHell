package fr.factionbedrock.aerialhell.Entity.AI.VoluciteWarden;

import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.VoluciteWardenEntity;
import fr.factionbedrock.aerialhell.Entity.Util.VoluciteLightningHelper;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.Nullable;

//core lightning : the core strikes lightning toward players around it, in any direction. a target is needed (no random lightning)
//shield phases : very frequent lightning, independently of the core repulsion. final phase : occasional lightning
public class VoluciteWardenCoreLightningGoal extends Goal
{
    private static final float SHIELD_PHASE_LIGHTNING_DAMAGE = 15.0F;
    private static final float FINAL_PHASE_LIGHTNING_DAMAGE = 10.0F;
    private static final float LIGHTNING_HIT_RADIUS = 1.5F; //entities this close to the lightning impact are hurt
    private static final float TARGET_RANGE = 12.0F; //players in this radius around the core center can be targeted
    private static final int SHIELD_PHASE_MIN_INTERVAL = 6, SHIELD_PHASE_MAX_INTERVAL = 10; //ticks between two lightning, during shield phases
    private static final int FINAL_PHASE_MIN_INTERVAL = 40, FINAL_PHASE_MAX_INTERVAL = 100; //ticks between two lightning, during final phase

    private final VoluciteWardenEntity warden;
    private int cooldown;

    public VoluciteWardenCoreLightningGoal(VoluciteWardenEntity warden) {this.warden = warden;}

    @Override public boolean canUse() {return this.warden.canCoreStrikeLightning();}
    @Override public boolean canContinueToUse() {return this.canUse();}

    @Override public void start() {this.cooldown = this.rollCooldown();}

    @Override public boolean requiresUpdateEveryTick() {return true;}

    @Override public void tick()
    {
        if (this.cooldown > 0) {this.cooldown--;}
        else if (this.strikeLightning()) {this.cooldown = this.rollCooldown();} //no target : trying again next tick, to strike as soon as a player gets close
    }

    private boolean strikeLightning() //returns true if a lightning is struck
    {
        if (!(this.warden.level() instanceof ServerLevel level)) {return false;}

        float damage = this.warden.isInFinalPhase() ? FINAL_PHASE_LIGHTNING_DAMAGE : SHIELD_PHASE_LIGHTNING_DAMAGE;
        VoluciteLightningHelper.LightningInfo lightningInfo = VoluciteLightningHelper.voluciteMobLightningInfo(this.warden, damage, LIGHTNING_HIT_RADIUS);
        @Nullable LivingEntity target = VoluciteLightningHelper.findRandomTargetAround(level, this.warden.getCoreLightningStartPos(), TARGET_RANGE, entity -> entity instanceof Player && lightningInfo.canHurt().test(entity));
        return target != null && VoluciteLightningHelper.strikeLightningAtTarget(level, this.warden.getCoreLightningStartPos(), target, lightningInfo);
    }

    private int rollCooldown()
    {
        int minInterval = this.warden.isInShieldPhase() ? SHIELD_PHASE_MIN_INTERVAL : FINAL_PHASE_MIN_INTERVAL;
        int maxInterval = this.warden.isInShieldPhase() ? SHIELD_PHASE_MAX_INTERVAL : FINAL_PHASE_MAX_INTERVAL;
        return minInterval + this.warden.getRandom().nextInt(maxInterval - minInterval + 1);
    }
}
