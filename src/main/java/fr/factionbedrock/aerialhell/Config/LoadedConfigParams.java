package fr.factionbedrock.aerialhell.Config;

public class LoadedConfigParams
{
    public static float SHADOW_SPREAD_SPEED_MULTIPLIER;
    public static boolean ENABLE_SHADOW_BIND_RELOAD_TEXTURE;
    public static boolean ENABLE_SHADOW_BIND_TEXTURE_SHIFT;

    public static void loadConfigParams(AerialHellConfig config)
    {
        SHADOW_SPREAD_SPEED_MULTIPLIER = Math.max(config.ShadowSpreadSpeedMultiplier, 0.0F);
        ENABLE_SHADOW_BIND_RELOAD_TEXTURE = config.enableShadowBindReloadTexture;
        ENABLE_SHADOW_BIND_TEXTURE_SHIFT = config.enableShadowBindTextureShift;
    }
}
