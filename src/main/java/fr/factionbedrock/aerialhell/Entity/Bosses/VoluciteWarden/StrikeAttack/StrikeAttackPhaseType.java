package fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.StrikeAttack;

public enum StrikeAttackPhaseType
{
    //"mandatory" phase types
    //should always have at least one of those all in your sequence
    WINDUP,
    STRIKE,
    RECOVERY,

    //"optional" phase type
    //put this as last phase if you want your goal to deactivate after the full sequence.
    //after that, you will need to manually trigger the goal to restart (to first phase).
    //obviously do not put this type in first phase of sequence, nor in the middle of your sequence. manual trigger restarts to first phase.
    INACTIVE
}
