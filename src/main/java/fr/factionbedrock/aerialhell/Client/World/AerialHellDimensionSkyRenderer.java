package fr.factionbedrock.aerialhell.Client.World;

import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.buffers.GpuBufferSlice;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.systems.RenderPass;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Axis;
import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.Util.SkyRendererHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.AbstractTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ARGB;
import net.minecraft.util.Mth;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.joml.Matrix4f;
import org.joml.Matrix4fStack;
import org.joml.Vector3f;
import org.joml.Vector4f;

import javax.annotation.Nullable;
import java.util.OptionalDouble;
import java.util.OptionalInt;

//edited copy of net.minecraft.client.renderer.SkyRenderer
@OnlyIn(Dist.CLIENT)
public class AerialHellDimensionSkyRenderer implements AutoCloseable
{
    private static final ResourceLocation AERIAL_HELL_SUN_LOCATION = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "textures/environment/aerial_hell_sun.png");
    private static final ResourceLocation AERIAL_HELL_MOON_PHASES_LOCATION = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "textures/environment/aerial_hell_moon_phases.png");
    private AbstractTexture sunTexture;
    private AbstractTexture moonTexture;
    private final RenderSystem.AutoStorageIndexBuffer starIndices;
    private final RenderSystem.AutoStorageIndexBuffer quadIndices;
    private final GpuBuffer starBuffer;
    private final GpuBuffer sunBuffer;
    private final GpuBuffer moonBuffer;
    private final GpuBuffer sunriseBuffer;
    public final GpuBuffer topSkyBuffer;
    private final GpuBuffer bottomSkyBuffer;
    private int starIndexCount;

    public AerialHellDimensionSkyRenderer()
    {
        this.starIndices = RenderSystem.getSequentialBuffer(VertexFormat.Mode.QUADS);
        this.quadIndices = RenderSystem.getSequentialBuffer(VertexFormat.Mode.QUADS);
        this.starBuffer = this.buildStars();
        this.sunBuffer = this.buildSunQuad();
        this.moonBuffer = this.buildMoonPhases();
        this.sunriseBuffer = this.buildSunriseFan();

        try (ByteBufferBuilder bytebufferbuilder = ByteBufferBuilder.exactlySized(10 * DefaultVertexFormat.POSITION.getVertexSize()))
        {
            BufferBuilder bufferbuilder = new BufferBuilder(bytebufferbuilder, VertexFormat.Mode.TRIANGLE_FAN, DefaultVertexFormat.POSITION);
            SkyRendererHelper.buildSkyDisc(bufferbuilder, 16.0F);

            try (MeshData topSkyBufferMeshData = bufferbuilder.buildOrThrow())
            {
                this.topSkyBuffer = RenderSystem.getDevice().createBuffer(() -> "Top sky vertex buffer", 32, topSkyBufferMeshData.vertexBuffer());
            }

            bufferbuilder = new BufferBuilder(bytebufferbuilder, VertexFormat.Mode.TRIANGLE_FAN, DefaultVertexFormat.POSITION);
            SkyRendererHelper.buildSkyDisc(bufferbuilder, -16.0F);

            try (MeshData meshdata1 = bufferbuilder.buildOrThrow())
            {
                this.bottomSkyBuffer = RenderSystem.getDevice().createBuffer(() -> "Bottom sky vertex buffer", 32, meshdata1.vertexBuffer());
            }
        }
    }

    protected void initTextures() //TODO call when ?
    {
        this.sunTexture = this.getTexture(AERIAL_HELL_SUN_LOCATION);
        this.moonTexture = this.getTexture(AERIAL_HELL_MOON_PHASES_LOCATION);
    }

    //copy of net.minecraft.client.renderer.SkyRenderer method getTexture
    private AbstractTexture getTexture(ResourceLocation location)
    {
        TextureManager texturemanager = Minecraft.getInstance().getTextureManager();
        AbstractTexture abstracttexture = texturemanager.getTexture(location);
        abstracttexture.setUseMipmaps(false);
        return abstracttexture;
    }

    protected GpuBuffer buildStars()
    {
        GpuBuffer gpubuffer;
        try (ByteBufferBuilder bytebufferbuilder = ByteBufferBuilder.exactlySized(DefaultVertexFormat.POSITION.getVertexSize() * 1900 * 4))
        {
            BufferBuilder bufferbuilder = new BufferBuilder(bytebufferbuilder, VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION);

            SkyRendererHelper.buildStars(bufferbuilder);

            try (MeshData meshdata = bufferbuilder.buildOrThrow())
            {
                this.starIndexCount = meshdata.drawState().indexCount();
                gpubuffer = RenderSystem.getDevice().createBuffer(() -> "Stars vertex buffer", 40, meshdata.vertexBuffer());
            }
        }
        return gpubuffer;
    }

    private GpuBuffer buildSunQuad()
    {
        GpuBuffer gpubuffer;
        try (ByteBufferBuilder bytebufferbuilder = ByteBufferBuilder.exactlySized(4 * DefaultVertexFormat.POSITION_TEX.getVertexSize()))
        {
            BufferBuilder bufferbuilder = new BufferBuilder(bytebufferbuilder, VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
            Matrix4f matrix4f = new Matrix4f();
            bufferbuilder.addVertex(matrix4f, -1.0F, 0.0F, -1.0F).setUv(0.0F, 0.0F);
            bufferbuilder.addVertex(matrix4f, 1.0F, 0.0F, -1.0F).setUv(1.0F, 0.0F);
            bufferbuilder.addVertex(matrix4f, 1.0F, 0.0F, 1.0F).setUv(1.0F, 1.0F);
            bufferbuilder.addVertex(matrix4f, -1.0F, 0.0F, 1.0F).setUv(0.0F, 1.0F);

            try (MeshData meshdata = bufferbuilder.buildOrThrow())
            {
                gpubuffer = RenderSystem.getDevice().createBuffer(() -> "Sun quad", 40, meshdata.vertexBuffer());
            }
        }
        return gpubuffer;
    }

    private GpuBuffer buildMoonPhases()
    {
        int number = 8;
        int vertexSize = DefaultVertexFormat.POSITION_TEX.getVertexSize();

        GpuBuffer gpubuffer;
        try (ByteBufferBuilder bytebufferbuilder = ByteBufferBuilder.exactlySized(32 * vertexSize))
        {
            BufferBuilder bufferbuilder = new BufferBuilder(bytebufferbuilder, VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
            Matrix4f matrix4f = new Matrix4f();

            for(int k = 0; k < 8; ++k)
            {
                int l = k % 4;
                int i1 = k / 4 % 2;
                float f = (float)l / 4.0F;
                float f1 = (float)i1 / 2.0F;
                float f2 = (float)(l + 1) / 4.0F;
                float f3 = (float)(i1 + 1) / 2.0F;
                bufferbuilder.addVertex(matrix4f, -1.0F, 0.0F, 1.0F).setUv(f2, f3);
                bufferbuilder.addVertex(matrix4f, 1.0F, 0.0F, 1.0F).setUv(f, f3);
                bufferbuilder.addVertex(matrix4f, 1.0F, 0.0F, -1.0F).setUv(f, f1);
                bufferbuilder.addVertex(matrix4f, -1.0F, 0.0F, -1.0F).setUv(f2, f1);
            }

            try (MeshData meshdata = bufferbuilder.buildOrThrow())
            {
                gpubuffer = RenderSystem.getDevice().createBuffer(() -> "Moon phases", 32, meshdata.vertexBuffer());
            }
        }
        return gpubuffer;
    }

    private GpuBuffer buildSunriseFan()
    {
        int number = 18;
        int vertexSize = DefaultVertexFormat.POSITION_COLOR.getVertexSize();

        GpuBuffer gpubuffer;
        try (ByteBufferBuilder bytebufferbuilder = ByteBufferBuilder.exactlySized(number * vertexSize))
        {
            BufferBuilder bufferbuilder = new BufferBuilder(bytebufferbuilder, VertexFormat.Mode.TRIANGLE_FAN, DefaultVertexFormat.POSITION_COLOR);
            int k = ARGB.white(1.0F);
            int l = ARGB.white(0.0F);
            bufferbuilder.addVertex(0.0F, 100.0F, 0.0F).setColor(k);

            for(int i1 = 0; i1 <= 16; ++i1)
            {
                float f = (float)i1 * ((float)Math.PI * 2F) / 16.0F;
                float f1 = Mth.sin(f);
                float f2 = Mth.cos(f);
                bufferbuilder.addVertex(f1 * 120.0F, f2 * 120.0F, -f2 * 40.0F).setColor(l);
            }

            try (MeshData meshdata = bufferbuilder.buildOrThrow())
            {
                gpubuffer = RenderSystem.getDevice().createBuffer(() -> "Sunrise/Sunset fan", 32, meshdata.vertexBuffer());
            }
        }
        return gpubuffer;
    }

    public void renderSkyDisc(float red, float green, float blue)
    {
        GpuBufferSlice gpubufferslice = RenderSystem.getDynamicUniforms().writeTransform(RenderSystem.getModelViewMatrix(), new Vector4f(red, green, blue, 1.0F), new Vector3f(), new Matrix4f(), 0.0F);
        GpuTextureView gputextureview = Minecraft.getInstance().getMainRenderTarget().getColorTextureView();
        GpuTextureView gputextureview1 = Minecraft.getInstance().getMainRenderTarget().getDepthTextureView();

        try (RenderPass renderpass = RenderSystem.getDevice().createCommandEncoder().createRenderPass(() -> "Sky disc", gputextureview, OptionalInt.empty(), gputextureview1, OptionalDouble.empty()))
        {
            renderpass.setPipeline(RenderPipelines.SKY);
            RenderSystem.bindDefaultUniforms(renderpass);
            renderpass.setUniform("DynamicTransforms", gpubufferslice);
            renderpass.setVertexBuffer(0, this.topSkyBuffer);
            renderpass.draw(0, 10);
        }

    }

    public void renderDarkDisc()
    {
        Matrix4fStack matrix4fstack = RenderSystem.getModelViewStack();
        matrix4fstack.pushMatrix();
        matrix4fstack.translate(0.0F, 12.0F, 0.0F);
        GpuBufferSlice gpubufferslice = RenderSystem.getDynamicUniforms().writeTransform(matrix4fstack, new Vector4f(0.0F, 0.0F, 0.0F, 1.0F), new Vector3f(), new Matrix4f(), 0.0F);
        GpuTextureView colorTextureView = Minecraft.getInstance().getMainRenderTarget().getColorTextureView();
        GpuTextureView depthTextureView = Minecraft.getInstance().getMainRenderTarget().getDepthTextureView();

        try (RenderPass renderpass = RenderSystem.getDevice().createCommandEncoder().createRenderPass(() -> "Sky dark", colorTextureView, OptionalInt.empty(), depthTextureView, OptionalDouble.empty()))
        {
            renderpass.setPipeline(RenderPipelines.SKY);
            RenderSystem.bindDefaultUniforms(renderpass);
            renderpass.setUniform("DynamicTransforms", gpubufferslice);
            renderpass.setVertexBuffer(0, this.bottomSkyBuffer);
            renderpass.draw(0, 10);
        }

        matrix4fstack.popMatrix();
    }

    public void renderSunMoonAndStars(PoseStack poseStack, float timeOfDay, int moonPhase, float rainLevel, float starAlpha)
    {
        poseStack.pushPose();
        poseStack.mulPose(Axis.YP.rotationDegrees(-90.0F));
        poseStack.mulPose(Axis.XP.rotationDegrees(timeOfDay * 360.0F));
        this.renderSun(rainLevel, poseStack);
        this.renderMoon(moonPhase, rainLevel, poseStack);
        if (starAlpha > 0.0F) {this.renderStars(starAlpha, poseStack);}

        poseStack.popPose();
    }

    private void renderSun(float alpha, PoseStack poseStack)
    {
        if (this.sunTexture != null)
        {
            Matrix4fStack matrix4fstack = RenderSystem.getModelViewStack();
            matrix4fstack.pushMatrix();
            matrix4fstack.mul(poseStack.last().pose());
            matrix4fstack.translate(0.0F, 100.0F, 0.0F);
            matrix4fstack.scale(30.0F, 1.0F, 30.0F);
            GpuBufferSlice gpubufferslice = RenderSystem.getDynamicUniforms().writeTransform(matrix4fstack, new Vector4f(1.0F, 1.0F, 1.0F, alpha), new Vector3f(), new Matrix4f(), 0.0F);
            GpuTextureView gputextureview = Minecraft.getInstance().getMainRenderTarget().getColorTextureView();
            GpuTextureView gputextureview1 = Minecraft.getInstance().getMainRenderTarget().getDepthTextureView();
            GpuBuffer gpubuffer = this.quadIndices.getBuffer(6);

            try (RenderPass renderpass = RenderSystem.getDevice().createCommandEncoder().createRenderPass(() -> "Sky sun", gputextureview, OptionalInt.empty(), gputextureview1, OptionalDouble.empty()))
            {
                renderpass.setPipeline(RenderPipelines.CELESTIAL);
                RenderSystem.bindDefaultUniforms(renderpass);
                renderpass.setUniform("DynamicTransforms", gpubufferslice);
                renderpass.bindSampler("Sampler0", this.sunTexture.getTextureView());
                renderpass.setVertexBuffer(0, this.sunBuffer);
                renderpass.setIndexBuffer(gpubuffer, this.quadIndices.type());
                renderpass.drawIndexed(0, 0, 6, 1);
            }
            matrix4fstack.popMatrix();
        }

    }

    private void renderMoon(int moonPhase, float rainLevel, PoseStack poseStack)
    {
        if (this.moonTexture != null)
        {
            int i = moonPhase & 7;
            int j = i * 4;
            Matrix4fStack matrix4fstack = RenderSystem.getModelViewStack();
            matrix4fstack.pushMatrix();
            matrix4fstack.mul(poseStack.last().pose());
            matrix4fstack.translate(0.0F, -100.0F, 0.0F);
            matrix4fstack.scale(20.0F, 1.0F, 20.0F);
            GpuBufferSlice gpubufferslice = RenderSystem.getDynamicUniforms().writeTransform(matrix4fstack, new Vector4f(1.0F, 1.0F, 1.0F, rainLevel), new Vector3f(), new Matrix4f(), 0.0F);
            GpuTextureView gputextureview = Minecraft.getInstance().getMainRenderTarget().getColorTextureView();
            GpuTextureView gputextureview1 = Minecraft.getInstance().getMainRenderTarget().getDepthTextureView();
            GpuBuffer gpubuffer = this.quadIndices.getBuffer(6);

            try (RenderPass renderpass = RenderSystem.getDevice().createCommandEncoder().createRenderPass(() -> "Sky moon", gputextureview, OptionalInt.empty(), gputextureview1, OptionalDouble.empty()))
            {
                renderpass.setPipeline(RenderPipelines.CELESTIAL);
                RenderSystem.bindDefaultUniforms(renderpass);
                renderpass.setUniform("DynamicTransforms", gpubufferslice);
                renderpass.bindSampler("Sampler0", this.moonTexture.getTextureView());
                renderpass.setVertexBuffer(0, this.moonBuffer);
                renderpass.setIndexBuffer(gpubuffer, this.quadIndices.type());
                renderpass.drawIndexed(j, 0, 6, 1);
            }
            matrix4fstack.popMatrix();
        }
    }

    private void renderStars(float starBrightness, PoseStack poseStack)
    {
        Matrix4fStack matrix4fstack = RenderSystem.getModelViewStack();
        matrix4fstack.pushMatrix();
        matrix4fstack.mul(poseStack.last().pose());
        RenderPipeline starsRenderPipeline = RenderPipelines.STARS;
        GpuTextureView colorTextureView = Minecraft.getInstance().getMainRenderTarget().getColorTextureView();
        GpuTextureView depthTextureView = Minecraft.getInstance().getMainRenderTarget().getDepthTextureView();
        GpuBuffer gpubuffer = this.starIndices.getBuffer(this.starIndexCount);
        GpuBufferSlice gpubufferslice = RenderSystem.getDynamicUniforms().writeTransform(matrix4fstack, new Vector4f(starBrightness, starBrightness, starBrightness, starBrightness), new Vector3f(), new Matrix4f(), 0.0F);

        try (RenderPass renderpass = RenderSystem.getDevice().createCommandEncoder().createRenderPass(() -> "Stars", colorTextureView, OptionalInt.empty(), depthTextureView, OptionalDouble.empty()))
        {
            renderpass.setPipeline(starsRenderPipeline);
            RenderSystem.bindDefaultUniforms(renderpass);
            renderpass.setUniform("DynamicTransforms", gpubufferslice);
            renderpass.setVertexBuffer(0, this.starBuffer);
            renderpass.setIndexBuffer(gpubuffer, this.starIndices.type());
            renderpass.drawIndexed(0, 0, this.starIndexCount, 1);
        }

        matrix4fstack.popMatrix();
    }

    public void renderSunriseAndSunset(PoseStack poseStack, float sunAngle, int color)
    {
        float f = ARGB.alphaFloat(color);
        if (!(f <= 0.001F))
        {
            float red = ARGB.redFloat(color);
            float green = ARGB.greenFloat(color);
            float blue = ARGB.blueFloat(color);
            poseStack.pushPose();
            poseStack.mulPose(Axis.XP.rotationDegrees(90.0F));
            float f4 = Mth.sin(sunAngle) < 0.0F ? 180.0F : 0.0F;
            poseStack.mulPose(Axis.ZP.rotationDegrees(f4 + 90.0F));
            Matrix4fStack matrix4fstack = RenderSystem.getModelViewStack();
            matrix4fstack.pushMatrix();
            matrix4fstack.mul(poseStack.last().pose());
            matrix4fstack.scale(1.0F, 1.0F, f);
            GpuBufferSlice gpubufferslice = RenderSystem.getDynamicUniforms().writeTransform(matrix4fstack, new Vector4f(red, green, blue, f), new Vector3f(), new Matrix4f(), 0.0F);
            GpuTextureView gputextureview = Minecraft.getInstance().getMainRenderTarget().getColorTextureView();
            GpuTextureView gputextureview1 = Minecraft.getInstance().getMainRenderTarget().getDepthTextureView();

            try (RenderPass renderpass = RenderSystem.getDevice().createCommandEncoder().createRenderPass(() -> "Sunrise sunset", gputextureview, OptionalInt.empty(), gputextureview1, OptionalDouble.empty()))
            {
                renderpass.setPipeline(RenderPipelines.SUNRISE_SUNSET);
                RenderSystem.bindDefaultUniforms(renderpass);
                renderpass.setUniform("DynamicTransforms", gpubufferslice);
                renderpass.setVertexBuffer(0, this.sunriseBuffer);
                renderpass.draw(0, 18);
            }

            matrix4fstack.popMatrix();
            poseStack.popPose();
        }
    }

    @Override public void close()
    {
        this.starBuffer.close();
        this.topSkyBuffer.close();
        this.bottomSkyBuffer.close();
    }
}
