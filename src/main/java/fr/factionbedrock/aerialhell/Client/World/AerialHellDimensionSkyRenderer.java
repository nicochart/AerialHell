package fr.factionbedrock.aerialhell.Client.World;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;

import fr.factionbedrock.aerialhell.AerialHell;
import net.minecraft.block.enums.CameraSubmersionType;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.ShaderProgram;
import net.minecraft.client.gl.VertexBuffer;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class AerialHellDimensionSkyRenderer
{
	private static VertexBuffer starBuffer;
	//private final VertexFormat skyVertexFormat = DefaultVertexFormat.POSITION;

	private static final Identifier AERIAL_HELL_SUN_LOCATION = AerialHell.id("textures/environment/aerial_hell_sun.png");
	private static final Identifier AERIAL_HELL_MOON_PHASES_LOCATION = AerialHell.id("textures/environment/aerial_hell_moon_phases.png");

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
		starBuffer.upload(this.renderStars(Tessellator.getInstance()));
		VertexBuffer.unbind();
	}

	// Copy from net.minecraft.client.renderer.WorldRenderer renderSky(PoseStack matrixStackIn, float partialTicks) function, only overworld part
	@SuppressWarnings("deprecation")
	public static boolean render(ClientWorld world, Matrix4f projectionMatrix, Matrix4f modelViewMatrix, float partialTicks, Camera camera, boolean isFoggy, Runnable setupFog)
	{
		WorldRenderer worldRenderer = MinecraftClient.getInstance().worldRenderer;
		setupFog.run();
		if (!isFoggy)
		{
			CameraSubmersionType fogtype = camera.getSubmersionType();
			if (fogtype != CameraSubmersionType.POWDER_SNOW && fogtype != CameraSubmersionType.LAVA && !doesMobEffectBlockSky(camera))
			{
				MatrixStack matrixstack = new MatrixStack();
				matrixstack.multiplyPositionMatrix(projectionMatrix);

				Vec3d vec3 = world.getSkyColor(camera.getPos(), partialTicks);
				float f = (float)vec3.x;
				float f1 = (float)vec3.y;
				float f2 = (float)vec3.z;
				BackgroundRenderer.applyFogColor();
				Tessellator tesselator = Tessellator.getInstance();
				RenderSystem.depthMask(false);
				RenderSystem.setShaderColor(f, f1, f2, 1.0F);
				ShaderProgram shaderProgram = RenderSystem.getShader();
				worldRenderer.lightSkyBuffer.bind();
				worldRenderer.lightSkyBuffer.draw(matrixstack.peek().getPositionMatrix(), modelViewMatrix, shaderProgram);
				VertexBuffer.unbind();
				RenderSystem.enableBlend();
				//sunrise and sunset
	    		/* No sunrise / sunset effect in sky (don't know how to remove fog effect yet)
				float[] afloat = level.effects().getSunriseColor(level.getTimeOfDay(partialTicks), partialTicks);
				if (afloat != null) {
					RenderSystem.setShader(GameRenderer::getPositionColorShader);
					RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
					posestack.push();
					posestack.multiplyPositionMatrix(RotationAxis.POSITIVE_X.rotationDegrees(90.0F));
					float f3 = MathHelper.sin(level.getSunAngle(partialTicks)) < 0.0F ? 180.0F : 0.0F;
					posestack.multiplyPositionMatrix(RotationAxis.POSITIVE_Z.rotationDegrees(f3));
					posestack.multiplyPositionMatrix(RotationAxis.POSITIVE_Z.rotationDegrees(90.0F));
					float f4 = afloat[0];
					float f5 = afloat[1];
					float f6 = afloat[2];
					Matrix4f matrix4f = posestack.peek().getPositionMatrix();
					BufferBuilder bufferbuilder = tesselator.begin(VertexFormat.Mode.TRIANGLE_FAN, DefaultVertexFormat.POSITION_COLOR);
					bufferbuilder.addVertex(matrix4f, 0.0F, 100.0F, 0.0F).setColor(f4, f5, f6, afloat[3]);
					int i = 16;

					for (int j = 0; j <= 16; j++) {
						float f7 = (float)j * (float) (Math.PI * 2) / 16.0F;
						float f8 = MathHelper.sin(f7);
						float f9 = MathHelper.cos(f7);
						bufferbuilder.addVertex(matrix4f, f8 * 120.0F, f9 * 120.0F, -f9 * 40.0F * afloat[3])
								.setColor(afloat[0], afloat[1], afloat[2], 0.0F);
					}

					BufferRenderer.drawWithGlobalProgram(bufferbuilder.buildOrThrow());
					posestack.popPose();
				}
	    		*/
				RenderSystem.blendFuncSeparate(GlStateManager.SrcFactor.SRC_ALPHA, GlStateManager.DstFactor.ONE, GlStateManager.SrcFactor.ONE, GlStateManager.DstFactor.ZERO);
				matrixstack.push();
				//float rainReduction = 1.0F - level.getRainLevel(partialTicks); No rain effect on shader color
				//RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, rainReduction);

				matrixstack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-90.0F));
				matrixstack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(world.getSkyAngle(partialTicks) * 360.0F));
				//sun and moon
				float moonBrightness = Math.min(world.getStarBrightness(partialTicks) * 2, 1.0F); //Moon brightness = 0.0F during the day, 1.0F during the night. Using / 0.5F and "min" because StarBrightness is never 1.0F (never above 0.6F) apparently
				float sunBrightness = 1.0F - moonBrightness; //Sun brightness = 1.0F during the day, 0.0F during the night
				RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, sunBrightness); //Sun is visible only during the day
				Matrix4f matrix4f1 = matrixstack.peek().getPositionMatrix();
				float f12 = 30.0F;
				RenderSystem.setShader(GameRenderer::getPositionTexProgram);
				RenderSystem.setShaderTexture(0, AERIAL_HELL_SUN_LOCATION);
				BufferBuilder bufferbuilder1 = tesselator.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE);
				bufferbuilder1.vertex(matrix4f1, -f12, 100.0F, -f12).texture(0.0F, 0.0F);
				bufferbuilder1.vertex(matrix4f1, f12, 100.0F, -f12).texture(1.0F, 0.0F);
				bufferbuilder1.vertex(matrix4f1, f12, 100.0F, f12).texture(1.0F, 1.0F);
				bufferbuilder1.vertex(matrix4f1, -f12, 100.0F, f12).texture(0.0F, 1.0F);
				BufferRenderer.drawWithGlobalProgram(bufferbuilder1.end());
				RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, moonBrightness); //Moon is visible only at night
				f12 = 20.0F;
				RenderSystem.setShaderTexture(0, AERIAL_HELL_MOON_PHASES_LOCATION);
				int k = world.getMoonPhase();
				int l = k % 4;
				int i1 = k / 4 % 2;
				float f13 = (float)(l + 0) / 4.0F;
				float f14 = (float)(i1 + 0) / 2.0F;
				float f15 = (float)(l + 1) / 4.0F;
				float f16 = (float)(i1 + 1) / 2.0F;
				bufferbuilder1 = tesselator.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE);
				bufferbuilder1.vertex(matrix4f1, -f12, -100.0F, f12).texture(f15, f16);
				bufferbuilder1.vertex(matrix4f1, f12, -100.0F, f12).texture(f13, f16);
				bufferbuilder1.vertex(matrix4f1, f12, -100.0F, -f12).texture(f13, f14);
				bufferbuilder1.vertex(matrix4f1, -f12, -100.0F, -f12).texture(f15, f14);
				BufferRenderer.drawWithGlobalProgram(bufferbuilder1.end());

				//stars
				float starBrightness = world.getStarBrightness(partialTicks); //* rainReduction; no Rain effect
				if (starBrightness > 0.0F)
				{
					//if (starBrightness > 0.7F) {starBrightness = 0.7F;} //maximum brightness = 0.7F
					RenderSystem.setShaderColor(starBrightness, starBrightness, starBrightness, starBrightness);
					BackgroundRenderer.clearFog();
					starBuffer.bind();
					starBuffer.draw(matrixstack.peek().getPositionMatrix(), modelViewMatrix, GameRenderer.getPositionProgram());
					VertexBuffer.unbind();
					setupFog.run();
				}

				RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
				RenderSystem.disableBlend();
				RenderSystem.defaultBlendFunc();
				matrixstack.pop();
				RenderSystem.setShaderColor(0.0F, 0.0F, 0.0F, 1.0F);
				/* No fog change when y < sea level
				double d0 = camera.getEntity().getEyePosition(partialTicks).y - level.getLevelData().getHorizonHeight(level);
				if (d0 < 0.0) {
					posestack.push();
					posestack.translate(0.0F, 12.0F, 0.0F);
					levelRenderer.darkBuffer.bind();
					levelRenderer.darkBuffer.drawWithShader(posestack.peek().getPositionMatrix(), modelViewMatrix, shaderinstance);
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
		return !(camera.getFocusedEntity() instanceof LivingEntity livingentity) ? false : livingentity.hasStatusEffect(StatusEffects.BLINDNESS) || livingentity.hasStatusEffect(StatusEffects.DARKNESS);
	}
	
	// Copy from net.minecraft.client.renderer.WorldRenderer renderStars(BufferBuilder bufferBuilderIn) but with more stars
	@SuppressWarnings("unused")
	private BuiltBuffer renderStars(Tessellator tesselator)
	{
		Random rand = Random.create(10842L);
		BufferBuilder bufferbuilder = tesselator.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION);

		//vecx & vecz are angular coordinates, vecy is distance - lens effect (0.0 looks like the cluster is closer, bigger. 1.0 or -1.0 is far away, smaller)
		//vecx is horizontal angle and vecz vertical angle. vecx = 0 -> vertically aligned with the moon
		renderStarCluster(bufferbuilder, 500, new Vector3f(0.3F, -0.7F, 0.25F), new Vector3f(0.5F, 0.5F, 0.8F), 0.1F, 0.6F, rand);
		renderStarCluster(bufferbuilder, 600, new Vector3f(-0.3F, -0.6F, -0.7F), new Vector3f(0.5F, 0.5F, 0.6F), 0.2F, 0.5F, rand);
		renderStarCluster(bufferbuilder, 400, new Vector3f(0.2F, 0.2F, -0.7F), new Vector3f(0.5F, 0.3F, 0.5F), 0.1F, 0.4F, rand);
		renderStarCluster(bufferbuilder, 600, new Vector3f(0.65F, 0.7F, 0.45F), new Vector3f(0.7F, 0.6F, 0.7F), 0.3F, 0.6F, rand);
		renderStarCluster(bufferbuilder, 700, new Vector3f(-0.8F, 0.1F, -0.5F), new Vector3f(0.8F, 0.7F, 0.7F), 0.1F, 0.6F, rand);
		renderStarCluster(bufferbuilder, 500, new Vector3f(0.7F, 0.75F, -0.4F), new Vector3f(0.6F, 0.5F, 0.6F), 0.15F, 0.4F, rand);
		renderScatteredStars(bufferbuilder, 2000, 0.01F, 0.4F, rand);

		return bufferbuilder.end();
	}

	private void renderScatteredStars(BufferBuilder builder, int starNumber, float bigChance, float bigSizeBonus, Random rand)
	{
		for (int i = 0; i < starNumber; i++)
		{
			Vector3f starVec = createRandomStar(rand);
			float starSize = 0.15F + rand.nextFloat() * 0.1F;
			if (rand.nextFloat() < bigChance) {starSize += rand.nextFloat() * bigSizeBonus;}

			double squaredMagnitude = MathHelper.squaredMagnitude(starVec.x, starVec.y, starVec.z);
			if (squaredMagnitude > 0.010000001F && squaredMagnitude < 1.0F)
			{
				generateStar(builder, starVec.normalize(100.0F), starSize);
			}
		}
	}

	private void renderStarCluster(BufferBuilder builder, int starNumber, Vector3f origin, Vector3f size, float bigChance, float bigSizeBonus, Random rand)
	{
		for (int i = 0; i < starNumber; i++)
		{
			Vector3f starVec = createRandomStar(origin, size, rand);
			float starSize = 0.15F + rand.nextFloat() * 0.1F;
			if (rand.nextFloat() < bigChance) {starSize += rand.nextFloat() * bigSizeBonus;}

			double squaredMagnitude = MathHelper.squaredMagnitude(starVec.x, starVec.y, starVec.z);
			if (squaredMagnitude > 0.010000001F && squaredMagnitude < 1.0F)
			{
				if (isStarInsideCluster(origin, starVec, new Vector3f(size).mul(0.70F)))
				{
					generateStar(builder, starVec.normalize(100.0F), starSize);
				}
				else if (isStarInsideCluster(origin, starVec, size))
				{
					if (rand.nextFloat() < 0.6F) {generateStar(builder, starVec.normalize(100.0F), starSize);}
				}
				else if (isStarInsideCluster(origin, starVec, new Vector3f(size).mul(1.15F)))
				{
					if (rand.nextFloat() < 0.2F) {generateStar(builder, starVec.normalize(100.0F), starSize);}
				}
			}
		}
	}

	private void generateStar(BufferBuilder builder, Vector3f normalizedStarVec, float starSize)
	{
		Quaternionf quaternionf = new Quaternionf().rotateTo(new Vector3f(0.0F, 0.0F, -1.0F), normalizedStarVec);
		builder.vertex(normalizedStarVec.add(new Vector3f(starSize, -starSize, 0.0F).rotate(quaternionf)));
		builder.vertex(normalizedStarVec.add(new Vector3f(starSize, starSize, 0.0F).rotate(quaternionf)));
		builder.vertex(normalizedStarVec.add(new Vector3f(-starSize, starSize, 0.0F).rotate(quaternionf)));
		builder.vertex(normalizedStarVec.add(new Vector3f(-starSize, -starSize, 0.0F).rotate(quaternionf)));
	}

	private Vector3f createRandomStar(Vector3f origin, Vector3f size, Random rand)
	{
		return new Vector3f(origin.x + size.x * (rand.nextFloat() - 0.5F), origin.y + size.y * (rand.nextFloat() - 0.5F), origin.z + size.z * (rand.nextFloat() - 0.5F));
	}

	private Vector3f createRandomStar(Random rand)
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