package fr.factionbedrock.aerialhell.Config;

public class LoadedConfigParams
{
    public static float SHADOW_SPREAD_SPEED_MULTIPLIER;
    public static boolean ENABLE_SHADOW_BIND_RELOAD_TEXTURE;
    public static boolean ENABLE_SHADOW_BIND_TEXTURE_SHIFT;
    public static boolean DO_BOSS_GRIEFING;
    public static int OVERWORLD_ABANDONNED_PORTAL_SPACING_OVERRIDE;

    public static void loadConfigParams(AerialHellConfig config)
    {
        SHADOW_SPREAD_SPEED_MULTIPLIER = Math.max(config.shadowSpreadSpeedMultiplier, 0.0F);
        ENABLE_SHADOW_BIND_RELOAD_TEXTURE = config.enableShadowBindReloadTexture;
        ENABLE_SHADOW_BIND_TEXTURE_SHIFT = config.enableShadowBindTextureShift;
        DO_BOSS_GRIEFING = config.doBossGriefing;
        OVERWORLD_ABANDONNED_PORTAL_SPACING_OVERRIDE = Math.clamp(config.overworldAbandonnedPortalSpacingOverride, 9, 4096);
    }
}
