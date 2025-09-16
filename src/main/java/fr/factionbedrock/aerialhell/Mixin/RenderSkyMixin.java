package fr.factionbedrock.aerialhell.Mixin;

import com.mojang.blaze3d.buffers.GpuBufferSlice;
import com.mojang.blaze3d.systems.RenderSystem;
import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.World.AerialHellDimensionSkyRenderer;
import net.minecraft.block.enums.CameraSubmersionType;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.math.ColorHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//temporary solution because I can't get Fabric's DimensionRenderingRegistry.SkyRenderer to work

@Mixin(WorldRenderer.class)
public class RenderSkyMixin
{
    private static AerialHellDimensionSkyRenderer ahSkyRenderer = null;
    
    @Inject(method = "renderSky", at = @At("HEAD"), cancellable = true)
    private void renderSky(FrameGraphBuilder frameGraphBuilder, Camera camera, float partialTicks, GpuBufferSlice fog, CallbackInfo callbackInfo)
    {
        WorldRenderer worldRenderer = (WorldRenderer) (Object) this;
        ClientWorld world = worldRenderer.world;

        //Override only for Aerial Hell dimension
        if (world == null || !world.getDimension().effects().getNamespace().equals(AerialHell.MODID) || !world.getDimension().effects().getPath().equals("aerial_hell")) {return;}

        CameraSubmersionType cameraSubmersionType = camera.getSubmersionType();
        if (cameraSubmersionType != CameraSubmersionType.POWDER_SNOW && cameraSubmersionType != CameraSubmersionType.LAVA && !hasBlindnessOrDarknessEffect(camera)) {
            DimensionEffects dimensionEffects = world.getDimensionEffects();
            DimensionEffects.SkyType skyType = dimensionEffects.getSkyType();
            if (skyType != DimensionEffects.SkyType.NONE)
            {
                FramePass renderPass = frameGraphBuilder.createPass("sky");
                worldRenderer.framebufferSet.mainFramebuffer = renderPass.transfer(worldRenderer.framebufferSet.mainFramebuffer);
                renderPass.setRenderer(() ->
                {
                    RenderSystem.setShaderFog(fog);
                    aerialHellRender(world, camera, partialTicks, fog);
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

    //was previously written and used in AerialHellDimensionSpecialEffects
    private void aerialHellRender(ClientWorld world, Camera camera, float partialTicks, GpuBufferSlice fog)
    {
        if (ahSkyRenderer == null) {ahSkyRenderer = new AerialHellDimensionSkyRenderer();}

        DimensionEffects dimensionEffects = world.getDimensionEffects();

        MatrixStack matrixStack = new MatrixStack();
        float sunAngle = world.getSkyAngleRadians(partialTicks);
        float timeOfDay = world.getSkyAngle(partialTicks);
        float moonAlpha = Math.min(world.getStarBrightness(partialTicks) * 2, 1.0F); //Moon brightness = 0.0F during the day, 1.0F during the night. Using / 0.5F and "min" because StarBrightness is never 1.0F (never above 0.6F) apparently
        float sunAlpha = 1.0F - moonAlpha; //Sun brightness = 1.0F during the day, 0.0F during the night
        float starAlpha = world.getStarBrightness(partialTicks); //no Rain effect
        int sunriseOrSunsetColor = dimensionEffects.getSkyColor(timeOfDay);
        int moonPhase = world.getMoonPhase();
        int skyColor = world.getSkyColor(camera.getPos(), partialTicks);
        float r = ColorHelper.getRedFloat(skyColor), g = ColorHelper.getGreenFloat(skyColor), b = ColorHelper.getBlueFloat(skyColor);
        ahSkyRenderer.renderSkyDisc(r, g, b);
        VertexConsumerProvider.Immediate bufferSource = MinecraftClient.getInstance().getBufferBuilders().getEntityVertexConsumers();
        if (dimensionEffects.isSunRisingOrSetting(timeOfDay))
        {
            ahSkyRenderer.renderSunriseAndSunset(matrixStack, bufferSource, sunAngle, sunriseOrSunsetColor);
        }
        ahSkyRenderer.renderSunMoonAndStars(matrixStack, bufferSource, timeOfDay, moonPhase, sunAlpha, moonAlpha, starAlpha);
        bufferSource.draw();
        if (shouldRenderDarkDisc(partialTicks, world))
        {
            ahSkyRenderer.renderDarkDisc();
        }
    }

    //edited copy of net.minecraft.client.render.WorldRenderer method
    private static boolean shouldRenderDarkDisc(float partialTick, ClientWorld level)
    {
        if (MinecraftClient.getInstance().player != null)
        {
            return MinecraftClient.getInstance().player.getCameraPosVec(partialTick).y - level.getLevelProperties().getSkyDarknessHeight(level) < 0.0;
        }
        return false;
    }
}
