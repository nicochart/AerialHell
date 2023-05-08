package fr.factionbedrock.aerialhell.Client.World;

import com.mojang.blaze3d.vertex.*;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;

import com.mojang.math.Vector3f;
import fr.factionbedrock.aerialhell.AerialHell;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.resources.ResourceLocation;
import com.mojang.math.Matrix4f;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.ISkyRenderHandler;

import java.util.Random;

public class AerialHellDimensionSkyRenderer implements ISkyRenderHandler
{
	private VertexBuffer starVBO;
	//private final VertexFormat skyVertexFormat = DefaultVertexFormat.POSITION;
	
	private static final ResourceLocation AERIAL_HELL_SUN_LOCATION = new ResourceLocation(AerialHell.MODID, "textures/environment/aerial_hell_sun.png");
	private static final ResourceLocation AERIAL_HELL_MOON_PHASES_LOCATION = new ResourceLocation(AerialHell.MODID, "textures/environment/aerial_hell_moon_phases.png");

	public AerialHellDimensionSkyRenderer()
	{
		generateStars();
	}
	
	// Copy from net.minecraft.client.renderer.LevelRenderer
	private void generateStars()
	{
		Tesselator tessellator = Tesselator.getInstance();
		BufferBuilder bufferbuilder = tessellator.getBuilder();

		if (this.starVBO != null) {this.starVBO.close();}
		
		this.starVBO = new VertexBuffer();
		this.renderStars(bufferbuilder);
		bufferbuilder.end();
		this.starVBO.upload(bufferbuilder);
	}

	@Override @OnlyIn(Dist.CLIENT)
	public void render(int ticks, float partialTicks, PoseStack poseStackIn, ClientLevel level, Minecraft mc)
	{
		this.render(level, poseStackIn, RenderSystem.getProjectionMatrix(), mc.gameRenderer.getMainCamera(), partialTicks);
	}

	// Copy from net.minecraft.client.renderer.LevelRenderer renderSky(PoseStack matrixStackIn, float partialTicks) function, only overworld part
	@SuppressWarnings("deprecation")
	@OnlyIn(Dist.CLIENT)
	public void render(ClientLevel level, PoseStack poseStackIn, Matrix4f matrix, Camera camera, float partialTicks)
	{
		LevelRenderer levelRenderer = Minecraft.getInstance().levelRenderer;

		RenderSystem.disableTexture();
		Vec3 skyColorVec = level.getSkyColor(camera.getPosition(), partialTicks);
		float x = (float)skyColorVec.x;
		float y = (float)skyColorVec.y;
		float z = (float)skyColorVec.z;
		FogRenderer.levelFogColor();
		BufferBuilder bufferbuilder = Tesselator.getInstance().getBuilder();
		RenderSystem.depthMask(false);
		RenderSystem.setShaderColor(x, y, z, 1.0F);
		ShaderInstance shaderinstance = RenderSystem.getShader();
		levelRenderer.skyBuffer.drawWithShader(poseStackIn.last().pose(), matrix, shaderinstance);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		//sunrise and sunset
	    /* No sunrise / sunset effect in sky (don't know how to remove fog effect yet)
		float[] afloat = level.effects().getSunriseColor(level.getTimeOfDay(partialTicks), partialTicks);
		if (afloat != null)
		{
			RenderSystem.setShader(GameRenderer::getPositionColorShader);
			RenderSystem.disableTexture();
			RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
			poseStackIn.pushPose();
			poseStackIn.mulPose(Vector3f.XP.rotationDegrees(90.0F));
			float f2 = Mth.sin(level.getSunAngle(partialTicks)) < 0.0F ? 180.0F : 0.0F;
			poseStackIn.mulPose(Vector3f.ZP.rotationDegrees(f2));
			poseStackIn.mulPose(Vector3f.ZP.rotationDegrees(90.0F));
			float f3 = afloat[0];
			float f4 = afloat[1];
			float f5 = afloat[2];
			Matrix4f matrix4f = poseStackIn.last().pose();
			bufferbuilder.begin(VertexFormat.Mode.TRIANGLE_FAN, DefaultVertexFormat.POSITION_COLOR);
			bufferbuilder.vertex(matrix4f, 0.0F, 100.0F, 0.0F).color(f3, f4, f5, afloat[3]).endVertex();
			int i = 16;

			for(int j = 0; j <= 16; ++j)
			{
				float f6 = (float)j * ((float)Math.PI * 2F) / 16.0F;
				float f7 = Mth.sin(f6);
				float f8 = Mth.cos(f6);
				bufferbuilder.vertex(matrix4f, f7 * 120.0F, f8 * 120.0F, -f8 * 40.0F * afloat[3]).color(afloat[0], afloat[1], afloat[2], 0.0F).endVertex();
			}

			bufferbuilder.end();
			BufferUploader.end(bufferbuilder);
			poseStackIn.popPose();
		}
		*/

		RenderSystem.enableTexture();
		RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
		poseStackIn.pushPose();
		//float rainReduction = 1.0F - level.getRainLevel(partialTicks); No rain effect on shader color
		//RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, rainReduction);
		poseStackIn.mulPose(Vector3f.YP.rotationDegrees(-90.0F));
		poseStackIn.mulPose(Vector3f.XP.rotationDegrees(level.getTimeOfDay(partialTicks) * 360.0F));

		//sun and moon
		float moonBrightness = Math.min(level.getStarBrightness(partialTicks) * 2, 1.0F); //Moon brightness = 0.0F during the day, 1.0F during the night. Using / 0.5F and "min" because StarBrightness is never 1.0F (never above 0.6F) apparently
		float sunBrightness = 1.0F - moonBrightness; //Sun brightness = 1.0F during the day, 0.0F during the night
		Matrix4f matrix4f1 = poseStackIn.last().pose();
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, sunBrightness); //Sun is visible only during the day
		float f12 = 30.0F;
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderTexture(0, AERIAL_HELL_SUN_LOCATION);
		bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
		bufferbuilder.vertex(matrix4f1, -f12, 100.0F, -f12).uv(0.0F, 0.0F).endVertex();
		bufferbuilder.vertex(matrix4f1, f12, 100.0F, -f12).uv(1.0F, 0.0F).endVertex();
		bufferbuilder.vertex(matrix4f1, f12, 100.0F, f12).uv(1.0F, 1.0F).endVertex();
		bufferbuilder.vertex(matrix4f1, -f12, 100.0F, f12).uv(0.0F, 1.0F).endVertex();
		bufferbuilder.end();
		BufferUploader.end(bufferbuilder);
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
		bufferbuilder.vertex(matrix4f1, -f12, -100.0F, f12).uv(f15, f16).endVertex();
		bufferbuilder.vertex(matrix4f1, f12, -100.0F, f12).uv(f13, f16).endVertex();
		bufferbuilder.vertex(matrix4f1, f12, -100.0F, -f12).uv(f13, f14).endVertex();
		bufferbuilder.vertex(matrix4f1, -f12, -100.0F, -f12).uv(f15, f14).endVertex();
		bufferbuilder.end();
		BufferUploader.end(bufferbuilder);
		RenderSystem.disableTexture();

