package fr.factionbedrock.aerialhell.Client.World;

import com.mojang.blaze3d.PrimitiveTopology;
import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.buffers.GpuBufferSlice;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.pipeline.RenderTarget;
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

import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;

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
    private final RenderTarget renderTarget;
    private int starIndexCount;

    public AerialHellDimensionSkyRenderer(RenderTarget renderTarget)
    {
        this.starIndices = RenderSystem.getSequentialBuffer(PrimitiveTopology.QUADS);
        this.quadIndices = RenderSystem.getSequentialBuffer(PrimitiveTopology.QUADS);
        this.renderTarget = renderTarget;
        this.celestialsAtlas = Minecraft.getInstance().getAtlasManager().getAtlasOrThrow(AtlasIds.CELESTIALS);
        this.starBuffer = this.buildStars();
        this.sunBuffer = buildSunQuad(this.celestialsAtlas);
        this.moonBuffer = buildMoonPhases(this.celestialsAtlas);
        this.sunriseBuffer = this.buildSunriseFan();

        try (ByteBufferBuilder bytebufferbuilder = ByteBufferBuilder.exactlySized(10 * DefaultVertexFormat.POSITION.getVertexSize()))
        {
            BufferBuilder bufferbuilder = new BufferBuilder(bytebufferbuilder, PrimitiveTopology.TRIANGLE_FAN, DefaultVertexFormat.POSITION);
            SkyRendererHelper.buildSkyDisc(bufferbuilder, 16.0F);

            try (MeshData topSkyBufferMeshData = bufferbuilder.buildOrThrow())
            {
                this.topSkyBuffer = RenderSystem.getDevice().createBuffer(() -> "Top sky vertex buffer", 32, topSkyBufferMeshData.vertexBuffer());
            }

            bufferbuilder = new BufferBuilder(bytebufferbuilder, PrimitiveTopology.TRIANGLE_FAN, DefaultVertexFormat.POSITION);
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
            BufferBuilder bufferbuilder = new BufferBuilder(bytebufferbuilder, PrimitiveTopology.QUADS, DefaultVertexFormat.POSITION);

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
            BufferBuilder bufferbuilder = new BufferBuilder(bytebufferbuilder, PrimitiveTopology.QUADS, vertexformat);
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
            BufferBuilder bufferbuilder = new BufferBuilder(bytebufferbuilder, PrimitiveTopology.QUADS, vertexformat);

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
            BufferBuilder bufferbuilder = new BufferBuilder(bytebufferbuilder, PrimitiveTopology.TRIANGLE_FAN, DefaultVertexFormat.POSITION_COLOR);
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

    public void renderSkyDisc(int skyColor)
    {
        GpuBufferSlice dynamicTransforms = RenderSystem.getDynamicUniforms().writeTransform(RenderSystem.getModelViewMatrixCopy(), ARGB.vector4fFromARGB32(skyColor));
        GpuTextureView colorTexture = this.renderTarget.getColorTextureView();
        GpuTextureView depthTexture = this.renderTarget.getDepthTextureView();

        try (RenderPass renderPass = RenderSystem.getDevice().createCommandEncoder().createRenderPass(() -> "Sky disc", colorTexture, Optional.empty(), depthTexture, OptionalDouble.empty()))
        {
            renderPass.setPipeline(RenderPipelines.SKY);
            RenderSystem.bindDefaultUniforms(renderPass);
            renderPass.setUniform("DynamicTransforms", dynamicTransforms);
            renderPass.setVertexBuffer(0, this.topSkyBuffer.slice());
            renderPass.draw(10, 1, 0, 0);
        }
    }

    public void renderDarkDisc()
    {
        Matrix4fStack modelViewStack = RenderSystem.getModelViewStack();
        modelViewStack.pushMatrix();
        modelViewStack.translate(0.0F, 12.0F, 0.0F);
        GpuBufferSlice dynamicTransforms = RenderSystem.getDynamicUniforms().writeTransform(new Matrix4f(modelViewStack), new Vector4f(0.0F, 0.0F, 0.0F, 1.0F));
        GpuTextureView colorTexture = this.renderTarget.getColorTextureView();
        GpuTextureView depthTexture = this.renderTarget.getDepthTextureView();

        try (RenderPass renderPass = RenderSystem.getDevice().createCommandEncoder().createRenderPass(() -> "Sky dark", colorTexture, Optional.empty(), depthTexture, OptionalDouble.empty()))
        {
            renderPass.setPipeline(RenderPipelines.SKY);
            RenderSystem.bindDefaultUniforms(renderPass);
            renderPass.setUniform("DynamicTransforms", dynamicTransforms);
            renderPass.setVertexBuffer(0, this.bottomSkyBuffer.slice());
            renderPass.draw(10, 1, 0, 0);
        }

        modelViewStack.popMatrix();
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

    private void renderSun(float rainBrightness, PoseStack poseStack)
    {
        Matrix4fStack modelViewStack = RenderSystem.getModelViewStack();
        modelViewStack.pushMatrix();
        modelViewStack.mul(poseStack.last().pose());
        modelViewStack.translate(0.0F, 100.0F, 0.0F);
        modelViewStack.scale(30.0F, 1.0F, 30.0F);
        GpuBufferSlice dynamicTransforms = RenderSystem.getDynamicUniforms().writeTransform(new Matrix4f(modelViewStack), new Vector4f(1.0F, 1.0F, 1.0F, rainBrightness));
        GpuTextureView color = this.renderTarget.getColorTextureView();
        GpuTextureView depth = this.renderTarget.getDepthTextureView();
        GpuBuffer indexBuffer = this.quadIndices.getBuffer(6);

        try (RenderPass renderPass = RenderSystem.getDevice().createCommandEncoder().createRenderPass(() -> "Sky sun", color, Optional.empty(), depth, OptionalDouble.empty()))
        {
            renderPass.setPipeline(RenderPipelines.CELESTIAL);
            RenderSystem.bindDefaultUniforms(renderPass);
            renderPass.setUniform("DynamicTransforms", dynamicTransforms);
            renderPass.bindTexture("Sampler0", this.celestialsAtlas.getTextureView(), this.celestialsAtlas.getSampler());
            renderPass.setVertexBuffer(0, this.sunBuffer.slice());
            renderPass.setIndexBuffer(indexBuffer, this.quadIndices.type());
            renderPass.drawIndexed(6, 1, 0, 0, 0);
        }

        modelViewStack.popMatrix();
    }

    private void renderMoon(MoonPhase moonPhase, float rainBrightness, PoseStack poseStack)
    {
        int baseVertex = moonPhase.index() * 4;
        Matrix4fStack modelViewStack = RenderSystem.getModelViewStack();
        modelViewStack.pushMatrix();
        modelViewStack.mul(poseStack.last().pose());
        modelViewStack.translate(0.0F, 100.0F, 0.0F);
        modelViewStack.scale(20.0F, 1.0F, 20.0F);
        GpuBufferSlice dynamicTransforms = RenderSystem.getDynamicUniforms().writeTransform(new Matrix4f(modelViewStack), new Vector4f(1.0F, 1.0F, 1.0F, rainBrightness));
        GpuTextureView color = this.renderTarget.getColorTextureView();
        GpuTextureView depth = this.renderTarget.getDepthTextureView();
        GpuBuffer indexBuffer = this.quadIndices.getBuffer(6);

        try (RenderPass renderPass = RenderSystem.getDevice().createCommandEncoder().createRenderPass(() -> "Sky moon", color, Optional.empty(), depth, OptionalDouble.empty()))
        {
            renderPass.setPipeline(RenderPipelines.CELESTIAL);
            RenderSystem.bindDefaultUniforms(renderPass);
            renderPass.setUniform("DynamicTransforms", dynamicTransforms);
            renderPass.bindTexture("Sampler0", this.celestialsAtlas.getTextureView(), this.celestialsAtlas.getSampler());
            renderPass.setVertexBuffer(0, this.moonBuffer.slice());
            renderPass.setIndexBuffer(indexBuffer, this.quadIndices.type());
            renderPass.drawIndexed(6, 1, 0, baseVertex, 0);
        }

        modelViewStack.popMatrix();
    }

    private void renderStars(float starBrightness, PoseStack poseStack)
    {
        Matrix4fStack modelViewStack = RenderSystem.getModelViewStack();
        modelViewStack.pushMatrix();
        modelViewStack.mul(poseStack.last().pose());
        RenderPipeline renderPipeline = RenderPipelines.STARS;
        GpuTextureView colorTexture = this.renderTarget.getColorTextureView();
        GpuTextureView depthTexture = this.renderTarget.getDepthTextureView();
        GpuBuffer indexBuffer = this.quadIndices.getBuffer(this.starIndexCount);
        GpuBufferSlice dynamicTransforms = RenderSystem.getDynamicUniforms().writeTransform(new Matrix4f(modelViewStack), new Vector4f(starBrightness, starBrightness, starBrightness, starBrightness));

        try (RenderPass renderPass = RenderSystem.getDevice().createCommandEncoder().createRenderPass(() -> "Stars", colorTexture, Optional.empty(), depthTexture, OptionalDouble.empty()))
        {
            renderPass.setPipeline(renderPipeline);
            RenderSystem.bindDefaultUniforms(renderPass);
            renderPass.setUniform("DynamicTransforms", dynamicTransforms);
            renderPass.setVertexBuffer(0, this.starBuffer.slice());
            renderPass.setIndexBuffer(indexBuffer, this.quadIndices.type());
            renderPass.drawIndexed(this.starIndexCount, 1, 0, 0, 0);
        }

        modelViewStack.popMatrix();
    }

    public void renderSunriseAndSunset(PoseStack poseStack, float sunAngle, int sunriseAndSunsetColor)
    {
        float alpha = ARGB.alphaFloat(sunriseAndSunsetColor);
        if (!(alpha <= 0.001F)) {
            poseStack.pushPose();
            poseStack.mulPose(Axis.XP.rotationDegrees(90.0F));
            float angle = Mth.sin((double)sunAngle) < 0.0F ? 180.0F : 0.0F;
            poseStack.mulPose(Axis.ZP.rotationDegrees(angle + 90.0F));
            Matrix4fStack modelViewStack = RenderSystem.getModelViewStack();
            modelViewStack.pushMatrix();
            modelViewStack.mul(poseStack.last().pose());
            modelViewStack.scale(1.0F, 1.0F, alpha);
            GpuBufferSlice dynamicTransforms = RenderSystem.getDynamicUniforms().writeTransform(new Matrix4f(modelViewStack), ARGB.vector4fFromARGB32(sunriseAndSunsetColor));
            GpuTextureView color = this.renderTarget.getColorTextureView();
            GpuTextureView depth = this.renderTarget.getDepthTextureView();

            try (RenderPass renderPass = RenderSystem.getDevice().createCommandEncoder().createRenderPass(() -> "Sunrise sunset", color, Optional.empty(), depth, OptionalDouble.empty()))
            {
                renderPass.setPipeline(RenderPipelines.SUNRISE_SUNSET);
                RenderSystem.bindDefaultUniforms(renderPass);
                renderPass.setUniform("DynamicTransforms", dynamicTransforms);
                renderPass.setVertexBuffer(0, this.sunriseBuffer.slice());
                renderPass.draw(18, 1, 0, 0);
            }

            modelViewStack.popMatrix();
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
