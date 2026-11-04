package fr.factionbedrock.aerialhell.Entity.AI.VoluciteWarden;

import fr.factionbedrock.aerialhell.Entity.AI.Phase.Phase;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.VoluciteWardenEntity;
import fr.factionbedrock.aerialhell.Entity.Util.VoluciteGlyphParticles;
import fr.factionbedrock.aerialhell.Entity.Util.VoluciteLightningHelper;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.phys.Vec3;

import java.util.List;
import java.util.function.Supplier;

public class VoluciteWardenArmLightningAttackGoal extends VoluciteWardenArmGoal
{
    private static final float LIGHTNING_DAMAGE = 5.0F;
    private static final float LIGHTNING_HIT_RADIUS = 1.5F; //entities this close to the lightning impact are hurt
    private static final float TARGET_CHANCE = 1.0F; //probability for a lightning to target an enemy below the hand (if there is one)
    private static final float TARGET_HORIZONTAL_RANGE = 12.0F; //enemies below the hand, in this horizontal radius, can be targeted
    private static final float TARGET_MAX_DEPTH = 40.0F; //max distance below the hand
    private static final float GROUND_LIGHTNING_SPREAD = 5.0F; //random horizontal offset of the lightning on the ground, when there is no target
    private static final float HAND_GLYPHS_MIN_RADIUS = 1.5F, HAND_GLYPHS_MAX_RADIUS = 4.0F; //glyph particles toward the hand, during lightning loading
    private static final int HAND_GLYPHS_PER_TICK = 4;

    public VoluciteWardenArmLightningAttackGoal(VoluciteWardenEntity entity, Supplier<List<VoluciteWardenEntity.ArmPartInfo>> arm, float distanceOffsetTolerance) {super(entity, arm, distanceOffsetTolerance);}

    @Override public List<Phase> getPhases() {return this.goalOwner.armsLightningAttackHandler.getAttackSequence(this.arm.get().getFirst().isRightArm);}

    @Override public boolean shouldTrigger() {return this.goalOwner.getTarget() != null && this.goalOwner.shouldTriggerArmLightningAttack();}

    public Vec3 getHandLevelPos() {return this.goalOwner.fromUnrotatedRelativeToLevelPos(this.getCachedUnrotatedRelativePos());}

    //lightning loading : glyph particles toward the hand
    public void spawnHandGlyphs()
    {
        if (this.goalOwner.level() instanceof ServerLevel level) {VoluciteGlyphParticles.sendInward(level, this.getHandLevelPos(), HAND_GLYPHS_MIN_RADIUS, HAND_GLYPHS_MAX_RADIUS, HAND_GLYPHS_PER_TICK);}
    }

    //lightning from the hand to the nearest enemy below the hand. if there is none, lightning on the ground below the hand
    //lightning stops at the first block on its way
    public void strikeLightning()
    {
        if (!(this.goalOwner.level() instanceof ServerLevel level)) {return;}

        VoluciteLightningHelper.LightningInfo lightningInfo = VoluciteLightningHelper.voluciteMobLightningInfo(this.goalOwner, LIGHTNING_DAMAGE, LIGHTNING_HIT_RADIUS);
        VoluciteLightningHelper.strikeLightningBelow(level, this.getHandLevelPos(), TARGET_CHANCE, lightningInfo.canHurt(), TARGET_HORIZONTAL_RANGE, GROUND_LIGHTNING_SPREAD, TARGET_MAX_DEPTH, true, lightningInfo);
    }
}
