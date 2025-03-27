package fr.factionbedrock.aerialhell.Client.World;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.client.rendering.v1.DimensionRenderingRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderContext;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.joml.Vector4f;

//Sky Renderer is unused at the moment, see RenderSkyMixin

public class AerialHellDimensionSpecialEffects extends DimensionEffects implements DimensionRenderingRegistry.SkyRenderer
{
    public AerialHellDimensionSkyRenderer skyRenderer = null; //skyRenderer should be initialized here, but since the game crashes with exception (Tesselator not initialized). So it is now initialized once in render method.

    public AerialHellDimensionSpecialEffects(float cloudLevel, boolean skyEffect, DimensionEffects.SkyType skyType, boolean forceBrightLightmap, boolean hasEntityGroundLit)
    {
        super(cloudLevel, skyEffect, skyType, forceBrightLightmap, hasEntityGroundLit);
    }
    
    // Copy from DimensionEffects.Overworld
    @Override public Vec3d adjustFogColor(Vec3d color, float sunHeight)
    {
        return color.multiply((double)(sunHeight * 0.94F + 0.06F), (double)(sunHeight * 0.94F + 0.06F), (double)(sunHeight * 0.91F + 0.09F));
    }

    @Override public boolean useThickFog(int camX, int camY) {return false;}

    @Override
    public void render(WorldRenderContext context)
    {
        if (skyRenderer == null) {this.skyRenderer = new AerialHellDimensionSkyRenderer();}
        this.render(context.world(), context.tickCounter().getTickDelta(false), context.camera(), getSetupFog(context));
    }

    //copy of net.minecraft.client.render.WorldRenderer renderSky method part about overworld
    private void render(ClientWorld world, float partialTicks, Camera camera, Runnable setupFog)
    {
        setupFog.run();
        DimensionEffects dimSpecialEffects = world.getDimensionEffects();
        GameRenderer gameRenderer = MinecraftClient.getInstance().gameRenderer;
        Vec3d vec3 = camera.getPos();
        double cameraPositionX = vec3.getX(), cameraPositionY = vec3.getY();
        float renderDistance = gameRenderer.getViewDistance();
        boolean shouldRenderCloseFog = world.getDimensionEffects().useThickFog(MathHelper.floor(cameraPositionX), MathHelper.floor(cameraPositionY)) || MinecraftClient.getInstance().inGameHud.getBossBarHud().shouldThickenFog();
        Vector4f vector4f = BackgroundRenderer.getFogColor(camera, partialTicks, world, MinecraftClient.getInstance().options.getClampedViewDistance(), gameRenderer.getSkyDarkness(partialTicks));
        Fog fogParams = BackgroundRenderer.applyFog(camera, BackgroundRenderer.FogType.FOG_SKY, vector4f, renderDistance, shouldRenderCloseFog, partialTicks);
        RenderSystem.setShaderFog(fogParams);

        MatrixStack matrixStack = new MatrixStack();
        float sunAngle = world.getSkyAngleRadians(partialTicks);
        float timeOfDay = world.getSkyAngle(partialTicks);
        float moonAlpha = Math.min(world.getStarBrightness(partialTicks) * 2, 1.0F); //Moon brightness = 0.0F during the day, 1.0F during the night. Using / 0.5F and "min" because StarBrightness is never 1.0F (never above 0.6F) apparently
        float sunAlpha = 1.0F - moonAlpha; //Sun brightness = 1.0F during the day, 0.0F during the night
        float starAlpha = world.getStarBrightness(partialTicks); //no Rain effect
        int sunriseOrSunsetColor = dimSpecialEffects.getSkyColor(timeOfDay);
        int moonPhase = world.getMoonPhase();
        int skyColor = world.getSkyColor(camera.getPos(), partialTicks);
        float r = ColorHelper.getRedFloat(skyColor), g = ColorHelper.getGreenFloat(skyColor), b = ColorHelper.getBlueFloat(skyColor);
        this.skyRenderer.renderSkyDisc(r, g, b);
        VertexConsumerProvider.Immediate bufferSource = MinecraftClient.getInstance().getBufferBuilders().getEntityVertexConsumers();
        if (dimSpecialEffects.isSunRisingOrSetting(timeOfDay))
        {
            this.skyRenderer.renderSunriseAndSunset(matrixStack, bufferSource, sunAngle, sunriseOrSunsetColor);
        }
        this.skyRenderer.renderSunMoonAndStars(matrixStack, bufferSource, timeOfDay, moonPhase, sunAlpha, moonAlpha, starAlpha, fogParams);
        bufferSource.draw();
        if (this.shouldRenderDarkDisc(partialTicks, world))
        {
            this.skyRenderer.renderDarkDisc(matrixStack);
        }
    }

    //edited copy of net.minecraft.client.renderer.LevelRenderer method of same name
    private boolean shouldRenderDarkDisc(float partialTick, ClientWorld level)
    {
        if (MinecraftClient.getInstance().player != null)
        {
            return MinecraftClient.getInstance().player.getCameraPosVec(partialTick).y - level.getLevelProperties().getSkyDarknessHeight(level) < 0.0;
        }
        return false;
    }

    private static Runnable getSetupFog(WorldRenderContext context)
    {
        float partialTicks = context.tickCounter().getTickDelta(false);
        Vector4f vector4f = BackgroundRenderer.getFogColor(context.camera(), partialTicks, context.world(), MinecraftClient.getInstance().options.getClampedViewDistance(), MinecraftClient.getInstance().gameRenderer.getSkyDarkness(partialTicks));
        float viewDistance = context.gameRenderer().getViewDistance();
        boolean shouldThickenFog = MinecraftClient.getInstance().inGameHud.getBossBarHud().shouldThickenFog();
        float f = context.tickCounter().getTickDelta(false);
        return () -> BackgroundRenderer.applyFog(context.camera(), BackgroundRenderer.FogType.FOG_SKY, vector4f, viewDistance, shouldThickenFog, f);
    }

    public static class AerialHellCloudRenderer implements DimensionRenderingRegistry.CloudRenderer
    {
        @Override public void render(WorldRenderContext context) {}
    }
}
