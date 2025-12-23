package fr.factionbedrock.aerialhell.Client.World;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.state.LevelRenderState;
import net.minecraft.client.renderer.state.SkyRenderState;
import net.minecraft.util.ARGB;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;

public class AerialHellDimensionSpecialEffects extends DimensionSpecialEffects
{
    public final AerialHellDimensionSkyRenderer skyRenderer = new AerialHellDimensionSkyRenderer();

    public AerialHellDimensionSpecialEffects(DimensionSpecialEffects.SkyType skyType, boolean forceBrightLightmap, boolean hasEntityGroundLit)
    {
        super(skyType, forceBrightLightmap, hasEntityGroundLit);
    }
    
    // Copy from DimensionSpecialEffects.Overworld
    @Override public Vec3 getBrightnessDependentFogColor(Vec3 biomeFogColor, float daylight)
    {
        return biomeFogColor.multiply(daylight * 0.94F + 0.06F, daylight * 0.94F + 0.06F, daylight * 0.91F + 0.09F);
    }

    @Override public boolean renderSky(LevelRenderState levelRenderState, SkyRenderState skyRenderState, Matrix4f modelViewMatrix, Runnable setupFog)
    {
        this.render(levelRenderState, skyRenderState, setupFog);
        return true;
    }

    //copy of net.minecraft.client.renderer.LevelRenderer addSkyPass method part about overworld
    private void render(LevelRenderState levelRenderState, SkyRenderState skyRenderState, Runnable setupFog)
    {
        setupFog.run();
        PoseStack posestack = new PoseStack();
        float red = ARGB.redFloat(skyRenderState.skyColor);
        float green = ARGB.greenFloat(skyRenderState.skyColor);
        float blue = ARGB.blueFloat(skyRenderState.skyColor);
        this.skyRenderer.renderSkyDisc(red, green, blue);
        if (skyRenderState.isSunriseOrSunset)
        {
            this.skyRenderer.renderSunriseAndSunset(posestack, skyRenderState.sunAngle, skyRenderState.sunriseAndSunsetColor);
        }

        this.skyRenderer.renderSunMoonAndStars(posestack, skyRenderState.timeOfDay, skyRenderState.moonPhase, skyRenderState.rainBrightness, skyRenderState.starBrightness);
        if (skyRenderState.shouldRenderDarkDisc) {this.skyRenderer.renderDarkDisc();}
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
