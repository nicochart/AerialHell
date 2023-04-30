package fr.factionbedrock.aerialhell.Client.World;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;

import fr.factionbedrock.aerialhell.AerialHell;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.multiplayer.ClientLevel;
import com.mojang.blaze3d.vertex.BufferBuilder;
import net.minecraft.client.renderer.FogRenderer;
import com.mojang.blaze3d.vertex.Tesselator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.WorldVertexBufferUploader;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexBuffer;
import net.minecraft.resources.ResourceLocation;
import com.mojang.math.Matrix4f;
import com.mojang.math.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.ISkyRenderHandler;

import java.util.Random;

import javax.annotation.Nullable;

public class AerialHellDimensionSkyRenderer implements ISkyRenderHandler
{
	private VertexBuffer starVBO;
	@Nullable
	public VertexBuffer skyVBO;
	@Nullable
	public VertexBuffer sky2VBO;
	private final VertexFormat skyVertexFormat = DefaultVertexFormats.POSITION;
	
	private static final ResourceLocation AERIAL_HELL_SUN_TEXTURES = new ResourceLocation(AerialHell.MODID, "textures/environment/aerial_hell_sun.png");
	private static final ResourceLocation AERIAL_HELL_MOON_PHASES_TEXTURES = new ResourceLocation(AerialHell.MODID, "textures/environment/aerial_hell_moon_phases.png");

	public AerialHellDimensionSkyRenderer()
	{
		generateStars();
	}
	
	// Copy from net.minecraft.client.renderer.WorldRenderer
	private void generateStars()
	{
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder bufferbuilder = tessellator.getBuffer();

		if (this.starVBO != null) {this.starVBO.close();}
		
		this.starVBO = new VertexBuffer(this.skyVertexFormat);
		this.renderStars(bufferbuilder);
		bufferbuilder.finishDrawing();
		this.starVBO.upload(bufferbuilder);
	}

