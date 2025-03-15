package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.HellSpiderSpikeModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.Layers.HellSpiderSpikesLayer;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.HellSpiderRenderState;
import fr.factionbedrock.aerialhell.Entity.Monster.Spider.HellSpiderEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.Shadow.ShadowSpiderEntity;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.SpiderEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.mob.SpiderEntity;
import net.minecraft.util.Identifier;

public class HellSpiderRender<T extends SpiderEntity> extends MobEntityRenderer<T, HellSpiderRenderState, SpiderEntityModel>
{
	private static String hsName = "hell_spider";
	private static final Identifier HELL_SPIDER_TEXTURE = Identifier.of(AerialHell.MODID, "textures/entity/" + hsName + "/" + hsName + ".png");
	private static String csName = "crystal_spider";
	private static final Identifier CRYSTAL_SPIDER_TEXTURE = Identifier.of(AerialHell.MODID, "textures/entity/" + csName + "/" + csName + ".png");
	private static String ssName = "shadow_spider";
	private static final Identifier SHADOW_SPIDER_TEXTURE = Identifier.of(AerialHell.MODID, "textures/entity/" + ssName + "/" + ssName + ".png");
	
	public HellSpiderRender(EntityRendererFactory.Context context)
	{
		super(context, new SpiderEntityModel(context.getPart(EntityModelLayers.SPIDER)), 0.5f);
		this.addFeature(new HellSpiderSpikesLayer<HellSpiderRenderState, SpiderEntityModel>(this, new HellSpiderSpikeModel(context.getPart(AerialHellModelLayers.SPIDER_SPIKE))));
	}

	@Override public HellSpiderRenderState createRenderState() {return new HellSpiderRenderState();}

	@Override public void updateRenderState(T entity, HellSpiderRenderState renderState, float partialTick)
	{
		super.updateRenderState(entity, renderState, partialTick);
		renderState.base_texture = getTexture(entity);
		renderState.layer_texture = HellSpiderSpikesLayer.getTexture(entity);
		renderState.scale = (entity instanceof HellSpiderEntity || entity instanceof ShadowSpiderEntity) ? 0.8F : 1.0F;
	}

	@Override protected void scale(HellSpiderRenderState renderState, MatrixStack matrixStack)
	{
		if (renderState.scale != 1.0F)
		{
			matrixStack.scale(renderState.scale, renderState.scale, renderState.scale);
		}
	}

	@Override public Identifier getTexture(HellSpiderRenderState renderState) {return renderState.base_texture;}

	public Identifier getTexture(T entity)
	{
		if (entity instanceof HellSpiderEntity) {return HELL_SPIDER_TEXTURE;}
		else if (entity instanceof ShadowSpiderEntity) {return SHADOW_SPIDER_TEXTURE;}
		else {return CRYSTAL_SPIDER_TEXTURE;}
	}
}