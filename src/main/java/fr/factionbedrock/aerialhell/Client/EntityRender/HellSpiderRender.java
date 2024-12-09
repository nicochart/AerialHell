package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.HellSpiderSpikeModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.Layers.HellSpiderSpikesLayer;
import fr.factionbedrock.aerialhell.Entity.Monster.Spider.HellSpiderEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.Shadow.ShadowSpiderEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.SpiderEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.mob.SpiderEntity;
import net.minecraft.util.Identifier;

public class HellSpiderRender<T extends SpiderEntity> extends MobEntityRenderer<T, SpiderEntityModel<T>>
{
	private static String hsName = "hell_spider";
	private static final Identifier HELL_SPIDER_TEXTURE = Identifier.of(AerialHell.MODID, "textures/entity/" + hsName + "/" + hsName + ".png");
	private static String csName = "crystal_spider";
	private static final Identifier CRYSTAL_SPIDER_TEXTURE = Identifier.of(AerialHell.MODID, "textures/entity/" + csName + "/" + csName + ".png");
	private static String ssName = "shadow_spider";
	private static final Identifier SHADOW_SPIDER_TEXTURE = Identifier.of(AerialHell.MODID, "textures/entity/" + ssName + "/" + ssName + ".png");
	
	public HellSpiderRender(EntityRendererFactory.Context context)
	{
		super(context, new SpiderEntityModel<>(context.getPart(EntityModelLayers.SPIDER)), 0.5f);
		this.addFeature(new HellSpiderSpikesLayer<T, SpiderEntityModel<T>>(this, new HellSpiderSpikeModel<>(context.getPart(AerialHellModelLayers.SPIDER_SPIKE))));
	}
	
	@Override
	protected void scale(T entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime)
	{
		if (entitylivingbaseIn instanceof HellSpiderEntity || entitylivingbaseIn instanceof ShadowSpiderEntity)
		{
			float f = 0.8F;
			matrixStackIn.scale(f, f, f);
		}
	}
	
	@Override
	public Identifier getTexture(T entity)
	{
		if (entity instanceof HellSpiderEntity) {return HELL_SPIDER_TEXTURE;}
		else if (entity instanceof ShadowSpiderEntity) {return SHADOW_SPIDER_TEXTURE;}
		else {return CRYSTAL_SPIDER_TEXTURE;}
	}
}