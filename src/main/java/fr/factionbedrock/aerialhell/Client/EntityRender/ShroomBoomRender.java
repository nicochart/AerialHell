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

//see net.minecraft.client.render.entity.CreeperEntityRenderer
public class ShroomBoomRender extends MobRenderer<ShroomBoomEntity, ShroomBoomModel>
{
	private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/shroomboom/shroomboom.png");
	
	public ShroomBoomRender(EntityRendererProvider.Context context) {super(context, new ShroomBoomModel(context.bakeLayer(AerialHellModelLayers.SHROOMBOOM)), 0.5F);}

	protected void scale(ShroomBoomEntity shroomBoomEntity, PoseStack matrixStack, float f)
	{
		float g = shroomBoomEntity.getSwelling(f);
		float h = 1.0F + Mth.sin(g * 100.0F) * g * 0.01F;
		g = Mth.clamp(g, 0.0F, 1.0F);
		g *= g;
		g *= g;
		float i = (1.0F + g * 0.4F) * h;
		float j = (1.0F + g * 0.1F) / h;
		matrixStack.scale(i, j, i);
	}

	@Override protected float getWhiteOverlayProgress(ShroomBoomEntity livingEntityIn, float partialTicks)
	{
		float f = livingEntityIn.getSwelling(partialTicks);
	    return (int)(f * 10.0F) % 2 == 0 ? 0.0F : Mth.clamp(f, 0.5F, 1.0F);
	}
	
	@Override public ResourceLocation getTextureLocation(ShroomBoomEntity entity) {return TEXTURE;}
}