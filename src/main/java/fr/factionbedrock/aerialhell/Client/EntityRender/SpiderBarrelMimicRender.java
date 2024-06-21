package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.SpiderBarrelMimicModel;
import fr.factionbedrock.aerialhell.Entity.Monster.BarrelMimic.AbstractBarrelMimicEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class SpiderBarrelMimicRender<T extends AbstractBarrelMimicEntity> extends MobRenderer<T, SpiderBarrelMimicModel<T>>
{
	private static final ResourceLocation SHADOW_PINE_TEXTURE = new ResourceLocation(AerialHell.MODID, "textures/entity/barrel_mimic/shadow_pine.png");

	public SpiderBarrelMimicRender(EntityRendererProvider.Context context)
	{
		super(context, new SpiderBarrelMimicModel(context.bakeLayer(AerialHellModelLayers.SPIDER_BARREL_MIMIC)), 1.0F);
	}

	@Override
	public ResourceLocation getTextureLocation(T mimic)
	{
		//if (mimic instanceof ShadowPineBarrelMimicEntity)
		//{
			return SHADOW_PINE_TEXTURE;
		//}
	}
}