package fr.factionbedrock.aerialhell.Client.World;

import com.mojang.blaze3d.vertex.*;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;

import com.mojang.math.Axis;
import fr.factionbedrock.aerialhell.AerialHell;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.*;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;
import org.joml.Matrix4fStack;

public class AerialHellDimensionSkyRenderer
{
	private static VertexBuffer starVBO;
	//private final VertexFormat skyVertexFormat = DefaultVertexFormat.POSITION;

	private static final ResourceLocation AERIAL_HELL_SUN_LOCATION = new ResourceLocation(AerialHell.MODID, "textures/environment/aerial_hell_sun.png");
	private static final ResourceLocation AERIAL_HELL_MOON_PHASES_LOCATION = new ResourceLocation(AerialHell.MODID, "textures/environment/aerial_hell_moon_phases.png");

	public AerialHellDimensionSkyRenderer()
	{
		generateStars();
	}
	
	// Copy from net.minecraft.client.renderer.LevelRenderer
	private void generateStars ()
	{
		Tesselator tesselator = Tesselator.getInstance();
		BufferBuilder bufferbuilder = tesselator.getBuilder();
		RenderSystem.setShader(GameRenderer::getPositionShader);
		if (this.starVBO != null) {
			this.starVBO.close();
		}

		this.starVBO = new VertexBuffer(VertexBuffer.Usage.STATIC);
		BufferBuilder.RenderedBuffer bufferbuilder$renderedbuffer = this.renderStars(bufferbuilder);
		this.starVBO.bind();
		this.starVBO.upload(bufferbuilder$renderedbuffer);
		VertexBuffer.unbind();
	}

