package fr.factionbedrock.aerialhell.Client.World;

import com.mojang.blaze3d.PrimitiveTopology;
import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.buffers.GpuBufferSlice;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.pipeline.RenderTarget;
import com.mojang.blaze3d.systems.RenderPass;
import com.mojang.blaze3d.systems.RenderSystem;

import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.ByteBufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.MeshData;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexFormat;
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
import org.joml.Matrix4f;
import org.joml.Matrix4fStack;
import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;

public class AerialHellDimensionSkyRenderer implements AutoCloseable
{
	private static final Identifier AERIAL_HELL_SUN_LOCATION = AerialHell.id("aerial_hell_sun");
	private static final Identifier AERIAL_HELL_MOON_PHASES_LOCATION = AerialHell.id("aerial_hell_moon_phases");
	private final RenderSystem.AutoStorageIndexBuffer starIndices;
	private final RenderSystem.AutoStorageIndexBuffer quadIndices;
	private final TextureAtlas celestialAtlasTexture;
	private final GpuBuffer starBuffer;
	public final GpuBuffer topSkyBuffer;
	private final GpuBuffer bottomSkyBuffer;
	private final GpuBuffer sunVertexBuffer;
	private final GpuBuffer moonPhaseVertexBuffer;
	private final GpuBuffer sunRiseVertexBuffer;
	private final RenderTarget renderTarget;
	private int starIndexCount;

	public AerialHellDimensionSkyRenderer(RenderTarget renderTarget)
	{
		this.starIndices = RenderSystem.getSequentialBuffer(PrimitiveTopology.QUADS);
		this.quadIndices = RenderSystem.getSequentialBuffer(PrimitiveTopology.QUADS);
		this.renderTarget = renderTarget;
		this.celestialAtlasTexture = Minecraft.getInstance().getAtlasManager().getAtlasOrThrow(AtlasIds.CELESTIALS);
		this.starBuffer = this.buildStars();
		this.sunVertexBuffer = createSun(this.celestialAtlasTexture);
		this.moonPhaseVertexBuffer = createMoonPhases(this.celestialAtlasTexture);
		this.sunRiseVertexBuffer = this.createSunRise();

		try (ByteBufferBuilder bufferAllocator = ByteBufferBuilder.exactlySized(10 * DefaultVertexFormat.POSITION.getVertexSize()))
		{
			BufferBuilder bufferBuilder = new BufferBuilder(bufferAllocator, PrimitiveTopology.TRIANGLE_FAN, DefaultVertexFormat.POSITION);
			SkyRendererHelper.buildSkyDisc(bufferBuilder, 16.0F);

			try (MeshData builtBuffer = bufferBuilder.buildOrThrow())
			{
				this.topSkyBuffer = RenderSystem.getDevice().createBuffer(() -> "Top sky vertex buffer", 32, builtBuffer.vertexBuffer());
			}

			bufferBuilder = new BufferBuilder(bufferAllocator, PrimitiveTopology.TRIANGLE_FAN, DefaultVertexFormat.POSITION);
			SkyRendererHelper.buildSkyDisc(bufferBuilder, -16.0F);

			try (MeshData builtBuffer = bufferBuilder.buildOrThrow())
			{
				this.bottomSkyBuffer = RenderSystem.getDevice().createBuffer(() -> "Bottom sky vertex buffer", 32, builtBuffer.vertexBuffer());
			}
		}
	}

	//copies of net.minecraft.client.render.SkyRendering methods of same name
	private static GpuBuffer createSun(TextureAtlas atlas)
	{
		return createQuadVertexBuffer("Sun quad", atlas.getSprite(AERIAL_HELL_SUN_LOCATION));
	}

	private static GpuBuffer createQuadVertexBuffer(String description, TextureAtlasSprite sprite)
	{
		VertexFormat vertexFormat = DefaultVertexFormat.POSITION_TEX;

		GpuBuffer var6;
		try (ByteBufferBuilder bufferAllocator = ByteBufferBuilder.exactlySized(4 * vertexFormat.getVertexSize()))
		{
			BufferBuilder bufferBuilder = new BufferBuilder(bufferAllocator, PrimitiveTopology.QUADS, vertexFormat);
			bufferBuilder.addVertex(-1.0F, 0.0F, -1.0F).setUv(sprite.getU0(), sprite.getV0());
			bufferBuilder.addVertex(1.0F, 0.0F, -1.0F).setUv(sprite.getU1(), sprite.getV0());
			bufferBuilder.addVertex(1.0F, 0.0F, 1.0F).setUv(sprite.getU1(), sprite.getV1());
			bufferBuilder.addVertex(-1.0F, 0.0F, 1.0F).setUv(sprite.getU0(), sprite.getV1());

			try (MeshData builtBuffer = bufferBuilder.buildOrThrow())
			{
				var6 = RenderSystem.getDevice().createBuffer(() -> description, 32, builtBuffer.vertexBuffer());
			}
		}
		return var6;
	}

