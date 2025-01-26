package fr.factionbedrock.aerialhell.Client.World;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Axis;
import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.Util.SkyRendererHelper;
import net.minecraft.client.renderer.FogParameters;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ARGB;
import net.minecraft.util.Mth;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.joml.Matrix4f;
import org.joml.Matrix4fStack;

//edited copy of net.minecraft.client.renderer.SkyRenderer
@OnlyIn(Dist.CLIENT)
public class AerialHellDimensionSkyRenderer implements AutoCloseable
{
    private static final ResourceLocation AERIAL_HELL_SUN_LOCATION = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "textures/environment/aerial_hell_sun.png");
    private static final ResourceLocation AERIAL_HELL_MOON_PHASES_LOCATION = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "textures/environment/aerial_hell_moon_phases.png");
    private final VertexBuffer starBuffer;
    public final VertexBuffer topSkyBuffer;
    private final VertexBuffer bottomSkyBuffer;

    public AerialHellDimensionSkyRenderer()
    {
        starBuffer = VertexBuffer.uploadStatic(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION, SkyRendererHelper::buildStars);
        topSkyBuffer = VertexBuffer.uploadStatic(VertexFormat.Mode.TRIANGLE_FAN, DefaultVertexFormat.POSITION, vertexConsumer -> SkyRendererHelper.buildSkyDisc(vertexConsumer, 16.0F));
        bottomSkyBuffer = VertexBuffer.uploadStatic(VertexFormat.Mode.TRIANGLE_FAN, DefaultVertexFormat.POSITION, vertexConsumer -> SkyRendererHelper.buildSkyDisc(vertexConsumer, -16.0F));
    }

    public void renderSkyDisc(float red, float green, float blue)
    {
        RenderSystem.setShaderColor(red, green, blue, 1.0F);
        this.topSkyBuffer.drawWithRenderType(RenderType.sky());
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
    }

    public void renderDarkDisc(PoseStack poseStack)
    {
        RenderSystem.setShaderColor(0.0F, 0.0F, 0.0F, 1.0F);
        poseStack.pushPose();
        poseStack.translate(0.0F, 12.0F, 0.0F);
        this.bottomSkyBuffer.drawWithRenderType(RenderType.sky());
        poseStack.popPose();
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
    }

    public void renderSunMoonAndStars(PoseStack poseStack, MultiBufferSource.BufferSource bufferSource, float timeOfDay,int moonPhase, float sunAlpha, float moonAlpha, float starAlpha, FogParameters fog)
    {
        poseStack.pushPose();
        poseStack.mulPose(Axis.YP.rotationDegrees(-90.0F));
        poseStack.mulPose(Axis.XP.rotationDegrees(timeOfDay * 360.0F));
        this.renderSun(sunAlpha, bufferSource, poseStack);
        this.renderMoon(moonPhase, moonAlpha, bufferSource, poseStack);
        bufferSource.endBatch();
        if (starAlpha > 0.0F) {this.renderStars(fog, starAlpha, poseStack);}
        poseStack.popPose();
    }

    private void renderSun(float alpha, MultiBufferSource bufferSource, PoseStack poseStack)
    {
        float f = 30.0F;
        float f1 = 100.0F;
        VertexConsumer vertexconsumer = bufferSource.getBuffer(RenderType.celestial(AERIAL_HELL_SUN_LOCATION));
        int i = ARGB.white(alpha);
        Matrix4f matrix4f = poseStack.last().pose();
        vertexconsumer.addVertex(matrix4f, -30.0F, 100.0F, -30.0F).setUv(0.0F, 0.0F).setColor(i);
        vertexconsumer.addVertex(matrix4f, 30.0F, 100.0F, -30.0F).setUv(1.0F, 0.0F).setColor(i);
        vertexconsumer.addVertex(matrix4f, 30.0F, 100.0F, 30.0F).setUv(1.0F, 1.0F).setColor(i);
        vertexconsumer.addVertex(matrix4f, -30.0F, 100.0F, 30.0F).setUv(0.0F, 1.0F).setColor(i);
    }

    private void renderMoon(int phase, float alpha, MultiBufferSource bufferSource, PoseStack poseStack)
    {
        float f = 20.0F;
        int i = phase % 4;
        int j = phase / 4 % 2;
        float f1 = (float)(i + 0) / 4.0F;
        float f2 = (float)(j + 0) / 2.0F;
        float f3 = (float)(i + 1) / 4.0F;
        float f4 = (float)(j + 1) / 2.0F;
        float f5 = 100.0F;
        VertexConsumer vertexconsumer = bufferSource.getBuffer(RenderType.celestial(AERIAL_HELL_MOON_PHASES_LOCATION));
        int k = ARGB.white(alpha);
        Matrix4f matrix4f = poseStack.last().pose();
        vertexconsumer.addVertex(matrix4f, -20.0F, -100.0F, 20.0F).setUv(f3, f4).setColor(k);
        vertexconsumer.addVertex(matrix4f, 20.0F, -100.0F, 20.0F).setUv(f1, f4).setColor(k);
        vertexconsumer.addVertex(matrix4f, 20.0F, -100.0F, -20.0F).setUv(f1, f2).setColor(k);
        vertexconsumer.addVertex(matrix4f, -20.0F, -100.0F, -20.0F).setUv(f3, f2).setColor(k);
    }

    private void renderStars(FogParameters fog, float starBrightness, PoseStack poseStack)
    {
        Matrix4fStack matrix4fstack = RenderSystem.getModelViewStack();
        matrix4fstack.pushMatrix();
        matrix4fstack.mul(poseStack.last().pose());
        RenderSystem.setShaderColor(starBrightness, starBrightness, starBrightness, starBrightness);
        RenderSystem.setShaderFog(FogParameters.NO_FOG);
        this.starBuffer.drawWithRenderType(RenderType.stars());
        RenderSystem.setShaderFog(fog);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        matrix4fstack.popMatrix();
    }

    public void renderSunriseAndSunset(PoseStack poseStack, MultiBufferSource.BufferSource bufferSource, float sunAngle, int color)
    {
        poseStack.pushPose();
        poseStack.mulPose(Axis.XP.rotationDegrees(90.0F));
        float f = Mth.sin(sunAngle) < 0.0F ? 180.0F : 0.0F;
        poseStack.mulPose(Axis.ZP.rotationDegrees(f));
        poseStack.mulPose(Axis.ZP.rotationDegrees(90.0F));
        Matrix4f matrix4f = poseStack.last().pose();
        VertexConsumer vertexconsumer = bufferSource.getBuffer(RenderType.sunriseSunset());
        float f1 = ARGB.alphaFloat(color);
        vertexconsumer.addVertex(matrix4f, 0.0F, 100.0F, 0.0F).setColor(color);
        int i = ARGB.transparent(color);
        int j = 16;

        for (int k = 0; k <= 16; k++)
        {
            float f2 = (float)k * (float) (Math.PI * 2) / 16.0F;
            float f3 = Mth.sin(f2);
            float f4 = Mth.cos(f2);
            vertexconsumer.addVertex(matrix4f, f3 * 120.0F, f4 * 120.0F, -f4 * 40.0F * f1).setColor(i);
        }

        poseStack.popPose();
    }

    private void buildEndSky(VertexConsumer buffer)
    {
        for (int i = 0; i < 6; i++)
        {
            Matrix4f matrix4f = new Matrix4f();
            switch (i)
            {
                case 1:
                    matrix4f.rotationX((float) (Math.PI / 2));
                    break;
                case 2:
                    matrix4f.rotationX((float) (-Math.PI / 2));
                    break;
                case 3:
                    matrix4f.rotationX((float) Math.PI);
                    break;
                case 4:
                    matrix4f.rotationZ((float) (Math.PI / 2));
                    break;
                case 5:
                    matrix4f.rotationZ((float) (-Math.PI / 2));
            }

            buffer.addVertex(matrix4f, -100.0F, -100.0F, -100.0F).setUv(0.0F, 0.0F).setColor(-14145496);
            buffer.addVertex(matrix4f, -100.0F, -100.0F, 100.0F).setUv(0.0F, 16.0F).setColor(-14145496);
            buffer.addVertex(matrix4f, 100.0F, -100.0F, 100.0F).setUv(16.0F, 16.0F).setColor(-14145496);
            buffer.addVertex(matrix4f, 100.0F, -100.0F, -100.0F).setUv(16.0F, 0.0F).setColor(-14145496);
        }
    }

    @Override public void close()
    {
        this.starBuffer.close();
        this.topSkyBuffer.close();
        this.bottomSkyBuffer.close();
    }
}
