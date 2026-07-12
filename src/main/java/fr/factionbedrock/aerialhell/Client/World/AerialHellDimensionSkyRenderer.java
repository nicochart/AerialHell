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
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;
import org.joml.Matrix4fStack;
import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.OptionalDouble;
import java.util.OptionalInt;

//edited copy of net.minecraft.client.renderer.SkyRenderer
public class AerialHellDimensionSkyRenderer implements AutoCloseable
{
	private static final ResourceLocation AERIAL_HELL_SUN_LOCATION = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "textures/environment/celestial/aerial_hell_sun.png");
	private static final ResourceLocation AERIAL_HELL_MOON_PHASES_LOCATION = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "textures/environment/celestial/aerial_hell_moon_phases.png");
	private final RenderSystem.AutoStorageIndexBuffer starIndices;
	private final RenderSystem.AutoStorageIndexBuffer quadIndices;
	@Nullable private AbstractTexture sunTexture;
	@Nullable private AbstractTexture moonTexture;
	private final GpuBuffer sunBuffer;
	private final GpuBuffer moonBuffer;
	private final GpuBuffer starBuffer;
	private final GpuBuffer sunriseBuffer;
	public final GpuBuffer topSkyBuffer;
	private final GpuBuffer bottomSkyBuffer;
	private int starIndexCount;

	public AerialHellDimensionSkyRenderer()
	{
		this.starIndices = RenderSystem.getSequentialBuffer(VertexFormat.Mode.QUADS);
		this.quadIndices = RenderSystem.getSequentialBuffer(VertexFormat.Mode.QUADS);
		this.sunBuffer = this.buildSunQuad();
		this.moonBuffer = this.buildMoonPhases();
		this.starBuffer = this.buildStars();
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

	public void initTextures()
	{
		this.sunTexture = this.getTexture(AERIAL_HELL_SUN_LOCATION);
		this.moonTexture = this.getTexture(AERIAL_HELL_MOON_PHASES_LOCATION);
	}

	private AbstractTexture getTexture(ResourceLocation resourceLocation)
	{
		TextureManager textureManager = Minecraft.getInstance().getTextureManager();
		AbstractTexture abstractTexture = textureManager.getTexture(resourceLocation);
		abstractTexture.setUseMipmaps(false);
		return abstractTexture;
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

	public void renderSkyDisc(float red, float green, float blue)
	{
		GpuBufferSlice gpubufferslice = RenderSystem.getDynamicUniforms().writeTransform(RenderSystem.getModelViewMatrix(), new Vector4f(red, green, blue, 1.0F), new Vector3f(), new Matrix4f(), 0.0F);
		GpuTextureView colorTextureView = Minecraft.getInstance().getMainRenderTarget().getColorTextureView();
		GpuTextureView depthTextureView = Minecraft.getInstance().getMainRenderTarget().getDepthTextureView();

		try (RenderPass renderpass = RenderSystem.getDevice().createCommandEncoder().createRenderPass(() -> "Sky disc", colorTextureView, OptionalInt.empty(), depthTextureView, OptionalDouble.empty()))
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

	public void renderSunMoonAndStars(PoseStack poseStack, float timeOfDay, int moonPhase, float sunAlpha, float moonAlpha, float starAlpha)
	{
		poseStack.pushPose();
		poseStack.mulPose(Axis.YP.rotationDegrees(-90.0F));
		poseStack.mulPose(Axis.XP.rotationDegrees(timeOfDay * 360.0F));
		this.renderSun(sunAlpha, poseStack);
		this.renderMoon(moonPhase, moonAlpha, poseStack);
		if (starAlpha > 0.0F) {this.renderStars(starAlpha, poseStack);}
		poseStack.popPose();
	}

	private void renderSun(float alpha, PoseStack poseStack)
	{
		if (this.sunTexture != null) {
			Matrix4fStack matrix4fStack = RenderSystem.getModelViewStack();
			matrix4fStack.pushMatrix();
			matrix4fStack.mul(poseStack.last().pose());
			matrix4fStack.translate(0.0F, 100.0F, 0.0F);
			matrix4fStack.scale(30.0F, 1.0F, 30.0F);
			GpuBufferSlice gpuBufferSlice = RenderSystem.getDynamicUniforms().writeTransform(matrix4fStack, new Vector4f(1.0F, 1.0F, 1.0F, alpha), new Vector3f(), new Matrix4f(), 0.0F);
			GpuTextureView gpuTextureView = Minecraft.getInstance().getMainRenderTarget().getColorTextureView();
			GpuTextureView gpuTextureView2 = Minecraft.getInstance().getMainRenderTarget().getDepthTextureView();
			GpuBuffer gpuBuffer = this.quadIndices.getBuffer(6);

			try (RenderPass renderPass = RenderSystem.getDevice().createCommandEncoder().createRenderPass(() -> "Sky sun", gpuTextureView, OptionalInt.empty(), gpuTextureView2, OptionalDouble.empty())) {
				renderPass.setPipeline(RenderPipelines.CELESTIAL);
				RenderSystem.bindDefaultUniforms(renderPass);
				renderPass.setUniform("DynamicTransforms", gpuBufferSlice);
				renderPass.bindSampler("Sampler0", this.sunTexture.getTextureView());
				renderPass.setVertexBuffer(0, this.sunBuffer);
				renderPass.setIndexBuffer(gpuBuffer, this.quadIndices.type());
				renderPass.drawIndexed(0, 0, 6, 1);
			}

			matrix4fStack.popMatrix();
		}
	}

	private void renderMoon(int i, float f, PoseStack poseStack)
	{
		if (this.moonTexture != null) {
			int j = i & 7;
			int k = j * 4;
			Matrix4fStack matrix4fStack = RenderSystem.getModelViewStack();
			matrix4fStack.pushMatrix();
			matrix4fStack.mul(poseStack.last().pose());
			matrix4fStack.translate(0.0F, -100.0F, 0.0F);
			matrix4fStack.scale(20.0F, 1.0F, 20.0F);
			GpuBufferSlice gpuBufferSlice = RenderSystem.getDynamicUniforms().writeTransform(matrix4fStack, new Vector4f(1.0F, 1.0F, 1.0F, f), new Vector3f(), new Matrix4f(), 0.0F);
			GpuTextureView gpuTextureView = Minecraft.getInstance().getMainRenderTarget().getColorTextureView();
			GpuTextureView gpuTextureView2 = Minecraft.getInstance().getMainRenderTarget().getDepthTextureView();
			GpuBuffer gpuBuffer = this.quadIndices.getBuffer(6);

			try (RenderPass renderPass = RenderSystem.getDevice().createCommandEncoder().createRenderPass(() -> "Sky moon", gpuTextureView, OptionalInt.empty(), gpuTextureView2, OptionalDouble.empty())) {
				renderPass.setPipeline(RenderPipelines.CELESTIAL);
				RenderSystem.bindDefaultUniforms(renderPass);
				renderPass.setUniform("DynamicTransforms", gpuBufferSlice);
				renderPass.bindSampler("Sampler0", this.moonTexture.getTextureView());
				renderPass.setVertexBuffer(0, this.moonBuffer);
				renderPass.setIndexBuffer(gpuBuffer, this.quadIndices.type());
				renderPass.drawIndexed(k, 0, 6, 1);
			}

			matrix4fStack.popMatrix();
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

	public void renderSunriseAndSunset(PoseStack poseStack, float f, int i) {
		float g = ARGB.alphaFloat(i);
		if (!(g <= 0.001F)) {
			float h = ARGB.redFloat(i);
			float j = ARGB.greenFloat(i);
			float k = ARGB.blueFloat(i);
			poseStack.pushPose();
			poseStack.mulPose(Axis.XP.rotationDegrees(90.0F));
			float l = Mth.sin(f) < 0.0F ? 180.0F : 0.0F;
			poseStack.mulPose(Axis.ZP.rotationDegrees(l + 90.0F));
			Matrix4fStack matrix4fStack = RenderSystem.getModelViewStack();
			matrix4fStack.pushMatrix();
			matrix4fStack.mul(poseStack.last().pose());
			matrix4fStack.scale(1.0F, 1.0F, g);
			GpuBufferSlice gpuBufferSlice = RenderSystem.getDynamicUniforms().writeTransform(matrix4fStack, new Vector4f(h, j, k, g), new Vector3f(), new Matrix4f(), 0.0F);
			GpuTextureView gpuTextureView = Minecraft.getInstance().getMainRenderTarget().getColorTextureView();
			GpuTextureView gpuTextureView2 = Minecraft.getInstance().getMainRenderTarget().getDepthTextureView();

			try (RenderPass renderPass = RenderSystem.getDevice().createCommandEncoder().createRenderPass(() -> "Sunrise sunset", gpuTextureView, OptionalInt.empty(), gpuTextureView2, OptionalDouble.empty())) {
				renderPass.setPipeline(RenderPipelines.SUNRISE_SUNSET);
				RenderSystem.bindDefaultUniforms(renderPass);
				renderPass.setUniform("DynamicTransforms", gpuBufferSlice);
				renderPass.setVertexBuffer(0, this.sunriseBuffer);
				renderPass.draw(0, 18);
			}

			matrix4fStack.popMatrix();
			poseStack.popPose();
		}
	}

	private GpuBuffer buildSunriseFan() {
		int i = 18;
		int j = DefaultVertexFormat.POSITION_COLOR.getVertexSize();

		GpuBuffer var16;
		try (ByteBufferBuilder byteBufferBuilder = ByteBufferBuilder.exactlySized(18 * j)) {
			BufferBuilder bufferBuilder = new BufferBuilder(byteBufferBuilder, VertexFormat.Mode.TRIANGLE_FAN, DefaultVertexFormat.POSITION_COLOR);
			int k = ARGB.white(1.0F);
			int l = ARGB.white(0.0F);
			bufferBuilder.addVertex(0.0F, 100.0F, 0.0F).setColor(k);

			for(int m = 0; m <= 16; ++m) {
				float f = (float)m * ((float)Math.PI * 2F) / 16.0F;
				float g = Mth.sin(f);
				float h = Mth.cos(f);
				bufferBuilder.addVertex(g * 120.0F, h * 120.0F, -h * 40.0F).setColor(l);
			}

			try (MeshData meshData = bufferBuilder.buildOrThrow()) {
				var16 = RenderSystem.getDevice().createBuffer(() -> "Sunrise/Sunset fan", 32, meshData.vertexBuffer());
			}
		}

		return var16;
	}

	private GpuBuffer buildSunQuad() {
		GpuBuffer var5;
		try (ByteBufferBuilder byteBufferBuilder = ByteBufferBuilder.exactlySized(4 * DefaultVertexFormat.POSITION_TEX.getVertexSize())) {
			BufferBuilder bufferBuilder = new BufferBuilder(byteBufferBuilder, VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
			Matrix4f matrix4f = new Matrix4f();
			bufferBuilder.addVertex(matrix4f, -1.0F, 0.0F, -1.0F).setUv(0.0F, 0.0F);
			bufferBuilder.addVertex(matrix4f, 1.0F, 0.0F, -1.0F).setUv(1.0F, 0.0F);
			bufferBuilder.addVertex(matrix4f, 1.0F, 0.0F, 1.0F).setUv(1.0F, 1.0F);
			bufferBuilder.addVertex(matrix4f, -1.0F, 0.0F, 1.0F).setUv(0.0F, 1.0F);

			try (MeshData meshData = bufferBuilder.buildOrThrow()) {
				var5 = RenderSystem.getDevice().createBuffer(() -> "Sun quad", 40, meshData.vertexBuffer());
			}
		}

		return var5;
	}

	private GpuBuffer buildMoonPhases() {
		int i = 8;
		int j = DefaultVertexFormat.POSITION_TEX.getVertexSize();

		GpuBuffer var18;
		try (ByteBufferBuilder byteBufferBuilder = ByteBufferBuilder.exactlySized(32 * j)) {
			BufferBuilder bufferBuilder = new BufferBuilder(byteBufferBuilder, VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
			Matrix4f matrix4f = new Matrix4f();

			for(int k = 0; k < 8; ++k) {
				int l = k % 4;
				int m = k / 4 % 2;
				float f = (float)l / 4.0F;
				float g = (float)m / 2.0F;
				float h = (float)(l + 1) / 4.0F;
				float n = (float)(m + 1) / 2.0F;
				bufferBuilder.addVertex(matrix4f, -1.0F, 0.0F, 1.0F).setUv(h, n);
				bufferBuilder.addVertex(matrix4f, 1.0F, 0.0F, 1.0F).setUv(f, n);
				bufferBuilder.addVertex(matrix4f, 1.0F, 0.0F, -1.0F).setUv(f, g);
				bufferBuilder.addVertex(matrix4f, -1.0F, 0.0F, -1.0F).setUv(h, g);
			}

			try (MeshData meshData = bufferBuilder.buildOrThrow()) {
				var18 = RenderSystem.getDevice().createBuffer(() -> "Moon phases", 32, meshData.vertexBuffer());
			}
		}

		return var18;
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