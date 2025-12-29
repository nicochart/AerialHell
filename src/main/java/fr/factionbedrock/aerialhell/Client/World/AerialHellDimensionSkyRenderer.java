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
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.data.AtlasIds;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ARGB;
import net.minecraft.util.Mth;
import net.minecraft.world.level.MoonPhase;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.joml.Matrix4f;
import org.joml.Matrix4fStack;
import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.OptionalDouble;
import java.util.OptionalInt;

//TODO write mixin

//edited copy of net.minecraft.client.renderer.SkyRenderer
@OnlyIn(Dist.CLIENT)
public class AerialHellDimensionSkyRenderer implements AutoCloseable
{
    private static final Identifier AERIAL_HELL_SUN_LOCATION = Identifier.fromNamespaceAndPath(AerialHell.MODID, "aerial_hell_sun");
    private static final Identifier AERIAL_HELL_MOON_PHASES_LOCATION = Identifier.fromNamespaceAndPath(AerialHell.MODID, "aerial_hell_moon_phases");
    private final TextureAtlas celestialsAtlas;
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
        this.celestialsAtlas = Minecraft.getInstance().getAtlasManager().getAtlasOrThrow(AtlasIds.CELESTIALS);
        this.starBuffer = this.buildStars();
        this.sunBuffer = buildSunQuad(this.celestialsAtlas);
        this.moonBuffer = buildMoonPhases(this.celestialsAtlas);
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

    //copy of net.minecraft.client.renderer.SkyRenderer methods of same name
    private static GpuBuffer buildSunQuad(TextureAtlas atlas)
    {
        return buildCelestialQuad("Sun quad", atlas.getSprite(AERIAL_HELL_SUN_LOCATION));
    }

    private static GpuBuffer buildCelestialQuad(String name, TextureAtlasSprite atlas)
    {
        VertexFormat vertexformat = DefaultVertexFormat.POSITION_TEX;

        GpuBuffer gpubuffer;
        try (ByteBufferBuilder bytebufferbuilder = ByteBufferBuilder.exactlySized(4 * vertexformat.getVertexSize()))
        {
            BufferBuilder bufferbuilder = new BufferBuilder(bytebufferbuilder, VertexFormat.Mode.QUADS, vertexformat);
            bufferbuilder.addVertex(-1.0F, 0.0F, -1.0F).setUv(atlas.getU0(), atlas.getV0());
            bufferbuilder.addVertex(1.0F, 0.0F, -1.0F).setUv(atlas.getU1(), atlas.getV0());
            bufferbuilder.addVertex(1.0F, 0.0F, 1.0F).setUv(atlas.getU1(), atlas.getV1());
            bufferbuilder.addVertex(-1.0F, 0.0F, 1.0F).setUv(atlas.getU0(), atlas.getV1());

            try (MeshData meshdata = bufferbuilder.buildOrThrow())
            {
                gpubuffer = RenderSystem.getDevice().createBuffer(() -> name, 32, meshdata.vertexBuffer());
            }
        }

        return gpubuffer;
    }