	// Copy from net.minecraft.client.renderer.LevelRenderer renderSky(PoseStack matrixStackIn, float partialTicks) function, only overworld part
	@SuppressWarnings("deprecation")
	public static boolean render(ClientLevel level, float partialTicks, Matrix4f modelViewMatrix, Camera camera, Matrix4f projectionMatrix, Runnable setupFog)
	{
		LevelRenderer levelRenderer = Minecraft.getInstance().levelRenderer;
		Matrix4fStack matrixStack = new Matrix4fStack(4);
		matrixStack.set(modelViewMatrix);

		setupFog.run();
		Vec3 skyColorVec = level.getSkyColor(camera.getPosition(), partialTicks);
		float x = (float)skyColorVec.x;
		float y = (float)skyColorVec.y;
		float z = (float)skyColorVec.z;
		FogRenderer.levelFogColor();
		BufferBuilder bufferbuilder = Tesselator.getInstance().getBuilder();
		RenderSystem.depthMask(false);
		RenderSystem.setShaderColor(x, y, z, 1.0F);
		ShaderInstance shaderinstance = RenderSystem.getShader();
		levelRenderer.skyBuffer.bind();
		levelRenderer.skyBuffer.drawWithShader(matrixStack, projectionMatrix, shaderinstance);
		VertexBuffer.unbind();
		RenderSystem.enableBlend();
		//sunrise and sunset
	    /* No sunrise / sunset effect in sky (don't know how to remove fog effect yet)
		float[] afloat = level.effects().getSunriseColor(level.getTimeOfDay(partialTicks), partialTicks);
		if (afloat != null)
		{
			RenderSystem.setShader(GameRenderer::getPositionColorShader);
			RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
			poseStackIn.pushPose();
			poseStackIn.mulPose(Axis.XP.rotationDegrees(90.0F));
			float f2 = Mth.sin(level.getSunAngle(partialTicks)) < 0.0F ? 180.0F : 0.0F;
			poseStackIn.mulPose(Axis.ZP.rotationDegrees(f2));
			poseStackIn.mulPose(Axis.ZP.rotationDegrees(90.0F));
			float f3 = afloat[0];
			float f4 = afloat[1];
			float f5 = afloat[2];
			Matrix4f matrix4f = poseStackIn.last().pose();
			bufferbuilder.begin(VertexFormat.Mode.TRIANGLE_FAN, DefaultVertexFormat.POSITION_COLOR);
			bufferbuilder.vertex(matrix4f, 0.0F, 100.0F, 0.0F).color(f3, f4, f5, afloat[3]).endVertex();
			int i = 16;

			for(int j = 0; j <= 16; ++j) {
				float f6 = (float)j * ((float)Math.PI * 2F) / 16.0F;
				float f7 = Mth.sin(f6);
				float f8 = Mth.cos(f6);
				bufferbuilder.vertex(matrix4f, f7 * 120.0F, f8 * 120.0F, -f8 * 40.0F * afloat[3]).color(afloat[0], afloat[1], afloat[2], 0.0F).endVertex();
			}

			BufferUploader.drawWithShader(bufferbuilder.end());
			poseStackIn.popPose();
		}
		*/
		RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
		matrixStack.pushMatrix();
		//float rainReduction = 1.0F - level.getRainLevel(partialTicks); No rain effect on shader color
		//RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, rainReduction);
		matrixStack.rotate(Axis.YP.rotationDegrees(-90.0F));
		matrixStack.rotate(Axis.XP.rotationDegrees(level.getTimeOfDay(partialTicks) * 360.0F));
		//sun and moon
		float moonBrightness = Math.min(level.getStarBrightness(partialTicks) * 2, 1.0F); //Moon brightness = 0.0F during the day, 1.0F during the night. Using / 0.5F and "min" because StarBrightness is never 1.0F (never above 0.6F) apparently
		float sunBrightness = 1.0F - moonBrightness; //Sun brightness = 1.0F during the day, 0.0F during the night
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, sunBrightness); //Sun is visible only during the day
		float f12 = 30.0F;
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderTexture(0, AERIAL_HELL_SUN_LOCATION);
		bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
		bufferbuilder.vertex(matrixStack, -f12, 100.0F, -f12).uv(0.0F, 0.0F).endVertex();
		bufferbuilder.vertex(matrixStack, f12, 100.0F, -f12).uv(1.0F, 0.0F).endVertex();
		bufferbuilder.vertex(matrixStack, f12, 100.0F, f12).uv(1.0F, 1.0F).endVertex();
		bufferbuilder.vertex(matrixStack, -f12, 100.0F, f12).uv(0.0F, 1.0F).endVertex();
		BufferUploader.drawWithShader(bufferbuilder.end());
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, moonBrightness); //Moon is visible only at night
		f12 = 20.0F;
		RenderSystem.setShaderTexture(0, AERIAL_HELL_MOON_PHASES_LOCATION);
		int k = level.getMoonPhase();
		int l = k % 4;
		int i1 = k / 4 % 2;
		float f13 = (float)(l + 0) / 4.0F;
		float f14 = (float)(i1 + 0) / 2.0F;
		float f15 = (float)(l + 1) / 4.0F;
		float f16 = (float)(i1 + 1) / 2.0F;
		bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
		bufferbuilder.vertex(matrixStack, -f12, -100.0F, f12).uv(f15, f16).endVertex();
		bufferbuilder.vertex(matrixStack, f12, -100.0F, f12).uv(f13, f16).endVertex();
		bufferbuilder.vertex(matrixStack, f12, -100.0F, -f12).uv(f13, f14).endVertex();
		bufferbuilder.vertex(matrixStack, -f12, -100.0F, -f12).uv(f15, f14).endVertex();
		BufferUploader.drawWithShader(bufferbuilder.end());

		//stars
		float starsBrightness = level.getStarBrightness(partialTicks); //* rainReduction; no Rain effect
		if (starsBrightness > 0.0F)
		{
			//if (starBrightness > 0.7F) {starBrightness = 0.7F;} //maximum brightness = 0.7F
			RenderSystem.setShaderColor(starsBrightness, starsBrightness, starsBrightness, starsBrightness);
			FogRenderer.setupNoFog();
			starVBO.bind();
			starVBO.drawWithShader(matrixStack, projectionMatrix, GameRenderer.getPositionShader());
			VertexBuffer.unbind();
			setupFog.run();
		}

		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		RenderSystem.disableBlend();
		RenderSystem.defaultBlendFunc();
		matrixStack.popMatrix();
		RenderSystem.setShaderColor(0.0F, 0.0F, 0.0F, 1.0F);
		/* No fog change when y < sea level
		double d0 = camera.getEntity().getEyePosition(partialTicks).y - level.getLevelData().getHorizonHeight(level);
		if (d0 < 0.0D) {
			poseStackIn.pushPose();
			poseStackIn.translate(0.0F, 12.0F, 0.0F);
			levelRenderer.darkBuffer.bind();
			levelRenderer.darkBuffer.drawWithShader(poseStackIn.last().pose(), projectionMatrix, shaderinstance);
			VertexBuffer.unbind();
			poseStackIn.popPose();
		}
		*/
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		RenderSystem.depthMask(true);
		return true;
	}
	
	// Copy from net.minecraft.client.renderer.WorldRenderer renderStars(BufferBuilder bufferBuilderIn) but with more stars
	@SuppressWarnings("unused")
	private BufferBuilder.RenderedBuffer renderStars(BufferBuilder buffer)
	{
		RandomSource randomsource = RandomSource.create(10842L);
		buffer.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION);

		//from 1500 to 4000
		for(int i = 0; i < 4000; ++i)
		{
			double d0 = (double)(randomsource.nextFloat() * 2.0F - 1.0F);
			double d1 = (double)(randomsource.nextFloat() * 2.0F - 1.0F);
			double d2 = (double)(randomsource.nextFloat() * 2.0F - 1.0F);
			double d3 = (double)(0.15F + randomsource.nextFloat() * 0.1F);
			double d4 = d0 * d0 + d1 * d1 + d2 * d2;
			if (d4 < 1.0D && d4 > 0.01D)
			{
				d4 = 1.0D / Math.sqrt(d4);
				d0 *= d4;
				d1 *= d4;
				d2 *= d4;
				double d5 = d0 * 100.0D;
				double d6 = d1 * 100.0D;
				double d7 = d2 * 100.0D;
				double d8 = Math.atan2(d0, d2);
				double d9 = Math.sin(d8);
				double d10 = Math.cos(d8);
				double d11 = Math.atan2(Math.sqrt(d0 * d0 + d2 * d2), d1);
				double d12 = Math.sin(d11);
				double d13 = Math.cos(d11);
				double d14 = randomsource.nextDouble() * Math.PI * 2.0D;
				double d15 = Math.sin(d14);
				double d16 = Math.cos(d14);

				for(int j = 0; j < 4; ++j)
				{
					double d17 = 0.0D;
					double d18 = (double)((j & 2) - 1) * d3;
					double d19 = (double)((j + 1 & 2) - 1) * d3;
					double d20 = 0.0D;
					double d21 = d18 * d16 - d19 * d15;
					double d22 = d19 * d16 + d18 * d15;
					double d23 = d21 * d12 + 0.0D * d13;
					double d24 = 0.0D * d12 - d21 * d13;
					double d25 = d24 * d9 - d22 * d10;
					double d26 = d22 * d9 + d24 * d10;
					buffer.vertex(d5 + d25, d6 + d23, d7 + d26).endVertex();
				}
			}
		}
		return buffer.end();
	}
}