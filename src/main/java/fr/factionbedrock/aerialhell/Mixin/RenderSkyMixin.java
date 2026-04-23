package fr.factionbedrock.aerialhell.Mixin;

import com.mojang.blaze3d.buffers.GpuBufferSlice;
import com.mojang.blaze3d.framegraph.FrameGraphBuilder;
import com.mojang.blaze3d.framegraph.FramePass;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import fr.factionbedrock.aerialhell.Client.World.AerialHellDimensionSkyRenderer;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellDimensions;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.SkyRenderer;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.state.level.LevelRenderState;
import net.minecraft.client.renderer.state.level.SkyRenderState;
import net.minecraft.util.ARGB;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.material.FogType;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LevelRenderer.class)
public class RenderSkyMixin
{
    @Shadow @Final public LevelRenderState levelRenderState;
    @Shadow private @Nullable SkyRenderer skyRenderer;
    private static AerialHellDimensionSkyRenderer ahSkyRenderer = null;
    
    @Inject(method = "addSkyPass", at = @At("HEAD"), cancellable = true)
    private void renderSky(FrameGraphBuilder frameGraphBuilder, CameraRenderState cameraRenderState, GpuBufferSlice fogBuffer, CallbackInfo callbackInfo)
    {
        LevelRenderer worldRenderer = (LevelRenderer) (Object) this;
        ClientLevel world = worldRenderer.level;

        //Override only for Aerial Hell dimension
        if (world == null || world.dimension() != AerialHellDimensions.AERIAL_HELL_DIMENSION) {return;}

        FogType fogType = cameraRenderState.fogType;
        if (fogType != FogType.POWDER_SNOW && fogType != FogType.LAVA && !cameraRenderState.entityRenderState.doesMobEffectBlockSky)
        {
            SkyRenderState skyRenderState = this.levelRenderState.skyRenderState;
            if (skyRenderState.skybox != DimensionType.Skybox.NONE)
            {
                FramePass framePass = frameGraphBuilder.addPass("sky");
                worldRenderer.targets.main = framePass.readsAndWrites(worldRenderer.targets.main);
                framePass.executes(() ->
                {
                    RenderSystem.setShaderFog(fogBuffer);
                    aerialHellRender(levelRenderState, skyRenderState);
                });
            }
        }
        callbackInfo.cancel();
    }

    private static boolean hasBlindnessOrDarknessEffect(Camera camera)
    {
        Entity var3 = camera.entity();
        if (!(var3 instanceof LivingEntity livingEntity)) {return false;}
        else {return livingEntity.hasEffect(MobEffects.BLINDNESS) || livingEntity.hasEffect(MobEffects.DARKNESS);}
    }

    private static void aerialHellRender(LevelRenderState worldRenderState, SkyRenderState skyRenderState)
    {
        if (ahSkyRenderer == null) {ahSkyRenderer = new AerialHellDimensionSkyRenderer();}

        PoseStack matrixStack = new PoseStack();
        float red = ARGB.redFloat(skyRenderState.skyColor);
        float green = ARGB.greenFloat(skyRenderState.skyColor);
        float blue =ARGB.blueFloat(skyRenderState.skyColor);
        ahSkyRenderer.renderSkyDisc(red, green, blue);
        ahSkyRenderer.renderSunriseAndSunset(matrixStack, skyRenderState.sunAngle, skyRenderState.sunriseAndSunsetColor);

        float moonAlpha = Math.min(skyRenderState.starBrightness * 2, 1.0F); //Moon brightness = 0.0F during the day, 1.0F during the night. Using / 0.5F and "min" because StarBrightness is never 1.0F (never above 0.6F) apparently
        float sunAlpha = 1.0F - moonAlpha; //Sun brightness = 1.0F during the day, 0.0F during the night

        ahSkyRenderer.renderSunMoonAndStars(matrixStack, skyRenderState.sunAngle, skyRenderState.moonAngle, skyRenderState.starAngle, skyRenderState.moonPhase, sunAlpha, moonAlpha, skyRenderState.starBrightness);
        if (skyRenderState.shouldRenderDarkDisc) {ahSkyRenderer.renderDarkDisc();}
    }
}
