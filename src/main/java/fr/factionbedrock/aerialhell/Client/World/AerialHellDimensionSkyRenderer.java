package fr.factionbedrock.aerialhell.Client.World;

import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.buffers.GpuBufferSlice;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.systems.RenderPass;
import com.mojang.blaze3d.systems.RenderSystem;

import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.vertex.VertexFormat;
import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.Util.SkyRendererHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.render.*;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.BufferAllocator;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Atlases;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.world.MoonPhase;
import org.joml.Matrix4f;
import org.joml.Matrix4fStack;
import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.OptionalDouble;
import java.util.OptionalInt;

public class AerialHellDimensionSkyRenderer implements AutoCloseable
{
	private static final Identifier AERIAL_HELL_SUN_LOCATION = AerialHell.id("aerial_hell_sun");
	private static final Identifier AERIAL_HELL_MOON_PHASES_LOCATION = AerialHell.id("aerial_hell_moon_phases");
	private final RenderSystem.ShapeIndexBuffer starIndices;
	private final RenderSystem.ShapeIndexBuffer quadIndices;
	private final SpriteAtlasTexture celestialAtlasTexture;
	private final GpuBuffer starBuffer;
	public final GpuBuffer topSkyBuffer;
	private final GpuBuffer bottomSkyBuffer;
	private final GpuBuffer sunVertexBuffer;
	private final GpuBuffer moonPhaseVertexBuffer;
	private final GpuBuffer sunRiseVertexBuffer;
	private int starIndexCount;

	public AerialHellDimensionSkyRenderer()
	{
		this.starIndices = RenderSystem.getSequentialBuffer(VertexFormat.DrawMode.QUADS);
		this.quadIndices = RenderSystem.getSequentialBuffer(VertexFormat.DrawMode.QUADS);
		this.celestialAtlasTexture = MinecraftClient.getInstance().getAtlasManager().getAtlasTexture(Atlases.CELESTIALS);
		this.starBuffer = this.buildStars();
		this.sunVertexBuffer = createSun(this.celestialAtlasTexture);
		this.moonPhaseVertexBuffer = createMoonPhases(this.celestialAtlasTexture);
		this.sunRiseVertexBuffer = this.createSunRise();

		try (BufferAllocator bufferAllocator = BufferAllocator.fixedSized(10 * VertexFormats.POSITION.getVertexSize()))
		{
			BufferBuilder bufferBuilder = new BufferBuilder(bufferAllocator, VertexFormat.DrawMode.TRIANGLE_FAN, VertexFormats.POSITION);
			SkyRendererHelper.buildSkyDisc(bufferBuilder, 16.0F);

			try (BuiltBuffer builtBuffer = bufferBuilder.end())
			{
				this.topSkyBuffer = RenderSystem.getDevice().createBuffer(() -> "Top sky vertex buffer", 32, builtBuffer.getBuffer());
			}

			bufferBuilder = new BufferBuilder(bufferAllocator, VertexFormat.DrawMode.TRIANGLE_FAN, VertexFormats.POSITION);
			SkyRendererHelper.buildSkyDisc(bufferBuilder, -16.0F);

			try (BuiltBuffer builtBuffer = bufferBuilder.end())
			{
				this.bottomSkyBuffer = RenderSystem.getDevice().createBuffer(() -> "Bottom sky vertex buffer", 32, builtBuffer.getBuffer());
			}
		}
	}

	//copies of net.minecraft.client.render.SkyRendering methods of same name
	private static GpuBuffer createSun(SpriteAtlasTexture atlas)
	{
		return createQuadVertexBuffer("Sun quad", atlas.getSprite(AERIAL_HELL_SUN_LOCATION));
	}