	private static GpuBuffer createMoonPhases(TextureAtlas atlas)
	{
		TextureAtlasSprite sprite = atlas.getSprite(AERIAL_HELL_MOON_PHASES_LOCATION);
		float spriteMinU = sprite.getU0(), spriteMaxU = sprite.getU1(), spriteMinV = sprite.getV0(), spriteMaxV = sprite.getV1();
		float uStep = (spriteMaxU - spriteMinU) / 4.0F;
		float vStep = (spriteMaxV - spriteMinV) / 2.0F;
		VertexFormat vertexFormat = DefaultVertexFormat.POSITION_TEX;

		GpuBuffer buffer;
		try (ByteBufferBuilder bufferAllocator = ByteBufferBuilder.exactlySized(32 * vertexFormat.getVertexSize()))
		{
			BufferBuilder bufferBuilder = new BufferBuilder(bufferAllocator, PrimitiveTopology.QUADS, vertexFormat);

			for (int k = 0; k < 8; k++)
			{
				int uInd = k % 4; //column index
				int vInd = k / 4 % 2; //row index
				float minU = spriteMinU + uStep * uInd;
				float minV = spriteMinV + vStep * vInd;
				float maxU = minU + uStep;
				float maxV = minV + vStep;
				bufferBuilder.addVertex(-1.0F, 0.0F, -1.0F).setUv(maxU, maxV);
				bufferBuilder.addVertex(1.0F, 0.0F, -1.0F).setUv(minU, maxV);
				bufferBuilder.addVertex(1.0F, 0.0F, 1.0F).setUv(minU, minV);
				bufferBuilder.addVertex(-1.0F, 0.0F, 1.0F).setUv(maxU, minV);
			}

			try (MeshData builtBuffer = bufferBuilder.buildOrThrow()) {buffer = RenderSystem.getDevice().createBuffer(() -> "Moon phases", 32, builtBuffer.vertexBuffer());}
		}
		return buffer;
	}

	private GpuBuffer createSunRise()
	{
		int i = 18;
		int j = DefaultVertexFormat.POSITION_COLOR.getVertexSize();

		GpuBuffer var16;
		try (ByteBufferBuilder bufferAllocator = ByteBufferBuilder.exactlySized(18 * j))
		{
			BufferBuilder bufferBuilder = new BufferBuilder(bufferAllocator, PrimitiveTopology.TRIANGLE_FAN, DefaultVertexFormat.POSITION_COLOR);
			int k = ARGB.white(1.0F);
			int l = ARGB.white(0.0F);
			bufferBuilder.addVertex(0.0F, 100.0F, 0.0F).setColor(k);

			for(int m = 0; m <= 16; ++m)
			{
				float f = (float)m * ((float)Math.PI * 2F) / 16.0F;
				float g = Mth.sin((double)f);
				float h = Mth.cos((double)f);
				bufferBuilder.addVertex(g * 120.0F, h * 120.0F, -h * 40.0F).setColor(l);
			}

			try (MeshData builtBuffer = bufferBuilder.buildOrThrow())
			{
				var16 = RenderSystem.getDevice().createBuffer(() -> "Sunrise/Sunset fan", 32, builtBuffer.vertexBuffer());
			}
		}
		return var16;
	}

	protected GpuBuffer buildStars()
	{
		GpuBuffer gpuBuffer;
		try (ByteBufferBuilder bufferAllocator = ByteBufferBuilder.exactlySized(DefaultVertexFormat.POSITION.getVertexSize() * 1900 * 4))
		{
			BufferBuilder bufferBuilder = new BufferBuilder(bufferAllocator, PrimitiveTopology.QUADS, DefaultVertexFormat.POSITION);

			SkyRendererHelper.buildStars(bufferBuilder);

			try (MeshData builtBuffer = bufferBuilder.buildOrThrow())
			{
				this.starIndexCount = builtBuffer.drawState().indexCount();
				gpuBuffer = RenderSystem.getDevice().createBuffer(() -> "Stars vertex buffer", 40, builtBuffer.vertexBuffer());
			}
		}
		return gpuBuffer;
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

	public void renderSunMoonAndStars(PoseStack matrices, float sunAngle, float moonAngle, float starAngle, MoonPhase moonPhase, float sunAlpha, float moonAlpha, float starAlpha)
	{
		matrices.pushPose();
		matrices.mulPose(Axis.YP.rotationDegrees(-90.0F));
		matrices.pushPose();
		matrices.mulPose(Axis.XP.rotation(sunAngle));
		this.renderSun(sunAlpha, matrices);
		matrices.popPose();
		matrices.pushPose();
		matrices.mulPose(Axis.XP.rotation(moonAngle));
		this.renderMoon(moonPhase, moonAlpha, matrices);
		matrices.popPose();
		if (starAlpha > 0.0F)
		{
			matrices.pushPose();
			matrices.mulPose(Axis.XP.rotation(starAngle));
			this.renderStars(starAlpha, matrices);
			matrices.popPose();
		}
		matrices.popPose();
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
			renderPass.bindTexture("Sampler0", this.celestialAtlasTexture.getTextureView(), this.celestialAtlasTexture.getSampler());
			renderPass.setVertexBuffer(0, this.sunVertexBuffer.slice());
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
			renderPass.bindTexture("Sampler0", this.celestialAtlasTexture.getTextureView(), this.celestialAtlasTexture.getSampler());
			renderPass.setVertexBuffer(0, this.moonPhaseVertexBuffer.slice());
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
				renderPass.setVertexBuffer(0, this.sunVertexBuffer.slice());
				renderPass.draw(18, 1, 0, 0);
			}

			modelViewStack.popMatrix();
			poseStack.popPose();
		}
	}

	@Override public void close()
	{
		this.sunVertexBuffer.close();
		this.moonPhaseVertexBuffer.close();
		this.starBuffer.close();
		this.topSkyBuffer.close();
		this.bottomSkyBuffer.close();
		this.sunRiseVertexBuffer.close();
	}
}