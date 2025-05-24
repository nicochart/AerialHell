package fr.factionbedrock.aerialhell.Config;

public class AerialHellConfig
{
    public String configVersion;
    public float shadowSpreadSpeedMultiplier;
    public boolean enableShadowBindReloadTexture;
    public boolean enableShadowBindTextureShift;
    public boolean doBossGriefing;
    public int overworldAbandonnedPortalSpacingOverride;

    public AerialHellConfig()
    {
        this.configVersion = "none";
        this.shadowSpreadSpeedMultiplier = 1.0F;
        this.enableShadowBindReloadTexture = true;
        this.enableShadowBindTextureShift = true;
        this.doBossGriefing = true;
        this.overworldAbandonnedPortalSpacingOverride = 60;
    }
}