	private static GpuBuffer createQuadVertexBuffer(String description, Sprite sprite)
	{
		VertexFormat vertexFormat = VertexFormats.POSITION_TEXTURE;

		GpuBuffer var6;
		try (BufferAllocator bufferAllocator = BufferAllocator.fixedSized(4 * vertexFormat.getVertexSize()))
		{
			BufferBuilder bufferBuilder = new BufferBuilder(bufferAllocator, VertexFormat.DrawMode.QUADS, vertexFormat);
			bufferBuilder.vertex(-1.0F, 0.0F, -1.0F).texture(sprite.getMinU(), sprite.getMinV());
			bufferBuilder.vertex(1.0F, 0.0F, -1.0F).texture(sprite.getMaxU(), sprite.getMinV());
			bufferBuilder.vertex(1.0F, 0.0F, 1.0F).texture(sprite.getMaxU(), sprite.getMaxV());
			bufferBuilder.vertex(-1.0F, 0.0F, 1.0F).texture(sprite.getMinU(), sprite.getMaxV());

			try (BuiltBuffer builtBuffer = bufferBuilder.end())
			{
				var6 = RenderSystem.getDevice().createBuffer(() -> description, 32, builtBuffer.getBuffer());
			}
		}
		return var6;
	}

	private static GpuBuffer createMoonPhases(SpriteAtlasTexture atlas)
	{
		Sprite sprite = atlas.getSprite(AERIAL_HELL_MOON_PHASES_LOCATION);
		float spriteMinU = sprite.getMinU(), spriteMaxU = sprite.getMaxU(), spriteMinV = sprite.getMinV(), spriteMaxV = sprite.getMaxV();
		float uStep = (spriteMaxU - spriteMinU) / 4.0F;
		float vStep = (spriteMaxV - spriteMinV) / 2.0F;
		VertexFormat vertexFormat = VertexFormats.POSITION_TEXTURE;

		GpuBuffer buffer;
		try (BufferAllocator bufferAllocator = BufferAllocator.fixedSized(32 * vertexFormat.getVertexSize()))
		{
			BufferBuilder bufferBuilder = new BufferBuilder(bufferAllocator, VertexFormat.DrawMode.QUADS, vertexFormat);

			for (int k = 0; k < 8; k++)
			{
				int uInd = k % 4; //column index
				int vInd = k / 4 % 2; //row index
				float minU = spriteMinU + uStep * uInd;
				float minV = spriteMinV + vStep * vInd;
				float maxU = minU + uStep;
				float maxV = minV + vStep;
				bufferBuilder.vertex(-1.0F, 0.0F, -1.0F).texture(maxU, maxV);
				bufferBuilder.vertex(1.0F, 0.0F, -1.0F).texture(minU, maxV);
				bufferBuilder.vertex(1.0F, 0.0F, 1.0F).texture(minU, minV);
				bufferBuilder.vertex(-1.0F, 0.0F, 1.0F).texture(maxU, minV);
			}

			try (BuiltBuffer builtBuffer = bufferBuilder.end()) {buffer = RenderSystem.getDevice().createBuffer(() -> "Moon phases", 32, builtBuffer.getBuffer());}
		}
		return buffer;
	}

	private GpuBuffer createSunRise()
	{
		int i = 18;
		int j = VertexFormats.POSITION_COLOR.getVertexSize();

		GpuBuffer var16;
		try (BufferAllocator bufferAllocator = BufferAllocator.fixedSized(18 * j))
		{
			BufferBuilder bufferBuilder = new BufferBuilder(bufferAllocator, VertexFormat.DrawMode.TRIANGLE_FAN, VertexFormats.POSITION_COLOR);
			int k = ColorHelper.getWhite(1.0F);
			int l = ColorHelper.getWhite(0.0F);
			bufferBuilder.vertex(0.0F, 100.0F, 0.0F).color(k);

			for(int m = 0; m <= 16; ++m)
			{
				float f = (float)m * ((float)Math.PI * 2F) / 16.0F;
				float g = MathHelper.sin((double)f);
				float h = MathHelper.cos((double)f);
				bufferBuilder.vertex(g * 120.0F, h * 120.0F, -h * 40.0F).color(l);
			}

			try (BuiltBuffer builtBuffer = bufferBuilder.end())
			{
				var16 = RenderSystem.getDevice().createBuffer(() -> "Sunrise/Sunset fan", 32, builtBuffer.getBuffer());
			}
		}
		return var16;
	}

