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
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.material.FogType;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class AerialHellDimensionSkyRenderer
{
	private static VertexBuffer starBuffer;
	//private final VertexFormat skyVertexFormat = DefaultVertexFormat.POSITION;

	private static final ResourceLocation AERIAL_HELL_SUN_LOCATION = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "textures/environment/aerial_hell_sun.png");
	private static final ResourceLocation AERIAL_HELL_MOON_PHASES_LOCATION = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "textures/environment/aerial_hell_moon_phases.png");

	public AerialHellDimensionSkyRenderer()
	{
		generateStars();
	}

	// Copy from net.minecraft.client.renderer.LevelRenderer
	private void generateStars()
	{
		if (starBuffer != null) {starBuffer.close();}

		starBuffer = new VertexBuffer(VertexBuffer.Usage.STATIC);
		starBuffer.bind();
		starBuffer.upload(this.renderStars(Tesselator.getInstance()));
		VertexBuffer.unbind();
	}

	// Copy from net.minecraft.client.renderer.LevelRenderer renderSky(PoseStack matrixStackIn, float partialTicks) function, only overworld part
	@SuppressWarnings("deprecation")
	public static boolean render(ClientLevel level, Matrix4f projectionMatrix, Matrix4f modelViewMatrix, float partialTicks, Camera camera, boolean isFoggy, Runnable setupFog)
	{
		LevelRenderer levelRenderer = Minecraft.getInstance().levelRenderer;
		setupFog.run();
		if (!isFoggy)
		{
			FogType fogtype = camera.getFluidInCamera();
			if (fogtype != FogType.POWDER_SNOW && fogtype != FogType.LAVA && !doesMobEffectBlockSky(camera))
			{
				PoseStack posestack = new PoseStack();
				posestack.mulPose(projectionMatrix);

				Vec3 vec3 = level.getSkyColor(camera.getPosition(), partialTicks);
				float f = (float)vec3.x;
				float f1 = (float)vec3.y;
				float f2 = (float)vec3.z;
				FogRenderer.levelFogColor();
				Tesselator tesselator = Tesselator.getInstance();
				RenderSystem.depthMask(false);
				RenderSystem.setShaderColor(f, f1, f2, 1.0F);
				ShaderInstance shaderinstance = RenderSystem.getShader();
				levelRenderer.skyBuffer.bind();
				levelRenderer.skyBuffer.drawWithShader(posestack.last().pose(), modelViewMatrix, shaderinstance);
				VertexBuffer.unbind();
				RenderSystem.enableBlend();
				//sunrise and sunset
	    		/* No sunrise / sunset effect in sky (don't know how to remove fog effect yet)
				float[] afloat = level.effects().getSunriseColor(level.getTimeOfDay(partialTicks), partialTicks);
				if (afloat != null) {
					RenderSystem.setShader(GameRenderer::getPositionColorShader);
					RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
					posestack.pushPose();
					posestack.mulPose(Axis.XP.rotationDegrees(90.0F));
					float f3 = Mth.sin(level.getSunAngle(partialTicks)) < 0.0F ? 180.0F : 0.0F;
					posestack.mulPose(Axis.ZP.rotationDegrees(f3));
					posestack.mulPose(Axis.ZP.rotationDegrees(90.0F));
					float f4 = afloat[0];
					float f5 = afloat[1];
					float f6 = afloat[2];
					Matrix4f matrix4f = posestack.last().pose();
					BufferBuilder bufferbuilder = tesselator.begin(VertexFormat.Mode.TRIANGLE_FAN, DefaultVertexFormat.POSITION_COLOR);
					bufferbuilder.addVertex(matrix4f, 0.0F, 100.0F, 0.0F).setColor(f4, f5, f6, afloat[3]);
					int i = 16;

					for (int j = 0; j <= 16; j++) {
						float f7 = (float)j * (float) (Math.PI * 2) / 16.0F;
						float f8 = Mth.sin(f7);
						float f9 = Mth.cos(f7);
						bufferbuilder.addVertex(matrix4f, f8 * 120.0F, f9 * 120.0F, -f9 * 40.0F * afloat[3])
								.setColor(afloat[0], afloat[1], afloat[2], 0.0F);
					}

					BufferUploader.drawWithShader(bufferbuilder.buildOrThrow());
					posestack.popPose();
				}
	    		*/
				RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
				posestack.pushPose();
				//float rainReduction = 1.0F - level.getRainLevel(partialTicks); No rain effect on shader color
				//RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, rainReduction);
				posestack.mulPose(Axis.YP.rotationDegrees(-90.0F));
				posestack.mulPose(Axis.XP.rotationDegrees(level.getTimeOfDay(partialTicks) * 360.0F));
				//sun and moon
				float moonBrightness = Math.min(level.getStarBrightness(partialTicks) * 2, 1.0F); //Moon brightness = 0.0F during the day, 1.0F during the night. Using / 0.5F and "min" because StarBrightness is never 1.0F (never above 0.6F) apparently
				float sunBrightness = 1.0F - moonBrightness; //Sun brightness = 1.0F during the day, 0.0F during the night
				RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, sunBrightness); //Sun is visible only during the day
				Matrix4f matrix4f1 = posestack.last().pose();
				float f12 = 30.0F;
				RenderSystem.setShader(GameRenderer::getPositionTexShader);
				RenderSystem.setShaderTexture(0, AERIAL_HELL_SUN_LOCATION);
				BufferBuilder bufferbuilder1 = tesselator.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
				bufferbuilder1.addVertex(matrix4f1, -f12, 100.0F, -f12).setUv(0.0F, 0.0F);
				bufferbuilder1.addVertex(matrix4f1, f12, 100.0F, -f12).setUv(1.0F, 0.0F);
				bufferbuilder1.addVertex(matrix4f1, f12, 100.0F, f12).setUv(1.0F, 1.0F);
				bufferbuilder1.addVertex(matrix4f1, -f12, 100.0F, f12).setUv(0.0F, 1.0F);
				BufferUploader.drawWithShader(bufferbuilder1.buildOrThrow());
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
				bufferbuilder1 = tesselator.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
				bufferbuilder1.addVertex(matrix4f1, -f12, -100.0F, f12).setUv(f15, f16);
				bufferbuilder1.addVertex(matrix4f1, f12, -100.0F, f12).setUv(f13, f16);
				bufferbuilder1.addVertex(matrix4f1, f12, -100.0F, -f12).setUv(f13, f14);
				bufferbuilder1.addVertex(matrix4f1, -f12, -100.0F, -f12).setUv(f15, f14);
				BufferUploader.drawWithShader(bufferbuilder1.buildOrThrow());

				//stars
				float starBrightness = level.getStarBrightness(partialTicks); //* rainReduction; no Rain effect
				if (starBrightness > 0.0F)
				{
					//if (starBrightness > 0.7F) {starBrightness = 0.7F;} //maximum brightness = 0.7F
					RenderSystem.setShaderColor(starBrightness, starBrightness, starBrightness, starBrightness);
					FogRenderer.setupNoFog();
					starBuffer.bind();
					starBuffer.drawWithShader(posestack.last().pose(), modelViewMatrix, GameRenderer.getPositionShader());
					VertexBuffer.unbind();
					setupFog.run();
				}

				RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
				RenderSystem.disableBlend();
				RenderSystem.defaultBlendFunc();
				posestack.popPose();
				RenderSystem.setShaderColor(0.0F, 0.0F, 0.0F, 1.0F);
				/* No fog change when y < sea level
				double d0 = camera.getEntity().getEyePosition(partialTicks).y - level.getLevelData().getHorizonHeight(level);
				if (d0 < 0.0) {
					posestack.pushPose();
					posestack.translate(0.0F, 12.0F, 0.0F);
					levelRenderer.darkBuffer.bind();
					levelRenderer.darkBuffer.drawWithShader(posestack.last().pose(), modelViewMatrix, shaderinstance);
					VertexBuffer.unbind();
					posestack.popPose();
				}
				*/
				RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
				RenderSystem.depthMask(true);
			}
		}
		return true;
	}

	private static boolean doesMobEffectBlockSky(Camera camera)
	{
		return !(camera.getEntity() instanceof LivingEntity livingentity) ? false : livingentity.hasEffect(MobEffects.BLINDNESS) || livingentity.hasEffect(MobEffects.DARKNESS);
	}
	
	// Copy from net.minecraft.client.renderer.WorldRenderer renderStars(BufferBuilder bufferBuilderIn) but with more stars
	@SuppressWarnings("unused")
	private MeshData renderStars(Tesselator tesselator)
	{
		RandomSource rand = RandomSource.create(10842L);
		BufferBuilder bufferbuilder = tesselator.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION);

		//vecx & vecz are angular coordinates, vecy is distance - lens effect (0.0 looks like the cluster is closer, bigger. 1.0 or -1.0 is far away, smaller)
		//vecx is horizontal angle and vecz vertical angle. vecx = 0 -> vertically aligned with the moon
		renderStarCluster(bufferbuilder, 500, new Vector3f(0.3F, -0.7F, 0.25F), new Vector3f(0.5F, 0.5F, 0.5F), 0.1F, 0.6F, rand);
		renderStarCluster(bufferbuilder, 600, new Vector3f(-0.3F, -0.6F, -0.7F), new Vector3f(0.5F, 0.5F, 0.5F), 0.2F, 0.5F, rand);
		renderStarCluster(bufferbuilder, 400, new Vector3f(0.2F, 0.2F, -0.7F), new Vector3f(0.5F, 0.3F, 0.5F), 0.1F, 0.4F, rand);
		renderStarCluster(bufferbuilder, 600, new Vector3f(0.65F, 0.7F, 0.45F), new Vector3f(0.7F, 0.6F, 0.7F), 0.3F, 0.6F, rand);
		renderStarCluster(bufferbuilder, 700, new Vector3f(-0.8F, 0.1F, -0.5F), new Vector3f(0.7F, 0.7F, 0.7F), 0.1F, 0.6F, rand);
		renderStarCluster(bufferbuilder, 500, new Vector3f(0.7F, 0.75F, -0.4F), new Vector3f(0.6F, 0.5F, 0.6F), 0.15F, 0.4F, rand);
		renderScatteredStars(bufferbuilder, 2000, 0.01F, 0.4F, rand);

		return bufferbuilder.buildOrThrow();
	}

	private void renderScatteredStars(BufferBuilder builder, int starNumber, float bigChance, float bigSizeBonus, RandomSource rand)
	{
		for (int i = 0; i < starNumber; i++)
		{
			Vector3f starVec = createRandomStar(rand);
			float starSize = 0.15F + rand.nextFloat() * 0.1F;
			if (rand.nextFloat() < bigChance) {starSize += rand.nextFloat() * bigSizeBonus;}

			float lengthSquared = Mth.lengthSquared(starVec.x, starVec.y, starVec.z);
			if (lengthSquared > 0.010000001F && lengthSquared < 1.0F)
			{
				generateStar(builder, starVec.normalize(100.0F), starSize);
			}
		}
	}

	private void renderStarCluster(BufferBuilder builder, int starNumber, Vector3f origin, Vector3f size, float bigChance, float bigSizeBonus, RandomSource rand)
	{
		for (int i = 0; i < starNumber; i++)
		{
			Vector3f starVec = createRandomStar(origin, size, rand);
			float starSize = 0.15F + rand.nextFloat() * 0.1F;
			if (rand.nextFloat() < bigChance) {starSize += rand.nextFloat() * bigSizeBonus;}

			float lengthSquared = Mth.lengthSquared(starVec.x, starVec.y, starVec.z);
			if (lengthSquared > 0.010000001F && lengthSquared < 1.0F && isStarInsideCluster(origin, starVec, size))
			{
				generateStar(builder, starVec.normalize(100.0F), starSize);
			}
		}
	}

	private void generateStar(BufferBuilder builder, Vector3f normalizedStarVec, float starSize)
	{
		Quaternionf quaternionf = new Quaternionf().rotateTo(new Vector3f(0.0F, 0.0F, -1.0F), normalizedStarVec);
		builder.addVertex(normalizedStarVec.add(new Vector3f(starSize, -starSize, 0.0F).rotate(quaternionf)));
		builder.addVertex(normalizedStarVec.add(new Vector3f(starSize, starSize, 0.0F).rotate(quaternionf)));
		builder.addVertex(normalizedStarVec.add(new Vector3f(-starSize, starSize, 0.0F).rotate(quaternionf)));
		builder.addVertex(normalizedStarVec.add(new Vector3f(-starSize, -starSize, 0.0F).rotate(quaternionf)));
	}

	private Vector3f createRandomStar(Vector3f origin, Vector3f size, RandomSource rand)
	{
		return new Vector3f(origin.x + size.x * (rand.nextFloat() - 0.5F), origin.y + size.y * (rand.nextFloat() - 0.5F), origin.z + size.z * (rand.nextFloat() - 0.5F));
	}

	private Vector3f createRandomStar(RandomSource rand)
	{
		return new Vector3f(rand.nextFloat() * 2.0F - 1.0F, rand.nextFloat() * 2.0F - 1.0F, rand.nextFloat() * 2.0F - 1.0F);
	}

	protected boolean isStarInsideCluster(Vector3f clusterCenter, Vector3f star, Vector3f clusterSize)
	{
		float x = star.x - clusterCenter.x, y = star.y - clusterCenter.y, z = star.z - clusterCenter.z;
		float sizex = clusterSize.x/2, sizey = clusterSize.y/2, sizez = clusterSize.z/2;
		return x*x/(sizex*sizex) + y*y/(sizey*sizey) + z*z/(sizez*sizez) < 1.0F;
	}
}