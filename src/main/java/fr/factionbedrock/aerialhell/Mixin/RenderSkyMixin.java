package fr.factionbedrock.aerialhell.Mixin;

import com.mojang.blaze3d.buffers.GpuBufferSlice;
import com.mojang.blaze3d.framegraph.FrameGraphBuilder;
import com.mojang.blaze3d.framegraph.FramePass;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import fr.factionbedrock.aerialhell.Client.World.AerialHellDimensionSkyRenderer;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellDimensions;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.LevelTargetBundle;
import net.minecraft.client.renderer.SkyRenderer;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.state.level.LevelRenderState;
import net.minecraft.client.renderer.state.level.SkyRenderState;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.model.sprite.AtlasManager;
import net.minecraft.util.ARGB;
import net.minecraft.util.profiling.Profiler;
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
    @Shadow private LevelTargetBundle targets;
    @Shadow private TextureManager textureManager;
    @Shadow private AtlasManager atlasManager;
    private static AerialHellDimensionSkyRenderer ahSkyRenderer = null;
    
    @Inject(method = "addSkyPass", at = @At("HEAD"), cancellable = true)
    private void renderSky(FrameGraphBuilder frameGraphBuilder, CameraRenderState cameraState, GpuBufferSlice fogBuffer, CallbackInfo callbackInfo)
    {
        LevelRenderer levelRenderer = (LevelRenderer) (Object) this;
        ClientLevel level = Minecraft.getInstance().level;
        //Override only for Aerial Hell dimension
        if (level == null || level.dimension() != AerialHellDimensions.AERIAL_HELL_DIMENSION) {return;}

        FogType fogType = cameraState.fogType;
        if (fogType != FogType.POWDER_SNOW && fogType != FogType.LAVA && !cameraState.entityRenderState.doesMobEffectBlockSky)
        {
            //temporary solution to make custom sky render work
            //initializing vanilla skyRenderer too
            //because AH skyRenderer needs skyRenderer to be initialized (IDK why)
            if (this.levelRenderState.shouldResetSkyRenderer || this.skyRenderer == null)
            {
                if (this.skyRenderer != null) {this.skyRenderer.close();}
                this.skyRenderer = new SkyRenderer(textureManager, atlasManager, levelRenderer.gameRenderer.mainRenderTarget());
            }

            if (this.levelRenderState.shouldResetSkyRenderer || ahSkyRenderer == null)
            {
                if (ahSkyRenderer != null) {ahSkyRenderer.close();}
                ahSkyRenderer = new AerialHellDimensionSkyRenderer(levelRenderer.gameRenderer.mainRenderTarget());
            }

            SkyRenderState state = this.levelRenderState.skyRenderState;
            if (state.skybox != DimensionType.Skybox.NONE)
            {
                FramePass framePass = frameGraphBuilder.addPass("sky");
                this.targets.main = framePass.readsAndWrites(this.targets.main);
                framePass.executes(() ->
                        {
                            RenderSystem.setShaderFog(fogBuffer);
                            aerialHellRender(state);
                            Profiler.get().pop();
                        }
                );
            }
        }
        callbackInfo.cancel();
    }

    private static void aerialHellRender(SkyRenderState state)
    {
        PoseStack poseStack = new PoseStack();
        ahSkyRenderer.renderSkyDisc(state.skyColor);
        ahSkyRenderer.renderSunriseAndSunset(poseStack, state.sunAngle, state.sunriseAndSunsetColor);

        float moonAlpha = Math.min(state.starBrightness * 2, 1.0F); //Moon brightness = 0.0F during the day, 1.0F during the night. Using / 0.5F and "min" because StarBrightness is never 1.0F (never above 0.6F) apparently
        float sunAlpha = 1.0F - moonAlpha; //Sun brightness = 1.0F during the day, 0.0F during the night

        ahSkyRenderer.renderSunMoonAndStars(poseStack, state.sunAngle, state.moonAngle, state.starAngle, state.moonPhase, sunAlpha, moonAlpha, state.starBrightness);
        if (state.shouldRenderDarkDisc) {ahSkyRenderer.renderDarkDisc();}
    }
}
