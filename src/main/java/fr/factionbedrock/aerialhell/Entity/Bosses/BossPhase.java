package fr.factionbedrock.aerialhell.Entity.Bosses;

import com.google.common.collect.ImmutableList;

public class BossPhase
{
    public static BossPhase FIRST_PHASE = new BossPhase(0);
    public static BossPhase FIRST_TO_SECOND_TRANSITION = new BossPhase(1);
    public static BossPhase SECOND_PHASE = new BossPhase(2);
    public static BossPhase SECOND_TO_THIRD_TRANSITION = new BossPhase(3);
    public static BossPhase THIRD_PHASE = new BossPhase(4);
    public static BossPhase THIRD_TO_FOURTH_TRANSITION = new BossPhase(5);
    public static BossPhase FOURTH_PHASE = new BossPhase(6);
    public static BossPhase DYING = new BossPhase(7);
    public static BossPhase DEAD = new BossPhase(8);
    public static ImmutableList<BossPhase> PHASE_LIST = ImmutableList.of(FIRST_PHASE, FIRST_TO_SECOND_TRANSITION, SECOND_PHASE, SECOND_TO_THIRD_TRANSITION, THIRD_PHASE, THIRD_TO_FOURTH_TRANSITION, FOURTH_PHASE, DYING, DEAD);

    public static BossPhase UNUSED = new BossPhase(-1);

    private final int phaseId;
    public BossPhase(int phaseId) {this.phaseId = phaseId;}

    public int getPhaseId() {return phaseId;}
    public int getNextPhase() {return phaseId + 1;}
}