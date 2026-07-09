package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.ShadowFlyingSkullModel;
import fr.factionbedrock.aerialhell.Entity.Monster.Shadow.ShadowFlyingSkullEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class ShadowFlyingSkullRender<T extends ShadowFlyingSkullEntity> extends MobRenderer<T, ShadowFlyingSkullModel<T>>
{
	private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/flying_skull/shadow_flying_skull.png");

	public ShadowFlyingSkullRender(EntityRendererProvider.Context context)
	{
		super(context, new ShadowFlyingSkullModel<T>(context.bakeLayer(AerialHellModelLayers.SHADOW_FLYING_SKULL)), 0.1F);
	}
	
	@Override public ResourceLocation getTextureLocation(T entity) {return TEXTURE;}
}