	protected GpuBuffer buildStars()
	{
		GpuBuffer gpuBuffer;
		try (BufferAllocator bufferAllocator = BufferAllocator.fixedSized(VertexFormats.POSITION.getVertexSize() * 1900 * 4))
		{
			BufferBuilder bufferBuilder = new BufferBuilder(bufferAllocator, VertexFormat.DrawMode.QUADS, VertexFormats.POSITION);

			SkyRendererHelper.buildStars(bufferBuilder);

			try (BuiltBuffer builtBuffer = bufferBuilder.end())
			{
				this.starIndexCount = builtBuffer.getDrawParameters().indexCount();
				gpuBuffer = RenderSystem.getDevice().createBuffer(() -> "Stars vertex buffer", 40, builtBuffer.getBuffer());
			}
		}
		return gpuBuffer;
	}

	public void renderSkyDisc(float red, float green, float blue)
	{
		GpuBufferSlice gpuBufferSlice = RenderSystem.getDynamicUniforms().write(RenderSystem.getModelViewMatrix(), new Vector4f(red, green, blue, 1.0F), new Vector3f(), new Matrix4f());
		GpuTextureView colorTextureView = MinecraftClient.getInstance().getFramebuffer().getColorAttachmentView();
		GpuTextureView depthTextureView = MinecraftClient.getInstance().getFramebuffer().getDepthAttachmentView();

		try (RenderPass renderPass = RenderSystem.getDevice().createCommandEncoder().createRenderPass(() -> "Sky disc", colorTextureView, OptionalInt.empty(), depthTextureView, OptionalDouble.empty()))
		{
			renderPass.setPipeline(RenderPipelines.POSITION_SKY);
			RenderSystem.bindDefaultUniforms(renderPass);
			renderPass.setUniform("DynamicTransforms", gpuBufferSlice);
			renderPass.setVertexBuffer(0, this.topSkyBuffer);
			renderPass.draw(0, 10);
		}
	}

	public void renderDarkDisc()
	{
		Matrix4fStack matrix4fStack = RenderSystem.getModelViewStack();
		matrix4fStack.pushMatrix();
		matrix4fStack.translate(0.0F, 12.0F, 0.0F);
		GpuBufferSlice gpuBufferSlice = RenderSystem.getDynamicUniforms().write(matrix4fStack, new Vector4f(0.0F, 0.0F, 0.0F, 1.0F), new Vector3f(), new Matrix4f());
		GpuTextureView colorTextureView = MinecraftClient.getInstance().getFramebuffer().getColorAttachmentView();
		GpuTextureView depthTextureView = MinecraftClient.getInstance().getFramebuffer().getDepthAttachmentView();

		try (RenderPass renderPass = RenderSystem.getDevice().createCommandEncoder().createRenderPass(() -> "Sky dark", colorTextureView, OptionalInt.empty(), depthTextureView, OptionalDouble.empty()))
		{
			renderPass.setPipeline(RenderPipelines.POSITION_SKY);
			RenderSystem.bindDefaultUniforms(renderPass);
			renderPass.setUniform("DynamicTransforms", gpuBufferSlice);
			renderPass.setVertexBuffer(0, this.bottomSkyBuffer);
			renderPass.draw(0, 10);
		}

		matrix4fStack.popMatrix();
	}

