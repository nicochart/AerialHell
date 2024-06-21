package fr.factionbedrock.aerialhell.Client.EntityRender;

import com.mojang.blaze3d.vertex.PoseStack;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.ShroomBoomModel;
import fr.factionbedrock.aerialhell.Entity.Monster.ShroomBoomEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

//see net.minecraft.client.renderer.entity.CreeperRenderer
public class ShroomBoomRender extends MobRenderer<ShroomBoomEntity, ShroomBoomModel>
{	
	private static final ResourceLocation TEXTURE = new ResourceLocation(AerialHell.MODID, "textures/entity/shroomboom/shroomboom.png");
	
	public ShroomBoomRender(EntityRendererProvider.Context context) {super(context, new ShroomBoomModel(context.bakeLayer(AerialHellModelLayers.SHROOMBOOM)), 0.5F);}
	
	@Override protected void scale(ShroomBoomEntity entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime)
	{
		float f = entitylivingbaseIn.getSwelling(partialTickTime);
	    float f1 = 1.0F + Mth.sin(f * 100.0F) * f * 0.01F;
	    f = Mth.clamp(f, 0.0F, 1.0F);
	    f = f * f;
	    f = f * f;
	    float f2 = (1.0F + f * 0.4F) * f1;
	    float f3 = (1.0F + f * 0.1F) / f1;
	    matrixStackIn.scale(f2, f3, f2);
	}

	@Override protected float getWhiteOverlayProgress(ShroomBoomEntity livingEntityIn, float partialTicks)
	{
		float f = livingEntityIn.getSwelling(partialTicks);
	    return (int)(f * 10.0F) % 2 == 0 ? 0.0F : Mth.clamp(f, 0.5F, 1.0F);
	}
	
	@Override public ResourceLocation getTextureLocation(ShroomBoomEntity entity) {return TEXTURE;}
}