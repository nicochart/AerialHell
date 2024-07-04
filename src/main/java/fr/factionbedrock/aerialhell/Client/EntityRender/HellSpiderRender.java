package fr.factionbedrock.aerialhell.Client.EntityRender;

import com.mojang.blaze3d.vertex.PoseStack;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.HellSpiderSpikeModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.Layers.HellSpiderSpikesLayer;
import fr.factionbedrock.aerialhell.Entity.Monster.Spider.HellSpiderEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.Shadow.ShadowSpiderEntity;
import net.minecraft.client.model.SpiderModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.resources.ResourceLocation;

public class HellSpiderRender<T extends Spider> extends MobRenderer<T, SpiderModel<T>>
{
	private static String hsName = "hell_spider";
	private static final ResourceLocation HELL_SPIDER_TEXTURE = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/" + hsName + "/" + hsName + ".png");
	private static String csName = "crystal_spider";
	private static final ResourceLocation CRYSTAL_SPIDER_TEXTURE = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/" + csName + "/" + csName + ".png");
	private static String ssName = "shadow_spider";
	private static final ResourceLocation SHADOW_SPIDER_TEXTURE = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/" + ssName + "/" + ssName + ".png");
	
	public HellSpiderRender(EntityRendererProvider.Context context)
	{
		super(context, new SpiderModel<>(context.bakeLayer(ModelLayers.SPIDER)), 0.5f);
		this.addLayer(new HellSpiderSpikesLayer<T, SpiderModel<T>>(this, new HellSpiderSpikeModel<>(context.bakeLayer(AerialHellModelLayers.SPIDER_SPIKE))));
	}
	
	@Override
	protected void scale(T entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime)
	{
		if (entitylivingbaseIn instanceof HellSpiderEntity || entitylivingbaseIn instanceof ShadowSpiderEntity)
		{
			float f = 0.8F;
			matrixStackIn.scale(f, f, f);
		}
	}
	
	@Override
	public ResourceLocation getTextureLocation(T entity)
	{
		if (entity instanceof HellSpiderEntity) {return HELL_SPIDER_TEXTURE;}
		else if (entity instanceof ShadowSpiderEntity) {return SHADOW_SPIDER_TEXTURE;}
		else {return CRYSTAL_SPIDER_TEXTURE;}
	}
}