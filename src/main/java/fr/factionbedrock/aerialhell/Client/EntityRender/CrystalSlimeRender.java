package fr.factionbedrock.aerialhell.Client.EntityRender;

import com.mojang.blaze3d.matrix.MatrixStack;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.CrystalSlimeModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.Layers.CrystalSlimeGelAndCrystalLayer;
import fr.factionbedrock.aerialhell.Entity.Monster.CrystalSlimeEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CrystalSlimeRender extends MobRenderer<CrystalSlimeEntity, CrystalSlimeModel>
{	
	private static final ResourceLocation TEXTURE = new ResourceLocation(AerialHell.MODID, "textures/entity/crystal_slime/crystal_slime.png");
	
	public CrystalSlimeRender(EntityRendererManager rendererManager)
	{
		super(rendererManager, new CrystalSlimeModel(false), 0.3F);
		this.addLayer(new CrystalSlimeGelAndCrystalLayer(this));
	}
	
	@Override
	protected void preRenderCallback(CrystalSlimeEntity entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime)
	{
		float f = 0.879F;
		matrixStackIn.scale(f, f, f);
		float f1 = 2; //entitylivingbaseIn.getSlimeSize() isn't working;
		float f2 = 0.0F;
		float f3 = 1.0F / (f2 + 1.0F);
		matrixStackIn.scale(f3 * f1, 1.0F / f3 * f1, f3 * f1);
	}
	
	@Override
	public ResourceLocation getEntityTexture(CrystalSlimeEntity entity)
	{
		return TEXTURE;
	}
}