	public void renderSunMoonAndStars(MatrixStack matrices, float sunAngle, float moonAngle, float starAngle, MoonPhase moonPhase, float sunAlpha, float moonAlpha, float starAlpha)
	{
		matrices.push();
		matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-90.0F));
		matrices.push();
		matrices.multiply(RotationAxis.POSITIVE_X.rotation(sunAngle));
		this.renderSun(sunAlpha, matrices);
		matrices.pop();
		matrices.push();
		matrices.multiply(RotationAxis.POSITIVE_X.rotation(moonAngle));
		this.renderMoon(moonPhase, moonAlpha, matrices);
		matrices.pop();
		if (starAlpha > 0.0F)
		{
			matrices.push();
			matrices.multiply(RotationAxis.POSITIVE_X.rotation(starAngle));
			this.renderStars(starAlpha, matrices);
			matrices.pop();
		}
		matrices.pop();
	}

	private void renderSun(float alpha, MatrixStack matrices)
	{
		Matrix4fStack matrix4fStack = RenderSystem.getModelViewStack();
		matrix4fStack.pushMatrix();
		matrix4fStack.mul(matrices.peek().getPositionMatrix());
		matrix4fStack.translate(0.0F, 100.0F, 0.0F);
		matrix4fStack.scale(30.0F, 1.0F, 30.0F);
		GpuBufferSlice gpuBufferSlice = RenderSystem.getDynamicUniforms().write(matrix4fStack, new Vector4f(1.0F, 1.0F, 1.0F, alpha), new Vector3f(), new Matrix4f());
		GpuTextureView gpuTextureView = MinecraftClient.getInstance().getFramebuffer().getColorAttachmentView();
		GpuTextureView gpuTextureView2 = MinecraftClient.getInstance().getFramebuffer().getDepthAttachmentView();
		GpuBuffer gpuBuffer = this.quadIndices.getIndexBuffer(6);

		try (RenderPass renderPass = RenderSystem.getDevice().createCommandEncoder().createRenderPass(() -> "Sky sun", gpuTextureView, OptionalInt.empty(), gpuTextureView2, OptionalDouble.empty()))
		{
			renderPass.setPipeline(RenderPipelines.POSITION_TEX_COLOR_CELESTIAL);
			RenderSystem.bindDefaultUniforms(renderPass);
			renderPass.setUniform("DynamicTransforms", gpuBufferSlice);
			renderPass.bindTexture("Sampler0", this.celestialAtlasTexture.getGlTextureView(), this.celestialAtlasTexture.getSampler());
			renderPass.setVertexBuffer(0, this.sunVertexBuffer);
			renderPass.setIndexBuffer(gpuBuffer, this.quadIndices.getIndexType());
			renderPass.drawIndexed(0, 0, 6, 1);
		}

		matrix4fStack.popMatrix();
	}

	private void renderMoon(MoonPhase moonPhase, float alpha, MatrixStack matrices)
	{
		int i = moonPhase.getIndex() * 4;
		Matrix4fStack matrix4fStack = RenderSystem.getModelViewStack();
		matrix4fStack.pushMatrix();
		matrix4fStack.mul(matrices.peek().getPositionMatrix());
		matrix4fStack.translate(0.0F, 100.0F, 0.0F);
		matrix4fStack.scale(20.0F, 1.0F, 20.0F);
		GpuBufferSlice gpuBufferSlice = RenderSystem.getDynamicUniforms().write(matrix4fStack, new Vector4f(1.0F, 1.0F, 1.0F, alpha), new Vector3f(), new Matrix4f());
		GpuTextureView gpuTextureView = MinecraftClient.getInstance().getFramebuffer().getColorAttachmentView();
		GpuTextureView gpuTextureView2 = MinecraftClient.getInstance().getFramebuffer().getDepthAttachmentView();
		GpuBuffer gpuBuffer = this.quadIndices.getIndexBuffer(6);

		try (RenderPass renderPass = RenderSystem.getDevice().createCommandEncoder().createRenderPass(() -> "Sky moon", gpuTextureView, OptionalInt.empty(), gpuTextureView2, OptionalDouble.empty()))
		{
			renderPass.setPipeline(RenderPipelines.POSITION_TEX_COLOR_CELESTIAL);
			RenderSystem.bindDefaultUniforms(renderPass);
			renderPass.setUniform("DynamicTransforms", gpuBufferSlice);
			renderPass.bindTexture("Sampler0", this.celestialAtlasTexture.getGlTextureView(), this.celestialAtlasTexture.getSampler());
			renderPass.setVertexBuffer(0, this.moonPhaseVertexBuffer);
			renderPass.setIndexBuffer(gpuBuffer, this.quadIndices.getIndexType());
			renderPass.drawIndexed(i, 0, 6, 1);
		}

		matrix4fStack.popMatrix();
	}

	private void renderStars(float brightness, MatrixStack matrices)
	{
		Matrix4fStack matrix4fStack = RenderSystem.getModelViewStack();
		matrix4fStack.pushMatrix();
		matrix4fStack.mul(matrices.peek().getPositionMatrix());
		RenderPipeline starsRenderPipeline = RenderPipelines.POSITION_STARS;
		GpuTextureView colorTextureView = MinecraftClient.getInstance().getFramebuffer().getColorAttachmentView();
		GpuTextureView depthTextureView = MinecraftClient.getInstance().getFramebuffer().getDepthAttachmentView();
		GpuBuffer gpuBuffer = this.starIndices.getIndexBuffer(this.starIndexCount);
		GpuBufferSlice gpuBufferSlice = RenderSystem.getDynamicUniforms().write(matrix4fStack, new Vector4f(brightness, brightness, brightness, brightness), new Vector3f(), new Matrix4f());

		try (RenderPass renderPass = RenderSystem.getDevice().createCommandEncoder().createRenderPass(() -> "Stars", colorTextureView , OptionalInt.empty(), depthTextureView, OptionalDouble.empty()))
		{
			renderPass.setPipeline(starsRenderPipeline);
			RenderSystem.bindDefaultUniforms(renderPass);
			renderPass.setUniform("DynamicTransforms", gpuBufferSlice);
			renderPass.setVertexBuffer(0, this.starBuffer);
			renderPass.setIndexBuffer(gpuBuffer, this.starIndices.getIndexType());
			renderPass.drawIndexed(0, 0, this.starIndexCount, 1);
		}

		matrix4fStack.popMatrix();
	}

	public void renderSunriseAndSunset(MatrixStack matrices, float solarAngle, int color)
	{
		float f = ColorHelper.getAlphaFloat(color);
		if (!(f <= 0.001F))
		{
			matrices.push();
			matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(90.0F));
			float g = MathHelper.sin((double)solarAngle) < 0.0F ? 180.0F : 0.0F;
			matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(g + 90.0F));
			Matrix4fStack matrix4fStack = RenderSystem.getModelViewStack();
			matrix4fStack.pushMatrix();
			matrix4fStack.mul(matrices.peek().getPositionMatrix());
			matrix4fStack.scale(1.0F, 1.0F, f);
			GpuBufferSlice gpuBufferSlice = RenderSystem.getDynamicUniforms().write(matrix4fStack, ColorHelper.toRgbaVector(color), new Vector3f(), new Matrix4f());
			GpuTextureView gpuTextureView = MinecraftClient.getInstance().getFramebuffer().getColorAttachmentView();
			GpuTextureView gpuTextureView2 = MinecraftClient.getInstance().getFramebuffer().getDepthAttachmentView();

			try (RenderPass renderPass = RenderSystem.getDevice().createCommandEncoder().createRenderPass(() -> "Sunrise sunset", gpuTextureView, OptionalInt.empty(), gpuTextureView2, OptionalDouble.empty()))
			{
				renderPass.setPipeline(RenderPipelines.POSITION_COLOR_SUNRISE_SUNSET);
				RenderSystem.bindDefaultUniforms(renderPass);
				renderPass.setUniform("DynamicTransforms", gpuBufferSlice);
				renderPass.setVertexBuffer(0, this.sunRiseVertexBuffer);
				renderPass.draw(0, 18);
			}

			matrix4fStack.popMatrix();
			matrices.pop();
		}
	}

	@Override public void close()
	{
		this.starBuffer.close();
		this.topSkyBuffer.close();
		this.bottomSkyBuffer.close();
	}
}