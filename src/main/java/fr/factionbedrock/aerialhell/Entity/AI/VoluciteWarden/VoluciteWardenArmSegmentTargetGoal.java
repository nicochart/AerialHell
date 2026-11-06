package fr.factionbedrock.aerialhell.Entity.AI.VoluciteWarden;

import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.VoluciteWardenArmSegmentEntity;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;

//final phase : each semi detached arm segment looks for its own target (nearest player in TARGET_RANGE), independently of the warden target
//mustSee false : the target is kept when out of sight, until it leaves TARGET_RANGE (acquisition still needs a line of sight)
//not used in other phases : attached arm segments targets are set by the arm beam attack (see SegmentBeamTargetManager)
public class VoluciteWardenArmSegmentTargetGoal extends NearestAttackableTargetGoal<Player>
{
    public static final double TARGET_RANGE = 16.0D;

    private final VoluciteWardenArmSegmentEntity armSegment;

    public VoluciteWardenArmSegmentTargetGoal(VoluciteWardenArmSegmentEntity armSegment)
    {
        super(armSegment, Player.class, false);
        this.armSegment = armSegment;
    }

    @Override protected double getFollowDistance() {return TARGET_RANGE;} //acquisition and follow range (instead of FOLLOW_RANGE attribute)

    @Override public boolean canUse() {return this.armSegment.canJumpAround() && super.canUse();}
    @Override public boolean canContinueToUse() {return this.armSegment.canJumpAround() && super.canContinueToUse();} //target reset to null when stopped (end of final phase)
}