		//stars
		float starBrightness = level.getStarBrightness(partialTicks); //* rainReduction; no Rain effect
		if (starBrightness > 0.0F)
		{
			//if (starBrightness > 0.7F) {starBrightness = 0.7F;} //maximum brightness = 0.7F
			RenderSystem.setShaderColor(Math.max(0.0F, starBrightness - 0.1F), starBrightness, starBrightness, 1.0F);
			RenderSystem.setShaderColor(starBrightness, starBrightness, starBrightness, starBrightness);
			FogRenderer.setupNoFog();
			this.starVBO.bind();
			this.starVBO.drawWithShader(poseStackIn.last().pose(), matrix, GameRenderer.getPositionShader());
			VertexBuffer.unbind();
		}

		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		RenderSystem.disableBlend();
		poseStackIn.popPose();
		RenderSystem.disableTexture();
		RenderSystem.setShaderColor(0.0F, 0.0F, 0.0F, 1.0F);
		/* No fog change when y < sea level
		double d0 = camera.getEntity().getEyePosition(partialTicks).y - level.getLevelData().getHorizonHeight(level);
		if (d0 < 0.0D) {
			poseStackIn.pushPose();
			poseStackIn.translate(0.0D, 12.0D, 0.0D);
			levelRenderer.darkBuffer.drawWithShader(poseStackIn.last().pose(), matrix, shaderinstance);
			poseStackIn.popPose();
		}

		if (level.effects().hasGround()) {
			RenderSystem.setShaderColor(x * 0.2F + 0.04F, y * 0.2F + 0.04F, z * 0.6F + 0.1F, 1.0F);
		} else {
			RenderSystem.setShaderColor(x, y, z, 1.0F);
		}
		*/
		RenderSystem.enableTexture();
		RenderSystem.depthMask(true);
	}
	
	// Copy from net.minecraft.client.renderer.WorldRenderer renderStars(BufferBuilder bufferBuilderIn) but with more stars
	@SuppressWarnings("unused")
	private void renderStars(BufferBuilder bufferBuilder)
	{
		Random random = new Random(42812L);
		bufferBuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION);

		//from 1500 to 4000
		for (int i = 0; i < 4000; ++i)
		{
			double d0 = random.nextFloat() * 2.0F - 1.0F;
			double d1 = random.nextFloat() * 2.0F - 1.0F;
			double d2 = random.nextFloat() * 2.0F - 1.0F;
			double d3 = 0.15F + random.nextFloat() * 0.1F;
			double d4 = d0 * d0 + d1 * d1 + d2 * d2;

			if (d4 < 1.0D && d4 > 0.01D)
			{
				d4 = 1.0D / Math.sqrt(d4);
				d0 = d0 * d4;
				d1 = d1 * d4;
				d2 = d2 * d4;
				double d5 = d0 * 100.0D;
				double d6 = d1 * 100.0D;
				double d7 = d2 * 100.0D;
				double d8 = Math.atan2(d0, d2);
				double d9 = Math.sin(d8);
				double d10 = Math.cos(d8);
				double d11 = Math.atan2(Math.sqrt(d0 * d0 + d2 * d2), d1);
				double d12 = Math.sin(d11);
				double d13 = Math.cos(d11);
				double d14 = random.nextDouble() * Math.PI * 2.0D;
				double d15 = Math.sin(d14);
				double d16 = Math.cos(d14);

				for (int j = 0; j < 4; ++j)
				{
					double d17 = 0.0D;
					double d18 = ((j & 2) - 1) * d3;
					double d19 = ((j + 1 & 2) - 1) * d3;
					double d20 = 0.0D;
					double d21 = d18 * d16 - d19 * d15;
					double d22 = d19 * d16 + d18 * d15;
					double d23 = d21 * d12 + 0.0D * d13;
					double d24 = 0.0D * d12 - d21 * d13;
					double d25 = d24 * d9 - d22 * d10;
					double d26 = d22 * d9 + d24 * d10;
					bufferBuilder.vertex(d5 + d25, d6 + d23, d7 + d26).endVertex();
				}
			}
		}
	}
}