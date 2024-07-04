package fr.factionbedrock.aerialhell.Client.EntityRender;

import com.mojang.blaze3d.vertex.PoseStack;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.CrystalSlimeModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.Layers.CrystalSlimeGelAndCrystalLayer;
import fr.factionbedrock.aerialhell.Entity.Monster.CrystalSlimeEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class CrystalSlimeRender extends MobRenderer<CrystalSlimeEntity, CrystalSlimeModel>
{	
	private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/crystal_slime/crystal_slime.png");
	
	public CrystalSlimeRender(EntityRendererProvider.Context context)
	{
		super(context, new CrystalSlimeModel(context.bakeLayer(AerialHellModelLayers.CRYSTAL_SLIME),false), 0.3F);
		this.addLayer(new CrystalSlimeGelAndCrystalLayer(this, new CrystalSlimeModel(context.bakeLayer(AerialHellModelLayers.CRYSTAL_SLIME),true)));
	}
	
	@Override
	protected void scale(CrystalSlimeEntity entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime)
	{
		float f = 0.879F;
		matrixStackIn.scale(f, f, f);
		float f1 = 2; //entitylivingbaseIn.getSlimeSize() isn't working;
		float f2 = 0.0F;
		float f3 = 1.0F / (f2 + 1.0F);
		matrixStackIn.scale(f3 * f1, 1.0F / f3 * f1, f3 * f1);
	}
	
	@Override
	public ResourceLocation getTextureLocation(CrystalSlimeEntity entity)
	{
		return TEXTURE;
	}
}