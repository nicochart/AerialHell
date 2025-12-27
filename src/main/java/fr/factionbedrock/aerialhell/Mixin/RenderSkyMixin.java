package fr.factionbedrock.aerialhell.Mixin;

import com.mojang.blaze3d.buffers.GpuBufferSlice;
import com.mojang.blaze3d.systems.RenderSystem;
import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.World.AerialHellDimensionSkyRenderer;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellDimensions;
import net.minecraft.block.enums.CameraSubmersionType;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.render.state.SkyRenderState;
import net.minecraft.client.render.state.WorldRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.world.dimension.DimensionType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldRenderer.class)
public class RenderSkyMixin
{
    @Shadow @Final public WorldRenderState worldRenderState;
    private static AerialHellDimensionSkyRenderer ahSkyRenderer = null;
    
    @Inject(method = "renderSky", at = @At("HEAD"), cancellable = true)
    private void renderSky(FrameGraphBuilder frameGraphBuilder, Camera camera, GpuBufferSlice fogBuffer, CallbackInfo callbackInfo)
    {
        WorldRenderer worldRenderer = (WorldRenderer) (Object) this;
        ClientWorld world = worldRenderer.world;

        //Override only for Aerial Hell dimension
        if (world == null || world.getRegistryKey() != AerialHellDimensions.AERIAL_HELL_DIMENSION) {return;}

        CameraSubmersionType cameraSubmersionType = camera.getSubmersionType();
        if (cameraSubmersionType != CameraSubmersionType.POWDER_SNOW && cameraSubmersionType != CameraSubmersionType.LAVA && !hasBlindnessOrDarknessEffect(camera))
        {
            SkyRenderState skyRenderState = worldRenderer.worldRenderState.skyRenderState;
            if (skyRenderState.skybox != DimensionType.Skybox.NONE)
            {
                FramePass framePass = frameGraphBuilder.createPass("sky");
                worldRenderer.framebufferSet.mainFramebuffer = framePass.transfer(worldRenderer.framebufferSet.mainFramebuffer);
                framePass.setRenderer(() ->
                {
                    RenderSystem.setShaderFog(fogBuffer);
                    aerialHellRender(worldRenderState, skyRenderState);
                });
            }
        }
        callbackInfo.cancel();
    }

    private static boolean hasBlindnessOrDarknessEffect(Camera camera)
    {
        Entity var3 = camera.getFocusedEntity();
        if (!(var3 instanceof LivingEntity livingEntity)) {return false;}
        else {return livingEntity.hasStatusEffect(StatusEffects.BLINDNESS) || livingEntity.hasStatusEffect(StatusEffects.DARKNESS);}
    }

    private static void aerialHellRender(WorldRenderState worldRenderState, SkyRenderState skyRenderState)
    {
        if (ahSkyRenderer == null) {ahSkyRenderer = new AerialHellDimensionSkyRenderer();}

        MatrixStack matrixStack = new MatrixStack();
        float red = ColorHelper.getRedFloat(skyRenderState.skyColor);
        float green = ColorHelper.getGreenFloat(skyRenderState.skyColor);
        float blue =ColorHelper.getBlueFloat(skyRenderState.skyColor);
        ahSkyRenderer.renderSkyDisc(red, green, blue);
        ahSkyRenderer.renderSunriseAndSunset(matrixStack, skyRenderState.sunAngle, skyRenderState.sunriseAndSunsetColor);

        float moonAlpha = Math.min(skyRenderState.starBrightness * 2, 1.0F); //Moon brightness = 0.0F during the day, 1.0F during the night. Using / 0.5F and "min" because StarBrightness is never 1.0F (never above 0.6F) apparently
        float sunAlpha = 1.0F - moonAlpha; //Sun brightness = 1.0F during the day, 0.0F during the night

        ahSkyRenderer.renderSunMoonAndStars(matrixStack, skyRenderState.sunAngle, skyRenderState.moonAngle, skyRenderState.starAngle, skyRenderState.moonPhase, sunAlpha, moonAlpha, skyRenderState.starBrightness);
        if (skyRenderState.shouldRenderSkyDark) {ahSkyRenderer.renderDarkDisc();}
    }
}
