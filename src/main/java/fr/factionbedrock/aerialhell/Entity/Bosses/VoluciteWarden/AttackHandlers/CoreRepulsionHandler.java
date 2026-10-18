package fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.AttackHandlers;

import fr.factionbedrock.aerialhell.Config.LoadedConfigParams;
import fr.factionbedrock.aerialhell.Entity.AI.VoluciteWarden.VoluciteWardenCoreRepulsionGoal;
import fr.factionbedrock.aerialhell.Entity.Bosses.NearbyEntitiesInteractionInfo;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.VoluciteWardenEntity;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Util.BlockHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

//core repulsion : the core gently attracts nearby entities, then suddenly repulses them, destroying blocks too close to the core
//drag / repulse is done here (both sides), depending on CORE_DRAG_OR_REPULSE_GOAL_STATUS (set by the goal). particles are handled by the warden
public class CoreRepulsionHandler
{
    public static final int ATTRACTION_DURATION = 100;
    public static final int REPULSION_DURATION = 10;
    private static final int DRAG_OR_REPULSE_RANGE = 30;
    private static final float ATTRACTION_FACTOR = 40.0F; //adds 0.04 block/tick
    private static final float REPULSION_FACTOR = 500.0F; //adds 0.5 block/tick
    private static final float BLOCK_DESTRUCTION_RADIUS = 7.0F;
    private static final float TRIGGER_RANGE = 16.0F; //max distance between core and target to trigger

    private final VoluciteWardenEntity warden;
    private final VoluciteWardenCoreRepulsionGoal goal;
    private int inactiveTicks;
    private int cooldown = 200;

    public CoreRepulsionHandler(VoluciteWardenEntity warden, VoluciteWardenCoreRepulsionGoal goal)
    {
        this.warden = warden;
        this.goal = goal;
    }

    public void tick()
    {
        if (!this.warden.level().isClientSide())
        {
            if (this.goal.isActive()) {this.inactiveTicks = 0;}
            else
            {
                this.inactiveTicks++;
                if (this.inactiveTicks > this.cooldown && this.canTrigger() && this.goal.trigger())
                {
                    this.cooldown = this.rollCooldown();
                    this.playAttractionStartSound();
                }
            }
        }

        //both sides, using synced status (goal does not exist client side). client side drag / repulse is needed to move players (player movement is client authoritative)
        if (this.warden.isCoreAttracting()) {this.warden.dragOrRepulseEntitiesFromCore(NearbyEntitiesInteractionInfo.DRAG_UNIFORM, ATTRACTION_FACTOR, DRAG_OR_REPULSE_RANGE);}
        else if (this.warden.isCoreRepulsing()) {this.warden.dragOrRepulseEntitiesFromCore(NearbyEntitiesInteractionInfo.REPULSE_UNIFORM, REPULSION_FACTOR, DRAG_OR_REPULSE_RANGE);}
    }

    private boolean canTrigger()
    {
        return this.warden.getTarget() != null && this.warden.isInNormalPhase() && this.warden.getTarget().distanceToSqr(this.warden.getCoreCenterPos()) < TRIGGER_RANGE * TRIGGER_RANGE;
    }

    private int rollCooldown() {return 300 + this.warden.getRandom().nextInt(300);}

    //called by the goal, at the end of attraction
    public void onRepulsionStart()
    {
        Vec3 corePos = this.warden.getCoreCenterPos();
        this.destroyBlocksNearCore(corePos);
        this.warden.level().playSound(null, corePos.x, corePos.y, corePos.z, SoundEvents.GENERIC_EXPLODE.value(), this.warden.getSoundSource(), 6.0F, 0.6F);
    }

    private void playAttractionStartSound()
    {
        Vec3 corePos = this.warden.getCoreCenterPos();
        this.warden.level().playSound(null, corePos.x, corePos.y, corePos.z, SoundEvents.CONDUIT_ACTIVATE, this.warden.getSoundSource(), 6.0F, 0.6F);
    }

    private void destroyBlocksNearCore(Vec3 corePos)
    {
        if (!LoadedConfigParams.DO_BOSS_GRIEFING) {return;}

        Level level = this.warden.level();
        BlockPos coreBlockPos = BlockPos.containing(corePos);
        int radius = Mth.ceil(BLOCK_DESTRUCTION_RADIUS);
        for (BlockPos pos : BlockPos.betweenClosed(coreBlockPos.offset(-radius, -radius, -radius), coreBlockPos.offset(radius, radius, radius)))
        {
            if (pos.distToCenterSqr(corePos) > BLOCK_DESTRUCTION_RADIUS * BLOCK_DESTRUCTION_RADIUS) {continue;}
            if (this.canDestroyBlock(level, pos, level.getBlockState(pos))) {level.destroyBlock(pos, false, this.warden);}
        }
    }

    //destroys any block except unbreakable blocks
    private boolean canDestroyBlock(Level level, BlockPos pos, BlockState state)
    {
        return !state.isAir() && state.getDestroySpeed(level, pos) >= 0.0F && !BlockHelper.isCoreProtected(state); //&& !state.liquid();
    }
}
