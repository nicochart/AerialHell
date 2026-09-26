package fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.AttackHandlers.ArmBeamAttack;

import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.VoluciteWardenArmSegmentEntity;

public class TargetAssignment
{
    private VoluciteWardenArmSegmentEntity rightSegment = null;
    private VoluciteWardenArmSegmentEntity leftSegment = null;

    public boolean canAccept(boolean isRightArm)
    {
        return isRightArm ? rightSegment == null : leftSegment == null;
    }

    public void assignSegment(boolean isRightArm, VoluciteWardenArmSegmentEntity segment)
    {
        if (isRightArm) this.rightSegment = segment;
        else this.leftSegment = segment;
    }

    public void removeSegment(boolean isRightArm)
    {
        if (isRightArm) this.rightSegment = null;
        else this.leftSegment = null;
    }

    public boolean isEmpty()
    {
        return rightSegment == null && leftSegment == null;
    }

    public VoluciteWardenArmSegmentEntity getRightSegment() {return rightSegment;}
    public VoluciteWardenArmSegmentEntity getLeftSegment() {return leftSegment;}
}
