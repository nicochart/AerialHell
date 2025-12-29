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
import net.minecraft.client.renderer.LevelTargetBundle;
import net.minecraft.client.renderer.SkyRenderer;
import net.minecraft.client.renderer.state.LevelRenderState;
import net.minecraft.client.renderer.state.SkyRenderState;
import net.minecraft.util.ARGB;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.material.FogType;
import org.joml.Matrix4f;
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
    @Shadow private LevelTargetBundle targets;
    private static AerialHellDimensionSkyRenderer ahSkyRenderer = null;
    
    @Inject(method = "addSkyPass(Lcom/mojang/blaze3d/framegraph/FrameGraphBuilder;" + "Lnet/minecraft/client/Camera;" + "Lcom/mojang/blaze3d/buffers/GpuBufferSlice;" + "Lorg/joml/Matrix4f;)V", at = @At("HEAD"), cancellable = true)
    private void addSkyPass(FrameGraphBuilder frameGraphBuilder, Camera camera, GpuBufferSlice shaderFog, Matrix4f modelViewMatrix, CallbackInfo callbackInfo)
    {
        LevelRenderer levelRenderer = (LevelRenderer) (Object) this;
        ClientLevel world = levelRenderer.level;
        //Override only for Aerial Hell dimension
        if (world == null || world.dimension() != AerialHellDimensions.AERIAL_HELL_DIMENSION) {return;}

        FogType cameraSubmersionType = camera.getFluidInCamera();
        if (cameraSubmersionType != FogType.POWDER_SNOW && cameraSubmersionType != FogType.LAVA && !hasBlindnessOrDarknessEffect(camera))
        {
            SkyRenderState skyRenderState = levelRenderState.skyRenderState;
            if (skyRenderState.skybox != DimensionType.Skybox.NONE)
            {
                FramePass framePass = frameGraphBuilder.addPass("sky");
                targets.main = framePass.readsAndWrites(targets.main);
                framePass.executes(() ->
                {
                    RenderSystem.setShaderFog(shaderFog);
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

    private static void aerialHellRender(LevelRenderState levelRenderState, SkyRenderState skyRenderState)
    {
        if (ahSkyRenderer == null) {ahSkyRenderer = new AerialHellDimensionSkyRenderer();}

        PoseStack poseStack = new PoseStack();
        float red = ARGB.redFloat(skyRenderState.skyColor);
        float green = ARGB.greenFloat(skyRenderState.skyColor);
        float blue = ARGB.blueFloat(skyRenderState.skyColor);
        ahSkyRenderer.renderSkyDisc(red, green, blue);
        ahSkyRenderer.renderSunriseAndSunset(poseStack, skyRenderState.sunAngle, skyRenderState.sunriseAndSunsetColor);

        float moonAlpha = Math.min(skyRenderState.starBrightness * 2, 1.0F); //Moon brightness = 0.0F during the day, 1.0F during the night. Using / 0.5F and "min" because StarBrightness is never 1.0F (never above 0.6F) apparently
        float sunAlpha = 1.0F - moonAlpha; //Sun brightness = 1.0F during the day, 0.0F during the night

        ahSkyRenderer.renderSunMoonAndStars(poseStack, skyRenderState.sunAngle, skyRenderState.moonAngle, skyRenderState.starAngle, skyRenderState.moonPhase, sunAlpha, moonAlpha, skyRenderState.starBrightness);
        if (skyRenderState.shouldRenderDarkDisc) {ahSkyRenderer.renderDarkDisc();}
    }
}
