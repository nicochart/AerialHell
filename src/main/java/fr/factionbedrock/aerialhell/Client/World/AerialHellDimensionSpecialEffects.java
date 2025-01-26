package fr.factionbedrock.aerialhell.Client.World;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.*;
import net.minecraft.util.ARGB;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;
import org.joml.Vector4f;

public class AerialHellDimensionSpecialEffects extends DimensionSpecialEffects
{
    public final AerialHellDimensionSkyRenderer skyRenderer = new AerialHellDimensionSkyRenderer();

    public AerialHellDimensionSpecialEffects(float cloudLevel, boolean skyEffect, DimensionSpecialEffects.SkyType skyType, boolean forceBrightLightmap, boolean hasEntityGroundLit)
    {
        super(cloudLevel, skyEffect, skyType, forceBrightLightmap, hasEntityGroundLit);
    }
    
    // Copy from DimensionSpecialEffects.Overworld
    @Override public Vec3 getBrightnessDependentFogColor(Vec3 biomeFogColor, float daylight)
    {
        return biomeFogColor.multiply(daylight * 0.94F + 0.06F, daylight * 0.94F + 0.06F, daylight * 0.91F + 0.09F);
    }

    @Override
    public boolean renderSky(ClientLevel level, int ticks, float partialTick, Matrix4f projectionMatrix, Camera camera, Matrix4f modelViewMatrix, Runnable setupFog)
    {
        //this.render(level, partialTick, camera, setupFog);
        return true;
    }

    //copy of net.minecraft.client.renderer.LevelRenderer addSkyPass method part about overworld
    private void render(ClientLevel level, float partialTicks, Camera camera, Runnable setupFog)
    {
        setupFog.run();
        DimensionSpecialEffects dimSpecialEffects = level.effects();
        GameRenderer gameRenderer = Minecraft.getInstance().gameRenderer;
        Vec3 vec3 = camera.getPosition();
        double cameraPositionX = vec3.x(), cameraPositionY = vec3.y();
        float renderDistance = gameRenderer.getRenderDistance();
        boolean shouldRenderCloseFog = level.effects().isFoggyAt(Mth.floor(cameraPositionX), Mth.floor(cameraPositionY)) || Minecraft.getInstance().gui.getBossOverlay().shouldCreateWorldFog();
        Vector4f vector4f = FogRenderer.computeFogColor(camera, partialTicks, level, Minecraft.getInstance().options.getEffectiveRenderDistance(), gameRenderer.getDarkenWorldAmount(partialTicks));
        FogParameters fogParams = FogRenderer.setupFog(camera, FogRenderer.FogMode.FOG_SKY, vector4f, renderDistance, shouldRenderCloseFog, partialTicks);
        RenderSystem.setShaderFog(fogParams);

        PoseStack posestack = new PoseStack();
        float sunAngle = level.getSunAngle(partialTicks);
        float timeOfDay = level.getTimeOfDay(partialTicks);
        float moonAlpha = Math.min(level.getStarBrightness(partialTicks) * 2, 1.0F); //Moon brightness = 0.0F during the day, 1.0F during the night. Using / 0.5F and "min" because StarBrightness is never 1.0F (never above 0.6F) apparently
        float sunAlpha = 1.0F - moonAlpha; //Sun brightness = 1.0F during the day, 0.0F during the night
        float starAlpha = level.getStarBrightness(partialTicks); //no Rain effect
        int sunriseOrSunsetColor = dimSpecialEffects.getSunriseOrSunsetColor(timeOfDay);
        int moonPhase = level.getMoonPhase();
        int skyColor = level.getSkyColor(camera.getPosition(), partialTicks);
        float r = ARGB.redFloat(skyColor), g = ARGB.greenFloat(skyColor), b = ARGB.blueFloat(skyColor);
        this.skyRenderer.renderSkyDisc(r, g, b);
        MultiBufferSource.BufferSource bufferSource = Minecraft.getInstance().renderBuffers().bufferSource();
        if (dimSpecialEffects.isSunriseOrSunset(timeOfDay))
        {
            this.skyRenderer.renderSunriseAndSunset(posestack, bufferSource, sunAngle, sunriseOrSunsetColor);
        }
        this.skyRenderer.renderSunMoonAndStars(posestack, bufferSource, timeOfDay, moonPhase, sunAlpha, moonAlpha, starAlpha, fogParams);
        bufferSource.endBatch();
        if (this.shouldRenderDarkDisc(partialTicks, level))
        {
            this.skyRenderer.renderDarkDisc(posestack);
        }
    }

    //edited copy of net.minecraft.client.renderer.LevelRenderer method of same name
    private boolean shouldRenderDarkDisc(float partialTick, ClientLevel level)
    {
        if (Minecraft.getInstance().player != null)
        {
            return Minecraft.getInstance().player.getEyePosition(partialTick).y - level.getLevelData().getHorizonHeight(level) < 0.0;
        }
        return false;
    }

    @Override public int getSunriseOrSunsetColor(float daycycle) {return 0;}
    @Override public boolean isFoggyAt(int x, int y) {return false;}
}
