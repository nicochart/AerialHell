package fr.factionbedrock.aerialhell.Client.EntityRender;

import com.mojang.blaze3d.vertex.PoseStack;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.HellSpiderSpikeModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.Layers.HellSpiderSpikesLayer;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.HellSpiderRenderState;
import fr.factionbedrock.aerialhell.Entity.Monster.Spider.HellSpiderEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.Shadow.ShadowSpiderEntity;
import net.minecraft.client.model.SpiderModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.resources.ResourceLocation;

public class HellSpiderRender<T extends Spider> extends MobRenderer<T, HellSpiderRenderState, SpiderModel>
{
	private static String hsName = "hell_spider";
	private static final ResourceLocation HELL_SPIDER_TEXTURE = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/" + hsName + "/" + hsName + ".png");
	private static String csName = "crystal_spider";
	private static final ResourceLocation CRYSTAL_SPIDER_TEXTURE = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/" + csName + "/" + csName + ".png");
	private static String ssName = "shadow_spider";
	private static final ResourceLocation SHADOW_SPIDER_TEXTURE = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/" + ssName + "/" + ssName + ".png");
	
	public HellSpiderRender(EntityRendererProvider.Context context)
	{
		super(context, new SpiderModel(context.bakeLayer(ModelLayers.SPIDER)), 0.5f);
		this.addLayer(new HellSpiderSpikesLayer<HellSpiderRenderState, SpiderModel>(this, new HellSpiderSpikeModel(context.bakeLayer(AerialHellModelLayers.SPIDER_SPIKE))));
	}

	@Override public HellSpiderRenderState createRenderState() {return new HellSpiderRenderState();}

	@Override public void extractRenderState(T entity, HellSpiderRenderState renderState, float partialTick)
	{
		super.extractRenderState(entity, renderState, partialTick);
		renderState.base_texture = getTextureLocation(entity);
		renderState.scale = (entity instanceof HellSpiderEntity || entity instanceof ShadowSpiderEntity) ? 0.8F : 1.0F;
	}

	@Override protected void scale(HellSpiderRenderState renderState, PoseStack poseStack)
	{
		if (renderState.scale != 1.0F)
		{
			poseStack.scale(renderState.scale, renderState.scale, renderState.scale);
		}
	}

	@Override public ResourceLocation getTextureLocation(HellSpiderRenderState renderState) {return renderState.base_texture;}

	public ResourceLocation getTextureLocation(T entity)
	{
		if (entity instanceof HellSpiderEntity) {return HELL_SPIDER_TEXTURE;}
		else if (entity instanceof ShadowSpiderEntity) {return SHADOW_SPIDER_TEXTURE;}
		else {return CRYSTAL_SPIDER_TEXTURE;}
	}
}