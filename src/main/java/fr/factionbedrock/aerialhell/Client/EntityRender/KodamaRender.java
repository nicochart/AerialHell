package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.KodamaModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.Layers.KodamaSkinLayer;
import fr.factionbedrock.aerialhell.Entity.Passive.KodamaEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class KodamaRender<T extends KodamaEntity> extends MobEntityRenderer<T, KodamaModel<T>>
{
	private static final Identifier TEXTURE = Identifier.of(AerialHell.MODID, "textures/entity/kodama/kodama.png");
	private static boolean CREATURE_RENDER = false, EMPTY_RENDER = true;

	public KodamaRender(EntityRendererFactory.Context context)
	{
		super(context, new KodamaModel(context.getPart(AerialHellModelLayers.KODAMA), EMPTY_RENDER), 0.0F);
		this.addFeature(new KodamaSkinLayer<>(this, new KodamaModel(context.getPart(AerialHellModelLayers.KODAMA), CREATURE_RENDER)));
	}

	@Override public Identifier getTexture(T entity) {return TEXTURE;}

	@Override protected void scale(T entityIn, MatrixStack matrixStackIn, float partialTickTime)
	{
		int sizeId = entityIn.getSizeId();
		float f = sizeId == 1 ? 0.45F : sizeId == 2 ? 0.5F : sizeId == 3 ? 0.55F : sizeId == 4 ? 0.6F : 0.65F;
		matrixStackIn.scale(f, f, f);
	}
}