    private static GpuBuffer buildMoonPhases(TextureAtlas atlas)
    {
        TextureAtlasSprite sprite = atlas.getSprite(AERIAL_HELL_MOON_PHASES_LOCATION);
        float spriteMinU = sprite.getU0(), spriteMaxU = sprite.getU1(), spriteMinV = sprite.getV0(), spriteMaxV = sprite.getV1();
        float uStep = (spriteMaxU - spriteMinU) / 4.0F;
        float vStep = (spriteMaxV - spriteMinV) / 2.0F;
        VertexFormat vertexformat = DefaultVertexFormat.POSITION_TEX;

        GpuBuffer gpubuffer;
        try (ByteBufferBuilder bytebufferbuilder = ByteBufferBuilder.exactlySized(32 * vertexformat.getVertexSize()))
        {
            BufferBuilder bufferbuilder = new BufferBuilder(bytebufferbuilder, VertexFormat.Mode.QUADS, vertexformat);

            for (int k = 0; k < 8; k++)
            {
                int uInd = k % 4; //column index
                int vInd = k / 4 % 2; //row index
                float minU = spriteMinU + uStep * uInd;
                float minV = spriteMinV + vStep * vInd;
                float maxU = minU + uStep;
                float maxV = minV + vStep;
                bufferbuilder.addVertex(-1.0F, 0.0F, -1.0F).setUv(maxU, maxV);
                bufferbuilder.addVertex(1.0F, 0.0F, -1.0F).setUv(minU, maxV);
                bufferbuilder.addVertex(1.0F, 0.0F, 1.0F).setUv(minU, minV);
                bufferbuilder.addVertex(-1.0F, 0.0F, 1.0F).setUv(maxU, minV);
            }

            try (MeshData meshdata = bufferbuilder.buildOrThrow()) {gpubuffer = RenderSystem.getDevice().createBuffer(() -> "Moon phases", 32, meshdata.vertexBuffer());}
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
        GpuBufferSlice gpubufferslice = RenderSystem.getDynamicUniforms().writeTransform(RenderSystem.getModelViewMatrix(), new Vector4f(red, green, blue, 1.0F), new Vector3f(), new Matrix4f());
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
        GpuBufferSlice gpubufferslice = RenderSystem.getDynamicUniforms().writeTransform(matrix4fstack, new Vector4f(0.0F, 0.0F, 0.0F, 1.0F), new Vector3f(), new Matrix4f());
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

    public void renderSunMoonAndStars(PoseStack poseStack, float sunAngle, float moonAngle, float starAngle, MoonPhase moonPhase, float sunAlpha, float moonAlpha, float starAlpha)
    {
        poseStack.pushPose();
        poseStack.mulPose(Axis.YP.rotationDegrees(-90.0F));
        poseStack.pushPose();
        poseStack.mulPose(Axis.XP.rotation(sunAngle));
        this.renderSun(sunAlpha, poseStack);
        poseStack.popPose();
        poseStack.pushPose();
        poseStack.mulPose(Axis.XP.rotation(moonAngle));
        this.renderMoon(moonPhase, moonAlpha, poseStack);
        poseStack.popPose();
        if (starAlpha > 0.0F)
        {
            poseStack.pushPose();
            poseStack.mulPose(Axis.XP.rotation(starAngle));
            this.renderStars(starAlpha, poseStack);
            poseStack.popPose();
        }

        poseStack.popPose();
    }

    private void renderSun(float alpha, PoseStack poseStack)
    {
        Matrix4fStack matrix4fstack = RenderSystem.getModelViewStack();
        matrix4fstack.pushMatrix();
        matrix4fstack.mul(poseStack.last().pose());
        matrix4fstack.translate(0.0F, 100.0F, 0.0F);
        matrix4fstack.scale(30.0F, 1.0F, 30.0F);
        GpuBufferSlice gpubufferslice = RenderSystem.getDynamicUniforms().writeTransform(matrix4fstack, new Vector4f(1.0F, 1.0F, 1.0F, alpha), new Vector3f(), new Matrix4f());
        GpuTextureView gputextureview = Minecraft.getInstance().getMainRenderTarget().getColorTextureView();
        GpuTextureView gputextureview1 = Minecraft.getInstance().getMainRenderTarget().getDepthTextureView();
        GpuBuffer gpubuffer = this.quadIndices.getBuffer(6);

        try (RenderPass renderpass = RenderSystem.getDevice().createCommandEncoder().createRenderPass(() -> "Sky sun", gputextureview, OptionalInt.empty(), gputextureview1, OptionalDouble.empty()))
        {
            renderpass.setPipeline(RenderPipelines.CELESTIAL);
            RenderSystem.bindDefaultUniforms(renderpass);
            renderpass.setUniform("DynamicTransforms", gpubufferslice);
            renderpass.bindTexture("Sampler0", this.celestialsAtlas.getTextureView(), this.celestialsAtlas.getSampler());
            renderpass.setVertexBuffer(0, this.sunBuffer);
            renderpass.setIndexBuffer(gpubuffer, this.quadIndices.type());
            renderpass.drawIndexed(0, 0, 6, 1);
        }

        matrix4fstack.popMatrix();
    }

    private void renderMoon(MoonPhase moonPhase, float rainBrightness, PoseStack poseStack)
    {
        int i = moonPhase.index() * 4;
        Matrix4fStack matrix4fstack = RenderSystem.getModelViewStack();
        matrix4fstack.pushMatrix();
        matrix4fstack.mul(poseStack.last().pose());
        matrix4fstack.translate(0.0F, 100.0F, 0.0F);
        matrix4fstack.scale(20.0F, 1.0F, 20.0F);
        GpuBufferSlice gpubufferslice = RenderSystem.getDynamicUniforms().writeTransform(matrix4fstack, new Vector4f(1.0F, 1.0F, 1.0F, rainBrightness), new Vector3f(), new Matrix4f());
        GpuTextureView gputextureview = Minecraft.getInstance().getMainRenderTarget().getColorTextureView();
        GpuTextureView gputextureview1 = Minecraft.getInstance().getMainRenderTarget().getDepthTextureView();
        GpuBuffer gpubuffer = this.quadIndices.getBuffer(6);

        try (RenderPass renderpass = RenderSystem.getDevice().createCommandEncoder().createRenderPass(() -> "Sky moon", gputextureview, OptionalInt.empty(), gputextureview1, OptionalDouble.empty()))
        {
            renderpass.setPipeline(RenderPipelines.CELESTIAL);
            RenderSystem.bindDefaultUniforms(renderpass);
            renderpass.setUniform("DynamicTransforms", gpubufferslice);
            renderpass.bindTexture("Sampler0", this.celestialsAtlas.getTextureView(), this.celestialsAtlas.getSampler());
            renderpass.setVertexBuffer(0, this.moonBuffer);
            renderpass.setIndexBuffer(gpubuffer, this.quadIndices.type());
            renderpass.drawIndexed(i, 0, 6, 1);
        }

        matrix4fstack.popMatrix();
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
        GpuBufferSlice gpubufferslice = RenderSystem.getDynamicUniforms().writeTransform(matrix4fstack, new Vector4f(starBrightness, starBrightness, starBrightness, starBrightness), new Vector3f(), new Matrix4f());

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
            GpuBufferSlice gpubufferslice = RenderSystem.getDynamicUniforms().writeTransform(matrix4fstack, new Vector4f(red, green, blue, f), new Vector3f(), new Matrix4f());
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
        this.sunBuffer.close();
        this.moonBuffer.close();
        this.starBuffer.close();
        this.topSkyBuffer.close();
        this.bottomSkyBuffer.close();
        this.sunriseBuffer.close();
    }
}
