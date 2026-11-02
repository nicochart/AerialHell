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
    public static BossPhase FOURTH_TO_FIFTH_TRANSITION = new BossPhase(7);
    public static BossPhase FIFTH_PHASE = new BossPhase(8);
    public static BossPhase FIFTH_TO_SIXTH_TRANSITION = new BossPhase(9);
    public static BossPhase SIXTH_PHASE = new BossPhase(10);
    public static BossPhase SIXTH_TO_SEVENTH_TRANSITION = new BossPhase(11);
    public static BossPhase SEVENTH_PHASE = new BossPhase(12);
    public static BossPhase SEVENTH_TO_EIGHTH_TRANSITION = new BossPhase(13);
    public static BossPhase EIGHTH_PHASE = new BossPhase(14);
    public static BossPhase DYING = new BossPhase(15);
    public static BossPhase DEAD = new BossPhase(16);
    public static ImmutableList<BossPhase> PHASE_LIST = ImmutableList.of(FIRST_PHASE, FIRST_TO_SECOND_TRANSITION, SECOND_PHASE, SECOND_TO_THIRD_TRANSITION, THIRD_PHASE, THIRD_TO_FOURTH_TRANSITION, FOURTH_PHASE, FOURTH_TO_FIFTH_TRANSITION, FIFTH_PHASE, FIFTH_TO_SIXTH_TRANSITION, SIXTH_PHASE, SIXTH_TO_SEVENTH_TRANSITION, SEVENTH_PHASE, SEVENTH_TO_EIGHTH_TRANSITION, EIGHTH_PHASE, DYING, DEAD);

    public static BossPhase UNUSED = new BossPhase(-1);

    private final int phaseId;
    public BossPhase(int phaseId) {this.phaseId = phaseId;}

    public int getPhaseId() {return phaseId;}
    public int getNextPhase() {return phaseId + 1;}
}