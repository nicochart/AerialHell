package fr.factionbedrock.aerialhell.Client.World;

import net.fabricmc.fabric.api.client.rendering.v1.DimensionRenderingRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderContext;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class AerialHellDimensionSpecialEffects extends DimensionSpecialEffects implements DimensionRenderingRegistry.SkyRenderer
{
    public AerialHellDimensionSpecialEffects(float cloudLevel, boolean skyEffect, DimensionSpecialEffects.SkyType skyType, boolean forceBrightLightmap, boolean hasEntityGroundLit)
    {
        super(cloudLevel, skyEffect, skyType, forceBrightLightmap, hasEntityGroundLit);
    }
    
    // Copy from DimensionEffects.Overworld
    @Override public Vec3 getBrightnessDependentFogColor(Vec3 color, float sunHeight)
    {
        return color.multiply((double)(sunHeight * 0.94F + 0.06F), (double)(sunHeight * 0.94F + 0.06F), (double)(sunHeight * 0.91F + 0.09F));
    }

    @Override @Nullable public float[] getSunriseColor(float skyAngle, float tickDelta) {return null;}
    @Override public boolean isFoggyAt(int camX, int camY) {return false;}

    @Override
    public void render(WorldRenderContext context)
    {
        AerialHellDimensionSkyRenderer.render(context.world(), context.positionMatrix(), context.projectionMatrix(), context.tickCounter().getGameTimeDeltaPartialTick(false), context.camera(), false, getSetupFog(context));
    }

    private static Runnable getSetupFog(WorldRenderContext context)
    {
        float viewDistance = context.gameRenderer().getRenderDistance();
        boolean shouldThickenFog = Minecraft.getInstance().gui.getBossOverlay().shouldCreateWorldFog();
        float f = context.tickCounter().getGameTimeDeltaPartialTick(false);
        return () -> FogRenderer.setupFog(context.camera(), FogRenderer.FogMode.FOG_SKY, viewDistance, shouldThickenFog, f);
    }

    public static class AerialHellCloudRenderer implements DimensionRenderingRegistry.CloudRenderer
    {
        @Override public void render(WorldRenderContext context) {}
    }
}
