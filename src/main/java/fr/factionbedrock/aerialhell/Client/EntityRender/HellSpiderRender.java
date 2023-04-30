package fr.factionbedrock.aerialhell.Client.EntityRender;

import com.mojang.blaze3d.vertex.PoseStack;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityRender.Layers.HellSpiderSpikesLayer;
import fr.factionbedrock.aerialhell.Entity.Monster.HellSpiderEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.ShadowSpiderEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.SpiderModel;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.resources.ResourceLocation;

public class HellSpiderRender<T extends Spider> extends MobRenderer<T, SpiderModel<T>>
{
	private static String hsName = "hell_spider";
	private static final ResourceLocation HELL_SPIDER_TEXTURE = new ResourceLocation(AerialHell.MODID, "textures/entity/" + hsName + "/" + hsName + ".png");
	private static String csName = "crystal_spider";
	private static final ResourceLocation CRYSTAL_SPIDER_TEXTURE = new ResourceLocation(AerialHell.MODID, "textures/entity/" + csName + "/" + csName + ".png");
	private static String ssName = "shadow_spider";
	private static final ResourceLocation SHADOW_SPIDER_TEXTURE = new ResourceLocation(AerialHell.MODID, "textures/entity/" + ssName + "/" + ssName + ".png");
	
	public HellSpiderRender(EntityRendererManager renderManager)
	{
		super(renderManager, new SpiderModel<>(), 0.5f);
		this.addLayer(new HellSpiderSpikesLayer<T, SpiderModel<T>>(this));
	}
	
	@Override
	protected void preRenderCallback(T entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime)
	{
		if (entitylivingbaseIn instanceof HellSpiderEntity || entitylivingbaseIn instanceof ShadowSpiderEntity)
		{
			float f = 0.8F;
			matrixStackIn.scale(f, f, f);
		}
	}
	
	@Override
	public ResourceLocation getEntityTexture(T entity)
	{
		if (entity instanceof HellSpiderEntity)
		{
			return HELL_SPIDER_TEXTURE;
		}
		else if (entity instanceof ShadowSpiderEntity)
		{
			return SHADOW_SPIDER_TEXTURE;
		}
		else
		{
			return CRYSTAL_SPIDER_TEXTURE;
		}
	}
}