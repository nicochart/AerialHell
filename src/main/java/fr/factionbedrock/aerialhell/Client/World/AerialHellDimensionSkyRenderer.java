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
import net.minecraft.client.util.BufferAllocator;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import org.joml.Matrix4f;
import org.joml.Matrix4fStack;
import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.OptionalDouble;
import java.util.OptionalInt;

public class AerialHellDimensionSkyRenderer implements AutoCloseable
{
	private static final Identifier AERIAL_HELL_SUN_LOCATION = AerialHell.id("textures/environment/aerial_hell_sun.png");
	private static final Identifier AERIAL_HELL_MOON_PHASES_LOCATION = AerialHell.id("textures/environment/aerial_hell_moon_phases.png");
	private final RenderSystem.ShapeIndexBuffer indexBuffer;
	private final GpuBuffer starBuffer;
	public final GpuBuffer topSkyBuffer;
	private final GpuBuffer bottomSkyBuffer;
	private int starIndexCount;

	public AerialHellDimensionSkyRenderer()
	{
		this.indexBuffer = RenderSystem.getSequentialBuffer(VertexFormat.DrawMode.QUADS);
		this.starBuffer = this.buildStars();

		try (BufferAllocator bufferAllocator = BufferAllocator.method_72201(10 * VertexFormats.POSITION.getVertexSize()))
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

	protected GpuBuffer buildStars()
	{
		GpuBuffer gpuBuffer;
		try (BufferAllocator bufferAllocator = BufferAllocator.method_72201(VertexFormats.POSITION.getVertexSize() * 1900 * 4))
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
		GpuBufferSlice gpuBufferSlice = RenderSystem.getDynamicUniforms().write(RenderSystem.getModelViewMatrix(), new Vector4f(red, green, blue, 1.0F), new Vector3f(), new Matrix4f(), 0.0F);
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
		GpuBufferSlice gpuBufferSlice = RenderSystem.getDynamicUniforms().write(matrix4fStack, new Vector4f(0.0F, 0.0F, 0.0F, 1.0F), new Vector3f(), new Matrix4f(), 0.0F);
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

	public void renderSunMoonAndStars(MatrixStack poseStack, VertexConsumerProvider.Immediate bufferSource, float timeOfDay,int moonPhase, float sunAlpha, float moonAlpha, float starAlpha)
	{
		poseStack.push();
		poseStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-90.0F));
		poseStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(timeOfDay * 360.0F));
		this.renderSun(sunAlpha, bufferSource, poseStack);
		this.renderMoon(moonPhase, moonAlpha, bufferSource, poseStack);
		bufferSource.draw();
		if (starAlpha > 0.0F) {this.renderStars(starAlpha, poseStack);}
		poseStack.pop();
	}

	private void renderSun(float alpha, VertexConsumerProvider bufferSource, MatrixStack poseStack)
	{
		float f = 30.0F;
		float f1 = 100.0F;
		VertexConsumer vertexconsumer = bufferSource.getBuffer(RenderLayer.getCelestial(AERIAL_HELL_SUN_LOCATION));
		int i = ColorHelper.getWhite(alpha);
		Matrix4f matrix4f = poseStack.peek().getPositionMatrix();
		vertexconsumer.vertex(matrix4f, -30.0F, 100.0F, -30.0F).texture(0.0F, 0.0F).color(i);
		vertexconsumer.vertex(matrix4f, 30.0F, 100.0F, -30.0F).texture(1.0F, 0.0F).color(i);
		vertexconsumer.vertex(matrix4f, 30.0F, 100.0F, 30.0F).texture(1.0F, 1.0F).color(i);
		vertexconsumer.vertex(matrix4f, -30.0F, 100.0F, 30.0F).texture(0.0F, 1.0F).color(i);
	}

	private void renderMoon(int phase, float alpha, VertexConsumerProvider bufferSource, MatrixStack poseStack)
	{
		float f = 20.0F;
		int i = phase % 4;
		int j = phase / 4 % 2;
		float f1 = (float)(i + 0) / 4.0F;
		float f2 = (float)(j + 0) / 2.0F;
		float f3 = (float)(i + 1) / 4.0F;
		float f4 = (float)(j + 1) / 2.0F;
		float f5 = 100.0F;
		VertexConsumer vertexconsumer = bufferSource.getBuffer(RenderLayer.getCelestial(AERIAL_HELL_MOON_PHASES_LOCATION));
		int k = ColorHelper.getWhite(alpha);
		Matrix4f matrix4f = poseStack.peek().getPositionMatrix();
		vertexconsumer.vertex(matrix4f, -20.0F, -100.0F, 20.0F).texture(f3, f4).color(k);
		vertexconsumer.vertex(matrix4f, 20.0F, -100.0F, 20.0F).texture(f1, f4).color(k);
		vertexconsumer.vertex(matrix4f, 20.0F, -100.0F, -20.0F).texture(f1, f2).color(k);
		vertexconsumer.vertex(matrix4f, -20.0F, -100.0F, -20.0F).texture(f3, f2).color(k);
	}

	private void renderStars(float brightness, MatrixStack matrices)
	{
		Matrix4fStack matrix4fStack = RenderSystem.getModelViewStack();
		matrix4fStack.pushMatrix();
		matrix4fStack.mul(matrices.peek().getPositionMatrix());
		RenderPipeline starsRenderPipeline = RenderPipelines.POSITION_STARS;
		GpuTextureView colorTextureView = MinecraftClient.getInstance().getFramebuffer().getColorAttachmentView();
		GpuTextureView depthTextureView = MinecraftClient.getInstance().getFramebuffer().getDepthAttachmentView();
		GpuBuffer gpuBuffer = this.indexBuffer.getIndexBuffer(this.starIndexCount);
		GpuBufferSlice gpuBufferSlice = RenderSystem.getDynamicUniforms().write(matrix4fStack, new Vector4f(brightness, brightness, brightness, brightness), new Vector3f(), new Matrix4f(), 0.0F);

		try (RenderPass renderPass = RenderSystem.getDevice().createCommandEncoder().createRenderPass(() -> "Stars", colorTextureView , OptionalInt.empty(), depthTextureView, OptionalDouble.empty()))
		{
			renderPass.setPipeline(starsRenderPipeline);
			RenderSystem.bindDefaultUniforms(renderPass);
			renderPass.setUniform("DynamicTransforms", gpuBufferSlice);
			renderPass.setVertexBuffer(0, this.starBuffer);
			renderPass.setIndexBuffer(gpuBuffer, this.indexBuffer.getIndexType());
			renderPass.drawIndexed(0, 0, this.starIndexCount, 1);
		}

		matrix4fStack.popMatrix();
	}

	public void renderSunriseAndSunset(MatrixStack poseStack, VertexConsumerProvider.Immediate bufferSource, float sunAngle, int color)
	{
		poseStack.push();
		poseStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(90.0F));
		float f = MathHelper.sin(sunAngle) < 0.0F ? 180.0F : 0.0F;
		poseStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(f));
		poseStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(90.0F));
		Matrix4f matrix4f = poseStack.peek().getPositionMatrix();
		VertexConsumer vertexconsumer = bufferSource.getBuffer(RenderLayer.getSunriseSunset());
		float f1 = ColorHelper.getAlphaFloat(color);
		vertexconsumer.vertex(matrix4f, 0.0F, 100.0F, 0.0F).color(color);
		int i = ColorHelper.zeroAlpha(color);
		int j = 16;

		for (int k = 0; k <= 16; k++)
		{
			float f2 = (float)k * (float) (Math.PI * 2) / 16.0F;
			float f3 = MathHelper.sin(f2);
			float f4 = MathHelper.cos(f2);
			vertexconsumer.vertex(matrix4f, f3 * 120.0F, f4 * 120.0F, -f4 * 40.0F * f1).color(i);
		}

		poseStack.pop();
	}

	@Override public void close()
	{
		this.starBuffer.close();
		this.topSkyBuffer.close();
		this.bottomSkyBuffer.close();
	}
}