package fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.AttackHandlers;

import fr.factionbedrock.aerialhell.Config.LoadedConfigParams;
import fr.factionbedrock.aerialhell.Entity.AI.VoluciteWarden.VoluciteWardenCoreRepulsionGoal;
import fr.factionbedrock.aerialhell.Entity.Bosses.NearbyEntitiesInteractionInfo;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.VoluciteWardenEntity;
import fr.factionbedrock.aerialhell.Entity.Util.DisplayParticlesHelper;
import fr.factionbedrock.aerialhell.Entity.Util.VoluciteGlyphParticles;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Util.BlockHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

//core repulsion (shield phases only) : the core gently attracts nearby entities, then suddenly repulses them, destroying blocks too close to the core
//drag / repulse is done here (both sides), depending on CORE_DRAG_OR_REPULSE_GOAL_STATUS (set by the goal)
public class CoreRepulsionHandler
{
    public static final int ATTRACTION_DURATION = 100;
    public static final int REPULSION_DURATION = 10;
    private static final int DRAG_OR_REPULSE_RANGE = 30;
    private static final float ATTRACTION_FACTOR = 40.0F; //adds 0.04 block/tick
    private static final float REPULSION_FACTOR = 500.0F; //adds 0.5 block/tick
    private static final float ATTRACTION_GLYPHS_MIN_RADIUS = 6.0F, ATTRACTION_GLYPHS_MAX_RADIUS = 16.0F; //glyph particles toward the core
    private static final int ATTRACTION_GLYPHS_PER_TICK = 8;
    private static final DisplayParticlesHelper REPULSION_EXPLOSION_PARTICLES = DisplayParticlesHelper.fromSource(ParticleTypes.EXPLOSION, 1, 2.0F, 0.0F, 0.0F);
    private static final DisplayParticlesHelper REPULSION_CLOUD_PARTICLES = DisplayParticlesHelper.fromSource(ParticleTypes.CLOUD, 20, 2.0F, 1.0F, 2.0F);
    private static final float REPULSION_GLYPHS_MIN_DISTANCE = 7.0F, REPULSION_GLYPHS_MAX_DISTANCE = 14.0F; //glyph particles from the core
    private static final int REPULSION_GLYPHS_PER_TICK = 12;
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
            if (this.goal.isActive())
            {
                this.inactiveTicks = 0;
                if (!this.warden.isInShieldPhase()) {this.goal.interrupt();} //e.g. shield broken during attraction
            }
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
        if (this.warden.isCoreAttracting()) {this.warden.dragOrRepulseEntitiesFromCore(NearbyEntitiesInteractionInfo.DRAG_UNIFORM, ATTRACTION_FACTOR, DRAG_OR_REPULSE_RANGE); this.spawnGlyphParticles(true);}
        else if (this.warden.isCoreRepulsing()) {this.warden.dragOrRepulseEntitiesFromCore(NearbyEntitiesInteractionInfo.REPULSE_UNIFORM, REPULSION_FACTOR, DRAG_OR_REPULSE_RANGE, REPULSION_EXPLOSION_PARTICLES, REPULSION_CLOUD_PARTICLES); this.spawnGlyphParticles(false);}
    }

    private void spawnGlyphParticles(boolean attracting) //client side
    {
        if (!this.warden.level().isClientSide()) {return;}
        if (attracting) {VoluciteGlyphParticles.spawnInward(this.warden.level(), this.warden.getCoreLightningStartPos(), ATTRACTION_GLYPHS_MIN_RADIUS, ATTRACTION_GLYPHS_MAX_RADIUS, ATTRACTION_GLYPHS_PER_TICK);}
        else {VoluciteGlyphParticles.spawnOutward(this.warden.level(), this.warden.getCoreLightningStartPos(), REPULSION_GLYPHS_MIN_DISTANCE, REPULSION_GLYPHS_MAX_DISTANCE, REPULSION_GLYPHS_PER_TICK);}
    }

    private boolean canTrigger()
    {
        return this.warden.getTarget() != null && this.warden.isInShieldPhase() && this.warden.getTarget().distanceToSqr(this.warden.getCoreCenterPos()) < TRIGGER_RANGE * TRIGGER_RANGE;
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
