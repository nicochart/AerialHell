package fr.factionbedrock.aerialhell.Client.EntityRender;

import com.mojang.blaze3d.vertex.PoseStack;
import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.KodamaModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.Layers.KodamaSkinLayer;
import fr.factionbedrock.aerialhell.Entity.Passive.KodamaEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class KodamaRender<T extends KodamaEntity> extends MobRenderer<T, KodamaModel<T>>
{
	private static final ResourceLocation TEXTURE = new ResourceLocation(AerialHell.MODID, "textures/entity/kodama/kodama.png");
	private static boolean SKIN = false, FACE = true;

	public KodamaRender(EntityRendererProvider.Context context)
	{
		super(context, new KodamaModel(context.bakeLayer(AerialHellModelLayers.KODAMA),FACE), 0.2F);
		this.addLayer(new KodamaSkinLayer<>(this, new KodamaModel(context.bakeLayer(AerialHellModelLayers.KODAMA),SKIN)));
	}
	
	@Override public ResourceLocation getTextureLocation(T entity) {return TEXTURE;}

	@Override protected void scale(T entityIn, PoseStack matrixStackIn, float partialTickTime)
	{
		int sizeId = entityIn.getSizeId();
		float f = sizeId == 1 ? 0.45F : sizeId == 2 ? 0.5F : sizeId == 3 ? 0.55F : sizeId == 4 ? 0.6F : 0.65F;
		matrixStackIn.scale(f, f, f);
	}
}