	// Copy from net.minecraft.client.renderer.WorldRenderer renderSky(PoseStack matrixStackIn, float partialTicks) function, only overworld part
	@SuppressWarnings("deprecation")
	@Override
	@OnlyIn(Dist.CLIENT)
	public void render(int ticks, float partialTicks, PoseStack matrixStackIn, ClientLevel world, Minecraft mc)
	{
		WorldRenderer worldRenderer = mc.worldRenderer;
		TextureManager textureManager = mc.getTextureManager();
		
		RenderSystem.disableTexture();
		Vector3d vector3d = world.getSkyColor(mc.gameRenderer.getActiveRenderInfo().getBlockPos(), partialTicks);
	    float x = (float)vector3d.x; float y = (float)vector3d.y; float z = (float)vector3d.z;
	    FogRenderer.applyFog();
	    BufferBuilder bufferbuilder = Tessellator.getInstance().getBuffer();
	    RenderSystem.depthMask(false);
	    RenderSystem.enableFog();
	    RenderSystem.color3f(x, y, z);
	    
	    worldRenderer.skyVBO.bindBuffer();
		this.skyVertexFormat.setupBufferState(0L);
		worldRenderer.skyVBO.draw(matrixStackIn.last().pose(), 7);
		VertexBuffer.unbindBuffer();
		this.skyVertexFormat.clearBufferState();
		
	    RenderSystem.disableFog();
	    RenderSystem.disableAlphaTest();
	    RenderSystem.enableBlend();
	    RenderSystem.defaultBlendFunc();
	    //sunrise and sunset
	    /* No sunrise / sunset effect in sky (don't know how to remove fog effect yet)
	    float[] afloat = world.func_239132_a_().func_230492_a_(world.func_242415_f(partialTicks), partialTicks);
	    if (afloat != null)
	    {
	    	RenderSystem.disableTexture();
	        RenderSystem.shadeModel(7425); //overworld value : 7425 ; cool value : 3612
	        matrixStackIn.push();
	        matrixStackIn.rotate(Vector3f.XP.rotationDegrees(90.0F));
	        float f3 = Mth.sin(world.getCelestialAngleRadians(partialTicks)) < 0.0F ? 180.0F : 0.0F;
	        matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(f3));
	        matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(90.0F));
	        float f4 = afloat[0];
	        float f5 = afloat[1];
	        float f6 = afloat[2];
	        Matrix4f matrix4f = matrixStackIn.last().pose();
	        bufferbuilder.begin(6, DefaultVertexFormats.POSITION_COLOR);
	        bufferbuilder.pos(matrix4f, 0.0F, 100.0F, 0.0F).color(f4, f5, f6, afloat[3]).endVertex();
	
	        for(int j = 0; j <= 16; ++j)
	        {
	        	float f7 = (float)j * ((float)Math.PI * 2F) / 16.0F;
	        	float f8 = Mth.sin(f7);
	        	float f9 = Mth.cos(f7);
	        	bufferbuilder.pos(matrix4f, f8 * 120.0F, f9 * 120.0F, -f9 * 40.0F * afloat[3]).color(afloat[0], afloat[1], afloat[2], 0.0F).endVertex();
	        }
	
	        bufferbuilder.finishDrawing();
	        WorldVertexBufferUploader.draw(bufferbuilder);
	        matrixStackIn.pop();
	        RenderSystem.shadeModel(7424);
	    }
	    */
	    
	    RenderSystem.enableTexture();    
	    RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
	    matrixStackIn.push();
	    //No rain effect on color
	    //float rainReduction = 1.0F - world.getRainStrength(partialTicks);
	    //RenderSystem.color4f(1.0F, 1.0F, 1.0F, rainReduction);
	    matrixStackIn.rotate(Vector3f.YP.rotationDegrees(-90.0F));
	    matrixStackIn.rotate(Vector3f.XP.rotationDegrees(world.func_242415_f(partialTicks) * 360.0F));	    
	    
	    //sun and moon
	    float moonBrightness = Math.min(world.getStarBrightness(partialTicks) * 2, 1.0F); //Moon brightness = 0.0F during the day, 1.0F during the night. Using / 0.5F and "min" because StarBrightness is never 1.0F (never above 0.6F) apparently
	    float sunBrightness = 1.0F - moonBrightness; //Sun brightness = 1.0F during the day, 0.0F during the night
	    Matrix4f matrix4f1 = matrixStackIn.last().pose();
	    RenderSystem.color4f(1.0F, 1.0F, 1.0F, sunBrightness); //Sun is visible only during the day
	    float f12 = 30.0F;
	    textureManager.bindTexture(AERIAL_HELL_SUN_TEXTURES);
	    bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
	    bufferbuilder.pos(matrix4f1, -f12, 100.0F, -f12).tex(0.0F, 0.0F).endVertex();
	    bufferbuilder.pos(matrix4f1, f12, 100.0F, -f12).tex(1.0F, 0.0F).endVertex();
	    bufferbuilder.pos(matrix4f1, f12, 100.0F, f12).tex(1.0F, 1.0F).endVertex();
	    bufferbuilder.pos(matrix4f1, -f12, 100.0F, f12).tex(0.0F, 1.0F).endVertex();
	    bufferbuilder.finishDrawing();
	    WorldVertexBufferUploader.draw(bufferbuilder);
	    RenderSystem.color4f(1.0F, 1.0F, 1.0F, moonBrightness); //Moon is visible only at night
	    f12 = 20.0F;
	    textureManager.bindTexture(AERIAL_HELL_MOON_PHASES_TEXTURES);
	    int k = world.getMoonPhase();
	    int l = k % 4;
	    int i1 = k / 4 % 2;
	    float f13 = (float)(l + 0) / 4.0F;
	    float f14 = (float)(i1 + 0) / 2.0F;
	    float f15 = (float)(l + 1) / 4.0F;
	    float f16 = (float)(i1 + 1) / 2.0F;
	    bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
	    bufferbuilder.pos(matrix4f1, -f12, -100.0F, f12).tex(f15, f16).endVertex();
	    bufferbuilder.pos(matrix4f1, f12, -100.0F, f12).tex(f13, f16).endVertex();
	    bufferbuilder.pos(matrix4f1, f12, -100.0F, -f12).tex(f13, f14).endVertex();
	    bufferbuilder.pos(matrix4f1, -f12, -100.0F, -f12).tex(f15, f14).endVertex();
	    bufferbuilder.finishDrawing();
	    WorldVertexBufferUploader.draw(bufferbuilder);
	    
	    RenderSystem.disableTexture();
	    //stars
	    float starBrightness = world.getStarBrightness(partialTicks); //* rainReduction; no Rain effect
	    if (starBrightness > 0.0F)
	    {
	       //if (starBrightness > 0.7F) {starBrightness = 0.7F;} //maximum brightness = 0.7F
	       RenderSystem.color4f(Math.max(0.0F, starBrightness - 0.1F), starBrightness, starBrightness, 1.0F); //RenderSystem.color4f(0.5F, 1.0F, 0.7F, starBrightness) = green stars
	       //RenderSystem.color4f(starBrightness, starBrightness, starBrightness, starBrightness); //vanilla
	       this.starVBO.bindBuffer();
	       this.skyVertexFormat.setupBufferState(0L);
	       this.starVBO.draw(matrixStackIn.last().pose(), 7);
	       VertexBuffer.unbindBuffer();
	       this.skyVertexFormat.clearBufferState();
	    }
	    
	    RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
	    RenderSystem.disableBlend();
	    RenderSystem.enableAlphaTest();
	    RenderSystem.enableFog();
	    matrixStackIn.pop();
	    RenderSystem.disableTexture();
	    RenderSystem.color3f(0.0F, 0.0F, 0.0F);
	    /* No fog change when y < sea level
	    double d0 = mc.player.getEyePosition(partialTicks).y - world.getWorldInfo().getVoidFogHeight(); //if editing sea level may cause problem
	    if (d0 < 0.0D) 
	    {
	       matrixStackIn.push();
	       matrixStackIn.translate(0.0D, 12.0D, 0.0D);
	       worldRenderer.sky2VBO.bindBuffer();
	       this.skyVertexFormat.setupBufferState(0L);
	       worldRenderer.sky2VBO.draw(matrixStackIn.last().pose(), 7);
	       VertexBuffer.unbindBuffer();
	       this.skyVertexFormat.clearBufferState();
	       matrixStackIn.pop();
	    }*/
	
	    if (world.func_239132_a_().func_239216_b_()) {RenderSystem.color3f(x * 0.2F + 0.04F, y * 0.2F + 0.04F, z * 0.6F + 0.1F);}
	    else {RenderSystem.color3f(x, y, z);}
	
	    RenderSystem.enableTexture();
	    RenderSystem.depthMask(true);
	    RenderSystem.disableFog();
	}
	
	// Copy from net.minecraft.client.renderer.WorldRenderer renderStars(BufferBuilder bufferBuilderIn) but with more stars
	@SuppressWarnings("unused")
	private void renderStars(BufferBuilder bufferBuilder)
	{
		Random random = new Random(42812L);
		bufferBuilder.begin(7, DefaultVertexFormats.POSITION);

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
					bufferBuilder.pos(d5 + d25, d6 + d23, d7 + d26).endVertex();
				}
			}
		}
	}
}