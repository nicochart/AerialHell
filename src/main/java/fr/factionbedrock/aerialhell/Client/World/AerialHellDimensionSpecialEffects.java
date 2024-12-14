package fr.factionbedrock.aerialhell.Client.World;

import net.fabricmc.fabric.api.client.rendering.v1.DimensionRenderingRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderContext;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.DimensionEffects;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.Nullable;

public class AerialHellDimensionSpecialEffects extends DimensionEffects implements DimensionRenderingRegistry.SkyRenderer
{
    public AerialHellDimensionSpecialEffects(float cloudLevel, boolean skyEffect, DimensionEffects.SkyType skyType, boolean forceBrightLightmap, boolean hasEntityGroundLit)
    {
        super(cloudLevel, skyEffect, skyType, forceBrightLightmap, hasEntityGroundLit);
    }
    
    // Copy from DimensionEffects.Overworld
    @Override public Vec3d adjustFogColor(Vec3d color, float sunHeight)
    {
        return color.multiply((double)(sunHeight * 0.94F + 0.06F), (double)(sunHeight * 0.94F + 0.06F), (double)(sunHeight * 0.91F + 0.09F));
    }

    @Override @Nullable public float[] getFogColorOverride(float skyAngle, float tickDelta) {return null;}
    @Override public boolean useThickFog(int camX, int camY) {return false;}

    @Override
    public void render(WorldRenderContext context)
    {
        AerialHellDimensionSkyRenderer.render(context.world(), context.projectionMatrix(), context.positionMatrix(), context.tickCounter().getTickDelta(false), context.camera(), false, getSetupFog(context));
    }

    private static Runnable getSetupFog(WorldRenderContext context)
    {
        float viewDistance = context.gameRenderer().getViewDistance();
        boolean shouldThickenFog = MinecraftClient.getInstance().inGameHud.getBossBarHud().shouldThickenFog();
        float f = context.tickCounter().getTickDelta(false);
        return () -> BackgroundRenderer.applyFog(context.camera(), BackgroundRenderer.FogType.FOG_SKY, viewDistance, shouldThickenFog, f);
    }
}
