package fr.factionbedrock.aerialhell.Entity.Bosses;

enum NearbyEntitiesInteractionInfo
{
    NONE(InteractionType.NONE, InteractionFalloff.UNIFORM),
    DRAG_UNIFORM(InteractionType.DRAG, InteractionFalloff.UNIFORM),
    DRAG_NEAR(InteractionType.DRAG, InteractionFalloff.INCREASES_NEAR),
    DRAG_FAR(InteractionType.DRAG, InteractionFalloff.DECREASES_NEAR),
    REPULSE_UNIFORM(InteractionType.REPULSE, InteractionFalloff.UNIFORM),
    REPULSE_NEAR(InteractionType.REPULSE, InteractionFalloff.INCREASES_NEAR),
    REPULSE_FAR(InteractionType.REPULSE, InteractionFalloff.DECREASES_NEAR);

    private final InteractionType type;
    private final InteractionFalloff falloff;

    enum InteractionType
    {
        NONE, DRAG, REPULSE;
        public boolean isDrag() {return this == DRAG;}
    }

    enum InteractionFalloff
    {
        UNIFORM, INCREASES_NEAR, DECREASES_NEAR;
        public boolean isUniform() {return this == UNIFORM;}
        public boolean increasesNear() {return this == INCREASES_NEAR;}
        public boolean decreasesNear() {return this == DECREASES_NEAR;}
    }

    NearbyEntitiesInteractionInfo(InteractionType type, InteractionFalloff falloff)
    {
        this.type = type;
        this.falloff = falloff;
    }

    public boolean noInteraction() {return this == NONE;}
    public InteractionType getType() {return type;}
    public InteractionFalloff getFalloff() {return falloff;}
}
