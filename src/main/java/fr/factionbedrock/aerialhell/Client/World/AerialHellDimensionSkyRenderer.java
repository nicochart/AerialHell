package fr.factionbedrock.aerialhell.Client.World;

import com.mojang.blaze3d.systems.RenderSystem;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.Util.SkyRendererHelper;
import net.minecraft.client.gl.VertexBuffer;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import org.joml.Matrix4f;
import org.joml.Matrix4fStack;

public class AerialHellDimensionSkyRenderer implements AutoCloseable
{
	private static final Identifier AERIAL_HELL_SUN_LOCATION = AerialHell.id("textures/environment/aerial_hell_sun.png");
	private static final Identifier AERIAL_HELL_MOON_PHASES_LOCATION = AerialHell.id("textures/environment/aerial_hell_moon_phases.png");
	private final VertexBuffer starBuffer;
	public final VertexBuffer topSkyBuffer;
	private final VertexBuffer bottomSkyBuffer;

	public AerialHellDimensionSkyRenderer()
	{
		starBuffer = VertexBuffer.createAndUpload(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION, SkyRendererHelper::buildStars);
		topSkyBuffer = VertexBuffer.createAndUpload(VertexFormat.DrawMode.TRIANGLE_FAN, VertexFormats.POSITION, vertexConsumer -> SkyRendererHelper.buildSkyDisc(vertexConsumer, 16.0F));
		bottomSkyBuffer = VertexBuffer.createAndUpload(VertexFormat.DrawMode.TRIANGLE_FAN, VertexFormats.POSITION, vertexConsumer -> SkyRendererHelper.buildSkyDisc(vertexConsumer, -16.0F));
	}

	public void renderSkyDisc(float red, float green, float blue)
	{
		RenderSystem.setShaderColor(red, green, blue, 1.0F);
		this.topSkyBuffer.draw(RenderLayer.getSky());
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
	}

	public void renderDarkDisc(MatrixStack poseStack)
	{
		RenderSystem.setShaderColor(0.0F, 0.0F, 0.0F, 1.0F);
		poseStack.push();
		poseStack.translate(0.0F, 12.0F, 0.0F);
		this.bottomSkyBuffer.draw(RenderLayer.getSky());
		poseStack.pop();
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
	}

	public void renderSunMoonAndStars(MatrixStack poseStack, VertexConsumerProvider.Immediate bufferSource, float timeOfDay,int moonPhase, float sunAlpha, float moonAlpha, float starAlpha, Fog fog)
	{
		poseStack.push();
		poseStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-90.0F));
		poseStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(timeOfDay * 360.0F));
		this.renderSun(sunAlpha, bufferSource, poseStack);
		this.renderMoon(moonPhase, moonAlpha, bufferSource, poseStack);
		bufferSource.draw();
		if (starAlpha > 0.0F) {this.renderStars(fog, starAlpha, poseStack);}
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

	private void renderStars(Fog fog, float starBrightness, MatrixStack poseStack)
	{
		Matrix4fStack matrix4fstack = RenderSystem.getModelViewStack();
		matrix4fstack.pushMatrix();
		matrix4fstack.mul(poseStack.peek().getPositionMatrix());
		RenderSystem.setShaderColor(starBrightness, starBrightness, starBrightness, starBrightness);
		RenderSystem.setShaderFog(Fog.DUMMY);
		this.starBuffer.draw(RenderLayer.getStars());
		RenderSystem.setShaderFog(fog);
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		matrix4fstack.popMatrix();
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